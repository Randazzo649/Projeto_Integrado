package com.sistema.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
@RequestMapping("/curadoria")
public class CuradoriaController {
    
    @GetMapping("/home")
    public String fornecerPaginaDeCuradoria(){
        return "curadoria_home.html";
    }

}
