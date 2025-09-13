package com.example.EscuelaPrimaria.repositories;

import com.example.EscuelaPrimaria.entities.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
    Materia findMateriaByNombre(String nombre);
    boolean findMateriaByNombreEqualsIgnoreCase(String nombre);
}
