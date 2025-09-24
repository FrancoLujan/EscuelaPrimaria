package com.example.EscuelaPrimaria.dtos.entrada;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RolDtoE {
    @Enumerated(EnumType.STRING)
    private String rol;
}
