package com.example.EscuelaPrimaria.gestores;

import com.example.EscuelaPrimaria.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// ESTE GESTOR MANEJA LAS CLASES PURAS DE EL DOMINIO
public class GestorRepo {


    private final AlumnoRepository alumnoRepository;
    private final GradoRepository gradoRepository;
    private final MateriaRepository materiaRepository;
    private final TurnoRepository turnoRepository;
    private final ProfesionalRepository profesionalRepository;

    @Autowired
    public GestorRepo(AlumnoRepository alumnoRepository,
                      GradoRepository gradoRepository,
                      MateriaRepository materiaRepository,
                      TurnoRepository turnoRepository,
                      ProfesionalRepository profesionalRepository) {
        this.alumnoRepository = alumnoRepository;
        this.gradoRepository = gradoRepository;
        this.materiaRepository = materiaRepository;
        this.turnoRepository = turnoRepository;
        this.profesionalRepository = profesionalRepository;
    }
}
