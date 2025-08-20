package com.example.EscuelaPrimaria.services.interfaces;


import java.util.List;

public interface Crud<T, K> {
    void add(T entity);
    void update(T entity);
    void delete(T entity);
    List<T> getAll();
    T getById(K id);


}
