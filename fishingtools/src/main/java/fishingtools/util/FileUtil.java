package fishingtools.util;

import java.io.File;

import javax.swing.JFileChooser;


public class FileUtil {
	
	public static String showOpenFileDialog(){
		
		JFileChooser fileChooser = new JFileChooser();
		
		int result = fileChooser.showOpenDialog(null);
		
		if(result == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			return file.getAbsolutePath();
		}
		return null;
	}
	
	/**
	 * Show save file dialog
	 * @return
	 */
	public static String showSaveFileDialog(){
		
		JFileChooser fileChooser = new JFileChooser();
	
		int result = fileChooser.showSaveDialog(null);
	
		if(result == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			return file.getAbsolutePath();
		}
		return null;
	}

}
