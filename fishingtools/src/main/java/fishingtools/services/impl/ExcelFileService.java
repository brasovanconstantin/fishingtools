package fishingtools.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import fishingtools.domain.FishingRods;
import fishingtools.domain.Power;
import fishingtools.services.FileService;

public class ExcelFileService implements FileService {
	
	HSSFWorkbook workbook = new HSSFWorkbook();

	@Override
	public void saveAll(List<FishingRods> rods, String path) throws Exception {

		File file = new File(path);

		FileOutputStream fos = new FileOutputStream(file);
		
		HSSFSheet sheet = workbook.createSheet("Sample sheet");		
		
		
		
		Row row = sheet.createRow(0);		
		Cell cell1 = row.createCell(0);
		cell1.setCellValue("Type");
		cell1.setCellStyle(getStyle(workbook));
		
		Cell cell2 = row.createCell(1);
		cell2.setCellValue("Length");
		cell2.setCellStyle(getStyle(workbook));
		
		Cell cell3 = row.createCell(2);
		cell3.setCellValue("Power");
		cell3.setCellStyle(getStyle(workbook));
		
		Cell cell4 = row.createCell(3);
		cell4.setCellValue("Material");
		cell4.setCellStyle(getStyle(workbook));
		
		Cell cell5 = row.createCell(4);
		cell5.setCellValue("Number Of Pieces");
		cell5.setCellStyle(getStyle(workbook));
		
		Cell cell6 = row.createCell(5);
		cell6.setCellValue("Date Of Manufacture");
		cell6.setCellStyle(getStyle(workbook));
		
		Cell cell7 = row.createCell(6);
		cell7.setCellValue("Price");
		cell7.setCellStyle(getStyle(workbook));
		
		Cell cell8 = row.createCell(7);
		cell8.setCellValue("Available In Stock");
		cell8.setCellStyle(getStyle(workbook));
		
		int rowIndex = 1;
		for (int i = rowIndex; i < rods.size(); i++) {
			
			Row row1 = sheet.createRow(i);		
			Cell celll = row1.createCell(0);
			celll.setCellValue(rods.get(i).getType());
			celll = row1.createCell(1);
			celll.setCellValue(rods.get(i).getLenght());
			celll = row1.createCell(2);
			celll.setCellValue(rods.get(i).getPower().toString());
			celll = row1.createCell(3);
			celll.setCellValue(rods.get(i).getMaterial());
			celll = row1.createCell(4);
			celll.setCellValue(rods.get(i).getNumberOfPieces());
			celll = row1.createCell(5);
			celll.setCellValue(rods.get(i).getDateOfManufacture());
			celll = row1.createCell(6);
			celll.setCellValue(rods.get(i).getPrice());
			celll = row1.createCell(7);
			celll.setCellValue(rods.get(i).getAvailableInStock());
			celll = row1.createCell(8);
		}
		
		autosizeColumns(sheet);	
		
		workbook.write(fos);
			
		workbook.close();

	}

	private CellStyle getStyle(HSSFWorkbook workbook) {
		
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderTop((short) 6);
		style.setBorderTop((short) 1);
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 8);
		font.setFontName("Courier New");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		
		return style;
	}

	private void autosizeColumns(HSSFSheet sheet) {

		HSSFRow row = workbook.getSheetAt(0).getRow(0);
		/*sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);
		sheet.autoSizeColumn(7);*/
		
		for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
			workbook.getSheetAt(0).autoSizeColumn(colNum);
		}
	}

	@Override
	public List<FishingRods> readAll(String path) throws Exception {
		
		List<FishingRods> rods = new ArrayList<>();
	    FileInputStream inputStream = new FileInputStream(new File(path));
	 
	    Workbook workbook = new HSSFWorkbook(inputStream);
	    Sheet firstSheet = (Sheet) workbook.getSheetAt(0);
	    Iterator<Row> iterator = firstSheet.iterator();
	 
	    while (iterator.hasNext()) {
	        Row nextRow = iterator.next();
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	        FishingRods rod = new FishingRods();
	 
	        while (cellIterator.hasNext()) {
	            Cell nextCell = cellIterator.next();
	            int columnIndex = nextCell.getColumnIndex();
	 
	            switch (columnIndex) {
	            case 1:
	            	rod.setType((String) getCellValue(nextCell));
	                break;
	            case 2:
	                rod.setLenght((double) getCellValue(nextCell));
	                break;
	            case 3:
	                rod.setPower((Power) getCellValue(nextCell));
	                break;
	            case 4:
	            	rod.setMaterial((String) getCellValue(nextCell));
	                break;
	            case 5:
	            	rod.setNumberOfPieces((int) getCellValue(nextCell));
	                break;
	            case 6:
	            	rod.setDateOfManufacture((Date) getCellValue(nextCell));
	                break;
	            case 7:
	            	rod.setPrice((double) getCellValue(nextCell));
	                break;
	            case 8:
	            	rod.setAvailableInStock((int) getCellValue(nextCell));
	                break;
	            }
	 
	 
	        }
	        rods.add(rod);
	    }
	 
	    workbook.close();
	    inputStream.close();
	 
	    return rods;
		
	}

	private Object getCellValue(Cell nextCell) {
		 switch (nextCell.getCellType()) {
		    case Cell.CELL_TYPE_STRING:
		        return nextCell.getStringCellValue();
		 
		    case Cell.CELL_TYPE_BOOLEAN:
		        return nextCell.getBooleanCellValue();
		 
		    case Cell.CELL_TYPE_NUMERIC:
		        return nextCell.getNumericCellValue();
		    }
		 
		    return null;
	}

}
