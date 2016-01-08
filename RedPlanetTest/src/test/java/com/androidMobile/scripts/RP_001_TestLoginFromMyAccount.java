package com.androidMobile.scripts;


import com.androidMobile.scripts.testObjects.AccountPageLocators;
import com.androidMobile.scripts.testObjects.HomePageLocators;
import com.androidMobile.workflows.LoginHelper;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RP_001_TestLoginFromMyAccount extends LoginHelper{
		/*
		 * Verify Login functionality from My Account screen
		 */

	    ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("AndroidTestData"),"RP_ANDR_001");
		@Test(dataProvider = "testData")
		public void testTestLoginFromMyAccount (String email, String password, String description,
				boolean res) throws Throwable {
			try{
            TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
            logOutAndGotToMainScreen();
			click(AccountPageLocators.logInButton, "logInButton");
			handleRateAppPopUp();
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
		public Object[][] createData() {
		    return new Object[][] {
		    		{xlsLogin.getCellValue("InvalidCredentials", "email"),xlsLogin.getCellValue("InvalidCredentials", "password"),"Invalid credentials",false},
		    		{xlsLogin.getCellValue("InvalidPassword", "email"),xlsLogin.getCellValue("InvalidPassword", "password"),"Invalid password and valid email",false},
		    		{xlsLogin.getCellValue("invalidEmail", "email"),xlsLogin.getCellValue("InvalidEmail", "password"),"Invalid email and valid password",false},
		    		{"","","Blank Email and blank password",false},
		    		{xlsLogin.getCellValue("ValidCredentials", "email"),xlsLogin.getCellValue("ValidCredentials", "password"),"Valid email and valid password",true}};
		}
}