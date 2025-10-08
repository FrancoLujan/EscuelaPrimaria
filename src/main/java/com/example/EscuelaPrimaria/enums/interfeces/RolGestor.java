package com.example.EscuelaPrimaria.enums.interfeces;

import com.example.EscuelaPrimaria.entities.security.Usuario;
import com.example.EscuelaPrimaria.enums.RolEnum;
import com.example.EscuelaPrimaria.services.implementations.security.UsuarioServiceImpl;

public interface RolGestor {
    // a partir del usuario y su rol crea o no el tipo de usuario (ej: alumno )
    void crear(UsuarioServiceImpl usuario);
}
