package com.androidMobile.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.androidMobile.scripts.testObjects.AccountPageLocators;
import com.androidMobile.scripts.testObjects.HomePageLocators;
import com.androidMobile.scripts.testObjects.LoginPageLocators;
import com.androidMobile.scripts.testObjects.SignUpEmailLocators;
import com.androidMobile.workflows.LoginHelper;
import com.androidMobile.workflows.SignUpHelper;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.support.ReportStampSupport;
import com.ctaf.utilities.Reporter;

public class RP_009_TestSignUpUser extends LoginHelper{
	ExcelReader xlsSearch = new ExcelReader(configProps.getProperty("TestData"),
			"RP_ANDR_009");
  @Test(dataProvider = "testData")
  public void testSignUpUser(String fName, String lName, String email,
		  String password,String description) throws Throwable{
	 
	try{
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, 
				description);		  	
		  //verify user already loggedIn, if yes signout
		  if(isElementDisplayed(HomePageLocators.closeWatchPopUp)){
				 click(HomePageLocators.closeWatchPopUp,"closeWatchPopUp");
			 }
	      navigateToMyAccount();
		  Thread.sleep(2000);
		  logOut();
		  click(AccountPageLocators.signUpButton, "signUpButton");
		  Thread.sleep(2000);
		  waitForElementPresent(SignUpEmailLocators.firstnameInputField, "firstnameInputField");
		  if(description.contains("SignUp a user")){
			  boolean result = SignUpHelper.SignUp(fName, lName, email, password);
			  if(result){
				  Reporter.SuccessReport(description, "Successful");
			  }else{
				  Reporter.failureReport(description, "Failed");
			  }
		  }else if(description.contains("exising user")){
				  boolean result = SignUpHelper.SignUp(fName, lName, email, password);
				  if(!result){
					 waitForElementPresent(LoginPageLocators.errorPop, "errorPop");
					 String errorMsg = getText(LoginPageLocators.errorPop, "errorPop");
					 System.out.println("error message on signUp with existing user "+ errorMsg);
					 Reporter.SuccessReport(description, "Successful "+errorMsg);
					 click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");
				  }else{
					  Reporter.failureReport(description, "Failed");
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
  		return (Object[][]) new Object[][] { 
			 
			  {xlsSearch.getCellValue("fName", "Value"),xlsSearch.getCellValue("lName", "Value"),
				  	newEmail,xlsSearch.getCellValue("password", "Value"),"Validate SignUp new user"},
				  	{xlsSearch.getCellValue("fName", "Value"),xlsSearch.getCellValue("lName", "Value"),
				  		xlsSearch.getCellValue("existingUser", "Value"),xlsSearch.getCellValue("password", "Value"),
				  		"Validate error on SignUp with exising user"}};
	}
}
