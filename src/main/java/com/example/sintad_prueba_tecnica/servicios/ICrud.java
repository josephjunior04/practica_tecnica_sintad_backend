package com.example.sintad_prueba_tecnica.servicios;

import java.util.List;
import java.util.Optional;

public interface ICrud<T, ID> {
    T save(T t);
    T update(T t);
    T findById(ID id);
    List<T> findAll();
    void delete(ID id);
}
