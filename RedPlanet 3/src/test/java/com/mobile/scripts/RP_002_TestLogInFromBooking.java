package com.mobile.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.support.ExcelReader;
import com.ctaf.utilities.Reporter;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.HtmlReportSupport;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.BookPageLocators;
import com.mobile.scripts.testObjects.LoginPageLocators;
import com.mobile.scripts.testObjects.PickRoomPageLocators;
import com.mobile.workflows.LoginHelper;
import com.mobile.scripts.testObjects.HomePageLocators;

public class RP_002_TestLogInFromBooking  extends LoginHelper{
	
	ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("TestData"),
			"RP_002");
		@Test(dataProvider = "testData")
  public void logInFromBookingScreen(String country,String city,
		  String email,String password,String description) throws Throwable {
	  try{
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);			  
		  //verify user already loggedIn, if yes sign out
		  handelSplashScreen();
		  navigateToMyAccount();
		  validateUserLogin();		  
		  navigateToBookNow();
		  selectDestination(country, city);
		  waitForElementPresent(HomePageLocators.searchButton, "searchButton");
		  click(HomePageLocators.searchButton, "searchButton");
		  waitForElementPresent(PickRoomPageLocators.pickRoomPage, "pickRoomPage");
		  click(PickRoomPageLocators.bookNowButton, "bookNowButton");
		  waitForElementPresent(BookPageLocators.contiuneButton,  "contiuneButton");
		  click(BookPageLocators.logInButton, "logInButton");
		  userlogin(email, password);
		  waitForElementPresent(BookPageLocators.contiuneButton,"contiuneButton");
		  click(BookPageLocators.contiuneButton, 
				  "contiuneButton");
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
