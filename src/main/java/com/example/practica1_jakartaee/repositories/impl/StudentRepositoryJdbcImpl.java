package com.example.practica1_jakartaee.repositories.impl;
import com.example.practica1_jakartaee.domain.model.Student;
import com.example.practica1_jakartaee.mapping.dtos.StudentDto;
import com.example.practica1_jakartaee.mapping.mappers.StudentMapper;
import com.example.practica1_jakartaee.repositories.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StudentRepositoryJdbcImpl implements Repository<StudentDto> {
    private Connection conn;
    public StudentRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public List<StudentDto> list(){
        List<Student> students = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT s.* order by s.id ASC")) {
            while (rs.next()) {
                Student ps= getStudent(rs);
                students.add(ps);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return StudentMapper.mapFrom(students);
    }

    private Student getStudent(ResultSet rs) {

    }

    @Override
    public StudentDto findById(Long id) {
        return null;
    }

    @Override
    public void save(StudentDto studentDto) {

    }

    @Override
    public void delete(Long id) {

    }
//demas metodos
}
