package com.example.EscuelaPrimaria.services.implementations;

import com.example.EscuelaPrimaria.dtos.entrada.GradoDtoE;
import com.example.EscuelaPrimaria.dtos.salida.GradoDtoS;
import com.example.EscuelaPrimaria.entities.Grado;
import com.example.EscuelaPrimaria.enums.NivelEnum;
import com.example.EscuelaPrimaria.gestores.GestorRepo;
import com.example.EscuelaPrimaria.services.interfaces.GradoService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class GradoServiceImpl implements GradoService<Grado, Long> {


    private final GestorRepo gestor;

    @Override
    public void add(Grado entity) {
        gestor.getGradoRepository().save(entity);

    }

    @Override
    public void update(Grado entity) {
        gestor.getGradoRepository().save(entity);

    }

    @Override
    public void delete(Grado entity) {
        gestor.getGradoRepository().deleteById(entity.getId());

    }

    @Override
    public List<Grado> findAll() {
        return gestor.getGradoRepository().findAll();
    }

    @Override
    public Grado findById(Long id) {
        return gestor.getGradoRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no se encontro grado con el id:" + id));
    }

    @Override
    public boolean findGradoByNivelEqualsIgnoreCase(int nivel) {
        return gestor.getGradoRepository().findGradoByNivelEqualsIgnoreCase(nivel);
    }

    @Override
    public boolean findGradoByTurnoEqualsIgnoreCase(String turno) {
        return gestor.getGradoRepository().findGradoByTurnoEqualsIgnoreCase(turno);
    }

    @Override
    public List<Grado> findGradoByNivel(int nivel) {
        return gestor.getGradoRepository().findGradoByNivel(nivel);
    }


    public void agregarGrado(GradoDtoE grado) {

        if (!existenciaGrado(grado)) {
            ModelMapper modelMapper = new ModelMapper();
            Grado gradoEntity = modelMapper.map(grado, Grado.class);
            add(gradoEntity);
        } else {
            throw new EntityExistsException("El grado existe en el sistema");
        }

    }

    public void actualizarGrado(GradoDtoE grado) {

        if (existenciaGrado(grado)) {
            ModelMapper modelMapper = new ModelMapper();
            Grado gradoEntity = modelMapper.map(grado, Grado.class);
            update(gradoEntity);
        } else {
            throw new EntityNotFoundException("El grado no existe en el sistema");
        }

    }

    public void eliminarGrado(GradoDtoE grado) {
        if (existenciaGrado(grado)) {
            ModelMapper modelMapper = new ModelMapper();
            Grado gradoEntity = modelMapper.map(grado, Grado.class);
            delete(gradoEntity);

        } else {
            throw new EntityNotFoundException("El grado no se puede eliminar porque no existe");
        }


    }

    public List<GradoDtoS> todos() {
        ModelMapper modelMapper = new ModelMapper();
        List<Grado> grados = gestor.getGradoRepository().findAll();
        return grados.stream().map(grado -> modelMapper.map(grado, GradoDtoS.class)).toList();
    }

    public List<GradoDtoS> buscarGradoPorNivel(int nivel) {
        ModelMapper modelMapper = new ModelMapper();
        List<Grado> grados = findGradoByNivel(nivel);
        return grados.stream().map(grado -> modelMapper.map(grado, GradoDtoS.class)).toList();


    }

    private boolean existenciaGrado(GradoDtoE grado) {
        //            throw new EntityExistsException("El grado existe en el sistema");
        return findGradoByNivelEqualsIgnoreCase(grado.getNivel().getValor())
                && findGradoByTurnoEqualsIgnoreCase(grado.getTurno().getNombre().name());
    }


}
