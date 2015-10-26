package com.redplanet.workflows;


import org.openqa.selenium.Keys;

import com.ctaf.utilities.Reporter;
import com.redplanet.testObjects.HomePageLocators;

public class HomePageHelper extends HomePageLocators {

	
	public static boolean PageNavigation(String pagename) throws Throwable{
		boolean res = false;
		try{
			if(pagename.equalsIgnoreCase("Loyalty")){
				click(loyaltylink, "loyaltylink");
				if(waitForElementPresent(pageLoyalty, "pageLoyalty"))
				res = true;
			}else if(pagename.equalsIgnoreCase("Deals")){
				click(dealslink, "dealslink");
				if(waitForElementPresent(pageDeals, "pageDeals"))
				res = true;
			}else if(pagename.equalsIgnoreCase("MyBooking")){
				click(myBookingslink, "myBookingslink");
				if(waitForElementPresent(pageMyBooking, "pageMyBooking"))
				res = true;
			}else if(pagename.equalsIgnoreCase("Referred")){
				click(referredlink, "referredlink");
				if(waitForElementPresent(pageReferred, "pageReferred"))
				res = true;
			}else if(pagename.equalsIgnoreCase("login")){
				click(logInlink, "logInlink");
				if(waitForElementPresent(dialogLogIn, "dialogLogIn"))
				res = true;
			}else if(pagename.equalsIgnoreCase("aboutus")){
				click(aboutUslink, "aboutUslink");
				if(waitForElementPresent(pageAboutUs, "pageAboutUs"))
				res = true;
			}else if(pagename.equalsIgnoreCase("contactus")){
				click(contactUslink, "contactUslink");
				if(waitForElementPresent(pageContactus, "pageContactus"))
				res = true;
			}else if(pagename.equalsIgnoreCase("signout")){
				click(signOutlink, "signOutlink");
				res = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			Reporter.failureReport("PageNavigation", "Failed");
			return res;
		}
		return res;
		
	}

	
	public static boolean picSearchType(String dateSelectionType) throws Throwable{
		boolean res = false;
  
		try {
			Thread.sleep(2000);
			if (dateSelectionType.trim().equalsIgnoreCase("tonight")) {				
				waitForElementPresent(tonightButton, "tonightButton");
				click(tonightButton, "tonightButton");
				waitForElementPresent(checkOutDatePicker, "CheckOutDatePicker");
				click(checkOutDatePicker, "checkOutDatePicker");
				if(waitForElementPresent(checkOutDatePicker, "CheckOutDatePicker"));
				res = true;
			} else if (dateSelectionType.trim().equalsIgnoreCase("tomorrow")) {
				waitForElementPresent(tomorrowButton, "tomorrowButton");
				click(tomorrowButton, "tomorrowButton");
				if(waitForElementPresent(checkOutDatePicker, "CheckOutDatePicker"));
				res = true;
			} else {
				click(pickDatesButton, "Pickdates");
				if(waitForElementPresent(checkOutDatePicker, "CheckOutDatePicker"));
				res = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	
	public static boolean SearchForHotels(String location, String dateSelectionType, String checkIn, String checkOut, 
			String noOfAdults, String noOfChildren) throws Throwable {
	  boolean res = false;
	  /*String location = xlsrdr.getCellValue(configProps.getProperty("sheetName3"),"Location",IntTestRowNum );
	  String */
	  try{
		  waitForElementPresent(locationInputField, "locationInputField");
		  type(locationInputField, location, "Location");
		  driver.findElement(locationInputField).sendKeys(Keys.TAB);
		  waitForElementPresent(adultCombo, "adultCombo");
		  selectByVisibleText(adultCombo, noOfAdults,"adultCombo");
		  selectByVisibleText(childCombo, noOfChildren,"childCombo");
		  Thread.sleep(2000);
		  if(dateSelectionType.trim().equalsIgnoreCase("tonight")){					 
			  waitForElementPresent(checkOutDatePicker, "CheckOutDatePicker");
			  click(checkOutDatePicker, "checkOutDatePicker");
			  type(checkOutDatePicker, checkOut,"CheckOut");
			  
		  }else if(dateSelectionType.trim().equalsIgnoreCase("tomorrow")){			  
			  //type(checkOutDatePicker, checkOut , "CheckOut");
			  type(checkOutDatePicker, checkOut , "CheckOut");
		 }else{			  
			  
			  type(checkInDatePicker, checkIn, "CheckInDatePicker");
			  type(checkInDatePicker, checkIn, "CheckInDatePicker");

			  type(checkOutDatePicker, checkOut, "CheckOutDatePicker");
			  type(checkOutDatePicker, checkOut, "CheckOutDatePicker");
		 }
		 
		  waitForElementPresent(searchButton, "searchButton");
		  click(searchButton, "searchButton");
		  if(waitForElementPresent(searchResultsPage, "searchResultsPage")){
			  Reporter.SuccessReport("Verify Searching for Hotels", "Search results displayed for given data");
			  res = true;
		  }
	  }catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	 
  }
}
