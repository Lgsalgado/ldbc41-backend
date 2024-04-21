package com.example.ldbc41.models;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Roles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre_rol", nullable = true, length = 50)
    private String nombreRol;
    @OneToMany(mappedBy = "rolesByRolId")
    private Collection<Menu> menusById;
    @OneToMany(mappedBy = "rolesByRolId")
    private Collection<UsuarioRoles> usuarioRolesById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Collection<Menu> getMenusById() {
        return menusById;
    }

    public void setMenusById(Collection<Menu> menusById) {
        this.menusById = menusById;
    }

    public Collection<UsuarioRoles> getUsuarioRolesById() {
        return usuarioRolesById;
    }

    public void setUsuarioRolesById(Collection<UsuarioRoles> usuarioRolesById) {
        this.usuarioRolesById = usuarioRolesById;
    }
}
