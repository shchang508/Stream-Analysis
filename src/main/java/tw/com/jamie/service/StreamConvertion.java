package tw.com.jamie.service;

import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;

public class StreamConvertion {

	private static final Logger logger = Logger.getLogger(StreamConvertion.class);

	public String path = "C:\\";

	public ArrayList<String> xfilenames = new ArrayList<String>();

	public ArrayList<String> getXfilenames() {
		return xfilenames;
	}

	public StreamConvertion(String streampath) {
		File folder = new File(streampath);
		File[] listofFiles = folder.listFiles();

		String xfilepath = path + "xml\\";
		File xmlFolder = new File(xfilepath);
		File[] listofXml = xmlFolder.listFiles();


		TSReader tsReader = new TSReader();
		for (File f : listofFiles) {
			for (File x : listofXml) {
				String tsName = f.getName().replace(".ts", "").replace(".trp", "");
				String xmlName = x.getName().replace(".xml", "");
				logger.info("TS / TRP: " + tsName);
				logger.info("XML: " + xmlName);
				if (!tsName.equals(xmlName)) {
					int count = 1;
					/* Load stream and export as XML */

					try {
						createfolder();
						for (int i = 0; i < listofFiles.length; i++) {
							File sfile = listofFiles[i];
							if (sfile.isFile()) {
								String sname = sfile.getName();
								if (sname.toLowerCase().endsWith(".ts") || sname.toLowerCase().endsWith(".trp")) {
									tsReader.send("setting PAST_EIT true");
									tsReader.send("setting KEEP_SPECIAL_XML true");
									tsReader.send("source TSReader_FileLoop.dll\n");
									tsReader.send("tune " + streampath + sname + "\n");
									tsReader.send("stall 300");
									// tsReader.waitfor("311 Table decoding complete"); //TSReader2.8.47c not
									// support

									String xfilename = nameconvert(sname, "xml");
									tsReader.send("export xml " + xfilepath + xfilename);
									tsReader.waitfor("Data exported");

									String result = "Stream [" + (count++) + "] is exported as XML: " + sfile.getName();
									logger.info(result);
									xfilenames.add(result);

								}

							}
						}
						tsReader.disconnect();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void createfolder() {
		String[] folder = { "xml\\", "excel\\" };
		for (String i : folder) {
			File directory = new File(path, i);
			if (directory.exists() && i.equals("xml\\")) {
				File f = new File(path + i);
				File[] f1 = f.listFiles();
//				for (File j : f1)
//					j.delete();
			}
			directory.mkdir();
		}
	}

	private void createfolder(String path) {
		String[] folder = { "xml\\", "excel\\" };
		for (String i : folder) {
			File directory = new File(path, i);
			if (directory.exists()) {
				File f = new File(path + i);
				File[] f1 = f.listFiles();
				for (File j : f1)
					j.delete();
				directory.delete();
			}
			directory.mkdir();
		}
	}

	public String nameconvert(String name, String ftype) {

		String finalname = null;
		if (ftype == "xml") {
			int index = name.lastIndexOf(".");
			finalname = name.substring(0, index) + ".xml";
		}

		return finalname;
	}
}
