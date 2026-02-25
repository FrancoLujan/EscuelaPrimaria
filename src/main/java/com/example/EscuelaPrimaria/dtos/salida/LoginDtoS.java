package com.example.EscuelaPrimaria.dtos.salida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class LoginDtoS {

    private String usuario;
    private String mensaje;
    private String token;
}
