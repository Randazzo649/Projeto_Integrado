package com.sistema.app.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;

import com.sistema.percistence.Repositories.FuncionarioRepository;
import com.sistema.percistence.Repositories.DepartamentoRepository;
import com.sistema.models.Empresa;
import com.sistema.models.Funcionario;
import com.sistema.models.Usuario;


@Controller 
@RequestMapping("/rh")
public class RHController {
    
    private FuncionarioRepository fr = new FuncionarioRepository();

    @GetMapping("/relatorio_funcionarios")
    public String fornecerPaginaDeCadastroDeFuncionario( Model m, HttpSession sessao ){
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        Empresa e = (Empresa) sessao.getAttribute("empresa");
        m.addAttribute("u", u);
        m.addAttribute("e", e);
        return "relatorio_funcionarios.html";
    }

    @GetMapping("/quadro_funcionarios")
    public String fornecerPaginaDeRelatorioDeFuncionarios(Model m, HttpSession sessao) throws SQLException{
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        Empresa e = (Empresa) sessao.getAttribute("empresa");

        List<Funcionario> funcionarios = fr.findAll("WHERE id_empresa = "+e.getId());

        m.addAttribute("funcs", funcionarios);
        m.addAttribute("u", u);
        m.addAttribute("e", e);
        return "quadro_funcionarios.html";
    }

    @GetMapping("/cadastro_funcionario")
    public String fornecerPaginaDeCadastroDeFuncionarios(Model m, HttpSession sessao){
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        Empresa e = (Empresa) sessao.getAttribute("empresa");
        m.addAttribute("u", u);
        m.addAttribute("e", e);
        return "cadastro_funcionario.html";
    }

}
