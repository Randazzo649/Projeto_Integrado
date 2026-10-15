package com.sistema.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.models.Usuario;
import com.sistema.persistence.Repositories.UsuarioRepository;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private UsuarioRepository ru;

    @GetMapping("/perfil")
    public String fornecerPaginaDePerfil(HttpSession sessao, Model m){
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        m.addAttribute("u", usuario);
        return "personalizacao_usuario.html";
    }
}
