package com.sistema.app.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.models.Modulo;
import com.sistema.percistence.Repositories.ModuloRepository;


@Controller
@RequestMapping("/")
public class LandingPageController{

    private ModuloRepository mr = new ModuloRepository();

    @GetMapping("/")
    public String fornecerLandingPage(){
        return "index.html";
    }

    @GetMapping("/solicitacao_cadastro/dados")
    public String fornecerPaginaDeSolicitacaoDeCadastroDeEmpresa(Model m) throws SQLException{
        ArrayList<Modulo> modulos = mr.findAll("");
        m.addAttribute("modulos", modulos);
        return "solicitacao_cadastro.html";
    }

}