package com.example.EscuelaPrimaria.controllers.security;

import com.example.EscuelaPrimaria.dtos.entrada.UsuarioDtoE;
import com.example.EscuelaPrimaria.dtos.salida.UsuarioDtoS;
import com.example.EscuelaPrimaria.services.implementations.security.UsuarioServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Usuario")
@AllArgsConstructor
public class UsuarioController {
    private final UsuarioServiceImpl usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<String> crear(@Valid @RequestBody UsuarioDtoE usuarioDto) throws MethodArgumentNotValidException {
        usuarioService.agregar(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDto.getNombre());

    }
}
