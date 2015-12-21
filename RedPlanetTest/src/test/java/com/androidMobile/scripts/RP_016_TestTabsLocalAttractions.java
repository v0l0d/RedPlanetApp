package com.androidMobile.scripts;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.androidMobile.scripts.testObjects.AccountPageLocators;
import com.androidMobile.scripts.testObjects.HomePageLocators;
import com.androidMobile.scripts.testObjects.PickRoomPageLocators;
import com.androidMobile.workflows.GeneralHelper;
import com.androidMobile.workflows.LoginHelper;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

public class RP_016_TestTabsLocalAttractions extends LoginHelper{
	ExcelReader xlsTabs = new ExcelReader(configProps.getProperty("TestData"),
			"RP_ANDR_016");
  @Test(dataProvider = "testData")
  public void testLocalAttractions(String country, String city, 
		  String email, String password,String description) 
		  throws Throwable{
	  int count = 0; 
	  try{
		 TestEngine.testDescription.put(HtmlReportSupport.tc_name,description);
	  		System.out.println(xlsTabs.getCellValue("country", "Value")+" "+xlsTabs.getCellValue("city", "Value"));
		//handleSplashDialog();
		 handleRateAppPopUp();
		navigateToBookNow();
		handleRateAppPopUp();
		if(description.contains("pickroom")){
		selectDestination(country, city);
		waitForElementPresent(By.xpath(HomePageLocators.locationCountryName.replace("#", country.trim())),
				"locationCountryName");
		waitForElementPresent(By.xpath(HomePageLocators.locationCountryName.replace("#", city.trim())),
					"locationCountryName");
		 waitForElementPresent(HomePageLocators.searchButton, "searchButton");
		 click(HomePageLocators.searchButton, "searchButton");
		 handleRateAppPopUp();
		 waitForElementPresent(PickRoomPageLocators.pickRoomPage,"pickRoomPage");
		 if(GeneralHelper.locateExpandButton()){
			 Reporter.SuccessReport("Verify located expand button ","Successful ");
			 Reporter.SuccessReport("Verify clicked on expand button ","Successful ");
			 if(GeneralHelper.locateNearByTab()){
				 Reporter.SuccessReport("Verify navigate to tabs ", "Successful ");
			 }else{
				 Reporter.failureReport("Verify navigate to tabs ", "Failed ");
			 }
		 }else{
			 Reporter.failureReport("Verify locate expand button ","Failed ");
		 }
		}else if(description.contains("Local Attractions")){
			navigateToMyAccount();
			handleRateAppPopUp();
			handleSplashDialog();
			click(AccountPageLocators.logInButton, "logInButton");	
			login(email, password);
			navigateToHome();
			handleRateAppPopUp();
			handleSplashDialog();
			waitForElementPresent(HomePageLocators.localAttractionButton, "localAttractionButton");
			click(HomePageLocators.localAttractionButton, "localAttractionButton");
		}
		if(waitForElementPresent(PickRoomPageLocators.foodTab, "foodTab")){
			click(PickRoomPageLocators.foodTab, "foodTab");
			Reporter.SuccessReport("Verify food tab ","Successful ");
			 count++;
		}else{
			 Reporter.failureReport("Verify food tab ", "Failed ");
		}
		if(waitForElementPresent(PickRoomPageLocators.attractionsTab, "attractionsTab")){
			click(PickRoomPageLocators.attractionsTab, "attractionsTab");
			Reporter.SuccessReport("Verify attractions tab ", "Successful ");
			 count++;
		}else{
			 Reporter.failureReport("Verify attractions  tab ","Failed ");
		}
		if(waitForElementPresent(PickRoomPageLocators.eventsTab, "eventsTab")){
			click(PickRoomPageLocators.eventsTab, "eventsTab");
			Reporter.SuccessReport("Verify events tab ", "Successful ");
			count++;
		}else{
			 Reporter.failureReport("Verify events  tab ","Failed");
		} 
	
		Thread.sleep(10000);
			 if(count>0){
				 Reporter.SuccessReport(description, "Successful ");
			 }else
				 Reporter.failureReport(description, "Failed ");
	}catch(Exception e) {
		e.printStackTrace();
	}	
  }
  	@DataProvider(name="testData")
	public Object[][] createdata1() {
  		return (Object[][]) new Object[][] { 
			  {xlsTabs.getCellValue("country", "Value"),xlsTabs.getCellValue("city", "Value"),
				  xlsTabs.getCellValue("ValidCredentials", "Value"),xlsTabs.getCellValue("ValidCredentials", "password"),
			  "Verify tabs under pickroom screen"},
			  {xlsTabs.getCellValue("country", "Value"),xlsTabs.getCellValue("city", "Value"),
				  xlsTabs.getCellValue("ValidCredentials", "Value"),xlsTabs.getCellValue("ValidCredentials", "password"),
			  "Verify tabs under Local Attractions screen"}};
	}
}
