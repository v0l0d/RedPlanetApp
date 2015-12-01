package com.androidMobile.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.androidMobile.scripts.testObjects.HomePageLocators;
import com.androidMobile.scripts.testObjects.PickRoomPageLocators;
import com.androidMobile.workflows.LoginHelper;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

public class RP_003_TestSearchHotels extends LoginHelper{
	ExcelReader xlsSearch = new ExcelReader(configProps.getProperty("TestData"),
			"RP_ANDR_003");
  @Test(dataProvider = "testData")
  public void testSearchHotels(String country, String city,String description) throws Throwable{
	try{
		 TestEngine.testDescription.put(HtmlReportSupport.tc_name, 
				description);
		 handleSplashDialog();
		 selectDestination(country, city);
		 waitForElementPresent(HomePageLocators.searchButton, "searchButton");
		 click(HomePageLocators.searchButton, "searchButton");
		 handleRateAppPopUp();
		 Thread.sleep(6000);
			 if(isElementDisplayed(PickRoomPageLocators.pickRoomPage)){
				 Reporter.SuccessReport(description, "Successful");
			 }else
				 Reporter.failureReport(description, "Failed");
	}catch(Exception e) {
		e.printStackTrace();
		Reporter.failureReport(description, "Failed with exception");
	}	
  }
  	@DataProvider(name="testData")
	public Object[][] createdata1() {
  		return (Object[][]) new Object[][] { 
			  {xlsSearch.getCellValue("country", "Value"),xlsSearch.getCellValue("city", "Value"),
				"Verify Search Hotel and Select Destination"}};
	}
}
