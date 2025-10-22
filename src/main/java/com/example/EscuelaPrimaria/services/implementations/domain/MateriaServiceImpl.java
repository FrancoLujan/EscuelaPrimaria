package com.example.EscuelaPrimaria.services.implementations.domain;

import com.example.EscuelaPrimaria.dtos.entrada.MateriaDtoE;
import com.example.EscuelaPrimaria.entities.domain.Materia;

import com.example.EscuelaPrimaria.errors.MensajeErrorValidaciones;
import com.example.EscuelaPrimaria.repositories.domain.MateriaRepository;
import com.example.EscuelaPrimaria.services.interfaces.domain.MateriaService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MateriaServiceImpl implements MateriaService<Materia, Long> {
    private final MateriaRepository repo;


    @Override
    public void add(Materia entity) {
        repo.save(entity);
    }

    @Override
    public void update(Materia entity) {
        repo.save(entity);

    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);

    }

    @Override
    public List<Materia> findAll() {
        return repo.findAll();
    }

    @Override
    public Materia findById(Long id) {
        return repo.findById(id).
                orElseThrow(() -> new EntityNotFoundException("No se encontro Materia"));
    }

    @Override
    public Materia findMateriaByNombre(String nombre) {

        return repo.findMateriaByNombre(nombre);
    }

    @Override
    // aplicaste la logica aca y no en un metodo privado porque no es tan complejo
    public boolean findExistsMateriaByNombre(String nombre) {
            return repo.findMateriaByNombreEqualsIgnoreCase(nombre);
        }




    public void agregarMateria(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String materiaNueva) throws EntityExistsException {

        if (findExistsMateriaByNombre(materiaNueva)) {
            Materia materia = new Materia();
            materia.setNombre(materiaNueva);
            add(materia);
        }
        else {
            throw new EntityExistsException("La entidad ya existe ");
        }

    }

    public void eliminarMateria(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String materia) throws EntityNotFoundException {
        if (findExistsMateriaByNombre(materia)) {
            delete(findMateriaByNombre(materia).getId());
        }
        else {
            throw new EntityNotFoundException("No existe la materia");
        }


    }

    public void actualizarMateria(String materiaActualizar) throws EntityNotFoundException {
        if (findExistsMateriaByNombre(materiaActualizar)) {
            Materia materia = new Materia();
            materia.setNombre(materiaActualizar);
            update(materia);
        }
        else{
            throw new EntityNotFoundException("No se puede actualizar la materia");
        }

    }

    public List<String> todos() {
        List<Materia> materias = repo.findAll();
        return materias.stream().map(Materia::getNombre).toList();
    }



}
