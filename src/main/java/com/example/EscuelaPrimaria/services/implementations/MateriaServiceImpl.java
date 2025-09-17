package com.example.EscuelaPrimaria.services.implementations;

import com.example.EscuelaPrimaria.dtos.entrada.MateriaDtoE;
import com.example.EscuelaPrimaria.dtos.salida.MateriaDtoS;
import com.example.EscuelaPrimaria.entities.Materia;
import com.example.EscuelaPrimaria.gestores.GestorConversionDto;
import com.example.EscuelaPrimaria.gestores.GestorRepo;
import com.example.EscuelaPrimaria.repositories.MateriaRepository;
import com.example.EscuelaPrimaria.services.interfaces.MateriaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MateriaServiceImpl implements MateriaService<Materia, Long> {
    private final GestorRepo gestorRepo;


    @Override
    public void add(Materia entity) {
        gestorRepo.getMateriaRepository().save(entity);
    }

    @Override
    public void update(Materia entity) {
        gestorRepo.getMateriaRepository().save(entity);

    }

    @Override
    public void delete(Long id) {
        gestorRepo.getMateriaRepository().deleteById(id);

    }

    @Override
    public List<Materia> findAll() {
        return gestorRepo.getMateriaRepository().findAll();
    }

    @Override
    public Materia findById(Long id) {
        return gestorRepo.getMateriaRepository().findById(id).
                orElseThrow(() -> new EntityNotFoundException("No se encontro Materia"));
    }
    @Override
    public Materia findMateriaByNombre(String nombre) {
        return null;
    }

    @Override
    // aplicaste la logica aca y no en un metodo privado porque no es tan complejo
    public boolean findExistsMateriaByNombre(String nombre) {
        if(nombre != null && !nombre.isEmpty()){
            return gestorRepo.getMateriaRepository().findMateriaByNombreEqualsIgnoreCase(nombre);
        }
        return false;

    }

    public void agregarMateria(MateriaDtoE materiaDto) throws IllegalArgumentException {
        if(materiaDto != null && !findExistsMateriaByNombre(materiaDto.getNombre())) {
            ModelMapper modelMapper = new ModelMapper();
            Materia materia = modelMapper.map(materiaDto, Materia.class);
            gestorRepo.getMateriaRepository().save(materia);
        }
        throw new IllegalArgumentException("Argumentos vacios o duplicado");

    }

    public void eliminarMateria(MateriaDtoE materiaDto) throws IllegalArgumentException {
        if(materiaDto != null) {
            delete(getMateriaByNombre(materiaDto.getNombre()).getId());
        }
        throw new IllegalArgumentException("No se puede eliminar la materia");

    }
    public void actualizarMateria(MateriaDtoE materiaDto) throws Exception {
        if(materiaDto != null && findExistsMateriaByNombre(materiaDto.getNombre())) {
            Materia materia = new Materia();
            materia.setNombre(materiaDto.getNombre());
            update(materia);
        }
        throw new Exception("No se puede actualizar la materia");
    }

    //Se usa MateriaDtoE porque solo tiene el atributo nombre
    public List<MateriaDtoE> findAllMateriasPorGrado() {
        ModelMapper modelMapper = new ModelMapper();
        List<Materia> materias = gestorRepo.getMateriaRepository().findAll();
        return materias.stream().map(e -> modelMapper.map(e, MateriaDtoE.class)).toList();
    }

    private Materia getMateriaByNombre(String nombre)throws EntityNotFoundException{
        if(nombre != null && findExistsMateriaByNombre(nombre)) {
            return findMateriaByNombre(nombre);
        }
        throw new EntityNotFoundException("No se encontro la materia: " + nombre);

    }


}
