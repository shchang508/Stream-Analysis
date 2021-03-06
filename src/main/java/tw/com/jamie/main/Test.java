package tw.com.jamie.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tw.com.jamie.domain.EIT_CHANNEL;
import tw.com.jamie.domain.EIT_CHANNEL_EVENT;
import tw.com.jamie.domain.MPEG_TABLES;
import tw.com.jamie.domain.NIT_ENTRY;
import tw.com.jamie.domain.PMTs_CHANNEL;
import tw.com.jamie.domain.PMTs_CHANNEL_ELEMENTARY_STREAM;
import tw.com.jamie.domain.PMTs_CHANNEL_ELEMENTARY_STREAM_DESCRIPTOR;
import tw.com.jamie.domain.TUNED_MULTIPLEX;
import tw.com.jamie.service.TestService;
import tw.com.jamie.util.CwFileUtils;
import tw.com.jamie.util.FileReader;




public class Test {

	private static final Logger logger = Logger.getLogger(Test.class);
	
	public static void main(String[] args) throws Exception {
		
		
//		String fileName = "NZ_PCH29_538M_Channel_ONE_Rating-AO-0_020912-1.xml";
//		String fileName = "RatingG.xml";
//		String path = "C:\\Users\\jamie.chang\\Desktop\\CourseRelated\\" + fileName;
//		String path = "D:\\" + fileName;
		
		
		String path = "D:\\Stream\\";
//		String path = "C:\\xml\\";
		
		FileReader fr = new FileReader();
		ArrayList<String> nameList =  fr.readFile(path);
		
		TestService service = new TestService ();
		MPEG_TABLES table = null;
		XSSFWorkbook workbook = new XSSFWorkbook();
				
		ArrayList<MPEG_TABLES> mpegList = new ArrayList<MPEG_TABLES>();
		ArrayList<String> fileList = new ArrayList<String>();
		
		for(String fName: nameList) {
			 table = (MPEG_TABLES) service.genTable(path + fName, MPEG_TABLES.class);
			 mpegList.add(table);
			 fileList.add(fName);

		}
		
		service.genMpegExcel(workbook, mpegList, fileList);
			
		 
		String reportName = "jamie_" + new Date().getTime();
		String destination = "D:\\Excel Test";

		CwFileUtils.createExcelFile(workbook, reportName, destination);

//		InputStream is = FileReader.getFileWithUtil(Test.class, "NZ_PCH29_538M_Channel_ONE_Rating-AO-0_020912-1.xml");
//		MPEG_TABLES table = (MPEG_TABLES) XMLUtil.convertXmlFileToObject(MPEG_TABLES.class, is);
		
	}

}
