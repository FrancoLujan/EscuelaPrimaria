package com.example.EscuelaPrimaria.dtos.entrada;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.Email;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UsuarioDtoE {
    @NotBlank
    @Size(min = 4, max = 10, message = "nombre muy pequeño o muy largo")
    private String nombre;
    @NotBlank
    @Email(message = "Mail no valido")
    private String mail;
    @NotBlank
    @Size(min = 5, max = 20 ,message = "password muy pequeño o largo ")
    private String password;

}
