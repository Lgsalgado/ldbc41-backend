package com.example.ldbc41.services;

import com.example.ldbc41.models.Equipo;
import com.example.ldbc41.models.Jugadore;
import com.example.ldbc41.repository.EquipoRepository;
import com.example.ldbc41.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
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
    final GoogleCloudStorageService googleCloudStorageService;

    @Value("${var.bucket}")
    private String bucket;

    @Autowired
    public EquipoServiceImpl(
            EquipoRepository equipoRepository,
            JugadorRepository jugadorRepository, GoogleCloudStorageService googleCloudStorageService
    ) {
        this.equipoRepository = equipoRepository;
        this.jugadorRepository= jugadorRepository;
        this.googleCloudStorageService = googleCloudStorageService;
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

    // Implementación del método para obtener todos los equipos
    @Override
    public List<Equipo> obtenerTodosLosEquipos() {
        return equipoRepository.findAllByOrderByNombreAsc();
    }


    public Jugadore guardarJugador(
            String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad,
            int numeroEquipo, String cedula, Integer equipoId,
            MultipartFile foto, MultipartFile cedulaImg) throws IOException {

        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        String urlFoto= googleCloudStorageService.uploadFile(foto,bucket);
        String urlFotoCedula= googleCloudStorageService.uploadFile(cedulaImg,bucket);

        Jugadore jugador = new Jugadore();
        jugador.setNombre(nombre);
        jugador.setApellido(apellido);
        jugador.setFechaNacimiento(fechaNacimiento);
        jugador.setNacionalidad(nacionalidad);
        jugador.setNumeroEquipo(numeroEquipo);
        jugador.setCedula(cedula);
        jugador.setEquipo(equipo);
        jugador.setFoto(urlFoto);
        jugador.setCedulaImg(urlFotoCedula);
        jugador.setEquipo(equipo);
        jugador.setEstado("activo");
        jugador.setUltimaActuacion(LocalDate.now().getYear());

        return jugadorRepository.save(jugador);
    }

    @Override
    public void agregarJugadorAlEquipo(Integer equipoId, Jugadore jugador) {
        try {
            // Buscar el equipo por ID, lanzar excepción si no se encuentra
            Equipo equipo = equipoRepository.findById(equipoId)
                    .orElseThrow(() -> new IllegalArgumentException("Equipo no encontrado"));

            // Verificar si el jugador ya pertenece a un equipo
            if (!jugadorService.jugadorPerteneceAEquipo(jugador.getCedula())) {

                // Validar que el número de jugador no se repita dentro del equipo
                for (Jugadore existente : equipo.getJugadores()) {
                    if (existente.getNumeroEquipo().equals(jugador.getNumeroEquipo())) {
                        throw new IllegalArgumentException("Ya existe un jugador con el número " + jugador.getNumeroEquipo()
                                + " en este equipo : "+existente.getNombre()+" "+existente.getApellido());
                    }
                }
                // Asignar el equipo al jugador y guardarlo
                jugador.setEquipo(equipo);
                equipo.getJugadores().add(jugador);
                jugador.setEstado("activo");
                jugador.setUltimaActuacion(LocalDate.now().getYear());
                jugadorRepository.save(jugador);
            } else {
                // Obtener el nombre del equipo actual del jugador y lanzar excepción con ese detalle
                String equipoNombre = jugadorService.obtenerNombreEquipoDelJugador(jugador.getCedula());
                throw new IllegalArgumentException("El jugador con cédula: "+jugador.getCedula()+" ya está en el equipo " + equipoNombre);
            }
        } catch (IllegalArgumentException e) {
            // Re-lanzar la excepción para que el controlador la maneje
            throw e;
        } catch (Exception e) {
            // En caso de cualquier otro error inesperado, lanzar una excepción genérica
            throw new IllegalArgumentException("Error al agregar jugador al equipo");
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
