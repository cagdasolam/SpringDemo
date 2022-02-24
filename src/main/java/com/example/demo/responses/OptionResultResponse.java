package com.example.demo.responses;

import com.example.demo.entity.Option;
import com.example.demo.repos.ResultRepo;
import lombok.Data;

@Data
public class OptionResultResponse {

    Long id;
    String text;
    int result;

    public OptionResultResponse(Option entity, ResultRepo resultRepo) {
        this.id = entity.getId();
        this.text = entity.getText();
        this.result = resultRepo.findByOptionId(entity.getId()).size();
    }
}
