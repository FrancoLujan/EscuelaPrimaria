package com.example.EscuelaPrimaria.enums;

import lombok.Getter;

@Getter

public enum NivelEnum {
    PRIMERO(1L),
    SEGUNDO(2L),
    TERCERO(3L),
    QUINTO(4L),
    SEXTO(5L);

    private Long valor;
    NivelEnum(Long i) {
        this.valor = i;
    }

}
