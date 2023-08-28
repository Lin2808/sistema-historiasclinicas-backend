package com.sistemahistoriasclinicasbackend.entidades;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="roles")
public class Rol {
    @Id
    private int idRol;
    private String nombre;
    private String descripcion;

    @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
    private Set<RolUsuario> rolUsuario = new HashSet<>();

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<RolUsuario> getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(Set<RolUsuario> rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Rol() {
    }
}
