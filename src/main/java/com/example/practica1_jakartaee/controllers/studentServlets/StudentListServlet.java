package com.example.practica1_jakartaee.controllers.studentServlets;

import com.example.practica1_jakartaee.services.LoginService;
import com.example.practica1_jakartaee.services.impl.LoginServiceImpl;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/student-list")
public class StudentListServlet extends HttpServlet {

    @Inject
    @Named("login")
    LoginService auth;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean authUser = auth.verifyUserCookie(request);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Students List</title></head>");
        out.println("<body>");

        if (authUser == true) {
            out.println("<h1>Students List</h1>");
            out.println("<ul>");

            out.println("<li>Nombre: Juan Perez, Semestre: 4, Carrera: Ingeniería Informática</li>");
            out.println("<li>Nombre: Maria Rodriguez, Semestre: 3, Carrera: Administración de Empresas</li>");
            out.println("<li>Nombre: Carlos Martinez, Semestre: 5, Carrera: Medicina</li>");

            out.println("</ul>");
        } else {
            out.println("<h1>Error: No estás autenticado</h1>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}