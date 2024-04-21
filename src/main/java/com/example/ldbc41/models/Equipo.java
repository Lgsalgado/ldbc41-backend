package com.example.ldbc41.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "equipos", schema = "campeonato")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = true, length = 100)
    private String colores;

    @Column(nullable = true, length = 100)
    private String lema;

    @Column(nullable = true, length = 100)
    private String nombre;

    @Column(nullable = true, length = 50)
    private String categoria;

    @OneToMany(mappedBy = "equipo")
    private Collection<Goleadores> goleadores;

    @OneToMany(mappedBy = "equipo")
    private Collection<Jugadore> jugadores;

    @OneToMany(mappedBy = "equipoLocal")
    private Collection<Partido> partidosLocal;

    @OneToMany(mappedBy = "equipoVisitante")
    private Collection<Partido> partidosVisitante;

    @OneToMany(mappedBy = "equipo")
    private Collection<SancionesEquipos> sancionesEquipos;

    @OneToMany(mappedBy = "equipo")
    private Collection<TablaPosiciones> tablaPosiciones;

    @OneToMany(mappedBy = "equipo")
    private Collection<Sanciones> sanciones;
}
