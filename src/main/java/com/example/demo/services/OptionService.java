package com.example.demo.services;

import com.example.demo.entity.Option;
import com.example.demo.entity.Survey;
import com.example.demo.repos.OptionRepo;
import com.example.demo.request.OptionCreateRequest;
import com.example.demo.request.OptionUpdateRequest;
import com.example.demo.responses.OptionResponse;
import com.example.demo.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OptionService {

    private final OptionRepo optionRepo;
//    private final ResultRepo resultRepo;
    private final SurveyService surveyService;
    private final UserService userService;

    @Autowired
    public OptionService(OptionRepo optionRepo, SurveyService surveyService, UserService userService) {
        this.optionRepo = optionRepo;
//        this.resultRepo = resultRepo;
        this.surveyService = surveyService;
        this.userService = userService;
    }

    public List<OptionResponse> getAllOptions(Optional<Long> surveyId) {
        List<Option> list;
        if (surveyId.isPresent()){
            list = optionRepo.findBySurveyId(surveyId.get());
        }
        else{
            list = optionRepo.findAll();
        }
        return list.stream().map(option -> new OptionResponse(option)).collect(Collectors.toList());
    }

//    public List<OptionResultResponse> getAllResults(Optional<Long> surveyId) {
//        List<Option> list;
//        if (surveyId.isPresent()){
//            list = optionRepo.findBySurveyId(surveyId.get());
//        }else {
//            list = optionRepo.findAll();
//        }
//        return list.stream().map(option -> new OptionResultResponse(option, resultRepo)).collect(Collectors.toList());
//    }

    public Option getOneOption(Long optionId) {
        return optionRepo.findById(optionId).orElse(null);
    }

    public Option createOption(OptionCreateRequest optionCreateRequest) {
        UserResponse user = userService.getOneUser(optionCreateRequest.getUserId());
        Survey survey = surveyService.getOneSurvey(optionCreateRequest.getSurveyId());

        if (user != null && survey != null){
            Option optionToSave = new Option();
            optionToSave.setId(optionCreateRequest.getId());
            optionToSave.setSurvey(survey);
            optionToSave.setText(optionCreateRequest.getText());
            return optionRepo.save(optionToSave);
        }
        return null;
    }

    public Option updateOption(Long optionId, OptionUpdateRequest optionUpdateRequest) {
        Optional<Option> option = optionRepo.findById(optionId);
        if (option.isPresent()){
            Option optionToUpdate = option.get();
            optionToUpdate.setText(optionUpdateRequest.getText());
            optionRepo.save(optionToUpdate);
        }
        return null;
    }

    public void deleteOption(Long optionId) {
        optionRepo.deleteById(optionId);
    }

    public Optional<Option> findById(Long optionId){
        return optionRepo.findById(optionId);
    }
}
