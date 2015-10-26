package com.redplanet.workflows;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.ctaf.support.ExcelReader;
import com.ctaf.utilities.Reporter;
import com.redplanet.testObjects.AffiliateSignUpLocators;

public class AffiliateSignUpHelper extends AffiliateSignUpLocators{
	
	static ExcelReader xlsrdr = new ExcelReader(configProps.getProperty("TestData"),configProps.getProperty("sheetName2"));
	
	public static boolean login(int iTestRowNum) throws Throwable{
		
		boolean res = false;
		try{
			switch (xlsrdr.getCellValue("signup", "value")){
			
			case "organization":
				click(OrganizationRadioButton, "Organization");
				type(organizationInput, xlsrdr.getCellValue("OrgName", "value"), "organization name");			
			case "individual":
				click(IndvdlRadioButton, "Individual");			
			}
			
			type(fnameInput, xlsrdr.getCellData(configProps.getProperty("sheetName2"), "FName", iTestRowNum),"FirstName");
			type(lnameInput,xlsrdr.getCellData(configProps.getProperty("sheetName2"),"LName", iTestRowNum),"LastName");
			type(emailInput,xlsrdr.getCellData(configProps.getProperty("sheetName2"),"Email", iTestRowNum),"Email");
			type(phoneInput,xlsrdr.getCellData(configProps.getProperty("sheetName2"),"Phone", iTestRowNum),"Phone");
			click(signUpButton, "SignUp");			
			
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			
			if(isElementPresent(RedPlanetLogo, "RedPlanet Logo")){
				res=true;
				Reporter.SuccessReport("Verify user signup in afiliate program", "User created successfully");
			}else
				res = false;
				Reporter.SuccessReport("Verify user signup in afiliate program", "Fail to signup User");
		}catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		
		return res;
		
	}
	
}
