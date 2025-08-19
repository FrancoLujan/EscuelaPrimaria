package com.example.EscuelaPrimaria.repositories;

import com.example.EscuelaPrimaria.entities.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {
}
