package com.example.EscuelaPrimaria.repositories.domain;

import com.example.EscuelaPrimaria.entities.domain.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {
}
