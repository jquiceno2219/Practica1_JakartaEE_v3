package com.example.practica1_jakartaee.repositories.impl.logic;

import com.example.practica1_jakartaee.domain.enums.Career;
import com.example.practica1_jakartaee.exceptions.UniversityException;
import com.example.practica1_jakartaee.mapping.dtos.StudentDto;
import com.example.practica1_jakartaee.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryLogicImpl implements Repository<StudentDto> {
    private List<StudentDto> students;

    public StudentRepositoryLogicImpl() {
        StudentDto s1 = new StudentDto(1L,"Monica", "1", Career.SOFTWARE);
        StudentDto s2 = new StudentDto(2L,"Pepe", "1", Career.SOFTWARE);
        StudentDto s3 = new StudentDto(3L,"Juan", "2", Career.INDUSTRIAL);
        students = new ArrayList<>(List.of(s1, s2, s3));
    }

    @Override
    public List<StudentDto> list() {
        return students;
    }

    @Override
    public StudentDto findById(Long id) {
        return students.stream()
                .filter(e->id.equals(e.id()))
                .findFirst()
                .orElseThrow(()-> new UniversityException("Student not found"));
    }

    @Override
    public void save(StudentDto student) {
        students.add(student);
    }

    @Override
    public void delete(Long id) {
        students.removeIf(e->e.id().equals(id));
    }
}