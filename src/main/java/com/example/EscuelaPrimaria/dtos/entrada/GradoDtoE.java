package com.example.EscuelaPrimaria.dtos.entrada;

import com.example.EscuelaPrimaria.enums.NivelEnum;
import com.example.EscuelaPrimaria.enums.TurnoEnum;
import com.example.EscuelaPrimaria.errors.MensajeErrorValidaciones;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GradoDtoE {

    @NotNull(message = MensajeErrorValidaciones.MENSAJE_NUMERO)
    @Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO)
    @Max(value = 6, message = MensajeErrorValidaciones.MENSAJE_NUMERO)
    private Long nivel;


    @NotEmpty(message = MensajeErrorValidaciones.MENSAJE_NOMBRE)
    private TurnoEnum turno;

}
