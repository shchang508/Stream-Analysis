package tw.com.jamie.main;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tw.com.jamie.domain.MPEG_TABLES;
import tw.com.jamie.service.StreamConvertion;
import tw.com.jamie.service.TestService;
import tw.com.jamie.util.CwFileUtils;
import tw.com.jamie.util.FileReader;


public class Main {
	
	private static final Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception{
		
		String streampath = JOptionPane.showInputDialog("Please input stream path: ");
		
		if(streampath.equals(" ")) 
			streampath = "D:\\";
		
		StreamConvertion convert = new StreamConvertion(streampath + "\\");
		
		
//		String path = "D:\\Stream\\";
		String path = "C:\\xml\\";
		
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

		
	}

}
