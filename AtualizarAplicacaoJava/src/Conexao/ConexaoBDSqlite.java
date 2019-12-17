package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/* * * * * * * * * 
 * 
 * Classe de conexao com o banco de dados sqlite
 *  
 * * * * * * * * */

public class ConexaoBDSqlite {

	public ConexaoBDSqlite() {
		
	}//fim construtor
	
	public Connection getConexaoSqlLite() {
		Connection conexaoSqlLite = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conexaoSqlLite = DriverManager.getConnection("jdbc:sqlite:database.db");
			return conexaoSqlLite;
		} catch (Exception er) {
			System.out.println("Houve um erro: " + er);
			return null;
		}
	}//fim metodo conexaoSqlLite
	
	public boolean fecharConexaoSqlLite() {
		if (getConexaoSqlLite() != null) {
			try {
				getConexaoSqlLite().close();
				return true;
			} catch (Exception er) {
				System.out.println("Erro eo fecha conexao com o SqlLite");
				return false;
			}
		} else {
			return false;
		}
	}//fim metofo fecharConexaoSqlLite
	
	public Connection reiniciarConexaoSqlLite() {
		fecharConexaoSqlLite();
		return getConexaoSqlLite();
	}//fim metodo reiniciarConexaoSqlLite
	
}//fim classe ConexaoBDSqlLite
