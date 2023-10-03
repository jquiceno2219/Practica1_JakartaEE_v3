package com.example.practica1_jakartaee.mapping.dtos;

import lombok.Builder;

@Builder
public record TeacherDto(Long id,
                         String name,
                         String email) {
}
