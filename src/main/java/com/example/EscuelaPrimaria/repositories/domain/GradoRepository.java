package com.example.EscuelaPrimaria.repositories.domain;

import com.example.EscuelaPrimaria.entities.domain.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradoRepository extends JpaRepository<Grado, Long> {
   boolean existsGradoByNivel(long nivel);
   boolean existsGradoByTurno_Nombre(String turnoNombre);

   Grado findGradoBynivel_AndTurno_Nombre(Long nivel, String turnoNombre);
   List<Grado> findGradoByNivel(long nivel);
   
}
