package com.example.EscuelaPrimaria.services.implementations.security;

import com.example.EscuelaPrimaria.entities.security.Usuario;

import com.example.EscuelaPrimaria.gestores.GestorRepositorySecurity;
import com.example.EscuelaPrimaria.services.interfaces.security.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService<Usuario, Long> {
   private final  GestorRepositorySecurity gestorRepo;

    @Override
    public void add(Usuario entity) {
        gestorRepo.getUsuarioRepository().save(entity);

    }

    @Override
    public void update(Usuario entity) {
        gestorRepo.getUsuarioRepository().save(entity);

    }

    @Override
    public void delete(Long id) {
        gestorRepo.getUsuarioRepository().deleteById(id);

    }

    @Override
    public List<Usuario> findAll() {

        return gestorRepo.getUsuarioRepository().findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return gestorRepo.getUsuarioRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El usuario no existe"));
    }

    // EL USO DE OPTIONAL
    /*
    El uso de Optional es para evitar tanta verificacion ademas DE MANEJAR los null de manera explicita
    es decir te obliga a tratarlo, por ejemplo en findById tengo en cuenta el null pero si no ? puedo crear un caos
    sumado a que no lo trato en metodos posteriores.
    Entoces puedo tratar los nulos en metodos que pueden lanzarlos(OJO si es que necesito evitar los nulos, no lo uses siempre)
    , ademas de verme obligado a no olvidarme que puede devolver nulos. Ejemplo puedo usar los metodos del Optional,
    para saber si esta presente, darle un valor por defecto si es nulo, lanzar errores en caso de nulo
    */
    @Override
    public Optional<Usuario> findUsuarioByNombre(String nombre) {

        return gestorRepo.getUsuarioRepository().findUsuarioByNombre(nombre);
    }
}
