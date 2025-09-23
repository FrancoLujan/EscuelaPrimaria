package com.example.EscuelaPrimaria.gestores;

import com.example.EscuelaPrimaria.repositories.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;


@Component
@Getter
@AllArgsConstructor
// ESTE GESTOR MANEJA LAS CLASES PURAS DE EL DOMINIO
public class GestorRepositoryDomain {

    private final AlumnoRepository alumnoRepository;
    private final GradoRepository gradoRepository;
    private final MateriaRepository materiaRepository;
    private final TurnoRepository turnoRepository;
    private final ProfesionalRepository profesionalRepository;


}
