package horto.servicos;

import horto.Constantes;
import horto.dtos.Cliente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoClientes extends Servico<Cliente> {
	private static ServicoClientes INSTANCIA = null;


	private ServicoClientes() { }

	public List<Cliente> buscar() {
		var list = new ArrayList<Cliente>();
		var rs = BD.select(Constantes.SELECT_CLIENTES);
		try {
			while (rs.next()) {
				list.add(
					new Cliente(
						rs.getInt("id"),
						rs.getString("email"),
						rs.getString("telefone"),
						rs.getString("documento"),
						rs.getString("nome"),
						rs.getString("cidade")
					)
				);
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return list;
	}

	public void editar(Cliente cliente) {
		BD.transformar(Constantes.atualizarCliente(cliente));
	}

	public void deletar(int idDoCliente) {
		BD.transformar(Constantes.deletarCliente(idDoCliente));
	}

	public void inserir(Cliente cliente) {
		BD.transformar(Constantes.adicionarCliente(cliente));
	}

	public static ServicoClientes get() {
		if (INSTANCIA == null)
			INSTANCIA = new ServicoClientes();
		return INSTANCIA;
	}

}
