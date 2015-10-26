package com.redplanet.workflows;

import com.ctaf.support.ExcelReader;
import com.ctaf.utilities.Reporter;
import com.redplanet.testObjects.SearchResultsLocators;


public class SearchResultsHelper extends SearchResultsLocators{
	
	static ExcelReader xlsrdr = new ExcelReader(configProps.getProperty("TestData"),configProps.getProperty("sheetName4"));
  
	  public static boolean SearchResults(String ModifySearch, String location, String checkIn, String checkOut, 
				String noOfAdults, String noOfChildren) throws Throwable {
		  boolean res = false;
		  
		  try{
			  waitForElementPresent(searchResultsPage, "searchResultsPage");
			  if(ModifySearch.equalsIgnoreCase("Yes")){
				  click(editSrchButton, "Edit Search");
				  if(waitForElementPresent(srchFilterFrame, "Search box filter")){
					   boolean rslt = ModifySearch(location, checkIn, checkOut, 
								noOfAdults, noOfChildren);
					   if(rslt){
						   Reporter.SuccessReport("Edit Search", "Passed");
					   }else
						   Reporter.failureReport("Edit Search", "Failed");
				  }
				  
			  }else{
				  click(booknowButton, "BookNow");
				  if(waitForElementPresent(roomSelectionPage, "hotelBookPage")){
					  res = true;
				  }
			  }
			  
		  }catch (Exception e) {
				e.printStackTrace();
				res = false;
			}
			
			return res;
	  }
	  
	  
	  
	  public static boolean ModifySearch(String location, String checkIn, String checkOut, 
				String noOfAdults, String noOfChildren) throws Throwable {
		  		
		  		boolean res = false;
			  type(LocInput,location , "Modify Location");
			  type(checkInDatePicker,checkIn, "CheckIn datepicker");
			  type(checkOutDatePicker,checkOut , "CheckOut datepicker");
			  type(adultCombo,noOfAdults, "Adults");
			  type(childCombo,noOfChildren , "Children");
			  click(btnSearch, "Search button");
			  if(waitForElementPresent(searchResultsPage, "searchResultsPage")){
				  Reporter.SuccessReport("Verify Searching Modification", "Search results displayed with modified data");
				  res = true;
			  }
			return res;
	  }
	  
}
