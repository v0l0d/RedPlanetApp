package com.androidMobile.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.androidMobile.scripts.testObjects.BookPageLocators;
import com.androidMobile.scripts.testObjects.HomePageLocators;
import com.androidMobile.scripts.testObjects.PickRoomPageLocators;
import com.androidMobile.workflows.BookingPageHelper;
import com.androidMobile.workflows.HomePageHelper;
import com.androidMobile.workflows.LoginHelper;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

public class RP_005_TestGuestBookingFromBookNow extends LoginHelper{
	ExcelReader xlsSearch = new ExcelReader(configProps.getProperty("TestData"),
			"RP_ANDR_005");
  @Test(dataProvider = "testData")
  public void testGuestBookingFromBookNow(String country, String city, String fName,String lName,String email,
		  String cardHolder,String cardNum,String expMonth,String cvv,String description) throws Throwable{
	  String bookingCode = null;
	try{
		 TestEngine.testDescription.put(HtmlReportSupport.tc_name, 
				description);	
		 navigateToBookNow();
		 selectDestination(country, city);
		 HomePageHelper.handleRateAppPopUp();
		 waitForElementPresent(HomePageLocators.searchButton, "searchButton");
		 click(HomePageLocators.searchButton, "searchButton");
		 HomePageHelper.handleRateAppPopUp();
		 if(waitForElementPresent(PickRoomPageLocators.pickRoomPage,"pickRoomPage")){
			 Reporter.SuccessReport("Search for Hotels", "Successful");
		 }else
			 Reporter.failureReport("Search for Hotels", "Failed");
		 click(PickRoomPageLocators.bookNowButton, "bookNowButton");
		 HomePageHelper.handleRateAppPopUp();
		 waitForElementPresent(BookPageLocators.contiuneButton,"contiuneButton");		 
		 click(BookPageLocators.contiuneButton, "contiuneButton");
		 HomePageHelper.handleRateAppPopUp();
		 BookingPageHelper.populateGuestDetails("", fName, lName, email, "");
		 BookingPageHelper.populatePaymentDetails(cardHolder, cardNum, expMonth, cvv);
		 HomePageHelper.handleRateAppPopUp();
		if(description.contains("Valid details")){
		 waitForElementPresent(BookPageLocators.doneButton, "doneButton");
		 if(isElementDisplayed(BookPageLocators.bookingCode)){
			  bookingCode = driver.findElement(BookPageLocators.bookingCode).getText().trim();
			 Reporter.SuccessReport(description, "Successful"+" Booking code is: "+bookingCode);
		 }else if(isElementDisplayed(BookPageLocators.errorPayment)){
			 Reporter.failureReport(description, "Failed to process payment, error message is: "
					 + driver.findElement(BookPageLocators.errorPayment).getText());
			 click(BookPageLocators.okButton, "okButton");
		 }else			 
			 Reporter.failureReport(description, "Failed");
		}else if(description.contains("Invalid details")){
			 if(isElementDisplayed(BookPageLocators.errorPayment)){
				  Reporter.SuccessReport(description, "Success error message "
							 + driver.findElement(BookPageLocators.errorPayment).getText());
			 }else			 
				 Reporter.failureReport(description, "Failed");
		}else if(description.contains("blank details")){
			 if(isElementDisplayed(BookPageLocators.errorPayment)){
				  Reporter.SuccessReport(description, "Success error message "
							 + driver.findElement(BookPageLocators.errorPayment).getText());
				  click(BookPageLocators.okButton, "okButton");
			 }else			 
				 Reporter.failureReport(description, "Failed");
		}
		
	}catch(Exception e) {
		e.printStackTrace();
		Reporter.failureReport(description, "Failed with exception");
	}	
  }

  	@DataProvider(name="testData")
	public Object[][] createdata1() {
  		
  		return (Object[][]) new Object[][] { 
			 
			{xlsSearch.getCellValue("country", "Value"),xlsSearch.getCellValue("city", "Value"),
				xlsSearch.getCellValue("fName", "Value"),xlsSearch.getCellValue("lName", "Value"),
				 xlsSearch.getCellValue("email", "Value"),xlsSearch.getCellValue("cardHolder", "Value"),
				 xlsSearch.getCellValue("cardNum", "Value"),xlsSearch.getCellValue("expirationmonth", "Value"),
				 xlsSearch.getCellValue("cvv", "Value"),
				  "Validate booking of a Hotel as Guest from Booking screen with valid details"},
			{xlsSearch.getCellValue("country", "Value"),xlsSearch.getCellValue("city", "Value"),
				xlsSearch.getCellValue("fName", "Value"),xlsSearch.getCellValue("lName", "Value"),
				xlsSearch.getCellValue("email", "Value"),xlsSearch.getCellValue("InValidcardHolder", "Value"),
				xlsSearch.getCellValue("InValidCardNum", "Value"),xlsSearch.getCellValue("InExpirationmonth", "Value"),
				xlsSearch.getCellValue("InValidcvv", "Value"),
				 "Validate booking of a Hotel as Guest from Booking screen with Invalid details"},
			 {xlsSearch.getCellValue("country", "Value"),xlsSearch.getCellValue("city", "Value"),
						  xlsSearch.getCellValue("fName", "Value"),xlsSearch.getCellValue("lName", "Value"),
						  xlsSearch.getCellValue("email", "Value"),"",
						  "",xlsSearch.getCellValue("expirationmonth", "Value"),
						  xlsSearch.getCellValue("cvv", "Value"),
						  "Validate booking of a Hotel as Guest from Booking screen with blank details"}};
	}
}
