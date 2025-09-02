package com.example.EscuelaPrimaria.services.implementations;

import com.example.EscuelaPrimaria.dtos.entrada.AlumnoDtoE;
import com.example.EscuelaPrimaria.dtos.salida.AlumnoDtoS;
import com.example.EscuelaPrimaria.dtos.salida.GradoDtoS;
import com.example.EscuelaPrimaria.entities.Alumno;
import com.example.EscuelaPrimaria.entities.Grado;
import com.example.EscuelaPrimaria.gestores.GestorConversionDto;
import com.example.EscuelaPrimaria.gestores.GestorRepo;
import com.example.EscuelaPrimaria.services.interfaces.AlumnoService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlumnoServiceImpl implements AlumnoService<Alumno, Long> {
    private GestorRepo gestorRepo;
    private GestorConversionDto gestorConversionDto;

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
    // POR CUERENCIA USO EL CUIL QUE ES UN ELEMENTO IRREPETIBLE (EN EL FUTURO CAMBIAR PK) O
    // CREAR UN METODO EN REPOSITORY QUE ME TRAIGA EL ID PASANDO EL CUIL
    // SIN EMBARGO EL METODO QUE IMPLEMENTA LA BUSQUEDA, BUSCA POR CUIL A LA ENTIDAD Y LUEGO PASA EL ID DE LA ENTIDAD
    public Alumno findById(Long id) {
        return gestorRepo.getAlumnoRepository().findById(id).orElse(null);
    }

    @Override
    public Alumno findAlumnoByCuil(Long cuil) {
        return gestorRepo.getAlumnoRepository().findAlumnoByCuil(cuil);
    }

    public void agregarAlumno(AlumnoDtoE alumno) throws EntityExistsException {
        if (alumno.getCuil() != null &&
                findAlumnoByCuil(alumno.getCuil()) == null) {
            ModelMapper modelMapper = new ModelMapper();
            Alumno entity = modelMapper.map(alumno, Alumno.class);
            add(entity);
        } else {
            throw new EntityExistsException("El alumno ya existe");
        }

    }

    public void modificarAlumno(AlumnoDtoE alumno) throws EntityNotFoundException {
        if (findAlumnoByCuil(alumno.getCuil()) != null) {
            ModelMapper modelMapper = new ModelMapper();
            Alumno entity = modelMapper.map(alumno, Alumno.class);
            update(entity);
        } else {
            throw new EntityNotFoundException("El alumno no existe");
        }

    }

    public void eliminarAlumno(Long cuil) throws EntityNotFoundException {
        if (findAlumnoByCuil(cuil) != null) {
            delete(findAlumnoByCuil(cuil));
        } else {
            throw new EntityNotFoundException("El alumno no existe");
        }

    }

    public List<AlumnoDtoS> todos() {
        ModelMapper modelMapper = new ModelMapper();
        List<Alumno> alumnos = findAll();
        return alumnos.stream().map(e -> gestorConversionDto.converterAlumnDtoS(e))
                .collect(Collectors.toList());

    }

    public AlumnoDtoS buscarAlumnoPorCuil(Long cuil) throws EntityNotFoundException {
        ModelMapper modelMapper = new ModelMapper();
        if (findAlumnoByCuil(cuil) != null) {
            return gestorConversionDto.converterAlumnDtoS(findAlumnoByCuil(cuil));

        } else {
            throw new EntityNotFoundException("El alumno no existe");
        }

    }



}
