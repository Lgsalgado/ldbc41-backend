package com.example.ldbc41.controllers;

import com.example.ldbc41.models.Equipo;
import com.example.ldbc41.models.Jugadore;
import com.example.ldbc41.services.EquipoService;
import com.example.ldbc41.services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;
    @Autowired
    private EquipoService equipoService;

    // Endpoint para buscar un jugador por cédula
    @GetMapping("/{cedula}")
    public ResponseEntity<Jugadore> buscarJugadorPorCedula(@PathVariable String cedula) {
        Jugadore jugador = jugadorService.buscarPorCedula(cedula);
        String equipo=jugadorService.obtenerNombreEquipoDelJugador(cedula);

        return new ResponseEntity<>(jugador, HttpStatus.ACCEPTED);
    }
    // Endpoint para obtener jugadores por equipoId
    @GetMapping("/equipo/{equipoId}")
    public ResponseEntity<List<Jugadore>> obtenerJugadoresPorEquipoId(@PathVariable Integer equipoId) {
        List<Jugadore> jugadores = jugadorService.buscarJugadoresPorEquipoId(equipoId);
        if (jugadores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(jugadores, HttpStatus.OK);
    }
}
