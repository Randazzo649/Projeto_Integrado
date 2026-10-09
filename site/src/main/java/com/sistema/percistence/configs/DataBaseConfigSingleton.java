package com.sistema.percistence.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfigSingleton{

    private static DataBaseConfigSingleton conf;

    private static String caminho = "jdbc:mysql://127.0.0.1/UnitHub";
    private static String senha = "";
    private static String usuario = "root";

    private DataBaseConfigSingleton(){}

    public static DataBaseConfigSingleton getInstance(){
        if(conf == null)
            conf = new DataBaseConfigSingleton();
        return conf;
    }

	public Connection conectarSql() throws SQLException{
		return DriverManager.getConnection(caminho, usuario, senha);
	}

}