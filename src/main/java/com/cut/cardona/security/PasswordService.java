package com.cut.cardona.security;

import com.cut.cardona.modelo.Usuarios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    public boolean comprobarContrasenia(String username, String rawPassword) {
        UserDetails usuario = usuarioRepositorio.findByUserNameAndEmail(username, username);

        if (usuario != null) {
            return passwordEncoder.matches(rawPassword, usuario.getPassword());
        }
        return false;
    }
}
