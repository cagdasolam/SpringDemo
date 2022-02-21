package com.example.demo.request;

import lombok.Data;

@Data
public class SurveyCreateRequest {

    private Long id;
    private String text;
    private Long userId;
}
