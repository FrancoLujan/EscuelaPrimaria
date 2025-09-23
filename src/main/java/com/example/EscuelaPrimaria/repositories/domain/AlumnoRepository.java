package com.example.EscuelaPrimaria.repositories.domain;

import com.example.EscuelaPrimaria.entities.domain.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    Alumno findAlumnoByCuil(Long cuil);
}
