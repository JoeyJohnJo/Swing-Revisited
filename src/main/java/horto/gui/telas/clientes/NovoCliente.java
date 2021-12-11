package horto.gui.telas.clientes;

import horto.dtos.Cliente;
import horto.gui.Janela;
import horto.gui.componentes.Botao;
import horto.gui.componentes.InputTexto;
import horto.gui.componentes.Titulo;
import horto.gui.telas.Tela;
import horto.servicos.ServicoClientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NovoCliente extends Tela {

	private final ServicoClientes servicoClientes = ServicoClientes.get();

	private InputTexto nome = new InputTexto("Nome          ");
	private InputTexto documento = new InputTexto("Documento");
	private InputTexto email = new InputTexto("Email           ");
	private InputTexto telefone = new InputTexto("Telefone     ");
	private InputTexto cidade = new InputTexto("Cidade        ");

	public NovoCliente(Janela parent) {
		super(parent);
		add(new Titulo("Novo Cliente"), BorderLayout.NORTH);
		var baixo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		var centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
		centro.add(nome);
		centro.add(documento);
		centro.add(email);
		centro.add(telefone);
		centro.add(cidade);
		baixo.add(new Botao("Voltar", 80, 50, this::voltarClicked));
		baixo.add(new Botao("Salvar", 80, 50, this::salvarClicked));
		add(centro, BorderLayout.CENTER);
		add(baixo, BorderLayout.SOUTH);
	}

	private void voltarClicked(ActionEvent e) {
		parent.mudarParaTela(ListarClientes.class);
	}

	private void salvarClicked(ActionEvent e) {
		if (nome.preenchido() && documento.preenchido() && email.preenchido() && telefone.preenchido() && cidade.preenchido())
			servicoClientes.inserir(Cliente.builder()
				.nome(nome.valor())
				.documento(documento.valor())
				.email(email.valor())
				.telefone(telefone.valor())
				.cidade(cidade.valor())
				.build()
			);

	}
}
