package tw.com.jamie.util;

import java.io.InputStream;

public class FileReader {
	
	public static InputStream getFileWithUtil(Class clazz, String fileName) {
		ClassLoader classLoader = clazz.getClassLoader();
		return classLoader.getResourceAsStream(fileName);
	}
}
