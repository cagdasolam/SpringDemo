package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "options")
@Data
public class Option {
    @Id
    Long id;
    Long surveyId;

    @Lob
    @Column(columnDefinition = "text")
    private String text;
}
