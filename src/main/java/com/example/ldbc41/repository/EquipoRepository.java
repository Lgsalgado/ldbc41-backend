package com.example.ldbc41.repository;

import com.example.ldbc41.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
    Equipo findByNombre(String nombre);
    List<Equipo> findAllByOrderByNombreAsc();
    List<Equipo> findAllByOrderByCategoriaAsc();
}

