package com.example.practica1_jakartaee.mapping.mappers;
import com.example.practica1_jakartaee.domain.model.Student;
import com.example.practica1_jakartaee.mapping.dtos.StudentDto;

import java.util.List;

public class StudentMapper {

    public static StudentDto mapFrom (Student source){
        return new StudentDto(source.getId(),
                source.getName(),
                source.getSemester(),
                source.getCareer());
    }

    public static Student mapFrom (StudentDto source){
        return new Student(source.id(),
                source.name(),
                source.semester(),
                source.career());
    }

    public static List<StudentDto> mapFrom(List<Student> sources){
        return sources.parallelStream().map(e-> mapFrom(e)).toList();
    }

}