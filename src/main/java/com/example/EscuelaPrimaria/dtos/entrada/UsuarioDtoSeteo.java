package com.example.EscuelaPrimaria.dtos.entrada;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
// ESTA CLASE dto SERA UTILIZADA PARA ACTUALIZAR LOS ESTADOS DEL USUARIO
public class UsuarioDtoSeteo {
    private boolean accountNotExpired;
    private boolean accountNotLocked;
    private boolean credentialsNotExpired;
    private boolean enabled;
}
