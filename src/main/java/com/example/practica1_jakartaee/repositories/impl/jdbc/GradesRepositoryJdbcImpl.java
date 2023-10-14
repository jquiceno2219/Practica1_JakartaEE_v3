package com.example.practica1_jakartaee.repositories.impl.jdbc;

import com.example.practica1_jakartaee.annotations.MysqlConn;
import com.example.practica1_jakartaee.domain.model.Grades;
import com.example.practica1_jakartaee.domain.model.Student;
import com.example.practica1_jakartaee.domain.model.Subject;
import com.example.practica1_jakartaee.exceptions.ServiceJdbcException;
import com.example.practica1_jakartaee.mapping.dtos.GradesDto;
import com.example.practica1_jakartaee.mapping.mappers.GradesMapper;
import com.example.practica1_jakartaee.repositories.Repository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@RequestScoped
@Named("gradeRepo")
public class GradesRepositoryJdbcImpl implements Repository<GradesDto> {
    @Inject
    @MysqlConn
    private Connection conn;

    private GradesDto createGrades(ResultSet resultSet) throws
            SQLException {
        Grades grades = new Grades();
        grades.setId(resultSet.getLong("id"));
        Student student = new Student();
        student.setId(resultSet.getLong("student"));
        student.setName(resultSet.getString("name"));
        //student.setEmail(resultSet.getString("email"));
        student.setSemester(resultSet.getString("semester"));
        student.getCareer().fromValue(resultSet.getString("career"));
        grades.setStudent(student);
        Subject subject = new Subject();
        subject.setId(resultSet.getLong("id"));
        subject.setName(resultSet.getString("name"));
        grades.setSubject(subject);

        grades.setGrade(resultSet.getDouble("grade"));
        grades.setTerm(resultSet.getString("term"));
        return GradesMapper.mapFrom(grades);
    }

    @Override
    public List<GradesDto> list() {
        List<GradesDto> gradesList = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT students.name as 'Student', subject.name as 'Subject'," +
                     " g.grade, g.term FROM `grades` as g INNER JOIN subject ON g.subject_id=subject.id" +
                     " INNER JOIN students ON g.student_id=students.id")) {
            while (rs.next()) {
                GradesDto grades = createGrades(rs);
                gradesList.add(grades);
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("Error recovering information from database", e.getCause());
        }
        return gradesList;
    }

    @Override
    public GradesDto findById(Long id) {
        GradesDto grades = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT students.name as 'Student', subject.name as 'Subject'," +
                        " g.grade, g.term FROM `grades` as g INNER JOIN subject ON g.subject_id=subject.id" +
                        " INNER JOIN students ON g.student_id=students.id WHERE g.id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                grades = createGrades(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to find id", e.getCause());
        }
        return grades;
    }

    @Override
    public void save(GradesDto grades) {
        String sql;
        if (grades.id() != null && grades.id() > 0) {
            sql = "UPDATE grades SET student_id=?, subject_id=?, grade=?, term=? WHERE id=?";
        } else {
            sql = "INSERT INTO grades(student_id, subject_id, grade, term) VALUES(?, ?, ?, ?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, grades.student().getId());
            stmt.setLong(2, grades.subject().getId());
            stmt.setDouble(3, grades.grade());
            stmt.setString(4, grades.term());

            if (grades.id() != null && grades.id() > 0) {
                stmt.setLong(5, grades.id());
            }

            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException("Unable to save info", throwables.getCause());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM grades WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to delete", e.getCause());
        }

    }

}
