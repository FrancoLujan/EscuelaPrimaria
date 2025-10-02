package com.example.EscuelaPrimaria.entities.domain;

import com.example.EscuelaPrimaria.entities.security.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "cuil", unique = true)
    private Long cuil;

    @Column(name = "fechaDeNacimiento")
    private Date fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "id_grado")
    @JsonBackReference
    private Grado grado;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
