package com.example.EscuelaPrimaria.services.interfaces;

import com.example.EscuelaPrimaria.entities.Materia;

public interface MateriaService<T, K> extends Crud<T, K>{
    Materia findMateriaByNombre(String nombre);
    boolean findExistsMateriaByNombre(String nombre);
}
