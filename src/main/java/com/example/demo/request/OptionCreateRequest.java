package com.example.demo.request;

import lombok.Data;

@Data
public class OptionCreateRequest {

    private Long id;
    private String text;
    private Long surveyId;
    private Long userId;
}
