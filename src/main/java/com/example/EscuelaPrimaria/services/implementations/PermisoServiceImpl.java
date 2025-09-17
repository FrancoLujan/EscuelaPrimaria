package com.example.EscuelaPrimaria.services.implementations;

import com.example.EscuelaPrimaria.entities.Permiso;
import com.example.EscuelaPrimaria.services.interfaces.PermisoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoServiceImpl implements PermisoService<Permiso, Long> {

    @Override
    public void add(Permiso entity) {

    }

    @Override
    public void update(Permiso entity) {

    }

    @Override
    public void delete(Permiso entity) {

    }

    @Override
    public List<Permiso> findAll() {
        return List.of();
    }

    @Override
    public Permiso findById(Long id) {
        return null;
    }
}
