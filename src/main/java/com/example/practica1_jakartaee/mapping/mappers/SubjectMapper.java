package com.example.practica1_jakartaee.mapping.mappers;

import com.example.practica1_jakartaee.domain.model.Subject;
import com.example.practica1_jakartaee.mapping.dtos.SubjectDto;

import java.util.List;

public class SubjectMapper {

    public static SubjectDto mapFrom (Subject source){
        return new SubjectDto(source.getId(),
                source.getName(),
                source.getTeacher());
    }

    public static Subject mapFrom (SubjectDto source){
        return new Subject(source.id(),
                source.name(),
                source.teacher());
    }

    public static List<SubjectDto> mapFrom (List<Subject> source){
        return source.parallelStream().map(e->mapFrom(e)).toList();
    }
}
