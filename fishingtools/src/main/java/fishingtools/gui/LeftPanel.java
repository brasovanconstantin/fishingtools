package fishingtools.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fishingtools.domain.FishingRods;
import fishingtools.domain.Power;
import fishingtools.gui.model.SqlFishingRodsTableModel;
import fishingtools.util.DateUtil;

public class LeftPanel extends JPanel {

	public static JTextField typeTextField;
	public static JTextField lengthTextField;
	public static JComboBox<Power> powerComboBox;
	public static JTextField materialTextField;
	public static JTextField numberOfPiecesTextField;
	public static JTextField dateOfManufactureTextField;
	public static JTextField priceTextField;
	public static JTextField availableInStockTextField;
	public static JButton saveButton;
	public static JButton clearButton;
	private String tittle = "INPUT FORM";
	private TableFrame tableFrame;

	public static JTextField getTypeTextField() {
		return typeTextField;
	}

	public static void setTypeTextField(JTextField typeTextField) {
		LeftPanel.typeTextField = typeTextField;
	}

	public static JTextField getLengthTextField() {
		return lengthTextField;
	}

	public static void setLengthTextField(JTextField lengthTextField) {
		LeftPanel.lengthTextField = lengthTextField;
	}

	public static JTextField getMaterialTextField() {
		return materialTextField;
	}

	public static void setMaterialTextField(JTextField materialTextField) {
		LeftPanel.materialTextField = materialTextField;
	}

	public static JTextField getNumberOfPiecesTextField() {
		return numberOfPiecesTextField;
	}

	public static void setNumberOfPiecesTextField(JTextField numberOfPiecesTextField) {
		LeftPanel.numberOfPiecesTextField = numberOfPiecesTextField;
	}

	public static JTextField getDateOfManufactureTextField() {
		return dateOfManufactureTextField;
	}

	public static void setDateOfManufactureTextField(JTextField dateOfManufactureTextField) {
		LeftPanel.dateOfManufactureTextField = dateOfManufactureTextField;
	}

	public static JTextField getPriceTextField() {
		return priceTextField;
	}

	public static void setPriceTextField(JTextField priceTextField) {
		LeftPanel.priceTextField = priceTextField;
	}

	public static JTextField getAvailableInStockTextField() {
		return availableInStockTextField;
	}

	public static void setAvailableInStockTextField(JTextField availableInStockTextField) {
		LeftPanel.availableInStockTextField = availableInStockTextField;
	}

	public static JButton getSaveButton() {
		return saveButton;
	}

	public static void setSaveButton(JButton saveButton) {
		LeftPanel.saveButton = saveButton;
	}

	public static JButton getClearButton() {
		return clearButton;
	}

	public static void setClearButton(JButton clearButton) {
		LeftPanel.clearButton = clearButton;
	}

	public static JComboBox<Power> getPowerComboBox() {
		return powerComboBox;
	}

	public static void setPowerComboBox(JComboBox<Power> powerComboBox) {
		LeftPanel.powerComboBox = powerComboBox;
	}

	public LeftPanel(TableFrame tableFrame) {
		super();
		this.tableFrame = tableFrame;

		setPreferredSize(new Dimension(200, 480));
		setBorder(BorderFactory.createTitledBorder(tittle));
		// BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		addJTextFields();
		add(new JLabel(" "));
		addSaveButton();
		add(new JLabel(" "));
		addClearButton();

	}

