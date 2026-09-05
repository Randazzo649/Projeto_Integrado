package com.sistema.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LandingPageController{


    @GetMapping("/index")
    public String fornecerLandingPage(){
        return "index.html";
    }

    @GetMapping("/solicitacao_cadastro/dados")
    public String fornecerPaginaDeSolicitacaoDeCadastroDeEmpresa(){
        return "solicitacao_cadastro.html";
    }

}