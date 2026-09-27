package com.sistema.percistence.Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sistema.models.Usuario;
import com.sistema.percistence.RepositoryTemplate;

public class UsuarioRepository extends RepositoryTemplate<Usuario> {

	public Usuario findByEmail(String email) throws SQLException{
		Usuario usuario = null;
		try( Connection conn = bd.conectarSql() ){
			String sql = "SELECT * FROM Usuario WHERE email = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				usuario = this.getClassFromResultSet(rs);
		}
		return  usuario;
	}

	@Override
	protected String getInsertionString() {
		return "INSERT INTO Usuario(nome, email, senha, foto, curador, ativo, id_empresa, funcao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected String getUpdateString() {
		return "UPDATE Usuario SET nome = ?, email = ?, senha = ?, foto = ?, curador = ?, ativo = ?, id_empresa = ?, funcao = ? WHERE id = ?";
	}

	@Override
	protected String getSelectByIdString() {
		return "SELECT * FROM Usuario WHERE id = ?";
	}

	@Override
	protected String getSelectAllString(String filtro) {
		return "SELECT * FROM Usuario " + filtro;
	}

	@Override
	protected void inserirInsertionParametros(PreparedStatement stmt, Usuario entity) throws SQLException {
		stmt.setString(1, entity.getNome());
        stmt.setString(2, entity.getEmail());
        stmt.setString(3, entity.getSenha());
        stmt.setString(4, entity.getFoto());
        stmt.setBoolean(5, entity.isCurador());
        stmt.setBoolean(6, entity.isAtivo());
        stmt.setLong(7, entity.getIdEmpresa());
        stmt.setString(8, entity.getFuncao());
	}

	@Override
	protected void inserirUpdateParametros(PreparedStatement stmt, Usuario entity) throws SQLException {
		stmt.setString(1, entity.getNome());
        stmt.setString(2, entity.getEmail());
        stmt.setString(3, entity.getSenha());
        stmt.setString(4, entity.getFoto());
        stmt.setBoolean(5, entity.isCurador());
        stmt.setBoolean(6, entity.isAtivo());
        stmt.setLong(7, entity.getIdEmpresa());
        stmt.setString(8, entity.getFuncao());
        stmt.setLong(9, entity.getId());
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
        u.setAtivo(rs.getBoolean("ativo"));
        u.setIdEmpresa(rs.getLong("id_empresa"));
        u.setFuncao(rs.getString("funcao"));
        return u;
	}

	@Override
	protected String toJson(ResultSet rs) throws SQLException {
		StringBuilder json = new StringBuilder("{");

        json.append("\"id\": ").append(rs.getLong("id")).append(", ");
        json.append("\"nome\": \"").append(rs.getString("nome")).append("\", ");
        json.append("\"email\": \"").append(rs.getString("email")).append("\", ");
        json.append("\"foto\": \"").append(rs.getString("foto")).append("\", ");
        json.append("\"curador\": ").append(rs.getBoolean("curador")).append(", ");
        json.append("\"ativo\": ").append(rs.getBoolean("ativo")).append(", ");
        json.append("\"funcao\": \"").append(rs.getString("funcao")).append("\", ");
        json.append("\"id_empresa\": ").append(rs.getLong("id_empresa"));
        json.append("}");
        
        return json.toString();
	}
    
}