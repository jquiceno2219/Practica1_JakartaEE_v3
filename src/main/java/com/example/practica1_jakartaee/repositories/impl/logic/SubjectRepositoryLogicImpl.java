package com.example.practica1_jakartaee.repositories.impl.logic;

import com.example.practica1_jakartaee.domain.model.Teacher;
import com.example.practica1_jakartaee.exceptions.UniversityException;
import com.example.practica1_jakartaee.mapping.dtos.SubjectDto;
import com.example.practica1_jakartaee.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryLogicImpl implements Repository<SubjectDto> {
    private List<SubjectDto> subjects;
    TeacherRepositoryLogicImpl teacherLog = new TeacherRepositoryLogicImpl();

    public SubjectRepositoryLogicImpl(){
        SubjectDto s1 = new SubjectDto(1L, "Mate", new Teacher(1L, "Mónica", "Mónica@mail.com"));
        SubjectDto s2 = new SubjectDto(1L, "Programación", new Teacher(2L, "Arle", "Arle@mail.com"));
        SubjectDto s3 = new SubjectDto(1L, "Ciencias", new Teacher(3L, "Javier", "Javier@mail.com"));
        subjects = new ArrayList<>(List.of(s1,s2,s3));
    }

    @Override
    public List<SubjectDto> list() {
        return subjects;
    }

    @Override
    public SubjectDto findById(Long id) {
        return subjects.stream()
                .filter(e->id.equals(e.id()))
                .findFirst()
                .orElseThrow(()-> new UniversityException("Subject not found"));
    }

    @Override
    public void save(SubjectDto subject) {
        subjects.add(subject);
    }

    @Override
    public void delete(Long id) {
        subjects.removeIf(e->e.id().equals(id));
    }
}