package com.example.demo.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.user.User;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/survey")
public class SurveyController {

    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping
    public List<Survey> getSurveys(){
        return surveyService.getSurveys();
    }

    @GetMapping
    public List<String> getOptions(Survey survey){
        return surveyService.getOptions(survey);
    }

    @PostMapping
    public void registerNewSurvey(@RequestBody Survey survey, User user){
        surveyService.addSurvey(survey, user);
    }

    @PostMapping
    public void registerOptions(@RequestBody Survey survey, User user, String option){
        surveyService.addOption(survey, user, option);
    }
}
