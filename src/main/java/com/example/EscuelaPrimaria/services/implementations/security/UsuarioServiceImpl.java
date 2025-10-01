package com.example.EscuelaPrimaria.services.implementations.security;

import com.example.EscuelaPrimaria.dtos.entrada.UsuarioDtoE;
import com.example.EscuelaPrimaria.dtos.salida.UsuarioDtoS;
import com.example.EscuelaPrimaria.entities.security.Rol;
import com.example.EscuelaPrimaria.entities.security.Usuario;

import com.example.EscuelaPrimaria.repositories.security.UsuarioRepository;
import com.example.EscuelaPrimaria.services.interfaces.security.UsuarioService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor                             // interface de spring security
public class UsuarioServiceImpl implements UsuarioService<Usuario, Long>, UserDetailsService {
    private final UsuarioRepository repo;
    private final RolServiceImpl rolService;

    @Override
    public void add(Usuario entity) {
        repo.save(entity);

    }

    @Override
    public void update(Usuario entity) {
        repo.save(entity);

    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);

    }

    @Override
    public List<Usuario> findAll() {

        return repo.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return repo.findById(id)
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

        return repo.findUsuarioByNombre(nombre);
    }

    @Override
    public String encriptarPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }


    public void agregar(UsuarioDtoE usuarioDtoE) throws EntityExistsException {
        if (usuarioDtoE.getNombre().isEmpty() || usuarioDtoE.getPassword().isEmpty() || usuarioDtoE.getMail().isEmpty()) {
            throw new EntityExistsException("el usuario ya existe");
        }

        Optional<Usuario> usuario = findUsuarioByNombre(usuarioDtoE.getNombre());
        if (usuario.isEmpty()) {
            Usuario usuarioNuevo = new Usuario();
            usuarioNuevo.setNombre(usuarioDtoE.getNombre());
            usuarioNuevo.setPassword(encriptarPassword(usuarioDtoE.getPassword()));
            usuarioNuevo.setMail(usuarioDtoE.getMail());

        } else {
            throw new EntityExistsException("el usuario ya existe");
        }
    }

    public void actualizarMail(Long id, String mail) throws EntityNotFoundException {
        if (!mail.isEmpty()) {
            Usuario usuario = findById(id);
            usuario.setMail(mail);
            update(usuario);
        } else {
            throw new EntityNotFoundException("El usuario no existe");
        }

    }

    public void actualizarPassword(Long id, String password) throws EntityNotFoundException {
        if (!password.isEmpty()) {
            Usuario usuario = findById(id);
            usuario.setPassword(encriptarPassword(password));
            update(usuario);
        } else {
            throw new EntityNotFoundException("El usuario no existe");
        }
    }

    public void actualizarNombre(Long id, String nombre) throws EntityNotFoundException {
        if (!nombre.isEmpty()) {
            Usuario usuario = findById(id);
            usuario.setNombre(nombre);
            update(usuario);
        } else {
            throw new EntityNotFoundException("El usuario no existe");
        }
    }

    // si usaras optional seria un poco mas simple pero solo un poco
    public void eliminar(Long id) throws EntityNotFoundException {
        if (id != null && id > 0) { // verificamos id porque no lo tomamos en cuenta en delete()
            delete(id);
        } else {
            throw new EntityNotFoundException("El usuario no existe");
        }
    }

    public List<UsuarioDtoS> todos() {
        List<Usuario> usuarios = findAll();
        ModelMapper modelMapper = new ModelMapper();
        return usuarios.stream().map(e -> modelMapper.map(e, UsuarioDtoS.class)).toList();


    }

    public UsuarioDtoS buscarUsuario(Long id) throws EntityNotFoundException {
        Usuario usuario = findById(id);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioDtoS.class);
    }

    public void asociarRol(Long idUsuario, Long idRol) throws EntityNotFoundException {
        Usuario usuario = findById(idUsuario);
        Rol rol = rolService.findById(idRol);
        usuario.getRoles().add(rol);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = findUsuarioByNombre(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));
        // manejar permisos
        List<SimpleGrantedAuthority> listAuthority = new ArrayList<>();

        // CONVERTIMOS LOS ROLES
        usuario.getRoles()
                .forEach(role -> listAuthority.add(new SimpleGrantedAuthority("ROLE_".concat(role.getNombre()))));

        usuario.getRoles().stream()
                .flatMap(role -> role.getPermisos().stream())
                .forEach(permiso -> listAuthority.add(new SimpleGrantedAuthority(permiso.getNombre())));




        // USER es de springSecurity
        return new User(
                usuario.getNombre(),
                usuario.getPassword(),
                usuario.isEnabled(),
                usuario.isAccountNonExpired(),
                usuario.isCredentialsNonExpired(),
                usuario.isAccountNonLocked(),
                listAuthority
        );
    }
}
