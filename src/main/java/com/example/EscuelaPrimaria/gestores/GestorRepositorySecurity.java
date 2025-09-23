package com.example.EscuelaPrimaria.gestores;

import com.example.EscuelaPrimaria.repositories.security.PermisoRepository;
import com.example.EscuelaPrimaria.repositories.security.RolRepository;
import com.example.EscuelaPrimaria.repositories.security.UsuarioRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class GestorRepositorySecurity {
    private final PermisoRepository permisoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;


}
