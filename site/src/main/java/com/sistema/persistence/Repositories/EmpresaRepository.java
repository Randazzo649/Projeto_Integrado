package com.sistema.persistence.Repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sistema.models.Empresa;
import com.sistema.persistence.RepositoryTemplate;
import com.sistema.persistence.configs.DataBaseConfigSingleton;

public class EmpresaRepository extends RepositoryTemplate<Empresa>{


    public void excluirEmpresa(long id) throws SQLException {
        String sql = "CALL excluir_empresa(?)";
        try (PreparedStatement stmt = DataBaseConfigSingleton.getInstance().conectarSql().prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }


    @Override
    protected String getInsertionString() {
        return "INSERT INTO Empresa(razao, cnpj, telefone, endereco, foto, cor, nome, ativo) VALUES (?,?,?,?,?,?,?,?)";
    }

    @Override
    protected String getUpdateString() {
        return "UPDATE Empresa SET razao = ?, cnpj = ?, telefone = ?, endereco = ?, foto = ?, cor = ?, nome = ?, ativo = ? WHERE id = ?;";
    }

    @Override
	protected String getDeleteString(){
		return "DELETE FROM Empresa WHERE id = ?";
	}

    @Override
    protected String getSelectByIdString() {
        return "SELECT * FROM Empresa WHERE id = ?";
    }

    @Override
    protected String getSelectAllString(String filtro) {
        return "SELECT * FROM Empresa " + filtro;
    }

    @Override
    protected void inserirInsertionParametros(PreparedStatement stmt, Empresa entity) throws SQLException {
        stmt.setString(1,entity.getRazao());
        stmt.setString(2,entity.getCnpj());
        stmt.setString(3,entity.getTelefone());
        stmt.setString(4,entity.getEndereco());
        stmt.setString(5,entity.getFoto());
        stmt.setString(6, entity.getCor());
        stmt.setString(7,entity.getNome());
        stmt.setBoolean(8,entity.isAtivo());
    }

    @Override
    protected void inserirUpdateParametros(PreparedStatement stmt, Empresa entity) throws SQLException {
        stmt.setString(1,entity.getRazao());
        stmt.setString(2,entity.getCnpj());
        stmt.setString(3,entity.getTelefone());
        stmt.setString(4,entity.getEndereco());
        stmt.setString(5,entity.getFoto());
        stmt.setString(6, entity.getCor());
        stmt.setString(7,entity.getNome());
        stmt.setBoolean(8, entity.isAtivo());
        stmt.setLong(9, entity.getId());
    }

    @Override
    protected Empresa getClassFromResultSet(ResultSet rs) throws SQLException {
        Empresa e = new Empresa();
        e.setId(rs.getLong("id"));
        e.setRazao(rs.getString("razao"));
        e.setCnpj(rs.getString("cnpj"));
        e.setTelefone(rs.getString("telefone"));
        e.setEndereco(rs.getString("endereco"));
        e.setFoto(rs.getString("foto"));
        e.setCor(rs.getString("cor"));
        e.setNome(rs.getString("nome"));
        e.setAtivo(rs.getBoolean("ativo"));
        return e;
    }

    @Override
    protected String toJson(ResultSet rs) throws SQLException {
        StringBuilder json = new StringBuilder("{");

        json.append("\"id\" : ").append(rs.getLong("id")).append(", ");
        json.append("\"razao\" : ").append(rs.getString("razao")).append(", ");
        json.append("\"cnpj\" : ").append(rs.getString("cnpj")).append(", ");
        json.append("\"telefone\" : ").append(rs.getString("telefone")).append(", ");
        json.append("\"endereco\" : ").append(rs.getString("endereco")).append(", ");
        json.append("\"foto\" : ").append(rs.getString("foto")).append(", ");
        json.append("\"cor\" : ").append(rs.getString("cor")).append(", ");
        json.append("\"nome\" : ").append(rs.getString("nome")).append(", ");
        json.append("\"nome\" : ").append(rs.getString("ativo"));
        json.append("}");
        return json.toString();
    }
    
}
