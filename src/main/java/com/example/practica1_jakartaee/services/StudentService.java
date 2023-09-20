package com.example.practica1_jakartaee.services;

import com.example.practica1_jakartaee.domain.model.Student;
import com.example.practica1_jakartaee.mapping.dtos.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> listar();

    StudentDto porId(Long id);

    void guardar(StudentDto t);

    void eliminar(Long id);
}