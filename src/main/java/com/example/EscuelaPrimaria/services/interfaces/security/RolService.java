package com.example.EscuelaPrimaria.services.interfaces.security;

import com.example.EscuelaPrimaria.services.interfaces.Crud;

public interface RolService <T, K> extends Crud<T,K> {
    boolean existsRolByNombre(String nombre);
}
