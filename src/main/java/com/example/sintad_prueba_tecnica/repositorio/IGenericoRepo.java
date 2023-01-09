package com.example.sintad_prueba_tecnica.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface IGenericoRepo<T, ID> extends JpaRepository<T, ID> {
}
