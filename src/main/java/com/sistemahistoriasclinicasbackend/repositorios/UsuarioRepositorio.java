package com.sistemahistoriasclinicasbackend.repositorios;

import com.sistemahistoriasclinicasbackend.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    public Usuario findByNombre(String nombreUsuario);
}