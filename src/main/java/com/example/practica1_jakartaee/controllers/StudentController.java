package com.example.practica1_jakartaee.controllers;


import java.io.*;

import com.example.practica1_jakartaee.domain.enums.Career;
import com.example.practica1_jakartaee.mapping.dtos.StudentDto;
import com.example.practica1_jakartaee.repositories.impl.StudentRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.StudentService;
import com.example.practica1_jakartaee.services.impl.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "studentController", value = "/student-form")
public class StudentController extends HttpServlet {

    private StudentRepositoryLogicImpl studentRepository;
    private StudentService service;

    public StudentController() {
        studentRepository = new StudentRepositoryLogicImpl();
        service = new StudentServiceImpl(studentRepository);
    }

    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

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

        String name = req.getParameter("name");
        String career = req.getParameter("career");
        StudentDto student = new StudentDto(4L, name, "1",Career.fromValue(career));
        service.save(student);
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
            out.println("            <li>Career: " + career + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    public void destroy() {
    }
}