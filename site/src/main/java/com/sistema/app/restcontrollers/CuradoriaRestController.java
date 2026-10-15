package com.sistema.app.restcontrollers;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.persistence.Repositories.SolicitacaoRepository;

@RequestMapping("/curadoria")
@RestController 
public class CuradoriaRestController {

    private SolicitacaoRepository sr = new SolicitacaoRepository();
    
    @PostMapping("/deletar_solicitacao")
    public String deletarSolicitacao( @RequestParam("id") long id) throws SQLException{
        sr.excluirSolicitacao(id);
        return "1";
    }

}
