package com.example.EscuelaPrimaria.errors;


import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.InvocationTargetException;

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
    // SE CAMBIO A BAD_REQUEST es mas general porque asi es tratado en los servicios con el mesaje
    // 'Formato no valido'
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MensajeError> handleParseException(IllegalArgumentException e) {
        MensajeError mensajeError = new MensajeError(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
    }
    // MANEJARE LOS ERRORES QUE SALGAN DE LOS DECORADORES @Valid y @NotBLank
    // @Valid en controladores
    // @NotBlank en dtos
    // ESTOS HACEN QUE EL CAMPO SEA OBLIGATORIO Y SI NO SE LLENA SALTA ERROR...
    // TENER EN CUENTA QUE ESTO SALTA CUANDO SE PASA UN DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        MensajeError mensajeError = new MensajeError("ERROR DE PARAMETROS ", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
    }

    // MANEJO LOS ERRORES DE VALIDACION EN LOS SERVICIOS QUE USAN
    // LOS DECORADORES DE ''validacion''
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MensajeError> handleConstraintViolationException(ConstraintViolationException e) {
        String mensaje = e.getMessage().split(":",2)[1]; // evito el mensaje que no va y solo uso el mio personalizado
        MensajeError mensajeError = new MensajeError(mensaje.trim(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
    }

     @ExceptionHandler(UsernameNotFoundException.class)
     public ResponseEntity<MensajeError> handleUsernameNotFoundException(UsernameNotFoundException e) {
        MensajeError mensajeError = new MensajeError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
     }

    @ExceptionHandler(Exception.class) // errores no controlados GENERALES
    public ResponseEntity<MensajeError> handleException(Exception e) {

        MensajeError mensajeError = new MensajeError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
    }

}
