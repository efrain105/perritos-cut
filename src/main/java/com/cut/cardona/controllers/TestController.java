package com.cut.cardona.controllers;

import com.cut.cardona.modelo.Usuarios.Roles.RepositorioRoles;
import com.cut.cardona.modelo.Usuarios.RepositorioUsuario;
import com.cut.cardona.modelo.Usuarios.Usuario;
import com.cut.cardona.security.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private RepositorioRoles rerpositorioRoles;
    @Autowired
    private PasswordService passwordService;


    @GetMapping("/test")
    void testMain() {

/*
        Usuario usuario = new Usuario();
        usuario.setUserName("testUser");
        usuario.setEmail("testUser@example.com");
        usuario.setPassword("password");

        // Set fechaCreacion and ultimoAcceso to the current timestamp
        Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
        usuario.setFechaCreacion(currentTimestamp);
        usuario.setUltimoAcceso(currentTimestamp);
        usuario.setRoles(rerpositorioRoles.findByNombre("ROLE_USER"));
        usuario.setActivo(true);
        // Save the user (assuming save method exists)
        repositorioUsuario.save(usuario);*/

        // Retrieve the user (assuming findByUserName method exists)
        UserDetails testUser = repositorioUsuario.findByUserNameOrEmail("efra105", "efra105");
        Usuario retrievedUser = (Usuario) testUser;
        if (retrievedUser != null) {
            System.out.println("Retrieved User: " + retrievedUser.getEmail());
        } else {
            System.out.println("User not found");
        }

        boolean b = passwordService.comprobarContrasenia("ebaudice@bbb.org", "123456");
        if (b) {
            System.out.println("Password is correct");
        } else {
            System.out.println("Password is incorrect");
        }


    }
    }