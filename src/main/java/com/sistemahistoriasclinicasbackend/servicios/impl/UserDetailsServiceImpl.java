package com.sistemahistoriasclinicasbackend.servicios.impl;


import com.sistemahistoriasclinicasbackend.entidades.Usuario;
import com.sistemahistoriasclinicasbackend.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepositorio.findByNombre(username);
        if(usuario == null)
        {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return usuario;
    }
}
