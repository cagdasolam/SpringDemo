package com.example.demo.controller;

import com.example.demo.entity.Option;
import com.example.demo.entity.Result;
import com.example.demo.entity.Survey;
import com.example.demo.responses.OptionResultResponse;
import com.example.demo.responses.ResultResponse;
import com.example.demo.services.ResultService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/results")
public class ResultController {

    private final ResultService resultService;


    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public List<OptionResultResponse> getResults(@RequestParam Optional<Long> surveyId){
        return resultService.getAllResults(surveyId);
    }
}
