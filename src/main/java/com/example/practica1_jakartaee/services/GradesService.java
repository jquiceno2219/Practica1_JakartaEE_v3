package com.example.practica1_jakartaee.services;

import com.example.practica1_jakartaee.mapping.dtos.GradesDto;

import java.util.List;

public interface GradesService {
        List<GradesDto> list();
        GradesDto findById(Long id);

        void save(GradesDto t);

        void delete(Long id);
}
