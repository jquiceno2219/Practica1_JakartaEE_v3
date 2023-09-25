package com.example.practica1_jakartaee.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface LoginService {
    Optional<String> getUsername(HttpServletRequest request);

    Boolean verifyUserCookie(HttpServletRequest request);


}