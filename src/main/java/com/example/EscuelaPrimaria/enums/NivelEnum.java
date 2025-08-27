package com.example.EscuelaPrimaria.enums;

import lombok.Getter;

@Getter

public enum NivelEnum {
    PRIMERO(1),
    SEGUNDO(2),
    TERCERO(3),
    QUINTO(4),
    SEXTO(5);

    private int valor;
    NivelEnum(int i) {
        this.valor = i;
    }

}
