package fishingtools.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import fishingtools.domain.FishingRods;
import fishingtools.domain.Power;
import fishingtools.gui.listeners.MyFocusListener;
import fishingtools.gui.model.SqlFishingRodsTableModel;
import fishingtools.util.Constants;
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
	private List<JTextField> invalidTextFields = new LinkedList<>();
	private Set<String> errorMessages = new LinkedHashSet<>();
	private FocusListener myListener = new MyFocusListener();
	private final String EMPTY_FIELD_ERROR = "All fields must be completed";

	public static JTextField getTypeTextField() {
		return typeTextField;
	}

	public static void setTypeTextField(JTextField typeTextField) {
		LeftPanel.typeTextField = typeTextField;
	}

	public static JTextField getLengthTextField() {
		return lengthTextField;
	}

	public static void setLengthTextField(JFormattedTextField lengthTextField) {
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

	public static void setNumberOfPiecesTextField(JFormattedTextField numberOfPiecesTextField) {
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
		clearButton.setToolTipText("Click to clear all fields");

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

		saveButton.setToolTipText("Click to save the data");

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				boolean valid = validateFields();

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
						// JOptionPane.showMessageDialog(null, new String[] {
						// "Wrong date format", "dd/MM/yyyy" },
						// "Atention", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}

					rod.setPrice(Double.parseDouble(priceTextField.getText().trim()));
					rod.setAvailableInStock(Integer.parseInt(availableInStockTextField.getText().trim()));

					SqlFishingRodsTableModel model = (SqlFishingRodsTableModel) RightPanel.table.getModel();
					model.addRod(rod);

					clearTextFields(getParent());
				} else {
					String[] errors = errorMessages.toArray(new String[errorMessages.size()]);
					invalidTextFields.forEach(object->{
						object.setText("");
					});
					JOptionPane.showMessageDialog(null, errors, "Atention", JOptionPane.WARNING_MESSAGE);
					errorMessages.clear();
					invalidTextFields.clear();
				}

			}

			private boolean validateFields() {

				boolean valid = true;

				if (typeTextField.getText().trim().isEmpty()) {
					valid = false;
					// change background color
					// typeTextField.setBorder(BorderFactory.createLineBorder(redColor));
					invalidTextFields.add(typeTextField);
					errorMessages.add(EMPTY_FIELD_ERROR);
				}

				if (lengthTextField.getText().trim().isEmpty()) {
					valid = false;
					// lengthTextField.setBorder(BorderFactory.createLineBorder(redColor));
					invalidTextFields.add(lengthTextField);
					errorMessages.add(EMPTY_FIELD_ERROR);
				} else {
					try {
						Double.parseDouble(lengthTextField.getText());
					} catch (Exception e) {
						valid = false;
						errorMessages.add("invalid number format for field Length");
						invalidTextFields.add(lengthTextField);
					}
				}
				if (materialTextField.getText().trim().isEmpty()) {
					valid = false;
					// materialTextField.setBorder(BorderFactory.createLineBorder(redColor));
					invalidTextFields.add(materialTextField);
				}
				if (numberOfPiecesTextField.getText().trim().isEmpty()) {
					valid = false;
					// numberOfPiecesTextField.setBorder(BorderFactory.createLineBorder(redColor));
					invalidTextFields.add(numberOfPiecesTextField);
					errorMessages.add(EMPTY_FIELD_ERROR);

				} else {
					try {
						Integer.parseInt(numberOfPiecesTextField.getText());
					} catch (Exception e) {
						valid = false;
						errorMessages.add("invalid number format for field Number of Pieces");
						invalidTextFields.add(numberOfPiecesTextField);
					}

				}
				if (dateOfManufactureTextField.getText().trim().isEmpty()) {
					valid = false;
					// dateOfManufactureTextField.setBorder(BorderFactory.createLineBorder(redColor));
					invalidTextFields.add(dateOfManufactureTextField);
				}
				if (priceTextField.getText().trim().isEmpty()) {
					valid = false;
					// priceTextField.setBorder(BorderFactory.createLineBorder(redColor));
					invalidTextFields.add(priceTextField);
					errorMessages.add(EMPTY_FIELD_ERROR);
				} else {
					try {
						System.out.println(priceTextField.getText());
						Double.parseDouble(priceTextField.getText());
					} catch (Exception e) {
						valid = false;
						errorMessages.add("invalid number format for field Price");
						invalidTextFields.add(priceTextField);
					}
				}
				if (availableInStockTextField.getText().trim().isEmpty()) {
					valid = false;
					// availableInStockTextField.setBorder(BorderFactory.createLineBorder(redColor));
					invalidTextFields.add(availableInStockTextField);
					errorMessages.add(EMPTY_FIELD_ERROR);
				} else {
					try {
						Integer.parseInt(availableInStockTextField.getText());
					} catch (Exception e) {
						valid = false;
						errorMessages.add("invalid number format for field Available in Stock");
						invalidTextFields.add(availableInStockTextField);
					}
				}

				for (JTextField jTextField : invalidTextFields) {
					jTextField.setBorder(Constants.ERROR_BORDER);

				}

				if (!invalidTextFields.isEmpty()) {
					invalidTextFields.get(0).requestFocus();
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
		//add(new JLabel("Type:"));
		typeTextField = new JTextField("sampletype");
		typeTextField.setBorder(new TitledBorder("Type:"));
		typeTextField
				.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) typeTextField.getPreferredSize().getHeight()));
		typeTextField.setForeground(Color.BLUE);
		typeTextField.addFocusListener(myListener);
		add(typeTextField);

		//add(new JLabel("Length:"));		
		lengthTextField = new JTextField("0.00");
		lengthTextField.setBorder(new TitledBorder("Length:"));
		lengthTextField
				.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) lengthTextField.getPreferredSize().getHeight()));
		lengthTextField.setForeground(Color.BLUE);
		lengthTextField.addFocusListener(myListener);
		add(lengthTextField);

		//add(new JLabel("Power:"));
		powerComboBox = createPowerComboBox();
		powerComboBox.setBorder(new TitledBorder("Power:"));
		powerComboBox
				.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) powerComboBox.getPreferredSize().getHeight()));
		add(powerComboBox);

		//add(new JLabel("Material:"));
		materialTextField = new JTextField("samplematerial");
		materialTextField.setBorder(new TitledBorder("Material:"));
		materialTextField.setMaximumSize(
				new Dimension(Integer.MAX_VALUE, (int) materialTextField.getPreferredSize().getHeight()));
		materialTextField.setForeground(Color.BLUE);
		materialTextField.addFocusListener(myListener);
		add(materialTextField);

		//add(new JLabel("Number Of Pieces:"));
		numberOfPiecesTextField = new JFormattedTextField("0");
		numberOfPiecesTextField.setBorder(new TitledBorder("Number Of Pieces:"));
		numberOfPiecesTextField.setMaximumSize(
				new Dimension(Integer.MAX_VALUE, (int) numberOfPiecesTextField.getPreferredSize().getHeight()));
		numberOfPiecesTextField.setForeground(Color.BLUE);
		numberOfPiecesTextField.addFocusListener(myListener);
		add(numberOfPiecesTextField);

		//add(new JLabel("Date of Manufacture:"));
		dateOfManufactureTextField = new JTextField("03/05/2016");
		dateOfManufactureTextField.setBorder(new TitledBorder("Date of Manufacture:"));
		dateOfManufactureTextField.setMaximumSize(
				new Dimension(Integer.MAX_VALUE, (int) dateOfManufactureTextField.getPreferredSize().getHeight()));
		dateOfManufactureTextField.setForeground(Color.BLUE);
		dateOfManufactureTextField.addFocusListener(myListener);
		add(dateOfManufactureTextField);

		//add(new JLabel("Price:"));
		priceTextField = new JTextField("0.00");
		priceTextField.setBorder(new TitledBorder("Price:"));
		priceTextField
				.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) priceTextField.getPreferredSize().getHeight()));
		priceTextField.setForeground(Color.BLUE);
		priceTextField.addFocusListener(myListener);
		add(priceTextField);

		//add(new JLabel("Available in Stock:"));
		availableInStockTextField = new JTextField("0");
		availableInStockTextField.setBorder(new TitledBorder("Available in Stock:"));
		availableInStockTextField.setMaximumSize(
				new Dimension(Integer.MAX_VALUE, (int) availableInStockTextField.getPreferredSize().getHeight()));
		availableInStockTextField.setForeground(Color.BLUE);
		availableInStockTextField.addFocusListener(myListener);
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
