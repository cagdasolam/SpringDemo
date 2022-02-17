package com.example.demo.survey;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SurveyConfig {

    @Bean
    CommandLineRunner commandLineRunner(SurveyRepository repository){
        return args -> {
            Survey example = new Survey("When do you think COVİD-19 will be finished?");

            repository.saveAll(List.of(example));
        };
    }
}
