package com.example.EscuelaPrimaria.services.implementations.domain;

import com.example.EscuelaPrimaria.dtos.entrada.MateriaDtoE;
import com.example.EscuelaPrimaria.dtos.salida.MateriaDtoS;
import com.example.EscuelaPrimaria.entities.domain.Grado;
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

    @Override
    public List<Materia> findMateriaByGrado_Id(Long id) {
        return repo.findMateriaByGrado_Id(id);
    }

    @Override
    public List<Materia> findMateriaByGrado_Nivel(Long nivel) {
        return repo.findMateriaByGrado_Nivel(nivel);
    }

    public void agregarMateria(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String materiaNueva) throws EntityExistsException {

        if (findMateriaByNombre(materiaNueva) == null) {
            Materia materia = new Materia();
            materia.setNombre(materiaNueva);
            add(materia);
        } else {
            throw new EntityExistsException("La entidad ya existe ");
        }

    }

    public void eliminarMateria(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String materia) throws EntityNotFoundException {
        if (findMateriaByNombre(materia) != null) {
            delete(findMateriaByNombre(materia).getId());
        } else {
            throw new EntityNotFoundException("No existe la materia");
        }


    }

    public void actualizarMateria(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String materiaActualizar,
                                  @NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String materiaNueva  ) throws EntityNotFoundException {
        if (findMateriaByNombre(materiaActualizar) != null) {
            Materia materia = findMateriaByNombre(materiaActualizar);
            materia.setNombre(materiaNueva);
            update(materia);
        } else {
            throw new EntityNotFoundException("No se puede actualizar la materia");
        }

    }

    // metodos para ver nombres de materias Casos especificos
    public List<String> todos() throws EntityNotFoundException {
        List<Materia> materias = repo.findAll();
        if (materias.isEmpty()) {
            throw new EntityNotFoundException("No existe las materias");
        }
        return materias.stream().map(Materia::getNombre).toList();
    }

    public String buscarMateriaPorNombre(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String nombre) throws EntityNotFoundException {
        Materia materia = repo.findMateriaByNombre(nombre);
        if (materia == null) {
            throw new EntityNotFoundException("No existe la materia");
        }
        return materia.getNombre();
    }
    public List<String> todos(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NUMERO) Long nivel)throws  EntityNotFoundException{
        List<Materia> materias = repo.findMateriaByGrado_Nivel(nivel);

        if (materias.isEmpty()) {
            throw new EntityNotFoundException("No existen materias en ese grado");
        }
        return materias.stream().map(Materia::getNombre).toList();

    }

    /**
     la asignacion de notas se hara en un servicio aparte para evitar exceso  la clase es VidaEstudiantil
     */





}
