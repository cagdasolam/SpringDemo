package com.example.demo.request;

import lombok.Data;

@Data

public class ResultCreateRequest {
    private Long id;
    private Long surveyId;
    private Long optionId;
    private Long userId;

}
