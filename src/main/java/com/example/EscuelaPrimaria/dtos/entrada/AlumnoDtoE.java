package com.example.EscuelaPrimaria.dtos.entrada;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDtoE {
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private Long cuil;
    private GradoDtoE grado;

}
