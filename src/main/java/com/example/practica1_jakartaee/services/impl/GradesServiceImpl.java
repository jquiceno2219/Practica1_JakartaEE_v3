package com.example.practica1_jakartaee.services.impl;

import com.example.practica1_jakartaee.mapping.dtos.GradesDto;
import com.example.practica1_jakartaee.repositories.Repository;
import com.example.practica1_jakartaee.repositories.impl.jdbc.GradesRepositoryJdbcImpl;
import com.example.practica1_jakartaee.services.GradesService;

import java.sql.Connection;
import java.util.List;

public class GradesServiceImpl implements GradesService {
    private Repository<GradesDto> repository;

    public GradesServiceImpl(Connection connection){
        this.repository = new GradesRepositoryJdbcImpl(connection);
    }
    @Override
    public List<GradesDto> list() {
        return repository.list();
    }

    @Override
    public GradesDto findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(GradesDto t) {
        repository.save(t);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
