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
import fishingtools.services.impl.JsonFileService;
import fishingtools.services.impl.XmlFileService;
import fishingtools.util.FileUtil;



public class BottomPanel extends JPanel {

	public static JButton deleteButton;
	public static JButton exportToJsonButton;
	public static JButton exportToXMLButton;
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

	}

	private void addExportToXMLButton() {

		exportToXMLButton = new JButton("Export to XML");
		add(exportToXMLButton);
		
		exportToXMLButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SqlFishingRodsTableModel model = (SqlFishingRodsTableModel) RightPanel.table.getModel();
				exportToXML(model.getRods());
				
			}

			private void exportToXML(List<FishingRods> rods) {
				fileService = new XmlFileService();
				try {
					// obtain file where to save
					String path = FileUtil.showSaveFileDialog();
					// check if a file was selected
					if (path == null) return;
					fileService.saveAll(rods, path.concat(".xml"));
					JOptionPane.showMessageDialog(null, 
							"Users was successfully exported", "Export to XML", 
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, 
							"Error on export to XML", "Export to XML", 
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				
			}
		});

	}

	private void addExportToJsonButton() {

		exportToJsonButton = new JButton("Export to JSON");
		add(exportToJsonButton);
		
		exportToJsonButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SqlFishingRodsTableModel model = (SqlFishingRodsTableModel) RightPanel.table.getModel();
				exportToJSON(model.getRods());
			}

			private void exportToJSON(List<FishingRods> rods) {
				fileService = new JsonFileService();
				try {
					
					String path = FileUtil.showSaveFileDialog();
					if (path == null) return;
					fileService.saveAll(rods, path.concat(".json"));
						JOptionPane.showMessageDialog(null, 
							"Users was successfully exported", "Export to JSON", 
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, 
							"Error on export to JSON", "Export to JSON", 
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				
			}

			
		});

	}

	private void addDeleteButton() {

		deleteButton = new JButton("Delete");
		add(deleteButton);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int row = RightPanel.table.getSelectedRow();
				
				if (row >= 1) {
					((SqlFishingRodsTableModel)RightPanel.table.getModel()).removeRod(row);
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row from the table!", "No selected row",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

	}

}
