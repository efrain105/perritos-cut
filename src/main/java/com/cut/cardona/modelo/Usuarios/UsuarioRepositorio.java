package com.cut.cardona.modelo.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    UserDetails findByUserNameAndEmail(String username, String email);
}
