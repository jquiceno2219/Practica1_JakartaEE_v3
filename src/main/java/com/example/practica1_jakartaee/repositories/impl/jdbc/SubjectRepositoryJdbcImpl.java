package com.example.practica1_jakartaee.repositories.impl.jdbc;

import com.example.practica1_jakartaee.annotations.MysqlConn;
import com.example.practica1_jakartaee.domain.model.Subject;
import com.example.practica1_jakartaee.domain.model.Teacher;
import com.example.practica1_jakartaee.exceptions.ServiceJdbcException;
import com.example.practica1_jakartaee.mapping.dtos.SubjectDto;
import com.example.practica1_jakartaee.mapping.mappers.SubjectMapper;
import com.example.practica1_jakartaee.repositories.Repository;
import com.example.practica1_jakartaee.utils.ConexionBaseDatos;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named("subjectRepo")
public class SubjectRepositoryJdbcImpl implements Repository<SubjectDto> {
    @Inject
    @MysqlConn
    private Connection conn;

    private Subject createSubject(ResultSet rs) throws SQLException {
        Subject subject = new Subject();
        subject.setId(rs.getLong("id"));
        subject.setName(rs.getString("name"));
        Teacher teacher = new Teacher();
        teacher.setId(rs.getLong("teacherId"));
        teacher.setName(rs.getString("teacherName"));
        teacher.setEmail(rs.getString("email"));
        subject.setTeacher(teacher);

        return subject;
    }


    @Override
    public List<SubjectDto> list() {
        List<Subject> subjectList = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT subject.*, teachers.name as 'teacherName', teachers.email ,teachers.id as 'teacherId'" +
                             "FROM subject INNER JOIN teachers ON subject.teacher_id=teachers.id")) {
            while (resultSet.next()) {
                Subject subject = createSubject(resultSet);
                subjectList.add(subject);
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("Error recovering information from database", e.getCause());
        }
        return SubjectMapper.mapFrom(subjectList);
    }

    @Override
    public SubjectDto findById(Long id) {
        Subject subject = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT subject.name as 'Subject', teachers.name as 'Teacher', teachers.email " +
                    "FROM subject INNER JOIN teachers ON subject.teacher_id=teachers.id WHERE subject.id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subject = createSubject(resultSet);
            }
            resultSet.close();
        } catch (SQLException e){
            throw new ServiceJdbcException("Unable to find id", e.getCause());
        }
        return SubjectMapper.mapFrom(subject);
    }

    @Override
    public void save(SubjectDto subject) {
        String sql;
        if (subject.id() != null && subject.id() > 0) {
            sql = "UPDATE subject SET name=?, teacher_id = ? WHERE id=?";
        } else {
            sql = "INSERT INTO subject(name, teacher_id) VALUES(?, ?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, subject.name());
            stmt.setLong(2, subject.teacher().getId());

            if (subject.id() != null && subject.id() > 0) {
                stmt.setLong(3, subject.id());
            }

            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException("Unable to save info", throwables.getCause());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM subject WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to delete", e.getCause());
        }
    }

}
