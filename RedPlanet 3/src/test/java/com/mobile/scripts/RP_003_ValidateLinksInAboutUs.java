package com.mobile.scripts;

import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AboutUsLocators;
import com.mobile.scripts.testObjects.HomePageLocators;
import com.mobile.workflows.LoginHelper;

public class RP_003_ValidateLinksInAboutUs extends LoginHelper{
	ExcelReader xlsSearch = new ExcelReader(configProps.getProperty("TestData"),
			"RP_ANDR_003");
		@Test(dataProvider = "testData")
  public void ValidateLinksInAboutUs(String description) throws Throwable{
	  
	 try{		  
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, 
					description);
		  
		  //verify user already loggedIn, if yes signout
		  validateUserLogin();
		  navigateToAboutUs();		  
		  Thread.sleep(2000);		  //Privacy Policy
		  scrollToElement("//UIAButton[@label='Terms of Use']");
		  if(description.equals("Validate visible social integrations in AboutUs page")){
			  if(isElementPresent(AboutUsLocators.facebookImage)){
				  Reporter.SuccessReport("Validate Facebook link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Facebook link in AboutUs page", "Failed");
			  }
			  
			  if(isElementPresent(AboutUsLocators.twitterImage)){
				  Reporter.SuccessReport("Validate twitter link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate twitter link in AboutUs page", "Failed");
			  }
			  if(isElementPresent(AboutUsLocators.googleImage)){
				  Reporter.SuccessReport("Validate GooglePlus link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate GooglePlus link in AboutUs page", "Failed");
			  }
			  if(isElementPresent(AboutUsLocators.instagramImage)){
				  Reporter.SuccessReport("Validate Instagram link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Instagram link in AboutUs page", "Failed");
			  }
			  if(isElementPresent(AboutUsLocators.pintrestImage)){
				  Reporter.SuccessReport("Validate Pintrest link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Pintrest link in AboutUs page", "Failed");
			  }
			  if(isElementPresent(AboutUsLocators.flipboardImage)){
				  Reporter.SuccessReport("Validate Flipboard link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Flipboard link in AboutUs page", "Failed");
			  }
			  if(isElementPresent(AboutUsLocators.facebookImage)){
				  Reporter.SuccessReport("Validate Facebook link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Facebook link in AboutUs page", "Failed");
			  }
			  if(isElementPresent(AboutUsLocators.vineImage)){
				  Reporter.SuccessReport("Validate Vine link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Vine link in AboutUs page", "Failed");
			  }
		  }
		  if(description.equals("Validate footer links in AboutUs page")){
			
			  if(isElementPresent(AboutUsLocators.privacypolicyLink)){
				  Reporter.SuccessReport("Validate Privacy policy link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Privacy policy link in AboutUs page", "Failed");
			  }
			  if(isElementPresent(AboutUsLocators.termsNcondiLink)){
				  Reporter.SuccessReport("Validate Terms&Conditions link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Terms&Conditions link in AboutUs page", "Failed");
			  }
			  if(isElementPresent(AboutUsLocators.termsuseLink)){
				  Reporter.SuccessReport("Validate TermsofUse link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate TermsofUse link in AboutUs page", "Failed");
			  }
		  }
	 }catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("Validate visible links in AboutUs page", "Failed with exception");
	}	 
  }

	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{"Validate visible social integrations in AboutUs page"},
	    		{"Validate footer links in AboutUs page"}};
	}
}
