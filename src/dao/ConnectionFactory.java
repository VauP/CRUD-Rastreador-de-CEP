package dao;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConnectionFactory {

	public Connection getConexao() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/endereco", "root", "Elephant_7");
		}catch(Exception erro) {
			throw new RuntimeException ("Erro 1 : " + erro);
		}
	}
	
}
