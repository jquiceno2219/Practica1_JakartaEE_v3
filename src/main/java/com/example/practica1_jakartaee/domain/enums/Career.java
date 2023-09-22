package com.example.practica1_jakartaee.domain.enums;

import com.example.practica1_jakartaee.exceptions.UniversityException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Career {
    SOFTWARE("Software"),
    CIVIL("Civil"),
    INDUSTRIAL("Industrial"),
    ADMINISTRATION("Administration")
    ;

    Career(String value) {
        this.value = value;
    }

    private String value;

    public static Career fromValue(String value) {
        return Arrays.stream(Career.values()).filter(e->value.equalsIgnoreCase(e.value)).findFirst()
                .orElseThrow(()->new UniversityException("Invalid value"));
    }
}