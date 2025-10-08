package com.example.EscuelaPrimaria.enums;

import com.example.EscuelaPrimaria.entities.security.Usuario;
import com.example.EscuelaPrimaria.enums.interfeces.RolGestor;
import com.example.EscuelaPrimaria.services.implementations.security.UsuarioServiceImpl;

public enum RolEnum {
    ADMINISTRADOR,
    DIRECTOR,
    VICEDIRECTOR,
    PROFESOR,
    ALUMNO;

}
