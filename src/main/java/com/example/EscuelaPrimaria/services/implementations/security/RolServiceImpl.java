package com.example.EscuelaPrimaria.services.implementations.security;

import com.example.EscuelaPrimaria.dtos.entrada.RolDtoE;
import com.example.EscuelaPrimaria.dtos.salida.RolDtoS;
import com.example.EscuelaPrimaria.entities.security.Permiso;
import com.example.EscuelaPrimaria.entities.security.Rol;
import com.example.EscuelaPrimaria.enums.RolEnum;
import com.example.EscuelaPrimaria.errors.MensajeErrorValidaciones;
import com.example.EscuelaPrimaria.gestores.GestorConversionDto;
import com.example.EscuelaPrimaria.repositories.security.RolRepository;
import com.example.EscuelaPrimaria.services.interfaces.security.RolService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@AllArgsConstructor
@Validated
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

    public void agregarRol(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE)
                           String rolE) throws EntityExistsException, IllegalArgumentException {

        if (!existsRolByNombre(rolE.toUpperCase())) {

            if (EnumUtils.isValidEnum(RolEnum.class, rolE.toUpperCase())) {
                Rol rol = new Rol();
                rol.setNombre(rolE.toUpperCase());
                add(rol);

            } else {
                throw new IllegalArgumentException("Formato no valido");
            }


        } else {
            throw new EntityExistsException("El rol ya existe");
        }

    }

    public void eliminar(@Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_ID) Long id) throws EntityNotFoundException {

        if (existsRolByNombre(findById(id).getNombre())) {
            delete(id);
        } else {
            throw new EntityNotFoundException("El rol no existe");
        }
    }

    public void actualizar(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE)  String rolE,
                           @Min(value = 1) Long idRol) throws EntityNotFoundException, IllegalArgumentException {
        if (existsRolByNombre(rolE.toUpperCase())) {
            if (EnumUtils.isValidEnum(RolEnum.class, rolE.toUpperCase())) {
                Rol rol = findById(idRol);
                rol.setNombre(rolE.toUpperCase());
                update(rol);

            } else {
                throw new IllegalArgumentException("Formato no valido");
            }

        } else {
            throw new EntityNotFoundException("El rol no existe");
        }
    }

    public List<RolDtoS> todos() {

        List<Rol> roles = rolRepository.findAll();

        return roles.stream().map(conversor::converterRolDtoS).toList();

    }

    public RolDtoS buscarRol(@Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_ID) Long id) throws EntityNotFoundException {
        Rol rol = findById(id);
        return conversor.converterRolDtoS(rol);
    }


    public void asociarPermisos(@Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_ID) Long idRol,
                                @Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_ID) Long idPermiso) throws EntityNotFoundException {
        Rol rol = findById(idRol);
        Permiso permiso = permisoServiceImpl.findById(idPermiso);
        if (rol.getPermisos().contains(permiso)) {
            throw new EntityExistsException("El rol ya tiene el permiso");
        }
        rol.getPermisos().add(permiso);
        update(rol);


    }

}
