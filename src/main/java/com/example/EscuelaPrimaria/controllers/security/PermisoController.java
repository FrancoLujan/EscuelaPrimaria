package com.example.EscuelaPrimaria.controllers.security;

import com.example.EscuelaPrimaria.dtos.entrada.PermisoDtoE;
import com.example.EscuelaPrimaria.dtos.salida.PermisoDtoS;
import com.example.EscuelaPrimaria.services.implementations.security.PermisoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Permiso")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMINISTRADOR')")

// solo el administrador podra usar los endpoint , se da por hecho los permisos ADMINISTRADOR
// CREATE, READ , UPDATE, DELETE
public class PermisoController {
    private final PermisoServiceImpl permisoService;

    @PostMapping("/crear/{nombrePermiso}")
    public ResponseEntity<String> crear(@PathVariable String nombrePermiso) {
        permisoService.agregar(nombrePermiso);
        return ResponseEntity.status(HttpStatus.CREATED).body(nombrePermiso);
    }


    @GetMapping("/todos")

    public ResponseEntity<List<PermisoDtoS>> todos() {
        List<PermisoDtoS> permisos = permisoService.todos();

        return ResponseEntity.status(HttpStatus.OK).body(permisos);
    }

    @GetMapping("/buscarPermiso/{nombrePermiso}")
    public ResponseEntity<PermisoDtoS> buscarPermiso(@PathVariable String nombrePermiso) {
        PermisoDtoS permiso = permisoService.buscarPorNombre(nombrePermiso);
        return ResponseEntity.status(HttpStatus.OK).body(permiso);
    }

    @PatchMapping("/actualizar/{viejoPermiso}/{nuevoPermiso}")
    public ResponseEntity<PermisoDtoS> actualizar(@PathVariable String viejoPermiso, @PathVariable String nuevoPermiso) {
        permisoService.actualizar(viejoPermiso, nuevoPermiso);
        return ResponseEntity.status(HttpStatus.OK).body(permisoService.buscarPorNombre(nuevoPermiso));
    }

    @DeleteMapping("/eliminar/{permiso}")
    public ResponseEntity<String> eliminar(@PathVariable String permiso) {
        permisoService.eliminar(permiso);
        return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado el permiso " + permiso);
    }

}
