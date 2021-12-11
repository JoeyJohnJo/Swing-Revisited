package horto.gui.telas.pedidos;

import horto.dtos.Cliente;
import horto.dtos.Pedido;
import horto.dtos.Produto;
import horto.gui.Janela;
import horto.gui.componentes.Botao;
import horto.gui.componentes.DropDown;
import horto.gui.componentes.InputTexto;
import horto.gui.componentes.Titulo;
import horto.gui.telas.Tela;
import horto.servicos.ServicoClientes;
import horto.servicos.ServicoPedidos;
import horto.servicos.ServicoProdutos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NovoPedido extends Tela {

	private final ServicoPedidos servicoPedidos = ServicoPedidos.get();
	private final ServicoProdutos servicoProdutos = ServicoProdutos.get();
	private final ServicoClientes servicoClientes = ServicoClientes.get();

	private DropDown<Produto> produto = new DropDown<>("Produto", servicoProdutos.buscar().toArray(new Produto[0]));
	private DropDown<Cliente> solicitante = new DropDown<>("Solicitante", servicoClientes.buscar().toArray(new Cliente[0]));
	private InputTexto origem = new InputTexto("Origem        ");
	private InputTexto finalidade = new InputTexto("Finalidade           ");
	private InputTexto quantidade = new InputTexto("Quantidade          ");

	public NovoPedido(Janela parent) {
		super(parent);
		add(new Titulo("Novo Pedido"), BorderLayout.NORTH);
		var baixo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		var centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
		centro.add(produto);
		centro.add(solicitante);
		centro.add(origem);
		centro.add(finalidade);
		centro.add(quantidade);
		baixo.add(new Botao("Voltar", 80, 50, this::voltarClicked));
		baixo.add(new Botao("Salvar", 80, 50, this::salvarClicked));
		add(centro, BorderLayout.CENTER);
		add(baixo, BorderLayout.SOUTH);
	}

	private void voltarClicked(ActionEvent e) {
		parent.mudarParaTela(ListarPedidos.class);
	}

	private void salvarClicked(ActionEvent e) {
		if (produto.preenchido() &&
				solicitante.preenchido() &&
				origem.preenchido() &&
				finalidade.preenchido() &&
				quantidade.preenchido())
			servicoPedidos.inserir(Pedido.builder()
				.produto(produto.valor().getId())
				.solicitante(solicitante.valor().getId())
				.origem(origem.valor())
				.finalidade(finalidade.valor())
				.valor(produto.valor().getPreco() * Double.parseDouble(quantidade.valor()))
				.build()
			);
	}

	@Override
	public void onEnter() {
		produto.atualizarLista(servicoProdutos.buscar().toArray(new Produto[0]));
		solicitante.atualizarLista(servicoClientes.buscar().toArray(new Cliente[0]));
	}
}
