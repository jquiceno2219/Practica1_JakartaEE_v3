package com.example.practica1_jakartaee.services.impl;

import com.example.practica1_jakartaee.mapping.dtos.StudentDto;
import com.example.practica1_jakartaee.repositories.Repository;
import com.example.practica1_jakartaee.repositories.impl.jdbc.StudentRepositoryJdbcImpl;
import com.example.practica1_jakartaee.repositories.impl.logic.StudentRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.StudentService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.sql.Connection;
import java.util.List;
@ApplicationScoped
public class StudentServiceImpl implements StudentService {
    @Inject
    @Named("studentRepo")
    private Repository<StudentDto> repository;

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