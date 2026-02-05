package com.example.EscuelaPrimaria.controllers.domain;

import com.example.EscuelaPrimaria.dtos.entrada.AlumnoDtoE;
import com.example.EscuelaPrimaria.dtos.salida.AlumnoDtoS;
import com.example.EscuelaPrimaria.services.implementations.domain.AlumnoServiceImpl;
import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Alumno")
@AllArgsConstructor
// POSIBLE REPLANTEO
// porque la logica no permite que se crear alumno de manera independiente si no de manera conjunta con usuario
// por logica entonces al eliminar el usuario se elimina el alumno entonces pensar si dejas el endpoint
//@PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ALUMNO')")
public class AlumnoController {
    private final AlumnoServiceImpl alumnoService;

//    @PostMapping("/crear")
//    public ResponseEntity<String> crear(@RequestBody AlumnoDtoE alumno) {
//        alumnoService.agregar(alumno);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Alumno creado correctamente");
//
//    }

    //@PreAuthorize("hasRole('ADMINISTRADOR')")

    @PatchMapping("/actualizar/usuario/{idUsuario}")
    public ResponseEntity<String> actualizarUsuarioAlumno(@RequestBody AlumnoDtoE alumno, @PathVariable Long idUsuario) {
        alumnoService.actualizarUsuarioAlumno(idUsuario, alumno);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Actualizado Correctamente");

    }
    //@PreAuthorize("hasAuthority('UPDATE')")
    @PatchMapping("/actualizar/datos/{cuil}")
    public ResponseEntity<String> actualizarDatos(@RequestBody AlumnoDtoE alumno, @PathVariable Long cuil) {
        alumnoService.actualizarDatos(cuil, alumno);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Actualizado correctamente");

    }

   // @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/eliminar/{cuil}")
    public ResponseEntity<String> eliminar(@PathVariable Long cuil) {
        alumnoService.eliminar(cuil);
        return ResponseEntity.status(HttpStatus.OK).body("Alumno eliminado correctamente");
    }

    //@PreAuthorize("hasAuthority('READ')")
    @GetMapping("/todos")
    public ResponseEntity<List<AlumnoDtoS>> todos(){
        List<AlumnoDtoS> alumnos = alumnoService.todos();
        return ResponseEntity.status(HttpStatus.OK).body(alumnos);
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/buscar/alumno/{cuil}")
    public ResponseEntity<AlumnoDtoS> buscar(@PathVariable Long cuil){
       AlumnoDtoS alumno  = alumnoService.buscarAlumnoPorCuil(cuil);
       return ResponseEntity.status(HttpStatus.OK).body(alumno);
    }
}
