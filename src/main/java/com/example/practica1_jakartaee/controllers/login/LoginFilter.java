package com.example.practica1_jakartaee.controllers.login;

import com.example.practica1_jakartaee.services.LoginService;
import com.example.practica1_jakartaee.services.impl.LoginServiceSessionImpl;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/students"})//urls que deseamos aplique el filtro
//si queremos implementar varias pondriamos:
//@WebFilter({"/students", “/teachers”,”subjects”})
public class LoginFilter implements Filter {

    @Inject
    @Named("loginSession")
    LoginService service;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain
            chain) throws IOException, ServletException {
        Optional<String> username = service.getUsername((HttpServletRequest)
                request);
        if (username.isPresent()) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse)response)
                    .sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "No estás autorizado para ingresar a esta página!");
        }
    }
}

