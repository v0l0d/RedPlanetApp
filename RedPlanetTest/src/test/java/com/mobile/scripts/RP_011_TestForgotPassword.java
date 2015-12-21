package com.mobile.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.support.ReportStampSupport;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.ForgotPasswordLocators;
import com.mobile.scripts.testObjects.LoginPageLocators;
import com.mobile.workflows.ForgotPasswordHelper;
import com.mobile.workflows.LoginHelper;

public class RP_011_TestForgotPassword  extends LoginHelper{
	
	ExcelReader xlsReset = new ExcelReader(configProps.getProperty("TestData"),
			"RP_011");
		@Test(dataProvider = "testData", groups = { "Mobile" })
  public void testForgotPassword(
		  String email,String password,String resetPwd, boolean status, String description) throws Throwable {
		String gmailUrl = "https://www.gmail.com" ;
		String newPass = resetPwd+ReportStampSupport.biRandomValue();
		System.out.println("newPassword "+newPass);
		try{
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);			  
		  //verify user already loggedIn, if yes sign out
		  handelSplashScreen();
		  //handleSplashDialog();
		  navigateToMyAccount();
		  validateUserLogin();
		  waitForElementPresent(AccountPageLocators.logInButton,"logInButton");
		  click(AccountPageLocators.logInButton,"logInButton");
		  waitForElementPresent(LoginPageLocators.forgotPasswrod, "forgotPasswrod");
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
			 if(description.contains("valid email ID")){
			  waitForElementPresent(LoginPageLocators.signInButton, "signInButton");
			  ForgotPasswordHelper.ResetPasswordViaGmail(gmailUrl, email, password, newPass);
			  Iosdriver.closeApp();
			  Iosdriver.launchApp();
			  handelSplashScreen();
			  navigateToMyAccount();
			  validateUserLogin();
			  click(AccountPageLocators.logInButton, "logInButton");	
			  login(email, newPass);
			  if(isElementDisplayed(AccountPageLocators.accountScreenTitle)){
				  Reporter.SuccessReport(description, "Successful ");
			  }else{
				  Reporter.failureReport(description, "Failed ");
			  }
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
		  }
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
		    			xlsReset.getCellValue("resetPass", "Value"),true,"Validate Forgot Password on valid email ID"}};
	}
}
