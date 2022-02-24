package com.example.demo.responses;

import com.example.demo.entity.Option;
import lombok.Data;

@Data
public class OptionResponse {

    Long id;
    String text;

    public OptionResponse(Option entity){
        this.id = entity.getId();
        this.text = entity.getText();
    }

}
