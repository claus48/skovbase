package dk.bhpark.skovbase;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/** ************************************************************************************************
 *                                                                                                 *
 * This class creates an object that contains baselines for both software ans services to be in-   *
 * on a standard machine. The baselines are kept in an Excel spreadsheet that is opened by this    *
 * class and kept for later requests.                                                              *
 *                                                                                                 *
 * <p>                                                                                             *                                                                      *
 * <b>(C) Copyright Claus Jensen. 2017                                                             *
 * @version 1.01 - 13/01/2017                                                                      *
 * @author Claus Jensen (claus@bhpark.dk)                                                          *
 ************************************************************************************************ */

public class Baseline {
	
	private String[][] software = null;
	private String[] services = null;

	/**
	 * The baseline tables are created based on data in baseline.xls spreadsheet.
	 */
	public Baseline() {

		HSSFWorkbook workbook = null;
		Sheet softwareSheet = null;
		Sheet servicesSheet = null;
		
		try {
			String prefix = (System.getProperty("Prefix") == null) ? "." : System.getProperty("Prefix"); 
			workbook = new HSSFWorkbook(new FileInputStream(new File(prefix + "/baseline.xls")));
			softwareSheet = workbook.getSheetAt(0);
			servicesSheet = workbook.getSheetAt(1);
			
			software = new String[softwareSheet.getLastRowNum() + 1][2];
			for (int i = 0; i < software.length; i++) {
				Row row = softwareSheet.getRow(i);
				software[i][0] = row.getCell(0).getStringCellValue();
				software[i][1] = row.getCell(1).getStringCellValue();
			}
			services = new String[servicesSheet.getLastRowNum() + 1];
			for (int i = 0; i < services.length; i++) {
				Row row = servicesSheet.getRow(i);
				services[i] = row.getCell(0).getStringCellValue();
			}			
			workbook.close();
		} catch (Exception e) {
			System.err.println("Error occurred processing baseline spreadsheet");
			e.printStackTrace(System.err);
		}
	}
	
	/** 
	 * @return One-dimensional String table containing services baseline. 
	 */
	public String[] getServiceBaseline() {
		
		return services;
	}
	
	/**
	 * @return Two-dimensional String table containing software baseline.
	 */
	public String[][] getSoftwareBaseline() {
		
		return software;
	}
}
