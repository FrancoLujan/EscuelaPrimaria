package com.example.EscuelaPrimaria.services.implementations.security;

import com.example.EscuelaPrimaria.entities.security.Rol;
import com.example.EscuelaPrimaria.services.interfaces.security.RolService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService<Rol, Long > {
    @Override
    public void add(Rol entity) {

    }

    @Override
    public void update(Rol entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Rol> findAll() {
        return List.of();
    }

    @Override
    public Rol findById(Long id) {
        return null;
    }


}
