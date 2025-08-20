package com.example.EscuelaPrimaria.services.implementations;

import com.example.EscuelaPrimaria.entities.Grado;
import com.example.EscuelaPrimaria.services.interfaces.GradoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradoServiceImpl implements GradoService<Grado, Long> {


    @Override
    public void add(Grado entity) {

    }

    @Override
    public void update(Grado entity) {

    }

    @Override
    public void delete(Grado entity) {

    }

    @Override
    public List<Grado> getAll() {
        return List.of();
    }

    @Override
    public Grado getById(Long id) {
        return null;
    }
}
