package com.example.EscuelaPrimaria.dtos.entrada;

import com.example.EscuelaPrimaria.errors.MensajeErrorValidaciones;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDtoE {
    @NotNull
    @NotEmpty(message = MensajeErrorValidaciones.MENSAJE_NOMBRE)
    private String nombre;
    @NotNull
    @NotEmpty(message = MensajeErrorValidaciones.MENSAJE_NOMBRE)
    private String apellido;

    @NotNull
    @PastOrPresent(message = MensajeErrorValidaciones.MENSAJE_FECHA)
    private LocalDate fechaNacimiento;
    @NotNull
    @Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO)
    private Long cuil;

}
