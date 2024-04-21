package com.example.ldbc41.services;

import com.example.ldbc41.models.Equipo;
import com.example.ldbc41.models.Jugadore;

import java.util.Optional;

public interface EquipoService {
    Equipo agregarEquipo(Equipo equipo);

    Optional<Equipo> buscarEquipoPorId(Integer id);

    void agregarJugadorAlEquipo(Integer equipoId, Jugadore jugador);

    boolean jugadorEstaEnEquipo(String cedula);
}
