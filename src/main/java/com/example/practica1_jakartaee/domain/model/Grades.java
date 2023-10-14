package com.example.practica1_jakartaee.domain.model;
import jakarta.enterprise.context.SessionScoped;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@SessionScoped
public class Grades implements Serializable {
    private Long id;
    private Student student;
    private Subject subject;
    private double grade;
    private String term;
}