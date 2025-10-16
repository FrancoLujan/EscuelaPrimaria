package com.example.EscuelaPrimaria.repositories.domain;

import com.example.EscuelaPrimaria.entities.domain.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.TreeSet;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    //TreeSet<Turno> findByNombre(String nombre);
    Turno findByNombre(String nombre);
}
