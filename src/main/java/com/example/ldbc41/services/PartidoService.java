package com.example.ldbc41.services;

import com.example.ldbc41.dto.EquipoTablaPosicionesDTO;
import com.example.ldbc41.models.Equipo;
import com.example.ldbc41.models.Goleadores;
import com.example.ldbc41.models.Partido;
import com.example.ldbc41.models.TablaPosiciones;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public interface PartidoService {
    void agregarPartido(Partido partido);
    void agregarGoleadoresDePartido(int partidoId, List<Goleadores> goleadores);
    public Object[] obtenerTablaDePosicionesPorCategoria(String categoria);
}
