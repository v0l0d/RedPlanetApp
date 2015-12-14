package com.mobile.scripts;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.HomePageLocators;
import com.mobile.scripts.testObjects.PickRoomPageLocators;
import com.mobile.workflows.LoginHelper;

public class RP_003_TestSearchHotels extends LoginHelper{
	ExcelReader xlsSearch = new ExcelReader(configProps.getProperty("TestData"),
			"RP_003");
  @Test(dataProvider = "testData")
  public void testSearchHotels(String country, String city, String description) 
		  throws Throwable{
	try{
		 TestEngine.testDescription.put(HtmlReportSupport.tc_name,description);
		 handelSplashScreen();
		 handleSplashDialog();
		 navigateToBookNow();
		 //select destination
		selectDestination(country, city);
		if(waitForElementPresent(By.xpath(HomePageLocators.locationCountryName.replace("#", country.trim().toUpperCase())),
				"locationCountryName")){
		if(waitForElementPresent(By.xpath(HomePageLocators.locationCountryName.replace("#", city.trim().toUpperCase())),
				"locationCountryName")){
			 Reporter.SuccessReport("Verify Select Destination functionality",
					 "Successful selected destination "+country+" and "+city);
		}else{
			 Reporter.SuccessReport("Verify Select Destination functionality",
					 "Failed to select destination "+country+" and "+city);
		}
		}
		if(!(description.contains("select Destination"))){
		 waitForElementPresent(HomePageLocators.searchButton, "searchButton");
		 click(HomePageLocators.searchButton, "searchButton");
		 //verify search results
			 if(waitForElementPresent(PickRoomPageLocators.pickRoomPage,"pickRoomPage")){
				 Reporter.SuccessReport("Verify Search functionality", "Successful found PickRoom screen after Search");
			 }else
				 Reporter.failureReport("Verify Search functionality", "Failed to find PickRoom screen after Search");
		}
	}catch(Exception e) {
		e.printStackTrace();
	}	
  }
  	@DataProvider(name="testData")
	public Object[][] createdata1() {
  		System.out.println(xlsSearch.getCellValue("country", "Value")+" "+xlsSearch.getCellValue("city", "Value"));
  		return (Object[][]) new Object[][] { 
			  {xlsSearch.getCellValue("country", "Value"),xlsSearch.getCellValue("city", "Value"),
			  "Verify select Destination"},
			  {xlsSearch.getCellValue("country", "Value"),xlsSearch.getCellValue("city", "Value"),
			  "Verify search hotel functionlity"}};
	}
}
