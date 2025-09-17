package com.example.EscuelaPrimaria.dtos.entrada;

import com.example.EscuelaPrimaria.enums.Cargo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProfesionalDtoE {
    private String nombre;
    private String apellido;
    private Long cuil;
    @Enumerated(EnumType.STRING)
    private Cargo cargo;


}
