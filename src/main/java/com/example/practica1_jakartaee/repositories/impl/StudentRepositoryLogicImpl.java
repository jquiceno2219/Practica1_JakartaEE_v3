package com.example.practica1_jakartaee.repositories.impl;

import com.example.practica1_jakartaee.domain.enums.Career;
import com.example.practica1_jakartaee.domain.model.Student;
import com.example.practica1_jakartaee.exceptions.UniversityException;
import com.example.practica1_jakartaee.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryLogicImpl implements Repository<Student> {
    private List<Student> students;

    public StudentRepositoryLogicImpl() {
        Student s1 = new Student(1L,"Monica", "1", Career.SOFTWARE);
        Student s2 = new Student(2L,"Pepe", "1", Career.SOFTWARE);
        Student s3 = new Student(3L,"Juan", "2", Career.INDUSTRIAL);
        students = new ArrayList<>(List.of(s1, s2, s3));
    }

    @Override
    public List<Student> listar() {
        return students;
    }

    @Override
    public Student porId(Long id) {
        return students.stream()
                .filter(e->id.equals(e.getId()))
                .findFirst()
                .orElseThrow(()-> new UniversityException("Student not found"));
    }

    @Override
    public void guardar(Student student) {
        students.add(student);
    }

    @Override
    public void eliminar(Long id) {
        students.removeIf(e->e.getId().equals(id));
    }
}