package com.example.EscuelaPrimaria.gestores;

import com.example.EscuelaPrimaria.dtos.salida.*;
import com.example.EscuelaPrimaria.entities.Alumno;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component

public class GestorConversionDto {

    private AlumnoDtoS converterAlumnDtoS(Alumno alumno) {
        AlumnoDtoS alumnoDto = new AlumnoDtoS();
        ModelMapper modelMapper = new ModelMapper();
        LocalDate localDate = LocalDate.now();

        int edad = localDate.getYear() - alumno.getFechaNacimiento().getYear() ;
        alumnoDto.setApellido(alumno.getApellido());
        alumnoDto.setNombre(alumno.getNombre());
        alumnoDto.setEdad(edad);
        alumnoDto.setGrado(modelMapper.map(alumno.getGrado(), GradoDtoS.class));
        return alumnoDto;
    }
}
