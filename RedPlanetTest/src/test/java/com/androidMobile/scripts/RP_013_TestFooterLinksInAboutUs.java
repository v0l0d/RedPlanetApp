package com.androidMobile.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.androidMobile.scripts.testObjects.AboutUsLocators;
import com.androidMobile.scripts.testObjects.HomePageLocators;
import com.androidMobile.workflows.HomePageHelper;
import com.androidMobile.workflows.LoginHelper;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

public class RP_013_TestFooterLinksInAboutUs extends LoginHelper{

  @Test
  public void testFooterLinksInAboutUs() throws Throwable{
	 try{		  
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, 
				  "Validate footer links in AboutUs page");
		  handleRateAppPopUp();
		  handleSplashDialog();
		  navigateToAboutUs();		  
		  Thread.sleep(2000);	  
		  //scrollToText("Privacy Policy");
		  handleRateAppPopUp();
		  handleSplashDialog();
		  waitForElementPresent(AboutUsLocators.termsNcondLink,"termsNcondLink");
			  click(AboutUsLocators.termsNcondLink,"Terms and Conditions Link");
			  handleRateAppPopUp();
			  handleSplashDialog();
			  if(isElementDisplayed(AboutUsLocators.termsNCondScreen)){
				  Reporter.SuccessReport("Validate Terms&Conditions Screen in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Terms&Conditions Screen in AboutUs page", "Failed");
			  }
			  AndroidDriver2.closeApp();
			  Thread.sleep(3000);
			  
			  AndroidDriver2.launchApp();
			  handleRateAppPopUp();
			  handleSplashDialog();
			  Thread.sleep(5000);
			  navigateToAboutUs();
			  //scrollToText("Privacy Policy");
			  handleRateAppPopUp();
			  handleSplashDialog();
			  waitForElementPresent(AboutUsLocators.privacypolicyLink,"privacypolicyLink");
			  click(AboutUsLocators.privacypolicyLink,"privacypolicyLink"); 

			  handleRateAppPopUp();
			  handleSplashDialog();
			  if(isElementDisplayed(AboutUsLocators.privacyPolocyScreen)){
				  Reporter.SuccessReport("Validate Privacy policy Screen in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Privacy policy Screen in AboutUs page", "Failed");
			  }

			  AndroidDriver2.closeApp();
			  Thread.sleep(3000);
			  AndroidDriver2.launchApp();
			  handleRateAppPopUp();
			  handleSplashDialog();
			  Thread.sleep(5000);
			  navigateToAboutUs();
			  handleRateAppPopUp();
			  handleSplashDialog();
			  //scrollToText("Privacy Policy");
			  waitForElementPresent(AboutUsLocators.termsOfUseLink,"termsOfUseLink");
				click(AboutUsLocators.termsOfUseLink,"termsOfUseLink");
				  handleRateAppPopUp();
				  handleSplashDialog();
			  if(isElementDisplayed(AboutUsLocators.termsOfUseScreen)) { 
				  Reporter.SuccessReport("Validate TermsofUse Screen in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate TermsofUse Screen in AboutUs page", "Failed");
			  }
	 }catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("Validate footer links in AboutUs page", "Failed with exception");
	}	 
  }

}
