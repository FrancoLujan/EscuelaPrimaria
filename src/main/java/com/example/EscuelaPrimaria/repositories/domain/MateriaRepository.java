package com.example.EscuelaPrimaria.repositories.domain;

import com.example.EscuelaPrimaria.entities.domain.Grado;
import com.example.EscuelaPrimaria.entities.domain.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
    Materia findMateriaByNombre(String nombre);
    boolean findMateriaByNombreEqualsIgnoreCase(String nombre);
    List<Materia> findMateriaByGrado_Id(Long id);
    List<Materia> findMateriaByGrado_Nivel(Long nivel);
}
