package com.example.practica1_jakartaee.mapping.mappers;

import com.example.practica1_jakartaee.domain.model.Grades;
import com.example.practica1_jakartaee.mapping.dtos.GradesDto;


import java.util.List;
import java.util.stream.Collectors;

public class GradesMapper {
    public static GradesDto mapFrom(Grades source){
        return new GradesDto(source.getId(), source.getStudent(),
                source.getSubject(), source.getGrade(), source.getTerm());
    }

    public static Grades mapFrom(GradesDto source){
        return new Grades(source.id(), source.student(), source.subject(), source.grade(), source.term());
    }
    public static List<GradesDto> mapFrom(List<Grades> source){
        return source.parallelStream()
                .map(GradesMapper::mapFrom)
                .collect(Collectors.toList());
    }
    public static List<Grades> mapFromDto(List<GradesDto> source){
        return source.parallelStream()
                .map(GradesMapper::mapFrom)
                .collect(Collectors.toList());
    }
}