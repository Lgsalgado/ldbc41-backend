package com.example.ldbc41.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tabla_posiciones", schema = "campeonato", catalog = "ldbc41Pruebas")
public class TablaPosiciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "equipo_id", nullable = true)
    private Integer equipoId;

    @Column(name = "periodo_campeonato", nullable = true)
    private Integer periodoCampeonato;

    @Column(name = "etapa_partido", nullable = true)
    private Integer etapaPartido;

    @Column(name = "puntos_obtenidos", nullable = true)
    private Integer puntosObtenidos;

    @Column(name = "partidos_jugados", nullable = true)
    private Integer partidosJugados;

    @Column(name = "partidos_ganados", nullable = true)
    private Integer partidosGanados;

    @Column(name = "partidos_empatados", nullable = true)
    private Integer partidosEmpatados;

    @Column(name = "partidos_perdidos", nullable = true)
    private Integer partidosPerdidos;

    @Column(name = "goles_a_favor", nullable = true)
    private Integer golesAFavor;

    @Column(name = "goles_en_contra", nullable = true)
    private Integer golesEnContra;

    @Column(name = "diferencia_de_goles", nullable = true)
    private Integer diferenciaDeGoles;

    @ManyToOne
    @JoinColumn(name = "equipo_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private Equipo equipo;
}
