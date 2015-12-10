package com.androidMobile.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.androidMobile.scripts.testObjects.BookPageLocators;
import com.androidMobile.scripts.testObjects.HomePageLocators;
import com.androidMobile.scripts.testObjects.PickRoomPageLocators;
import com.androidMobile.workflows.LoginHelper;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

public class RP_002_TestLogInFromBookingScreen  extends LoginHelper{
	
	ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("TestData"),
			"RP_ANDR_002");
		@Test(dataProvider = "testData")
  public void testLogInFromBookingScreen(String country,String city,
		  String email,String password,String description) throws Throwable {
	  try{
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, 
					description);	
		  handleSplashDialog();
		  //verify user already loggedIn, if yes signout
		  navigateToBookNow();
		  handleRateAppPopUp();
		  selectDestination(country, city);
		  waitForElementPresent(HomePageLocators.searchButton, 
				  "searchButton");
		  click(HomePageLocators.searchButton, "searchButton");
		  handleRateAppPopUp();
		  waitForElementPresent(PickRoomPageLocators.pickRoomPage, 
				  "pickRoomPage");
		  click(PickRoomPageLocators.bookNowButton, "bookNowButton");
		  handleRateAppPopUp();
		  waitForElementPresent(BookPageLocators.contiuneButton, 
				  "contiuneButton");
		  click(BookPageLocators.logInButton, "logInButton");
		  handleRateAppPopUp();
		  userlogin(email, password);
		  if(waitForElementPresent(BookPageLocators.guestDetailsFrame, "guestDetailsFrame")){
			  Reporter.SuccessReport("Validate login from Booking page", "Successful");			  
		  }else{
			  Reporter.failureReport("Validate login from Booking page", "Failed");
		  }
	  }catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("LogIn", "Failed");
		}
  }
  
  @DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{xlsLogin.getCellValue("country", "Value"),xlsLogin.getCellValue("city", "Value"),
	    			xlsLogin.getCellValue("email", "Value"),xlsLogin.getCellValue("password", "Value"),
	    			"Validate LogIn from Booking page"}};
	}
}
