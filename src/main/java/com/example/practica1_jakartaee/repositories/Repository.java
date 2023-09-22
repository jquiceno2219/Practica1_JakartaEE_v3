package com.example.practica1_jakartaee.repositories;

import java.util.List;

public interface Repository <T>{
    List<T> list();

    T findById(Long id);

    void save(T t);

    void delete(Long id);
}