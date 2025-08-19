package com.example.EscuelaPrimaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int horaInicio;

    @Column(name = "horaFin")
    private int horaFin;

    @OneToOne
    @JoinColumn(name = "id_grado")
    private Grado grado;
}
