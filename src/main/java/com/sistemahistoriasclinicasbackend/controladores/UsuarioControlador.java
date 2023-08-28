package com.sistemahistoriasclinicasbackend.controladores;

import com.sistemahistoriasclinicasbackend.entidades.Rol;
import com.sistemahistoriasclinicasbackend.entidades.RolUsuario;
import com.sistemahistoriasclinicasbackend.entidades.Usuario;
import com.sistemahistoriasclinicasbackend.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception
    {
        Set<RolUsuario> rolesUsuario = new HashSet<>();

        Rol rol = new Rol();
        rol.setIdRol(2);
        rol.setNombre("estándar");

        RolUsuario rolUsuario = new RolUsuario();
        rolUsuario.setUsuario(usuario);
        rolUsuario.setRol(rol);

        rolesUsuario.add(rolUsuario);
        return usuarioServicio.guardarUsuario(usuario, rolesUsuario);
    }

    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String nombre)
    {
        return usuarioServicio.obtenerUsuario(nombre);
    }

    @DeleteMapping("/{idUsuario}")
    public void eliminarUsuario(@PathVariable("idUsuario") Integer idUsuario)
    {
        usuarioServicio.eliminarUsuario(idUsuario);
    }
}
