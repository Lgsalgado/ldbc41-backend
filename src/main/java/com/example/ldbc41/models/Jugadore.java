package com.example.ldbc41.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "jugadores", schema = "campeonato")
public class Jugadore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "cedula", length = 20)
    private String cedula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "nacionalidad", length = 50)
    private String nacionalidad;

    @Column(name = "numero_equipo")
    private Integer numeroEquipo;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "ultima_actuacion")
    private Integer ultimaActuacion;

}
