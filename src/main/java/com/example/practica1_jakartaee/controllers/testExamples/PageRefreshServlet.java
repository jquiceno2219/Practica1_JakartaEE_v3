package com.example.practica1_jakartaee.controllers.testExamples;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//Test realizado directamente en la página value
//El reloj se actualiza correctamente
@WebServlet(name = "pageRefresh", value = "/page-refresher-test")
public class PageRefreshServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        //Establece que el texto use el formato UTF-8, para utilizar tildes y ñ.
        resp.setContentType("text/html;charset=UTF-8");

        //En esta cabecera se indica al navegador que debe refrescarse cada 1 segundo
        resp.setHeader("refresh", "1");
        //Instanciar variable "hora" como hora actual
        LocalTime hora = LocalTime.now();

        //Da formato "hh:mm:ss" a la hora
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm:ss");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>La hora actualizada</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>La hora actualizada!</h1>");
            out.println("<h3>"+ hora.format(df) + "</h3>");
            out.println(" </body>");
            out.println("</html>");
        }
    }
}
