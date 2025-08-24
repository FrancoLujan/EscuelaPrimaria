package com.example.EscuelaPrimaria.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GRADOS")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Grado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nivel")
    private int nivel;

    @OneToOne(mappedBy = "grado")
    private Turno turno;

    @OneToOne(mappedBy = "grado")
    private Profesional profesor;


    @JsonManagedReference
    @OneToMany(mappedBy = "grado")
    List<Materia> materias = new ArrayList<>(5);

    @JsonManagedReference
    @OneToMany(mappedBy = "grado")
    List<Alumno> alumnos;
}
