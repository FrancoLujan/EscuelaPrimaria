package com.example.EscuelaPrimaria.repositories;

import com.example.EscuelaPrimaria.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    Alumno findAlumnoByCuil(Long cuil);
}
