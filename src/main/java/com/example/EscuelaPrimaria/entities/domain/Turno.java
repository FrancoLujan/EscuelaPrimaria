package com.example.EscuelaPrimaria.entities.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "TURNOS")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
/// SUBIR CAMBIOS DE TABLAS
// BORRASTE LOS USUARIOS aaaa

public class Turno {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //enum ?
    @Column(name = "nombre")
    private String nombre;

    @Column(name="horaInicio")
    private LocalTime horaInicio;

    @Column(name = "horaFin")
    private LocalTime horaFin;

    @JsonManagedReference
    @OneToMany(mappedBy = "turno")
    private List<Grado> grado;
}
