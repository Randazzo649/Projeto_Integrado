package com.sistema.percistence.Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sistema.models.Modulo;
import com.sistema.percistence.RepositoryTemplate;

public class ModuloRepository extends RepositoryTemplate<Modulo> {

    public List<Modulo> buscarPorEmpresa(long empresaId) throws SQLException{

        String sql = """
            SELECT m.id, m.nome, m.descricao, m.imagem FROM Modulo AS m
            INNER JOIN Empresa_has_modulo AS em ON em.id_modulo = m.id
            WHERE em.id_empresa = ?
            """;

        try (
            Connection conn = bd.conectarSql();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, empresaId);

            try (ResultSet rs = stmt.executeQuery()) {

                List<Modulo> lista = new ArrayList<>();

                while (rs.next()) {
                    Modulo modulo = new Modulo();

                    modulo.setId(rs.getLong("id"));
                    modulo.setNome(rs.getString("nome"));
                    modulo.setDescricao(rs.getString("descricao"));
                    modulo.setImagem(rs.getString("imagem"));

                    lista.add(modulo);
                }

                return lista;
            }
        }
    }

    @Override
    protected String getInsertionString() {
        return "INSERT INTO Modulo(nome, descricao, imagem) VALUES (?, ?, ?)";
    }

    @Override
    protected String getUpdateString() {
        return "UPDATE Modulo SET nome = ?, descricao = ?, imagem = ? WHERE id = ?";
    }

    @Override
    protected String getSelectByIdString() {
        return "SELECT * FROM Modulo WHERE id = ?";
    }

    @Override
    protected String getSelectAllString(String filtro) {
        return "SELECT * FROM Modulo " + filtro;
    }

    @Override
    protected void inserirInsertionParametros(PreparedStatement stmt, Modulo entity) throws SQLException {
        stmt.setString(1, entity.getNome());
        stmt.setString(2, entity.getDescricao());
        stmt.setString(3, entity.getImagem());
    }

    @Override
    protected void inserirUpdateParametros(PreparedStatement stmt, Modulo entity) throws SQLException {
        stmt.setString(1, entity.getNome());
        stmt.setString(2, entity.getDescricao());
        stmt.setString(3, entity.getImagem());
        stmt.setLong(4, entity.getId());
    }

    @Override
    protected Modulo getClassFromResultSet(ResultSet rs) throws SQLException {
        Modulo modulo = new Modulo();

        modulo.setId(rs.getLong("id"));
        modulo.setNome(rs.getString("nome"));
        modulo.setDescricao(rs.getString("descricao"));
        modulo.setImagem(rs.getString("imagem"));

        return modulo;
    }

    @Override
    protected String toJson(ResultSet rs) throws SQLException {
        StringBuilder json = new StringBuilder("{");

        json.append("\"id\": ").append(rs.getLong("id")).append(", ");
        json.append("\"nome\": \"").append(rs.getString("nome")).append("\", ");
        json.append("\"descricao\": \"").append(rs.getString("descricao")).append("\", ");
        json.append("\"imagem\": \"").append(rs.getString("imagem")).append("\"");

        json.append("}");

        return json.toString();
    }
}