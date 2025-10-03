package com.example.EscuelaPrimaria.services.implementations.security;

import com.example.EscuelaPrimaria.dtos.entrada.UsuarioDtoE;
import com.example.EscuelaPrimaria.dtos.salida.UsuarioDtoS;
import com.example.EscuelaPrimaria.entities.security.Rol;
import com.example.EscuelaPrimaria.entities.security.Usuario;
import com.example.EscuelaPrimaria.errors.MensajeErrorValidaciones;
import com.example.EscuelaPrimaria.gestores.GestorConversionDto;
import com.example.EscuelaPrimaria.repositories.security.UsuarioRepository;
import com.example.EscuelaPrimaria.services.interfaces.security.UsuarioService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor// interface de spring security:  UserDetailsService
@Validated // para poder usar @Valid
// cuando no se cumpla el @Valid lanza inplicitamente MethodArgumentNotValidException (no debo tratarlo)
public class UsuarioServiceImpl implements UsuarioService<Usuario, Long>, UserDetailsService {
    private final UsuarioRepository repo;
    private final RolServiceImpl rolService;
    private final GestorConversionDto conversion;

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

    @Override
    public boolean existsUsuarioByNombre(String nombre) {
        return repo.existsUsuarioByNombre(nombre);
    }


    public void agregar(@Valid UsuarioDtoE usuarioDtoE) throws EntityExistsException {
        if (!existsUsuarioByNombre(usuarioDtoE.getNombre())) {
            Usuario usuarioNuevo = new Usuario();
            usuarioNuevo.setNombre(usuarioDtoE.getNombre());
            usuarioNuevo.setPassword(encriptarPassword(usuarioDtoE.getPassword()));
            usuarioNuevo.setMail(usuarioDtoE.getMail());
            add(usuarioNuevo);

        } else {
            throw new EntityExistsException("El usuario ya existe");
        }

    }

    // esta limpio porque el mail se valida antes y al buscar el id puede lanzar un error...
    // Se repite en todas las actualizaciones
    public void actualizarMail(@NotNull @Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_ID) Long id,
                               @Email(message = MensajeErrorValidaciones.MENSAJE_EMAIL) String mail)
            throws EntityNotFoundException, ConstraintViolationException {

        Usuario usuario = findById(id);
        usuario.setMail(mail);
        update(usuario);

    }

    public void actualizarPassword(@Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_ID) Long id,
                                   @Size(min = 5, max = 20, message = MensajeErrorValidaciones.MENSAJE_PASSWORD)
                                   @NotBlank String password) throws EntityNotFoundException, ConstraintViolationException {
        Usuario usuario = findById(id);
        usuario.setPassword(encriptarPassword(password));
        update(usuario);

    }

    public void actualizarNombre(@Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_ID) Long id,
                                 @Size(min = 4, max = 10, message = MensajeErrorValidaciones.MENSAJE_NOMBRE) @NotEmpty String nombre)
            throws EntityNotFoundException, ConstraintViolationException {
        Usuario usuario = findById(id);
        usuario.setNombre(nombre);
        update(usuario);

    }

    // si usaras optional seria un poco mas simple pero solo un poco

    public void eliminar(@Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_ID) Long id) throws EntityNotFoundException, ConstraintViolationException {
        if (existsUsuarioByNombre(findById(id).getNombre())) {
            delete(id);
        } else {
            throw new EntityNotFoundException("El usuario no existe");
        }


    }

    public List<UsuarioDtoS> todos() {
        List<Usuario> usuarios = findAll();
        return usuarios.stream().map(conversion::converterUsuarioDtoS).toList();


    }

    public UsuarioDtoS buscarUsuario(@Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_ID) Long id)
            throws EntityNotFoundException, ConstraintViolationException {
        Usuario usuario = findById(id);
        return conversion.converterUsuarioDtoS(usuario);
    }

    // cuidado Esto tendria QUE ESTAR EN el servicio de roles (por como estan las relaciones de las tablas...)
    public void asociarRol(@Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_ID) Long idUsuario,
                           @Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_ID) Long idRol) throws EntityNotFoundException, ConstraintViolationException {
        Usuario usuario = findById(idUsuario);
        Rol rol = rolService.findById(idRol);
        usuario.getRoles().add(rol);
        rol.getUsuarios().add(usuario);
        rolService.update(rol);
        update(usuario);

    }


    // tratar luego cuando ya tengas casi todo listo la seguridad...
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
