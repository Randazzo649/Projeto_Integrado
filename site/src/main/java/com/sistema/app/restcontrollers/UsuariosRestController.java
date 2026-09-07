package com.sistema.app.restcontrollers;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.models.Usuario;
import com.sistema.percistence.Repositories.UsuarioRepository;
import com.sistema.percistence.configs.SecurityConfig;

import jakarta.servlet.http.HttpSession;

@RestController 
@RequestMapping("/usuarios")
public class UsuariosRestController {
    
    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    @PostMapping("/login")
    public String autenticarUsuario(@RequestParam("email") String email, @RequestParam("senha") String senhaInformada, HttpSession sessao) throws SQLException{
        
        Usuario usuario = usuarioRepository.findByEmail(email);
        if(usuario == null)
            return "0";
        boolean autenticado = SecurityConfig.getInstance().verificar(usuario.getSenha(), senhaInformada);

        if (!autenticado)
            return "0";

        sessao.setAttribute("usuario", usuario);

        return usuario.isCurador() ? "/curadoria/home" : "/empresa/home";
    }

}
