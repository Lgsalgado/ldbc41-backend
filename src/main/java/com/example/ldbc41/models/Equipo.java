package com.example.ldbc41.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @OneToMany(mappedBy = "equipo", fetch = FetchType.LAZY)
    @JsonIgnore // Ignora la serialización de la colección jugadores
    private Collection<Jugadore> jugadores;

    @OneToMany(mappedBy = "equipo", fetch = FetchType.LAZY)
    @JsonIgnore // Ignora la serialización de la colección goleadores
    private Collection<Goleadores> goleadores;

    @OneToMany(mappedBy = "equipoLocal", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Partido> partidosLocal;

    @OneToMany(mappedBy = "equipoVisitante", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Partido> partidosVisitante;

    @OneToMany(mappedBy = "equipo", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<SancionesEquipos> sancionesEquipos;

    @OneToMany(mappedBy = "equipo", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<TablaPosiciones> tablaPosiciones;

    @OneToMany(mappedBy = "equipo", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Sanciones> sanciones;
}
