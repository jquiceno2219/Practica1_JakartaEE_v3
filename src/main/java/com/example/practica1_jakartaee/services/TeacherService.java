package com.example.practica1_jakartaee.services;

import com.example.practica1_jakartaee.domain.model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> listar();

    Teacher porId(Long id);

    void guardar(Teacher t);

    void eliminar(Long id);
}