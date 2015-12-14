package com.mobile.scripts;

import java.util.Set;

import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AboutUsLocators;
import com.mobile.workflows.LoginHelper;

public class RP_012_ValidateSocialNetworkLinksInAboutUs extends LoginHelper{
	@Test
  public void ValidateSocialNetworkLinksInAboutUs() throws Throwable{
	  
	 try{		  
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, 
					"Validate visible social integrations in AboutUs page");
		  //verify user already loggedIn, if yes signout
		  handelSplashScreen();
		  //handleSplashDialog();
		  navigateToMyAccount();
		  validateUserLogin();
		  navigateToAboutUs();		  
			  if(waitForElementPresent(AboutUsLocators.facebookImage,"facebookImage")){
				  click(AboutUsLocators.facebookImage,"facebookImage");
				  Set<String> currContexts = Iosdriver.getContextHandles();
				  for(String contex:currContexts){
					  System.out.println("++++++++"+contex);
					  if(contex.contains("WEBVIEW")){
						  Iosdriver.context(contex);
						  System.out.println(Iosdriver.getContext()+" ++++ "+Iosdriver.getTitle());
						  break;
					  }
				  }
				  Thread.sleep(10000);
				  Iosdriver.closeApp();
				  Thread.sleep(10000);
				  Iosdriver.launchApp();
				  handelSplashScreen();
				  navigateToAboutUs();
				  Reporter.SuccessReport("Validate Facebook link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Facebook link in AboutUs page", "Failed");
			  }
			  
			  if(waitForElementPresent(AboutUsLocators.twitterImage,"twitterImage")){
				  click(AboutUsLocators.twitterImage,"twitterImage");
				  Thread.sleep(10000);
				  Iosdriver.closeApp();
				  Thread.sleep(10000);
				  Iosdriver.launchApp();
				  handelSplashScreen();
				  navigateToAboutUs();
				  Reporter.SuccessReport("Validate twitter link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate twitter link in AboutUs page", "Failed");
			  }
			  if(waitForElementPresent(AboutUsLocators.googleImage,"googleImage")){
				  click(AboutUsLocators.googleImage,"googleImage");
				  Thread.sleep(10000);
				  Iosdriver.closeApp();
				  Thread.sleep(10000);
				  Iosdriver.launchApp();
				  handelSplashScreen();
				  navigateToAboutUs();
				  Reporter.SuccessReport("Validate GooglePlus link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate GooglePlus link in AboutUs page", "Failed");
			  }
			  if(waitForElementPresent(AboutUsLocators.instagramImage,"instagramImage")){
				  click(AboutUsLocators.instagramImage,"instagramImage");
				  Thread.sleep(10000);
				  Iosdriver.closeApp();
				  Thread.sleep(10000);
				  Iosdriver.launchApp();
				  handelSplashScreen();
				  navigateToAboutUs();
				  Reporter.SuccessReport("Validate Instagram link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Instagram link in AboutUs page", "Failed");
			  }
			  if(waitForElementPresent(AboutUsLocators.pintrestImage,"pintrestImage")){
				  click(AboutUsLocators.pintrestImage,"pintrestImage");
				  Thread.sleep(10000);
				  Iosdriver.closeApp();
				  Thread.sleep(10000);
				  Iosdriver.launchApp();
				  handelSplashScreen();
				  navigateToAboutUs();
				  Reporter.SuccessReport("Validate Pintrest link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Pintrest link in AboutUs page", "Failed");
			  }
			  if(waitForElementPresent(AboutUsLocators.flipboardImage,"flipboardImage")){
				  click(AboutUsLocators.flipboardImage,"flipboardImage");
				  Thread.sleep(10000);
				  Iosdriver.closeApp();
				  Thread.sleep(10000);
				  Iosdriver.launchApp();
				  handelSplashScreen();
				  navigateToAboutUs();
				  Reporter.SuccessReport("Validate Flipboard link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Flipboard link in AboutUs page", "Failed");
			  }
			  if(waitForElementPresent(AboutUsLocators.vineImage,"vineImage")){
				  click(AboutUsLocators.vineImage,"vineImage");
				  Thread.sleep(10000);
				  Iosdriver.closeApp();
				  Thread.sleep(10000);
				  Iosdriver.launchApp();
				  handelSplashScreen();
				  navigateToAboutUs();
				  Reporter.SuccessReport("Validate Vine link in AboutUs page", "Successful");
			  }else{
				  Reporter.failureReport("Validate Vine link in AboutUs page", "Failed");
			  }
	 }catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("Validate visible social integrations in AboutUs page", "Failed with exception");
	}	 
  }
}
