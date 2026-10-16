package com.sistema.persistence.Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sistema.models.Produto;
import com.sistema.persistence.RepositoryTemplate;

public class ProdutoRepository extends RepositoryTemplate<Produto> {

    public void cadastrarProduto(Produto produto, String nomeEstoque, long idEmpresa) throws SQLException {
        String sql = "CALL cadastrar_produto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = bd.conectarSql(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setFloat(2, produto.getPreco());
            stmt.setString(3, produto.getDescricao());
            stmt.setString(4, produto.getTipo());
            stmt.setInt(5, produto.getQuantidade());
            stmt.setString(6, produto.getUnidadeMedida());
            stmt.setString(7, produto.getFoto());
            stmt.setString(8, produto.getCategoria());
            stmt.setString(9, produto.getMaterial());
            stmt.setString(10, produto.getTamanho());
            stmt.setString(11, produto.getCor());
            stmt.setString(12, nomeEstoque);
            stmt.setLong(13, idEmpresa);
            stmt.execute();
        }
    }


    @Override
    protected String getInsertionString() {
        return "INSERT INTO Produto(nome, preco, descricao, tipo, quantidade, unidade_medida, categoria, material, tamanho, cor, id_estoque) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateString() {
        return "UPDATE Produto SET nome = ?, preco = ?, descricao = ?, tipo = ?, quantidade = ?, unidade_medida = ?, categoria = ?, material = ?, tamanho = ?, cor = ?, id_estoque = ? WHERE id = ?";
    }

    @Override
    protected String getDeleteString() {
        return "DELETE FROM Produto WHERE id = ?";
    }

    @Override
    protected String getSelectByIdString() {
        return "SELECT * FROM Produto WHERE id = ?";
    }

    @Override
    protected String getSelectAllString(String filtro) {
        return "SELECT * FROM Produto " + filtro;
    }

    @Override
    protected void inserirInsertionParametros(PreparedStatement stmt, Produto entity) throws SQLException {
        stmt.setString(1, entity.getNome());
        stmt.setFloat(2, entity.getPreco());
        stmt.setString(3, entity.getDescricao());
        stmt.setString(4, entity.getTipo());
        stmt.setInt(5, entity.getQuantidade());
        stmt.setString(6, entity.getUnidadeMedida());
        stmt.setString(7, entity.getCategoria());
        stmt.setString(8, entity.getMaterial());
        stmt.setString(9, entity.getTamanho());
        stmt.setString(10, entity.getCor());
        stmt.setLong(11, entity.getIdEstoque());
    }

    @Override
    protected void inserirUpdateParametros(PreparedStatement stmt, Produto entity) throws SQLException {
        stmt.setString(1, entity.getNome());
        stmt.setFloat(2, entity.getPreco());
        stmt.setString(3, entity.getDescricao());
        stmt.setString(4, entity.getTipo());
        stmt.setInt(5, entity.getQuantidade());
        stmt.setString(6, entity.getUnidadeMedida());
        stmt.setString(7, entity.getCategoria());
        stmt.setString(8, entity.getMaterial());
        stmt.setString(9, entity.getTamanho());
        stmt.setString(10, entity.getCor());
        stmt.setLong(11, entity.getIdEstoque());
        stmt.setLong(12, entity.getId());
    }

    @Override
    protected Produto getClassFromResultSet(ResultSet rs) throws SQLException {
        Produto p = new Produto();
        p.setId(rs.getLong("id"));
        p.setNome(rs.getString("nome"));
        p.setPreco(rs.getFloat("preco"));
        p.setDescricao(rs.getString("descricao"));
        p.setTipo(rs.getString("tipo"));
        p.setQuantidade(rs.getInt("quantidade"));
        p.setUnidadeMedida(rs.getString("unidade_medida"));
        p.setCategoria(rs.getString("categoria"));
        p.setMaterial(rs.getString("material"));
        p.setTamanho(rs.getString("tamanho"));
        p.setCor(rs.getString("cor"));
        p.setIdEstoque(rs.getLong("id_estoque"));
        return p;
    }

    @Override
    protected String toJson(ResultSet rs) throws SQLException {
        StringBuilder json = new StringBuilder("{");
        json.append("\"id\": ").append(rs.getLong("id")).append(", ");
        json.append("\"nome\": \"").append(rs.getString("nome")).append("\", ");
        json.append("\"preco\": ").append(rs.getFloat("preco")).append(", ");
        json.append("\"descricao\": \"").append(rs.getString("descricao")).append("\", ");
        json.append("\"tipo\": \"").append(rs.getString("tipo")).append("\", ");
        json.append("\"quantidade\": ").append(rs.getInt("quantidade")).append(", ");
        json.append("\"unidade_medida\": ").append(rs.getString("unidade_medida") == null ? "null" : "\"" + rs.getString("unidade_medida") + "\"").append(", ");
        json.append("\"categoria\": ").append(rs.getString("categoria") == null ? "null" : "\"" + rs.getString("categoria") + "\"").append(", ");
        json.append("\"material\": ").append(rs.getString("material") == null ? "null" : "\"" + rs.getString("material") + "\"").append(", ");
        json.append("\"tamanho\": ").append(rs.getString("tamanho") == null ? "null" : "\"" + rs.getString("tamanho") + "\"").append(", ");
        json.append("\"cor\": ").append(rs.getString("cor") == null ? "null" : "\"" + rs.getString("cor") + "\"").append(", ");
        json.append("\"id_estoque\": ").append(rs.getLong("id_estoque"));
        json.append("}");
        return json.toString();
    }
}