package com.mobile.scripts;

import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.support.ReportStampSupport;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.HomePageLocators;
import com.mobile.scripts.testObjects.InHousePhoneLocators;
import com.mobile.scripts.testObjects.LoginPageLocators;
import com.mobile.scripts.testObjects.PickRoomPageLocators;
import com.mobile.workflows.GeneralHelper;
import com.mobile.workflows.HomePageHelper;
import com.mobile.workflows.LoginHelper;

import io.appium.java_client.ios.IOSDriver;

public class RP_018_TestDeepLinks extends LoginHelper{
	ExcelReader xlsDeepLinks = new ExcelReader(configProps.getProperty("TestData"),
			"RP_018");	
  @Test(dataProvider = "testData")
  public void testDeepLinks(String firstName, String lastName, String emailId,
		  String password,boolean status, String description) throws Throwable{  
	try{
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, 
				description);
		  Set<String> contexts = null;
		  	navigateToMyAccount();
		  	validateUserLogin();
			click(LoginPageLocators.connectWithFacebookButton, "connectWithFacebookButton");							
			Thread.sleep(10000);
			GeneralHelper.deepLinkHelper("redplanet://hotel/1/start_date=2015-11-29&end_date=2015-11-30&guest=1");
			contexts = ((IOSDriver) driver).getContextHandles();
			for(String currContext : contexts){
				System.out.println("current context is :"+currContext);
				if(currContext.contains("NATIVE")){
					((IOSDriver) driver).context(currContext);
					waitForElementPresent(PickRoomPageLocators.checkInButtonOnPickRoom, "checkInButtonOnPickRoom");
					break;
				}
			}
			waitForElementPresent(PickRoomPageLocators.backButton, "backButton");
			click(PickRoomPageLocators.backButton, "backButton");
			navigateToMyAccount();
		  	validateUserLogin();

			click(LoginPageLocators.connectWithFacebookButton, "connectWithFacebookButton");							
			Thread.sleep(10000);
			GeneralHelper.deepLinkHelper("redplanet://in_stay_mode/7");
			contexts = ((IOSDriver) driver).getContextHandles();
			for(String currContext : contexts){
				System.out.println("current context is :"+currContext);
				if(currContext.contains("NATIVE")){
					((IOSDriver) driver).context(currContext);
					waitForElementPresent(HomePageLocators.inHousePhoneButton, "inHousePhoneButton");
					break;
				}
			}
			navigateToMyAccount();
		  	validateUserLogin();

			click(LoginPageLocators.connectWithFacebookButton, "connectWithFacebookButton");							
			Thread.sleep(10000);
			GeneralHelper.deepLinkHelper("redplanet://phone/9003");
			contexts = ((IOSDriver) driver).getContextHandles();
			for(String currContext : contexts){
				System.out.println("current context is :"+currContext);
				if(currContext.contains("NATIVE")){
					((IOSDriver) driver).context(currContext);
					waitForElementPresent(InHousePhoneLocators.callIcon, "callIcon");
					break;
				}
			}
			navigateToMyAccount();
		  	validateUserLogin();

			click(LoginPageLocators.connectWithFacebookButton, "connectWithFacebookButton");							
			Thread.sleep(10000);
			GeneralHelper.deepLinkHelper("redplanet://phone/9003");
			contexts = ((IOSDriver) driver).getContextHandles();
			for(String currContext : contexts){
				System.out.println("current context is :"+currContext);
				if(currContext.contains("NATIVE")){
					((IOSDriver) driver).context(currContext);
					waitForElementPresent(InHousePhoneLocators.callIcon, "callIcon");
					break;
				}
			}
			navigateToMyAccount();
		  	validateUserLogin();

			click(LoginPageLocators.connectWithFacebookButton, "connectWithFacebookButton");							
			Thread.sleep(10000);
			GeneralHelper.deepLinkHelper("redplanet://account");
			contexts = ((IOSDriver) driver).getContextHandles();
			for(String currContext : contexts){
				System.out.println("current context is :"+currContext);
				if(currContext.contains("NATIVE")){
					((IOSDriver) driver).context(currContext);
					waitForElementPresent(AccountPageLocators.upcomingBookings, "upcomingBookings");
					break;
				}
			}
			
			  /*if(waitForElementPresent(AccountPageLocators.errorMsgForSignUpExistingEmailID, 
					  "errorMsgForSignUpExistingEmailID")){
				  Reporter.SuccessReport(description, "Successful");
			  }else{
				  Reporter.failureReport("Sign Up with existing user Id",
						  "Failed to find error for sign up with existing user id "+emailId);
			  }*/
	}catch(Exception e) {
		e.printStackTrace();
		Reporter.failureReport(description, "Failed with exception");
	}	
  }

  	@DataProvider(name="testData")
	public Object[][] createdata1() {
  		String newEmail = "test"+ReportStampSupport.randomValue().concat("@gmail.com");
  		//String updatedName = xlsSearch.getCellValue("ModifiedFName", "Value")+" "+xlsSearch.getCellValue("ModifiedLName", "Value");
  		return (Object[][]) new Object[][] { 
			  {xlsDeepLinks.getCellValue("fName", "Value"),xlsDeepLinks.getCellValue("lName", "Value"),
				  newEmail,xlsDeepLinks.getCellValue("ValidCredentials","password"),true,"SignUp as new user"}};
	}
}
