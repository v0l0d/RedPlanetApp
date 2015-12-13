package com.mobile.scripts;

import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.FacebookLoginLocators;
import com.mobile.scripts.testObjects.HomePageLocators;
import com.mobile.scripts.testObjects.InHousePhoneLocators;
import com.mobile.scripts.testObjects.LoginPageLocators;
import com.mobile.scripts.testObjects.PickRoomPageLocators;
import com.mobile.workflows.GeneralHelper;
import com.mobile.workflows.LoginHelper;

import io.appium.java_client.ios.IOSDriver;

public class RP_018_TestDeepLinks extends LoginHelper{
	ExcelReader xlsDeepLinks = new ExcelReader(configProps.getProperty("TestData"),
			"RP_018");	
  @Test(dataProvider = "testData" , groups = { "Mobile" })
  public void testDeepLinks(String firstName, String lastName, String emailId,
		  String password,boolean status, String description) throws Throwable{  
	try{
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
		  handelSplashScreen();
		  //handleSplashDialog();
		  Set<String> contexts = null;
		  	navigateToMyAccount();
		  	validateUserLogin();
			click(LoginPageLocators.connectWithFacebookButton, "connectWithFacebookButton");							
			Thread.sleep(10000);
			GeneralHelper.deepLinkHelper("redplanet://hotel/"+xlsDeepLinks.getCellValue("HotelNo", "Value"));
			contexts = ((IOSDriver) driver).getContextHandles();
			for(String currContext : contexts){
				System.out.println("current context is :"+currContext);
				if(currContext.contains("NATIVE")){
					((IOSDriver) driver).context(currContext);
					if(Iosdriver.getPageSource().contains(xlsDeepLinks.getCellValue("HotelName", "Value"))){
						  Reporter.SuccessReport("Verify Search Hotel with Hotel No", "Successful");
					}
					break;
				}
			}
			waitForElementPresent(PickRoomPageLocators.backButton, "backButton");
			click(PickRoomPageLocators.backButton, "backButton");
			navigateToMyAccount();
		  	validateUserLogin();
			click(LoginPageLocators.connectWithFacebookButton, "connectWithFacebookButton");							
			Thread.sleep(10000);
			GeneralHelper.deepLinkHelper("redplanet://in_stay_mode");
			contexts = ((IOSDriver) driver).getContextHandles();
			for(String currContext : contexts){
				System.out.println("current context is :"+currContext);
				if(currContext.contains("NATIVE")){
					((IOSDriver) driver).context(currContext);
					waitForElementPresent(FacebookLoginLocators.redPlanetApplication, "redPlanetApplication");
					navigateToMyAccount();
					click(AccountPageLocators.logInButton, "logInButton");
					login(emailId, password);
					Iosdriver.closeApp();
					Iosdriver.launchApp();
					waitForElementPresent(HomePageLocators.inHousePhoneButton, "inHousePhoneButton");
					break;
				}
			}
			Iosdriver.closeApp();
			Iosdriver.launchApp();
			handelSplashScreen();
			navigateToMyAccount();
		  	validateUserLogin();
			click(LoginPageLocators.connectWithFacebookButton, "connectWithFacebookButton");							
			Thread.sleep(10000);
			GeneralHelper.deepLinkHelper("redplanet://phone");
			contexts = ((IOSDriver) driver).getContextHandles();
			for(String currContext : contexts){
				System.out.println("current context is :"+currContext);
				if(currContext.contains("NATIVE")){
					((IOSDriver) driver).context(currContext);
					waitForElementPresent(FacebookLoginLocators.redPlanetApplication, "redPlanetApplication");
					navigateToMyAccount();
					click(AccountPageLocators.logInButton, "logInButton");
					login(emailId, password);
					waitForElementPresent(InHousePhoneLocators.callIcon, "callIcon");
					break;
				}
			}
			Iosdriver.closeApp();
			Iosdriver.launchApp();
			handelSplashScreen();
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
					waitForElementPresent(FacebookLoginLocators.redPlanetApplication, "redPlanetApplication");
					navigateToMyAccount();
					click(AccountPageLocators.logInButton, "logInButton");
					login(emailId, password);
					Iosdriver.closeApp();
					Iosdriver.launchApp();
					handelSplashScreen();
					waitForElementPresent(HomePageLocators.inHousePhoneButton, "inHousePhoneButton");
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
  		return (Object[][]) new Object[][] { 
			  {xlsDeepLinks.getCellValue("fName", "Value"),xlsDeepLinks.getCellValue("lName", "Value"),
				  xlsDeepLinks.getCellValue("ValidCredentials","Value"),
				  xlsDeepLinks.getCellValue("ValidCredentials","password"),true,"SignUp as new user"}};
	}
}
