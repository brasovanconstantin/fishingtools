package fishingtools.util;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class Constants {
	
	public static Border DEFAULT_BORDER = UIManager.getBorder("TextField.border");
	public static Border ERROR_BORDER = BorderFactory.createLineBorder(Color.RED);

}
