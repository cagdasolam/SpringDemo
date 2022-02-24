package com.example.demo.responses;

import com.example.demo.entity.Option;
import com.example.demo.entity.Result;
import com.example.demo.entity.Survey;
import lombok.Data;

import java.util.List;

@Data
public class ResultResponse {

    Long id;
    String text;
    List<OptionResultResponse> results;

    public ResultResponse(Survey entity, List<OptionResultResponse> results){
        this.id = entity.getId();
        this.text = entity.getText();
        this.results = results;
    }

}
