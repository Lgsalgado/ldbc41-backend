package com.example.ldbc41.services;

import com.example.ldbc41.models.Equipo;
import com.example.ldbc41.models.Jugadore;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

public interface EquipoService {
    Equipo agregarEquipo(Equipo equipo);

    Optional<Equipo> buscarEquipoPorId(Integer id);

    void agregarJugadorAlEquipo(Integer equipoId, Jugadore jugador);

    boolean jugadorEstaEnEquipo(String cedula);

    // Método para obtener todos los equipos
    List<Equipo> obtenerTodosLosEquipos();


    public Jugadore guardarJugador(
            String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad,
            int numeroEquipo, String cedula, Integer equipoId,
            MultipartFile foto, MultipartFile cedulaImg) throws IOException;
}

