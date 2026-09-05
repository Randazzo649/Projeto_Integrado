package com.sistema.percistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;



public abstract class RepositoryTemplate<T> {
    protected static DataBaseConfig bd = DataBaseConfig.getInstance();

    public RepositoryTemplate() {
        
    }

    public final boolean cadastrar(T entity) throws SQLException {
        String sql = getInsertionString();
        try (Connection conn = bd.conectarSql();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            inserirInsertionParametros(stmt, entity);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        }
    }

    public final boolean salvarEstadoAtual(T entity) throws SQLException {
        String sql = getUpdateString();
        try (Connection conn = bd.conectarSql();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            inserirUpdateParametros(stmt, entity);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        }
    }

    public final T findById(int id) throws SQLException {
        String sql = getSelectByIdString();
        try (Connection conn = bd.conectarSql();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return getClassFromResultSet(rs);
            }
        }
        return null;
    }

    public final ArrayList<T> findAll(String filtro) throws SQLException {
        String sql = getSelectAllString(filtro);
        ArrayList<T> list = new ArrayList<>();
        try (Connection conn = bd.conectarSql();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(getClassFromResultSet(rs));
            }
        }
        return list;
    }

    public final String findAllJSON(String filtro) throws SQLException{
        StringBuilder json = new StringBuilder("[");
        String sql = getSelectAllString(filtro);

        try (Connection conn = bd.conectarSql();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String itemJson = toJson(rs);
                json.append(itemJson).append(", ");
            }
        }

        json.append("{} ]");
        return json.toString();
    }

    // Métodos abstratos que as subclasses devem implementar
    protected abstract String getInsertionString();
    protected abstract String getUpdateString();
    protected abstract String getSelectByIdString();
    protected abstract String getSelectAllString(String filtro);
    protected abstract void inserirInsertionParametros(PreparedStatement stmt, T entity) throws SQLException;
    protected abstract void inserirUpdateParametros(PreparedStatement stmt, T entity) throws SQLException;
    protected abstract T getClassFromResultSet(ResultSet rs) throws SQLException;
    protected abstract String toJson(ResultSet rs) throws SQLException;
}