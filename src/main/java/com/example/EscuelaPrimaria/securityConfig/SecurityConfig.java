package com.example.EscuelaPrimaria.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    //cadena de filtros
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return null;
    }
    @Bean
    //Cordina la auntenticacion; usa el authenticationProvider
    public AuthenticationManager authenticationManager(AuthenticationConfiguration a) throws Exception {
        return null;
    }

    @Bean
    //Auntentica segun del proveedor
    public AuthenticationProvider authenticationProvider() {
        return null;
    }

    @Bean
    //ENCRIPTADO
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
