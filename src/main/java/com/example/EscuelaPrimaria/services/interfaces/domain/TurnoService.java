package com.example.EscuelaPrimaria.services.interfaces.domain;

import com.example.EscuelaPrimaria.services.interfaces.transactions.Crud;

public interface TurnoService<T, K> extends Crud<T, K> {
    T findByNombre(String nombre);

}
