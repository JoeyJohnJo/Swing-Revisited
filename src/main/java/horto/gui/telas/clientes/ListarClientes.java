package horto.gui.telas.clientes;

import horto.dtos.Cliente;
import horto.gui.Janela;
import horto.gui.componentes.Botao;
import horto.gui.componentes.Tabela;
import horto.gui.componentes.Titulo;
import horto.gui.telas.MenuInicial;
import horto.gui.telas.Tela;
import horto.servicos.ServicoClientes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;


public class ListarClientes extends Tela {

	private final ServicoClientes servico = ServicoClientes.get();

	private final Tabela tabela = new Tabela();
	private final Botao voltar = new Botao("Voltar", 80, 50, this::voltarClicked);
	private final Botao novo = new Botao("Novo", 80, 50, this::novoClicked);
	private final Botao salvar = new Botao("Salvar", 80, 50, this::saveClicked);
	private final Botao remover = new Botao("Remover", 100, 50, this::removeClicked);
	private final Botao recarregar = new Botao("Recarregar", 120, 50, e -> populateTable());

	public void createFrame() {
		populateTable();
		add(new Titulo("Clientes"), BorderLayout.NORTH);
		add(new JScrollPane(tabela), BorderLayout.CENTER);
		var voltarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		voltarPanel.add(salvar);
		voltarPanel.add(remover);
		voltarPanel.add(novo);
		voltarPanel.add(recarregar);
		voltarPanel.add(voltar);
		add(voltarPanel, BorderLayout.SOUTH);
	}

	public ListarClientes(Janela parent){
		super(parent);
		createFrame();
	}

	public void voltarClicked(ActionEvent e) {
		parent.mudarParaTela(MenuInicial.class);
	}
	public void novoClicked(ActionEvent e) {
		parent.mudarParaTela(NovoCliente.class);
	}
	public void removeClicked(ActionEvent e) {
		if (tabela.getSelectedRow() < 0) return;
		var linha = ((DefaultTableModel) tabela.getModel()).getDataVector().get(tabela.getSelectedRow());
		var idDoCliente = (Integer) linha.get(0);
		servico.deletar(idDoCliente);
		populateTable();
	}
	public void saveClicked(ActionEvent e) {
		((DefaultTableModel) tabela.getModel()).getDataVector().forEach(it ->
			servico.editar(
				Cliente.builder()
					.id((Integer) it.get(0))
					.nome((String) it.get(1))
					.documento((String) it.get(2))
					.telefone((String) it.get(3))
					.email((String) it.get(4))
					.cidade((String) it.get(5))
					.build()
			)
		);
	}

	void populateTable() {
		tabela.populate(List.of("ID", "NOME", "DOCUMENTO", "TELEFONE", "EMAIL", "CIDADE"),
			servico.buscar().stream()
				.map(it -> List.<Object>of(
					it.getId(),
					it.getNome(),
					it.getDocumento(),
					it.getTelefone(),
					it.getEmail(),
					it.getCidade())
				)
				.toList()
			);
		tabela.revalidate();
		tabela.repaint();
	}

}