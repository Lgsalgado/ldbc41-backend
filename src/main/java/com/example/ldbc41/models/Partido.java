package com.example.ldbc41.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "partidos", schema = "campeonato")
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private Date fecha;

    @Column(name = "resultado_local")
    private Integer resultadoLocal;

    @Column(name = "resultado_visitante")
    private Integer resultadoVisitante;

    @Column(name = "periodo_campeonato")
    private Integer periodoCampeonato;

    @Column(name = "etapa_partido")
    private Integer etapaPartido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_local_id")
    private Equipo equipoLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_visitante_id")
    private Equipo equipoVisitante;

    @OneToMany(mappedBy = "partido")
    private Collection<ArbitrosPartidos> arbitrosPartidos;

    @OneToMany(mappedBy = "partido")
    private Collection<Goleadores> goleadores;

    @OneToMany(mappedBy = "partido")
    private Collection<SancionesEquipos> sancionesEquipos;

    @OneToMany(mappedBy = "partido")
    private Collection<SancionesJugadores> sancionesJugadores;
}
