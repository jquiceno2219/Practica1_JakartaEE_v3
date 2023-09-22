package com.example.practica1_jakartaee.repositories.impl;

import com.example.practica1_jakartaee.domain.model.Teacher;
import com.example.practica1_jakartaee.exceptions.UniversityException;
import com.example.practica1_jakartaee.mapping.dtos.TeacherDto;
import com.example.practica1_jakartaee.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryLogicImpl implements Repository<TeacherDto> {

    private List<TeacherDto> teachers;
    public TeacherRepositoryLogicImpl(){
        TeacherDto teacher1 = new TeacherDto(1L, "Mónica", "Monica@mail.com");
        TeacherDto teacher2 = new TeacherDto(2L, "Arle", "Arle@mail.com");
        TeacherDto teacher3 = new TeacherDto(3L, "Javier", "Javier@mail.com");
        teachers = new ArrayList<>(List.of(teacher1, teacher2, teacher3));
    }
    @Override
    public List<TeacherDto> list() {
        return teachers;
    }

    @Override
    public TeacherDto findById(Long id) {
        return teachers.stream()
                .filter(e->id.equals(e.id()))
                .findFirst()
                .orElseThrow(()-> new UniversityException("Teacher not found"));
    }

    @Override
    public void save(TeacherDto teacher) {
        teachers.add(teacher);

    }

    @Override
    public void delete(Long id) {
        teachers.removeIf(e->e.id().equals(id));}
}