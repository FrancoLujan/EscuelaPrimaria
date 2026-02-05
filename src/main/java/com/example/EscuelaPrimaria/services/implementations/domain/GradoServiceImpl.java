package com.example.EscuelaPrimaria.services.implementations.domain;

import com.example.EscuelaPrimaria.dtos.entrada.GradoDtoE;
import com.example.EscuelaPrimaria.dtos.entrada.ProfesionalDtoE;
import com.example.EscuelaPrimaria.dtos.salida.GradoDtoS;
import com.example.EscuelaPrimaria.entities.domain.Grado;
import com.example.EscuelaPrimaria.entities.domain.Profesional;
import com.example.EscuelaPrimaria.entities.domain.Turno;
import com.example.EscuelaPrimaria.enums.TurnoEnum;
import com.example.EscuelaPrimaria.errors.MensajeErrorValidaciones;
import com.example.EscuelaPrimaria.gestores.GestorConversionDto;
import com.example.EscuelaPrimaria.repositories.domain.GradoRepository;
import com.example.EscuelaPrimaria.services.interfaces.domain.GradoService;
import com.example.EscuelaPrimaria.services.interfaces.domain.TurnoService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@AllArgsConstructor
public class GradoServiceImpl implements GradoService<Grado, Long> {


    private final GradoRepository repository;
    private final GestorConversionDto gestorConversionDto;
    private final TurnoServiceImpl turnoService;
    private final ProfesionalServiceImpl profesionalService;

    @Override
    public void add(Grado entity) {
        repository.save(entity);

    }

    @Override
    public void update(Grado entity) {
        repository.save(entity);

    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    public List<Grado> findAll() {

        return repository.findAll();
    }

    @Override
    public Grado findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no se encontro el grado :" + id));
    }


    @Override
    public boolean existsGradoByNivel(Long nivel) {
        return repository.existsGradoByNivel(nivel);
    }

    @Override
    public boolean existsGradoByTurno_Id(Long turnoId) {
        return repository.existsGradoByTurno_Id(turnoId);
    }


    @Override
    public Grado findByGradoByNivel(Long nivel, String turno) {
        return repository.findGradoBynivel_AndTurno_Nombre(nivel, turno);
    }


    @Override
    public List<Grado> findGradoByNivel(Long nivel) {
        return repository.findGradoByNivel(nivel);
    }


    public void agregarGrado(@Valid GradoDtoE gradoDtoE) throws EntityExistsException {
        if(!existenciaGrado(gradoDtoE)){
            Grado grado = new Grado();
            grado.setNivel(gradoDtoE.getNivel());
            grado.setTurno(turnoService.findByNombre(gradoDtoE.getTurno().name()));
            add(grado);

        }
        else {
            throw new EntityExistsException("El grado ya existe en el sistema");
        }


    }

    // se hace de manera inmediata para asignar profe
    public void actualizarGrado(@Valid GradoDtoE gradoDtoE,
                               Long cuil) throws EntityNotFoundException {

        if(existenciaGrado(gradoDtoE)){
            Grado grado = findByGradoByNivel(gradoDtoE.getNivel(), gradoDtoE.getTurno().name());
            Profesional profesional =  profesionalService.findProfesionalByCuil(cuil).getFirst();
            profesional.setGrado(grado);
            grado.setProfesor(profesional);
            update(grado);


        }
        else {
            throw new EntityNotFoundException("El grado no existe en el sistema");
        }


    }

    public void eliminarGrado(@Valid GradoDtoE grado) throws EntityNotFoundException {
        if(existenciaGrado(grado)){
            delete(findByGradoByNivel(grado.getNivel(), grado.getTurno().name()).getId());
        }
        else {
            throw new EntityNotFoundException("El grado no existe en el sistema");
        }


    }

    public List<GradoDtoS> todos() {
        List<Grado> grados = findAll();
        return grados.stream().map(gestorConversionDto::converterGradoDtoS).toList();
    }

    public List<GradoDtoS> buscarGradoPorNivel(@Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO)
                                              @Max(value = 6, message = MensajeErrorValidaciones.MENSAJE_NUMERO) Long nivel) throws EntityNotFoundException {
        List<Grado> grados = findGradoByNivel(nivel);
        return grados.stream().map(gestorConversionDto::converterGradoDtoS).toList();

    }

   private boolean existenciaGrado(GradoDtoE gradoDtoE) {
        if(existsGradoByNivel(gradoDtoE.getNivel())) {
            if(findByGradoByNivel(gradoDtoE.getNivel(), gradoDtoE.getTurno().name()) != null) {
                return true;

            }

        }
        return false;
   }



}
