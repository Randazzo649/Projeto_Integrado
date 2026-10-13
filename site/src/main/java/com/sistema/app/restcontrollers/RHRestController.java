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
import com.sistema.security.validadors.FuncionarioValidatorStrategy;
import com.sistema.generation.relatorioTemplates.RelatorioRH;


@RestController 
@RequestMapping("/rh")
public class RHRestController {

    private FuncionarioRepository fr = new FuncionarioRepository();
    private FuncionarioValidatorStrategy fvs = new FuncionarioValidatorStrategy();
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
        
        if(!fvs.isValido(f))
            return "0";

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

    @PostMapping("/salvar_estado_atual")
    public String salvarEstadoAtualDeFuncionario( @RequestParam("id") long id, @RequestParam("nome") String nome, @RequestParam("data_nascimento") String data_nascimento, @RequestParam("cpf") String cpf, @RequestParam("genero") String genero, @RequestParam("telefone") String telefone, @RequestParam("endereco") String endereco, @RequestParam("data_admissao") String data_admissao, @RequestParam("cargo") String cargo, @RequestParam("departamento") String departamento, @RequestParam("salario") String salario, @RequestParam("email") String email) throws SQLException{

        Funcionario f = fr.findById(id);
        //não muda o departamento ainda
        f.setNome(nome);
        f.setData_nascimento(data_nascimento);
        f.setCpf(cpf);
        f.setGenero(genero);
        f.setTelefone(telefone);
        f.setEndereco(endereco);
        f.setData_admissao(data_admissao);
        f.setCargo(cargo);
        f.setSalario(salario);
        f.setEmail(email);

        if( !fvs.isValido(f) )
            return "0";

        fr.salvarEstadoAtual(f);
        
        return "1";
    }

    @PostMapping("/remover")
    public String deletarFuncionario(@RequestParam("id") long id) throws SQLException{
        boolean deletado = fr.delete(id);
        if(!deletado)
            return "0";
        return "1";
    }

}
