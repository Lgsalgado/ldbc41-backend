package com.example.ldbc41.services;

import com.example.ldbc41.models.Equipo;
import com.example.ldbc41.models.Jugadore;
import com.example.ldbc41.repository.EquipoRepository;
import com.example.ldbc41.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private final EquipoRepository equipoRepository;
    @Autowired
    private final JugadorRepository jugadorRepository;
    @Autowired
    private JugadorService jugadorService;

    @Autowired
    public EquipoServiceImpl(
            EquipoRepository equipoRepository,
            JugadorRepository jugadorRepository
    ) {
        this.equipoRepository = equipoRepository;
        this.jugadorRepository= jugadorRepository;
    }
    @Override
    public Optional<Equipo> buscarEquipoPorId(Integer id) {
        return equipoRepository.findById(id);
    }
    @Override
    public boolean jugadorEstaEnEquipo(String cedula) {
        List<Equipo> equipos = equipoRepository.findAll();
        for (Equipo equipo : equipos) {
            for (Jugadore jugador : equipo.getJugadores()) {
                if (jugador.getCedula().equals(cedula)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void agregarJugadorAlEquipo(Integer equipoId, Jugadore jugador) {
        try {
            Equipo equipo = equipoRepository.findById(equipoId).orElseThrow(() -> new IllegalArgumentException("Equipo no encontrado"));
            if (!jugadorService.jugadorPerteneceAEquipo(jugador.getCedula())) {
                jugador.setEquipo(equipo);
                equipo.getJugadores().add(jugador);
                jugadorRepository.save(jugador);
                //equipoRepository.save(equipo);
            } else {
                throw new IllegalArgumentException("El jugador ya está en un equipo");
            }
        } catch (IllegalArgumentException e) {
            // Si el jugador ya está en un equipo, se captura la excepción y se devuelve el nombre del equipo
            if (e.getMessage().equals("El jugador ya está en un equipo")) {
                String equipoNombre = jugadorService.obtenerNombreEquipoDelJugador(jugador.getCedula());
                throw new IllegalArgumentException("El jugador ya está en el equipo " + equipoNombre);
            } else {
                // Se lanza la excepción si ocurre cualquier otro error
                throw new IllegalArgumentException("Error al agregar jugador al equipo");
            }
        }

    }

    // Método para obtener el nombre del equipo en el que se encuentra el jugador
    private String obtenerNombreEquipoDelJugador(String cedula) {
        List<Equipo> equipos = equipoRepository.findAll();
        for (Equipo equipo : equipos) {
            for (Jugadore jugador : equipo.getJugadores()) {
                if (jugador.getCedula().equals(cedula)) {
                    return equipo.getNombre();
                }
            }
        }
        return null;
    }
    @Override
    public Equipo agregarEquipo(Equipo equipo) {
        try {
            if (equipo.getNombre() == null || equipo.getNombre().isEmpty()) {
                throw new IllegalArgumentException("El nombre del equipo no puede estar vacío");
            }

            String nombreEquipoMayusculas = equipo.getNombre().toUpperCase();

            Equipo equipoExistente = equipoRepository.findByNombre(nombreEquipoMayusculas);
            if (equipoExistente != null) {
                throw new IllegalArgumentException("Ya existe un equipo con ese nombre");
            }

            equipo.setNombre(nombreEquipoMayusculas);
            return equipoRepository.save(equipo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error al agregar equipo: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error interno al agregar equipo", e);
        }
    }
}
