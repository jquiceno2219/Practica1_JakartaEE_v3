package com.example.practica1_jakartaee.controllers;

import com.example.practica1_jakartaee.domain.model.Student;
import com.example.practica1_jakartaee.domain.model.Subject;
import com.example.practica1_jakartaee.mapping.dtos.GradesDto;
import com.example.practica1_jakartaee.mapping.dtos.StudentDto;
import com.example.practica1_jakartaee.mapping.dtos.SubjectDto;
import com.example.practica1_jakartaee.mapping.dtos.TeacherDto;
import com.example.practica1_jakartaee.mapping.mappers.StudentMapper;
import com.example.practica1_jakartaee.mapping.mappers.SubjectMapper;
import com.example.practica1_jakartaee.repositories.impl.jdbc.GradesRepositoryJdbcImpl;
import com.example.practica1_jakartaee.repositories.impl.jdbc.SubjectRepositoryJdbcImpl;
import com.example.practica1_jakartaee.services.GradesService;
import com.example.practica1_jakartaee.services.StudentService;
import com.example.practica1_jakartaee.services.TeacherService;
import com.example.practica1_jakartaee.services.impl.GradesServiceImpl;
import com.example.practica1_jakartaee.services.impl.SubjectServiceImpl;
import com.example.practica1_jakartaee.services.impl.TeacherServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value="/grades-form")
public class GradesController extends HttpServlet {
    private GradesRepositoryJdbcImpl gradesRep;
    @Inject
    GradesService gradesService;

    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        // Connection conn = (Connection) request.getAttribute("conn");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Grades</h1>");
        out.println(gradesService.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        //Connection conn = (Connection) req.getAttribute("conn");

        String gradeName = req.getParameter("grade");
        double grade = Double.parseDouble(gradeName);
        String subjectName = req.getParameter("subject-name");
        String student = req.getParameter("student-name");

        SubjectDto subjectDto = getSubjectbyName(req.getParameter("subject-name"));
        Subject mapSubject = SubjectMapper.mapFrom(subjectDto);

        StudentDto studentDto = getStudentByName(req.getParameter("student-name"));
        Student mapStudent = StudentMapper.mapFrom(studentDto);

        String term = req.getParameter("term");

        GradesDto grades = GradesDto.builder()
                .student(mapStudent)
                .subject(mapSubject)
                .grade(grade)
                .term(term)
                .build();

        Map<String, String> errorsmap = getErrors(gradeName, subjectName, student, term);


        if (errorsmap.isEmpty()) {
            gradesService.save(grades);
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Resultado form</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Resultado form!</h1>");

                out.println("        <ul>");
                out.println("            <li>Grades: " + grades + "</li>");
                out.println("        </ul>");

                //            out.println("        <p> lista: " + service.list() + "</p>");

                out.println("    </body>");
                out.println("</html>");
            }
        }
        else{
/* errores.forEach(error -> {
out.println("<li>" + error + "</li>");
});
out.println("<p><a href=\"/student.jsp\">volver</a></p>");*/
            req.setAttribute("errorsmap", errorsmap);
            getServletContext().getRequestDispatcher("/subject.jsp").forward(req, resp);
        }
    }

    private SubjectDto getSubjectbyName(String subject) {
        List<SubjectDto> subjectDto = (List<SubjectDto>)getServletContext()
                .getAttribute("subjectDtoList");
        return subjectDto.stream()
                .filter(e->e.name().equalsIgnoreCase(subject))
                .findFirst()
                .orElseGet(null);
    }

    private StudentDto getStudentByName(String student) {
        List<StudentDto> studentDto = (List<StudentDto>)getServletContext()
                .getAttribute("studentDtoList");
        return studentDto.stream()
                .filter(e->e.name().equalsIgnoreCase(student))
                .findFirst()
                .orElseGet(null);
    }

    private Map<String, String> getErrors(String gradeName, String subjectName, String student, String term) {
        Map<String, String> errors = new HashMap<>();
        if (gradeName == null || gradeName.isBlank()) {
            errors.put("grade", "La nota es requerida");
        }
        if (subjectName == null || subjectName.isBlank()) {
            errors.put("subject-name", "La materia es requerida");
        }
        if (student == null || student.isBlank()) {
            errors.put("student-name", "El estudiante es requerido");
        }
        if (term == null || term.isBlank()) {
            errors.put("term", "El corte es requerido");
        }
        return errors;
    }
}