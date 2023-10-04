package com.example.practica1_jakartaee.domain.model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Grades {
    private Long id;
    private Student student;
    private Subject subject;
    private double grade;
    private String term;
}