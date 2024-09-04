package com.example.ldbc41.services;

import com.example.ldbc41.dto.EquipoTablaPosicionesDTO;
import com.example.ldbc41.models.Equipo;
import com.example.ldbc41.models.Goleadores;
import com.example.ldbc41.models.Partido;
import com.example.ldbc41.models.TablaPosiciones;
import com.example.ldbc41.repository.GoleadoresRepository;
import com.example.ldbc41.repository.PartidoRepository;
import com.example.ldbc41.repository.TablaPosicionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PartidoServiceImpl implements PartidoService {

    private  final EquipoService equipoService;

    private final PartidoRepository partidoRepository;

    private final GoleadoresRepository goleadoresRepository;

    private final TablaPosicionesRepository tablaPosicionesRepository;

    @Autowired
    public PartidoServiceImpl(
            PartidoRepository partidoRepository,
            EquipoService equipoService,
            TablaPosicionesRepository tablaPosicionesRepository,
            GoleadoresRepository goleadoresRepository) {
        this.equipoService = equipoService;
        this.partidoRepository = partidoRepository;
        this.goleadoresRepository= goleadoresRepository;
        this.tablaPosicionesRepository=tablaPosicionesRepository;
    }

    @Override
    @Transactional
    public void agregarPartido(Partido partido) {
        try {
            // Obtener los objetos Equipo asociados a los IDs de equipo local y visitante
            Equipo equipoLocal = equipoService.buscarEquipoPorId(partido.getEquipoLocal().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Equipo local no encontrado"));

            Equipo equipoVisitante = equipoService.buscarEquipoPorId(partido.getEquipoVisitante().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Equipo visitante no encontrado"));

            // Verificar si los equipos pertenecen a la misma categoría
            if (!equipoLocal.getCategoria().equals(equipoVisitante.getCategoria())) {
                throw new IllegalArgumentException("Los equipos no pertenecen a la misma categoría");
            }else{
                String categoria=equipoLocal.getCategoria();
            }

            // Verificar si el equipo local y visitante son diferentes
            if (equipoLocal.getId().equals(equipoVisitante.getId())) {
                throw new IllegalArgumentException("El equipo local y el equipo visitante deben ser diferentes");
            }
            // Guardar el partido en la base de datos
            partidoRepository.save(partido);
        } catch (IllegalArgumentException e) {
            // Manejar excepciones específicas
            throw e; // Propagar la excepción
        } catch (Exception e) {
            // Manejar otras excepciones y propagarlas como RuntimeException
            throw new RuntimeException("Error al agregar el partido: " + e.getMessage(), e);
        }
    }

    //agregar los goles de cada partido por jugador
    @Override
    @Transactional
    public void agregarGoleadoresDePartido(int partidoId, List<Goleadores> goleadores) {
        try {
            // Obtener el partido por su ID
            Partido partido = partidoRepository.findById(partidoId)
                    .orElseThrow(() -> new IllegalArgumentException("Partido no encontrado"));

            // Validar que el partido ya se haya jugado
            // (Aquí puedes agregar tu lógica para verificar si el partido ya se jugó)

            // Asignar el partido a cada goleador
            for (Goleadores goleador : goleadores) {
                goleador.setPartido(partido);

                // Guardar el goleador en la base de datos
                goleadoresRepository.save(goleador);
            }
        } catch (IllegalArgumentException e) {
            // Manejar excepciones específicas
            throw e; // Propagar la excepción
        } catch (Exception e) {
            // Manejar otras excepciones y propagarlas como RuntimeException
            throw new RuntimeException("Error al agregar los goleadores del partido: " + e.getMessage(), e);
        }
    }

    //tabla posiciiones
    @Transactional(readOnly = true)
    public Object[] obtenerTablaDePosicionesPorCategoria(String categoria) {
        Object[] lista =tablaPosicionesRepository.findEquiposByCategoria(categoria);
        return lista;
    }
}
