package com.example.EscuelaPrimaria.entities.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Prueba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_realizacion")
    private LocalDate fechaRealizacion;

    @Column(name = "tema")
    private String tema;

    // regla de negocio: son notas redondas
    @Column(name = "nota")
    private Integer nota;

    @ManyToMany
    @JoinTable(
            name = "prueba_alumno",
            joinColumns = @JoinColumn(name = "id_prueba"),
            inverseJoinColumns = @JoinColumn(name = "id_alumno")
    )
    private List<Alumno> alumnos;






}
