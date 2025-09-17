package com.example.EscuelaPrimaria.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long cuil;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @OneToOne(mappedBy = "profesional")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name="id_grado")
    private Grado grado;


}
