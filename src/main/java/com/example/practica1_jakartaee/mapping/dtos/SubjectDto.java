package com.example.practica1_jakartaee.mapping.dtos;

import com.example.practica1_jakartaee.domain.model.Teacher;
import lombok.Builder;


@Builder
public record SubjectDto(Long id,
                         String name,
                         Teacher teacher) {
}
