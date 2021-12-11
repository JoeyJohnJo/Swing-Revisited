package horto.gui.telas.pedidos;

import horto.dtos.Pedido;
import horto.gui.Janela;
import horto.gui.componentes.Botao;
import horto.gui.componentes.Tabela;
import horto.gui.componentes.Titulo;
import horto.gui.telas.MenuInicial;
import horto.gui.telas.Tela;
import horto.servicos.ServicoPedidos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ListarPedidos extends Tela {

	private final ServicoPedidos servico = ServicoPedidos.get();
	private final List<Pedido> pedidos = new ArrayList<>();

	private final Tabela tabela = new Tabela();
	private final Botao voltar = new Botao("Voltar", 80, 50, this::voltarClicked);
	private final Botao novo = new Botao("Novo", 80, 50, this::novoClicked);
	private final Botao salvar = new Botao("Salvar", 80, 50, this::saveClicked);
	private final Botao remover = new Botao("Remover", 100, 50, this::removeClicked);
	private final Botao recarregar = new Botao("Recarregar", 120, 50, e -> populateTable());

	public void createFrame() {
		populateTable();
		add(new Titulo("Pedidos"), BorderLayout.NORTH);
		add(new JScrollPane(tabela), BorderLayout.CENTER);
		var voltarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		voltarPanel.add(salvar);
		voltarPanel.add(remover);
		voltarPanel.add(novo);
		voltarPanel.add(recarregar);
		voltarPanel.add(voltar);
		add(voltarPanel, BorderLayout.SOUTH);
	}

	public ListarPedidos(Janela parent) {
		super(parent);
		createFrame();
	}

	public void voltarClicked(ActionEvent e) {
		parent.mudarParaTela(MenuInicial.class);
	}

	public void novoClicked(ActionEvent e) {
		parent.mudarParaTela(NovoPedido.class);
	}

	public void removeClicked(ActionEvent e) {
		if (tabela.getSelectedRow() < 0) return;
		var linha = ((DefaultTableModel) tabela.getModel()).getDataVector().get(tabela.getSelectedRow());
		var idDoProduto = (Integer) linha.get(0);
		servico.deletar(idDoProduto);
		populateTable();
	}

	public void saveClicked(ActionEvent e) {
		((DefaultTableModel) tabela.getModel()).getDataVector().forEach(it -> {
			var pedidoEquivalente = pedidos.stream().filter(p -> p.getId() == (Integer) it.get(0)).findFirst().orElse(Pedido.builder().build());
			servico.editar(
				Pedido.builder()
					.id((Integer) it.get(0))
					.produto(pedidoEquivalente.getProduto())
					.solicitante(pedidoEquivalente.getSolicitante())
					.origem((String) it.get(3))
					.finalidade((String) it.get(4))
					.valor((Double) it.get(5))
					.build()
			);

		});
	}

	void populateTable() {
		pedidos.clear();
		pedidos.addAll(servico.buscar());
		tabela.populate(List.of("ID", "PRODUTO", "SOLICITANTE", "ORIGEM", "FINALIDADE", "VALOR"),
			pedidos.stream()
				.map(it -> List.<Object>of(
					it.getId(),
					servico.especieDoProdutoDoPedido(it.getId()),
					servico.nomeDoClienteDoPedido(it.getId()),
					it.getOrigem(),
					it.getFinalidade(),
					it.getValor()
				)).collect(Collectors.toList())
		);
		tabela.revalidate();
		tabela.repaint();
	}


}