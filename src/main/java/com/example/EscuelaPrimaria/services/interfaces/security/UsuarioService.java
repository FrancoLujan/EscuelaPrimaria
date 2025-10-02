package com.example.EscuelaPrimaria.services.interfaces.security;

import com.example.EscuelaPrimaria.services.interfaces.Crud;

import java.util.Optional;

public interface UsuarioService<T,K> extends Crud<T,K> {
    Optional<T> findUsuarioByNombre(String nombre);
    String encriptarPassword(String password);
    boolean existsUsuarioByNombre(String nombre);
}
