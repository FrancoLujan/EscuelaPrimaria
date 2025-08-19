package com.example.EscuelaPrimaria.repositories;

import com.example.EscuelaPrimaria.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
}
