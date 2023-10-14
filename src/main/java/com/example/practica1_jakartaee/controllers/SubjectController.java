package com.example.practica1_jakartaee.controllers;

import com.example.practica1_jakartaee.domain.model.Teacher;
import com.example.practica1_jakartaee.mapping.dtos.SubjectDto;
import com.example.practica1_jakartaee.mapping.dtos.TeacherDto;
import com.example.practica1_jakartaee.mapping.mappers.TeacherMapper;
import com.example.practica1_jakartaee.repositories.impl.jdbc.SubjectRepositoryJdbcImpl;
import com.example.practica1_jakartaee.services.SubjectService;
import com.example.practica1_jakartaee.services.TeacherService;
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

@WebServlet(name = "subjectController", value = "/subject-form")
public class SubjectController extends HttpServlet {
    @Inject
    SubjectService subjectService;
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
//        Connection conn = (Connection) request.getAttribute("conn");


        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Subjects</h1>");
        out.println(subjectService.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
//        Connection conn = (Connection) req.getAttribute("conn");

        String name = req.getParameter("name");
        String teacherName = req.getParameter("teacher-name");

        //SubjectDto subject = new SubjectDto(4L, name, new Teacher(5L,"Test", "Test@mail.com"));


        TeacherDto teacherDto = getTeacherByName(req.getParameter("teacher-name"));

        Teacher mapTeacher = TeacherMapper.mapFrom(teacherDto);


        SubjectDto subject = SubjectDto.builder()
                .name(name)
                .teacher(mapTeacher)
                .build();

        Map<String, String> errorsmap = getErrors(name, teacherName);

        if (errorsmap.isEmpty()) {
            subjectService.save(subject);
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
                out.println("            <li>Name: " + name + "</li>");
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

    private TeacherDto getTeacherByName(String teacher) {
        List<TeacherDto> teachers = (List<TeacherDto>)getServletContext().getAttribute("teacherDtoList");
        return teachers.stream()
                .filter(e->e.name().equalsIgnoreCase(teacher))
                .findFirst()
                .orElseGet(null);
    }

    private Map<String, String> getErrors(String name, String teacher) {
        Map<String, String> errors = new HashMap<>();
        if (name == null || name.isBlank()) {
            errors.put("name", "El nombre es requerido");
        }
        if (teacher == null || teacher.isBlank()) {
            errors.put("teacher", "El profesor es requerido");
        }

        return errors;
    }

    public void destroy() {
    }
}