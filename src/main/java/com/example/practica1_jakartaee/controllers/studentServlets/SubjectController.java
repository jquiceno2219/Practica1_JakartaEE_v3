package com.example.practica1_jakartaee.controllers.studentServlets;

import com.example.practica1_jakartaee.domain.model.Teacher;
import com.example.practica1_jakartaee.mapping.dtos.SubjectDto;
import com.example.practica1_jakartaee.mapping.dtos.TeacherDto;
import com.example.practica1_jakartaee.repositories.impl.jdbc.StudentRepositoryJdbcImpl;
import com.example.practica1_jakartaee.repositories.impl.jdbc.SubjectRepositoryJdbcImpl;
import com.example.practica1_jakartaee.repositories.impl.logic.SubjectRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.impl.SubjectServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "subjectController", value = "/subject-form")
public class SubjectController extends HttpServlet {
    private SubjectRepositoryJdbcImpl subjectRepository;
    private SubjectServiceImpl service;
    private Connection conn;
    public SubjectController() {
        subjectRepository = new SubjectRepositoryJdbcImpl(conn);
        service = new SubjectServiceImpl(conn);
    }

    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Connection conn = (Connection) request.getAttribute("conn");
        subjectRepository = new SubjectRepositoryJdbcImpl(conn);
        service = new SubjectServiceImpl(conn);

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        subjectRepository = new SubjectRepositoryJdbcImpl(conn);
        service = new SubjectServiceImpl(conn);

        String name = req.getParameter("name");
        String teacherName = req.getParameter("teacher-name");

        //SubjectDto subject = new SubjectDto(4L, name, new Teacher(5L,"Test", "Test@mail.com"));
        Teacher teacher = Teacher.builder()
                .name(teacherName)
                .build();

        SubjectDto subject = SubjectDto.builder()
                        .name(name)
                .teacher(teacher)
                .build();
        service.save(subject);

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

    public void destroy() {
    }
}