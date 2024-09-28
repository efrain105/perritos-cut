package com.cut.cardona;

import com.cut.cardona.modelo.Usuarios.RepositorioUsuario;
import com.cut.cardona.modelo.Usuarios.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
class CutApplicationTests {
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Test
    void contextLoads() {
    }

    @Test
    void testMain() {
        Usuario usuario = new Usuario();
        usuario.setUserName("testUser");
        usuario.setEmail("testUser@example.com");
        usuario.setPassword("password");

        // Save the user (assuming save method exists)
        repositorioUsuario.save(usuario);

        // Retrieve the user (assuming findByUserName method exists)
        UserDetails testUser = repositorioUsuario.findByUserNameOrEmail("testUser", "testUser@example.com");
        Usuario retrievedUser = (Usuario) testUser;
        System.out.println("Retrieved User: " + retrievedUser.getUsername());
    }

}
