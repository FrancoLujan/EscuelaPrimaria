package com.example.EscuelaPrimaria.controllers.domain;

import com.example.EscuelaPrimaria.dtos.entrada.ProfesionalDtoE;
import com.example.EscuelaPrimaria.dtos.salida.ProfesionalDtoS;
import com.example.EscuelaPrimaria.services.implementations.domain.ProfesionalServiceImpl;
import com.example.EscuelaPrimaria.services.interfaces.domain.ProfesionalService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Profesional")
@AllArgsConstructor
public class ProfesionalController {

    private ProfesionalServiceImpl profesionalService;

    @PutMapping("/actualizar/{idUsuarioProfesional}")
    public ResponseEntity<String> actualizarUsuarioProfesional(@PathVariable Long idUsuarioProfesional, @RequestBody ProfesionalDtoE profesionalDtoE) {
        profesionalService.actualizarUsuarioProfesional(idUsuarioProfesional, profesionalDtoE);
        return ResponseEntity.ok("Actualizado exitosamente");
    }
    @PatchMapping("/actualizar/{cuil}")
    public ResponseEntity<String> actualizarDatos(@PathVariable Long cuil, @RequestBody ProfesionalDtoE profesionalDtoE) {
        profesionalService.actualizarDatos(cuil, profesionalDtoE);
        return ResponseEntity.ok("Actualizado exitosamente");
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ProfesionalDtoS>> findAll() {
        return ResponseEntity.ok(profesionalService.todos());
    }

    @GetMapping("/buscar/{cuil}")
    public ResponseEntity<List<ProfesionalDtoS>> buscar(@PathVariable Long cuil) {
        return ResponseEntity.ok(profesionalService.buscarPorCuil(cuil));
    }

    @GetMapping("buscar/profesional/{rol}")
    public ResponseEntity<List<ProfesionalDtoS>> profesionalPorRol(@PathVariable String rol) {
        return  ResponseEntity.ok(profesionalService.buscarProfesionalPorROl(rol));

    }
}

