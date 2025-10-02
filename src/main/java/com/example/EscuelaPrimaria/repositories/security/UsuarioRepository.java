package com.example.EscuelaPrimaria.repositories.security;

import com.example.EscuelaPrimaria.entities.security.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

        Optional<Usuario> findUsuarioByNombre(String nombre);
        Boolean existsUsuarioByNombre(String nombre);


}
