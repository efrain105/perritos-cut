package com.cut.cardona.controllers;
import com.cut.cardona.modelo.Usuarios.DatosAutenticacionUsuario;
import com.cut.cardona.modelo.Usuarios.Usuario;
import com.cut.cardona.security.DatosJWTToken;
import com.cut.cardona.security.PasswordService;
import com.cut.cardona.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login-api")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    //test
    @Autowired
    PasswordService passwordService ;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication authenticationToken  = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.userName(), datosAutenticacionUsuario.password());
        Authentication authenticationToken2  = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.email(), datosAutenticacionUsuario.password());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        var JWTtoken = tokenService.generarToken((Usuario) authenticate.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

    /*@GetMapping
    public void test() {
        System.out.println(passwordService.comprobarContrasenia("efra105", "123456"));

    }*/

}
