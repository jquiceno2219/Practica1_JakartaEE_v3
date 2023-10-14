package com.example.practica1_jakartaee.domain.model;

import jakarta.enterprise.context.SessionScoped;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
@SessionScoped
public class Teacher implements Serializable {
    private Long id;
    private String name;
    private String email;
}