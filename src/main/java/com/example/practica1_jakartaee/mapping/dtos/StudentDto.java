package com.example.practica1_jakartaee.mapping.dtos;

import com.example.practica1_jakartaee.domain.enums.Career;

public record StudentDto(Long id,
                         String name,
                         String semester,
                         Career career) {
}