	private void addClearButton() {

		clearButton = new JButton("Clear ");
		add(clearButton);

		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearTextFields(getParent());

			}

		});

	}

	private void clearTextFields(Container container) {
		for (Component c : container.getComponents()) {
			if (c instanceof JTextField) {
				JTextField f = (JTextField) c;
				f.setText("");
			} else if (c instanceof Container)
				clearTextFields((Container) c);
		}
	}

	private void addSaveButton() {

		saveButton = new JButton(" Save ");
		add(saveButton);

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				boolean valid = validateFields();
				double format;

				if (valid) {
					FishingRods rod = new FishingRods();
					rod.setType(typeTextField.getText().trim());
					rod.setLenght(Double.parseDouble(lengthTextField.getText().trim()));
					rod.setPower(Power.getPower(powerComboBox.getSelectedItem()));
					rod.setMaterial(materialTextField.getText().trim());
					rod.setNumberOfPieces(Integer.parseInt(numberOfPiecesTextField.getText().trim()));
					Date date;
					try {
						date = DateUtil.getDateFromString(dateOfManufactureTextField.getText().trim());
						rod.setDateOfManufacture(date);
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null, new String[] { "Wrong date format", "dd/MM/yyyy" },
								"Atention", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}

					rod.setPrice(Double.parseDouble(priceTextField.getText().trim()));
					rod.setAvailableInStock(Integer.parseInt(availableInStockTextField.getText().trim()));

					SqlFishingRodsTableModel model = (SqlFishingRodsTableModel) RightPanel.table.getModel();
					model.addRod(rod);

					clearTextFields(getParent());
				} else {
					// TODO display error
				}

			}

			private boolean validateFields() {

				boolean valid = true;
				Color redColor = Color.RED;

				if (typeTextField.getText().trim().isEmpty()) {
					valid = false;
					// change background color
					typeTextField.setBorder(BorderFactory.createLineBorder(redColor));
				}
				if (lengthTextField.getText().trim().isEmpty()) {
					valid = false;
					lengthTextField.setBorder(BorderFactory.createLineBorder(redColor));
				}
				if (materialTextField.getText().trim().isEmpty()) {
					valid = false;
					materialTextField.setBorder(BorderFactory.createLineBorder(redColor));
				}
				if (numberOfPiecesTextField.getText().trim().isEmpty()) {
					valid = false;
					numberOfPiecesTextField.setBorder(BorderFactory.createLineBorder(redColor));
				}
				if (dateOfManufactureTextField.getText().trim().isEmpty()) {
					valid = false;
					dateOfManufactureTextField.setBorder(BorderFactory.createLineBorder(redColor));
				}
				if (priceTextField.getText().trim().isEmpty()) {
					valid = false;
					priceTextField.setBorder(BorderFactory.createLineBorder(redColor));
				}
				if (availableInStockTextField.getText().trim().isEmpty()) {
					valid = false;
					availableInStockTextField.setBorder(BorderFactory.createLineBorder(redColor));
				}
				/*
				 * if (typeTextField.getText().trim().isEmpty() ||
				 * (lengthTextField.getText().trim().isEmpty()) ||
				 * (materialTextField.getText().trim().isEmpty()) ||
				 * (numberOfPiecesTextField.getText().trim().isEmpty()) ||
				 * (dateOfManufactureTextField.getText().trim().isEmpty()) ||
				 * (priceTextField.getText().trim().isEmpty()) ||
				 * (availableInStockTextField.getText().trim().isEmpty())) {
				 * return false; }
				 */

				return valid;
			}
		});

	}

	private void addJTextFields() {

		add(new JLabel("Type:"));
		typeTextField = new JTextField("sampletype");
		typeTextField.setForeground(Color.BLUE);
		typeTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				typeTextField.setText("");

			}
		});
		add(typeTextField);

		add(new JLabel("Length:"));
		lengthTextField = new JTextField("0.00");
		lengthTextField.setForeground(Color.BLUE);
		lengthTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				lengthTextField.setText("");

			}
		});
		add(lengthTextField);

		add(new JLabel("Power:"));
		powerComboBox = createPowerComboBox();
		add(powerComboBox);

		add(new JLabel("Material:"));
		materialTextField = new JTextField("samplematerial");
		materialTextField.setForeground(Color.BLUE);
		materialTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				materialTextField.setText("");

			}
		});
		add(materialTextField);

		add(new JLabel("Number Of Pieces:"));
		numberOfPiecesTextField = new JTextField("0");
		numberOfPiecesTextField.setForeground(Color.BLUE);
		numberOfPiecesTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				numberOfPiecesTextField.setText("");

			}
		});
		add(numberOfPiecesTextField);

		add(new JLabel("Date of Manufacture:"));
		dateOfManufactureTextField = new JTextField("03/05/2016");
		dateOfManufactureTextField.setForeground(Color.BLUE);
		dateOfManufactureTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				dateOfManufactureTextField.setText("");

			}
		});
		add(dateOfManufactureTextField);

		add(new JLabel("Price:"));
		priceTextField = new JTextField("0.00");
		priceTextField.setForeground(Color.BLUE);
		priceTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				priceTextField.setText("");

			}
		});
		add(priceTextField);

		add(new JLabel("Available in Stock:"));
		availableInStockTextField = new JTextField("0");
		availableInStockTextField.setForeground(Color.BLUE);
		availableInStockTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				availableInStockTextField.setText("");

			}
		});
		add(availableInStockTextField);

	}

	private JComboBox<Power> createPowerComboBox() {

		DefaultComboBoxModel<Power> model = new DefaultComboBoxModel<>();
		model.addElement(Power.LIGHT);
		model.addElement(Power.MEDIUM);
		model.addElement(Power.HEAVY);
		model.addElement(Power.ULTRA_HEAVY);

		powerComboBox = new JComboBox<>(model);

		return powerComboBox;
	}

}
