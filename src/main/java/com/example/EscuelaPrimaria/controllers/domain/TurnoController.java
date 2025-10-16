package com.example.EscuelaPrimaria.controllers.domain;

import com.example.EscuelaPrimaria.dtos.salida.TurnoDtoS;
import com.example.EscuelaPrimaria.services.implementations.domain.TurnoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Turno")
@AllArgsConstructor
@PreAuthorize("hasAuthority('ADMINISTRADOR')")
public class TurnoController {
    final private TurnoServiceImpl turnoService;

    @PostMapping("/crear/{nuevoTurno}")
    public ResponseEntity<TurnoDtoS> crear(@PathVariable String nuevoTurno) {
        turnoService.agregar(nuevoTurno);
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoService.buscarPorNombre(nuevoTurno));

    }

    @PatchMapping("/actualizar/{turnoViejo}/{turnoNuevo}")
    public ResponseEntity<TurnoDtoS> actualizar(@PathVariable String turnoViejo, @PathVariable String turnoNuevo) {
        turnoService.actualizar(turnoViejo, turnoNuevo);
        return ResponseEntity.status(HttpStatus.OK).body(turnoService.buscarPorNombre(turnoNuevo));

    }

    @DeleteMapping("/eliminar/{turnoEliminar}")
    public ResponseEntity<String> eliminar(@PathVariable String turnoEliminar) {
        turnoService.eliminar(turnoEliminar);
        return ResponseEntity.status(HttpStatus.OK).body("El Turno eliminado correctamente");
    }

    @PreAuthorize("hasAnyRole(permitAll())")
    @GetMapping("/todos")
    public ResponseEntity<List<TurnoDtoS>> todos() {
        List<TurnoDtoS> turnos = turnoService.todos();
        return ResponseEntity.status(HttpStatus.OK).body(turnos);
    }
    @PreAuthorize("hasAnyRole(permitAll())")
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<TurnoDtoS> buscar(@PathVariable String nombre) {

        return ResponseEntity.status(HttpStatus.OK).body(turnoService.buscarPorNombre(nombre));
    }
}
