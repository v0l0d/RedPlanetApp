package com.mobile.scripts;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import com.mobile.workflows.HomePageHelper;


public class TestSearch extends HomePageHelper{

		/*
		 * Verify Search Hotels functionality
		 */
		@Test(/*groups={"chrome" , "firefox", "ie","Mobile"} ,*/ /*dataProvider = "testData"*/)
		public static void testSearch(/*String location, String dateSelectionType, String checkOut, 
				String checkIn,String noOfAdults, String noOfChildren*/) throws Throwable {
			ExcelReader xlsSearch = new ExcelReader(configProps.getProperty("TestData"),
					configProps.getProperty("sheetName3"));
			String country = "Japan";
			String city = "Tokyo";
			String monthYear = "December 2015";
			String day = "20";
			boolean result = false;
			try{
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, 
					"Verify Search Hotels functionality in Mobile");
			//remove photo ssplash_screen
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
			String formattedDate = sdf.format(date);
			System.out.println(formattedDate.toUpperCase());
			if(startApp(country, city)){
			click(checkInLink, "checkInLink");
			scrollToText(monthYear);
			}
			Thread.sleep(3000);
			click(By.xpath(checkInDate.replace("#",monthYear ).replace("$", day)),
					"checkInLink");
			Thread.sleep(8000);
			if(result){
				Reporter.SuccessReport("validate signin for AU domain with email: ", "Successfully");
			}else{
				Reporter.failureReport("validate  signin for AU domain with email: ", "Failed");
			}
			} catch (Exception e) {
				e.printStackTrace();
				Reporter.failureReport("tesSearch", "Failed");
			}
		}
		
		/*@DataProvider(name="testData")
		public Object[][] createdata1() {
		    return (Object[][]) new Object[][] { 
		    		{"",xlsrdr.getCellValue("ValidateRegionAU", "password"), false," blank Email Error message should be displayed"},
		    		{xlsrdr.getCellValue("ValidateRegionAU", "email"),"", false," blank Password Error message should be displayed"},
		    		{xlsrdr.getCellValue("invalidEmail", "email"),xlsrdr.getCellValue("ValidateRegionAU", "password"), false," invalid Email Error message should be displayed"},
		    		{xlsrdr.getCellValue("ValidateRegionAU", "email"),xlsrdr.getCellValue("ValidateRegionAU", "password")+1, false," invalid Password Error message should be displayed"},
		    		{xlsrdr.getCellValue("ValidateRegionNZ", "email"),xlsrdr.getCellValue("ValidateRegionNZ", "password"), false," NZ credentials,  Error message should be displayed"},	    		
		    		{xlsrdr.getCellValue("ValidateRegionAU", "email"), xlsrdr.getCellValue("ValidateRegionAU", "password"),true," AU Email and Password, should be logged in"}};
		}*/
}
