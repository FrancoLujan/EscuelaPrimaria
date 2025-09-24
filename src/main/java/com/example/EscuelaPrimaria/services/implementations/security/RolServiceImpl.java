package com.example.EscuelaPrimaria.services.implementations.security;

import com.example.EscuelaPrimaria.entities.security.Rol;
import com.example.EscuelaPrimaria.repositories.security.RolRepository;
import com.example.EscuelaPrimaria.services.interfaces.security.RolService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RolServiceImpl implements RolService<Rol, Long > {
    private final RolRepository rolRepository;

    @Override
    public void add(Rol entity) {
        rolRepository.save(entity);
    }

    @Override
    public void update(Rol entity) {
        rolRepository.save(entity);

    }

    @Override
    public void delete(Long id) {
        rolRepository.deleteById(id);

    }

    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public Rol findById(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));
    }

    public void agregarRol() {}


    private void asociarPermisos(){

    }

}
