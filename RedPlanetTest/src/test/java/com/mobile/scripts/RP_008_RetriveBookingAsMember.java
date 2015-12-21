package com.mobile.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.BookPageLocators;
import com.mobile.scripts.testObjects.HomePageLocators;
import com.mobile.scripts.testObjects.PickRoomPageLocators;
import com.mobile.scripts.testObjects.RetrieveBookingLocators;
import com.mobile.workflows.BookingPageHelper;
import com.mobile.workflows.LoginHelper;
import com.mobile.workflows.RetrieveBookingPageHelper;

public class RP_008_RetriveBookingAsMember extends LoginHelper{
	ExcelReader xlsRetrive = new ExcelReader(configProps.getProperty("TestData"),
			"RP_008");
  @Test(dataProvider = "testData" , groups = { "Mobile" })
  public void retriveBookingAsMember(String emailID,String password, String country, String city,String fName,
		  String lName,String email,String cardHolder,
		  String cardNum,String expMonth,String expYear,String cvv, String description) throws Throwable{
	  	String bookingCode = null;
	try{
		 TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
		 handelSplashScreen();
		 //handleSplashDialog();
		 navigateToMyAccount();
		 validateUserLogin();
		click(AccountPageLocators.logInButton, "logInButton");	
		 login(emailID, password);
		 navigateToBookNow();
		 selectDestination(country, city);
		 waitForElementPresent(HomePageLocators.searchButton, "searchButton");
		 click(HomePageLocators.searchButton, "searchButton");
		 if(isElementDisplayed(PickRoomPageLocators.pickRoomPage)){
			 Reporter.SuccessReport("Search for Hotels", "Successful");
		 }else{
			 Reporter.failureReport("Search for Hotels", "Failed");
		}
		 waitForElementPresent(PickRoomPageLocators.bookNowButton, "bookNowButton");
		 click(PickRoomPageLocators.bookNowButton, "bookNowButton");
		 waitForElementPresent(BookPageLocators.contiuneButton, "contiuneButton");	 
		 click(BookPageLocators.contiuneButton, "contiuneButton");
			 BookingPageHelper.populateGuestDetails("", fName, lName, email, "");
			 BookingPageHelper.populatePaymentDetails(cardHolder, cardNum, expMonth, expYear, cvv);
		 waitForElementPresent(BookPageLocators.doneButton, "doneButton");
		 if(isElementDisplayed(BookPageLocators.bookingCode)){
			  String temp = getText(BookPageLocators.bookingCode,"bookingCode");
			  bookingCode = temp.split(":")[1].trim();
			  logger.info("bookingCode "+bookingCode);
			 Reporter.SuccessReport(description, "Successful"+" Booking code is: "+bookingCode);
		 }else if(isElementDisplayed(BookPageLocators.errorPayment)){
			 Reporter.failureReport(description, "Failed to process payment, error message is: "
					 + driver.findElement(BookPageLocators.errorPayment).getText());
			 click(BookPageLocators.okButton, "okButton");
		 }else{		 
			 Reporter.failureReport(description, "Failed");
		 }
		 click(BookPageLocators.doneButton, "doneButton");
		 navigateToReirieveBooking();
		 RetrieveBookingPageHelper.RetrieveBooking(email,bookingCode);
		 if(isElementPresent(RetrieveBookingLocators.bookingDetailView)){
			 Reporter.SuccessReport(description, "Successful and booking details retrived");
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
			  {xlsRetrive.getCellValue("ValidCredentials", "Value"),xlsRetrive.getCellValue("ValidCredentials", "password"),
				  xlsRetrive.getCellValue("country", "Value"),xlsRetrive.getCellValue("city", "Value"),
				  xlsRetrive.getCellValue("fName", "Value"),xlsRetrive.getCellValue("lName", "Value"),
				  xlsRetrive.getCellValue("email", "Value"),xlsRetrive.getCellValue("cardHolder", "Value"),
				  xlsRetrive.getCellValue("cardNum", "Value"),xlsRetrive.getCellValue("expirationMonth", "Value"),
				  xlsRetrive.getCellValue("expirationYear", "Value"),
				  xlsRetrive.getCellValue("cvv", "Value"),"Validate Retrive Booking Details"}};
	}
}
