package com.example.practica1_jakartaee.controllers.studentServlets;

import com.example.practica1_jakartaee.domain.model.Student;
import com.example.practica1_jakartaee.exceptions.UniversityException;
import com.example.practica1_jakartaee.mapping.dtos.StudentDto;
import com.example.practica1_jakartaee.mapping.mappers.StudentMapper;
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

import static java.lang.String.valueOf;

@WebServlet("/studentId")
public class StudentByIdServlet extends HttpServlet {

    @Inject
    StudentService studentService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        Long id = Long.parseLong(req.getParameter("id"));

        try{
            StudentDto student= studentService.findById(id);
            Student student1 = StudentMapper.mapFrom(student);
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Login correcto</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Login correcto!</h1>");
                out.println(" <h3>Hola "+ student1.getName() + " has iniciado sesión con éxito!</h3>");
                out.println(" </body>");
                out.println("</html>");
            }
        }
        catch(UniversityException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Lo sentimos. " +
                    "No esta autorizado para ingresar a esta página!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        StudentDto studentDto = mapper.readValue(JsonStream,
                StudentDto.class);
        Long id = studentDto.id();
        studentService.findById(id);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<h1>Students</h1>");
            out.println(studentService.findById(id));
            out.println("</body></html>");
    }
}