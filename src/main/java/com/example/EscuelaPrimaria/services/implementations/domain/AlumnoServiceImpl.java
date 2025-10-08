package com.example.EscuelaPrimaria.services.implementations.domain;

import com.example.EscuelaPrimaria.dtos.entrada.AlumnoDtoE;
import com.example.EscuelaPrimaria.dtos.salida.AlumnoDtoS;
import com.example.EscuelaPrimaria.entities.domain.Alumno;
import com.example.EscuelaPrimaria.entities.security.Usuario;
import com.example.EscuelaPrimaria.errors.MensajeErrorValidaciones;
import com.example.EscuelaPrimaria.gestores.GestorConversionDto;

import com.example.EscuelaPrimaria.repositories.domain.AlumnoRepository;
import com.example.EscuelaPrimaria.services.interfaces.domain.AlumnoService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
@AllArgsConstructor


public class AlumnoServiceImpl implements AlumnoService<Alumno, Long> {
    private AlumnoRepository repository;
    private GestorConversionDto gestorConversionDto;

    @Override
    public void add(Alumno entity) {
        repository.save(entity);

    }

    @Override
    public void update(Alumno entity) {
        repository.save(entity);

    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    public List<Alumno> findAll() {
        return repository.findAll();
    }

    @Override
    public Alumno findById(Long id) {

        return repository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Alumno no existe"));
    }

    @Override
    public Alumno findAlumnoByCuil(Long cuil) {

        return repository.findAlumnoByCuil(cuil);
    }

    @Override
    public boolean existsAlumnoByCuil(Long cuil) {
        return repository.existsAlumnoByCuil(cuil);
    }

    @Override
    // damos por hecho que existira siempre porque este metodo se usara con el actualiar que se hace luego de la creacion
    // de usuario
    public Alumno findAlumnoByUsuario_Id(Long idUsuario) {
        return  repository.findAlumnoByUsuario_Id(idUsuario);
    }

    // AL CREAR VACIO, PUEDO CREARLO UNA VEZ CREADO Y EL USUARIO LUEGO SETEO
    public void crearAlumnoVacio(Usuario usuario) throws EntityExistsException {
        Alumno alumno = new Alumno();
        alumno.setUsuario(usuario);
        if(!existsAlumnoByCuil(alumno.getCuil())) {

            add(alumno);
        }else {
            throw new EntityExistsException("Alumno ya existe");
        }
    }
    // mediante el ID esta actualizacion debe realizarce de inmediato se crea el usuario...
    public void actualizar(@NotNull @Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO) Long idUsuario ,
                           @Valid AlumnoDtoE alumno) throws EntityNotFoundException {

            Alumno alumnoE = findAlumnoByUsuario_Id(idUsuario);
            alumnoE.setCuil(alumno.getCuil());
            alumnoE.setNombre(alumno.getNombre());
            alumnoE.setApellido(alumno.getApellido());
            alumnoE.setFechaNacimiento(alumno.getFechaNacimiento());

            update(alumnoE);


    }


// ESTE ACTULIZAR TENES QUE MODIFICAR ESTE ES EL NORMAL
    public void actualizarExistente(@NotNull @Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO) Long cuil ,
                           @Valid AlumnoDtoE alumno) throws EntityNotFoundException {

        if (existsAlumnoByCuil(cuil)) {
            Alumno alumnoE = findAlumnoByCuil(cuil);
            alumnoE.setCuil(alumno.getCuil());
            alumnoE.setNombre(alumno.getNombre());
            alumnoE.setApellido(alumno.getApellido());
            alumnoE.setFechaNacimiento(alumno.getFechaNacimiento());

            update(alumnoE);
        } else {
            throw new EntityNotFoundException("El alumno no existe");
        }

    }

    public void eliminar(@NotNull @Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO) Long cuil) throws EntityNotFoundException {
        if (existsAlumnoByCuil(cuil)) {
            delete(cuil);
        } else {
            throw new EntityNotFoundException("El alumno no existe");
        }

    }

    public List<AlumnoDtoS> todos() {
        List<Alumno> alumnos = findAll();
        return alumnos.stream().map(e -> gestorConversionDto.converterAlumnDtoS(e))
                .collect(Collectors.toList());

    }

    public AlumnoDtoS buscarAlumnoPorCuil(@NotNull @Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO) Long cuil) throws EntityNotFoundException {
        if (existsAlumnoByCuil(cuil)) {
            return gestorConversionDto.converterAlumnDtoS(findAlumnoByCuil(cuil));

        } else {
            throw new EntityNotFoundException("El alumno no existe");
        }

    }




}
