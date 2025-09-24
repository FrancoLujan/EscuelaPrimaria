package com.example.EscuelaPrimaria.dtos.salida;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UsuarioDtoS {
    private Long id; // necesario
    private String nombre;
    private String mail;

    private List<RolDtoS> rol;

}
