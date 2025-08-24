package com.example.EscuelaPrimaria.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "cuil")
    private Long cuil;


    @ManyToOne
    @JoinColumn(name = "id_grado")
    @JsonBackReference
    private Grado grado;

    @OneToOne(mappedBy = "alumno")
    private Usuario usuario;
}
