package com.sistemahistoriasclinicasbackend.servicios.impl;

import com.sistemahistoriasclinicasbackend.entidades.RolUsuario;
import com.sistemahistoriasclinicasbackend.entidades.Usuario;
import com.sistemahistoriasclinicasbackend.repositorios.RolRepositorio;
import com.sistemahistoriasclinicasbackend.repositorios.UsuarioRepositorio;
import com.sistemahistoriasclinicasbackend.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UsuarioServicioImpl implements UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<RolUsuario> rolesUsuario) throws Exception {
        Usuario usuarioLocal = usuarioRepositorio.findByNombre(usuario.getNombre());
        if(usuarioLocal != null)
        {
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya está presente");
        }
        else
        {
            for(RolUsuario rolUsuario:rolesUsuario)
            {
                rolRepositorio.save(rolUsuario.getRol());
            }
            usuario.getRolUsuario().addAll(rolesUsuario);
            usuarioLocal = usuarioRepositorio.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String nombre) {
        return usuarioRepositorio.findByNombre(nombre);
    }

    @Override
    public void eliminarUsuario(Integer idUsuario) {
        usuarioRepositorio.deleteById(idUsuario);
    }
}
