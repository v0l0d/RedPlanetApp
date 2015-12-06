package com.androidMobile.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.androidMobile.scripts.testObjects.AccountPageLocators;
import com.androidMobile.scripts.testObjects.HomePageLocators;
import com.androidMobile.scripts.testObjects.LoginPageLocators;
import com.androidMobile.workflows.GmailHelper;
import com.androidMobile.workflows.HomePageHelper;
import com.androidMobile.workflows.LoginHelper;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

public class RP_011_Forgot_Password extends LoginHelper{
	ExcelReader xlsPsw = new ExcelReader(configProps.getProperty("TestData"),
			"RP_ANDR_011");
  @Test(dataProvider = "testData")
  public void ResetPassword(String email, String password,String resetPwd,String description) throws Throwable{
	  System.out.println("In ResetPassword");
	 String gmailUrl = "https://www.gmail.com" ;
	try{
		 TestEngine.testDescription.put(HtmlReportSupport.tc_name, 
				description);
		 if(isElementDisplayed(HomePageLocators.closeWatchPopUp)){
			 click(HomePageLocators.closeWatchPopUp,"closeWatchPopUp");
		 }
		 handleRateAppPopUp();
		 navigateToMyAccount();
		 HomePageHelper.handleRateAppPopUp();
		  //verify user already loggedIn, if yes signout		  	 
		  Thread.sleep(2000);
		  if(isElementDisplayed(LoginPageLocators.editButton)){
			  Thread.sleep(2000);
			  waitForElementPresent(AccountPageLocators.signOutButton, 
					  "sinOutButton");
			  click(AccountPageLocators.signOutButton, "sinOutButton");
			  Thread.sleep(2000);
		  }	
		  waitForElementPresent(AccountPageLocators.logInButton, "logInButton");
		  click(AccountPageLocators.logInButton, "logInButton");
		  HomePageHelper.handleRateAppPopUp();
		 if(description == "Validate Forgot password frame and click on Cancel"){			 	
			  waitForElementPresent(LoginPageLocators.forgotPasswordLink, 
					  "forgotPasswordLink");		  
			  clickAndWaitForElementPresent(LoginPageLocators.forgotPasswordLink,
					  LoginPageLocators.forgotPasswordframe, "forgotPasswordframe");
			 click(LoginPageLocators.cancelButton, "cancelButton");
			 if(isElementPresent(LoginPageLocators.signInButton, "signInButton")){
				 Reporter.SuccessReport(description, "Successfull");
			 }else
				 Reporter.failureReport(description, "Failed");
		 }
		 if((description.contains("InValid email"))|(description.contains("blank email"))){
			 waitForElementPresent(LoginPageLocators.forgotPasswordLink, 
					  "forgotPasswordLink");
			 clickAndWaitForElementPresent(LoginPageLocators.forgotPasswordLink,
					  LoginPageLocators.forgotPasswordframe, "forgotPasswordframe");
			 HomePageHelper.handleRateAppPopUp();
			 Thread.sleep(3000);
			 waitForElementPresent(LoginPageLocators.emailFieldForForgotPswd, 
					  "emailFieldForForgotPswd");
			 type(LoginPageLocators.emailFieldForForgotPswd, email, "emailFieldForForgotPswd");
			 click(LoginPageLocators.resetButton, "resetButton");
			 Thread.sleep(3000);
			 if(isElementPresent(LoginPageLocators.errorPop, "errorPop")){
				 String errMsg = getText(LoginPageLocators.errorPop, "errorPop");
				 System.out.println(" errMsg "+errMsg);
				 click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");
				 Reporter.SuccessReport(description, "Successfull with error message "+errMsg);
			 }else
				 Reporter.failureReport(description, "Failed");
		 }
		 if(description.contains("Reset password and Validate")){			
			 GmailHelper.ResetPasswordViaGmail(gmailUrl, email, password, resetPwd);
			 waitForElementPresent(LoginPageLocators.signInButton, "signInButton");
			 boolean result = login(email, resetPwd);
			 if(result){
				 Reporter.SuccessReport(description, "Successful");
			 }else
				 Reporter.failureReport(description, "Failed");
		 }
	     
	}catch(Exception e) {
		e.printStackTrace();
		Reporter.failureReport(description, "Failed with exception");		
	}	
  }

  	@DataProvider(name="testData")
	public Object[][] createdata1() {  		
  		return (Object[][]) new Object[][] { 
			 
			  {"","","","Validate Forgot password frame and click on Cancel"},
			 {xlsPsw.getCellValue("InvalidEmail", "Value"),"","","Validate error for Reset password with InValid email"},
			 {"","","","Validate error for Reset password with blank email"},
			 {xlsPsw.getCellValue("email", "Value"),xlsPsw.getCellValue("password", "Value"),
				  xlsPsw.getCellValue("resetPwd", "Value"),"Reset password and Validate"}};
	}
}
