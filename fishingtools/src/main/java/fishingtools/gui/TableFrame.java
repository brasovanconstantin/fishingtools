package fishingtools.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TableFrame extends JFrame {

	private JPanel bigPanel;
	private JPanel searchPanel;
	private JPanel mainPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel bottomPanel;

	public JPanel getLeftPanel() {
		return leftPanel;
	}

	public JPanel getRightPanel() {
		return rightPanel;
	}

	public JPanel getBottomPanel() {
		return bottomPanel;
	}

	public void start() {

		initGUI();
		requestFocusInWindow();
		setVisible(true);
	}

	private void initGUI() {
		setTitle("Fishing Rods");
		setMinimumSize(new Dimension(1300, 550));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	

		mainPanel = new JPanel(new BorderLayout());
		
		getContentPane().add(mainPanel);
		
		leftPanel = new LeftPanel(this);
		mainPanel.add(leftPanel, BorderLayout.WEST);
		
		rightPanel = new RightPanel(this);
		mainPanel.add(rightPanel, BorderLayout.CENTER);
		
		bottomPanel = new BottomPanel(this);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		bigPanel = new JPanel(new BorderLayout());
		bigPanel.add(mainPanel, BorderLayout.CENTER);
		searchPanel = new SearchPanel(this);
		bigPanel.add(searchPanel, BorderLayout.NORTH);
		getContentPane().add(bigPanel);
	}

}
