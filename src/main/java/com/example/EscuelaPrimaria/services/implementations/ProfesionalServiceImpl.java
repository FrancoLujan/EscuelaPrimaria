package com.example.EscuelaPrimaria.services.implementations;

import com.example.EscuelaPrimaria.dtos.entrada.ProfesionalDtoE;
import com.example.EscuelaPrimaria.dtos.salida.ProfesionalDtoS;
import com.example.EscuelaPrimaria.entities.Profesional;
import com.example.EscuelaPrimaria.gestores.GestorConversionDto;
import com.example.EscuelaPrimaria.gestores.GestorRepo;
import com.example.EscuelaPrimaria.services.interfaces.ProfesionalService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfesionalServiceImpl implements ProfesionalService<Profesional, Long> {
    private final GestorRepo gestorRepo;
    private final GestorConversionDto dto;
    @Override
    public void add(Profesional entity) {
        gestorRepo.getProfesionalRepository().save(entity);

    }

    @Override
    public void update(Profesional entity) {
        gestorRepo.getProfesionalRepository().save(entity);

    }

    @Override
    public void delete(Long id) {
        gestorRepo.getProfesionalRepository().deleteById(id);
    }

    @Override
    public List<Profesional> findAll() {
        return gestorRepo.getProfesionalRepository().findAll();
    }

    @Override
    public Profesional findById(Long id) {
        return gestorRepo.getProfesionalRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profesional no encontrado"));
    }

    public void agregar(ProfesionalDtoE profesionalDtoE) throws EntityExistsException {
        if (profesionalDtoE != null &&
                (profesionalDtoE.getCuil() != null &&
                        profesionalDtoE.getCuil() != findById(profesionalDtoE.getCuil()).getCuil())
        ) {
            ModelMapper modelMapper = new ModelMapper();
            Profesional profesional = modelMapper.map(profesionalDtoE, Profesional.class);
            add(profesional);
        } else {
            throw new EntityExistsException("el profesional ya existe");
        }

    }

    public void eliminar(Long cuil) throws EntityNotFoundException {
        if (findById(cuil) != null) {
            delete(cuil);
        }
        throw new EntityNotFoundException("el profesional no existe");

    }

    public void actualizar(ProfesionalDtoE profesionalDtoE) throws EntityNotFoundException {
        if (findById(profesionalDtoE.getCuil()) != null) {
            Profesional profesional = findById(profesionalDtoE.getCuil());
            profesional.setCuil(profesionalDtoE.getCuil());
            profesional.setNombre(profesionalDtoE.getNombre());
            profesional.setApellido(profesionalDtoE.getApellido());
            update(profesional);
        } else
            throw new EntityNotFoundException("el profesional no existe");
    }

    public List<ProfesionalDtoS> todos() {
        return findAll().stream()
                .map(dto::converterProfesionalDtoS)
                .collect(Collectors.toList());
    }

    public ProfesionalDtoS buscarPorCuil(Long cuil)throws EntityNotFoundException {
        if(findById(cuil) != null){
            return dto.converterProfesionalDtoS(findById(cuil));
        }else
            throw new EntityNotFoundException("el profesional no existe");

    }


}
