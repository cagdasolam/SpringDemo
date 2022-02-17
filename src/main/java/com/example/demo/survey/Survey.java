package com.example.demo.survey;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Survey {

    @Id
    @SequenceGenerator(
            name = "survey_sequence",
            sequenceName = "survey_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "survey_sequence"
    )
    private long id;

    private final String question;

    private List<String> options;

    public Survey(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getQuestion() {
        return question;
    }

    public void addOption(String option){
        options.add(option);
    }

    public long getId() {
        return id;
    }




}
