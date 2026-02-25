package com.example.EscuelaPrimaria.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

/*
Lo separe para que no halla una dependencia circular entre la SecurityConfig y UsuarioServiceImp
 */
public class PasswordEncoderConfig {
    //ENCRIPTADO
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
