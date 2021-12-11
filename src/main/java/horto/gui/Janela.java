package horto.gui;

import horto.gui.telas.MenuInicial;
import horto.gui.telas.Tela;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Janela extends JFrame {

	private static final List<Tela> stack = new ArrayList<>();

	public Janela(int width, int height) {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(width, height));
		setBackground(Color.white);
		setLocationRelativeTo(null);
		add(new MenuInicial(this));
		setVisible(true);
	}

	public void mudarParaTela(Class<? extends Tela> state) {
		var tela = stack.stream().filter(state::isInstance)
			.findFirst().orElseGet(() -> {
				Tela instance = null;
				try {
					instance = state.getConstructor(Janela.class).newInstance(this);
					stack.add(instance);
					return instance;
				} catch (Exception e) { e.printStackTrace(); }
				return instance;
			});
		if (tela == null) return;
		tela.onEnter();
		getContentPane().removeAll();
		getContentPane().add(tela);
		revalidate();
		repaint();
	}
}
