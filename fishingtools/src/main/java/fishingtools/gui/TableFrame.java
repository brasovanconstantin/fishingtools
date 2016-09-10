package fishingtools.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;




public class TableFrame extends JFrame {

	private JPanel bigPanel;
	private JPanel searchPanel;
	private JPanel mainPanel;
	private LeftPanel leftPanel;
	private RightPanel rightPanel;
	private BottomPanel bottomPanel;
	private JMenuBar menuBar;

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

		menuBar = createMenuBar();
		setJMenuBar(menuBar);
	}

	private JMenuBar createMenuBar() {

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F1);
		JMenu infoMenu = new JMenu("Info");
		infoMenu.setMnemonic(KeyEvent.VK_F2);

		menuBar.add(fileMenu);
		menuBar.add(infoMenu);

		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				leftPanel.save();
			}
		});

		JMenu exportMenuItem = new JMenu("Export");
		JMenuItem exportToJsonMenuItem = new JMenuItem("Export to JSON");
		exportToJsonMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bottomPanel.exportToJson();

			}
		});
		exportMenuItem.add(exportToJsonMenuItem);
		JMenuItem exportToXMLMenuItem = new JMenuItem("Export to XML");
		exportToXMLMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bottomPanel.exportToXML();

			}
		});
		exportMenuItem.add(exportToXMLMenuItem);
		JMenuItem exportToExcelMenuItem = new JMenuItem("Export to Excel");
		exportToExcelMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bottomPanel.exportToExcel();

			}
		});
		exportMenuItem.add(exportToExcelMenuItem);

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(TableFrame.this, "Are you sure you want to close?", "Exit",
						JOptionPane.OK_CANCEL_OPTION);
				if (response == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});
		
		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame aboutFrame = new JFrame("About");
				aboutFrame.setSize(300,200);
				aboutFrame.setLocationRelativeTo(null);
				aboutFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				aboutFrame.setVisible(true);
				
				BorderLayout l = new BorderLayout(10, 10);
				JPanel aboutPanel = new JPanel(l);
				aboutPanel.setBorder( BorderFactory.createEmptyBorder(10, 10, 10, 10));
				aboutPanel.setBackground(Color.WHITE);
				JPanel imagePanel = new JPanel(new BorderLayout());			
				JPanel centerImagePanel = new JPanel();
				centerImagePanel.setPreferredSize(new Dimension(100, 200));
				imagePanel.add(centerImagePanel, BorderLayout.CENTER);
				
				JLabel imageLabel = new JLabel();
				imageLabel.setSize(100,100);
				BufferedImage img = null;
				try {
					img = ImageIO.read(getClass().getResourceAsStream("fishingrods.png"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				Image dimg = img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), 0);
				imageLabel.setIcon(new ImageIcon(dimg));
				imagePanel.add(imageLabel);
				aboutPanel.add(imagePanel, BorderLayout.WEST);
				
				JTextArea textArea = new JTextArea();
				textArea.setLineWrap(true);
				textArea.setText("\nApplication for warehouse\n\nVersion: 1.1\n\nAll rights reserved");
				textArea.setEditable(false);
				
				aboutPanel.add(textArea, BorderLayout.CENTER);	 			
				imagePanel.setBackground(Color.WHITE);
				imagePanel.setOpaque(true);
				
				getContentPane().add(aboutPanel);
				aboutFrame.add(aboutPanel);
				
				
				
				
			}
		});

		fileMenu.add(saveMenuItem);
		fileMenu.add(exportMenuItem);
		fileMenu.add(exitMenuItem);
		infoMenu.add(aboutMenuItem);

		KeyStroke controlS = KeyStroke.getKeyStroke("control S");
		saveMenuItem.setAccelerator(controlS);
		KeyStroke f4 = KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK);
		exitMenuItem.setAccelerator(f4);

		return menuBar;
	}

}
