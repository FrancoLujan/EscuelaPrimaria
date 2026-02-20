package com.example.EscuelaPrimaria.services.interfaces.domain;

import com.example.EscuelaPrimaria.services.interfaces.transactions.Crud;

import java.util.List;

public interface ProfesionalService<T,K> extends Crud<T, K> {
    List<T> findProfesionalByCuil(K cuil);
    T findProfesionalByUsuario_Id(K idUsuario);
}
