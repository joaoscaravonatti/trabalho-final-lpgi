package br.edu.ifc.videira.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	// conex�o MySQL
	public static Connection conectar() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexao = DriverManager
					.getConnection("jdbc:mysql://localhost/mydb?user=root&password=toor&useSSL=false");
			return conexao;
		} catch (ClassNotFoundException | SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

}
