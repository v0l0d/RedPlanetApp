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

public class RP_004_TestGuestBookingFromHome extends LoginHelper{
	ExcelReader xlsGuestBook = new ExcelReader(configProps.getProperty("TestData"),
			"RP_ANDR_004");
  @Test(dataProvider = "testData")
  public void testGuestBookingFromHome(String country, String city, String fName,String lName,String email,
		  String cardHolder,String cardNum,String expMonth,String cvv,String description) throws Throwable{
	  String bookingCode = null;
	try{
		 TestEngine.testDescription.put(HtmlReportSupport.tc_name, 
				description);	
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
			 //click(BookPageLocators.guestRadioButton, "guestRadioButton");
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
			 
			{xlsGuestBook.getCellValue("country", "Value"),xlsGuestBook.getCellValue("city", "Value"),
				  xlsGuestBook.getCellValue("fName", "Value"),xlsGuestBook.getCellValue("lName", "Value"),
				  xlsGuestBook.getCellValue("email", "Value"),xlsGuestBook.getCellValue("cardHolder", "Value"),
				  xlsGuestBook.getCellValue("cardNum", "Value"),xlsGuestBook.getCellValue("expirationmonth", "Value"),
				  xlsGuestBook.getCellValue("cvv", "Value"),
				  "Validate booking of a Hotel as Guest from Home screen with valid details"},
				  {xlsGuestBook.getCellValue("country", "Value"),xlsGuestBook.getCellValue("city", "Value"),
					  xlsGuestBook.getCellValue("fName", "Value"),xlsGuestBook.getCellValue("lName", "Value"),
					  xlsGuestBook.getCellValue("email", "Value"),xlsGuestBook.getCellValue("InValidcardHolder", "Value"),
					  xlsGuestBook.getCellValue("InValidCardNum", "Value"),xlsGuestBook.getCellValue("InExpirationmonth", "Value"),
					  xlsGuestBook.getCellValue("InValidcvv", "Value"),
					  "Validate booking of a Hotel as Guest from Home screen with Invalid details"},
					  {xlsGuestBook.getCellValue("country", "Value"),xlsGuestBook.getCellValue("city", "Value"),
						  xlsGuestBook.getCellValue("fName", "Value"),xlsGuestBook.getCellValue("lName", "Value"),
						  xlsGuestBook.getCellValue("email", "Value"),"",
						  "",xlsGuestBook.getCellValue("expirationmonth", "Value"),
						  xlsGuestBook.getCellValue("cvv", "Value"),
						  "Validate booking of a Hotel as Guest from Home screen with blank details"}};
	}
}
