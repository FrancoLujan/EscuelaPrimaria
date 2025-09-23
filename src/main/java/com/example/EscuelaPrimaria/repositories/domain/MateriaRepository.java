package com.example.EscuelaPrimaria.repositories.domain;

import com.example.EscuelaPrimaria.entities.domain.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
    Materia findMateriaByNombre(String nombre);
    boolean findMateriaByNombreEqualsIgnoreCase(String nombre);
}
