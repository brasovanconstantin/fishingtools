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

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import fishingtools.domain.FishingRods;
import fishingtools.domain.Power;

public class SearchPanel extends JPanel {

	private JLabel searchLabel;
	private JTextField searchTextField;
	private JButton searchButton;
	private JComboBox<String> fieldsComboBox;
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
		searchLabel.setSize(25, 25);

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

		searchButton = new JButton("Search");
		searchButton.setIcon(new ImageIcon(dimg));
		add(searchButton);

		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				search(searchTextField.getText());

			}

			private void search(String text) {
				
				 for(int i = 0; i < RightPanel.table.getRowCount(); i++){
				        for(int j = 0; j < RightPanel.table.getColumnCount(); j++){
				            if(RightPanel.table.getModel().getValueAt(i, j).equals(text)){
				                System.out.println(RightPanel.table.getModel().getValueAt(i, j));
				            }
				        }
				    }
				
			}
		});
	}
}
