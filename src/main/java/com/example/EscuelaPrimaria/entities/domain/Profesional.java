package com.example.EscuelaPrimaria.entities.domain;

import com.example.EscuelaPrimaria.entities.security.Usuario;
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
