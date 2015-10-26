/*package com.redplanet.scripts.signin;  

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import com.quickflix.workFlows.SignInHelper;

 * Test method Verify Error Message whether user Enters AU Email Instead oF NZ Email
 * 
 
public class ValidateSignInRegionAU extends SignInHelper {
	ExcelReader xlsrdr = new ExcelReader(configProps.getProperty("TestData"),
			configProps.getProperty("sheetName1"));
	Logger logger = Logger.getLogger(ValidateSignInRegionAU.class.getName());

	
	 * Verify Error Message whether user Enters AU Email Instead oF NZ Email
	 
	@Test(groups={"chrome" , "firefox", "ie",
			"Mobile"} , dataProvider = "testData")
	public void validateSignInRegionAU(String email, String password , boolean expRes, String desc) throws Throwable {
		try{
		boolean result = false;
		TestEngine.testDescription.put(HtmlReportSupport.tc_name,  " CTP - 5 : When a user tries to login for AU domain with"+desc);
		
		logger.info("Email = "+email);
		logger.info("Password = "+password);
		result = validateEmailSignInRegion(email, password, expRes);
		if(result){
			if(validateSignIn()){
				signOut();
				}
			Reporter.SuccessReport("validate signin for AU domain with email: "+email+" password: "+password, "Successfully");
		}else{
				Reporter.failureReport("validate  signin for AU domain with email: "+email+" password "+password, "Failed");
				Assert.assertTrue(result, "Failed to signin for AU domain with with email "+email+" password "+password);
			}
		} catch (Exception e) {

			Reporter.failureReport("validate  signin for AU domain with email: "+email+" password "+password, "Failed");
			e.printStackTrace();
			Assert.assertTrue(false, "Failed to signin for AU domain with with email "+email+" password "+password);
		}finally{
			//launching base url to proceed to logout
			launchUrl(url);
			if (validateSignIn()) {
				signOut();
			}
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{"",xlsrdr.getCellValue("ValidateRegionAU", "password"), false," blank Email Error message should be displayed"},
	    		{xlsrdr.getCellValue("ValidateRegionAU", "email"),"", false," blank Password Error message should be displayed"},
	    		{xlsrdr.getCellValue("invalidEmail", "email"),xlsrdr.getCellValue("ValidateRegionAU", "password"), false," invalid Email Error message should be displayed"},
	    		{xlsrdr.getCellValue("ValidateRegionAU", "email"),xlsrdr.getCellValue("ValidateRegionAU", "password")+1, false," invalid Password Error message should be displayed"},
	    		{xlsrdr.getCellValue("ValidateRegionNZ", "email"),xlsrdr.getCellValue("ValidateRegionNZ", "password"), false," NZ credentials,  Error message should be displayed"},	    		
	    		{xlsrdr.getCellValue("ValidateRegionAU", "email"), xlsrdr.getCellValue("ValidateRegionAU", "password"),true," AU Email and Password, should be logged in"}};
	}
}
*/