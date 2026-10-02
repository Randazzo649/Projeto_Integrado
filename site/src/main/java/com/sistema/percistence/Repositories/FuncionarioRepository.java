package com.sistema.percistence.Repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sistema.models.Funcionario;
import com.sistema.percistence.RepositoryTemplate;
import com.sistema.percistence.configs.DataBaseConfig;

public class FuncionarioRepository extends RepositoryTemplate<Funcionario> {

    public void inserirFuncionario(Funcionario f, String departamento) throws SQLException {
        String sql = "{CALL cadastrar_funcionario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DataBaseConfig.getInstance().conectarSql();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getEmail());
            stmt.setString(3, f.getCpf());
            stmt.setString(4, f.getSenha());
            stmt.setString(5, f.getCargo());
            stmt.setString(6, f.getSalario());
            stmt.setString(7, f.getEndereco());
            stmt.setString(8, f.getData_admissao());
            stmt.setString(9, f.getData_nascimento());
            stmt.setString(10, f.getEstado());
            stmt.setString(11, f.getGenero());
            stmt.setString(12, f.getTelefone());
            stmt.setLong(13, f.getIdEmpresa());
            stmt.setString(14, departamento);

            stmt.execute();
        }
    }

    @Override
    protected String getInsertionString() {
        return "INSERT INTO Funcionario(nome, email, cpf, senha, cargo, salario, endereco, data_admissao, data_nascimento, estado, genero, telefone, id_empresa, id_departamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateString() {
        return "UPDATE Funcionario SET nome = ?, email = ?, cpf = ?, senha = ?, cargo = ?, salario = ?, endereco = ?, data_admissao = ?, data_nascimento = ?, estado = ?, genero = ?, telefone = ?, id_empresa = ?, id_departamento = ? WHERE id = ?";
    }

    @Override
    protected String getSelectByIdString() {
        return "SELECT * FROM Funcionarios_Com_Departamento WHERE id = ?";
    }

    @Override
    protected String getSelectAllString(String filtro) {
        return "SELECT * FROM Funcionarios_Com_Departamento " + filtro;
    }

    @Override
    protected void inserirInsertionParametros(PreparedStatement stmt, Funcionario entity) throws SQLException {
        stmt.setString(1, entity.getNome());
        stmt.setString(2, entity.getEmail());
        stmt.setString(3, entity.getCpf());
        stmt.setString(4, entity.getSenha());
        stmt.setString(5, entity.getCargo());
        stmt.setString(6, entity.getSalario());
        stmt.setString(7, entity.getEndereco());
        stmt.setString(8, entity.getData_admissao());
        stmt.setString(9, entity.getData_nascimento());
        stmt.setString(10, entity.getEstado());
        stmt.setString(11, entity.getGenero());
        stmt.setString(12, entity.getTelefone());
        stmt.setLong(13, entity.getIdEmpresa());
        stmt.setLong(14, entity.getIdDepartamento());
    }

    @Override
    protected void inserirUpdateParametros(PreparedStatement stmt, Funcionario entity) throws SQLException {
        stmt.setString(1, entity.getNome());
        stmt.setString(2, entity.getEmail());
        stmt.setString(3, entity.getCpf());
        stmt.setString(4, entity.getSenha());
        stmt.setString(5, entity.getCargo());
        stmt.setString(6, entity.getSalario());
        stmt.setString(7, entity.getEndereco());
        stmt.setString(8, entity.getData_admissao());
        stmt.setString(9, entity.getData_nascimento());
        stmt.setString(10, entity.getEstado());
        stmt.setString(11, entity.getGenero());
        stmt.setString(12, entity.getTelefone());
        stmt.setLong(13, entity.getIdEmpresa());
        stmt.setLong(14, entity.getIdDepartamento());
        stmt.setLong(15, entity.getId());
    }

    @Override
    protected Funcionario getClassFromResultSet(ResultSet rs) throws SQLException {
        Funcionario f = new Funcionario();

        f.setId(rs.getLong("id"));
        f.setNome(rs.getString("nome"));
        f.setEmail(rs.getString("email"));
        f.setCpf(rs.getString("cpf"));
        f.setSenha(rs.getString("senha"));
        f.setCargo(rs.getString("cargo"));
        f.setSalario(rs.getString("salario"));
        f.setEndereco(rs.getString("endereco"));
        f.setData_admissao(rs.getString("data_admissao"));
        f.setData_nascimento(rs.getString("data_nascimento"));
        f.setEstado(rs.getString("estado"));
        f.setGenero(rs.getString("genero"));
        f.setTelefone(rs.getString("telefone"));
        f.setIdEmpresa(rs.getLong("id_empresa"));
        f.setIdDepartamento(rs.getLong("id_departamento"));
        f.setNomeDepartamento(rs.getString("departamento_nome"));

        return f;
    }

    @Override
    protected String toJson(ResultSet rs) throws SQLException {
        StringBuilder json = new StringBuilder("{");

        json.append("\"id\": ").append(rs.getLong("id")).append(", ");
        json.append("\"nome\": \"").append(rs.getString("nome")).append("\", ");
        json.append("\"email\": \"").append(rs.getString("email")).append("\", ");
        json.append("\"cpf\": \"").append(rs.getString("cpf")).append("\", ");
        json.append("\"cargo\": \"").append(rs.getString("cargo")).append("\", ");
        json.append("\"salario\": \"").append(rs.getString("salario")).append("\", ");
        json.append("\"endereco\": \"").append(rs.getString("endereco")).append("\", ");
        json.append("\"data_admissao\": \"").append(rs.getString("data_admissao")).append("\", ");
        json.append("\"data_nascimento\": \"").append(rs.getString("data_nascimento")).append("\", ");
        json.append("\"telefone\": \"").append(rs.getString("telefone")).append("\", ");
        json.append("\"genero\": \"").append(rs.getString("genero")).append("\", ");
        json.append("\"estado\": \"").append(rs.getString("estado")).append("\", ");
        json.append("\"id_empresa\": ").append(rs.getLong("id_empresa")).append(", ");
        json.append("\"id_departamento\": ").append(rs.getLong("id_departamento")).append(", ");
        json.append("\"departamento_nome\": \"").append(rs.getString("departamento_nome")).append("\"");

        json.append("}");

        return json.toString();
    }
}