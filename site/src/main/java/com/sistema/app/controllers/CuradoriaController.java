package com.sistema.app.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.models.Solicitacao;
import com.sistema.models.Usuario;
import com.sistema.percistence.Repositories.SolicitacaoRepository;

import jakarta.servlet.http.HttpSession;

@Controller 
@RequestMapping("/curadoria")
public class CuradoriaController {

    private SolicitacaoRepository solicitacaoRepository = new SolicitacaoRepository();
    
    @GetMapping("/home")
    public String fornecerPaginaDeCuradoria(HttpSession sessao, Model m){
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");

        m.addAttribute("u", usuario);
        return "curadoria_home.html";
    }

    @GetMapping("/solicitacoes")
    public String fornecerPaginaDeAvaliacaoDeSolicitacoes(HttpSession sessao, Model m) throws SQLException{
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        ArrayList<Solicitacao> solicitacoes = solicitacaoRepository.findAll("");

        m.addAttribute("u", usuario);
        m.addAttribute("solicitacoes", solicitacoes);
        return "solicitacoes_avaliacao.html";
    }

}
