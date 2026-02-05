package com.example.EscuelaPrimaria.dtos.entrada;

import com.example.EscuelaPrimaria.errors.MensajeErrorValidaciones;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProfesionalDtoE {
    @NotEmpty(message = MensajeErrorValidaciones.MENSAJE_NOMBRE)
    private String nombre;

    @NotEmpty(message = MensajeErrorValidaciones.MENSAJE_NOMBRE)
    private String apellido;

    @NotNull(message = MensajeErrorValidaciones.MENSAJE_NUMERO)
    @Min(value = 99999999, message = MensajeErrorValidaciones.MENSAJE_NUMERO)
    @Max(value = 9999999999L ,message = MensajeErrorValidaciones.MENSAJE_NUMERO)
    private Long cuil;


}
