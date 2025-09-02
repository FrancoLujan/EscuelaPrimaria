package com.example.EscuelaPrimaria.dtos.salida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Component
public class ProfesionalDtoS {
    private String nombre;
    private String apellido;
    private GradoDtoS grado;
}
