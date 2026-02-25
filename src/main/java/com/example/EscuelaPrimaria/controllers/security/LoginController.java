package com.example.EscuelaPrimaria.controllers.security;

import com.example.EscuelaPrimaria.dtos.entrada.LoginDtoE;
import com.example.EscuelaPrimaria.dtos.salida.LoginDtoS;
import com.example.EscuelaPrimaria.services.implementations.security.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private final UsuarioServiceImpl usuarioServiceImpl;

    public LoginController(UsuarioServiceImpl usuarioServiceImpl) {
        this.usuarioServiceImpl = usuarioServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDtoS> login(@RequestBody LoginDtoE login) {
        LoginDtoS loginDtoS = usuarioServiceImpl.loginUser(login);
        return ResponseEntity.status(HttpStatus.OK).body(loginDtoS);

    }
}
