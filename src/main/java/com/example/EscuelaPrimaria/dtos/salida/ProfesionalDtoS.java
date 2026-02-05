package com.example.EscuelaPrimaria.dtos.salida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Component
public class ProfesionalDtoS {
    private String nombre;
    private String apellido;
    private Long cuil;
    private Long grado;
    private List<String> rol;
}
