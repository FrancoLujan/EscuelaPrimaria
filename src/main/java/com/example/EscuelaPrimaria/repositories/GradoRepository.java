package com.example.EscuelaPrimaria.repositories;

import com.example.EscuelaPrimaria.entities.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradoRepository extends JpaRepository<Grado, Long> {
   boolean existsGradoByNivel(int nivel);
   boolean existsGradoByTurno_Nombre(String turnoNombre);
   List<Grado> findGradoByNivel(int nivel);
}
