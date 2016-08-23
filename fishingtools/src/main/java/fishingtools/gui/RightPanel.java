package fishingtools.gui;

import java.awt.BorderLayout;


import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fishingtools.gui.model.FishingRodsTableModel;
import fishingtools.gui.model.SqlFishingRodsTableModel;

public class RightPanel extends JPanel {

	public static JTable table;
	private TableFrame tableFrame;
	private String tittle = "TABLE";

	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		RightPanel.table = table;
	}

	public RightPanel(TableFrame tableFrame) {
		super();
		this.tableFrame = tableFrame;

		setBorder(BorderFactory.createTitledBorder(tittle));
		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		addTable();

	}

	private void addTable() {

		// FishingRodsTableModel model = new FishingRodsTableModel();
		
		SqlFishingRodsTableModel model = new SqlFishingRodsTableModel();
		
		table = new JTable(model);
		table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		
		add(scrollPane, BorderLayout.CENTER);

	}

}
