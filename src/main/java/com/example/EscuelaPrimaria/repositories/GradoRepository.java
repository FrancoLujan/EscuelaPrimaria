package com.example.EscuelaPrimaria.repositories;

import com.example.EscuelaPrimaria.entities.Grado;
import com.example.EscuelaPrimaria.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradoRepository extends JpaRepository<Grado, Long> {
   boolean existsGradoByNivel(long nivel);
   boolean existsGradoByTurno_Nombre(String turnoNombre);
   Grado findGradoBynivel(long nivel, String turnoNombre);
   List<Grado> findGradoByNivel(long nivel);
   
}
