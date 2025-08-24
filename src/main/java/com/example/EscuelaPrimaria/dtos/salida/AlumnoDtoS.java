package com.example.EscuelaPrimaria.dtos.salida;

import com.example.EscuelaPrimaria.dtos.entrada.GradoDtoE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class AlumnoDtoS {
    private String nombre;
    private String apellido;
    private int edad;
    private GradoDtoE grado;

}
