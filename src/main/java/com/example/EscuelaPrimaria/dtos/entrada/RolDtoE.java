package com.example.EscuelaPrimaria.dtos.entrada;

import com.example.EscuelaPrimaria.enums.RolEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
// importante los constructores
// no se esta usando
public class RolDtoE {
    @Enumerated(EnumType.STRING)
    private RolEnum rol;
}
