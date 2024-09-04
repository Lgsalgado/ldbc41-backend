package com.example.ldbc41.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "paises", schema = "campeonato")
public class Pais {

    @Id
    @Column(name = "codigopais", nullable = false, length = 2)
    private String codigoPais;

    @Column(name = "nombrepais", nullable = false, length = 80)
    private String nombrePais;
}
