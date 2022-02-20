package com.example.demo.repos;

import com.example.demo.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepo extends JpaRepository<Survey, Long> {
}
