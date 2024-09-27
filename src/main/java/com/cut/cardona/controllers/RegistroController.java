package com.cut.cardona.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroController {

    @GetMapping("registro")
    public String registrarUsuario() {
        return "registro";
    }

}
