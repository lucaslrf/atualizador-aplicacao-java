package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Conexao.ConexaoBDSqlite;

public class BancoAtualizacao {
	
	ConexaoBDSqlite conexaoBDSqlLite = new ConexaoBDSqlite();
	Connection conexaoSqlLite;
	
	public BancoAtualizacao() {
		
	}

	public String inserirCaminhoInstalacao(String caminho_instalacao) {
		System.out.println("entrou em inserir");
		conexaoSqlLite = conexaoBDSqlLite.getConexaoSqlLite();
		PreparedStatement stmt = null;
		try {
			String sql = "insert into table_atualizar (caminho_instalacao) values (?)";
			stmt = conexaoSqlLite.prepareStatement(sql);
			stmt.setString(1, caminho_instalacao);
			stmt.execute();
			stmt.close();
			conexaoBDSqlLite.fecharConexaoSqlLite();
			conexaoSqlLite.close();
			return "Inserido com sucesso";
		} catch (Exception er) {
			System.out.println(er);
			return ("Houve um erro ao inserir: " + er);
		}
		
	}
	
	
	public String consultarCaminhoInstalacao(String sql) {
		conexaoSqlLite = conexaoBDSqlLite.getConexaoSqlLite();

		Statement stmt = null;
		try {
			
			stmt = conexaoSqlLite.createStatement();
			conexaoSqlLite.setAutoCommit(false);

			String caminho_instalacao = null;
			
			stmt = conexaoSqlLite.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				caminho_instalacao = rs.getString("caminho_instalacao");
				
			}
			
			rs.close();
			stmt.close();
			conexaoBDSqlLite.fecharConexaoSqlLite();
			conexaoSqlLite.close();
			return caminho_instalacao;
		} catch (Exception er) {
			System.out.println("Houve um erro: " + er);
			return null;
		}
	}//fim metodo
	
	public String consultarVersao(String sql) {
		conexaoSqlLite = conexaoBDSqlLite.getConexaoSqlLite();

		Statement stmt = null;
		try {
			
			stmt = conexaoSqlLite.createStatement();
			conexaoSqlLite.setAutoCommit(false);

			String versao = null;
			
			stmt = conexaoSqlLite.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				versao = rs.getString("versao");
				
			}
			
			rs.close();
			stmt.close();
			conexaoBDSqlLite.fecharConexaoSqlLite();
			conexaoSqlLite.close();
			return versao;
		} catch (Exception er) {
			JOptionPane.showMessageDialog(null, "Erro em consultar versao "+er);
			System.out.println("Houve um erro: " + er);
			return null;
		}
	}//fim metodo
	
	public String atualizarCaminhoInstalacao(String caminho_instalacao, int id) {
		conexaoSqlLite = conexaoBDSqlLite.getConexaoSqlLite();
		PreparedStatement stmt = null;
		try {
			String sql = "update table_atualizar set caminho_instalacao=? where id=?;";
			stmt = conexaoSqlLite.prepareStatement(sql);
			stmt.setString(1, caminho_instalacao);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			stmt.close();
			conexaoBDSqlLite.fecharConexaoSqlLite();
			conexaoSqlLite.close();
			return "Atualizado com sucesso";
		} catch(Exception er) {
			System.out.println(er);
			return "Houve um erro ao atualizar: "+er;
		}
	}//fim metodo
	
	public String atualizarVersao(String versao, int id) {
		conexaoSqlLite = conexaoBDSqlLite.getConexaoSqlLite();
		PreparedStatement stmt = null;
		try {
			String sql = "update table_atualizar set versao=? where id=?;";
			stmt = conexaoSqlLite.prepareStatement(sql);
			stmt.setString(1, versao);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			stmt.close();
			conexaoBDSqlLite.fecharConexaoSqlLite();
			conexaoSqlLite.close();
			return "Atualizado com sucesso";
		} catch(Exception er) {
			System.out.println(er);
			return "Houve um erro ao atualizar: "+er;
		}
	}//fim metodo
	

}
