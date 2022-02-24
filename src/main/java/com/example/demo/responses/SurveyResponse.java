package com.example.demo.responses;

import com.example.demo.entity.Option;
import com.example.demo.entity.Survey;
import lombok.Data;

import java.util.List;

@Data
public class SurveyResponse {

    Long id;
    String text;
    List<OptionResponse> options;

    public SurveyResponse(Survey survey, List<OptionResponse> options){
        this.id = survey.getId();
        this.text = survey.getText();
        this.options = options;
    }

}
