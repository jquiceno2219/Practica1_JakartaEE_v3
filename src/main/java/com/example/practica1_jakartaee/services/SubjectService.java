package com.example.practica1_jakartaee.services;

import com.example.practica1_jakartaee.mapping.dtos.SubjectDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> list();

    SubjectDto findById(Long id);

    void save(SubjectDto t);

    void delete(Long id);
}
