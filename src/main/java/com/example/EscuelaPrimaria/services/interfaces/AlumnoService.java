package com.example.EscuelaPrimaria.services.interfaces;

import com.example.EscuelaPrimaria.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AlumnoService<T,K> extends Crud<T,K> {
    T findAlumnoByCuil(K cuil);
}
