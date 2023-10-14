package com.example.practica1_jakartaee.domain.model;

import com.example.practica1_jakartaee.domain.enums.Career;
import jakarta.enterprise.context.SessionScoped;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Getter
@Setter
@SessionScoped
public class Student implements Serializable {

    private Long id;
    private String name;
    private String semester;
    private Career career;
}