package com.example.practica1_jakartaee.repositories.impl;

import com.example.practica1_jakartaee.domain.model.Subject;
import com.example.practica1_jakartaee.domain.model.Teacher;
import com.example.practica1_jakartaee.exceptions.UniversityException;
import com.example.practica1_jakartaee.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryLogicImpl implements Repository<Subject> {
    private List<Subject> subjects;
    TeacherRepositoryLogicImpl teacherLog = new TeacherRepositoryLogicImpl();

    public SubjectRepositoryLogicImpl(){
        Subject s1 = new Subject(1L, "Mate", new Teacher(1L, "Mónica", "Mónica@mail.com"));
        Subject s2 = new Subject(1L, "Programación", new Teacher(2L, "Arle", "Arle@mail.com"));
        Subject s3 = new Subject(1L, "Ciencias", new Teacher(3L, "Javier", "Javier@mail.com"));
        subjects = new ArrayList<>(List.of(s1,s2,s3));
    }

    @Override
    public List<Subject> listar() {
        return subjects;
    }

    @Override
    public Subject porId(Long id) {
        return subjects.stream()
                .filter(e->id.equals(e.getId()))
                .findFirst()
                .orElseThrow(()-> new UniversityException("Subject not found"));
    }

    @Override
    public void guardar(Subject subject) {
        subjects.add(subject);
    }

    @Override
    public void eliminar(Long id) {
        subjects.removeIf(e->e.getId().equals(id));
    }
}