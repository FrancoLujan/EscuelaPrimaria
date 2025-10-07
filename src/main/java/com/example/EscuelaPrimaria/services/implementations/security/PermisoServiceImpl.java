package com.example.EscuelaPrimaria.services.implementations.security;

import com.example.EscuelaPrimaria.dtos.salida.PermisoDtoS;
import com.example.EscuelaPrimaria.entities.security.Permiso;
import com.example.EscuelaPrimaria.enums.PermisoEnum;
import com.example.EscuelaPrimaria.errors.MensajeErrorValidaciones;
import com.example.EscuelaPrimaria.gestores.GestorConversionDto;
import com.example.EscuelaPrimaria.repositories.security.PermisoRepository;
import com.example.EscuelaPrimaria.services.interfaces.security.PermisoService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@AllArgsConstructor
@Validated
public class PermisoServiceImpl implements PermisoService<Permiso, Long> {
    private final PermisoRepository permisoRepository;
    private GestorConversionDto conversionDto;
    // PARA EVITAR TANTA COMPLICACION SE IRA EL DTO DE ENTRADA

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


    // RECORDAR EnumUtils.isValidEnum(PermisoEnum.class, CLASE PARA VALIDAR QUE ENUM)
    // No olvidar que la asignacion del permiso es en Rol
    public void agregar(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String permisoE) throws EntityExistsException, HttpMessageNotReadableException {
        if (!existsPermisoByNombre(permisoE)) {
            if (EnumUtils.isValidEnum(PermisoEnum.class, permisoE)) {
                Permiso permiso = new Permiso();
                permiso.setNombre(permisoE);
                add(permiso);
            } else {
                throw new IllegalArgumentException("Formato to valido");
            }

        } else {
            throw new EntityExistsException("el permiso ya existe");

        }

    }

    // EL porque actualizar un permiso (el nombre del permiso)
    public void actualizar(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String viejoPermiso,
                          @NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String permisoNuevo) throws EntityNotFoundException , IllegalArgumentException{

        if (existsPermisoByNombre(viejoPermiso.toUpperCase()) && !permisoNuevo.equals(viejoPermiso.toUpperCase())) {
            if (EnumUtils.isValidEnum(PermisoEnum.class, permisoNuevo.toUpperCase())) {
                Permiso permiso = findByNombre(viejoPermiso.toUpperCase());
                permiso.setNombre(permisoNuevo.toUpperCase());
                update(permiso);
            }else{
                throw new IllegalArgumentException("Formato to valido");
            }



        } else {
            throw new EntityExistsException("el permiso no existe");
        }


    }

    public void eliminar(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String nombre) throws EntityNotFoundException {
        if (existsPermisoByNombre(nombre)) {
            Permiso permiso = findByNombre(nombre); // lanza error al no encontrar
            delete(permiso.getId());
        } else {
            throw new EntityNotFoundException("el permiso no existe");
        }


    }

    // CUIDADO CON ESTO , AL SER SIMPLES LOS PERMISOS BUSCO POR NOMBRE Y NO POR ID
    // ES UNA DEUDA TECNICA QUE LUEGO CAMBIARIAS EN DTOS AGREGANDO ID...
    public PermisoDtoS buscarPorNombre(@NotNull @NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE)  String permisoE) throws EntityNotFoundException {
        if (existsPermisoByNombre(permisoE.toUpperCase())) {
            Permiso permiso = findByNombre(permisoE.toUpperCase());

            return conversionDto.converterPermisoDtoS(permiso);

        }
        throw new EntityNotFoundException("el permiso no existe");


    }

    public List<PermisoDtoS> todos() {
        return findAll().stream().map(conversionDto::converterPermisoDtoS).toList();
    }


}
