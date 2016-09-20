package fishingtools.gui;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

class DateEditor extends AbstractCellEditor implements TableCellEditor {

	private Object cellEditorValue;

	@Override
	public Object getCellEditorValue() {

		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		if(this.cellEditorValue instanceof Date){
			System.out.println("ok");
		}else{
			System.out.println("not ok");
		}
		
		try{
			return dateFormat.format(this.cellEditorValue);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, new String[] {
					"Wrong date format", "dd/MM/yyyy" },
					"Atention", JOptionPane.ERROR_MESSAGE);
		}
		
		return dateFormat.format(this.cellEditorValue);
		// return this.cellEditorValue;
	}

	public void setCellEditorValue(Object value) {
		this.cellEditorValue = value;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {
		final JTextField textField = new JTextField();
		// JPanel c = new JPanel();

		if (value instanceof Date) {
			final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			textField.setText(dateFormat.format(value));

			textField.addCaretListener(new CaretListenerForDate(this, textField));

			// c.add(textField);
		}

		return textField;
	}

}