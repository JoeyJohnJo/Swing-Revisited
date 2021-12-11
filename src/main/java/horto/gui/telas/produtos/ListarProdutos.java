package horto.gui.telas.produtos;

import horto.dtos.Produto;
import horto.gui.Janela;
import horto.gui.componentes.Botao;
import horto.gui.componentes.Tabela;
import horto.gui.componentes.Titulo;
import horto.gui.telas.MenuInicial;
import horto.gui.telas.Tela;
import horto.servicos.ServicoProdutos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.List;


public class ListarProdutos extends Tela {

	private final ServicoProdutos servico = ServicoProdutos.get();

	private final Tabela tabela = new Tabela();
	private final Botao voltar = new Botao("Voltar", 80, 50, this::voltarClicked);
	private final Botao novo = new Botao("Novo", 80, 50, this::novoClicked);
	private final Botao salvar = new Botao("Salvar", 80, 50, this::saveClicked);
	private final Botao remover = new Botao("Remover", 100, 50, this::removeClicked);
	private final Botao recarregar = new Botao("Recarregar", 120, 50, e -> populateTable());

	public void createFrame() {
		populateTable();
		add(new Titulo("Produtos"), BorderLayout.NORTH);
		add(new JScrollPane(tabela), BorderLayout.CENTER);
		var voltarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		voltarPanel.add(salvar);
		voltarPanel.add(remover);
		voltarPanel.add(novo);
		voltarPanel.add(recarregar);
		voltarPanel.add(voltar);
		add(voltarPanel, BorderLayout.SOUTH);
	}

	public ListarProdutos(Janela parent) {
		super(parent);
		createFrame();
	}

	public void voltarClicked(ActionEvent e) {
		parent.mudarParaTela(MenuInicial.class);
	}

	public void novoClicked(ActionEvent e) {
		parent.mudarParaTela(NovoProduto.class);
	}

	public void removeClicked(ActionEvent e) {
		if (tabela.getSelectedRow() < 0) return;
		var linha = ((DefaultTableModel) tabela.getModel()).getDataVector().get(tabela.getSelectedRow());
		var idDoProduto = (Integer) linha.get(0);
		servico.deletar(idDoProduto);
		populateTable();
	}

	public void saveClicked(ActionEvent e) {
		((DefaultTableModel) tabela.getModel()).getDataVector().forEach(it ->
			servico.editar(
				Produto.builder()
					.id((Integer) it.get(0))
					.nome((String) it.get(1))
					.especie((String) it.get(2))
					.tipo((String) it.get(3))
					.preco(Double.parseDouble((String) it.get(4)))
					.inicio((Date) it.get(5))
					.fim((Date) it.get(6))
					.build()
			)
		);
	}

	void populateTable() {
		tabela.populate(List.of("ID", "NOME CIENT.", "ESPECIE", "TIPO", "PRECO", "INICIO", "FIM"),
			servico.buscar().stream()
				.map(it -> List.<Object>of(
					it.getId(),
					it.getNome(),
					it.getEspecie(),
					it.getTipo(),
					it.getPreco(),
					it.getInicio(),
					it.getFim())
				)
				.toList()
		);
		tabela.revalidate();
		tabela.repaint();
	}


}