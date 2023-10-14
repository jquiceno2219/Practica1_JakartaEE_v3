package com.example.practica1_jakartaee.services.impl;

import com.example.practica1_jakartaee.services.LoginService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

@ApplicationScoped
@Named("login")
public class LoginServiceImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        return Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

    public Boolean verifyUserCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];

        Optional<Boolean> result = Arrays.stream(cookies)
                .filter(cookie -> Boolean.parseBoolean(cookie.getValue()))
                .map(cookie -> true)
                .findFirst();

        return result.orElse(false);
    }
}