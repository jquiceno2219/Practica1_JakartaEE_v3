package com.example.practica1_jakartaee.controllers.studentServlets;

import com.example.practica1_jakartaee.mapping.dtos.StudentDto;
import com.example.practica1_jakartaee.repositories.impl.jdbc.StudentRepositoryJdbcImpl;
import com.example.practica1_jakartaee.repositories.impl.logic.StudentRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.StudentService;
import com.example.practica1_jakartaee.services.impl.StudentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "StudentJson", value = "/student.json")
public class StudentJson extends HttpServlet {

    @Inject
    StudentService studentService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        StudentDto students = mapper.readValue(jsonStream, StudentDto.class);
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Detalle de students desde json</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Detalle de students desde json!</h1>");
            out.println("<ul>");
            out.println("<li>Id: "+ students.id() + "</li>");
            out.println("<li>Name: " + students.name() + "</li>");
            out.println("<li>Semester: " + students.semester() + "</li>");
            out.println("<li>Career: " + students.career() + "</li>");
            out.println("</ul>");
            out.println(" </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        List<StudentDto> students = studentService.list();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(students);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}