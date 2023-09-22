package com.example.practica1_jakartaee.controllers;

import com.example.practica1_jakartaee.domain.model.Teacher;
import com.example.practica1_jakartaee.mapping.dtos.SubjectDto;
import com.example.practica1_jakartaee.repositories.impl.SubjectRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.impl.SubjectServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "subjectController", value = "/subject-form")
public class SubjectController extends HttpServlet {
    private SubjectRepositoryLogicImpl subjectRepository;
    private SubjectServiceImpl service;

    public SubjectController() {
        subjectRepository = new SubjectRepositoryLogicImpl();
        service = new SubjectServiceImpl(subjectRepository);
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
        SubjectDto subject = new SubjectDto(4L, name, new Teacher(5L,"Test", "Test@mail.com"));
        service.save(subject);
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
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    public void destroy() {
    }
}