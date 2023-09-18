package com.example.practica1_jakartaee.services;

import com.example.practica1_jakartaee.domain.model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> listar();

    Subject porId(Long id);

    void guardar(Subject t);

    void eliminar(Long id);
}
