package com.example.ldbc41.controllers;

import com.example.ldbc41.models.Goleadores;
import com.example.ldbc41.models.Partido;
import com.example.ldbc41.services.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    private final PartidoService partidoService;

    @Autowired
    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }
    /*--------
    * agrega los partidos
    *
    * */
    @PostMapping("/agregar")
    public ResponseEntity<?> agregarPartido(@RequestBody Partido partido) {
        try {
            partidoService.agregarPartido(partido);
            return new ResponseEntity<>(partido, HttpStatus.CREATED);
       } catch (IllegalArgumentException e) {
            // Manejar el caso cuando no se encuentra el equipo local
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al agregar partido: " + e.getMessage());
        } catch (Exception e) {
            // Manejar otros errores internos
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al procesar la solicitud");
        }
    }
    //agrega los goles de cada partido
    @PostMapping("/{partidoId}/goleadores/agregar")
    public ResponseEntity<?> agregarGoleadoresDePartido(@PathVariable int partidoId, @RequestBody List<Goleadores> goleadores) {
        try {
            partidoService.agregarGoleadoresDePartido(partidoId, goleadores);
            return new ResponseEntity<>("Goleadores agregados correctamente", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Manejar el caso cuando el partido no se encuentra
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al agregar goleadores: " + e.getMessage());
        } catch (Exception e) {
            // Manejar otros errores internos
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al procesar la solicitud");
        }
    }
}
