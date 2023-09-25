package com.example.practica1_jakartaee.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(value="/test")
public class Test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        resp.setContentType("text/html");
            //getMethod retorna el método por el que se realiza el request. Ej: GET, POST o PUT.
        String metodoHttp = req.getMethod();
            //getRequestURI retorna el URL desde después del dominio principal
        String requestUri = req.getRequestURI();
        /*
            getRequestURL retorna el URL completo, desde el protocolo. Se debe poner .toString porque retorna un
            StringBuffer (o sea, es un String modificable)
        */
        String requestUrl = req.getRequestURL().toString();
        /*
            getContextPath retorna la parte de la request que indica el contexto de la petición.
            En otras palabras, devuelve el nombre de el directorio raíz del proyecto o servidor.
        */
        String contexPath = req.getContextPath();
        /*
            getServletPath retorna el nombre del servlet específico en el que se realiza la petición.
         */
        String servletPath = req.getServletPath();
            //getRemoteAddr retorna la dirección IP del cliente que envía la petición
        String ipCliente = req.getRemoteAddr();
            //getLocalAddr retorna la dirección IP en la que se recibe la petición
        String ip = req.getLocalAddr();
            //getLocalPort retorna el número del Puerto Local en que se recibe la petición
        int port = req.getLocalPort();
            //getScheme retorna el esquema o protocolo de la URL.
        String scheme = req.getScheme();
        /*
            getHeader retorna un request header especifico como atributo de la función.
            En este caso, 'host', que especifica el nombre del dominio del servidor
            (como indica: https://developer.mozilla.org/es/docs/Web/HTTP/Headers/Host)
        */
        String host = req.getHeader("host");
            // Aquí se define la URL de la página mediante el dominio
        String url = scheme + "://" + host + contexPath + servletPath;
            // Aquí se define la URL mediante la IP local y el puerto
        String url2 = scheme + "://" + ip + ":" + port + contexPath + servletPath;
            // Aquí se define el código HTML mediante el printWriter
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Cabeceras HTTP Request</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Cabeceras HTTP Request!</h1>");
            out.println("<ul>");
            out.println("<li>metodo http: " + metodoHttp + "</li>");
            out.println("<li>request uri: " + requestUri + "</li>");
            out.println("<li>request url: " + requestUrl + "</li>");
            out.println("<li>context path: " + contexPath + "</li>");
            out.println("<li>servlet path: " + servletPath + "</li>");
            out.println("<li>ip local: " + ip + "</li>");
            out.println("<li>ip cliente: " + ipCliente + "</li>");
            out.println("<li>puerto local: " + port + "</li>");
            out.println("<li>scheme: " + scheme + "</li>");
            out.println("<li>host: " + host + "</li>");
            out.println("<li>url: " + url + "</li>");
            out.println("<li>url2: " + url2 + "</li>");
                //getHeaderNames retorna una enumeración de todas las cabeceras en el request
            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String cabecera = headerNames.nextElement();
                out.println("<li>"+ cabecera + ": " + req.getHeader(cabecera) + "</li>");
            }
            out.println("</ul>");
            out.println(" </body>");
            out.println("</html>");
        }
    }
}