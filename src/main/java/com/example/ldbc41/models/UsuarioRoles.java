package com.example.ldbc41.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario_roles", schema = "seguridad", catalog = "ldbc41Pruebas")
public class UsuarioRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "usuario_id", nullable = true)
    private Integer usuarioId;

    @Column(name = "rol_id", nullable = true)
    private Integer rolId;

    // Relación Many-to-One con Usuarios
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuarios usuariosByUsuarioId;

    // Relación Many-to-One con Roles
    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Roles rolesByRolId;
}
