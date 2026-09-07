package com.sistema.app.restcontrollers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.sistema.models.Solicitacao;
import com.sistema.percistence.configs.FilePersistenceConfig;
import com.sistema.percistence.Repositories.SolicitacaoRepository;

@RestController
@RequestMapping("/empresa")
public class EmpresaRestController {

    @PostMapping("/solicitacao")
    public String cadastroDeSolicitacaoDeContaEmpresarial( @RequestParam("razao") String razao, @RequestParam("cnpj") String cnpj,  @RequestParam("telefone") String telefone, @RequestParam("endereco") String endereco,  @RequestParam("email") String email, @RequestParam("senha") String senha, @RequestParam("nome") String nome, @RequestParam("file") MultipartFile multiFile) throws IOException, SQLException {
        //declara as variaveis necessárias para a operação
        SolicitacaoRepository sr = new SolicitacaoRepository();
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
        s.setSenha(senha);
        s.setNome(nome);
        //define o local do documento
        multiFile.transferTo(new File(caminhoArquivo));
        s.setDocumento(caminhoArquivo);
        //salva a solicitação no banco de dados
        sr.cadastrar(s);
        return "1";
    }
}
