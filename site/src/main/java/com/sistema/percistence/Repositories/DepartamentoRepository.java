package com.sistema.percistence.Repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sistema.models.Departamento;
import com.sistema.percistence.RepositoryTemplate;

public class DepartamentoRepository extends RepositoryTemplate<Departamento> {

    @Override
    protected String getInsertionString() {
        return "INSERT INTO Departamento(nome, descricao, id_empresa) VALUES (?, ?, ?)";
    }

    @Override
    protected String getUpdateString() {
        return "UPDATE Departamento SET nome = ?, descricao = ?, id_empresa = ? WHERE id = ?";
    }

    @Override
    protected String getSelectByIdString() {
        return "SELECT * FROM Departamento WHERE id = ?";
    }

    @Override
    protected String getSelectAllString(String filtro) {
        return "SELECT * FROM Departamento " + filtro;
    }

    @Override
    protected void inserirInsertionParametros(
            PreparedStatement stmt, Departamento entity) throws SQLException {

        stmt.setString(1, entity.getNome());
        stmt.setString(2, entity.getDescricao());
        stmt.setLong(3, entity.getIdEmpresa());
    }

    @Override
    protected void inserirUpdateParametros(
            PreparedStatement stmt, Departamento entity) throws SQLException {

        stmt.setString(1, entity.getNome());
        stmt.setString(2, entity.getDescricao());
        stmt.setLong(3, entity.getIdEmpresa());
        stmt.setLong(4, entity.getId());
    }

    @Override
    protected Departamento getClassFromResultSet(ResultSet rs) throws SQLException {

        Departamento d = new Departamento();

        d.setId(rs.getLong("id"));
        d.setNome(rs.getString("nome"));
        d.setDescricao(rs.getString("descricao"));
        d.setIdEmpresa(rs.getLong("id_empresa"));

        return d;
    }

    @Override
    protected String toJson(ResultSet rs) throws SQLException {

        StringBuilder json = new StringBuilder("{");

        json.append("\"id\": ").append(rs.getLong("id")).append(", ");
        json.append("\"nome\": \"").append(rs.getString("nome")).append("\", ");
        json.append("\"descricao\": \"").append(rs.getString("descricao")).append("\", ");
        json.append("\"id_empresa\": ").append(rs.getLong("id_empresa"));
        json.append("}");

        return json.toString();
    }
}