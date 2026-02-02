package com.example.EscuelaPrimaria.enums;

import com.example.EscuelaPrimaria.entities.security.Usuario;
import com.example.EscuelaPrimaria.enums.interfeces.RolGestor;
import com.example.EscuelaPrimaria.services.implementations.security.UsuarioServiceImpl;


/*
Administrador: se encarga de el crud de las clase de seguridad, TODO LO REFERIDO A ROLES, PERMISOS Y USUARIOS LO HARA  UN ADMINISTRADOR

roles de negocio

Director: El director se encarga del crud de materias, turnos y de grados  ademas asi como la colacion de sexto grado. la colacion involucra
          que el director saca el promedio de toda la vida escolar de los alumnos(proceso automatico) y generando un diploma con dicho promedio
          el diploma tiene datos basicos del alumno(pdf) .


Vicedirector: promueve a los alumnos de grado y de la asignacion de profes a grados. genera el imforme de grados.

Profesor: asigna notas y promedio de materia asi como el estado del alumno a fin de año(el proceso de cambio de estado
es automatico dependiendo del promedio)

Alumno: ve sus notas, año, materia y estado academico
 */

public enum RolEnum {
    ADMINISTRADOR,
    DIRECTOR,
    VICEDIRECTOR,
    PROFESOR,
    ALUMNO;

}
