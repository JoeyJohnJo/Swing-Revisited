package horto;

import horto.dtos.Cliente;
import horto.dtos.Pedido;
import horto.dtos.Produto;

public class Constantes {
	public static final int LARGURA = 750;
	public static final int ALTURA = 500;

	public static final String SELECT_CLIENTES = "SELECT * FROM horto.cliente;";
	public static final String SELECT_PRODUTOS = "SELECT * FROM horto.produto;";
	public static final String SELECT_PEDIDOS = "SELECT * FROM horto.pedidos;";

	public static String atualizarCliente(Cliente cliente) {
		return String.format(
			"UPDATE horto.cliente SET email = '%s', telefone = '%s', documento = '%s', nome = '%s', cidade = '%s' WHERE id = %d",
			cliente.getEmail(),
			cliente.getTelefone(),
			cliente.getDocumento(),
			cliente.getNome(),
			cliente.getCidade(),
			cliente.getId()
		);
	}

	public static String atualizarProduto(Produto produto) {
		return String.format(
			"UPDATE horto.produto SET especie = '%s', nome_cient = '%s', data_inicio = '%s', data_final = '%s', preco = %s, tipo = '%s' WHERE id = %d",
			produto.getEspecie(),
			produto.getNome(),
			produto.getInicio(),
			produto.getFim(),
			produto.getPreco(),
			produto.getTipo(),
			produto.getId()
		);
	}

	public static String deletarProduto(int idDoProduto) {
		return String.format("DELETE FROM horto.produto WHERE id = %d;", idDoProduto);
	}

	public static String deletarCliente(int idDoCliente) {
		return String.format("DELETE FROM horto.cliente WHERE id = %d;", idDoCliente);
	}

	public static String adicionarCliente(Cliente cliente) {
		return String.format(
			"INSERT INTO horto.cliente(nome, documento, email, telefone, cidade) VALUES ('%s', '%s', '%s', '%s', '%s');",
			cliente.getNome(),
			cliente.getDocumento(),
			cliente.getEmail(),
			cliente.getTelefone(),
			cliente.getCidade()
		);
	}

	public static String adicionarProduto(Produto produto) {
		return String.format(
			"INSERT INTO horto.produto(especie, nome_cient, data_inicio, data_final, preco, tipo) VALUES ('%s', '%s', '%s', '%s', %s, '%s');",
			produto.getEspecie(),
			produto.getNome(),
			produto.getInicio(),
			produto.getFim(),
			produto.getPreco(),
			produto.getTipo()
		);
	}

	public static String atualizarPedido(Pedido pedido) {
		return String.format(
			"UPDATE horto.pedidos SET produto = %d, solicitante = %d, origem = '%s', finalidade = '%s', valor = %s WHERE id = %d",
			pedido.getProduto(),
			pedido.getSolicitante(),
			pedido.getOrigem(),
			pedido.getFinalidade(),
			pedido.getValor(),
			pedido.getId()
		);
	}

	public static String deletarPedido(int id) {
		return String.format("DELETE FROM horto.pedidos WHERE id = %d;", id);
	}

	public static String adicionarPedido(Pedido pedido) {
		return String.format(
			"INSERT INTO horto.pedidos(produto, solicitante, origem, finalidade, valor) VALUES (%s, %s, '%s', '%s', %s);",
			pedido.getProduto(),
			pedido.getSolicitante(),
			pedido.getOrigem(),
			pedido.getFinalidade(),
			pedido.getValor()
		);
	}
}
