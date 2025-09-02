package com.example.EscuelaPrimaria.services.implementations;

import com.example.EscuelaPrimaria.entities.Alumno;
import com.example.EscuelaPrimaria.gestores.GestorRepo;
import com.example.EscuelaPrimaria.repositories.AlumnoRepository;
import com.example.EscuelaPrimaria.services.interfaces.AlumnoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlumnoServiceImpl implements AlumnoService<Alumno, Long> {
    private final GestorRepo gestorRepo;

    @Override
    public void add(Alumno entity) {
        gestorRepo.getAlumnoRepository().save(entity);
    }

    @Override
    public void update(Alumno entity) {
        gestorRepo.getAlumnoRepository().save(entity);

    }

    @Override
    public void delete(Alumno entity) {
        gestorRepo.getAlumnoRepository().deleteById(entity.getId());

    }

    @Override
    public List<Alumno> findAll() {
        return gestorRepo.getAlumnoRepository().findAll();
    }

    @Override
    public Alumno findById(Long id) {
        return gestorRepo.getAlumnoRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no se encontro el alumno con el id: " + id));
    }


}
