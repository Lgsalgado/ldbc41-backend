package com.example.ldbc41.repository;

import com.example.ldbc41.models.Equipo;
import com.example.ldbc41.models.TablaPosiciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TablaPosicionesRepository extends JpaRepository<TablaPosiciones, Integer> {

    @Query("SELECT DISTINCT tp FROM TablaPosiciones tp JOIN tp.equipo e WHERE e.categoria = :categoria")
    Object[] findEquiposByCategoria(@Param("categoria") String categoria);
}
