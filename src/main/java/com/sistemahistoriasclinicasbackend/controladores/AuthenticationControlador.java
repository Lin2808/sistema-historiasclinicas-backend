package com.sistemahistoriasclinicasbackend.controladores;

import com.sistemahistoriasclinicasbackend.configuraciones.JwtUtils;
import com.sistemahistoriasclinicasbackend.entidades.JwtRequest;
import com.sistemahistoriasclinicasbackend.entidades.JwtResponse;
import com.sistemahistoriasclinicasbackend.servicios.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class AuthenticationControlador {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception
    {
        try
        {
            autenticar(jwtRequest.getUserName(), jwtRequest.getPassword());

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUserName());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticar(String userName, String password) throws Exception
    {
        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        }
        catch(DisabledException disabledException)
        {
            throw new Exception("Usuario deshabilitado " + disabledException.getMessage());
        }
        catch(BadCredentialsException badCredentialsException)
        {
            throw new Exception("Credenciales inválidas " + badCredentialsException.getMessage());
        }
    }
}
