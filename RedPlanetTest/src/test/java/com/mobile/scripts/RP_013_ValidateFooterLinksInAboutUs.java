package com.mobile.scripts;

import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AboutUsLocators;
import com.mobile.workflows.LoginHelper;

public class RP_013_ValidateFooterLinksInAboutUs extends LoginHelper{
		@Test(groups = { "Mobile" })
  public void ValidateFooterLinksInAboutUs() throws Throwable{
	  
	 try{		  
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, 
					"Validate Footer links in AboutUs page");
		  //verify user already loggedIn, if yes signout
		  handelSplashScreen();
		  //handleSplashDialog();
		  navigateToAboutUs();		  
		  scrollToText("Terms of Use");
		  	waitForElementPresent(AboutUsLocators.privacypolicyLink,"privacypolicyLink");
		  	click(AboutUsLocators.privacypolicyLink,"privacypolicyLink");
				  if(waitForElementPresent(AboutUsLocators.privacyPolicyTitle,"privacyPolicyTitle")){
					  Reporter.SuccessReport("Validate Privacy policy link in AboutUs page", "Successful");
				  }else{
					  Reporter.failureReport("Validate Privacy policy link in AboutUs page", "Failed");
				  }
				  handelSplashScreen();
				  //handleSplashDialog();
				  Iosdriver.closeApp();
				  Iosdriver.launchApp();
				  handelSplashScreen();
				  //handleSplashDialog();
				  navigateToAboutUs();		  
				  scrollToText("Terms of Use");
				  waitForElementPresent(AboutUsLocators.termsNcondiLink,"termsNcondiLink");
				  click(AboutUsLocators.termsNcondiLink,"termsNcondiLink");
				  if(waitForElementPresent(AboutUsLocators.termsAndCondTitle,"termsAndCondTitle")){
					  Reporter.SuccessReport("Validate Terms&Conditions link in AboutUs page", "Successful");
				  }else{
					  Reporter.failureReport("Validate Terms&Conditions link in AboutUs page", "Failed");
				  }
				  handelSplashScreen();
				  //handleSplashDialog();
				  Iosdriver.closeApp();
				  Iosdriver.launchApp();
				  handelSplashScreen();
				  //handleSplashDialog();
				  navigateToAboutUs();
				  waitForElementPresent(AboutUsLocators.termsuseLink,"termsuseLink");
				  click(AboutUsLocators.termsuseLink,"termsuseLink");
				  if(waitForElementPresent(AboutUsLocators.termsOfUseTitle,"termsOfUseTitle")){
					  Reporter.SuccessReport("Validate TermsofUse link in AboutUs page", "Successful");
				  }else{
					  Reporter.failureReport("Validate TermsofUse link in AboutUs page", "Failed");
				  }
		 }catch (Exception e) {
				e.printStackTrace();
				Reporter.failureReport("Validate Footer links", "Failed to validate Footer links in AboutUp screen");
		 }
	}
		 
}
