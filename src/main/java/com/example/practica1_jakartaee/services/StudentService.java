package com.example.practica1_jakartaee.services;

import com.example.practica1_jakartaee.mapping.dtos.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> list();

    StudentDto findById(Long id);

    void save(StudentDto t);

    void delete(Long id);
}