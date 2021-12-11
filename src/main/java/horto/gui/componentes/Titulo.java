package horto.gui.componentes;

import javax.swing.*;
import java.awt.*;

public class Titulo extends JPanel {

	private final JLabel title;

	public Titulo(String text) {
		super(new FlowLayout(FlowLayout.CENTER));
		title = new JLabel(text, SwingConstants.CENTER);
		title.setFont(new Font("Arial Rounded MT", Font.BOLD, 30));
		title.setPreferredSize(new Dimension(300, 75));
		add(title);
	}

}
