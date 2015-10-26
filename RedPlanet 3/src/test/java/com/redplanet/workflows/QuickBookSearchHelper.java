package com.redplanet.workflows;

import org.openqa.selenium.Keys;

import com.ctaf.utilities.Reporter;
import com.redplanet.testObjects.QuickBookSearch;

public class QuickBookSearchHelper extends QuickBookSearch{
  
	public static boolean picSearchType(String dateSelectionType) throws Throwable{
		boolean res = false;
  
		try {
			if (dateSelectionType.trim().equalsIgnoreCase("tonight")) {
				waitForElementPresent(tonightButton, "tonightButton");
				click(tonightButton, "tonightButton");
				waitForElementPresent(checkOutDatePicker, "CheckOutDatePicker");
				click(checkOutDatePicker, "checkOutDatePicker");
				waitForElementPresent(checkOutDatePicker, "CheckOutDatePicker");
				res = true;
			} else if (dateSelectionType.trim().equalsIgnoreCase("tomorrow")) {
				waitForElementPresent(tomorrowButton, "tomorrowButton");
				click(tomorrowButton, "tomorrowButton");
				waitForElementPresent(checkOutDatePicker, "CheckOutDatePicker");
				res = true;
			} else {
				click(pickDatesButton, "Pickdates");
				res = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	public static boolean QuickBookSearch(String location, String dateSelectionType, String checkIn, String checkOut, 
			String noOfAdults, String noOfChildren) throws Throwable {
	  boolean res = false;
	  
	  try{
		  waitForElementPresent(locationInput, "locationInput");
		  type(locationInput, location, "Location");
		  driver.findElement(locationInput).sendKeys(Keys.TAB);
		  waitForElementPresent(adultCombo, "adultCombo");
		  selectByVisibleText(adultCombo, noOfAdults,"adultCombo");
		  selectByVisibleText(childCombo, noOfChildren,"childCombo");
		  if(dateSelectionType.trim().equalsIgnoreCase("tonight")){			 
			  waitForElementPresent(checkOutDatePicker, "CheckOutDatePicker");
			  click(checkOutDatePicker, "checkOutDatePicker");
			  type(checkOutDatePicker, checkOut,"CheckOut");
			  Thread.sleep(6000);
		  }else if(dateSelectionType.trim().equalsIgnoreCase("tomorrow")){
			  type(checkOutDatePicker, checkOut , "CheckOut");
			  type(checkOutDatePicker, checkOut , "CheckOut");
		 }else{
			  click(pickDatesButton, "Pickdates");
			  click(checkInDatePicker, "checkInDatePicker");
			  type(checkInDatePicker, checkIn, "CheckInDatePicker");
			  click(checkOutDatePicker, "checkOutDatePicker");
			  type(checkOutDatePicker, checkOut, "CheckOutDatePicker");
		 }
		 
		  waitForElementPresent(searchButton, "searchButton");
		  click(searchButton, "searchButton");
		  if(waitForElementPresent(searchResultsPage, "searchResultsPage")){
			  Reporter.SuccessReport("Validate quick book search", "Passed");
			  res = true;
		  }
	  }catch (Exception e) {
			e.printStackTrace();
			res = false;
			Reporter.failureReport("Validate quick book search", "Failed");
		}
		return res;
	 
  }
	
}
