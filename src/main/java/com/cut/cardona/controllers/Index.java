package com.cut.cardona.controllers;

import com.cut.cardona.modelo.perros.DatosPerro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class Index {
    @GetMapping("/index")
    public String index(){

        return "index";
    }
}
