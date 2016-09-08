package fishingtools.gui.listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import fishingtools.util.Constants;

public class MyFocusListener implements FocusListener {

	@Override
	public void focusGained(FocusEvent event) {
		JTextField source = (JTextField) event.getSource();
		source.setText("");
		
	}

	@Override
	public void focusLost(FocusEvent event) {
		JTextField source = (JTextField) event.getSource();
		if (!source.getText().isEmpty()) {
			source.setBorder(new TitledBorder(Constants.DEFAULT_BORDER, source.getName()));
		}
		
	}

}
