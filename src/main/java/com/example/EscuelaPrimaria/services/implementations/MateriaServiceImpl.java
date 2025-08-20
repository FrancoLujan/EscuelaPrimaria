package com.example.EscuelaPrimaria.services.implementations;

import com.example.EscuelaPrimaria.entities.Materia;
import com.example.EscuelaPrimaria.services.interfaces.MateriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService<Materia, Long> {
    @Override
    public void add(Materia entity) {

    }

    @Override
    public void update(Materia entity) {

    }

    @Override
    public void delete(Materia entity) {

    }

    @Override
    public List<Materia> getAll() {
        return List.of();
    }

    @Override
    public Materia getById(Long id) {
        return null;
    }
}
