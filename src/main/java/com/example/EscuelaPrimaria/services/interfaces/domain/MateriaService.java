package com.example.EscuelaPrimaria.services.interfaces.domain;

import com.example.EscuelaPrimaria.entities.domain.Materia;
import com.example.EscuelaPrimaria.services.interfaces.Crud;

public interface MateriaService<T, K> extends Crud<T, K> {
    Materia findMateriaByNombre(String nombre);
    boolean findExistsMateriaByNombre(String nombre);
}
