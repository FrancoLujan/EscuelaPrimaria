package com.example.EscuelaPrimaria.entities.security;

import com.example.EscuelaPrimaria.entities.domain.Alumno;
import com.example.EscuelaPrimaria.entities.domain.Profesional;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean accountNotExpired;
    private boolean accountNotLocked;
    private boolean credentialsNotExpired;
    private boolean enabled;

    @Column(unique = true, name = "nombre_usuario", nullable = false)
    private String nombre;
    @Column(name = "password")
    private String password;

    //agregado
    @Column(name = "mail")
    @Email
    private String mail;


    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.EAGER)
    private List<Rol> roles;


    @OneToOne(mappedBy = "usuario")
    private Profesional profesional;

    @OneToOne(mappedBy = "usuario")
    private Alumno alumno;
}
