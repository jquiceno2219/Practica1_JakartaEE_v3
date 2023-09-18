package com.example.practica1_jakartaee.services;

import com.example.practica1_jakartaee.domain.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> listar();

    Student porId(Long id);

    void guardar(Student t);

    void eliminar(Long id);
}