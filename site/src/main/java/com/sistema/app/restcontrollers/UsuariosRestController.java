package com.sistema.app.restcontrollers;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sistema.models.Usuario;
import com.sistema.persistence.FilePersistenceStrategies.PngPersistenceStrategy;
import com.sistema.persistence.Repositories.UsuarioRepository;
import com.sistema.security.HashConfigSingleton;

import jakarta.servlet.http.HttpSession;

@RestController 
@RequestMapping("/usuarios")
public class UsuariosRestController {
    
    private UsuarioRepository usuarioRepository = new UsuarioRepository();
    private PngPersistenceStrategy pngPersistence = new PngPersistenceStrategy();


    @PostMapping("/login")
    public String autenticarUsuario(@RequestParam("email") String email, @RequestParam("senha") String senhaInformada, HttpSession sessao) throws SQLException{
        
        Usuario usuario = usuarioRepository.findByEmail(email);
        if(usuario == null)
            return "0";
        
        boolean autenticado = HashConfigSingleton.getInstance().verificar(usuario.getSenha(), senhaInformada);

        if (!autenticado)
            return "0";

        sessao.setAttribute("usuario", usuario);

        return usuario.isCurador() ? "/curadoria/home" : "/empresa/home";
    }

    @PostMapping("/edicao")
    public String alterarPerfil(HttpSession sessao, @RequestParam(value="foto", required = false) MultipartFile foto, @RequestParam("nome") String nome, @RequestParam("funcao") String funcao, @RequestParam("email") String Email, @RequestParam(value="senha", required=false) String senha) throws IOException {
        //ele não altera diretamente a seção,
        //só depois de garantir salvamento completo no banco de dados
        Usuario usuario_salvo_na_sessao = (Usuario) sessao.getAttribute("usuario");
        Usuario usuario_modificado = new Usuario();
        //atribui id, nome, email e funcao
        usuario_modificado.setId(usuario_salvo_na_sessao.getId());
        usuario_modificado.setNome(nome);
        usuario_modificado.setEmail(Email);
        usuario_modificado.setFuncao(funcao);
        //atribui senha nova caso haja, do contrario, a antiga
        HashConfigSingleton h = HashConfigSingleton.getInstance();
        senha = (senha == null || senha.isBlank()) ? usuario_salvo_na_sessao.getSenha() : h.hash(senha);
        usuario_modificado.setSenha(senha);
        //atribui ativo, idEmpresa e curador
        usuario_modificado.setAtivo(usuario_salvo_na_sessao.isAtivo());
        usuario_modificado.setIdEmpresa(usuario_salvo_na_sessao.getIdEmpresa());
        usuario_modificado.setCurador(usuario_salvo_na_sessao.isCurador());
        //salva a foto em uploads e salva o caminho para o BD
        if(foto != null){
            String caminho_bd = pngPersistence.salvar(foto);
            usuario_modificado.setFoto(caminho_bd);
        } else{
            usuario_modificado.setFoto(usuario_salvo_na_sessao.getFoto());
        }

        //salva no bd
        try{
            usuarioRepository.salvarEstadoAtual(usuario_modificado);
            sessao.setAttribute("usuario", usuario_modificado);
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return "0";
        }

        return "1";
    }

}
