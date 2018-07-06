package tw.com.jamie.main;

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
import tw.com.jamie.service.TestService;
import tw.com.jamie.util.CwFileUtils;



public class Test {

	private static final Logger logger = Logger.getLogger(Test.class);
	
	public static void main(String[] args) throws Exception {
		
		 String path = "C:\\Users\\jamie.chang\\Desktop\\CourseRelated\\NZ_PCH29_538M_Channel_ONE_Rating-AO-0_020912-1.xml";
		 TestService service = new TestService ();
		 MPEG_TABLES table = (MPEG_TABLES) service.genTable(path, MPEG_TABLES.class);
		 
		XSSFWorkbook workbook = new XSSFWorkbook();
		service.genMpegExcel(workbook, table);
		String reportName = "seesawin_" + new Date().getTime();
		String destination = "C:\\test";

		CwFileUtils.createExcelFile(workbook, reportName, destination);

//		InputStream is = FileReader.getFileWithUtil(Test.class, "NZ_PCH29_538M_Channel_ONE_Rating-AO-0_020912-1.xml");
//		MPEG_TABLES table = (MPEG_TABLES) XMLUtil.convertXmlFileToObject(MPEG_TABLES.class, is);
		
		logger.info("---Convert XML to Object START---");
		
		/************************************ PMTs ************************************/
		List<PMTs_CHANNEL> pmtsChannelList = table.getPmts().getPmtsChannelList();
		logger.info("Number of Channels : " + pmtsChannelList.size());

		for (PMTs_CHANNEL c : pmtsChannelList) {
			logger.info("ServiceNumber : " + c.getServiceNumber());
			List<PMTs_CHANNEL_ELEMENTARY_STREAM> elementaryStreamList = c.getPmtsChannelEementaryStreamList();
			for (PMTs_CHANNEL_ELEMENTARY_STREAM e : elementaryStreamList) {
				logger.info("STREAM-TYPE : " + e.getStreamType());
				logger.info("AUDIO-LANGUAGE : " + e.getAudioLanguage());
				logger.info("AUDIO-TYPE : " + e.getAudioType());
				logger.info("HORIZONTAL-RESOLUTION : " + e.getHorizontalResolution());
				logger.info("VERTICAL-RESOLUTION : " + e.getVerticalResolution());
				List<PMTs_CHANNEL_ELEMENTARY_STREAM_DESCRIPTOR> descriptorList = e.getDescriptorList();
				for (PMTs_CHANNEL_ELEMENTARY_STREAM_DESCRIPTOR d : descriptorList) {
					logger.info("TAG : " + d.getTag());
					logger.info("LENGTH : " + d.getLength());
					logger.info("DATA : " + d.getData());
				}
			}
		}
		
		
		/************************************ NIT ************************************/
		List<NIT_ENTRY> entryList = table.getNit().getNitEntryList();
		for (NIT_ENTRY nc : entryList) {
			logger.info("NETWORK-NAME : " + nc.getNetworkName());
		}
		
		
		/************************************ EIT ************************************/
		List<EIT_CHANNEL> eitChannelList = table.getEit().getEitChannelList();
		for (EIT_CHANNEL ec : eitChannelList) {
			List<EIT_CHANNEL_EVENT> eventList = ec.getEitChannelEventList();
			for (EIT_CHANNEL_EVENT evnt : eventList) {
				logger.info("EVENT : " + evnt.getRating());
			}
		}
		
		logger.info("---Convert XML to Object END---"); 
	}

}
