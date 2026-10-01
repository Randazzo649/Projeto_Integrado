package com.sistema.app.restcontrollers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

import com.sistema.models.Funcionario;
import com.sistema.models.Empresa;
import com.sistema.percistence.Repositories.FuncionarioRepository;
import com.sistema.security.HashConfigSingleton;


@RestController 
@RequestMapping("/rh")
public class RHRestController {

    FuncionarioRepository fr = new FuncionarioRepository();
    
    @PostMapping("cadastrar_funcionario")
    public String cadastrarFuncionarioNovo( HttpSession sessao, @RequestParam("nome") String nome, @RequestParam("data_nasc") String data_nasc, @RequestParam("cpf") String cpf, @RequestParam("genero") String genero, @RequestParam("telefone") String telefone, @RequestParam("endereco") String endereco, @RequestParam("data_adm") String data_adm, @RequestParam("cargo") String cargo, @RequestParam("departamento") String departamento, @RequestParam("salario") String salario, @RequestParam("email") String email, @RequestParam("senha") String senha) throws SQLException{

        Empresa e = (Empresa) sessao.getAttribute("empresa");
       
        //cria o objeto a ser persistido
        Funcionario f = new Funcionario();
        f.setNome(nome);
        f.setCargo(cargo);
        f.setData_admissao(data_adm);
        f.setEmail(email);
        f.setSenha(HashConfigSingleton.getInstance().hash(senha));
        f.setSalario(salario);
        f.setEstado("ativo");
        f.setData_nascimento(data_nasc);
        f.setGenero(genero);
        f.setTelefone(telefone);
        f.setIdEmpresa(e.getId());
        f.setCpf(cpf);
        f.setEndereco(endereco);
        
        fr.inserirFuncionario(f, departamento);

        return "1";
    }

}
