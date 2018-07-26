package tw.com.jamie.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;


public class FileReader {

	 public static InputStream getFileWithUtil(Class clazz, String fileName) {
	 ClassLoader classLoader = clazz.getClassLoader();
	 return classLoader.getResourceAsStream(fileName);
	 }

	 
	 public static ArrayList ReadFile(String filePath) {
			File folder = new File(filePath);
//			File[] listOfFiles = folder.listFiles();
			
//			String fileName = new String();
			ArrayList<String> fileList = new ArrayList<String>(); 
			
			for(String fileName: folder.list()) {
				fileList.add(fileName);
			}
			
//			for(int i = 0; i < listOfFiles.length; i++) {
//				fileName = listOfFiles[i].getName();
//				if (fileName.endsWith(".xml") || fileName.endsWith(".XML")) {
//					//System.out.println(filename);
//					
//				}
//			
//			}
			return fileList;
		}	
	
	


}
