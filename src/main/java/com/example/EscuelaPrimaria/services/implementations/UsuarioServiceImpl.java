package com.example.EscuelaPrimaria.services.implementations;

import com.example.EscuelaPrimaria.entities.Usuario;
import com.example.EscuelaPrimaria.services.interfaces.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService<Usuario, Long> {

    @Override
    public Usuario getByName(String name) {
        return null;
    }

    @Override
    public void add(Usuario entity) {

    }

    @Override
    public void update(Usuario entity) {

    }

    @Override
    public void delete(Usuario entity) {

    }

    @Override
    public List<Usuario> findAll() {
        return List.of();
    }

    @Override
    public Usuario findById(Long id) {
        return null;
    }
}
