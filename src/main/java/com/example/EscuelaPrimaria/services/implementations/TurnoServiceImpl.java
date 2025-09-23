package com.example.EscuelaPrimaria.services.implementations;

import com.example.EscuelaPrimaria.dtos.entrada.TurnoDtoE;
import com.example.EscuelaPrimaria.dtos.salida.TurnoDtoS;
import com.example.EscuelaPrimaria.entities.Turno;
import com.example.EscuelaPrimaria.enums.TurnoEnum;
import com.example.EscuelaPrimaria.gestores.GestorRepo;
import com.example.EscuelaPrimaria.services.interfaces.TurnoService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TurnoServiceImpl implements TurnoService<Turno, Long> {
    private final GestorRepo gestorRepo;

    @Override
    public void add(Turno entity) {
        gestorRepo.getTurnoRepository().save(entity);

    }

    @Override
    public void update(Turno entity) {
        gestorRepo.getTurnoRepository().save(entity);

    }

    @Override
    public void delete(Long id) {
        gestorRepo.getTurnoRepository().deleteById(id);

    }

    @Override
    public List<Turno> findAll() {
        return gestorRepo.getTurnoRepository().findAll();
    }

    @Override
    public Turno findById(Long id) {
        return gestorRepo.getTurnoRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turno no encontrado"));
    }

    @Override
    public TreeSet<Turno> findByNombre(String nombre) {
        return gestorRepo.getTurnoRepository().findByNombre(nombre);
    }


    //SOLO SE AGREGA EL NOMBRE DEL TURNO LO QUE ES LOS HORARIOS EN UN METODO APARTE
    public void agregar(TurnoDtoE turnoE) throws EntityExistsException {
        if (!turnoE.getNombre().name().isEmpty() &&
                findByNombre(turnoE.getNombre().name()).isEmpty()) {
            Turno turno = new Turno();
            turno.setNombre(turnoE.getNombre().name());
            asignarHorarios(turnoE.getNombre(), turno);
            add(turno);
        } else {
            throw new EntityExistsException("El turno ya existe");
        }

    }

    public void actualizar(TurnoDtoE turnoE) throws EntityNotFoundException {
        TreeSet<Turno> turno = findByNombre(turnoE.getNombre().name());
        if (!turnoE.getNombre().name().isEmpty() && !turno.isEmpty()) {
            Turno turnoAc = turno.stream().toList().getFirst();
            turnoAc.setNombre(turnoE.getNombre().name());
            asignarHorarios(turnoE.getNombre(), turnoAc);
            update(turnoAc);


        } else {
            throw new EntityNotFoundException("El turno no existe");
        }

    }




    public void eliminar(TurnoDtoE turnoE) throws EntityNotFoundException {
        TreeSet<Turno> turno = findByNombre(turnoE.getNombre().name());
        if (!turno.isEmpty()) {
            delete(turno.stream().findFirst().get().getId());


        } else {
            throw new EntityNotFoundException("El turno no existe");

        }

    }
    public TurnoDtoS turnoPorNombre(String nombre) {
        if(!nombre.isEmpty()) {
            TreeSet<Turno> turno = findByNombre(nombre.toUpperCase());
            ModelMapper model = new ModelMapper();
            return model.map(turno, TurnoDtoS.class);


        }else {
            return null;
        }

    }
    private void asignarHorarios(TurnoEnum t, Turno turno) {
        if (t.equals(TurnoEnum.MANANA)) {
            turno.setHoraInicio(LocalTime.of(8, 0));
            turno.setHoraFin(LocalTime.of(13, 0));
        } else if (t.equals(TurnoEnum.TARDE)) {
            turno.setHoraInicio(LocalTime.of(13, 10));
            turno.setHoraFin(LocalTime.of(17, 10));
        }
    }


}
