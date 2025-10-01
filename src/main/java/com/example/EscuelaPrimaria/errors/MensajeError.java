package com.example.EscuelaPrimaria.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalTime;

// mensaje de error personalizado...
@Getter
@Setter
public class MensajeError {
    private String mensaje;
    private HttpStatus status;
    private LocalTime horaDeError;


    public MensajeError(String mensaje, HttpStatus status) {
        this.mensaje = mensaje;
        this.status = status;
        this.horaDeError = LocalTime.now();
    }
}
