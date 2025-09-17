package com.example.EscuelaPrimaria.services.implementations;

import com.example.EscuelaPrimaria.entities.Profesional;
import com.example.EscuelaPrimaria.services.interfaces.ProfesionalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesionalServiceImpl implements ProfesionalService<Profesional, Long>{

    @Override
    public void add(Profesional entity) {

    }

    @Override
    public void update(Profesional entity) {

    }

    @Override
    public void delete(Profesional entity) {

    }

    @Override
    public List<Profesional> findAll() {
        return List.of();
    }

    @Override
    public Profesional findById(Long id) {
        return null;
    }
}
