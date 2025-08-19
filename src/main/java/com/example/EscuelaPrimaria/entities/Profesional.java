package com.example.EscuelaPrimaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "cuil")
    private Long cuil;


    @OneToOne(mappedBy = "profesional")
    private Usuario usuario;


    @OneToOne
    @JoinColumn(name="id_grado")
    private Grado grado;


}
