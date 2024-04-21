package com.example.ldbc41.controllers;

import com.example.ldbc41.models.Equipo;
import com.example.ldbc41.models.Jugadore;
import com.example.ldbc41.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    @Autowired
    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarEquipo(@RequestBody Equipo equipo) {
        try {
            Equipo equipoGuardado = equipoService.agregarEquipo(equipo);
            return new ResponseEntity<>(equipoGuardado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Devuelve un estado de bad request (400) con el mensaje de error
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage()); // Devuelve un estado de error interno del servidor (500) con el mensaje de error
        }
    }

    @PostMapping("/{equipoId}/jugadores")
    public ResponseEntity<?> agregarJugadorAlEquipo(@PathVariable Integer equipoId, @RequestBody Jugadore jugador) {
        try {
            Optional<Equipo> optionalEquipo = equipoService.buscarEquipoPorId(equipoId);
            if (optionalEquipo.isPresent()) {
                equipoService.agregarJugadorAlEquipo(equipoId, jugador);
                return ResponseEntity.ok("Jugador agregado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El equipo con ID " + equipoId + " no se encontró o no existe");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}
