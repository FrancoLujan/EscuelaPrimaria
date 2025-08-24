package com.example.EscuelaPrimaria.dtos.salida;

import com.example.EscuelaPrimaria.entities.Materia;
import com.example.EscuelaPrimaria.entities.Profesional;
import com.example.EscuelaPrimaria.entities.Turno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GradoDtoS {
    private  int nivel;
    private Turno turno;
    private Profesional profesor;
    List<MateriaDtoS> materias;
    List<AlumnoDtoS> alumnos;

}
