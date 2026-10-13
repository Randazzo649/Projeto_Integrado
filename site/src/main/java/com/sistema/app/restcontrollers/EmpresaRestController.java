package com.sistema.app.restcontrollers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

import com.sistema.models.Solicitacao;
import com.sistema.persistence.Repositories.EmpresaRepository;
import com.sistema.persistence.Repositories.SolicitacaoRepository;
import com.sistema.security.HashConfigSingleton;
import com.sistema.security.validadors.SolicitacaoValidatorStrategy;
import com.sistema.external.email.EmailSender;
import com.sistema.external.email.messageTemplates.EmailAprovacao;
import com.sistema.persistence.FilePersistenceStrategies.PdfPersistenceStrategy;
import com.sistema.persistence.FilePersistenceStrategies.PngPersistenceStrategy;
import com.sistema.external.email.messageTemplates.EmailReprovacao;
import com.sistema.models.Empresa;


@RestController
@RequestMapping("/empresa")
public class EmpresaRestController {

    private SolicitacaoRepository sr = new SolicitacaoRepository();
    private EmpresaRepository er = new EmpresaRepository();
    private SolicitacaoValidatorStrategy svs = new SolicitacaoValidatorStrategy();
    private PdfPersistenceStrategy pdfStrategy = new PdfPersistenceStrategy();
    private PngPersistenceStrategy pngStrategy = new PngPersistenceStrategy();
    @Autowired private EmailSender emailSender;
    
    @PostMapping("/solicitacao")
    public String cadastroDeSolicitacaoDeContaEmpresarial( @RequestParam("razao") String razao, @RequestParam("cnpj") String cnpj,  @RequestParam("telefone") String telefone, @RequestParam("endereco") String endereco,  @RequestParam("email") String email, @RequestParam("senha") String senha, @RequestParam("nome") String nome, @RequestParam("file") MultipartFile multiFile, @RequestParam("modulos") String modulos) throws IOException, SQLException {
        String[] modulosEscolhidos = modulos.split(",");
        Solicitacao s = new Solicitacao();

        s.setRazaoSocial(razao);
        s.setCnpj(cnpj);
        s.setTelefone(telefone);
        s.setEndereco(endereco);
        s.setEmail(email);
        s.setSenha(HashConfigSingleton.getInstance().hash(senha));
        s.setNome(nome);

        if (!svs.isValido(s))
            return "o formulário foi preenchido de forma inválida, cheque os dados fornecidos ou tente novamente";

        s.setDocumento(pdfStrategy.salvar(multiFile));

        long id = sr.cadastrar(s);
        sr.adicionarModulos(modulosEscolhidos, id);

        return "1";
    }

    @PostMapping("/avaliacao_aprovacao")
    public String aprovarSolicitacao( @RequestParam("id") long id, HttpSession sessao ) throws SQLException, MessagingException{
        Solicitacao solicitacao = sr.findById(id);
        sr.registrarAprovacaoSolicitacao(solicitacao);
        String assunto = "Solicitação aprovada !!!";
        String msg = new EmailAprovacao(solicitacao.getEmail(), solicitacao.getRazaoSocial()).gerarEmail();
        emailSender.enviarEmail(solicitacao.getEmail(), assunto, msg);
        return "1";
    }

    @PostMapping("/avaliacao_rejeicao")
    public String rejeitarSolicitacao(@RequestParam("id") long id, HttpSession sessao) throws SQLException, MessagingException{
        Solicitacao solicitacao = sr.findById(id);
        solicitacao.setAprovada(false);
        solicitacao.setDataDecisao(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        String assunto = "Sua solicitação foi rejeitada";
        String msg = new EmailReprovacao(solicitacao.getEmail(), solicitacao.getRazaoSocial()).gerarEmail();
        
        sr.salvarEstadoAtual(solicitacao);
        emailSender.enviarEmail(solicitacao.getEmail(), assunto, msg);
        return "1";
    }

    @PostMapping("/salvar_alteracoes")
    public String salvarAlteracoes(HttpSession sessao, @RequestParam(value="cor", required = false) String cor, @RequestParam(value="foto", required = false) MultipartFile logo) throws SQLException, IOException{
  
        Empresa empresa = (Empresa) sessao.getAttribute("empresa");

        if (cor != null)
            empresa.setCor(cor);

        if (logo != null && !logo.isEmpty()) {
            try {
                empresa.setFoto(pngStrategy.salvar(logo));
            } catch (IOException e) {
                return "0";
            }
        }

        er.salvarEstadoAtual(empresa);
        return "1";
    }

    @PostMapping ("/desativar")
    public String desativarEmpresa(HttpSession sessao, @RequestParam("id") long id) throws SQLException{
        Empresa empresa = (Empresa) er.findById(id);
        empresa.setAtivo(false);
        er.salvarEstadoAtual(empresa);
        return "1";
    }
}
