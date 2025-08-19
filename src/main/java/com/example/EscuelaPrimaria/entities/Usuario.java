package com.example.EscuelaPrimaria.entities;

import jakarta.persistence.*;
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

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @Column(unique = true, name = "nombre_usuario", nullable = false)
    private String nombre;
    @Column(name = "password")
    private String password;

    @Column(name = "mail")
    private String mail;


    @ManyToMany(mappedBy = "usuarios")
    private List<Rol> roles;


    @OneToOne
    @JoinColumn(name = "id_profesional")
    private Profesional profesional;

    @OneToOne
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;
}
