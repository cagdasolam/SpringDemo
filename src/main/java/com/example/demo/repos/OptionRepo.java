package com.example.demo.repos;

import com.example.demo.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepo extends JpaRepository<Option, Long> {

    List<Option> findBySurveyId(Long surveyID);
}
