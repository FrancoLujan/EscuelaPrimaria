package com.example.EscuelaPrimaria.repositories;

import com.example.EscuelaPrimaria.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
        // devuelve un usuario o un Optional.empty()
        Optional findUserEntityByUsername(String username);



}
