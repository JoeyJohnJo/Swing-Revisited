package horto.gui.telas;

import horto.Constantes;
import horto.gui.Janela;

import javax.swing.*;
import java.awt.*;

public class Tela extends JPanel {
	protected Janela parent;

	public Tela(Janela parent) {
		this.parent = parent;
		setSize(new Dimension(Constantes.LARGURA, Constantes.ALTURA));
		setLayout(new BorderLayout());
	}

	public void onEnter() {
		// Sobre escrever esta funcao caso precise executar alguma acao quando entrar nesta tela
	}

}
