package connect;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
//MINHA CONEXÃO COM O BANCO
	
	public Connection getConexao(){
		try{
			return DriverManager.getConnection("jdbc:mysql://localhost/mercado", "root", "admin");
		}catch(Exception erro){
			throw new RuntimeException("Erro 1" + erro);	
		}
		
	}

 }
	
