package com.example.EscuelaPrimaria.gestores;

import com.example.EscuelaPrimaria.repositories.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
@Getter
@AllArgsConstructor
// ESTE GESTOR MANEJA LAS CLASES PURAS DE EL DOMINIO
public class GestorRepo {

    private final AlumnoRepository alumnoRepository;
    private final GradoRepository gradoRepository;
    private final MateriaRepository materiaRepository;
    private final TurnoRepository turnoRepository;
    private final ProfesionalRepository profesionalRepository;


}
