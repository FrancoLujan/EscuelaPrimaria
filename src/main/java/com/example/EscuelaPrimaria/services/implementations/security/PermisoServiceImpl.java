package com.example.EscuelaPrimaria.services.implementations.security;

import com.example.EscuelaPrimaria.dtos.entrada.PermisoDtoE;
import com.example.EscuelaPrimaria.dtos.salida.PermisoDtoS;
import com.example.EscuelaPrimaria.entities.security.Permiso;
import com.example.EscuelaPrimaria.repositories.security.PermisoRepository;
import com.example.EscuelaPrimaria.services.interfaces.security.PermisoService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PermisoServiceImpl implements PermisoService<Permiso, Long> {
    private final PermisoRepository permisoRepository;

    @Override
    public void add(Permiso entity) {
        permisoRepository.save(entity);

    }

    @Override
    public void update(Permiso entity) {
        permisoRepository.save(entity);
    }

    @Override
    public void delete(Long entity) {
        permisoRepository.deleteById(entity);

    }

    @Override
    public List<Permiso> findAll() {

        return permisoRepository.findAll();
    }

    @Override
    public Permiso findById(Long id) {
        return permisoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El permiso no existe"));
    }

    @Override
    public boolean existsPermisoByNombre(String nombre) {
        return permisoRepository.existsPermisoByNombre(nombre);
    }

    @Override
    public Permiso findByNombre(String nombre) {
        return permisoRepository.findPermisoByNombre(nombre);
    }

    // No olvidar que la asignacion del permiso es en Rol
    public void agregar(PermisoDtoE permisoDtoE) throws EntityExistsException {
        if (!permisoDtoE.getPermiso().name().isEmpty() && !existsPermisoByNombre(permisoDtoE.getPermiso().name())) {
            Permiso permiso = new Permiso();
            permiso.setNombre(permisoDtoE.getPermiso().name());
            permisoRepository.save(permiso);
        } else {
            throw new EntityExistsException("el permiso ya existe");

        }

    }

    // EL porque actualizar un permiso (el nombre del permiso)
    public void actualizar(PermisoDtoE permisoDtoE) throws EntityNotFoundException {
        if (existsPermisoByNombre(permisoDtoE.getPermiso().name()) && !permisoDtoE.getPermiso().name().isEmpty()) {
            Permiso permiso = findByNombre(permisoDtoE.getPermiso().name());
            permiso.setNombre(permisoDtoE.getPermiso().name());
            permisoRepository.save(permiso);

        } else {
            throw new EntityExistsException("el permiso ya existe");
        }


    }

    public void eliminar(Long id) throws EntityNotFoundException {
        if (id > 0) {
            Permiso permiso = findById(id); // lanza error al no encontrar
            delete(permiso.getId());

        } else {
            throw new EntityNotFoundException("el id no existe");
        }


    }
    // esto deberia devolver un null si no se encuentra y un error si no se pasa bien el dto
    public PermisoDtoS buscarPorNombre(PermisoDtoE permisoDtoE)throws EntityNotFoundException {
        if(permisoDtoE.getPermiso().name().isEmpty()){
            Permiso permiso = findByNombre(permisoDtoE.getPermiso().name());
            ModelMapper model = new ModelMapper();
           return  model.map(permiso, PermisoDtoS.class);


        }
        throw new EntityNotFoundException("el permiso no existe");


    }

    public List<PermisoDtoS> todos(){
        ModelMapper model = new ModelMapper();
        return findAll().stream().map(permiso -> model.map(permiso, PermisoDtoS.class)).toList();
    }


}
