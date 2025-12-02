package com.douglas.genericUtility;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
/**
 * This class is contains java specific methods
 */
public class JavaUtility {
	/**
	 * This method is used to convert String value to long data type
	 * @param value
	 * @return
	 */
	public long stringConvertToLong(String value)
	{
		return Long.parseLong(value);
	}

	/**
	 * This method is used to generate random number
	 * @param limit
	 * @return
	 */
	public int generateRandomNum(int limit)
	{
		Random r = new Random();
		return r.nextInt(limit);
	}
	
	/**
	 * This method is used to generate random string
	 * @param limit
	 * @return
	 */
	public String generateRandomString(int limit) {
		String generatedString = RandomStringUtils.randomAlphabetic(limit);
		return generatedString;
	}
	
}
