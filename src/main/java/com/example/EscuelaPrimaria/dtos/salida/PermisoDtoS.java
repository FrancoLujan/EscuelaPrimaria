package com.example.EscuelaPrimaria.dtos.salida;

import com.example.EscuelaPrimaria.enums.PermisoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

public class PermisoDtoS {
    @Enumerated(EnumType.STRING)
    private PermisoEnum permisoEnum;


}
