package com.example.practica1_jakartaee.services.impl;

import com.example.practica1_jakartaee.domain.model.Student;
import com.example.practica1_jakartaee.repositories.impl.StudentRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.StudentService;

import java.util.List;
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryLogicImpl repository;

    public StudentServiceImpl(StudentRepositoryLogicImpl repository) {
        this.repository = repository;
    }
    @Override
    public List<Student> listar() {
        return repository.listar();
    }

    @Override
    public Student porId(Long id) {
        return repository.porId(id);
    }

    @Override
    public void guardar(Student t) {
        repository.guardar(t);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}