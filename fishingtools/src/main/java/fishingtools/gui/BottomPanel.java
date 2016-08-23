package fishingtools.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class BottomPanel extends JPanel {

	public static JButton deleteButton;
	public static JButton exportToJsonButton;
	public static JButton exportToXMLButton;
	private TableFrame tableFrame;

	public static JButton getDeleteButton() {
		return deleteButton;
	}

	public static void setDeleteButton(JButton deleteButton) {
		BottomPanel.deleteButton = deleteButton;
	}

	public static JButton getExportToJsonButton() {
		return exportToJsonButton;
	}

	public static void setExportToJsonButton(JButton exportToJsonButton) {
		exportToJsonButton = exportToJsonButton;
	}

	public static JButton getExportToXMLButton() {
		return exportToXMLButton;
	}

	public static void setExportToXMLButton(JButton exportToXMLButton) {
		exportToXMLButton = exportToXMLButton;
	}

	public BottomPanel(TableFrame tableFrame) {
		super();
		this.tableFrame = tableFrame;

		setBorder(new EtchedBorder());
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		layout.setHgap(25);
		setLayout(layout);

		addDeleteButton();

		addExportToJsonButton();
		
		addExportToXMLButton();

	}

	private void addExportToXMLButton() {
		
		exportToXMLButton = new JButton("Export to XML");
		add(exportToXMLButton);
		
	}

	private void addExportToJsonButton() {

		exportToJsonButton = new JButton("Export to JSON");
		add(exportToJsonButton);

	}

	private void addDeleteButton() {

		deleteButton = new JButton("Delete");
		add(deleteButton);

	}

}
