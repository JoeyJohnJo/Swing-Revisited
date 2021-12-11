package horto.gui.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Botao extends JButton {

	public Botao(String text) {
		super(text);
		defaultButton();
	}

	public Botao(String text, ActionListener onClick) {
		super(text);
		defaultButton();
		onClick(onClick);
	}

	public Botao(String text, int width, int height, ActionListener onClick) {
		super(text);
		defaultButtonOfSize(width, height);
		onClick(onClick);
	}

	private void defaultButton() {
		setFocusable(false);
		setFont(new Font("Arial Rounded MT", Font.BOLD, 15));
		setPreferredSize(new Dimension(300, 75));
		setBackground(Color.decode("#1e90ff"));
	}
	private void defaultButtonOfSize(int width, int height) {
		setFocusable(false);
		setFont(new Font("Arial Rounded MT", Font.BOLD, 15));
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.decode("#1e90ff"));
	}

	public void onClick(ActionListener action) {
		addActionListener(action);
	}
}
