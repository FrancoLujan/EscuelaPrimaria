package com.example.EscuelaPrimaria.errors;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.query.sqm.tree.expression.SqmHqlNumericLiteral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
// maneja todos los errores para evitar try y catch (crea los metodos mas comunes...)
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<MensajeError> handleEntityExistsException(EntityExistsException e) {
        MensajeError mensajeError = new MensajeError(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MensajeError> handleEntityNotFoundException(EntityNotFoundException e) {
        MensajeError mensajeError = new MensajeError(e.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
    }



    @ExceptionHandler(Exception.class) // errores no controlados GENERALES
    public ResponseEntity<MensajeError> handleException(Exception e) {

        MensajeError mensajeError = new MensajeError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
    }

}
