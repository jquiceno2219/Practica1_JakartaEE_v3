package com.example.practica1_jakartaee.controllers;

import com.example.practica1_jakartaee.exceptions.UniversityException;
import com.example.practica1_jakartaee.mapping.dtos.StudentDto;
import com.example.practica1_jakartaee.repositories.impl.StudentRepositoryLogicImpl;
import com.example.practica1_jakartaee.services.impl.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.String.valueOf;

@WebServlet("/studentId")
public class StudentByIdServlet extends HttpServlet {
    private StudentRepositoryLogicImpl repository;
    private StudentServiceImpl service;
    public StudentByIdServlet(){
        repository = new StudentRepositoryLogicImpl();
        service = new StudentServiceImpl(repository);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        Long id = Long.parseLong(req.getParameter("id"));

        try{
            StudentDto student= service.findById(id);
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Login correcto</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Login correcto!</h1>");
                out.println(" <h3>Hola "+ student.name() + " has iniciado sesión con éxito!</h3>");
                out.println(" </body>");
                out.println("</html>");
            }

        }catch
         (UniversityException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }

    }
}