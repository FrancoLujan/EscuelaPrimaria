package com.example.EscuelaPrimaria.services.implementations.domain;

import com.example.EscuelaPrimaria.dtos.entrada.ProfesionalDtoE;
import com.example.EscuelaPrimaria.dtos.salida.ProfesionalDtoS;
import com.example.EscuelaPrimaria.entities.domain.Profesional;
import com.example.EscuelaPrimaria.entities.security.Usuario;
import com.example.EscuelaPrimaria.errors.MensajeErrorValidaciones;
import com.example.EscuelaPrimaria.gestores.GestorConversionDto;
import com.example.EscuelaPrimaria.gestores.GestorRepositoryDomain;
import com.example.EscuelaPrimaria.repositories.domain.ProfesionalRepository;
import com.example.EscuelaPrimaria.services.interfaces.domain.ProfesionalService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfesionalServiceImpl implements ProfesionalService<Profesional, Long> {
    private final ProfesionalRepository repo;
    private final GestorConversionDto dto;

    @Override
    public void add(Profesional entity) {
        repo.save(entity);

    }

    @Override
    public void update(Profesional entity) {
        repo.save(entity);

    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Profesional> findAll() {
        return repo.findAll();
    }

    @Override
    public Profesional findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profesional no encontrado"));
    }


    @Override
    public List<Profesional> findProfesionalByCuil(Long cuil) {
        return repo.findProfesionalByCuil(cuil);
    }

    @Override
    public Profesional findProfesionalByUsuario_Id(Long idUsuario) {
        return repo.findProfesionalByUsuario_Id(idUsuario);
    }

    public void crearProfesionalVacio(Usuario usuario) throws EntityExistsException {

        if (usuario.getProfesional() == null) {
            Profesional profesional = new Profesional();
            profesional.setUsuario(usuario);
            add(profesional);


        } else {
            throw new EntityExistsException("el profesional ya existe");
        }

    }

    public void eliminar(
            @Min(value = 99999999, message = MensajeErrorValidaciones.MENSAJE_NUMERO)
            @Max(value = 9999999999L, message = MensajeErrorValidaciones.MENSAJE_NUMERO)
            Long cuil) throws EntityNotFoundException {
        if (existeProfesionalUnico(cuil)) {
            delete(findProfesionalByCuil(cuil).getFirst().getId());
        }
        throw new EntityNotFoundException("el profesional no existe");

    }

    public void actualizarDatos(
            @Min(value = 99999999, message = MensajeErrorValidaciones.MENSAJE_NUMERO)
            @Max(value = 9999999999L, message = MensajeErrorValidaciones.MENSAJE_NUMERO)
            Long cuil, @Valid ProfesionalDtoE profesionalDtoE) throws EntityNotFoundException {
        if (existeProfesionalUnico(cuil)) {
            Profesional profesional = findProfesionalByCuil(cuil).getFirst();

            profesional.setCuil(profesionalDtoE.getCuil());
            profesional.setNombre(profesionalDtoE.getNombre());
            profesional.setApellido(profesionalDtoE.getApellido());
            update(profesional);
        } else
            throw new EntityNotFoundException("el profesional no existe");
    }

    public void actualizarUsuarioProfesional(@NotNull @Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO)
                                             Long idUsuarioProfesional, @Valid ProfesionalDtoE profesionalDtoE) throws EntityNotFoundException {
        if (findProfesionalByUsuario_Id(idUsuarioProfesional) != null) {


            Profesional profesional = findProfesionalByUsuario_Id(idUsuarioProfesional);
            profesional.setNombre(profesionalDtoE.getNombre());
            profesional.setCuil(profesionalDtoE.getCuil());
            profesional.setApellido(profesionalDtoE.getApellido());
            update(profesional);


        } else {
            throw new EntityNotFoundException("el profesional no existe");
        }

    }

    public List<ProfesionalDtoS> todos() {
        return findAll().stream()
                .map(dto::converterProfesionalDtoS)
                .toList();
    }

    public List<ProfesionalDtoS> buscarPorCuil(
            @Min(value = 99999999, message = MensajeErrorValidaciones.MENSAJE_NUMERO)
            @Max(value = 9999999999L, message = MensajeErrorValidaciones.MENSAJE_NUMERO)
            Long cuil) throws EntityNotFoundException {
        if (existeProfesionalUnico(cuil)) {
            return findProfesionalByCuil(cuil).stream().map(dto::converterProfesionalDtoS).toList();
        } else
            throw new EntityNotFoundException("el profesional no existe");

    }

    private boolean existeProfesionalUnico(Long cuil) {
        int cantidadProfesionales = findProfesionalByCuil(cuil).size();
        return cantidadProfesionales != 0;

    }

    // CREAR METODOS PARA MOSTRAR PORFESIONALES POR TIPO DE ROL

    public List<ProfesionalDtoS> buscarProfesionalPorROl(@NotBlank(message = MensajeErrorValidaciones.MENSAJE_NOMBRE) String rol) throws EntityNotFoundException {
        if (!findAll().isEmpty()) {
            List<Profesional> profesionales = findAll().stream().filter(prof -> {

                        if (prof.getUsuario().getRoles().stream().anyMatch(r -> r.getNombre().equals(rol))) return true;
                        return false;


                    })
                    .toList();
            return profesionales.stream().map(dto::converterProfesionalDtoS).toList();
        } else {
            throw new EntityNotFoundException("el profesional no existe");
        }

    }

}
