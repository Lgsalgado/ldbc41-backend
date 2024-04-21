package com.example.ldbc41.repository;


import com.example.ldbc41.models.Goleadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoleadoresRepository extends JpaRepository<Goleadores, Integer> {
    // Puedes agregar métodos personalizados de consulta si es necesario
}
