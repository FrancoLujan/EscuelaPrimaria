package com.example.EscuelaPrimaria.gestores;

import com.example.EscuelaPrimaria.repositories.PermisoRepository;
import com.example.EscuelaPrimaria.repositories.RolRepository;
import com.example.EscuelaPrimaria.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GestorEntitiesSecurity {

    private final PermisoRepository permisoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Autowired
    public GestorEntitiesSecurity(RolRepository rolRepository,
                                  UsuarioRepository usuarioRepository,
                                  PermisoRepository permisoRepository) {
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.permisoRepository = permisoRepository;
    }
}
