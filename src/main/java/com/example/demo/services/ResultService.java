package com.example.demo.services;

import com.example.demo.entity.Option;
import com.example.demo.entity.Result;
import com.example.demo.entity.Survey;
import com.example.demo.entity.User;
import com.example.demo.repos.OptionRepo;
import com.example.demo.repos.ResultRepo;
import com.example.demo.request.ResultCreateRequest;
import com.example.demo.responses.OptionResultResponse;
import com.example.demo.responses.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultService {

    private final ResultRepo resultRepo;
    private final OptionRepo optionRepo;
    private UserService userService;

    public ResultService(ResultRepo resultRepo, OptionRepo optionRepo, UserService userService, SurveyService surveyService) {
        this.resultRepo = resultRepo;
        this.optionRepo = optionRepo;
        this.userService = userService;
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


    public Result saveAnswer(Result resultToSave){
        return resultRepo.save(resultToSave);
    }

}
