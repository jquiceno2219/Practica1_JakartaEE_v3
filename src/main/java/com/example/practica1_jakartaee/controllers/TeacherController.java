package com.example.practica1_jakartaee.controllers;


import com.example.practica1_jakartaee.domain.model.Teacher;
import com.example.practica1_jakartaee.mapping.dtos.TeacherDto;
import com.example.practica1_jakartaee.mapping.mappers.TeacherMapper;
import com.example.practica1_jakartaee.repositories.impl.jdbc.TeacherRepositoryJdbcImpl;
import com.example.practica1_jakartaee.repositories.impl.logic.TeacherRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.TeacherService;
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
import java.util.Map;

@WebServlet(name = "teacherController", value = "/teacher-form")
public class TeacherController extends HttpServlet {
    private TeacherRepositoryJdbcImpl teacherRepository;
    @Inject
    TeacherService service;


    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
       // Connection connection = (Connection) request.getAttribute("conn");

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
//        Connection connection = (Connection) req.getAttribute("conn");

        String name = req.getParameter("teacher-name");
        String email = req.getParameter("teacher-email");
        TeacherDto teacher = TeacherDto.builder()
                .name(name)
                .email(email)
                .build();

        Map<String, String> errorsmap = getErrors(name, email);

        if (errorsmap.isEmpty()) {
            service.save(teacher);
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
                out.println("        <p> lista: " + service.list() + "</p>");

                out.println("    </body>");
                out.println("</html>");
            }
        } else {
/* errores.forEach(error -> {
out.println("<li>" + error + "</li>");
});
out.println("<p><a href=\"/student.jsp\">volver</a></p>");*/
            req.setAttribute("errorsmap", errorsmap);
            getServletContext().getRequestDispatcher("/teacher.jsp").forward(req, resp);
        }
    }

    private Map<String, String> getErrors(String name, String email) {
        Map<String, String> errors = new HashMap<>();
        if (name == null || name.isBlank()) {
            errors.put("name", "El nombre es requerido");
        }
        if (email == null || email.isBlank() || !email.contains("@")) {
            errors.put("email", "El email no es verificable");
        }

        return errors;
    }

    public void destroy() {
    }
}
