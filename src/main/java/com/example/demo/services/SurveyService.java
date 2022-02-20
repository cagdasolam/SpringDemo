package com.example.demo.services;

import com.example.demo.entity.Survey;
import com.example.demo.repos.SurveyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    private final SurveyRepo surveyRepo;

    @Autowired
    public SurveyService(SurveyRepo surveyRepo){
        this.surveyRepo = surveyRepo;
    }

    public List<Survey> getUsers() {
        return surveyRepo.findAll();
    }

    public Survey getOneSurvey(Long surveyId) {
        return surveyRepo.findById(surveyId).orElse(null);
    }


    public void addSurvey(Survey survey) {
        Optional<Survey> surveyOptional = surveyRepo.findById(survey.getId());
        if (surveyOptional.isPresent()){
            throw new IllegalStateException("survey is already exist");
        }
        surveyRepo.save(survey);
    }

    public Survey updateSurvey(Long surveyId, Survey newSurvey) {
        Optional<Survey> survey = surveyRepo.findById(surveyId);
        if (survey.isPresent()){
            Survey foundSurvey =survey.get();
            foundSurvey.setText(newSurvey.getText());
            return surveyRepo.save(foundSurvey);
        }
        return null;
    }

    public void deleteSurvey(Long surveyId) {
        surveyRepo.deleteById(surveyId);
    }
}
