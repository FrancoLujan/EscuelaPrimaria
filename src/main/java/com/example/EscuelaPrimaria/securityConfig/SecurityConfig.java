package com.example.EscuelaPrimaria.securityConfig;

import com.example.EscuelaPrimaria.services.implementations.security.UsuarioServiceImpl;
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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    //cadena de filtros
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(f -> f.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(s-> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
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
    public AuthenticationProvider authenticationProvider(UsuarioServiceImpl usuarioServiceImpl) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(usuarioServiceImpl);
        return provider;
    }

    @Bean
    //ENCRIPTADO
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
