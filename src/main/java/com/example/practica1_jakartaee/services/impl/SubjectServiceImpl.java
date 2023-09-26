package com.example.practica1_jakartaee.services.impl;

import com.example.practica1_jakartaee.mapping.dtos.StudentDto;
import com.example.practica1_jakartaee.mapping.dtos.SubjectDto;
import com.example.practica1_jakartaee.mapping.dtos.TeacherDto;
import com.example.practica1_jakartaee.repositories.Repository;
import com.example.practica1_jakartaee.repositories.impl.jdbc.StudentRepositoryJdbcImpl;
import com.example.practica1_jakartaee.repositories.impl.jdbc.SubjectRepositoryJdbcImpl;
import com.example.practica1_jakartaee.repositories.impl.logic.SubjectRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.SubjectService;

import java.sql.Connection;
import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private Repository<SubjectDto> repository;
    public SubjectServiceImpl(Connection connection) {
        this.repository = new SubjectRepositoryJdbcImpl(connection);
    }
    @Override
    public List<SubjectDto> list() {
        return repository.list();
    }

    @Override
    public SubjectDto findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(SubjectDto t) {
        repository.save(t);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}