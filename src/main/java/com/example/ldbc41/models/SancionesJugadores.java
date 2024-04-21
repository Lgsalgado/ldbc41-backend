package com.example.ldbc41.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "sanciones_jugadores", schema = "campeonato", catalog = "ldbc41Pruebas")
public class SancionesJugadores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "jugador_id", nullable = true)
    private Integer jugadorId;

    @Column(name = "partido_id", nullable = true)
    private Integer partidoId;

    @Column(name = "tipo_sancion", nullable = true, length = 20)
    private String tipoSancion;

    @Column(name = "minuto", nullable = true)
    private Integer minuto;

    @Column(name = "otros_sanciones", nullable = true, length = 255)
    private String otrosSanciones;

    @Column(name = "num_partidos_sancionados", nullable = true)
    private Integer numPartidosSancionados;

    @Column(name = "tarjetas_amarillas", nullable = true)
    private Integer tarjetasAmarillas;

    @Column(name = "tarjetas_rojas", nullable = true)
    private Integer tarjetasRojas;

    @ManyToOne
    @JoinColumn(name = "jugador_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Jugadore jugador;

    @ManyToOne
    @JoinColumn(name = "partido_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Partido partido;
}
