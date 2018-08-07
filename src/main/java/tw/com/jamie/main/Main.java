package tw.com.jamie.main;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tw.com.jamie.domain.MPEG_TABLES;
import tw.com.jamie.service.StreamConvertion;
import tw.com.jamie.service.TestService;
import tw.com.jamie.util.CwFileUtils;
import tw.com.jamie.util.FileReader;


public class Main {
	
	private static final Logger logger = Logger.getLogger(Main.class);
	
	public ImageIcon getImage (String imagePath) {
		URL url = getClass().getResource(imagePath);
		ImageIcon image = new ImageIcon(url);
		return image;
	}
	
	public static void main(String[] args) throws Exception{
		
		//Change application icon from Java coffee cup to customize image 
		Image iconImage = ImageIO.read(Main.class.getResource("/output.png"));
		
		Main m = new Main();
		ImageIcon icon = m.getImage("/start.png");
		

//		JFrame startFrame = new JFrame("Stream Convertion");
//		startFrame.getContentPane().setBackground(Color.DARK_GRAY);
//		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		startFrame.setVisible(true);
//		startFrame.setResizable(false);
//		startFrame.setSize(400, 300);
//		startFrame.getContentPane().setLayout(null);
//		startFrame.setLocationRelativeTo(null);
//		startFrame.setIconImage(iconImage);
		
		String streampath = (String) JOptionPane.showInputDialog(null, "Please input stream path: ", "Input", JOptionPane.QUESTION_MESSAGE, icon, null, null);
		
		
		if(streampath.equals(" ")) 
			streampath = "D:\\";
		
		//loading frame
		JFrame loadingFrame = new JFrame("Stream Analysis");
		ImageIcon loadingIcon = m.getImage("/ajax-loader.gif");
		JLabel loadingLabel = new JLabel("Loading... ", loadingIcon, JLabel.CENTER);
		loadingFrame.add(loadingLabel);
		loadingLabel.setFont(new Font("Arial", Font.BOLD, 18));
		loadingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadingFrame.setSize(300, 200);
		loadingFrame.setLocationRelativeTo(null);  //set Jframe to the centre of the screen
		loadingFrame.setVisible(true);
		
		loadingFrame.setIconImage(iconImage);
		
		
		
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
		
		loadingFrame.dispose();        //window close after generate xml file
		
		
		service.genMpegExcel(workbook, mpegList, fileList);
			
		String reportName = "jamie_" + new Date().getTime();
		String destination = "D:\\Excel Test";

		CwFileUtils.createExcelFile(workbook, reportName, destination);
		
		
		JFrame resultFrame = new JFrame("Result");
		Component comp = null;
		JOptionPane.showMessageDialog(null, "Excel file is generated!", "Stream Analysis", JOptionPane.INFORMATION_MESSAGE, icon);
		
		for (String label : convert.getXfilenames()) {
			comp = resultFrame.add(new JLabel(label, JLabel.CENTER));
		}
//		JOptionPane.showMessageDialog(comp, "Result");
//
//		resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		resultFrame.setLocationRelativeTo(null);
//		resultFrame.setSize(400, 300);
//		resultFrame.setVisible(true);
//		resultFrame.setIconImage(iconImage);
		
	}
	
	

}
