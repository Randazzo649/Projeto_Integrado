package com.sistema.app.restcontrollers;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

import com.sistema.persistence.FilePersistenceStrategies.PngPersistenceStrategy;
import com.sistema.persistence.Repositories.ProdutoRepository;
import com.sistema.models.Produto;
import com.sistema.models.Empresa;

@RestController 
@RequestMapping("/estoque")
public class EstoqueRestController {
    
    ProdutoRepository pr = new ProdutoRepository();
    PngPersistenceStrategy pngStrategy = new PngPersistenceStrategy();

    @PostMapping("/produto_cadastro")
    public String cadastrarNovoProduto( HttpSession sessao, @RequestParam("nome") String nome, @RequestParam("tipo") String tipo, @RequestParam("preco") float preco, @RequestParam("quantidade") int quantidade, @RequestParam("Unidae") String Unidae, @RequestParam(value="material",required=false) String material, @RequestParam(value="tamanho",required=false) String tamanho, @RequestParam(value="cor",required=false) String cor, @RequestParam("estoque") String estoque, @RequestParam("descricao") String descricao, @RequestParam("fotos") MultipartFile fotos) throws SQLException, IOException{
        
        Empresa e = (Empresa) sessao.getAttribute("empresa");
        Produto produto = new Produto();

        produto.setNome(nome);
        produto.setTipo(tipo);
        produto.setPreco(preco);
        produto.setQuantidade(quantidade);
        produto.setUnidadeMedida(Unidae);
        produto.setDescricao(descricao);
        if(material !=  null)
            produto.setMaterial(material);
        if(tamanho !=  null)
            produto.setTamanho(tamanho);
        if(cor !=  null)
            produto.setCor(cor);


        produto.setFoto(pngStrategy.salvar(fotos));
        pr.cadastrarProduto(produto, estoque, e.getId());

        return "1";
    }

}
