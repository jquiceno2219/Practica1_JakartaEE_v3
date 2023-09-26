package com.example.practica1_jakartaee.services.impl;

import com.example.practica1_jakartaee.mapping.dtos.StudentDto;
import com.example.practica1_jakartaee.mapping.dtos.TeacherDto;
import com.example.practica1_jakartaee.repositories.Repository;
import com.example.practica1_jakartaee.repositories.impl.jdbc.StudentRepositoryJdbcImpl;
import com.example.practica1_jakartaee.repositories.impl.jdbc.TeacherRepositoryJdbcImpl;
import com.example.practica1_jakartaee.repositories.impl.logic.TeacherRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.TeacherService;

import java.sql.Connection;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private Repository<TeacherDto> repository;

    public TeacherServiceImpl(Connection connection) {
        this.repository = new TeacherRepositoryJdbcImpl(connection);
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
