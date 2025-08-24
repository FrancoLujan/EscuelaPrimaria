package com.example.EscuelaPrimaria.dtos.salida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ProfesionalDtoS {
    private String nombre;
    private String apellido;
    private GradoDtoS grado;
}
