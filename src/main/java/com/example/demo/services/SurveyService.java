package com.example.demo.services;

import com.example.demo.entity.Survey;
import com.example.demo.entity.User;
import com.example.demo.repos.SurveyRepo;
import com.example.demo.request.SurveyCreateRequest;
import com.example.demo.request.SurveyUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    private final SurveyRepo surveyRepo;
    private final UserService userService;


    @Autowired
    public SurveyService(SurveyRepo surveyRepo, UserService userService){
        this.surveyRepo = surveyRepo;
        this.userService = userService;
    }

    public List<Survey> getSurveys() {
        return surveyRepo.findAll();
    }

    public Survey getOneSurvey(Long surveyId) {
        return surveyRepo.findById(surveyId).orElse(null);
    }


    public Survey addSurvey(SurveyCreateRequest newSurvey) {
        User user = userService.getOneUser(newSurvey.getUserId());
        if (user == null)
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
}
