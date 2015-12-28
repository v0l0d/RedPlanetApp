package com.androidMobile.scripts;

import org.openqa.selenium.By;
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
        navigateToMyAccount();
        handleRateAppPopUp();
        logOut();
        navigateToBookNow();
        handleRateAppPopUp();
		 selectDestination(country, city);
		 if(description.contains("Select Destination")){
			 if((isElementPresent(By.xpath(HomePageLocators.locationCityName.replace("#", city.trim())),
					 "locationCityName"))&
					 (isElementPresent(By.xpath(HomePageLocators.locationCountryName.replace("#", country.trim())),
							 "locationCityName"))){
				 Reporter.SuccessReport(description, "Successful");
			 }else{
				 Reporter.failureReport(description, "Failed");
			 }
		 }
		 if(description.contains("Search Hotel")){
		 waitForElementPresent(HomePageLocators.searchButton, "searchButton");
		 click(HomePageLocators.searchButton, "searchButton");
		 handleRateAppPopUp();
		 Thread.sleep(6000);
			 if(isElementDisplayed(PickRoomPageLocators.pickRoomPage)){
				 Reporter.SuccessReport(description, "Successful");
			 }else
				 Reporter.failureReport(description, "Failed");
		 }
	}catch(Exception e) {
		e.printStackTrace();
		Reporter.failureReport(description, "Failed with exception");
	} finally {
        driver.navigate().back();
    }
  }
  	@DataProvider(name="testData")
	public Object[][] createdata1() {
  		return (Object[][]) new Object[][] { 
//  				 {xlsSearch.getCellValue("country", "Value"),xlsSearch.getCellValue("city", "Value"),
// 				"Verify Select Destination"},
			  {xlsSearch.getCellValue("country", "Value"),xlsSearch.getCellValue("city", "Value"),
				"Verify Search Hotels"}};
	}
}
