package Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

import Model.BancoAtualizacao;
import View.Loading;

public class Atualizar {

	private static BancoAtualizacao comunicacao_bd_atualizacao = new BancoAtualizacao();

	private static String string_url = ""; //Aqui vai o link onde se encontra a aplicacao jar
	private static String string_url_versao = ""; //Aqui vai o link onde se encontra o arquivo txt com o numero da versao
	private static HttpURLConnection connection_versao;
	private static HttpURLConnection connection_update;
	private static String token = ""; //Aqui vai o token caso use para buscar atraves de alguma api

	public Atualizar() {

	}// fim construtor

	public static void main(String[] args) {

		String caminho_instalacao = comunicacao_bd_atualizacao
				.consultarCaminhoInstalacao("Select caminho_instalacao from table_atualizar where id=1");

		if (caminho_instalacao == null) {

			caminho_instalacao = JOptionPane.showInputDialog("Coloque o caminho de instalação da aplicação");
			comunicacao_bd_atualizacao.atualizarCaminhoInstalacao(caminho_instalacao, 1);

		}

		boolean funcionou = false;

		try {

			System.out.println("Caminho instalacao 2: " + caminho_instalacao);
			URL url_versao = new URL(string_url_versao);
			connection_versao = (HttpURLConnection) url_versao.openConnection();
			connection_versao.setRequestProperty("Authorization", token);
			
			
			
			File file_versao = new File(caminho_instalacao + "/versao_aplicacacao.txt");
			System.out.println(file_versao);

			//FileUtils.copyURLToFile(url_versao, file_versao);
			FileUtils.copyInputStreamToFile(connection_versao.getInputStream(), file_versao);

			int bytes = 0;

			Path caminho = Paths.get(caminho_instalacao + "/versao_aplicacacao.txt");

			byte[] texto = Files.readAllBytes(caminho);
			String leitura = new String(texto);

			System.out.println("leitura " + leitura);
			String array[] = leitura.toString().split("\\.");

			int major = Integer.parseInt(array[0]);
			int minor = Integer.parseInt(array[1]);
			int patch = Integer.parseInt(array[2]);

			System.out.println("Major: " + major + "\nMinor: " + minor + "\nPatch: " + patch);

			String consulta_versao = comunicacao_bd_atualizacao
					.consultarVersao("Select versao from table_atualizar where id=1");

			if (consulta_versao != null) {

				String array_versao_consultada[] = consulta_versao.split("\\.");

				int major_consultada = Integer.parseInt(array_versao_consultada[0]);
				int minor_consultada = Integer.parseInt(array_versao_consultada[1]);
				int patch_consultada = Integer.parseInt(array_versao_consultada[2]);

				System.out.println(
						"Major: " + major_consultada + "\nMinor: " + minor_consultada + "\nPatch: " + patch_consultada);

				if (major_consultada < major) {
					atualizarVersao(leitura, caminho_instalacao, bytes);
				} else if (minor_consultada < minor) {
					atualizarVersao(leitura, caminho_instalacao, bytes);
				} else if (patch_consultada < patch) {
					atualizarVersao(leitura, caminho_instalacao, bytes);
				}

			} else {

				
				URL url_aplicacao = new URL(string_url);
				connection_update = (HttpURLConnection) url_aplicacao.openConnection();
				connection_update.setRequestProperty("Authorization", token);
				
				int status = connection_update.getResponseCode();
				
				System.out.println("STATUS: "+status);
				
				File file_aplicacao = new File(caminho_instalacao + "/aplicacao.jar");

				FileUtils.copyInputStreamToFile(connection_update.getInputStream(), file_aplicacao);
				
				comunicacao_bd_atualizacao.atualizarVersao(leitura, 1);

			}

			funcionou = true;

		} catch (Exception erroExceptionDownload) {
			funcionou = false;
			JOptionPane.showMessageDialog(null, "Erro download " + erroExceptionDownload);
			System.out.println("Erro download " + erroExceptionDownload);
		}

		if (funcionou) {
			try {
				/*Comando do cmd para abrir a aplicacao jar*/
				String comando = "java -jar aplicacao.jar";
				System.out.println("Comando: " + comando);
				Runtime.getRuntime().exec(comando);
				System.exit(0); //fechando o modulod e atualizar.jar
			} catch (Exception er) {
				JOptionPane.showMessageDialog(null, "Houve um erro ao iniciar a aplicacao " + er);
				System.out.println("Houve um erro ao iniciar a aplicacao " + er);
			}

		} else {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao salvar o caminho e verificar a versao do sistema - contate o suporte");
		}

	}// fim metodo main

	public static void atualizarVersao(String leitura, String caminho_instalacao, int bytes) {

		Loading loading = new Loading();
		loading.setVisible(true);

		try {
			
			URL url = new URL(string_url);
			connection_update = (HttpURLConnection) url.openConnection();
			connection_update.setRequestProperty("Authorization", token);
			
			int status = connection_update.getResponseCode();
			
			System.out.println("STATUS: "+status);
			
			File file = new File(caminho_instalacao + "/aplicacao.jar");

			//FileUtils.copyURLToFile(url, file);
			FileUtils.copyInputStreamToFile(connection_update.getInputStream(), file);
			
			comunicacao_bd_atualizacao.atualizarVersao(leitura, 1);

			loading.dispose();

		} catch (Exception exceptionAtualizarVersao) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar versao: " + exceptionAtualizarVersao);
			System.out.println("Erro ao atualizar versao: " + exceptionAtualizarVersao);

		}

	}// fim metodo atualizarVersao

}// fim class atualizar
