package com.example.EscuelaPrimaria.services.implementations.security;

import com.example.EscuelaPrimaria.dtos.entrada.LoginDtoE;
import com.example.EscuelaPrimaria.dtos.entrada.UsuarioDtoE;
import com.example.EscuelaPrimaria.dtos.entrada.UsuarioDtoSeteo;
import com.example.EscuelaPrimaria.dtos.salida.LoginDtoS;
import com.example.EscuelaPrimaria.dtos.salida.UsuarioDtoS;
import com.example.EscuelaPrimaria.entities.security.Rol;
import com.example.EscuelaPrimaria.entities.security.Usuario;
import com.example.EscuelaPrimaria.enums.RolEnum;
import com.example.EscuelaPrimaria.errors.MensajeErrorValidaciones;
import com.example.EscuelaPrimaria.gestores.GestorConversionDto;
import com.example.EscuelaPrimaria.repositories.security.UsuarioRepository;
import com.example.EscuelaPrimaria.securityConfig.PasswordEncoderConfig;
import com.example.EscuelaPrimaria.services.implementations.domain.AlumnoServiceImpl;
import com.example.EscuelaPrimaria.services.implementations.domain.ProfesionalServiceImpl;
import com.example.EscuelaPrimaria.services.interfaces.security.UsuarioService;
import com.example.EscuelaPrimaria.utils.JwtUtils;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Validated // para poder usar @Valid
// cuando no se cumpla el @Valid lanza implicitamente MethodArgumentNotValidException (no debo tratarlo)
public class UsuarioServiceImpl implements UsuarioService<Usuario, Long>, UserDetailsService {
    private final UsuarioRepository repo;
    private final RolServiceImpl rolService;
    private final GestorConversionDto conversion;
    private final AlumnoServiceImpl alumnoService;
    private final ProfesionalServiceImpl profesionalService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoderConfig passwordEncoder;
    //private final ProfesionalServiceImpl profesionalService;

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

            // no olvidar seteo
            usuarioNuevo.setEnabled(true);
            usuarioNuevo.setAccountNotExpired(true);
            usuarioNuevo.setAccountNotLocked(true);
            usuarioNuevo.setCredentialsNotExpired(true);

            add(usuarioNuevo);

        } else {
            throw new EntityExistsException("El usuario ya existe");
        }

    }


    public void actualizarEstados(@NotNull @Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO) Long id
            , UsuarioDtoSeteo estados) {
        Usuario usuario = findById(id);
        usuario.setEnabled(estados.isEnabled());
        usuario.setAccountNotExpired(estados.isAccountNotExpired());
        usuario.setAccountNotLocked(estados.isAccountNotLocked());
        usuario.setCredentialsNotExpired(estados.isCredentialsNotExpired());
        update(usuario);


    }


    // esta limpio porque el mail se valida antes y al buscar el id puede lanzar un error...
    // Se repite en todas las actualizaciones
    public void actualizarMail(@NotNull @Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO) Long id,
                               @Email(message = MensajeErrorValidaciones.MENSAJE_EMAIL) String mail)
            throws EntityNotFoundException {

        Usuario usuario = findById(id);
        usuario.setMail(mail);
        update(usuario);

    }

    public void actualizarPassword(@Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO) Long id,
                                   @Size(min = 5, max = 20, message = MensajeErrorValidaciones.MENSAJE_PASSWORD)
                                   @NotBlank String password) throws EntityNotFoundException, ConstraintViolationException {
        Usuario usuario = findById(id);
        usuario.setPassword(encriptarPassword(password));
        update(usuario);

    }

    public void actualizarNombre(@Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO) Long id,
                                 @Size(min = 4, max = 10, message = MensajeErrorValidaciones.MENSAJE_NOMBRE) @NotEmpty String nombre)
            throws EntityNotFoundException, ConstraintViolationException {
        Usuario usuario = findById(id);
        usuario.setNombre(nombre);
        update(usuario);

    }

    // si usaras optional seria un poco mas simple pero solo un poco
    // recorda que esta involucrado por una tabla intermedia con rol por ende
    // en el rol elimino los usuarios ... ES MUY IMPORTANTE
    public void eliminar(@Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO) Long id) throws EntityNotFoundException, ConstraintViolationException {
        if (existsUsuarioByNombre(findById(id).getNombre())) {
            Usuario usuario = findById(id);
            usuario.getRoles().forEach(rol -> {rol.getUsuarios().remove(usuario);});
            delete(id);
        } else {
            throw new EntityNotFoundException("El usuario no existe");
        }


    }

    public List<UsuarioDtoS> todos() {
        List<Usuario> usuarios = findAll();
        return usuarios.stream().map(conversion::converterUsuarioDtoS).toList();


    }

    public UsuarioDtoS buscarUsuario(@Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO) Long id)
            throws EntityNotFoundException, ConstraintViolationException {
        Usuario usuario = findById(id);
        return conversion.converterUsuarioDtoS(usuario);
    }

    // cuidado Esto tendria QUE ESTAR EN el servicio de roles (por como estan las relaciones de las tablas...)
    public void asociarRol(@Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO) Long idUsuario,
                           @Min(value = 1, message = MensajeErrorValidaciones.MENSAJE_NUMERO) Long idRol) throws EntityNotFoundException, ConstraintViolationException
    , EntityExistsException {
        Usuario usuario = findById(idUsuario);
        Rol rol = rolService.findById(idRol);
        if (usuario.getRoles().contains(rol)) {
            throw new EntityExistsException("Existe el rol para dicho usuario");
        }
        usuario.getRoles().add(rol);
        rol.getUsuarios().add(usuario);
        rolService.update(rol);
        update(usuario);
        asociarEntidad(usuario.getId());


    }

    // USO CUANDO ASOCIO EL ROL
    private void asociarEntidad(Long id) {
        Usuario usuario = findById(id);
        usuario.getRoles().forEach(rol -> {
            if (rol.getNombre().equalsIgnoreCase(RolEnum.ALUMNO.toString())) {
                alumnoService.crearAlumnoVacio(usuario); // CREAR UN ALUMNO VACIO pero con usuario
            } else if(Arrays.stream(RolEnum.values()).anyMatch(e -> e.name().equalsIgnoreCase(rol.getNombre()))) {
                profesionalService.crearProfesionalVacio(usuario);
            }
        });

    }

    // tratar luego cuando ya tengas casi todo listo la seguridad...
    // GRACIAS A ESTO PUEDO MENEJAR EN LOS CONTROLLERS LOS PERMISOS Y LOS ROLES DE CADA ENPOINT COMO QUIERA
    // puedo usar la granularidad que se me antoje...
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
                usuario.isAccountNotExpired(),
                usuario.isCredentialsNotExpired(),
                usuario.isAccountNotLocked(),
                listAuthority
        );
    }

    public LoginDtoS loginUser(@Valid LoginDtoE loginDtoE) {
        String username = loginDtoE.getUsuario();
        String password = loginDtoE.getPassword();

        Authentication authentication = authentication(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.createToken(authentication);

        return new LoginDtoS(username,"login ok",token);

    }

    public Authentication authentication(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);

        if(userDetails == null) throw new BadCredentialsException("El usuario o contraseña incorrecta");
        if(!passwordEncoder.passwordEncoder().matches(password, userDetails.getPassword())) throw new BadCredentialsException("El usuario o contraseña incorrecta");

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }






}
