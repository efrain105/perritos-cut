package com.cut.cardona.security;

import com.cut.cardona.modelo.Usuarios.RepositorioUsuario;
import com.cut.cardona.modelo.Usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public boolean comprobarContrasenia(String username, String rawPassword) {
        // Aquí obtienes tu entidad Usuario en lugar de UserDetails
        UserDetails usuario = repositorioUsuario.findByUserNameOrEmail(username, username);
        Usuario retrievedUser = (Usuario) usuario;
        if (retrievedUser != null) {
            // Compara la contraseña cruda con la encriptada en la base de datos
            return passwordEncoder.matches(rawPassword, usuario.getPassword());
        }
        return false;
    }
}
