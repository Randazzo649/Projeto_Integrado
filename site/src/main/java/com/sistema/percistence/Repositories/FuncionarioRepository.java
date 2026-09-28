package com.sistema.percistence.Repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sistema.models.Funcionario;
import com.sistema.percistence.RepositoryTemplate;

public class FuncionarioRepository extends RepositoryTemplate<Funcionario> {

    @Override
    protected String getInsertionString() {
        return "INSERT INTO Funcionario(nome, email, senha, cargo, salario, data_admissao, estado, id_empresa, id_departamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateString() {
        return "UPDATE Funcionario SET nome = ?, email = ?, senha = ?, cargo = ?, salario = ?, data_admissao = ?, estado = ?, id_empresa = ?, id_departamento = ? WHERE id = ?";
    }

    @Override
    protected String getSelectByIdString() {
        return "SELECT * FROM Funcionario WHERE id = ?";
    }

    @Override
    protected String getSelectAllString(String filtro) {
        return "SELECT * FROM Funcionario " + filtro;
    }

    @Override
    protected void inserirInsertionParametros(
            PreparedStatement stmt, Funcionario entity) throws SQLException {

        stmt.setString(1, entity.getNome());
        stmt.setString(2, entity.getEmail());
        stmt.setString(3, entity.getSenha());
        stmt.setString(4, entity.getCargo());
        stmt.setString(5, entity.getSalario());
        stmt.setString(6, entity.getData_admissao());
        stmt.setString(7, entity.getEstado());
        stmt.setLong(8, entity.getIdEmpresa());
        stmt.setLong(9, entity.getIdDepartamento());
    }

    @Override
    protected void inserirUpdateParametros(
            PreparedStatement stmt, Funcionario entity) throws SQLException {

        stmt.setString(1, entity.getNome());
        stmt.setString(2, entity.getEmail());
        stmt.setString(3, entity.getSenha());
        stmt.setString(4, entity.getCargo());
        stmt.setString(5, entity.getSalario());
        stmt.setString(6, entity.getData_admissao());
        stmt.setString(7, entity.getEstado());
        stmt.setLong(8, entity.getIdEmpresa());
        stmt.setLong(9, entity.getIdDepartamento());
        stmt.setLong(10, entity.getId());
    }

    @Override
    protected Funcionario getClassFromResultSet(ResultSet rs) throws SQLException {

        Funcionario f = new Funcionario();

        f.setId(rs.getLong("id"));
        f.setNome(rs.getString("nome"));
        f.setEmail(rs.getString("email"));
        f.setSenha(rs.getString("senha"));
        f.setCargo(rs.getString("cargo"));
        f.setSalario(rs.getString("salario"));
        f.setData_admissao(rs.getString("data_admissao"));
        f.setEstado(rs.getString("estado"));
        f.setIdEmpresa(rs.getLong("id_empresa"));
        f.setIdDepartamento(rs.getLong("id_departamento"));

        return f;
    }

    @Override
    protected String toJson(ResultSet rs) throws SQLException {

        StringBuilder json = new StringBuilder("{");

        json.append("\"id\": ").append(rs.getLong("id")).append(", ");
        json.append("\"nome\": \"").append(rs.getString("nome")).append("\", ");
        json.append("\"email\": \"").append(rs.getString("email")).append("\", ");
        json.append("\"cargo\": \"").append(rs.getString("cargo")).append("\", ");
        json.append("\"salario\": \"").append(rs.getString("salario")).append("\", ");
        json.append("\"data_admissao\": \"").append(rs.getString("data_admissao")).append("\", ");
        json.append("\"estado\": \"").append(rs.getString("estado")).append("\", ");
        json.append("\"id_empresa\": ").append(rs.getLong("id_empresa")).append(", ");
        json.append("\"id_departamento\": ").append(rs.getLong("id_departamento"));

        json.append("}");

        return json.toString();
    }
}