package com.sistema.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;

import com.sistema.models.Empresa;
import com.sistema.models.Usuario;

@Controller 
@RequestMapping("/rh")
public class RHController {
    
    @GetMapping("/relatorio_funcionarios")
    public String fornecerPaginaDeCadastroDeFuncionario( Model m, HttpSession sessao ){
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        Empresa e = (Empresa) sessao.getAttribute("empresa");
        m.addAttribute("u", u);
        m.addAttribute("e", e);
        return "relatorio_funcionarios.html";
    }

    @GetMapping("/quadro_funcionarios")
    public String fornecerPaginaDeRelatorioDeFuncionarios(Model m, HttpSession sessao){
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        Empresa e = (Empresa) sessao.getAttribute("empresa");
        m.addAttribute("u", u);
        m.addAttribute("e", e);
        return "quadro_funcionarios.html";
    }

}
