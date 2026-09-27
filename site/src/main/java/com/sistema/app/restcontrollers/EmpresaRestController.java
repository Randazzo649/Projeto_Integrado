package com.sistema.app.restcontrollers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.sistema.models.Solicitacao;
import com.sistema.percistence.configs.FilePersistenceConfig;
import com.sistema.security.HashConfigSingleton;

import jakarta.servlet.http.HttpSession;

import com.sistema.percistence.Repositories.SolicitacaoRepository;
import com.sistema.external.EmailSender;

@RestController
@RequestMapping("/empresa")
public class EmpresaRestController {

    SolicitacaoRepository sr = new SolicitacaoRepository();
    @Autowired EmailSender emailSender;
    
    @PostMapping("/solicitacao")
    public String cadastroDeSolicitacaoDeContaEmpresarial( @RequestParam("razao") String razao, @RequestParam("cnpj") String cnpj,  @RequestParam("telefone") String telefone, @RequestParam("endereco") String endereco,  @RequestParam("email") String email, @RequestParam("senha") String senha, @RequestParam("nome") String nome, @RequestParam("file") MultipartFile multiFile, @RequestParam("modulos") String modulos) throws IOException, SQLException {
        //declara as variaveis necessárias para a operação
        String[] modulosEscolhidos = modulos.split(","); 
        Solicitacao s = new Solicitacao();
        String out = FilePersistenceConfig.getInstance().getOutDocumentosSolicitacao();
        String caminhoArquivo = out + "/" + multiFile.getOriginalFilename();
        //valida as informacoes que necessitam de validação
        if(!FilePersistenceConfig.getInstance().isValido(caminhoArquivo))
            return "0";
        //define os dados iniciais
        s.setRazaoSocial(razao);
        s.setCnpj(cnpj);
        s.setTelefone(telefone);
        s.setEndereco(endereco);
        s.setEmail(email);
        s.setSenha(HashConfigSingleton.getInstance().hash(senha));
        s.setNome(nome);
        //define o local do documento
        multiFile.transferTo(new File(caminhoArquivo));
        s.setDocumento(caminhoArquivo);
        //salva a solicitação no banco de dados
        long id = sr.cadastrar(s);
        sr.adicionarModulos(modulosEscolhidos, id);
        return "1";
    }

    @PostMapping("/avaliacao")
    public String avaliarSolicitacao( @RequestParam("id") long id, HttpSession sessao ) throws SQLException{
        Solicitacao solicitacao = sr.findById(id);
        sr.registrarAprovacaoSolicitacao(solicitacao);
        String assunto = "Solicitação aprovada !!!";
        String msg = "Olá " + solicitacao.getEmail() + ", a UnitHub fica feliz em informar que sua solicitação foi aprovada !";
        emailSender.enviarEmail(solicitacao.getEmail(), assunto, msg);
        return "1";
    }
}
