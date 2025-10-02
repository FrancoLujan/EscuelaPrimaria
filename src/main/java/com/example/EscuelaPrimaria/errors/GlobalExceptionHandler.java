package com.example.EscuelaPrimaria.errors;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.query.sqm.tree.expression.SqmHqlNumericLiteral;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    // para manejar errores cuando no esta previsto En el enum l o que pase el cliente
    // (igual verificar en servicios)
    /*/
    TODO LO QUE MANEJE ENUMS TENGO QUE VALIDAR ESE ERROR DE ESA MANERA
    PUEDO EVITAR ERRORES DE MAPEO CON ENUMS
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MensajeError> handleParseException(IllegalArgumentException e) {
        MensajeError mensajeError = new MensajeError(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(mensajeError);
    }

    @ExceptionHandler(Exception.class) // errores no controlados GENERALES
    public ResponseEntity<String> handleException(Exception e) {

        MensajeError mensajeError = new MensajeError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
    }

}
