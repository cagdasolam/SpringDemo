package com.example.demo.services;

import com.example.demo.entity.Survey;
import com.example.demo.entity.User;
import com.example.demo.repos.SurveyRepo;
import com.example.demo.request.SurveyCreateRequest;
import com.example.demo.request.SurveyUpdateRequest;
import com.example.demo.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    private SurveyRepo surveyRepo;
    private UserService userService;
    private OptionService optionService;
    private ResultService resultService;

    @Autowired
    public SurveyService(SurveyRepo surveyRepo, UserService userService,
                         @Lazy OptionService optionService, @Lazy ResultService resultService){
        this.surveyRepo = surveyRepo;
        this.userService = userService;
        this.optionService = optionService;
        this.resultService = resultService;
    }



    public List<Survey> getSurveys() {
        return surveyRepo.findAll();
    }

    public Survey getOneSurvey(Long surveyId) {
        return surveyRepo.findById(surveyId).orElse(null);
    }


    public Survey addSurvey(SurveyCreateRequest newSurvey) {
        UserResponse userResponse = userService.getOneUser(newSurvey.getUserId());
        if (userResponse == null)
            return null;
        Survey toSave = new Survey();
        toSave.setId(newSurvey.getId());
        toSave.setText(newSurvey.getText());

        return surveyRepo.save(toSave);
    }

    public Survey updateSurvey(Long surveyId, SurveyUpdateRequest newSurvey) {
        Optional<Survey> survey = surveyRepo.findById(surveyId);
        if (survey.isPresent()){
            Survey foundSurvey = survey.get();
            foundSurvey.setText(newSurvey.getText());
            surveyRepo.save(foundSurvey);
            return foundSurvey;
        }
        return null;
    }

    public void deleteSurvey(Long surveyId) {
        surveyRepo.deleteById(surveyId);
    }

    public SurveyResponse getOneSurveyWithOptions(Long surveyId) {
        Survey survey = surveyRepo.findById(surveyId).orElse(null);
        List<OptionResponse> options = optionService.getAllOptions(Optional.of(surveyId));
        return new SurveyResponse(survey, options);
    }

    public ResultResponse getResultsOneSurvey(Long surveyId) {
        Survey survey = surveyRepo.findById(surveyId).orElse(null);
        List<OptionResultResponse> responses = resultService.getAllResults(Optional.of(surveyId));
        return new ResultResponse(survey, responses);
    }
}
