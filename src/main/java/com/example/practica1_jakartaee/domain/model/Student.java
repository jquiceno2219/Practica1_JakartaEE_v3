package com.example.practica1_jakartaee.domain.model;

import com.example.practica1_jakartaee.domain.enums.Career;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Getter
@Setter
public class Student {

    private Long id;
    private String name;
    private String semester;
    private Career career;
}