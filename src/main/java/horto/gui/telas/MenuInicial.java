package horto.gui.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import horto.gui.Janela;
import horto.gui.componentes.Botao;
import horto.gui.componentes.Titulo;
import horto.gui.telas.clientes.ListarClientes;
import horto.gui.telas.pedidos.ListarPedidos;
import horto.gui.telas.produtos.ListarProdutos;

public class MenuInicial extends Tela {
	JPanel box = new JPanel();
	private final Titulo titulo = new Titulo("Horto CRUD");
	private final Botao btnClients = new Botao("Clientes", this::btnClientsClicked);
	private final Botao btnProducts = new Botao("Produtos", this::btnProductsClicked);
	private final Botao btnPedidos = new Botao("Pedidos", this::btnPedidosClicked);

	public MenuInicial(Janela parent) {
		super(parent);
		createButtonPanel();
		add(titulo, BorderLayout.NORTH);
		add(box, BorderLayout.CENTER);
	}

	void createButtonPanel() {
		box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		var constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 10, 10, 10);
		top.setLayout(new GridBagLayout());
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		top.add(btnClients, constraints);
		top.add(btnProducts, constraints);
		bottom.add(btnPedidos, constraints);
		box.add(top);
		box.add(bottom);
	}


	public void btnClientsClicked(ActionEvent e) {
		parent.mudarParaTela(ListarClientes.class);
	}
	public void btnProductsClicked(ActionEvent e) {
		parent.mudarParaTela(ListarProdutos.class);
	}
	public void btnPedidosClicked(ActionEvent e) {
		parent.mudarParaTela(ListarPedidos.class);
	}

}
