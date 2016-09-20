package fishingtools.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import fishingtools.dao.impl.FishingRodsDaoImpl;
import fishingtools.domain.FishingRods;
import fishingtools.domain.Power;
import fishingtools.gui.model.SqlFishingRodsTableModel;

public class SearchPanel extends JPanel {

	private JLabel searchLabel;
	private JTextField searchTextField;
	private JButton searchButton;
	private JButton resetButton;
	private JComboBox<String> fieldsComboBox;
	private JLabel searchResultLabel;
	private TableFrame tableFrame;

	public SearchPanel(TableFrame tableFrame) {
		super();
		this.tableFrame = tableFrame;

		setBorder(new EtchedBorder());
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		layout.setHgap(25);
		setLayout(layout);

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		String colType = RightPanel.table.getModel().getColumnName(1);
		String colMaterial = RightPanel.table.getModel().getColumnName(4);
		model.addElement(colType);
		model.addElement(colMaterial);
		fieldsComboBox = new JComboBox<>(model);
		add(fieldsComboBox);

		searchLabel = new JLabel();
		searchLabel.setSize(15, 15);

		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("searchIcon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg = img.getScaledInstance(searchLabel.getWidth(), searchLabel.getHeight(), 0);

		add(searchLabel);

		searchTextField = new JTextField(10);
		add(searchTextField);
		add(new JLabel(" "));

		searchButton = new JButton("Search");
		searchButton.setIcon(new ImageIcon(dimg));
		searchButton.setToolTipText("Click to search");
		add(searchButton);
		
		resetButton = new JButton("Reset");
		resetButton.setToolTipText("Click to reset the text field");
		add(resetButton);
		
		searchResultLabel = new JLabel("");
		add(searchResultLabel);
		
		
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FishingRodsDaoImpl rodDao = new FishingRodsDaoImpl();
				List<FishingRods> list = rodDao.findAll();
				SqlFishingRodsTableModel model = (SqlFishingRodsTableModel) RightPanel.table.getModel();
				model.setRods(list);
				searchTextField.setText("");
				searchResultLabel.setText("");
			
				
			}
		});
		
		

		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				search(searchTextField.getText());

			}

		});
	}

	protected void search(String text) {

		FishingRodsDaoImpl rodDao = new FishingRodsDaoImpl();
		String columnName = fieldsComboBox.getSelectedItem().toString();
		List<FishingRods> list = rodDao.findBy(columnName, text);
		SqlFishingRodsTableModel model = (SqlFishingRodsTableModel) RightPanel.table.getModel();
		model.setRods(list);
		int foundRows = list.size();
		int totalRows = (int) rodDao.count();
		searchResultLabel.setText("total found rows " + foundRows + " from " + totalRows );
	}
}
