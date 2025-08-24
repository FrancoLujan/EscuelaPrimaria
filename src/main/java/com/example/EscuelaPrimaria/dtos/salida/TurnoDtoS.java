package com.example.EscuelaPrimaria.dtos.salida;

import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TurnoDtoS {
    private String nombre;
    private LocalTime horaInicio;
    private LocalTime horaFin;

}
