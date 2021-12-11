package horto.gui.componentes;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class DropDown<T> extends JPanel {

	private JLabel label;
	private final JComboBox<T> dropDown;

	public DropDown(String label, T[] items) {
		this.label = new JLabel(label);
		this.label.setFont(new Font("Arial Rounded MT", Font.BOLD, 15));
		dropDown = new JComboBox<>(items);
		dropDown.setPreferredSize(new Dimension(400, 30));
		add(this.label);
		add(dropDown);
	}

	public T valor() {
		return Optional.ofNullable((T) this.dropDown.getSelectedItem()).orElse(null);
	}

	public boolean preenchido() {
		return !(this.dropDown.getSelectedItem() == null);
	}

	public void atualizarLista(T[] items) {
		dropDown.removeAllItems();
		dropDown.setModel(new DefaultComboBoxModel<>(items));
		dropDown.revalidate();
		dropDown.repaint();
	}
}
