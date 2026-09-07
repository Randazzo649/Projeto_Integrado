package com.sistema.percistence.Repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sistema.models.Solicitacao;
import com.sistema.percistence.RepositoryTemplate;

public class SolicitacaoRepository extends RepositoryTemplate<Solicitacao>{

    @Override
    protected String getInsertionString() {
        return "INSERT INTO Solicitacao(data_solic, razao, cnpj, telefone, endereco, email, senha, nome, documento) VALUES (CURDATE(), ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateString() {
        return "UPDATE TABLE Solicitacao SET data_decis = ?,  data_solic = ?,  razao = ?,  cnpj = ?,  telefone = ?,  endereco = ?,  email = ?,  senha = ?,  nome = ?, documento = ? WHERE id = ?";
    }

    @Override
    protected String getSelectByIdString() {
        return "SELECT * FROM Solicitacao WHERE id = ?";
    }

    @Override
    protected String getSelectAllString(String filtro) {
        return "SELECT * FROM Solicitacao " + filtro;
    }

    @Override
    protected void inserirInsertionParametros(PreparedStatement stmt, Solicitacao entity) throws SQLException {
        stmt.setString(1,entity.getRazaoSocial());
        stmt.setString(2,entity.getCnpj());
        stmt.setString(3,entity.getTelefone());
        stmt.setString(4,entity.getEndereco());
        stmt.setString(5, entity.getEmail());
        stmt.setString(6, entity.getSenha());
        stmt.setString(7,entity.getNome());
        stmt.setString(8,entity.getDocumento());
        
    }

    @Override
    protected void inserirUpdateParametros(PreparedStatement stmt, Solicitacao entity) throws SQLException {
        stmt.setString(1,entity.getDataDecisao());
        stmt.setString(2,entity.getDataSolicitacao());
        stmt.setString(3,entity.getRazaoSocial());
        stmt.setString(4,entity.getCnpj());
        stmt.setString(5,entity.getTelefone());
        stmt.setString(6,entity.getEndereco());
        stmt.setString(7, entity.getEmail());
        stmt.setString(8, entity.getSenha());
        stmt.setString(9,entity.getNome());
        stmt.setString(10, entity.getDocumento());
        stmt.setLong(11,entity.getId());
    }

    @Override
    protected Solicitacao getClassFromResultSet(ResultSet rs) throws SQLException {
        Solicitacao e = new Solicitacao();
        e.setId(rs.getLong("id"));
        e.setCnpj(rs.getString("cnpj"));
        e.setTelefone(rs.getString("telefone"));
        e.setEndereco(rs.getString("endereco"));
        e.setEmail(rs.getString("email"));
        e.setSenha(rs.getString("senha"));
        e.setNome(rs.getString("nome"));
        e.setDataDecisao(rs.getString("data_decis"));
        e.setDataSolicitacao(rs.getString("data_solic"));
        e.setDocumento(rs.getString("documento"));
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
        json.append("\"email\" : ").append(rs.getString("email")).append(", ");
        json.append("\"senha\" : ").append(rs.getString("senha")).append(", ");
        json.append("\"data_decis\" : ").append(rs.getString("data_decis")).append(", ");
        json.append("\"data_solic\" : ").append(rs.getString("data_solic")).append(", ");
        json.append("\"nome\" : ").append(rs.getString("nome")).append(", ");
        json.append("\"documento\" : ").append(rs.getString("documento"));
        json.append("}");
        
        return json.toString();
    }

    
    
}
