package horto.gui.componentes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Tabela extends JTable {
	public Tabela() {
		super();
		defaultTable();
	}

	private void defaultTable() {
		setCellSelectionEnabled(false);
		setRowSelectionAllowed(true);
		setBounds(30, 40, 200, 300);
		setFont(new Font("Arial Rounded MT", Font.TRUETYPE_FONT, 12));
		getTableHeader().setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		getTableHeader().setReorderingAllowed(false);
		setShowHorizontalLines(true);
	}

	public void populate(List<String> columns, List<List<Object>> rows) {
		var model = new DefaultTableModel();
		columns.forEach(model::addColumn);
		rows.forEach(it -> model.addRow(it.toArray()));
		setModel(model);
	}
}
