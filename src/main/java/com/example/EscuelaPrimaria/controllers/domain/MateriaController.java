package com.example.EscuelaPrimaria.controllers.domain;

import com.example.EscuelaPrimaria.dtos.entrada.MateriaDtoE;
import com.example.EscuelaPrimaria.services.implementations.domain.MateriaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Materia")
@AllArgsConstructor
@PreAuthorize("permitAll()")
public class MateriaController {
    final private MateriaServiceImpl servicio;

    @GetMapping("/todos")
    public ResponseEntity<List<String>> todos(){
        List<String> materias = servicio.todos();
        return ResponseEntity.status(HttpStatus.OK).body(materias);
    }

    @GetMapping("/todos/{nivel}")
    public ResponseEntity<List<String>> todos(@PathVariable Long nivel){
        List<String> materias = servicio.todos(nivel);
        return ResponseEntity.status(HttpStatus.OK).body(materias);

    }


    @GetMapping("/materia/{nombre}")
    public ResponseEntity<String> buscarMateriaPorNombre(@PathVariable String nombre){
        String materia = servicio.buscarMateriaPorNombre(nombre);
        return ResponseEntity.status(HttpStatus.OK).body(materia);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearMateria(@RequestBody MateriaDtoE dto){
        servicio.agregarMateria(dto.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto.getNombre());
    }

    @PatchMapping("/actualizar/{nombreMateria}")
    public ResponseEntity<String> actualizarMateria(@RequestBody MateriaDtoE dto, @PathVariable String nombreMateria){
        servicio.actualizarMateria(nombreMateria, dto.getNombre());
        return ResponseEntity.status(HttpStatus.OK).body("matteria actualizada");
    }

    @DeleteMapping("/eliminar/{nombreMateria}")
    public ResponseEntity<String> eliminarMateria(@PathVariable String nombreMateria){
        servicio.eliminarMateria(nombreMateria);
        return ResponseEntity.status(HttpStatus.OK).body("Se elimino la materia");
    }






}
