package com.mobile.scripts;

import org.openqa.selenium.By;
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
import com.mobile.scripts.testObjects.InHousePhoneLocators;

public class RP_014_TestInHousePhone  extends LoginHelper{
	ExcelReader xlsInHouse = new ExcelReader(configProps.getProperty("TestData"),
			"RP_014");
		@Test(dataProvider = "testData")
  public void testInHousePhone(String dialNumber,
		  String email,String password,boolean status, String description) throws Throwable {
	  try{
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);			  
		  char[] phNo = dialNumber.toCharArray(); 
		  navigateToMyAccount();
		  //verify user already loggedIn, if yes sign out
		  validateUserLogin();		  
		  click(AccountPageLocators.logInButton, "logInButton");
		  login(email, password);
		  navigateToHome();
		  handelWelcomeDashboardDialog();
		  waitForElementPresent(HomePageLocators.chatWithFrontDeskButton, "chatWithFrontDeskButton");
		  click(HomePageLocators.inHousePhoneButton, "inHousePhoneButton");
		  Thread.sleep(2000);
		  if(status){
			  System.out.println("Dial Number "+phNo+" no of digits "+phNo.length);
				 for(char digi : phNo){
				  waitForElementPresent(By.xpath(InHousePhoneLocators.noButtonToDial.replace("#", Character.toString(digi))),
						  "noButtonToDial "+digi);
				  click(By.xpath(InHousePhoneLocators.noButtonToDial.replace("#", Character.toString(digi))),
						  "noButtonToDial"+digi);
				 }
			  click(InHousePhoneLocators.callIcon, "callIcon");
			  if(isElementDisplayed(InHousePhoneLocators.rigingLabel)){
				  Reporter.SuccessReport(description, " Successful");			  
			  }else{
				  if(description.contains("invalid")){
					  System.out.println("In Invalid room no block");
					 click(InHousePhoneLocators.callIcon, "callIcon");
				  if(!(isElementDisplayed(InHousePhoneLocators.callingRoomLabel))){
					  Reporter.SuccessReport(description, " Successful");
					  System.out.println("successfully failed to call Invalid room");
				  }
			  }
				  Reporter.failureReport(description, " Failed");
		  }
		}else if(description.contains("FrontDesk")){
				  System.out.println("In call Front Desk block");
				  click(InHousePhoneLocators.frontDeskButtonToCall, "frontDeskButtonToCall");
				  if((isElementDisplayed(InHousePhoneLocators.rigingLabel))){
					  Reporter.SuccessReport(description, " Successful");
					  System.out.println("successfully calling Front Desk ");
				  }else{
					  Reporter.failureReport(description, " Failed");
				  }
			  }
	  }catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("LogIn", "Failed");
		}
  }
  
  @DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		/*{xlsInHouse.getCellValue("ValidRoomNo", "Value"),
	    			xlsInHouse.getCellValue("ValidCredentials", "Value"),
	    			xlsInHouse.getCellValue("ValidCredentials", "password"),true,"Validate InHousePhone to valid room number"},*/
	    		{xlsInHouse.getCellValue("InvalidRoomNo", "Value"),
	    				xlsInHouse.getCellValue("ValidCredentials", "Value"),xlsInHouse.getCellValue("ValidCredentials", "password"),
		    			true,"Validate InHousePhone to invalid room number"},
	    		{xlsInHouse.getCellValue("InvalidRoomNo", "Value"),
		    				xlsInHouse.getCellValue("ValidCredentials", "Value"),xlsInHouse.getCellValue("ValidCredentials", "password"),
			    			false,"Validate InHousePhone to FrontDesk"}};
	}
}
