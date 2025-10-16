package com.example.EscuelaPrimaria.services.implementations.domain;

import com.example.EscuelaPrimaria.dtos.salida.TurnoDtoS;
import com.example.EscuelaPrimaria.entities.domain.Turno;
import com.example.EscuelaPrimaria.enums.TurnoEnum;
import com.example.EscuelaPrimaria.errors.MensajeErrorValidaciones;
import com.example.EscuelaPrimaria.repositories.domain.TurnoRepository;
import com.example.EscuelaPrimaria.services.interfaces.domain.TurnoService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TurnoServiceImpl implements TurnoService<Turno, Long> {
    private final TurnoRepository repo;

    @Override
    public void add(Turno entity) {
        repo.save(entity);

    }

    @Override
    public void update(Turno entity) {
        repo.save(entity);

    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);

    }

    @Override
    public List<Turno> findAll() {
        return repo.findAll();
    }

    @Override
    public Turno findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turno no encontrado"));
    }

    @Override
    public Turno findByNombre(String nombre) {
        return repo.findByNombre(nombre);
    }


    //SOLO SE AGREGA EL NOMBRE DEL TURNO
    public void agregar(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String turno) throws EntityExistsException, IllegalArgumentException {
        if(!EnumUtils.isValidEnum(TurnoEnum.class, turno)) throw new IllegalArgumentException("Formato no valido"); // no es la mejor manera
        if(findByNombre(turno) == null) {
            Turno turnoNuevo = new Turno();

            turnoNuevo.setNombre(turno);
            asignarHorarios(turnoNuevo);
            add(turnoNuevo);

        }else {
            throw new EntityExistsException("El turno existe");
        }



    }
    // Raro que se actualize
    // ACTUALIZA EL NOMBRE Y A LA VEZ BUSCA POR NOMBRE
    public void actualizar(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String turnoViejo,
                           @NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String turnoNuevo) throws EntityNotFoundException {
        Turno turno = findByNombre(turnoViejo);
        if (turno != null && !turnoViejo.equals(turnoNuevo)) {
            turno.setNombre(turnoNuevo);
            asignarHorarios(turno);
            update(turno);


        } else {
            throw new EntityNotFoundException("El turno no existe");
        }

    }




    public void eliminar(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String turno) throws EntityNotFoundException {

        if (findByNombre(turno) != null) {
            Turno turnoEliminado = findByNombre(turno);
            delete(turnoEliminado.getId());


        } else {
            throw new EntityNotFoundException("El turno no existe");

        }

    }
    public TurnoDtoS buscarPorNombre(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String nombre) throws EntityNotFoundException {
        if (findByNombre(nombre) != null) {
            ModelMapper modelMapper = new ModelMapper();
            Turno turno = findByNombre(nombre);
            return modelMapper.map(turno, TurnoDtoS.class);
        }else {
            throw new EntityNotFoundException("El turno no existe");
        }
    }
    public List<TurnoDtoS>todos(){
        ModelMapper modelMapper = new ModelMapper();
        List<Turno> turnos = repo.findAll();
        return turnos.stream().map(t -> modelMapper.map(t, TurnoDtoS.class)).toList();
    }
    // asigna el horario segun el nombre
    private void asignarHorarios(Turno turno) {
        if (TurnoEnum.MANANA.name().equals(turno.getNombre())) {
            turno.setHoraInicio(LocalTime.of(8, 0));
            turno.setHoraFin(LocalTime.of(13, 0));
        } else if (TurnoEnum.TARDE.name().equals(turno.getNombre())) {
            turno.setHoraInicio(LocalTime.of(13, 10));
            turno.setHoraFin(LocalTime.of(17, 10));
        }
    }


}
