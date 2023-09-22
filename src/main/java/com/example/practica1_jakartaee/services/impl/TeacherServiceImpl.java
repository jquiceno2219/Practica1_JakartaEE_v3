package com.example.practica1_jakartaee.services.impl;

import com.example.practica1_jakartaee.mapping.dtos.TeacherDto;
import com.example.practica1_jakartaee.repositories.impl.TeacherRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryLogicImpl repository;

    public TeacherServiceImpl(TeacherRepositoryLogicImpl repository) {
        this.repository = repository;
    }
    @Override
    public List<TeacherDto> list() {
        return repository.list();
    }

    @Override
    public TeacherDto findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(TeacherDto t) {
        repository.save(t);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
