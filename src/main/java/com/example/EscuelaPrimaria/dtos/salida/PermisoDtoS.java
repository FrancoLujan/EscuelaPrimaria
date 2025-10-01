package com.example.EscuelaPrimaria.dtos.salida;

import com.example.EscuelaPrimaria.enums.PermisoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PermisoDtoS {
    @Enumerated(EnumType.STRING)
    private PermisoEnum permisoEnum;


}
