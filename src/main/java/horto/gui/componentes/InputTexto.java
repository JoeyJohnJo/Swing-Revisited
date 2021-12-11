package horto.gui.componentes;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class InputTexto extends JPanel {

	private JLabel label;
	private final JTextField textField = new JTextField();

	public InputTexto(String label) {
		this.label = new JLabel(label);
		this.label.setFont(new Font("Arial Rounded MT", Font.BOLD, 15));
		textField.setPreferredSize(new Dimension(400, 30));
		add(this.label);
		add(textField);
	}

	public String valor() {
		return Optional.ofNullable(this.textField.getText()).orElse("");
	}

	public boolean preenchido() {
		return !Optional.ofNullable(this.textField.getText()).orElse("").isBlank();
	}
}
