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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class SearchPanel extends JPanel {

	private JLabel searchLabel;
	private JTextField searchTextField;
	private JButton searchButton;
	private TableFrame tableFrame;

	public SearchPanel(TableFrame tableFrame) {
		super();
		this.tableFrame = tableFrame;
		
		setBorder(new EtchedBorder());
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		layout.setHgap(25);
		setLayout(layout);

		//URL url = getClass().getResource("searchIcon.png");
		
		searchLabel = new JLabel();
		searchLabel.setSize(20,20);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(getClass().getResourceAsStream("searchIcon.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimg =  img.getScaledInstance(searchLabel.getWidth(), searchLabel.getHeight(),0);
	   System.out.println(dimg);
	  
	  
		add(searchLabel);
		
		searchTextField = new JTextField(10);
		add(searchTextField);
		
		searchButton = new JButton("Search");
		searchButton.setIcon(new ImageIcon(dimg));
		add(searchButton);
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//search(searchTextField.getText());
				
			}
		});
	}
}
