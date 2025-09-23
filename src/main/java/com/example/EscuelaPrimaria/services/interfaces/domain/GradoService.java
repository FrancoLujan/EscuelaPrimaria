package com.example.EscuelaPrimaria.services.interfaces.domain;


import com.example.EscuelaPrimaria.entities.domain.Grado;
import com.example.EscuelaPrimaria.services.interfaces.Crud;

import java.util.List;

public interface GradoService<T, K> extends Crud<T, K> {
    boolean existsGradoByNivel(K nivel);
    boolean existsGradoByTurno_Nombre(String turno);
    Grado findByGradoByNivel(K nivel, String turno);
    List<Grado> findGradoByNivel(K nivel);


}
