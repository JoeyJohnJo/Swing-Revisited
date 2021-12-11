package horto.banco;

import java.sql.*;

public class BancoDeDados {

	private static BancoDeDados INSTANCIA = null;
	private static Connection conexao; // Criado para conectar no banco de dados

	// Mudar para conectar em outros bancos de dados
	private static final String URL = "jdbc:mysql://localhost:3306";
	private static final String USER = "root";
	private static final String PASS = "mysql";

	//Tentativa de conexao ao banco de dados
	private static void conectar() throws SQLException {
		conexao = DriverManager.getConnection(URL, USER, PASS);
	}

	//Invocar este metodo em outras classes para executar comandos SELECT no banco de dados
	//a String command e onde vai o codigo de SQL
	public ResultSet select(String query) {
		ResultSet rs = null;
		try {
			rs = conexao.createStatement().executeQuery(query);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return rs;
	}

	//Invocar este metodo em outras classes para executar comandos que transformam os dados (INSERT/UPDATE/DELETE) no banco de dados
	public void transformar(String query) {
		try (Statement statement = conexao.createStatement()) {
			statement.executeUpdate(query);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	private BancoDeDados() {
		try {
			conectar();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static BancoDeDados get() {
		if (INSTANCIA == null)
			INSTANCIA = new BancoDeDados();
		return INSTANCIA;
	}
}
