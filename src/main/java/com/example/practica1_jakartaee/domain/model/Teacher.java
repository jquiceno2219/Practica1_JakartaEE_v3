package com.example.practica1_jakartaee.domain.model;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class Teacher {
    private Long id;
    private String name;
    private String email;
}