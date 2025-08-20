package com.example.EscuelaPrimaria.services.implementations;

import com.example.EscuelaPrimaria.entities.Turno;
import com.example.EscuelaPrimaria.services.interfaces.TurnoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoServiceImpl implements TurnoService<Turno, Long> {
    @Override
    public void add(Turno entity) {

    }

    @Override
    public void update(Turno entity) {

    }

    @Override
    public void delete(Turno entity) {

    }

    @Override
    public List<Turno> getAll() {
        return List.of();
    }

    @Override
    public Turno getById(Long id) {
        return null;
    }
}
