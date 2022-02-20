package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "surveys")
@Data
public class Survey {
    @Id
    Long id;

    @Lob
    @Column(columnDefinition = "text")
    private String text;
}
