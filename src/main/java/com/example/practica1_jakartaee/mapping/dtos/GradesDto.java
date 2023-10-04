package com.example.practica1_jakartaee.mapping.dtos;

import com.example.practica1_jakartaee.domain.model.Student;
import com.example.practica1_jakartaee.domain.model.Subject;
import lombok.Builder;

@Builder
public record GradesDto(Long id, Student student, Subject subject, double grade, String term) {
}