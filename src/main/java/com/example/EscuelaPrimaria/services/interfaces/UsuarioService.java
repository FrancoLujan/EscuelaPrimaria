package com.example.EscuelaPrimaria.services.interfaces;

public interface UsuarioService<T,K> extends Crud<T,K> {
    T getByName(String name);
}
