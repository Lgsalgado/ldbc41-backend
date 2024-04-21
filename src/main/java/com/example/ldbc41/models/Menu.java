package com.example.ldbc41.models;

import com.example.ldbc41.models.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nombre", nullable = true, length = 100)
    private String nombre;

    @Column(name = "url", nullable = true, length = 255)
    private String url;

    @Column(name = "rol_id", nullable = true)
    private Integer rolId;

    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Roles rolesByRolId;
}
