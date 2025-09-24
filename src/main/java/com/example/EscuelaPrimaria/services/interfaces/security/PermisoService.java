package com.example.EscuelaPrimaria.services.interfaces.security;

import com.example.EscuelaPrimaria.entities.security.Permiso;
import com.example.EscuelaPrimaria.services.interfaces.Crud;

public interface PermisoService<T, K>  extends Crud<T, K> {
    boolean existsPermisoByNombre(String nombre);
    Permiso findByNombre(String nombre);
}
