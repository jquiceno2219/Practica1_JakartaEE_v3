package com.example.practica1_jakartaee.services.impl;

import com.example.practica1_jakartaee.mapping.dtos.SubjectDto;
import com.example.practica1_jakartaee.repositories.impl.SubjectRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepositoryLogicImpl repository;
    public SubjectServiceImpl(SubjectRepositoryLogicImpl repository){
        this.repository = repository;
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