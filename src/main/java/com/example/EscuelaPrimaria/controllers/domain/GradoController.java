package com.example.EscuelaPrimaria.controllers.domain;

import com.example.EscuelaPrimaria.dtos.entrada.GradoDtoE;
import com.example.EscuelaPrimaria.dtos.entrada.ProfesionalDtoE;
import com.example.EscuelaPrimaria.dtos.salida.GradoDtoS;
import com.example.EscuelaPrimaria.services.implementations.domain.GradoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Grado")
@AllArgsConstructor
public class GradoController {
    private final GradoServiceImpl gradoService;

    @PostMapping("/crear")
    public ResponseEntity<String> crear(@RequestBody GradoDtoE gradoDtoE) {
        gradoService.agregarGrado(gradoDtoE);
        return ResponseEntity.status(HttpStatus.CREATED).body("Grado creado");
    }

    @PatchMapping("/actualizar/{cuilProfe}")
    public ResponseEntity<String> actualizar(@RequestBody GradoDtoE gradoActualizar, @PathVariable Long cuilProfe) {
        gradoService.actualizarGrado(gradoActualizar, cuilProfe);
        return ResponseEntity.status(HttpStatus.OK).body("Grado actualizado");
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminar(@RequestBody GradoDtoE gradoDtoE) {
        gradoService.eliminarGrado(gradoDtoE);
        return ResponseEntity.status(HttpStatus.OK).body("Grado eliminado");
    }

    @GetMapping("/buscar/{nivel}")
    public ResponseEntity<List<GradoDtoS>> buscar(@PathVariable Long nivel) {

        return ResponseEntity.status(HttpStatus.OK).body(gradoService.buscarGradoPorNivel(nivel));
    }


    @GetMapping("/todos")
    public ResponseEntity<List<GradoDtoS>> todos() {

        List<GradoDtoS> grado = gradoService.todos();
        return ResponseEntity.status(HttpStatus.OK).body(grado);

    }


}
