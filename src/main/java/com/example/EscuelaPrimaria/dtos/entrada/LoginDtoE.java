package com.example.EscuelaPrimaria.dtos.entrada;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginDtoE {
    @NotBlank
    private String usuario;

    @NotBlank
    @Size(min = 5, max = 20 ,message = "password muy pequeño o largo ")
    private String password;

}
