package com.example.ldbc41.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ArbitrosPartidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "arbitro_id", nullable = true)
    private Integer arbitroId;

    @Column(name = "partido_id", nullable = true)
    private Integer partidoId;

    @Column(name = "calificacion", nullable = false)
    private int calificacion;

    @ManyToOne
    @JoinColumn(name = "arbitro_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Arbitros arbitro;

    @ManyToOne
    @JoinColumn(name = "partido_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Partido partido;
}
