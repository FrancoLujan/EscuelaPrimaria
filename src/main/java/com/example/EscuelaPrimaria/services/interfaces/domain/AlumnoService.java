package com.example.EscuelaPrimaria.services.interfaces.domain;

import com.example.EscuelaPrimaria.entities.domain.Alumno;
import com.example.EscuelaPrimaria.services.interfaces.Crud;

public interface AlumnoService<T,K> extends Crud<T,K> {
    T findAlumnoByCuil(K cuil);
    boolean existsAlumnoByCuil(K cuil);
    T findAlumnoByUsuario_Id(K idUsuario);
}
