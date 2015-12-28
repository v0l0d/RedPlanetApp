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

    private static final String NOT_FULL_DATA = "Validate booking of a Hotel as Guest from Home screen with blank details";
    private static final String INVALID_DATA = "Validate booking of a Hotel as Guest from Home screen with Invalid details";
    private static final String VALID_DATA = "Validate booking of a Hotel as Guest from Home screen with Valid details";


	ExcelReader xlsGuestBook = new ExcelReader(configProps.getProperty("TestData"),
			"RP_ANDR_004");
  @Test(dataProvider = "testData")
  public void testGuestBookingFromHome(String country, String city, String fName,String lName,String email,
		  String cardHolder,String cardNum,String expMonth,String cvv,String description) throws Throwable{
	  String bookingCode = null;
	try{
        long timeOnStart = System.currentTimeMillis();
        TestEngine.testDescription.put(HtmlReportSupport.tc_name,
				description);
        //TODO add to all scripts method for navigate mainActivity screen and logout
		handleRateAppPopUp();
        System.out.println(" after 1st handleRateAppPopUp " + (System.currentTimeMillis() - timeOnStart) / 1000);
        navigateToMyAccount();
        System.out.println(" after navigateToMyAccount " + (System.currentTimeMillis() - timeOnStart) / 1000);
        handleRateAppPopUp();
        logOut();
        System.out.println(" after logOut " + (System.currentTimeMillis() - timeOnStart) / 1000);
        navigateToBookNow();
        handleRateAppPopUp();
        System.out.println(" after navigateToBookNow " + (System.currentTimeMillis() - timeOnStart) / 1000);
        selectDestination(country, city);
		handleRateAppPopUp();
        System.out.println(" after selectDestination " + (System.currentTimeMillis() - timeOnStart) / 1000);
		  waitForElementPresent(HomePageLocators.searchButton, "searchButton");
		 click(HomePageLocators.searchButton, "searchButton");
		 handleRateAppPopUp();
        System.out.println(" after searchButton " + (System.currentTimeMillis() - timeOnStart) / 1000);
		  if(waitForElementPresent(PickRoomPageLocators.pickRoomPage,"pickRoomPage")){
			 Reporter.SuccessReport("Search for Hotels", "Successful");
		 }else {
             Reporter.failureReport("Search for Hotels", "Failed");
         }
        System.out.println(" after pickRoomPage " + (System.currentTimeMillis() - timeOnStart) / 1000);
		 click(PickRoomPageLocators.bookNowButton, "bookNowButton");
		 handleRateAppPopUp();
        System.out.println(" after bookNowButton " + (System.currentTimeMillis() - timeOnStart) / 1000);
		  waitForElementPresent(BookPageLocators.contiuneButton,"contiuneButton");
         click(BookPageLocators.contiuneButton, "contiuneButton");
        System.out.println(" after contiuneButton " + (System.currentTimeMillis() - timeOnStart) / 1000);
         handleRateAppPopUp();
         BookingPageHelper.populateGuestDetails("", fName, lName, email, "");
        System.out.println(" after populateGuestDetails " + (System.currentTimeMillis() - timeOnStart) / 1000);
         //click(BookPageLocators.guestRadioButton, "guestRadioButton");
		 BookingPageHelper.populatePaymentDetails(cardHolder, cardNum, expMonth, cvv);
		 handleRateAppPopUp();
        System.out.println(" after populatePaymentDetails " + (System.currentTimeMillis() - timeOnStart) / 1000);
        if(description.equals(VALID_DATA)) {
//             Thread.sleep(5000); //TODO need to recheck this value
             waitForElementPresent(BookPageLocators.doneButton, "doneButton", 10);
             if(isElementDisplayed(BookPageLocators.bookingCode)){
                  bookingCode = driver.findElement(BookPageLocators.bookingCode).getText().trim();
                 Reporter.SuccessReport(description, "Successful"+" Booking code is: "+bookingCode);
             }else if(isElementDisplayed(BookPageLocators.errorPayment)){
                 Reporter.failureReport(description, "Failed to process payment, error message is: "
                         + driver.findElement(BookPageLocators.errorPayment).getText());
                 click(BookPageLocators.okButton, "okButton");
             }else {
                 Reporter.failureReport(description, "Failed");
             }
             click(BookPageLocators.doneButton, "doneButton");
		}else if(description.equals(INVALID_DATA)) {
			 if(isElementDisplayed(BookPageLocators.errorPayment)){
				  Reporter.SuccessReport(description, "Success error message "
							 + driver.findElement(BookPageLocators.errorPayment).getText());
                 click(BookPageLocators.okButton, "okButton");
			 }else			 
				 Reporter.failureReport(description, "Failed");
            driver.navigate().back();
            driver.navigate().back();
		}else if(description.equals(NOT_FULL_DATA)) {
			 if(isElementDisplayed(BookPageLocators.errorPayment)){
				  Reporter.SuccessReport(description, "Success error message "
							 + driver.findElement(BookPageLocators.errorPayment).getText());
				  click(BookPageLocators.okButton, "okButton");
			 }else			 
				 Reporter.failureReport(description, "Failed");
            driver.navigate().back();
            driver.navigate().back();
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
					 xlsGuestBook.getCellValue("email", "Value"),"",
					 "",xlsGuestBook.getCellValue("expirationmonth", "Value"),
					 xlsGuestBook.getCellValue("cvv", "Value"),
                    NOT_FULL_DATA},
				{xlsGuestBook.getCellValue("country", "Value"),xlsGuestBook.getCellValue("city", "Value"),
					  xlsGuestBook.getCellValue("fName", "Value"),xlsGuestBook.getCellValue("lName", "Value"),
					  xlsGuestBook.getCellValue("email", "Value"),xlsGuestBook.getCellValue("InValidcardHolder", "Value"),
					  xlsGuestBook.getCellValue("InValidCardNum", "Value"),xlsGuestBook.getCellValue("InExpirationmonth", "Value"),
					  xlsGuestBook.getCellValue("InValidcvv", "Value"),
					INVALID_DATA},
			{xlsGuestBook.getCellValue("country", "Value"),xlsGuestBook.getCellValue("city", "Value"),
				  xlsGuestBook.getCellValue("fName", "Value"),xlsGuestBook.getCellValue("lName", "Value"),
				  xlsGuestBook.getCellValue("email", "Value"),xlsGuestBook.getCellValue("cardHolder", "Value"),
				  xlsGuestBook.getCellValue("cardNum", "Value"),xlsGuestBook.getCellValue("expirationmonth", "Value"),
				  xlsGuestBook.getCellValue("cvv", "Value"),
				  VALID_DATA}};
	}
}
