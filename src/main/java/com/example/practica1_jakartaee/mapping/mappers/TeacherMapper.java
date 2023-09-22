package com.example.practica1_jakartaee.mapping.mappers;

import com.example.practica1_jakartaee.domain.model.Teacher;
import com.example.practica1_jakartaee.mapping.dtos.TeacherDto;

import java.util.List;

public class TeacherMapper {

    public static TeacherDto mapFrom(Teacher source){
        return new TeacherDto(source.getId(),
                source.getName(),
                source.getEmail());
    }

    public static Teacher mapFrom(TeacherDto source){
        return new Teacher(source.id(),
                source.name(),
                source.email());
    }

    public static List<TeacherDto> mapFrom(List<Teacher> source){
        return source.parallelStream().map(e->mapFrom(e)).toList();
    }
}
