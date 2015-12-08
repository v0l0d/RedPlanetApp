package com.mobile.scripts;



import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.workflows.LoginHelper;


public class RP_001_TestLoginFromHome extends LoginHelper{
		/*
		 * Verify Login functionality
		 */
	ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("TestData"),
			"RP_001");
		@Test(dataProvider = "testData")
		public  void validateTestLogin(String email, String password, String description, 
				boolean res) throws Throwable {
			logger.info("username "+email);
			logger.info("password "+password);
			try{
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			handelSplashScreen();
			navigateToMyAccount();
			validateUserLogin();
			click(AccountPageLocators.logInButton, "logInButton");							
			if(res){
				boolean result = login(email, password);
				if(result==res){
					Reporter.SuccessReport("validate Login with "+description+":", "Successfull");
				}else{
					Reporter.failureReport("validate Login with "+description+":", "Failed");
				}
			}else{
				boolean result = loginforErrorPopup(email, password);
				if(result == res){
					Reporter.SuccessReport("validate Login with "+description+":", "Successfull");
				}else{
					Reporter.failureReport("validate Login with "+description+":", "Failed");
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
		    		{xlsLogin.getCellValue("InvalidCredentials", "email"),xlsLogin.getCellValue("InvalidCredentials", "password"),"Invalid credentials",false},		    		
		    		{xlsLogin.getCellValue("InvalidPassword", "email"),xlsLogin.getCellValue("InvalidPassword", "password")," Invalid password and valid email",false},
		    		{xlsLogin.getCellValue("invalidEmail", "email"),xlsLogin.getCellValue("InvalidEmail", "password")," invalid Email Error message should be displayed",false},
		    		{"",""," Email and password field left blank",false},
		    		{xlsLogin.getCellValue("ValidCredentials", "email"),xlsLogin.getCellValue("ValidCredentials", "password"),"Valid email and password",true}};
		}
}


