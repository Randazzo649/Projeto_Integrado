package com.sistema.percistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfig{

    private static DataBaseConfig conf;

    private static String caminho = "jdbd:mysql://127.0.0.1/UnitHub";
    private static String senha = "";
    private static String usuario = "root";

    public static DataBaseConfig getInstance(){
        if(conf == null)
            conf = new DataBaseConfig();
        return conf;
    }

	public Connection conectarSql() throws SQLException{
		return DriverManager.getConnection(caminho, usuario, senha);
	}

}