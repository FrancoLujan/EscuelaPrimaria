package com.example.EscuelaPrimaria.gestores;

import com.example.EscuelaPrimaria.dtos.salida.*;
import com.example.EscuelaPrimaria.entities.domain.Alumno;
import com.example.EscuelaPrimaria.entities.domain.Grado;
import com.example.EscuelaPrimaria.entities.domain.Profesional;
import com.example.EscuelaPrimaria.entities.security.Permiso;
import com.example.EscuelaPrimaria.entities.security.Rol;
import com.example.EscuelaPrimaria.entities.security.Usuario;
import com.example.EscuelaPrimaria.enums.PermisoEnum;
import com.example.EscuelaPrimaria.enums.RolEnum;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component

// CONVERTIR DTOS A MANO (TODO LO QUE INVOLUCRE DATOS COMPUESTOS...)
//
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

    public RolDtoS converterRolDtoS(Rol rol) {
        RolDtoS rolDtoS = new RolDtoS();
        rolDtoS.setRol(RolEnum.valueOf(rol.getNombre()));

        List<PermisoDtoS> listaPermisos = rol.getPermisos().
                stream().map(this::converterPermisoDtoS)
                .toList();

        rolDtoS.setPermisos(listaPermisos);
        return rolDtoS;
    }

    public UsuarioDtoS converterUsuarioDtoS(Usuario usuario) {
        UsuarioDtoS usuarioDtoS = new UsuarioDtoS();
        usuarioDtoS.setNombre(usuario.getNombre());
        usuarioDtoS.setId(usuario.getId());
        usuarioDtoS.setMail(usuario.getMail());
        usuarioDtoS.setRol(usuario.getRoles().stream().map(this::converterRolDtoS).toList());
        return usuarioDtoS;
    }



    // OJO RESULTA QUE MAPPER LE CUESTA UN MONTON HACER MAPEOS SI HAY ATRIBUTOS NO PRIMITIVOS
    // ES MEJOR HACER CONVERSIONES A MANO SIEMPRE Y CUANDO SEAN COMPUESTAS...
    public PermisoDtoS converterPermisoDtoS(Permiso permiso) {
        PermisoDtoS permisoDto = new PermisoDtoS();
        permisoDto.setPermisoEnum(PermisoEnum.valueOf(permiso.getNombre()));
        return permisoDto;
    }
}
