package com.example.demo.controller;

import com.example.demo.entity.Survey;
import com.example.demo.request.SurveyCreateRequest;
import com.example.demo.request.SurveyUpdateRequest;
import com.example.demo.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveys")
public class SurveyController {
    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService){
        this.surveyService = surveyService;
    }

    @GetMapping
    public List<Survey> getSurvey(){
        return surveyService.getSurveys();
    }

    @GetMapping("/{surveyId}")
    public Survey getOneSurvey(@PathVariable Long surveyId){
        return surveyService.getOneSurvey(surveyId);
    }

    @PostMapping
    public Survey registerNewSurvey(@RequestBody SurveyCreateRequest newSurvey){
        return surveyService.addSurvey(newSurvey);
    }

    @PutMapping("/{surveyId}")
    public Survey updateSurvey(@PathVariable Long surveyId, @RequestBody SurveyUpdateRequest newSurvey){
    return  surveyService.updateSurvey(surveyId, newSurvey);
    }

    @DeleteMapping("/{surveyId}")
    public void deleteSurvey( @PathVariable Long surveyId){
        surveyService.deleteSurvey(surveyId);
    }
}
