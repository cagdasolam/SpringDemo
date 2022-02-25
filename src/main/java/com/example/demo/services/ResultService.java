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
    private SurveyService surveyService;

    public ResultService(ResultRepo resultRepo, OptionRepo optionRepo, UserService userService, SurveyService surveyService) {
        this.resultRepo = resultRepo;
        this.optionRepo = optionRepo;
        this.userService = userService;
        this.surveyService = surveyService;
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

    public Result addResult(ResultCreateRequest resultCreateRequest) {
        UserResponse user = userService.getOneUser(resultCreateRequest.getUserId());
        Option option = optionRepo.findById(resultCreateRequest.getOptionId()).get();
        Survey survey = surveyService.getOneSurvey(resultCreateRequest.getSurveyId());
        if (user == null || option == null)
            return null;
        Result resultToSave = new Result();
        resultToSave.setId(resultCreateRequest.getId());
        resultToSave.setOption(option);
        resultToSave.setSurvey(survey);
        return resultRepo.save(resultToSave);
    }

}
