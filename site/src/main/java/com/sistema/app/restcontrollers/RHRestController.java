package com.sistema.app.restcontrollers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

import com.sistema.models.Funcionario;
import com.sistema.models.Empresa;
import com.sistema.percistence.Repositories.FuncionarioRepository;
import com.sistema.security.HashConfigSingleton;
import com.sistema.generation.relatorioTemplates.RelatorioRH;


@RestController 
@RequestMapping("/rh")
public class RHRestController {

    private FuncionarioRepository fr = new FuncionarioRepository();
    private RelatorioRH relatorioRH;
    
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

    @GetMapping("/relatorio")
    public  ResponseEntity<byte[]> gerarRelatorioFuncionarios(HttpSession sessao) throws SQLException{
        //carrega os funcionarios
        Empresa e = (Empresa) sessao.getAttribute("empresa");
        List<Funcionario> funcionarios = fr.findAll("WHERE id_empresa = "+e.getId());
        //gera o relatório
        relatorioRH = new RelatorioRH(funcionarios);
        byte[] pdf = relatorioRH.gerar();

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-rh.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdf);
    }

}
