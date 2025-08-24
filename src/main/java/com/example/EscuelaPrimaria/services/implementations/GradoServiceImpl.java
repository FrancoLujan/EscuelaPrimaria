package com.example.EscuelaPrimaria.services.implementations;

import com.example.EscuelaPrimaria.entities.Grado;
import com.example.EscuelaPrimaria.gestores.GestorRepo;
import com.example.EscuelaPrimaria.services.interfaces.GradoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class GradoServiceImpl implements GradoService<Grado, Long> {


    private final GestorRepo gestor;

    @Override
    public void add(Grado entity) {
        gestor.getGradoRepository().save(entity);

    }

    @Override
    public void update(Grado entity) {
        gestor.getGradoRepository().save(entity);

    }

    @Override
    public void delete(Grado entity) {
        gestor.getGradoRepository().deleteById(entity.getId());

    }

    @Override
    public List<Grado> getAll() {
        return gestor.getGradoRepository().findAll();
    }

    @Override
    public Grado getById(Long id) {
        return gestor.getGradoRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no se encontro grado con el id:" + id));
    }



}
