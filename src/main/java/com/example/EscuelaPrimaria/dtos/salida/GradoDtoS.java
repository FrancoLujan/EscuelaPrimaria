package com.example.EscuelaPrimaria.dtos.salida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class GradoDtoS {
    private Long nivel;
    private TurnoDtoS turno;
    private ProfesionalDtoS profesor;
    List<MateriaDtoS> materias;
    List<AlumnoDtoS> alumnos;

}
