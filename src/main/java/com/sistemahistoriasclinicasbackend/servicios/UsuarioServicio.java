package com.sistemahistoriasclinicasbackend.servicios;

import com.sistemahistoriasclinicasbackend.entidades.RolUsuario;
import com.sistemahistoriasclinicasbackend.entidades.Usuario;

import java.util.Set;
public interface UsuarioServicio {
    public Usuario guardarUsuario(Usuario usuario, Set<RolUsuario> rolUsuario) throws Exception;
    public Usuario obtenerUsuario(String nombre);

    public void eliminarUsuario(Integer idUsuario);
}
