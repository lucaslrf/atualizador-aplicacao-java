# atualizador-aplicacao-java

### Introdução - Sobre o atualizador

Esse é um atualizador de aplicação java

Obs.: Esse atualizador é para facilitar aplicação que não são java web, ou para quem tem dificuldade em configurar arquivos JNLP.

O melhor método de atualização é com o JNLP, contudo se sentir dificuldade e já tiver terminado o projeto e gerado o jar, poderá utilizar a forma
criada.

### Atualizar

<p>As seguintes variaveis precisam ser preenchidas: </p>

<br />

<p>

    private static String string_url = ""; //Aqui vai o link onde se encontra a aplicacao jar
	private static String string_url_versao = ""; //Aqui vai o link onde se encontra o arquivo txt com o numero da versao
	private static String token = ""; //Aqui vai o token caso use para buscar atraves de alguma api se for usar um link aberto no servidor não precisa usar o token

</p>

### SQLite

<p>A aplicação usa o sqlite para armazenar a versão e o caminho da instalação, as informações de conexão pode ser encontrado no pacote Conexão, na classe ConexaoBDSqlite.</p>

<p>conexaoSqlLite = DriverManager.getConnection("jdbc:sqlite:database-atualizador.db");</p>

<p>Você pode alterar a seguinte linha: "jdbc:sqlite:nome_do_seu_database.db"</p>

<p>Recomendo deixar a estrutura da tabela a mesma, com 3 colunas. id, versao e caminho_instalação</p>

<p>Através da classe BancoAtualizacao é possível acessar os métodos de inserção e update da table_atualizar</p>

### Loading

<p>A classe no pacote View de Loading é para mostrar um carregamento caso ocorra alguma atualização</p>

<p>A seguite linha: 
	<br />
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); 
	<br />

	Impede o fechamento da tela de carregament, assim o usuário vai continuar esperando até que a atualização seja finalizada totalmente
</p>