package com.example.EscuelaPrimaria.entities.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class  Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nota")
    private int nota;

    @ManyToOne
    @JoinColumn(name = "id_grado")
    @JsonBackReference
    private Grado grado;
}
