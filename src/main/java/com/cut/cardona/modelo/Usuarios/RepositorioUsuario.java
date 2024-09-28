package com.cut.cardona.modelo.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.userName = :username OR u.email = :email")
    UserDetails findByUserNameOrEmail(@Param("username") String username, @Param("email") String email);

    Usuario findByUserName(String username);

    Usuario findByEmail(String email);
}
