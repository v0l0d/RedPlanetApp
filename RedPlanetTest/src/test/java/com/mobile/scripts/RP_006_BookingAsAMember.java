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
import com.mobile.workflows.BookingPageHelper;
import com.mobile.workflows.LoginHelper;

public class RP_006_BookingAsAMember extends LoginHelper{
	ExcelReader xlsBook = new ExcelReader(configProps.getProperty("TestData"),
			"RP_006");
  @Test(dataProvider = "testData" , groups = { "Mobile" })
  public void testBookingAsMember(String emailId,String password,String country, String city,
		 String fName,String lName,String email,String cardHolder,String cardNum,
		 String expMonth, String expYear, String cvv,boolean status,
		 String description) throws Throwable{
	  	 System.out.println(country +" "+city);
	  	String bookingCode = "";
	  	String bookedCity = "";
	  	String bookingFromToDate = "";
	  	String bookedHotel = "";
	  	String totalBookingCost = "";
	try{
		 TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
		 handelSplashScreen();
		 //handleSplashDialog();
		 System.out.println("userId "+emailId+" password "+password);
		 navigateToMyAccount();
		 validateUserLogin();
		 click(AccountPageLocators.logInButton,"logInButton");
		 login(emailId,password);
		 navigateToBookNow();
	     selectDestination(country, city);
		 waitForElementPresent(HomePageLocators.searchButton, "searchButton");
		 click(HomePageLocators.searchButton, "searchButton");
		 Thread.sleep(10000);		 
		 if(isElementDisplayed(PickRoomPageLocators.pickRoomPage)){
			 Reporter.SuccessReport("Search for Hotels", "Successful");
		 }else
			 Reporter.failureReport("Search for Hotels", "Failed");
		 click(PickRoomPageLocators.bookNowButton, "bookNowButton");
		 Thread.sleep(5000);
		 isElementDisplayed(BookPageLocators.contiuneButton);	 
			 click(BookPageLocators.contiuneButton, "contiuneButton");
			 BookingPageHelper.populateGuestDetails("", fName, lName, email, "");
			 BookingPageHelper.populatePaymentDetails(cardHolder, cardNum, expMonth, expYear, cvv);
		 Thread.sleep(15000);
		 if(!status){
			if(isElementDisplayed(BookPageLocators.errorPayment)){
				String err = driver.findElement(BookPageLocators.inValidError).getAttribute("value").trim();
				System.out.println("+++++++++++++++++++"+err+"+++++++++++");
				 Reporter.SuccessReport("Verify error with invalid payment details ",
						 "Successfully verified error for invalid payment details : "+err);
			}
		 }else{
			 waitForElementPresent(BookPageLocators.doneButton, "doneButton");
			 if(isElementDisplayed(BookPageLocators.bookingCode)){
				  bookingCode = driver.findElement(BookPageLocators.bookingCode).getAttribute("value").trim();
				 Reporter.SuccessReport("Validate Successful Booking "," Booking code is: "+bookingCode);
				 bookedCity = driver.findElement(BookPageLocators.bookedCity).getText().trim();
				 bookingFromToDate = driver.findElement(BookPageLocators.bookingFromToDate).getText().trim();
				 bookedHotel = driver.findElement(BookPageLocators.bookedHotel).getText().trim();
				 totalBookingCost = driver.findElement(BookPageLocators.totalCost).getText().trim();
				 Reporter.SuccessReport("Validate Successful Booking "," Booking City is: "+bookedCity);
				 Reporter.SuccessReport("Validate Successful Booking "," Booking Hotel is: "+bookedHotel);
				 Reporter.SuccessReport("Validate Successful Booking "," FromToBookingDate  is: "+bookingFromToDate);
				 Reporter.SuccessReport("Validate Successful Booking "," Total Booking Cost is: "+totalBookingCost);
				 click(BookPageLocators.doneButton, "doneButton");
				 navigateToMyAccount();
				 waitForElementPresent(AccountPageLocators.upcomingBookings, "upcomingBookings");
				 if((scrollToText(bookedCity.toLowerCase()))&(scrollToText(totalBookingCost))){
				 Reporter.SuccessReport("Validate Booking details under My Account Screen ",
						 " Successfully validated Booking details City : "+bookedCity+" Hotel "
						 		+bookedHotel+" From To Date "+bookingFromToDate+ " and Total Cost "+totalBookingCost);
				 }else{
					 Reporter.failureReport("Validate Booking details under My Account Screen ",
							 "Failed to validated Booking details City : "+bookedCity+" Hotel "
						 		+bookedHotel+" From To Date "+bookingFromToDate+ " and Total Cost "+totalBookingCost);
				 }
			 }else if(isElementDisplayed(BookPageLocators.errorPayment)){
				 Reporter.failureReport(description, "Failed to process payment, error message is: "
						 + driver.findElement(BookPageLocators.errorPayment).getText());
				 click(BookPageLocators.okButton, "okButton");
			 }
		 }
	}catch(Exception e) {
		e.printStackTrace();
		Reporter.failureReport(description, "Failed with exception");
	}	
  }

  	@DataProvider(name="testData")
	public Object[][] createdata1() {
  		return (Object[][]) new Object[][] {
  			{xlsBook.getCellValue("ValidCredentials", "Value"),xlsBook.getCellValue("ValidCredentials", "password"),
		  		xlsBook.getCellValue("country", "Value"),xlsBook.getCellValue("city", "Value"),
				xlsBook.getCellValue("fName", "Value"),xlsBook.getCellValue("lName", "Value"),
				xlsBook.getCellValue("email", "Value"),"",
				"",xlsBook.getCellValue("expirationmonth", "Value"),
				xlsBook.getCellValue("expirationyear", "Value"), xlsBook.getCellValue("invalidCVV", "Value"),
				false,"Validate Hotel Booking as a Guest with blank payment details"},
  			{xlsBook.getCellValue("ValidCredentials", "Value"),xlsBook.getCellValue("ValidCredentials", "password"),
	  				xlsBook.getCellValue("country", "Value"),xlsBook.getCellValue("city", "Value"),
			  		xlsBook.getCellValue("fName", "Value"),xlsBook.getCellValue("lName", "Value"),
			  		xlsBook.getCellValue("email", "Value"),xlsBook.getCellValue("invalidCardHolder", "Value"),
			  		xlsBook.getCellValue("invalidCardNum", "Value"),xlsBook.getCellValue("expirationmonth", "Value"),
			  		xlsBook.getCellValue("expirationyear", "Value"),xlsBook.getCellValue("invalidCVV", "Value"),
			  		false,"Validate Hotel Booking as a Guest with invalid payment details"},
  			{xlsBook.getCellValue("ValidCredentials", "Value"),xlsBook.getCellValue("ValidCredentials", "password"),
  				xlsBook.getCellValue("country", "Value"),xlsBook.getCellValue("city", "Value"),
  				xlsBook.getCellValue("fName", "Value"),xlsBook.getCellValue("lName", "Value"),
  				xlsBook.getCellValue("email", "Value"),xlsBook.getCellValue("cardHolder", "Value"),
  				xlsBook.getCellValue("cardNum", "Value"),xlsBook.getCellValue("expirationmonth", "Value"),
  				xlsBook.getCellValue("expirationyear", "Value"), xlsBook.getCellValue("cvv", "Value"),
  				true,"Validate Hotel Booking as a Guest with valid payment details"}
  			
			};
	}
}
