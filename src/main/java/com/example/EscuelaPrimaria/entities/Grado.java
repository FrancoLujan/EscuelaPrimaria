package com.example.EscuelaPrimaria.entities;

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

    @OneToMany(mappedBy = "grado")
    List<Materia> materias;

    @OneToMany(mappedBy = "grado")
    List<Alumno> alumnos = new ArrayList<>(5);
}
