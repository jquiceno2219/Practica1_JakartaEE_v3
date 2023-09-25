package com.example.practica1_jakartaee.services.impl;

import com.example.practica1_jakartaee.exceptions.ServiceJdbcException;
import com.example.practica1_jakartaee.mapping.dtos.StudentDto;
import com.example.practica1_jakartaee.repositories.Repository;
import com.example.practica1_jakartaee.repositories.impl.StudentRepositoryJdbcImpl;
import com.example.practica1_jakartaee.repositories.impl.StudentRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.StudentService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
public class StudentServiceImpl implements StudentService {

    private Repository<StudentDto> studentRepository;
    public StudentServiceImpl(Connection connection) {
        this.studentRepository = new StudentRepositoryJdbcImpl(connection);
    }

    @Override
    public List<StudentDto> list() {
        try {
            return studentRepository.list();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),
                    throwables.getCause());
        }
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