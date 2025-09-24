package com.example.EscuelaPrimaria.dtos.entrada;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.Email;
@AllArgsConstructor
@Getter
public class UsuarioDtoE {
    private String nombre;
    @Email
    private String mail;

    private String password;
}
