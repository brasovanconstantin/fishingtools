package fishingtools.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fishingtools.domain.FishingRods;
import fishingtools.domain.Power;
import fishingtools.gui.model.SqlFishingRodsTableModel;

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

		setPreferredSize(new Dimension(200, 500));
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

	}

	private void addSaveButton() {

		saveButton = new JButton(" Save ");
		add(saveButton);

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
					rod.setDateOfManufacture(Date.parse(dateOfManufactureTextField.getText().trim()));
					rod.setPrice(Double.parseDouble(priceTextField.getText().trim()));
					rod.setAvailableInStock(Integer.parseInt(availableInStockTextField.getText().trim()));
					
					SqlFishingRodsTableModel model = (SqlFishingRodsTableModel) RightPanel.table.getModel();
					model.addRod(rod);
					
					typeTextField.setText("");
					lengthTextField.setText("");
					materialTextField.setText("");
					numberOfPiecesTextField.setText("");
					dateOfManufactureTextField.setText("");
					priceTextField.setText("");
					availableInStockTextField.setText("");
				}

			}

			private boolean validateFields() {

				if (typeTextField.getText().trim().isEmpty() || (lengthTextField.getText().trim().isEmpty())
						|| (materialTextField.getText().trim().isEmpty())
						|| (numberOfPiecesTextField.getText().trim().isEmpty())
						|| (dateOfManufactureTextField.getText().trim().isEmpty())
						|| (priceTextField.getText().trim().isEmpty())
						|| (availableInStockTextField.getText().trim().isEmpty())) {
					return false;
				}

				return true;
			}
		});

	}

	private void addJTextFields() {

		add(new JLabel("Type:"));
		typeTextField = new JTextField();
		add(typeTextField);

		add(new JLabel("Length:"));
		lengthTextField = new JTextField();
		add(lengthTextField);

		add(new JLabel("Power:"));
		powerComboBox = createPowerComboBox();
		add(powerComboBox);

		add(new JLabel("Material:"));
		materialTextField = new JTextField();
		add(materialTextField);

		add(new JLabel("Number Of Pieces:"));
		numberOfPiecesTextField = new JTextField();
		add(numberOfPiecesTextField);

		add(new JLabel("Date of Manufacture:"));
		dateOfManufactureTextField = new JTextField();
		add(dateOfManufactureTextField);

		add(new JLabel("Price:"));
		priceTextField = new JTextField();
		add(priceTextField);

		add(new JLabel("Available in Stock:"));
		availableInStockTextField = new JTextField();
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
