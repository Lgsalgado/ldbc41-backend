package com.example.ldbc41.controllers;

import com.example.ldbc41.models.Equipo;
import com.example.ldbc41.models.Jugadore;
import com.example.ldbc41.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipos")
//@PreAuthorize("hasRole('admin_client_role')")
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
                return ResponseEntity.ok(Collections.singletonMap("message", "Jugador agregado exitosamente"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("El equipo con ID " + equipoId + " no se encontró o no existe");
            }
        } catch (IllegalArgumentException e) {
            // Aquí devolvemos un 400 con un mensaje claro para el frontend
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor");
        }
    }


    @PostMapping("/{equipoId}/jugadorCalificacion")
    public ResponseEntity<?> agregarJugadorAlEquipo(
            @PathVariable Integer equipoId,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("fechaNacimiento") LocalDate fechaNacimiento,
            @RequestParam("nacionalidad") String nacionalidad,
            @RequestParam("numeroEquipo") int numeroEquipo,
            @RequestParam("cedula") String cedula,
            @RequestParam(value = "foto", required = false) MultipartFile foto,
            @RequestParam(value = "cedulaImg", required = false) MultipartFile cedulaImg) {

        try {
            Optional<Equipo> optionalEquipo = equipoService.buscarEquipoPorId(equipoId);
            if (optionalEquipo.isPresent()) {
                // Crear el jugador y agregarlo al equipo
                Jugadore jugador = equipoService.guardarJugador(
                        nombre, apellido, fechaNacimiento, nacionalidad, numeroEquipo, cedula, equipoId, foto, cedulaImg);
                //equipoService.agregarJugadorAlEquipo(equipoId, jugador);
                return ResponseEntity.ok(Collections.singletonMap("message", "Jugador agregado exitosamente"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("El equipo con ID " + equipoId + " no se encontró o no existe");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor");
        }
    }


    // Endpoint para obtener todos los equipos
    @GetMapping("/todos")
    public ResponseEntity<?> obtenerTodosLosEquipos() {
        try {
            List<Equipo> equipos = equipoService.obtenerTodosLosEquipos();
            return ResponseEntity.ok(equipos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }
    }

}
