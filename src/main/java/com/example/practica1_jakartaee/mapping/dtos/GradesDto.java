package com.example.practica1_jakartaee.mapping.dtos;

import com.example.practica1_jakartaee.domain.model.Student;
import com.example.practica1_jakartaee.domain.model.Subject;

public record GradesDto(long id, Student student, Subject subject, double grade, String term) {
}