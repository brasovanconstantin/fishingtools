package fishingtools.gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

class CaretListenerForDate implements CaretListener
{       
    private JTextField textField;
    private DateEditor dateEditor;

    public CaretListenerForDate(DateEditor dateEditor, JTextField textField)
    {
        this.textField = textField;
        this.dateEditor = dateEditor;
    }

    public void caretUpdate(CaretEvent e) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        try {
            dateEditor.setCellEditorValue(dateFormat.parse(textField.getText()));
        } catch (ParseException e1) {
            System.err.println(String.format("Worng date format! [%s] Error is [%s]", textField.getText(), e1.getMessage() ));
            //JOptionPane.showMessageDialog(null, "Error format date");
            dateEditor.setCellEditorValue(textField.getText());
        }
System.out.println("enter");
    }                               
}