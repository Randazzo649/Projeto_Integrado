package com.sistema.app.restcontrollers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/Empresa")
public class EmpresaRestController {
    @PostMapping("/Cadastro")
    public String cadastro(@RequestParam("razao") String razao, @RequestParam("cnpj") String cnpj, 
    @RequestParam("telefone") String telefone, @RequestParam("endereco") String endereco, @RequestParam("email") String email,
    @RequestParam("senha") String senha, @RequestParam("nome") String nome, @RequestParam("file") MultipartFile file){
        return "";
    }
}
