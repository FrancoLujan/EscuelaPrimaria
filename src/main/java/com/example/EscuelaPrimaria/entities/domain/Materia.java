package com.example.EscuelaPrimaria.entities.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "promedio")
    private Integer promedio;

   /*
   Tabla intermedia con prueba
    */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "materia_prueba",
            joinColumns = @JoinColumn(name = "id_materia"),
            inverseJoinColumns = @JoinColumn(name = "id_prueba")
    )
    private List<Prueba> pruebas = new ArrayList<>(3);


    @ManyToOne
    @JoinColumn(name = "id_grado")
    @JsonBackReference
    private Grado grado;
}
