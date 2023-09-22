package com.example.practica1_jakartaee.services;

import com.example.practica1_jakartaee.mapping.dtos.TeacherDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> list();

    TeacherDto findById(Long id);

    void save(TeacherDto t);

    void delete(Long id);
}