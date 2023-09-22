package com.example.practica1_jakartaee.services.impl;

import com.example.practica1_jakartaee.mapping.dtos.StudentDto;
import com.example.practica1_jakartaee.repositories.impl.StudentRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.StudentService;

import java.util.List;
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryLogicImpl repository;

    public StudentServiceImpl(StudentRepositoryLogicImpl repository) {
        this.repository = repository;
    }
    @Override
    public List<StudentDto> list() {
        return repository.list();
    }

    @Override
    public StudentDto findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(StudentDto t) {
        repository.save(t);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}