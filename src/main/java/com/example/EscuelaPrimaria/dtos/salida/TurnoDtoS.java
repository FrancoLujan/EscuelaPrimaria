package com.example.EscuelaPrimaria.dtos.salida;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Component
public class TurnoDtoS {
    private String nombre;
    private LocalTime horaInicio;
    private LocalTime horaFin;

}
