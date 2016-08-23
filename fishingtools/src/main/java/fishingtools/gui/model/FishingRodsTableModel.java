package fishingtools.gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.FileChooserUI;
import javax.swing.table.AbstractTableModel;

import org.apache.poi.sl.draw.geom.IfElseExpression;

import fishingtools.domain.FishingRods;
import fishingtools.services.FileService;
import fishingtools.services.impl.XmlFileService;
import fishingtools.util.FileUtil;



public class FishingRodsTableModel extends AbstractTableModel {

	private FileService fileService;

	List<FishingRods> rods = new ArrayList<>();
	String[] columnNames = { "Id", "Type", "Length", "Power", "Material", "Number of pieces", "Date of manufacture",
			"Price", "Available in stock" };

	public FishingRodsTableModel() {
		super();

		fileService = new XmlFileService();
		String path = FileUtil.showOpenFileDialog();
		if (path == null)
			return;

		try {
			rods = fileService.readAll(path);
			System.out.println(rods);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getColumnCount() {

		return columnNames.length;
	}

	@Override
	public int getRowCount() {

		return rods.size();
	}

	@Override
	public Object getValueAt(int row, int column) {

		FishingRods rod = rods.get(row);

		if (column == 0) {
			return rod.getId();
		} else if (column == 1) {
			return rod.getType();
		} else if (column == 2) {
			return rod.getLenght();
		} else if (column == 3) {
			return rod.getPower();
		} else if (column == 4) {
			return rod.getMaterial();
		} else if (column == 5) {
			return rod.getNumberOfPieces();
		} else if (column == 6) {
			return rod.getDateOfManufacture();
		} else if (column == 7) {
			return rod.getPrice();
		} else if (column == 8) {
			return rod.getAvailableInStock();
		}

		return "no data";
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	public void addUser(FishingRods rod) {

		rods.add(rod);
		fireTableDataChanged();
	}
	
	public void removeUser(int row){
		
		rods.remove(row);		
		fireTableDataChanged();
	}
	
	public FishingRods getRod(int row) {
		return rods.get(row);
	}

}
