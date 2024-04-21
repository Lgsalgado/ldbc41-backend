package com.example.ldbc41.repository;

import com.example.ldbc41.models.Jugadore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<Jugadore, Long> {
    Jugadore findByCedula(String cedula);
}
