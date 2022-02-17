package com.example.demo.survey;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    private final UserRepository userRepository;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository, UserRepository userRepository) {
        this.surveyRepository = surveyRepository;
        this.userRepository = userRepository;
    }

    public List<Survey> getSurveys() {
        return surveyRepository.findAll();
    }

    public List<String> getOptions(Survey survey){
        List<Survey> surveys = getSurveys();
        for (Survey sur :
                surveys) {
            if (sur.equals(survey)){
                return survey.getOptions();
            }
        }
        return null;
    }

    public void addSurvey(Survey survey, User user) {
        Optional<Survey> surveyOptional = surveyRepository.findSurveyById(survey.getId());
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());

        if (surveyOptional.isPresent() && userOptional.isPresent()){
            throw new IllegalStateException();
        }

        if (user.getUserRole() != UserRole.Admin){
            throw new IllegalStateException("user is not admin");
        }

        surveyRepository.save(survey);
    }

    public void addOption(Survey survey, User user, String option) {
        Optional<Survey> surveyOptional = surveyRepository.findSurveyById(survey.getId());
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());

        if (surveyOptional.isPresent() && userOptional.isPresent()){
            throw new IllegalStateException();
        }

        if (user.getUserRole() != UserRole.Admin){
            throw new IllegalStateException("user is not admin");
        }

        List<Survey> surveys = getSurveys();
        for (Survey sur :
                surveys) {
            if (sur.equals(survey)){
                survey.addOption(option);
            }
        }
    }
}
