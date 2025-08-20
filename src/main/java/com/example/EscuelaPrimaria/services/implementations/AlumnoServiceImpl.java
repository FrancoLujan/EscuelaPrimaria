package com.example.EscuelaPrimaria.services.implementations;

import com.example.EscuelaPrimaria.entities.Alumno;
import com.example.EscuelaPrimaria.services.interfaces.AlumnoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService<Alumno, Long> {
    @Override
    public void add(Alumno entity) {

    }

    @Override
    public void update(Alumno entity) {

    }

    @Override
    public void delete(Alumno entity) {

    }

    @Override
    public List<Alumno> getAll() {
        return List.of();
    }

    @Override
    public Alumno getById(Long id) {
        return null;
    }
}
