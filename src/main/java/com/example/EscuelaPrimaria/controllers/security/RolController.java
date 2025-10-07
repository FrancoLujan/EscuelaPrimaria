package com.example.EscuelaPrimaria.controllers.security;

import com.example.EscuelaPrimaria.dtos.entrada.RolDtoE;
import com.example.EscuelaPrimaria.dtos.salida.RolDtoS;
import com.example.EscuelaPrimaria.entities.security.Rol;
import com.example.EscuelaPrimaria.services.implementations.security.RolServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Rol")
@AllArgsConstructor

@PreAuthorize("hasRole('ADMINISTRADOR')")
public class RolController {
    private final RolServiceImpl rolService;

    // DEBERIAS CREAR UN METODO QUE BUSQUE POR NOMBRE ASI LO DEVUELVE..+
    // TOTAL LOS ROLES COMO LOS PERMISOS SON RE ACOTADOS...
    @PostMapping("/crear/{rol}")
    public ResponseEntity<String> crear(@PathVariable String rol) {
        rolService.agregarRol(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(rol);

    }

    @GetMapping("/todos")
    public ResponseEntity<List<RolDtoS>> todos() {
        List<RolDtoS> roles = rolService.todos();
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

    @GetMapping("/buscarRol/{idRol}")
    public ResponseEntity<RolDtoS> buscarRol(@PathVariable Long idRol) {
        RolDtoS rol = rolService.buscarRol(idRol);
        return ResponseEntity.status(HttpStatus.OK).body(rol);

    }

    @PatchMapping("/asociar/permiso/{idRol}/{idPermiso}")
    public ResponseEntity<RolDtoS> asociarPermiso(@PathVariable Long idPermiso, @PathVariable Long idRol) {
        rolService.asociarPermisos(idRol, idPermiso);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(rolService.buscarRol(idRol));

    }
    @DeleteMapping("/eliminar/{idRol}")
    public ResponseEntity<String> eliminar(@PathVariable Long idRol) {
        rolService.eliminar(idRol);
        return ResponseEntity.status(HttpStatus.OK).body("Se a elimnado el rol ");
    }
    // se actualiza el nombre del rol(si hace cambiar RolEnum)
    @PatchMapping("/actualizar/{idRol}/{rol}")
    public ResponseEntity<RolDtoS> actualizar( @PathVariable String rol ,@PathVariable Long idRol) {
        rolService.actualizar(rol, idRol);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(rolService.buscarRol(idRol));
    }

}
