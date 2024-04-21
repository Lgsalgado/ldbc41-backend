package com.example.ldbc41.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "usuarios") // Nombre de la tabla en la base de datos
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nombre_usuario", nullable = true, length = 100)
    private String nombreUsuario;

    @Column(name = "contrasena", nullable = true, length = 100)
    private String contrasena;

    // Relación One-to-Many con UsuarioRoles
    @OneToMany(mappedBy = "usuariosByUsuarioId")
    private Collection<UsuarioRoles> usuarioRolesById;
}
