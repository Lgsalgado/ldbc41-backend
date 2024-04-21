package com.example.ldbc41.services;

import com.example.ldbc41.models.Goleadores;
import com.example.ldbc41.models.Partido;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PartidoService {
    void agregarPartido(Partido partido);
    void agregarGoleadoresDePartido(int partidoId, List<Goleadores> goleadores);
}
