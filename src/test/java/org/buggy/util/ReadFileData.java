package org.buggy.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadFileData {
	public static Properties prop = new Properties();

	public static Properties getProperty() {

		try {
			InputStream is = ReadFileData.class.getClassLoader().getResourceAsStream("datafile.properties");

			// load properties file

			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();

		}
		return prop;

	}
}