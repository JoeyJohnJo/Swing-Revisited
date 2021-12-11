package horto.servicos;

import horto.Constantes;
import horto.dtos.Pedido;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicoPedidos extends Servico<Pedido> {

	private static ServicoPedidos INSTANCE = null;

	private ServicoPedidos() {

	}

	@Override
	public List<Pedido> buscar() {
		var list = new ArrayList<Pedido>();
		var rs = BD.select(Constantes.SELECT_PEDIDOS);
		try {
			while (rs.next()) {
				list.add(
					new Pedido(
						rs.getInt("id"),
						rs.getInt("solicitante"),
						rs.getInt("produto"),
						rs.getString("origem"),
						rs.getString("finalidade"),
						rs.getDouble("valor")
					)
				);
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return list;
	}

	@Override
	public void editar(Pedido pedido) {
		BD.transformar(Constantes.atualizarPedido(pedido));
	}

	@Override
	public void deletar(int id) {
		BD.transformar(Constantes.deletarPedido(id));
	}

	@Override
	public void inserir(Pedido pedido) {
		BD.transformar(Constantes.adicionarPedido(pedido));
	}

	public String especieDoProdutoDoPedido(int id) {
		String especie = "PRODUTO NAO ENCONTRADO";
		var rs = BD.select(String.format(
			"select produto.especie as especie from horto.pedidos pedido join horto.produto produto on pedido.produto = produto.id WHERE pedido.id = %d;", id
		));
		try {
			while (rs.next()) {
				especie = Optional.of(rs.getString("especie")).orElse("PRODUTO NAO ENCONTRADO");
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return especie;
	}

	public String nomeDoClienteDoPedido(int id) {
		String nome = "CLIENTE NAO ENCONTRADO";
		var rs = BD.select(String.format(
			"select cliente.nome as nome from horto.pedidos pedido join horto.cliente cliente on pedido.solicitante = cliente.id WHERE pedido.id = %d;", id
		));
		try {
			while (rs.next()) {
				nome = Optional.of(rs.getString("nome")).orElse("CLIENTE NAO ENCONTRADO");
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return nome;
	}

	public static ServicoPedidos get() {
		if (INSTANCE == null)
			INSTANCE = new ServicoPedidos();
		return INSTANCE;
	}
}
