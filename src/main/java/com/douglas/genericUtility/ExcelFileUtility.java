package com.douglas.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class is contains excel file specific methods
 * @author Niraj
 */
public class ExcelFileUtility {
	Workbook book;
	/**
	 * This methods is used to open the excelSheet
	 * @param filePath
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	public void openExcelFile(String filePath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to fetch the data from excel
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @return 
	 */
	public String getDataFromExcelFile(String sheetName, int rowNumber, int cellNumber) {
		DataFormatter formate = new DataFormatter();
		//		String data = book.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getStringCellValue();
		String data = formate.formatCellValue(book.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber));
		return data;
	}


	/**
	 * This method is used to send in excel
	 * @param filePath
	 * @throws IOException
	 */
	public void writeDataInExcel(String filePath) 
	{
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 *  This method is used to write data in to excel
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @param msg
	 */
	public void setDataIntoExcel(String sheetName, int rowNumber, int cellNumber,String msg)
	{
		book.getSheet(sheetName).getRow(rowNumber).createCell(cellNumber).setCellValue(msg);
	}
	/**
	 * This method is used to close the excelSheet
	 * @throws IOException 
	 */
	public void closeExcelFile() {
		try {
			book.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
