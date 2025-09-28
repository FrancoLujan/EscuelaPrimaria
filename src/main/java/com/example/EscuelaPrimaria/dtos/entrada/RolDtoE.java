package com.example.EscuelaPrimaria.dtos.entrada;

import com.example.EscuelaPrimaria.enums.RolEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RolDtoE {
    @Enumerated(EnumType.STRING)
    private RolEnum rol;
}
