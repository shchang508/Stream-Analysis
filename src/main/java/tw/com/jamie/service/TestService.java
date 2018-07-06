package tw.com.jamie.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tw.com.jamie.domain.MPEG_TABLES;
import tw.com.jamie.domain.PMTs_CHANNEL;
import tw.com.jamie.domain.PMTs_CHANNEL_ELEMENTARY_STREAM;
import tw.com.jamie.domain.PMTs_CHANNEL_ELEMENTARY_STREAM_DESCRIPTOR;
import tw.com.jamie.main.Test;
import tw.com.jamie.util.XMLUtil;

public class TestService {
	
	private static final Logger logger = Logger.getLogger(TestService.class);
	
	public Object genTable (String path, Class clazz) {
		 
		return XMLUtil.convertXmlFileToObject(clazz, path);
				
	}

	public void genMpegExcel (XSSFWorkbook workbook, MPEG_TABLES table) {
		System.out.println("generate excel...");

		XSSFSheet sheet = workbook.createSheet("sheet01");

		// 固定欄位寬度設定
		sheet.setColumnWidth(0, 256 * 20);
		sheet.setColumnWidth(1, 256 * 20);
		sheet.setColumnWidth(2, 256 * 20);

		XSSFRow title_header = sheet.createRow(0);

		XSSFCell title00 = title_header.createCell(0);
		title00.setCellValue("Name of the Stream");

		XSSFCell title01 = title_header.createCell(1);
		title01.setCellValue("TELETEXT");

		XSSFCell title02 = title_header.createCell(2);
		title02.setCellValue("Resolution");

		XSSFCell cell = null;
		int initRow = 1;
		
		
			XSSFRow detailRow = sheet.createRow(initRow);
			
			cell = detailRow.createCell(1);
			
			List<PMTs_CHANNEL> pmtsChannelList = table.getPmts().getPmtsChannelList();
			logger.info("Number of Channels : " + pmtsChannelList.size());

			for (PMTs_CHANNEL c : pmtsChannelList) {
				logger.info("ServiceNumber : " + c.getServiceNumber());
				List<PMTs_CHANNEL_ELEMENTARY_STREAM> elementaryStreamList = c.getPmtsChannelEementaryStreamList();
				for (PMTs_CHANNEL_ELEMENTARY_STREAM e : elementaryStreamList) {
					logger.info("STREAM-TYPE : " + e.getStreamType());
					
					if ("TELETEXT".equals(e.getStreamType())) {
						cell.setCellValue("Y");
						break;
					} else {
						cell.setCellValue("N");
						break;
					}
	
				}	
			}
			
			
			
			cell = detailRow.createCell(1);
			cell.setCellValue("222");
			
			cell = detailRow.createCell(2);
			cell.setCellValue("33344445555");
		
	}
}
