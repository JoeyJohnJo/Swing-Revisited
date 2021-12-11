package horto.gui.telas.produtos;

import horto.dtos.Produto;
import horto.gui.Janela;
import horto.gui.componentes.Botao;
import horto.gui.componentes.InputTexto;
import horto.gui.componentes.Titulo;
import horto.gui.telas.Tela;
import horto.servicos.ServicoProdutos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;

public class NovoProduto extends Tela {

	private final ServicoProdutos servico = ServicoProdutos.get();

	private InputTexto nome =      new InputTexto("Nome Científico");
	private InputTexto especie = new InputTexto("Espécie        ");
	private InputTexto tipo =     new InputTexto("Tipo           ");
	private InputTexto preco =  new InputTexto("Preco          ");
	private InputTexto inicio =    new InputTexto("Data Inicio    ");
	private InputTexto fim =    new InputTexto("Data Fim       ");

	public NovoProduto(Janela parent) {
		super(parent);
		add(new Titulo("Novo Produto"), BorderLayout.NORTH);
		var baixo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		var centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
		centro.add(nome);
		centro.add(especie);
		centro.add(tipo);
		centro.add(preco);
		centro.add(inicio);
		centro.add(fim);
		baixo.add(new Botao("Voltar", 80, 50, this::voltarClicked));
		baixo.add(new Botao("Salvar", 80, 50, this::salvarClicked));
		add(centro, BorderLayout.CENTER);
		add(baixo, BorderLayout.SOUTH);
	}

	private void voltarClicked(ActionEvent e) {
		parent.mudarParaTela(ListarProdutos.class);
	}

	private void salvarClicked(ActionEvent e) {
		if (nome.preenchido() &&
			especie.preenchido() &&
			tipo.preenchido() &&
			preco.preenchido() &&
			inicio.preenchido() &&
			fim.preenchido())
			servico.inserir(Produto.builder()
				.nome(nome.valor())
				.especie(especie.valor())
				.tipo(tipo.valor())
				.preco(Double.parseDouble(preco.valor()))
				.inicio(Date.valueOf(inicio.valor()))
				.fim(Date.valueOf(fim.valor()))
				.build()
			);

	}
}
