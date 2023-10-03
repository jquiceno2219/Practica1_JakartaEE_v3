package com.example.practica1_jakartaee.controllers.studentServlets;


import java.io.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.example.practica1_jakartaee.domain.enums.Career;
import com.example.practica1_jakartaee.mapping.dtos.StudentDto;
import com.example.practica1_jakartaee.repositories.impl.jdbc.StudentRepositoryJdbcImpl;
import com.example.practica1_jakartaee.services.StudentService;
import com.example.practica1_jakartaee.services.impl.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "studentController", value = "/student-form")
public class StudentController extends HttpServlet {

    private StudentRepositoryJdbcImpl studentRepository;
    private StudentService service;
    private String message;

    private Connection conn;

    public StudentController() {
        studentRepository = new StudentRepositoryJdbcImpl(conn);
        service = new StudentServiceImpl(conn);
    }

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Connection conn = (Connection) request.getAttribute("conn");
        studentRepository = new StudentRepositoryJdbcImpl(conn);
        service = new StudentServiceImpl(conn);

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
        studentRepository = new StudentRepositoryJdbcImpl(conn);
        service = new StudentServiceImpl(conn);

        String name = req.getParameter("name");
        String career = req.getParameter("career");
        StudentDto student = StudentDto.builder()
                .name(name)
                .career(Career.fromValue(career))
                .semester("1")
                .build();

        Map<String, String> errorsmap = getErrors(name, career);

        if (errorsmap.isEmpty()) {
            service.save(student);
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
                out.println("            <li>Career: " + career + "</li>");
                out.println("        </ul>");

                out.println("        <p> lista: " + service.list() + "</p>");

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
            getServletContext().getRequestDispatcher("/student.jsp").forward(req, resp);
        }
    }

    //Por fin aprendí a usar mapas :D creo :P
    private Map<String, String> getErrors(String name, String career) {
        Map<String, String> errors = new HashMap<>();
        if (name == null || name.isBlank()) {
            errors.put("name", "El nombre es requerido");
        }
        if (career == null || career.isBlank()) {
            errors.put("career", "La carrera es requerida");
        }

        return errors;
    }

    public void destroy() {
    }
}