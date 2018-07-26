package tw.com.jamie.service;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

public class TestService extends BaseAbstractService {

	private static final Logger logger = Logger.getLogger(TestService.class);

	public Object genTable(String path, Class clazz) {

		return XMLUtil.convertXmlFileToObject(clazz, path);

	}

	public void genMpegExcel(XSSFWorkbook workbook, MPEG_TABLES table, String fileName) {
		System.out.println("generate excel...");
		
		this.setStyle(workbook);

		XSSFSheet sheet = workbook.createSheet("sheet01");
		
		
//		sheet.createFreezePane(1, 2);

		// 固定欄位寬度設定
		sheet.setColumnWidth(0, 500 * 20); //
		sheet.setColumnWidth(1, 256 * 20);
		sheet.setColumnWidth(2, 256 * 20);
		sheet.setColumnWidth(3, 256 * 20);
		sheet.setColumnWidth(4, 256 * 20);
		sheet.setColumnWidth(5, 256 * 20);
		sheet.setColumnWidth(6, 256 * 20);
		sheet.setColumnWidth(7, 256 * 20);
		sheet.setColumnWidth(8, 256 * 20);
		sheet.setColumnWidth(9, 256 * 20);
		sheet.setColumnWidth(10, 500 * 20);

		XSSFRow title_header = sheet.createRow(0);

		XSSFCell title00 = title_header.createCell(0);
		title00.setCellValue("Name of the Stream");
		title00.setCellStyle(cellStyleMap.get("style_01"));

		XSSFCell title01 = title_header.createCell(1);
		title01.setCellValue("Number of Channels");
		title01.setCellStyle(cellStyleMap.get("style_01"));

		XSSFCell title02 = title_header.createCell(2);
		title02.setCellValue("Subtitle Language");
		title02.setCellStyle(cellStyleMap.get("style_01"));

		XSSFCell title03 = title_header.createCell(3);
		title03.setCellValue("Audio Language");
		title03.setCellStyle(cellStyleMap.get("style_01"));
		
		XSSFCell title04 = title_header.createCell(4);
		title04.setCellValue("TELETEXT");
		title04.setCellStyle(cellStyleMap.get("style_01"));

		XSSFCell title05 = title_header.createCell(5);
		title05.setCellValue("Dolby Digital");
		title05.setCellStyle(cellStyleMap.get("style_01"));

		XSSFCell title06 = title_header.createCell(6);
		title06.setCellValue("Resolution");
		title06.setCellStyle(cellStyleMap.get("style_01"));

		XSSFCell title07 = title_header.createCell(7);
		title07.setCellValue("Visually Impaired");
		title07.setCellStyle(cellStyleMap.get("style_01"));

		XSSFCell title08 = title_header.createCell(8);
		title08.setCellValue("HbbTV");
		title08.setCellStyle(cellStyleMap.get("style_01"));

		XSSFCell title09 = title_header.createCell(9);
		title09.setCellValue("Freeview");
		title09.setCellStyle(cellStyleMap.get("style_01"));

		XSSFCell title10 = title_header.createCell(10);
		title10.setCellValue("Rating");
		title10.setCellStyle(cellStyleMap.get("style_01"));

		XSSFCell cell = null;
		int initRow = 1;

		XSSFRow detailRow = sheet.createRow(initRow);
		
		logger.info("---------------Convert XML to Object START---------------");
		
		/************************************ Name of the Stream ************************************/
		cell = detailRow.createCell(0);
		// TUNED_MULTIPLEX tunerString = table.getTunedMultiplex();
		// logger.info("Name of the Stream : " + tunerString.getTunerString());
		cell.setCellValue(fileName.replaceAll(".xml", ""));
		cell.setCellStyle(cellStyleMap.get("style_02"));	
		
		
		/************************************ Number of channels ************************************/
		cell = detailRow.createCell(1);
		List<PMTs_CHANNEL> pmtsChannelList = table.getPmts().getPmtsChannelList();
		logger.info("Number of Channels : " + pmtsChannelList.size());
		cell.setCellValue(pmtsChannelList.size());
		cell.setCellStyle(cellStyleMap.get("style_02"));		
		
		boolean audioLanFlag = true;
		boolean dolbyFlag = true;
		boolean resolutionFlag = true;
		boolean hbbFlag = true;
		boolean visFlag = true;

		Set<String> setsData = new HashSet<String>();
		for (PMTs_CHANNEL c : pmtsChannelList) {
			logger.info("ServiceNumber : " + c.getServiceNumber());
			List<PMTs_CHANNEL_ELEMENTARY_STREAM> elementaryStreamList = c.getPmtsChannelEementaryStreamList();
			for (PMTs_CHANNEL_ELEMENTARY_STREAM e : elementaryStreamList) {
				List<PMTs_CHANNEL_ELEMENTARY_STREAM_DESCRIPTOR> descriptorList = e.getDescriptorList();
				logger.info("descriptorList : " + descriptorList);
				if (descriptorList != null) {
					for (PMTs_CHANNEL_ELEMENTARY_STREAM_DESCRIPTOR d : descriptorList) {
						logger.info("TAG : " + d.getTag());
						logger.info("LENGTH : " + d.getLength());
						logger.info("DATA : " + d.getData());
						
						
						if ("0x59".equals(d.getTag())) {
							String data = d.getData().substring(0, 3);
							setsData.add(data);
						} 
//						else {
//							cell = detailRow.createCell(2);
//							cell.setCellValue("No subtitles");
//							cell.setCellStyle(cellStyleMap.get("style_02"));
//						}
						
						
						/************************************ HbbTV ************************************/
						if (hbbFlag) {
							logger.info("OOOOOO: " + d.getTag());
							cell = detailRow.createCell(8);
							if ("0x6f".equals(d.getTag())) {
								cell.setCellValue("Y");
								hbbFlag = false;
								cell.setCellStyle(cellStyleMap.get("style_02"));
							} else {
								cell.setCellValue("N");
								cell.setCellStyle(cellStyleMap.get("style_02"));
							}
						}
						
						
						/************************************ Visually Impaired ************************************/
						if (visFlag) {
							cell = detailRow.createCell(7);
							if ("0x06".equals(d.getTag())) {
								cell.setCellValue("Y");
								cell.setCellStyle(cellStyleMap.get("style_02"));
								visFlag = false;
							} else {
								cell.setCellValue("N");
								cell.setCellStyle(cellStyleMap.get("style_02"));
							}
						}

					}

					
					/************************************ Audio Language ************************************/
					logger.info("AUDIO-LANGUAGE : " + e.getAudioLanguage());
					if (e.getAudioLanguage() != null) {
						if (audioLanFlag) {
							cell = detailRow.createCell(3);
							logger.info(">>>>>>> AUDIO-LANGUAGE : " + e.getAudioLanguage());
							cell.setCellValue(e.getAudioLanguage());
							audioLanFlag = false;
							cell.setCellStyle(cellStyleMap.get("style_02"));
						}
					}
					
					
					/************************************ TELETEXT ************************************/
					logger.info("STREAM-TYPE : " + e.getStreamType());
					if ("TELETEXT".equals(e.getStreamType())) {
						cell = detailRow.createCell(4);
						cell.setCellValue("Y");
						cell.setCellStyle(cellStyleMap.get("style_02"));
					} else {
						cell = detailRow.createCell(4);
						cell.setCellValue("N");
						cell.setCellStyle(cellStyleMap.get("style_02"));
					}

					
					/************************************ Audio Type ************************************/
					logger.info("AUDIO-TYPE : " + e.getAudioType());
					if (dolbyFlag) {
						if (e.getAudioType() != null && e.getAudioType().contains("AC3")) {
							cell = detailRow.createCell(5);
							logger.info(">>>>>>AUDIO-TYPE : " + e.getAudioType());
							cell.setCellValue("Y");
							dolbyFlag = false;
							cell.setCellStyle(cellStyleMap.get("style_02"));
						} else {
							cell.setCellValue("N");
							cell.setCellStyle(cellStyleMap.get("style_02"));
						}
					}
					

					/************************************ Resolution ************************************/
					logger.info("HORIZONTAL-RESOLUTION : " + e.getHorizontalResolution());
					logger.info("VERTICAL-RESOLUTION : " + e.getVerticalResolution());
					if (e.getHorizontalResolution() != null && e.getVerticalResolution() != null) {
						if (resolutionFlag) {
							cell = detailRow.createCell(6);
							cell.setCellValue(e.getHorizontalResolution() + "*" + e.getVerticalResolution());
							resolutionFlag = false;
							cell.setCellStyle(cellStyleMap.get("style_02"));
						}
					}

				}
				
				
			}
		}

		/************************************ Subtitle Language ************************************/
		StringBuilder sbData = new StringBuilder();
		int countData = 0;
		for (String sData : setsData) {
			countData++;
			sbData.append(sData);
			if (countData != setsData.size()) {
				sbData.append(", ");
			}
		}
		
		cell = detailRow.createCell(2);
		cell.setCellValue(sbData.toString());
		cell.setCellStyle(cellStyleMap.get("style_02"));
		
		
		/************************************ Freeview ************************************/
		cell = detailRow.createCell(9);
		List<NIT_ENTRY> entryList = table.getNit().getNitEntryList();
		for (NIT_ENTRY nc : entryList) {
			if ("Freeview".equals(nc.getNetworkName().trim())) {
				logger.info(">>>>>>>NETWORK-NAME : " + nc.getNetworkName());
				cell.setCellValue("Y");
				cell.setCellStyle(cellStyleMap.get("style_02"));
			} else {
				cell.setCellValue("N");
				cell.setCellStyle(cellStyleMap.get("style_02"));
			}
		}

		
		/************************************ Rating ************************************/
		Set<String> sets = new HashSet<String>();
		cell = detailRow.createCell(10);
		List<EIT_CHANNEL> eitChannelList = table.getEit().getEitChannelList();
		for (EIT_CHANNEL ec : eitChannelList) {
			List<EIT_CHANNEL_EVENT> eventList = ec.getEitChannelEventList();
			for (EIT_CHANNEL_EVENT evnt : eventList) {
				logger.info("EVENT : " + evnt.getRating());
				// if (evnt.getRating().length() != 0) {
				// cell.setCellValue(evnt.getRating().substring(5, evnt.getRating().length()));
				// } else {
				// break;
				// }

				String[] arrRating = evnt.getRating().split(": ");
				if ((evnt.getRating() != null && !"".equals(evnt.getRating())) && !"undefined".equals(arrRating[1]) && !"Not classified".equals(arrRating[1])) {
					sets.add(arrRating[1]);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (String s : sets) {
			count++;
			logger.info("Sets : " + s);
			String[] numbers = s.split(" ");

			sb.append(s);
			if (count != sets.size()) {
				sb.append(", ");
			}
		}
		cell.setCellValue(sb.toString());
		cell.setCellStyle(cellStyleMap.get("style_02"));
		
		
		logger.info("---Convert XML to Object END---"); 
	}
}
