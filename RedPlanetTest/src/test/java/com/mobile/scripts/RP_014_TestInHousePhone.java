package com.mobile.scripts;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.HomePageLocators;
import com.mobile.scripts.testObjects.InHousePhoneLocators;
import com.mobile.workflows.HomePageHelper;
import com.mobile.workflows.LoginHelper;

public class RP_014_TestInHousePhone  extends LoginHelper{
	ExcelReader xlsInHouse = new ExcelReader(configProps.getProperty("TestData"),
			"RP_014");
		@Test(dataProvider = "testData", groups = { "Mobile" })
  public void testInHousePhone(String dialNumber,
		  String email,String password,boolean status, String description) throws Throwable {
	  try{
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);			  
		  char[] phNo = dialNumber.toCharArray(); 
		  handelSplashScreen();
		  handleSplashDialog();
		  navigateToMyAccount();
		  //verify user already loggedIn, if yes sign out
		  handelSplashScreen();
		  handleSplashDialog();
		  validateUserLogin();		  
		  click(AccountPageLocators.logInButton, "logInButton");
		  login(email, password);
		  navigateToHome();
		  handelWelcomeDashboardDialog();
		  waitForElementPresent(HomePageLocators.chatWithFrontDeskButton, "chatWithFrontDeskButton");
		  click(HomePageLocators.inHousePhoneButton, "inHousePhoneButton");
		  if(isElementDisplayedTemp(HomePageLocators.microPhonePopUp)){
				click(HomePageLocators.microPhonePopUp, "microPhonePopUp");
			}
		  if(status){
			 if(phNo.length>0){
			  System.out.println("Dial Number "+phNo+" no of digits "+phNo.length);
			  int i=0;
			  while(i<=3){
			  for(char digi : phNo){
				  waitForElementPresent(By.xpath(InHousePhoneLocators.noButtonToDial.replace("#", Character.toString(digi))),
						  "noButtonToDial "+digi);
				  click(By.xpath(InHousePhoneLocators.noButtonToDial.replace("#", Character.toString(digi))),
						  "noButtonToDial"+digi);
				 }
				 click(InHousePhoneLocators.callIcon, "callIcon");
				 if((isElementDisplayed(InHousePhoneLocators.callingRoomLabel))){
					  Reporter.SuccessReport(description, " Successful");		
					  break;
				  }else{
					  if(i==3){
						  Reporter.failureReport(description, " Failed");
					  }
					  i++;
				  }
			  }
			 }else{
				 click(InHousePhoneLocators.callIcon, "callIcon");
				 if(description.contains("invalid")){
					  System.out.println("In Invalid room no block");
					 click(InHousePhoneLocators.callIcon, "callIcon");
				  if(!(isElementDisplayed(InHousePhoneLocators.callingRoomLabel))){
					  Reporter.SuccessReport(description, " Successful");
					  System.out.println("successfully failed to call Invalid room");
				  }else{
					  Reporter.failureReport(description, " Failed");
				  }
			 }
		  }
		}else if(description.contains("FrontDesk")){
				  System.out.println("In call Front Desk block");
				  int i=0;
				  while(i<=3){
				  click(InHousePhoneLocators.frontDeskButtonToCall, "frontDeskButtonToCall");
				  if((isElementDisplayed(InHousePhoneLocators.callingRoomLabel))){
					  Reporter.SuccessReport(description, " Successful");
					  System.out.println("successfully calling Front Desk ");
					  break;
				  }else{
					  if(i==3){
						  Reporter.failureReport(description, " Failed");
					  }
					  i++;
				  }
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
	    		{xlsInHouse.getCellValue("ValidRoomNo", "Value"),xlsInHouse.getCellValue("ValidCredentials", "Value"),
	    			xlsInHouse.getCellValue("ValidCredentials", "password"),true,"Validate InHousePhone to valid room number"},
	    		{"",xlsInHouse.getCellValue("ValidCredentials", "Value"),
	    				xlsInHouse.getCellValue("ValidCredentials", "password"),true,"Validate InHousePhone to invalid room number"},
	    		{"",xlsInHouse.getCellValue("ValidCredentials", "Value"),
	    			xlsInHouse.getCellValue("ValidCredentials", "password"),false,"Validate InHousePhone to FrontDesk"}};
	}
}
