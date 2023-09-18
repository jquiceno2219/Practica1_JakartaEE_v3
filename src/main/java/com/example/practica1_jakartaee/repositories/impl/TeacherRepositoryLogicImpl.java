package com.example.practica1_jakartaee.repositories.impl;

import com.example.practica1_jakartaee.domain.model.Teacher;
import com.example.practica1_jakartaee.exceptions.UniversityException;
import com.example.practica1_jakartaee.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryLogicImpl implements Repository<Teacher> {

    private List<Teacher> teachers;
    public TeacherRepositoryLogicImpl(){
        Teacher teacher1 = new Teacher(1L, "Mónica", "Monica@mail.com");
        Teacher teacher2 = new Teacher(2L, "Arle", "Arle@mail.com");
        Teacher teacher3 = new Teacher(3L, "Javier", "Javier@mail.com");
        teachers = new ArrayList<>(List.of(teacher1, teacher2, teacher3));
    }
    @Override
    public List<Teacher> listar() {
        return teachers;
    }

    @Override
    public Teacher porId(Long id) {
        return teachers.stream()
                .filter(e->id.equals(e.getId()))
                .findFirst()
                .orElseThrow(()-> new UniversityException("Teacher not found"));
    }

    @Override
    public void guardar(Teacher teacher) {
        teachers.add(teacher);

    }

    @Override
    public void eliminar(Long id) {
        teachers.removeIf(e->e.getId().equals(id));}
}