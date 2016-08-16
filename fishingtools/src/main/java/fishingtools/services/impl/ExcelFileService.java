package fishingtools.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import fishingtools.domain.FishingRods;
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
			
			Row row1 = sheet.createRow(0);		
			Cell celll = row1.createCell(0);
			celll.setCellValue("Type");
			celll = row1.createCell(0);
			
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
		// TODO Auto-generated method stub
		return null;
	}

}
