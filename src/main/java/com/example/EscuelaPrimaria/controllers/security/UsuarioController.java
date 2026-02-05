package com.example.EscuelaPrimaria.controllers.security;

import com.example.EscuelaPrimaria.dtos.entrada.UsuarioDtoE;
import com.example.EscuelaPrimaria.dtos.entrada.UsuarioDtoSeteo;
import com.example.EscuelaPrimaria.dtos.salida.UsuarioDtoS;
import com.example.EscuelaPrimaria.services.implementations.security.UsuarioServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Usuario")
@AllArgsConstructor
//@PreAuthorize("hasRole('ADMINISTRADOR')")

/*
    Importante pasos de creacion correcta
    1. crear usuario
    2. asignar rol (esto creara un Alumno o Un profesional  )
    3. actualizar datos, del correspondiente rol es decir si es un profesionar uso los edpoint de profesionar para actualizar
    y confirmar una creacion correcta del usuario y el rol
    4. FIN. Seguido los 3 pasos tendras un profesional o un alumno perfectamente creado
 */
public class UsuarioController {
    private final UsuarioServiceImpl usuarioService;


    @PostMapping("/crear")
    public ResponseEntity<String> crear(@Valid @RequestBody UsuarioDtoE usuarioDto){
        usuarioService.agregar(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDto.getNombre());

    }


    @GetMapping("/todos")
    public ResponseEntity<List<UsuarioDtoS>> todos() {
        List<UsuarioDtoS> usuarios = usuarioService.todos();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }
    @GetMapping("/buscar/{idUsuario}")
    public ResponseEntity<UsuarioDtoS> buscar(@PathVariable Long idUsuario) {
        UsuarioDtoS usuario = usuarioService.buscarUsuario(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PatchMapping("/actualizar/{idUsuario}/mail/{mail}")
    public ResponseEntity<UsuarioDtoS> actualizarMail(@PathVariable Long idUsuario,  @PathVariable String mail) {
        usuarioService.actualizarMail(idUsuario, mail);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioService.buscarUsuario(idUsuario));
    }

    @PatchMapping("/actualizar/{idUsuario}/password/{password}")
    public ResponseEntity<String> actualizarPassword(@PathVariable Long idUsuario, @PathVariable String password) {
        usuarioService.actualizarPassword(idUsuario, password);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Password actualizado");
    }

    @PatchMapping("/actualizar/{idUsuario}/nombreUsuario/{nombre}")
    public ResponseEntity<UsuarioDtoS> actualizarNombreUsuario(@PathVariable Long idUsuario, @PathVariable String nombre) {
        usuarioService.actualizarNombre(idUsuario, nombre);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioService.buscarUsuario(idUsuario));
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<String> eliminar(@PathVariable Long idUsuario) {
        usuarioService.eliminar(idUsuario);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario eliminado");
    }

    // Cuando se asigna un rol dependiendo del rol se creara por ejemplo un alumno
    @PatchMapping("/asignar/usuario/{idUsuario}/rol/{idRol}")
    public ResponseEntity<UsuarioDtoS> asignarUsuario(@PathVariable Long idUsuario, @PathVariable Long idRol) {
        usuarioService.asociarRol(idUsuario, idRol);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioService.buscarUsuario(idUsuario));
    }

    @PatchMapping("/actualizar/estado/{idUsuario}/")
    public ResponseEntity<String> actualizarEstado(@PathVariable Long idUsuario, @RequestBody UsuarioDtoSeteo estados) {
        usuarioService.actualizarEstados(idUsuario, estados);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Estados actualizado");


    }
}
