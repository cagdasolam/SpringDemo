package com.example.demo.services;

import com.example.demo.entity.Option;
import com.example.demo.repos.OptionRepo;
import com.example.demo.repos.ResultRepo;
import com.example.demo.responses.OptionResultResponse;
import com.example.demo.responses.ResultResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultService {

    private final ResultRepo resultRepo;
    private final OptionRepo optionRepo;

    public ResultService(ResultRepo resultRepo, OptionRepo optionRepo) {
        this.resultRepo = resultRepo;
        this.optionRepo = optionRepo;
    }

    public List<OptionResultResponse> getAllResults(Optional<Long> surveyId) {
        List<Option> list;
        if (surveyId.isPresent()){
            list = optionRepo.findBySurveyId(surveyId.get());
        }else {
            list = optionRepo.findAll();
        }
        return list.stream().map(option -> new OptionResultResponse(option, resultRepo)).collect(Collectors.toList());
    }
}
