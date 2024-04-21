package com.example.ldbc41.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
public class Arbitros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = true, length = 100)
    private String nombre;

    @Column(name = "nacionalidad", nullable = true, length = 50)
    private String nacionalidad;

    @OneToMany(mappedBy = "arbitro")
    private Collection<ArbitrosPartidos> arbitrosPartidosById;
}
