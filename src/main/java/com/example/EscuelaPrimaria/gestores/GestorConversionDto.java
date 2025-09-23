package com.example.EscuelaPrimaria.gestores;

import com.example.EscuelaPrimaria.dtos.salida.*;
import com.example.EscuelaPrimaria.entities.domain.Alumno;
import com.example.EscuelaPrimaria.entities.domain.Grado;
import com.example.EscuelaPrimaria.entities.domain.Profesional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.Collectors;


@Component

// CONVERTIR DTOS A MANO (TODO LO QUE INVOLUCRE DATOS RELACIONADOS CON AlumnoDtoS)
// Es decir todos los deteos complejos de salida
public class GestorConversionDto {
    // conversion a mano por el tema de mostrar la edad...
    public AlumnoDtoS converterAlumnDtoS(Alumno alumno) {
        AlumnoDtoS alumnoDto = new AlumnoDtoS();
        ModelMapper modelMapper = new ModelMapper();
        LocalDate localDate = LocalDate.now();

        int edad = localDate.getYear() - alumno.getFechaNacimiento().getYear();
        alumnoDto.setApellido(alumno.getApellido());
        alumnoDto.setNombre(alumno.getNombre());
        alumnoDto.setEdad(edad);
        alumnoDto.setGrado(converterGradoDtoS(alumno.getGrado()));
        return alumnoDto;
    }

    public GradoDtoS converterGradoDtoS(Grado grado) {
        GradoDtoS gradoDto = new GradoDtoS();
        ModelMapper modelMapper = new ModelMapper();
        gradoDto.setAlumnos(grado.getAlumnos().stream().map(this::converterAlumnDtoS).collect(Collectors.toList()));
        gradoDto.setTurno(modelMapper.map(grado.getTurno(), TurnoDtoS.class));
        gradoDto.setMaterias(grado.getMaterias().stream().map(m -> modelMapper.map(m, MateriaDtoS.class)).collect(Collectors.toList()));
        gradoDto.setNivel(grado.getNivel());
        gradoDto.setProfesor(converterProfesionalDtoS(grado.getProfesor()));
        return gradoDto;
    }

    public ProfesionalDtoS converterProfesionalDtoS(Profesional profesional) {
        ProfesionalDtoS profDto = new ProfesionalDtoS();
        profDto.setNombre(profesional.getNombre());
        profDto.setApellido(profesional.getApellido());
        profDto.setGrado(converterGradoDtoS(profesional.getGrado()));
        return profDto;
    }
}
