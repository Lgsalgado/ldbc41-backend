package com.example.ldbc41.services;
import com.example.ldbc41.models.Jugadore;
import com.example.ldbc41.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class JugadorServiceImpl implements JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Override
    public Jugadore agregarJugador(Jugadore jugador) {
        return jugadorRepository.save(jugador);
    }

    @Override
    public boolean jugadorPerteneceAEquipo(String cedula) {
        Jugadore jugador = jugadorRepository.findByCedula(cedula);
        return jugador != null && jugador.getEquipo() != null;
    }
    // Método para buscar un jugador por su cédula
    public Jugadore buscarPorCedula(String cedula) {
        return jugadorRepository.findByCedula(cedula);
    }
    @Override
    public String obtenerNombreEquipoDelJugador(String cedula) {
        Jugadore jugador = jugadorRepository.findByCedula(cedula);
        if (jugador != null && jugador.getEquipo() != null) {
            return jugador.getEquipo().getNombre();
        } else {
            return "El jugador no pertenece a ningún equipo";
        }
    }
}

