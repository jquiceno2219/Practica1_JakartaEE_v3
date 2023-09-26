package com.example.practica1_jakartaee.controllers;


import com.example.practica1_jakartaee.domain.model.Teacher;
import com.example.practica1_jakartaee.mapping.dtos.TeacherDto;
import com.example.practica1_jakartaee.mapping.mappers.TeacherMapper;
import com.example.practica1_jakartaee.repositories.impl.jdbc.TeacherRepositoryJdbcImpl;
import com.example.practica1_jakartaee.repositories.impl.logic.TeacherRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.impl.TeacherServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "teacherController", value = "/teacher-form")
public class TeacherController extends HttpServlet {
    private TeacherRepositoryJdbcImpl teacherRepository;
    private TeacherServiceImpl service;
    private Connection connection;

    public TeacherController() {
        teacherRepository = new TeacherRepositoryJdbcImpl(connection);
        service = new TeacherServiceImpl(connection);
    }
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Connection connection = (Connection) request.getAttribute("conn");
        teacherRepository = new TeacherRepositoryJdbcImpl(connection);
        service = new TeacherServiceImpl(connection);

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Teachers</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection connection = (Connection) req.getAttribute("conn");
        teacherRepository = new TeacherRepositoryJdbcImpl(connection);
        service = new TeacherServiceImpl(connection);

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        Teacher teacher = Teacher.builder()
                .name(name)
                .email(email).build();
        TeacherDto teacherDto = TeacherMapper.mapFrom(teacher);
        service.save(teacherDto);
        System.out.println(service.list());

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
            out.println("            <li>Email: " + email + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    public void destroy() {
    }
}
