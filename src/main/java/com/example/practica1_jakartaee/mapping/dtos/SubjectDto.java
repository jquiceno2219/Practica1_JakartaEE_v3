package com.example.practica1_jakartaee.mapping.dtos;

import com.example.practica1_jakartaee.domain.model.Teacher;

public record SubjectDto(Long id,
                         String name,
                         Teacher teacher) {
}
