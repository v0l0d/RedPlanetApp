package com.redplanet.scripts.search;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.ctaf.accelerators.ActionEngine;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import com.redplanet.workflows.GuestDetailsHelper;
import com.redplanet.workflows.HomePageHelper;
import com.redplanet.workflows.PaymentDetailsHelper;
import com.redplanet.workflows.RoomSelectionHelper;
import com.redplanet.workflows.SearchResultsHelper;

public class TestSearch extends ActionEngine{

		/*
		 * Verify Search Hotels functionality
		 */
		@Test(/*groups={"chrome" , "firefox", "ie","Mobile"} ,*/ /*dataProvider = "testData"*/)
		public static void testSearch(/*String location, String dateSelectionType, String checkOut, 
				String checkIn,String noOfAdults, String noOfChildren*/) throws Throwable {
			ExcelReader xlsSearch = new ExcelReader(configProps.getProperty("TestData"),
					configProps.getProperty("sheetName3"));
			ExcelReader xlsSrchResults = new ExcelReader(configProps.getProperty("TestData"),
					configProps.getProperty("sheetName4"));
			ExcelReader xlsRoomSel = new ExcelReader(configProps.getProperty("TestData"),
					configProps.getProperty("sheetName5"));
			ExcelReader xlsGuestDts = new ExcelReader(configProps.getProperty("TestData"),
					configProps.getProperty("sheetName6"));
			ExcelReader xlsPayment = new ExcelReader(configProps.getProperty("TestData"),
					configProps.getProperty("sheetName7"));
			
			try{
			boolean result = false;
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, 
					"Verify Search Hotels functionality");
			//remove photo ssplash_screen
			Thread.sleep(2000);
			driver.findElement(By.xpath("//body")).sendKeys(Keys.ESCAPE);
						
			//picking the search type
			HomePageHelper.picSearchType(xlsSearch.getCellValue("RPH1", "dateselection"));
			
			//Search Hotels 
			result = HomePageHelper.SearchForHotels(
					xlsSearch.getCellValue("RPH1", "Location"),
					xlsSearch.getCellValue("RPH1", "dateselection"),
					xlsSearch.getCellValue("RPH1", "CheckIn"),
					xlsSearch.getCellValue("RPH1", "CheckOut"),
					xlsSearch.getCellValue("RPH1", "Adults"),
					xlsSearch.getCellValue("RPH1", "Children"));					
			
			if(result){
				Reporter.SuccessReport("validate signin for AU domain with email: ", "Successfully");
			}else{
				Reporter.failureReport("validate  signin for AU domain with email: ", "Failed");
			}
			
			//Modify Search or direct BookNow
			result = SearchResultsHelper.SearchResults(
					xlsSrchResults.getCellValue("RPH1", "ModifySearch"), "", "", "", "", "");
			if(result){
				Reporter.SuccessReport("validate Search Results and click BookNow: ", "Successfully");
			}else{
				Reporter.failureReport("validate Search Results and click BookNow: ", "Failed");
			}
			
			//Room Selection
			RoomSelectionHelper.RoomSelection(xlsRoomSel.getCellValue("RPH1", "roomType"));
			
			//Guest details
			result = GuestDetailsHelper.GuestDetails(
					xlsGuestDts.getCellValue("RPH1", "FirstName"), 
					xlsGuestDts.getCellValue("RPH1", "LastName"), 
					xlsGuestDts.getCellValue("RPH1", "Email"), 
					xlsGuestDts.getCellValue("RPH1", "CountryName"), 
					xlsGuestDts.getCellValue("RPH1", "Phone"));
			if(result){
				Reporter.SuccessReport("validate Guest details: ", "Successfully");
			}else{
				Reporter.failureReport("validate Guest details: ", "Failed");
			}
			
			//Payment details
			PaymentDetailsHelper.PaymentDetails(
					xlsPayment.getCellValue("RPH1", "PaymentMethod"),
					xlsPayment.getCellValue("RPH1", "Cardname"), 
					xlsPayment.getCellValue("RPH1", "CardNumber"), 
					xlsPayment.getCellValue("RPH1", "Expiration"), 
					xlsPayment.getCellValue("RPH1", "CVC"),
					"");
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
