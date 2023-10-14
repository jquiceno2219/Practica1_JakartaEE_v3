package com.example.practica1_jakartaee.services.impl;

import com.example.practica1_jakartaee.mapping.dtos.GradesDto;
import com.example.practica1_jakartaee.repositories.Repository;
import com.example.practica1_jakartaee.repositories.impl.jdbc.GradesRepositoryJdbcImpl;
import com.example.practica1_jakartaee.services.GradesService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.sql.Connection;
import java.util.List;

@ApplicationScoped
public class GradesServiceImpl implements GradesService {
    @Inject
    @Named("gradeRepo")
    private Repository<GradesDto> repository;

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
