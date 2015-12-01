package com.androidMobile.scripts;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.androidMobile.scripts.testObjects.AboutUsLocators;
import com.androidMobile.scripts.testObjects.HomePageLocators;
import com.androidMobile.workflows.LoginHelper;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

public class RP_012_TestSocialLinksInAboutUs extends LoginHelper{

  @Test
  public void testSocialNetworkLinksInAboutUs() throws Throwable{
	 try{		  
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, 
				  "Validate visible social integrations in AboutUs page");
		  if(isElementDisplayed(HomePageLocators.closeWatchPopUp)){
				 click(HomePageLocators.closeWatchPopUp,"closeWatchPopUp");
			 }
		  //verify user already loggedIn, if yes signout
		  navigateToAboutUs();		  
		  Thread.sleep(2000);		  
			  if(isElementDisplayed(AboutUsLocators.facebookImage)){
				  click(AboutUsLocators.facebookImage,"facebookImage");
				  Thread.sleep(6000);	
				  //waitForElementPresent(By.xpath("com.redplanethotels.staging:id/social_iv_facebook"),"browser");
				  //click(By.xpath("com.redplanethotels.staging:id/social_iv_facebook"),"browser");
				  /* Code for context to find the View used in application */
				 /* Set<String> contextNames = AndroidDriver2.getContextHandles();
				  for (String contextName : contextNames) {
				  System.out.println("+++++++current Context ++++++++"+contextName);
				  if (contextName.contains("WEBVIEW")){
					  (AndroidDriver2).context(contextName);
					System.out.println(AndroidDriver2.getContext()+"++++++++++++++++++++++"+AndroidDriver2.getCurrentUrl());
				  }
				  }*/
				  Reporter.SuccessReport("Validate Facebook link in AboutUs page", "Successful");
				  AndroidDriver2.closeApp();
				  AndroidDriver2.launchApp();
				  Thread.sleep(6000);
				  navigateToAboutUs();
			  }else{
				  Reporter.failureReport("Validate Facebook link in AboutUs page", "Failed");
			  }
			  
			  if(isElementDisplayed(AboutUsLocators.twitterImage)){
				  click(AboutUsLocators.twitterImage,"twitterImage");
				  Thread.sleep(6000);	
				  Reporter.SuccessReport("Validate twitter link in AboutUs page", "Successful");
				  AndroidDriver2.closeApp();
				  AndroidDriver2.launchApp();
				  Thread.sleep(6000);
				  navigateToAboutUs();
			  }else{
				  Reporter.failureReport("Validate twitter link in AboutUs page", "Failed");
			  }
			  if(isElementDisplayed(AboutUsLocators.googleImage)){
				  click(AboutUsLocators.googleImage,"googleImage");
				  Thread.sleep(6000);	
				  Reporter.SuccessReport("Validate GooglePlus link in AboutUs page", "Successful");
				  AndroidDriver2.closeApp();
				  AndroidDriver2.launchApp();
				  Thread.sleep(6000);
				  navigateToAboutUs();
			  }else{
				  Reporter.failureReport("Validate GooglePlus link in AboutUs page", "Failed");
			  }
			  if(isElementDisplayed(AboutUsLocators.instagramImage)){
				  click(AboutUsLocators.instagramImage,"instagramImage");
				  Thread.sleep(6000);
				  Reporter.SuccessReport("Validate Instagram link in AboutUs page", "Successful");
				  AndroidDriver2.closeApp();
				  AndroidDriver2.launchApp();
				  Thread.sleep(6000);
				  navigateToAboutUs();
			  }else{
				  Reporter.failureReport("Validate Instagram link in AboutUs page", "Failed");
			  }
			  if(isElementDisplayed(AboutUsLocators.pintrestImage)){
				  click(AboutUsLocators.pintrestImage,"pintrestImage");
				  Thread.sleep(6000);
				  Reporter.SuccessReport("Validate Pintrest link in AboutUs page", "Successful");
				  AndroidDriver2.closeApp();
				  AndroidDriver2.launchApp();
				  Thread.sleep(6000);
				  navigateToAboutUs();
			  }else{
				  Reporter.failureReport("Validate Pintrest link in AboutUs page", "Failed");
			  }
			  if(isElementDisplayed(AboutUsLocators.flipboardImage)){
				  click(AboutUsLocators.flipboardImage,"flipboardImage");
				  Thread.sleep(6000);
				  Reporter.SuccessReport("Validate Flipboard link in AboutUs page", "Successful");
				  AndroidDriver2.closeApp();
				  AndroidDriver2.launchApp();
				  Thread.sleep(6000);
				  navigateToAboutUs();
			  }else{
				  Reporter.failureReport("Validate Flipboard link in AboutUs page", "Failed");
			  }
			  
			  if(isElementDisplayed(AboutUsLocators.vineImage)){
				  click(AboutUsLocators.vineImage,"vineImage");
				  Thread.sleep(6000);
				  Reporter.SuccessReport("Validate Vine link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Vine link in AboutUs page", "Failed");
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
