package com.example.ldbc41.repository;

import com.example.ldbc41.models.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {
    // Puedes agregar métodos personalizados si es necesario
}
