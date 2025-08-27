package com.example.EscuelaPrimaria.services.interfaces;


import com.example.EscuelaPrimaria.entities.Grado;

import java.util.List;

public interface GradoService<T, K> extends Crud<T, K> {
    boolean findGradoByNivelEqualsIgnoreCase(int nivel);
    boolean findGradoByTurnoEqualsIgnoreCase(String turnoId);
    List<Grado> findGradoByNivel(int nivel);


}
