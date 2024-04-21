package com.example.ldbc41.services;

import com.example.ldbc41.models.Jugadore;

public interface JugadorService {
    Jugadore agregarJugador(Jugadore jugador);
    boolean jugadorPerteneceAEquipo(String cedula);
    String obtenerNombreEquipoDelJugador(String cedula);
}
