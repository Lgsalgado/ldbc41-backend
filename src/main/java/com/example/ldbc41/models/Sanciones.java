package com.example.ldbc41.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Sanciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "tipo_sancion", nullable = true, length = 20)
    private String tipoSancion;

    @Column(name = "descripcion", nullable = true, length = 255)
    private String descripcion;

    @Column(name = "jugador_id", nullable = true)
    private Integer jugadorId;

    @Column(name = "equipo_id", nullable = true)
    private Integer equipoId;

    @ManyToOne
    @JoinColumn(name = "jugador_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Jugadore jugador;

    @ManyToOne
    @JoinColumn(name = "equipo_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Equipo equipo;
}
