package com.example.ldbc41.repository;

import com.example.ldbc41.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
    Equipo findByNombre(String nombre);
}

