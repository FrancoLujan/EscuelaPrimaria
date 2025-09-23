package com.example.EscuelaPrimaria.services.interfaces.domain;

import com.example.EscuelaPrimaria.services.interfaces.Crud;

import java.util.TreeSet;

public interface TurnoService<T, K> extends Crud<T, K> {
    TreeSet<T> findByNombre(String nombre);
}
