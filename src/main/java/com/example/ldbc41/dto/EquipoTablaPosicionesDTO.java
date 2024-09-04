package com.example.ldbc41.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoTablaPosicionesDTO {
    // Campos de la clase Equipo
    private Integer equipoId;
    private String colores;
    private String lema;
    private String nombre;
    private String categoria;

    // Campos de la clase TablaPosiciones
    private int tablaPosicionesId;
    private Integer periodoCampeonato;
    private Integer etapaPartido;
    private Integer puntosObtenidos;
    private Integer partidosJugados;
    private Integer partidosGanados;
    private Integer partidosEmpatados;
    private Integer partidosPerdidos;
    private Integer golesAFavor;
    private Integer golesEnContra;
    private Integer diferenciaDeGoles;

}
