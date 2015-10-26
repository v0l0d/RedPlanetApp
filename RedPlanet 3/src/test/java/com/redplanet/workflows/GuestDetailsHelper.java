package com.redplanet.workflows;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ctaf.utilities.Reporter;
import com.redplanet.testObjects.GuestDetailsLocators;


public class GuestDetailsHelper extends GuestDetailsLocators {
  
  public static boolean GuestDetails(String fName,String lName,String email,String countryname,
		  								String phone) throws Throwable {
	  boolean res = false;
	  try{
		  	  
			  waitForElementPresent(guestDetailsFrame, "guestDetailsFrame");
			  type(firstNameInput, fName, "firstNameInput");
			  type(lastNameInput, lName, "lNameInput");
			  type(emailInput, email, "emailInput");
			  SelectCountryCode(countryname);
			  type(phoneInput, phone, "phoneInput");
			  click(continueButton, "continueButton");
			  if(waitForElementPresent(paymentDtsframe, "paymentDtsframe")){				  
				  res = true;				 
				  Reporter.SuccessReport("GuestDetails", "Passed");
			  }else
				  Reporter.failureReport("GuestDetails", "Failed");
			  
	  }catch(Exception e){
		e.printStackTrace();
		res = false;
		return res;
	  }
	  
	  return res;
  }
  
  public static void SelectCountryCode(String countryname) throws Throwable{
	  
	  waitForElementPresent(countrycodeDropDown, "countrycodeDropdown");
	  click(countrycodeDropDown, "countrycodeDropdown");
	  waitForElementPresent(By.xpath(countriesList), "countriesList");
	  List<WebElement> countries = driver.findElements(By.xpath(countriesList));
	  for(WebElement country:countries){
		  System.out.println(country.getText());
		  if(country.getText().contains(countryname)){
			  System.out.println(country.getText());
			  country.click();
			  Reporter.SuccessReport("Click", "Successfully clicked on "
						+ country.getText()+"countryName");
			  break;
		  }		  
	  }
	  
  }
}
