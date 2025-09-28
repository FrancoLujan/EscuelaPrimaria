package com.example.EscuelaPrimaria.dtos.salida;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RolDtoS {
    @Enumerated(EnumType.STRING)
    private String rol;
    private List<PermisoDtoS> permisos; // es conveniente listar los permisos de un rol
                                        // en vez de los permisos con roles asociados
}
