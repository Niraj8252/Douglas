package com.douglas.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * This class is contains property file specific methods
 * @author Niraj
 */
public class PropertyFileUtility {

	Properties property;
	/**
	 * This method is used to open property file
	 * @throws IOException 
	 */
	public void openPropertyFile(String filePath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			System.err.print("Exception occured whil loading file : "+ e.getMessage());
		}

	}
	/**
	 * This method is used to fetch data from property file
	 * @param key
	 * @return
	 */
	public String getDataFromPropertyFile(String key) {
		String value = property.getProperty(key);
		return value;
	}

}
