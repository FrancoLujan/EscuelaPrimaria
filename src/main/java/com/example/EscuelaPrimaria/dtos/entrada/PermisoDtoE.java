package com.example.EscuelaPrimaria.dtos.entrada;

import com.example.EscuelaPrimaria.enums.PermisoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

// ES EN VERDAD NECESARIO ? EL DE SALIDA LO ES PERO EL DE ENTRADA ?
public class PermisoDtoE {
    @Enumerated(EnumType.STRING)
    private PermisoEnum permiso;

}
