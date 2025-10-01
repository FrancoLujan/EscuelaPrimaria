package com.example.EscuelaPrimaria.dtos.salida;

import com.example.EscuelaPrimaria.enums.RolEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class RolDtoS {
    @Enumerated(EnumType.STRING)
    private RolEnum rol;
    private List<PermisoDtoS> permisos; // es conveniente listar los permisos de un rol
                                        // en vez de los permisos con roles asociados
}
