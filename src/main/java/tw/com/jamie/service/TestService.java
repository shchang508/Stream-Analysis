package tw.com.jamie.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tw.com.jamie.domain.EIT_CHANNEL;
import tw.com.jamie.domain.EIT_CHANNEL_EVENT;
import tw.com.jamie.domain.MPEG_TABLES;
import tw.com.jamie.domain.NIT_ENTRY;
import tw.com.jamie.domain.PMTs_CHANNEL;
import tw.com.jamie.domain.PMTs_CHANNEL_ELEMENTARY_STREAM;
import tw.com.jamie.domain.PMTs_CHANNEL_ELEMENTARY_STREAM_DESCRIPTOR;
import tw.com.jamie.domain.TUNED_MULTIPLEX;
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
		sheet.setColumnWidth(3, 256 * 20);
		sheet.setColumnWidth(4, 256 * 20);
		sheet.setColumnWidth(5, 256 * 20);
		sheet.setColumnWidth(6, 256 * 20);
		sheet.setColumnWidth(7, 256 * 20);
		sheet.setColumnWidth(8, 256 * 20);
		sheet.setColumnWidth(9, 256 * 20);
		sheet.setColumnWidth(10, 256 * 20);

		XSSFRow title_header = sheet.createRow(0);

		XSSFCell title00 = title_header.createCell(0);
		title00.setCellValue("Name of the Stream");

		XSSFCell title01 = title_header.createCell(1);
		title01.setCellValue("Number of Channels");
		
		XSSFCell title02 = title_header.createCell(2);
		title02.setCellValue("Subtitle Language");
		
		XSSFCell title03 = title_header.createCell(3);
		title03.setCellValue("Audio Language");
		
		XSSFCell title04 = title_header.createCell(4);
		title04.setCellValue("TELETEXT");
		
		XSSFCell title05 = title_header.createCell(5);
		title05.setCellValue("Dolby Digital");

		XSSFCell title06 = title_header.createCell(6);
		title06.setCellValue("Resolution");
		
		XSSFCell title07 = title_header.createCell(7);
		title07.setCellValue("Visually Impaired");
		
		XSSFCell title08 = title_header.createCell(8);
		title08.setCellValue("HbbTV");
		
		XSSFCell title09 = title_header.createCell(9);
		title09.setCellValue("Freeview");
		
		XSSFCell title10 = title_header.createCell(10);
		title10.setCellValue("Rating");

		
		XSSFCell cell = null;
		int initRow = 1;
		
		
		XSSFRow detailRow = sheet.createRow(initRow);
		
		cell = detailRow.createCell(0);
		TUNED_MULTIPLEX tunerString = table.getTunedMultiplex();
		logger.info("Name of the Stream : " + tunerString.getTunerString());
		cell.setCellValue(tunerString.getTunerString());

		cell = detailRow.createCell(1);
		List<PMTs_CHANNEL> pmtsChannelList = table.getPmts().getPmtsChannelList();
		logger.info("Number of Channels : " + pmtsChannelList.size());
		cell.setCellValue(pmtsChannelList.size());
		
		
		for (PMTs_CHANNEL c : pmtsChannelList) {
			logger.info("ServiceNumber : " + c.getServiceNumber());
			List<PMTs_CHANNEL_ELEMENTARY_STREAM> elementaryStreamList = c.getPmtsChannelEementaryStreamList();
			for (PMTs_CHANNEL_ELEMENTARY_STREAM e : elementaryStreamList) {
				logger.info("AUDIO-LANGUAGE : " + e.getAudioLanguage());
				cell = detailRow.createCell(3);
				if () {
					
				}

				
				
				logger.info("STREAM-TYPE : " + e.getStreamType());
				cell = detailRow.createCell(4);
				if ("TELETEXT".equals(e.getStreamType())) {
					cell.setCellValue("Y");
					break;
				} else {
					cell.setCellValue("N");
					break;
				}
			}
		}
		
		cell = detailRow.createCell(9);
		List<NIT_ENTRY> entryList = table.getNit().getNitEntryList();
		for (NIT_ENTRY nc : entryList) {
			logger.info("NETWORK-NAME : " + nc.getNetworkName());
			if ("Freeview".equals(nc.getNetworkName())) {
				cell.setCellValue("Y");
				break;
			} else {
				cell.setCellValue("N");
				break;
			}
		}

		cell = detailRow.createCell(10);
		List<EIT_CHANNEL> eitChannelList = table.getEit().getEitChannelList();
		for (EIT_CHANNEL ec : eitChannelList) {
			List<EIT_CHANNEL_EVENT> eventList = ec.getEitChannelEventList();
			for (EIT_CHANNEL_EVENT evnt : eventList) {
				logger.info("EVENT : " + evnt.getRating());
				if (evnt.getRating().length() != 0) {
					cell.setCellValue(evnt.getRating().substring(5, evnt.getRating().length()));
				} else {
					break;
				}
				
				
//				String[] rating = evnt.getRating().split(" "); 	
//				for (String s : rating) {
//					cell.setCellValue(s);
//				}
			
				
				
			}
		}
//			cell = detailRow.createCell(2);
//			cell.setCellValue("222");
//			
//			cell = detailRow.createCell(3);
//			cell.setCellValue("33344445555");
		
	}
}
