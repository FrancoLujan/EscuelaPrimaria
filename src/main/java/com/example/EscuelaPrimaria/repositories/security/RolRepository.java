package com.example.EscuelaPrimaria.repositories.security;

import com.example.EscuelaPrimaria.entities.security.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}
