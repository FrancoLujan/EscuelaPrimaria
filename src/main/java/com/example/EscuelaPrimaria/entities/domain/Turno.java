package com.example.EscuelaPrimaria.entities.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "TURNOS")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToOne
    @JoinColumn(name = "id_grado")
    private Grado grado;
}
