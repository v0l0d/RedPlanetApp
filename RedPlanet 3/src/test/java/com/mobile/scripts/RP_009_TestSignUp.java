package com.mobile.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.support.ReportStampSupport;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.LoginPageLocators;
import com.mobile.scripts.testObjects.SignUpEmailLocators;
import com.mobile.workflows.LoginHelper;
import com.mobile.workflows.SignUpHelper;

public class RP_009_TestSignUp extends LoginHelper{
	ExcelReader xlsSearch = new ExcelReader(configProps.getProperty("TestData"),
			"RP_009");
  @Test(dataProvider = "testData")
  public void testSignUpUser(String firstName, String lastName, String emailId,
		  String password,boolean status, String description) throws Throwable{
	 
	try{
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, 
				description);
		  handelSplashScreen();
		  //handleSplashDialog();
	      navigateToMyAccount();
	      validateUserLogin();
	      click(AccountPageLocators.signUpButton, "signUpButton");
		  Thread.sleep(2000);
		  //waitForElementPresent(SignUpEmailLocators.firstnameInputField, "firstnameInputField");
		  SignUpHelper.SignUp(firstName, lastName, emailId, password);
		  //verify user already loggedIn, if yes sign out
		  if(status){		 
			  scrollToText("SIGN OUT");
			  waitForElementPresent(AccountPageLocators.signOutButton, "signOutButton");
			  String actual = getText(AccountPageLocators.userName,"userNameInputField");
			  click(AccountPageLocators.signOutButton, "signOutButton");
			  if((actual.contains(firstName))&(actual.contains(lastName))){
				  Reporter.SuccessReport("Sign Up as new user", "Successful Signed Up with new emailID "+emailId);
			  }else{
				  Reporter.failureReport("Sign Up as new user", "Failed to Sign Up with new emailID "+emailId);
			  }
		  }else{
			  if(isElementDisplayed(AccountPageLocators.signUpDataValidationAlert)){
				  click(AccountPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");  
			  }
			  if(waitForElementPresent(AccountPageLocators.errorMsgForSignUpExistingEmailID, 
					  "errorMsgForSignUpExistingEmailID")){
				  Reporter.SuccessReport(description, "Successful");
			  }else{
				  Reporter.failureReport("Sign Up with existing user Id",
						  "Failed to find error for sign up with existing user id "+emailId);
			  }
		  }
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
			  {xlsSearch.getCellValue("fName", "Value"),xlsSearch.getCellValue("lName", "Value"),
				  newEmail,xlsSearch.getCellValue("ValidCredentials","password"),true,"SignUp as new user"},		    		
			  {xlsSearch.getCellValue("fName", "Value"),xlsSearch.getCellValue("lName", "Value"),
				   xlsSearch.getCellValue("ValidCredentials", "Value"),xlsSearch.getCellValue("ValidCredentials", "password"),
					false,"SignUp with existing user id"}};
	}
}
