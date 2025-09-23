package com.example.EscuelaPrimaria.services.interfaces;

import com.example.EscuelaPrimaria.entities.Turno;

import java.rmi.Remote;
import java.util.Set;
import java.util.TreeSet;

public interface TurnoService<T, K> extends Crud<T, K> {
    TreeSet<T> findByNombre(String nombre);
}
