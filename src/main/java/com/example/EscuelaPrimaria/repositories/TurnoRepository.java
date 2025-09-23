package com.example.EscuelaPrimaria.repositories;

import com.example.EscuelaPrimaria.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    TreeSet<Turno> findByNombre(String nombre);
}
