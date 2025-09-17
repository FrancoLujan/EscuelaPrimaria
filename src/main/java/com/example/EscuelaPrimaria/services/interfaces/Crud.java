package com.example.EscuelaPrimaria.services.interfaces;


import java.util.List;

public interface Crud<T, K> {
    void add(T entity);
    void update(T entity);
    void delete(K id);
    List<T> findAll();
    T findById (K id);


}
