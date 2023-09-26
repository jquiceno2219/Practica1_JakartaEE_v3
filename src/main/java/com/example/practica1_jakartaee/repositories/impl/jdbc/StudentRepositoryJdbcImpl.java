package com.example.practica1_jakartaee.repositories.impl.jdbc;
import com.example.practica1_jakartaee.domain.enums.Career;
import com.example.practica1_jakartaee.domain.model.Student;
import com.example.practica1_jakartaee.exceptions.ServiceJdbcException;
import com.example.practica1_jakartaee.mapping.dtos.StudentDto;
import com.example.practica1_jakartaee.mapping.mappers.StudentMapper;
import com.example.practica1_jakartaee.repositories.Repository;
import com.example.practica1_jakartaee.utils.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentRepositoryJdbcImpl implements Repository<StudentDto> {
    private Connection conn;
    public StudentRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<StudentDto> list(){
        List<StudentDto> students = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT s.* order by s.id ASC")) {
            while (rs.next()) {
                StudentDto ps= createStudent(rs);
                students.add(ps);
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("Error recovering information from database", e.getCause());
        }
        return students;
    }

    private StudentDto createStudent(ResultSet rs) throws SQLException{
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setSemester(rs.getString("semester"));
        student.setCareer(Career.valueOf(rs.getString("career")));
        return StudentMapper.mapFrom(student);
    }

    @Override
    public StudentDto findById(Long id) {
        StudentDto student = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM students WHERE id =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = createStudent(resultSet);
            }
            resultSet.close();
        } catch (SQLException e){
            throw new ServiceJdbcException("Unable to find id", e.getCause());
        }
        return student;
    }

    @Override
    public void save(StudentDto student) {
        String sql;

        if (student.id() != null && student.id() > 0) {
            sql = "UPDATE students SET name=?, semester=?, career=? WHERE id=?";
        } else {
            sql = "INSERT INTO student(name, semester, career) VALUES(?, ?, ?, ?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.name());
            stmt.setString(2, student.semester());
            stmt.setString(3, String.valueOf(student.career()));

            if (student.id() > 0) {
                stmt.setLong(4, student.id());
            }

            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException("Unable to save info", throwables.getCause());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM students WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to delete", e.getCause());
        }
    }
}
