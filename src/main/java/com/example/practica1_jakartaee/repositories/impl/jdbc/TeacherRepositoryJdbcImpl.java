package com.example.practica1_jakartaee.repositories.impl.jdbc;

import com.example.practica1_jakartaee.domain.model.Teacher;
import com.example.practica1_jakartaee.exceptions.ServiceJdbcException;
import com.example.practica1_jakartaee.mapping.dtos.TeacherDto;
import com.example.practica1_jakartaee.mapping.mappers.TeacherMapper;
import com.example.practica1_jakartaee.repositories.Repository;
import com.example.practica1_jakartaee.utils.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryJdbcImpl implements Repository<TeacherDto> {

    private Connection conn;
    public TeacherRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }


    private TeacherDto createTeacher(ResultSet resultSet) throws SQLException {
        Teacher teachers = new Teacher();
        teachers.setId(resultSet.getLong("id"));
        teachers.setName(resultSet.getString("name"));
        teachers.setEmail(resultSet.getString("email"));
        return TeacherMapper.mapFrom(teachers);
    }

    @Override
    public List<TeacherDto> list() {
        List<TeacherDto> teacherList = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from teachers")) {
            while (resultSet.next()) {
                TeacherDto teacher = createTeacher(resultSet);
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("Error recovering information from database", e.getCause());
        }
        return teacherList;
    }
    @Override
    public TeacherDto findById(Long id) {
        TeacherDto teacher = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM teachers WHERE id =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher = createTeacher(resultSet);
            }
            resultSet.close();
        } catch (SQLException e){
            throw new ServiceJdbcException("Unable to find id", e.getCause());
        }
        return teacher;
    }

    @Override
    public void save(TeacherDto teacher) {
        String sql;
        if (teacher.id() != null && teacher.id()>0) {
            sql = "UPDATE teachers SET name=?, email=? WHERE id=?";
        } else {
            sql = "INSERT INTO teachers(name, email) VALUES(?, ?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, teacher.name());
            stmt.setString(2, teacher.email());

            if (teacher.id() != null && teacher.id()>0) {
                stmt.setLong(3, teacher.id());
            }

            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException("Unable to save info", throwables.getCause());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM teachers WHERE id = ?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to delete", e.getCause());
        }
    }

}