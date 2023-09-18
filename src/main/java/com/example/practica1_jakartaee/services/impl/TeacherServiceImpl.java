package com.example.practica1_jakartaee.services.impl;

import com.example.practica1_jakartaee.domain.model.Teacher;
import com.example.practica1_jakartaee.repositories.impl.TeacherRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryLogicImpl repository;

    public TeacherServiceImpl(TeacherRepositoryLogicImpl repository) {
        this.repository = repository;
    }
    @Override
    public List<Teacher> listar() {
        return repository.listar();
    }

    @Override
    public Teacher porId(Long id) {
        return repository.porId(id);
    }

    @Override
    public void guardar(Teacher t) {
        repository.guardar(t);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
