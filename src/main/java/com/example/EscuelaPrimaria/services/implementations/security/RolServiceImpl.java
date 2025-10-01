package com.example.EscuelaPrimaria.services.implementations.security;

import com.example.EscuelaPrimaria.dtos.entrada.PermisoDtoE;
import com.example.EscuelaPrimaria.dtos.entrada.RolDtoE;
import com.example.EscuelaPrimaria.dtos.salida.PermisoDtoS;
import com.example.EscuelaPrimaria.dtos.salida.RolDtoS;
import com.example.EscuelaPrimaria.entities.security.Permiso;
import com.example.EscuelaPrimaria.entities.security.Rol;
import com.example.EscuelaPrimaria.gestores.GestorConversionDto;
import com.example.EscuelaPrimaria.repositories.security.RolRepository;
import com.example.EscuelaPrimaria.services.interfaces.security.RolService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RolServiceImpl implements RolService<Rol, Long> {
    private final RolRepository rolRepository;
    private final PermisoServiceImpl permisoServiceImpl;
    private final GestorConversionDto conversor;

    @Override
    public void add(Rol entity) {
        rolRepository.save(entity);
    }

    @Override
    public void update(Rol entity) {
        rolRepository.save(entity);

    }

    @Override
    public void delete(Long id) {
        rolRepository.deleteById(id);

    }

    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public Rol findById(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));
    }


    @Override
    public boolean existsRolByNombre(String nombre) {
        return rolRepository.existsRolByNombre(nombre);
    }

    public void agregarRol(RolDtoE rolDtoE) throws EntityExistsException {

        if (!rolDtoE.getRol().name().isEmpty() && !existsRolByNombre(rolDtoE.getRol().name().toUpperCase())) {
            Rol rol = new Rol();
            rol.setNombre(rolDtoE.getRol().name());
            add(rol);

        } else {
            throw new EntityExistsException("El rol ya existe");
        }

    }

    public void eliminar(Long id)throws EntityNotFoundException {

        if(existsRolByNombre(findById(id).getNombre())){
            delete(id);
        }else{
            throw new EntityNotFoundException("El rol no existe");
        }
    }
    public void actualizar(RolDtoE rolDtoE, Long id) throws EntityNotFoundException {
        if(existsRolByNombre(rolDtoE.getRol().name())){
            Rol rol = findById(id);
            rol.setNombre(rolDtoE.getRol().name());
            update(rol);

        }
    }
    public List<RolDtoS> todos(){

        List<Rol> roles = rolRepository.findAll();

        return  roles.stream().map(conversor::converterRolDtoS).toList();

    }

    public RolDtoS buscarRol(Long id)throws EntityNotFoundException {
        Rol rol = findById(id);
        return conversor.converterRolDtoS(rol);
    }


    public void asociarPermisos(Long idRol, Long idPermiso) throws EntityNotFoundException {
       Rol rol =  findById(idRol);
       Permiso permiso = permisoServiceImpl.findById(idPermiso);
       rol.getPermisos().add(permiso);
       update(rol);
       

    }

}
