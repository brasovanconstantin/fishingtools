package fishingtools.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import fishingtools.domain.FishingRods;
import fishingtools.gui.model.SqlFishingRodsTableModel;
import fishingtools.services.FileService;
import fishingtools.services.impl.ExcelFileService;
import fishingtools.services.impl.JsonFileService;
import fishingtools.services.impl.XmlFileService;
import fishingtools.util.FileUtil;

public class BottomPanel extends JPanel {

	public static JButton deleteButton;
	public static JButton exportToJsonButton;
	public static JButton exportToXMLButton;
	public static JButton exportToExcelButton;

	public static JButton getExportToExcelButton() {
		return exportToExcelButton;
	}

	public static void setExportToExcelButton(JButton exportToExcelButton) {
		BottomPanel.exportToExcelButton = exportToExcelButton;
	}

	private TableFrame tableFrame;
	private FileService fileService;

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

		addExportToExcelButton();

	}

	private void addExportToExcelButton() {

		exportToExcelButton = new JButton("Export to Excel");
		add(exportToExcelButton);

		exportToExcelButton.setToolTipText("Click to export the table to excel");

		exportToExcelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				exportToExcel();
			}
		});

	}

	private void addExportToXMLButton() {

		exportToXMLButton = new JButton("Export to XML");
		add(exportToXMLButton);

		exportToXMLButton.setToolTipText("Click to export the table to a XML file");

		exportToXMLButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exportToXML();

			}
		});

	}

	private void addExportToJsonButton() {

		exportToJsonButton = new JButton("Export to JSON");
		add(exportToJsonButton);

		exportToJsonButton.setToolTipText("Click to export the table to a text file");

		exportToJsonButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exportToJson();

			}

		});

	}

	private void addDeleteButton() {

		deleteButton = new JButton("Delete");
		add(deleteButton);

		deleteButton.setToolTipText("Click to delete the selected row");

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int rows[] = RightPanel.table.getSelectedRows();
				if (rows.length == 0) {
					JOptionPane.showMessageDialog(null, "Please select a row from the table!", "No selected row",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
	
				((SqlFishingRodsTableModel) RightPanel.table.getModel()).removeRods(rows);
			}
		});

	}

	public void exportToExcel() {
		SqlFishingRodsTableModel model = (SqlFishingRodsTableModel) RightPanel.table.getModel();
		exportToExcel(model.getRods());

	}

	private void exportToExcel(List<FishingRods> rods) {
		fileService = new ExcelFileService();
		try {
			// obtain file where to save
			String path = FileUtil.showSaveFileDialog();
			// check if a file was selected
			if (path == null)
				return;
			fileService.saveAll(rods, path.concat(".xls"));
			JOptionPane.showMessageDialog(null, "Rod was successfully exported", "Export to Excel",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error on export to Excel", "Export to Excel",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	public void exportToJson() {
		SqlFishingRodsTableModel model = (SqlFishingRodsTableModel) RightPanel.table.getModel();
		exportToJSON(model.getRods());
	}

	private void exportToJSON(List<FishingRods> rods) {
		fileService = new JsonFileService();
		try {

			String path = FileUtil.showSaveFileDialog();
			if (path == null)
				return;
			fileService.saveAll(rods, path.concat(".json"));
			JOptionPane.showMessageDialog(null, "Rod was successfully exported", "Export to JSON",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error on export to JSON", "Export to JSON", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	public void exportToXML() {
		SqlFishingRodsTableModel model = (SqlFishingRodsTableModel) RightPanel.table.getModel();
		exportToXML(model.getRods());

	}

	private void exportToXML(List<FishingRods> rods) {
		fileService = new XmlFileService();
		try {
			// obtain file where to save
			String path = FileUtil.showSaveFileDialog();
			// check if a file was selected
			if (path == null)
				return;
			fileService.saveAll(rods, path.concat(".xml"));
			JOptionPane.showMessageDialog(null, "Rod was successfully exported", "Export to XML",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error on export to XML", "Export to XML", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

}
