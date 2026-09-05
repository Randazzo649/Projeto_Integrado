package com.sistema.percistence.Repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sistema.models.Usuario;
import com.sistema.percistence.RepositoryTemplate;

public class UsuarioRepository extends RepositoryTemplate<Usuario> {

	@Override
	protected String getInsertionString() {
		return "INSERT INTO Usuario(nome, email, senha, foto, curador) VALUES (?, ?, ?, ?, ?)";
	}

	@Override
	protected String getUpdateString() {
		return "UPDATE TABLE Usuario SET nome = ?, email = ?, senha = ?, foto = ?, curador = ? WHERE id = ?";
	}

	@Override
	protected String getSelectByIdString() {
		return "SELECT * FROM Usuario WHERE id = ?";
	}

	@Override
	protected String getSelectAllString(String filtro) {
		return "SELECT * FROM Usuario";
	}

	@Override
	protected void inserirInsertionParametros(PreparedStatement stmt, Usuario entity) throws SQLException {
		stmt.setString(1, entity.getNome());
        stmt.setString(2, entity.getEmail());
        stmt.setString(3, entity.getSenha());
        stmt.setString(4, entity.getFoto());
        stmt.setBoolean(5, entity.isCurador());
        
	}

	@Override
	protected void inserirUpdateParametros(PreparedStatement stmt, Usuario entity) throws SQLException {
		stmt.setString(1, entity.getNome());
        stmt.setString(2, entity.getEmail());
        stmt.setString(3, entity.getSenha());
        stmt.setString(4, entity.getFoto());
        stmt.setBoolean(5, entity.isCurador());
        stmt.setLong(6, entity.getId());
	}

	@Override
	protected Usuario getClassFromResultSet(ResultSet rs) throws SQLException {
		Usuario u = new Usuario();
        u.setId(rs.getLong("id"));
        u.setNome(rs.getString("nome"));
        u.setEmail(rs.getString("email"));
        u.setSenha(rs.getString("senha"));
        u.setFoto(rs.getString("foto"));
        u.setCurador(rs.getBoolean("curador"));
        return u;
	}

	@Override
	protected String toJson(ResultSet rs) throws SQLException {
		StringBuilder json = new StringBuilder("{");

        json.append("\"id\": ").append(rs.getInt("id")).append(", ");
        json.append("\"nome\": \"").append(rs.getString("nome")).append("\", ");
        json.append("\"email\": \"").append(rs.getString("email")).append("\", ");
        json.append("\"foto\": \"").append(rs.getString("foto")).append(", \"");
        json.append("\"curador\": ").append(rs.getBoolean("curador")).append(", ");
        json.append("\"vendedor\": ").append(rs.getBoolean("vendedor"));
        json.append("}");
        
        return json.toString();
	}
    
}
