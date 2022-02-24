package com.example.demo.repos;

import com.example.demo.entity.Option;
import com.example.demo.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepo extends JpaRepository<Result, Long> {
    List<Result> findBySurveyId(Long surveyId);

    List<Result> findByOptionId(Long optionId);
}
