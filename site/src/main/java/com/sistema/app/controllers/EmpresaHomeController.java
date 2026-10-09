package com.sistema.app.controllers;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.models.Empresa;
import com.sistema.models.Modulo;
import com.sistema.models.Usuario;
import com.sistema.percistence.Repositories.EmpresaRepository;
import com.sistema.percistence.Repositories.ModuloRepository;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/empresa")
public class EmpresaHomeController {

    private ModuloRepository mr = new ModuloRepository();
    private EmpresaRepository empresaRepository = new EmpresaRepository();

    @GetMapping("/home")
    public String fornecerPaginaDeNavegacaoDeMódulos(HttpSession sessao, Model m) throws SQLException{
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        
        Empresa e = (Empresa) empresaRepository.findById(u.getIdEmpresa());
        sessao.setAttribute("empresa", e);

        List<Modulo> modulos = mr.buscarPorEmpresa(u.getIdEmpresa());
        
        List<Long> modulos_ids = new ArrayList<>();
        for(Modulo modulo : modulos)
            modulos_ids.add(modulo.getId());

        m.addAttribute("u", u);
        m.addAttribute("e", e);
        m.addAttribute("modulos_ids", modulos_ids);
        return "navegacao_modulos.html";
    }

    @GetMapping("/personalizacao")
    public String fornecerPaginaDePersonalizacaoDeEmpresa(HttpSession sessao, Model m) throws SQLException {
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        Empresa e = (Empresa) empresaRepository.findById(u.getIdEmpresa());
        m.addAttribute("u", u);
        m.addAttribute("e", e);
        return "personalizacao_empresa.html";
    }
    
}

