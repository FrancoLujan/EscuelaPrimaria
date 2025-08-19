package com.example.EscuelaPrimaria.repositories;

import com.example.EscuelaPrimaria.entities.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradoRepository extends JpaRepository<Grado, Long> {

}
