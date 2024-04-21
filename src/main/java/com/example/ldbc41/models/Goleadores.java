package com.example.ldbc41.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "goleadores", schema = "campeonato")
public class Goleadores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "jugador_id", referencedColumnName = "id")
    private Jugadore jugador;

    @ManyToOne
    @JoinColumn(name = "equipo_id", referencedColumnName = "id")
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "partido_id", referencedColumnName = "id")
    private Partido partido;

    @Column(name = "etapa_partido", nullable = true)
    private Integer etapaPartido;

    @Column(nullable = true)
    private Integer periodo;

    @Column(nullable = true)
    private Integer goles;
}
