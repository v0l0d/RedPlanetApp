package com.mobile.scripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.ForgotPasswordLocators;
import com.mobile.scripts.testObjects.LoginPageLocators;
import com.mobile.workflows.ForgotPasswordHelper;
import com.mobile.workflows.LoginHelper;

public class RP_011_TestForgotPassword  extends LoginHelper{
	
	ExcelReader xlsReset = new ExcelReader(configProps.getProperty("TestData"),
			"RP_011");
		@Test(dataProvider = "testData")
  public void testForgotPassword(
		  String email,String password,String resetPwd, boolean status, String description) throws Throwable {
		String gmailUrl = "https://www.gmail.com" ;
		try{
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);			  
		  //verify user already loggedIn, if yes sign out
		  handelSplashScreen();
		  //handleSplashDialog();
		  navigateToMyAccount();
		  validateUserLogin();
		  waitForElementPresent(AccountPageLocators.logInButton,"logInButton");
		  click(AccountPageLocators.logInButton,"logInButton");
		  Thread.sleep(2000);
		  waitForElementPresent(LoginPageLocators.forgotPasswrod, "forgotPasswrod");
		  //type(LoginPageLocators.emailFieldForLogin, email, "emailFieldForLogin");
		  //type(LoginPageLocators.passwordFieldForLogin,"Password1","passwordFieldForLogin");
		  //Iosdriver.hideKeyboard();
		  Thread.sleep(2000);
		  ForgotPasswordHelper.navigateToForgotPasswordScreen();
		  waitForElementPresent(ForgotPasswordLocators.emailInputField,  "emailInputField");
		  if(description.contains("cancel")){
				 waitForElementPresent(ForgotPasswordLocators.signInBackButton, "signInBackButton");
				 click(ForgotPasswordLocators.signInBackButton, "signInBackButton");
				 if(isElementDisplayed(LoginPageLocators.emailFieldForLogin)){
					 Reporter.SuccessReport("Validate cancel ResetPassword", "Successful ");
				 }else{
					 Reporter.failureReport("Validate cancel ResetPassword", "Failed ");
				 }

		  }
		  if(status){
			  type(ForgotPasswordLocators.emailInputField, email, "emailInputField");
			  click(ForgotPasswordLocators.resetPasswordButton, "resetPasswordButton");
			  Thread.sleep(2000);
			 if(description.contains("valid email ID")){
			  waitForElementPresent(LoginPageLocators.signInButton, "signInButton");
			  ForgotPasswordHelper.ResetPasswordViaGmail(gmailUrl, email, password, resetPwd);
			  Iosdriver.closeApp();
			  Iosdriver.launchApp();
			  handelSplashScreen();
			  navigateToMyAccount();
			  validateUserLogin();
			  login(email, resetPwd);
			  //if(isElementDisplayed(loc))
			  Reporter.SuccessReport(description, "Successful ");
			 }else{
				 boolean res = false;
				 res = waitForElementPresent(ForgotPasswordLocators.passwordErrorPopUp, "passwordErrorPopUp");
				 waitForElementPresent(ForgotPasswordLocators.passwordErrorPopUp, "passwordErrorPopUp");
				 click(ForgotPasswordLocators.okButtonOnErrorPopUp, "okButtonOnErrorPopUp");
				 if(res){
					 Reporter.SuccessReport(description, "Successful ");
				 }else{
					 Reporter.failureReport(description, "Failed ");
				 }
			 }
		  }/*else{
			  waitForElementPresent(ForgotPasswordLocators.signInBackButton, "signInBackButton");
			  click(ForgotPasswordLocators.signInBackButton, "signInBackButton");
			  if(waitForElementPresent(LoginPageLocators.signInButton, "signInButton")){
					 Reporter.SuccessReport("Validate cancel ResetPassword", "Successful ");
				 }else{
					 Reporter.failureReport("Validate cancel ResetPassword", "Failed ");
				 }
		  }*/
		  /*webDriver = new FirefoxDriver();
		  //webDriver.manage().deleteAllCookies();
		  webDriver.get("https://gmail.com");
		  try{
			  webDriver.findElement(ForgotPasswordLocators.signInGmailLink);
		  }catch(Exception e){
			  //e.printStackTrace();
		  }
		  webDriver.findElement(ForgotPasswordLocators.emailGmailFiled).sendKeys(email);
		  webDriver.findElement(ForgotPasswordLocators.nextGmailButton).click();
		  webDriver.findElement(ForgotPasswordLocators.passwordGmailField).sendKeys(password);*/
		//webDriver.findElement(ForgotPasswordLocators.)
		  /*waitForElementPresent(BookPageLocators.contiuneButton,"contiuneButton");
		  click(BookPageLocators.contiuneButton, 
				  "contiuneButton");
		  if(waitForElementPresent(BookPageLocators.guestDetailsFrame, "guestDetailsFrame")){
			  Reporter.SuccessReport("Validate login from Booking page", "Successful");			  
		  }else{
			  Reporter.failureReport("Validate login from Booking page", "Failed");
		  }*/
		//  }
	  }catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("LogIn", "Failed");
		}
  }
  @DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{xlsReset.getCellValue("validEmail", "Value"),xlsReset.getCellValue("gmailPass", "Value"),
	    			xlsReset.getCellValue("resetPass", "Value"),false,"Validate cancel Forgot Password"},
	    		{xlsReset.getCellValue("InvalidEmail", "Value"),xlsReset.getCellValue("gmailPass", "Value"),
	    			xlsReset.getCellValue("resetPass", "Value"),true,"Validate error message for "
	    					+ "Forgot Password on InValid email ID"},
	    		{"",xlsReset.getCellValue("gmailPass", "Value"),
	    		    			xlsReset.getCellValue("resetPass", "Value"),true,"Validate error message for "
	    		    					+ "Forgot Password on blank email ID"},
	    			{xlsReset.getCellValue("validEmail", "Value"),xlsReset.getCellValue("gmailPass", "Value"),
		    			xlsReset.getCellValue("resetPass", "Value"),true,"Validate Forgot Password on valid email ID"}
	    			};
	}
}
