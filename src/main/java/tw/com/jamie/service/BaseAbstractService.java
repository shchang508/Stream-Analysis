package tw.com.jamie.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class BaseAbstractService {
	protected Map<String, XSSFCellStyle> cellStyleMap;
	

	protected void setStyle(XSSFWorkbook workbook) {
		cellStyleMap = new HashMap<String, XSSFCellStyle>();
		
		XSSFCellStyle style_01 = workbook.createCellStyle();
		
		cellStyleMap.put("style_01", style_01);
		
		//create a font
		XSSFFont font_01 = workbook.createFont();
		font_01.setFontHeightInPoints((short) 10);
		font_01.setFontName("Arial");
		font_01.setBold(true);
		style_01.setFont(font_01);
//		style_01.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//		style_01.setFillForegroundColor(IndexedColors.BLACK.getIndex());
//		font_01.setColor(IndexedColors.WHITE.getIndex());
		style_01.setAlignment(XSSFCellStyle.ALIGN_CENTER); //置中
		
	}
}
