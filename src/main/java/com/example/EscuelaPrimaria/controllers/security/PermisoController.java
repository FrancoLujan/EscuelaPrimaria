package com.example.EscuelaPrimaria.controllers.security;

import com.example.EscuelaPrimaria.dtos.entrada.PermisoDtoE;
import com.example.EscuelaPrimaria.services.implementations.security.PermisoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Permiso")
@AllArgsConstructor
public class PermisoController {
    private final PermisoServiceImpl permisoServiceImpl;

    @PostMapping("/crear")
    public ResponseEntity<PermisoDtoE> crear(@RequestBody PermisoDtoE permisoDto) {

        permisoServiceImpl.agregar(permisoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(permisoDto);


    }

}
