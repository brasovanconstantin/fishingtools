package fishingtools.gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fishingtools.dao.impl.FishingRodsDaoImpl;
import fishingtools.domain.FishingRods;


public class SqlFishingRodsTableModel extends AbstractTableModel {

	private List<FishingRods> rods = new ArrayList<>();
	private String[] columnNames = { "Id", "Type", "Length", "Power", "Material", "Number of pieces",
			"Date of manufacture", "Price", "Available in stock" };

	FishingRodsDaoImpl rodDao = new FishingRodsDaoImpl();

	public SqlFishingRodsTableModel() {
		super();
		this.rods = rodDao.findAll();
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

	public void addRod(FishingRods rod) {

		rodDao.save(rod);
		rods = rodDao.findAll();
		fireTableDataChanged();
	}

	@Deprecated
	public void removeRod(int row){
		FishingRods rod = rods.get(row);
		// delete user from database
		rodDao.delete(rod.getId());
		// get updated list of users from database
		rods = rodDao.findAll();
		// fire an event to table that content of the model was changed
		fireTableDataChanged();
	}
	
	public void removeRods(int[] rows){
		List<FishingRods> rodsToRemove = new ArrayList<>();
		for (int row : rows) {
			rodsToRemove.add(rods.get(row));
		}
		for (FishingRods fishingRods : rodsToRemove) {
			rodDao.delete(fishingRods.getId());
			rods = rodDao.findAll();
			fireTableDataChanged();
		}
	}

	
	public FishingRods getRod(int row) {
		return rods.get(row);
	}

	public List<FishingRods> getRods() {
		return rods;
	}

	public void setRods(List<FishingRods> rods) {
		this.rods = rods;
		fireTableDataChanged();
	}
	
	
}
