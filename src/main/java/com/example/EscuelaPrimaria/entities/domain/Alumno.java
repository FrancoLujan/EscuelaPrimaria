package com.example.EscuelaPrimaria.entities.domain;

import com.example.EscuelaPrimaria.entities.security.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class  Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "cuil", unique = true)
    private Long cuil;

    @Column(name = "fechaDeNacimiento")
    private LocalDate fechaNacimiento;

    // definira si el alumno pasa o no de grado, EN TRUE SI PASA Y POR DEFECTO....
    @JoinColumn(name = "estadoAcademico")
    private Boolean estadoAcademico;

    @ManyToOne
    @JoinColumn(name = "id_grado")
    @JsonBackReference
    private Grado grado;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


    @ManyToMany(mappedBy = "alumnos")
    private List<Prueba> pruebas;


}
