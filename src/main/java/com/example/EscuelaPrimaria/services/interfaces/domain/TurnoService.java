package com.example.EscuelaPrimaria.services.interfaces.domain;

import com.example.EscuelaPrimaria.entities.domain.Turno;
import com.example.EscuelaPrimaria.services.interfaces.Crud;

import java.util.TreeSet;

public interface TurnoService<T, K> extends Crud<T, K> {
    T findByNombre(String nombre);
}
