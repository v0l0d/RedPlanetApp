package com.redplanet.utils;  

import org.testng.annotations.DataProvider;

import com.ctaf.accelerators.ActionEngine;
import com.ctaf.support.ExcelReader;

public class Dataprovider extends ActionEngine {
	static ExcelReader xlsrdr = new ExcelReader(configProps.getProperty("TestData"),configProps.getProperty("sheetName"));

	@DataProvider(name="testData")
	public static Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{xlsrdr.getCellValue("ValidateRegionNZ", "email"),xlsrdr.getCellValue("ValidateRegionNZ", "password") },
	    		{xlsrdr.getCellValue("ValidateRegionAU", "email"), xlsrdr.getCellValue("ValidateRegionAU", "password")}};
	}
}
