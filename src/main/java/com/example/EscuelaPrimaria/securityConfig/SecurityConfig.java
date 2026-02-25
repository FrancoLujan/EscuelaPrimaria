package com.example.EscuelaPrimaria.securityConfig;

import com.example.EscuelaPrimaria.securityConfig.filter.JwtTokenValidator;
import com.example.EscuelaPrimaria.services.implementations.security.UsuarioServiceImpl;
import com.example.EscuelaPrimaria.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private UsuarioServiceImpl usuarioServiceImpl;

    private JwtUtils jwtUtils;

    private PasswordEncoderConfig passwordEncoderConfig;

    @Bean
    //cadena de filtros
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(f -> f.disable())
                .authenticationProvider(authenticationProvider())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(s-> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }
    @Bean
    //Cordina la auntenticacion; usa el authenticationProvider
    public AuthenticationManager authenticationManager(AuthenticationConfiguration a) throws Exception {
        return a.getAuthenticationManager();
    }
//
    @Bean
    //Auntentica segun del proveedor
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoderConfig.passwordEncoder());
        provider.setUserDetailsService(usuarioServiceImpl);
        return provider;
    }

//    @Bean
//    //ENCRIPTADO
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
