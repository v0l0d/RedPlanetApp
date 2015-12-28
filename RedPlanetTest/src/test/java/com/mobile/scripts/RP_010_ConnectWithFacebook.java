package com.mobile.scripts;


import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.FacebookLoginLocators;
import com.mobile.scripts.testObjects.LoginPageLocators;
import com.mobile.workflows.LoginHelper;

import io.appium.java_client.ios.IOSDriver;


public class RP_010_ConnectWithFacebook extends LoginHelper{
	
		/*
		 * Verify Login functionality
		 */
	ExcelReader xlsFBLogin = new ExcelReader(configProps.getProperty("TestData"),
			"RP_010");
		@Test(dataProvider = "testData", groups = { "Mobile" })
		public  void validateConnectWithFacebook(String FBemail, String FBpassword, String description
				) throws Throwable {
			logger.info("username "+FBemail);
			logger.info("password "+FBpassword);
			boolean result = false;
			try{
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			
			handelSplashScreen();
			//handleSplashDialog();
			navigateToMyAccount();
			validateUserLogin();
			navigateToMyAccount();
			click(LoginPageLocators.connectWithFacebookButton, "connectWithFacebookButton");							
			Thread.sleep(10000);
			Set<String> contexts = ((IOSDriver) driver).getContextHandles();
			for(String currContext : contexts){
				System.out.println("current context is :"+currContext);
				if(currContext.contains("WEBVIEW")){
					Reporter.SuccessReport("validate switch to web context", 
							" Successfull switched to web context"+currContext);
					((IOSDriver) driver).context(currContext);
					String title = Iosdriver.getTitle();
					System.out.println(Iosdriver.getContext()+" ++++ "+title);
					if(Iosdriver.getPageSource().contains("Confirm")){
						Reporter.SuccessReport("validate Page Title", " Successfull found page Title "+title);
						waitForElementPresent(FacebookLoginLocators.oKFacebookButton, "oKFacebookButton");
						click(FacebookLoginLocators.oKFacebookButton, "oKFacebookButton");
						break;
					}
					waitForElementPresent(FacebookLoginLocators.emailForFacebookLogin, "emailForFacebookLogin");
					type(FacebookLoginLocators.emailForFacebookLogin, FBemail, "emailForFacebookLogin");
					type(FacebookLoginLocators.passwordForFacebookLogin, FBpassword, "passwordForFacebookLogin");
					click(FacebookLoginLocators.loginButtonForFacebookLogin, "loginButtonForFacebookLogin");
					waitForElementPresent(FacebookLoginLocators.oKFacebookButton, "oKFacebookButton");
					click(FacebookLoginLocators.oKFacebookButton, "oKFacebookButton");
					break;
				}
			}
			//Set<String> contexts2 = ((IOSDriver) driver).getContextHandles();
			for(String currCont : contexts){
				System.out.println("current context is :"+currCont);
				if(currCont.contains("NATIVE")){
					((IOSDriver) driver).context(currCont);
					Reporter.SuccessReport("validate switch to native context", 
							" Successfull switched to native context"+currCont);
					boolean loginStat = isElementDisplayed(AccountPageLocators.logInButton);
					if(!(loginStat)){					    
						System.out.println("user is logged in successfully");
						Reporter.SuccessReport("validate user login with facebook", 
								" Successfull varified user login with facebook "+!loginStat);
						result = true;
						scrollToText("SIGN OUT");
						waitForElementPresent(AccountPageLocators.signOutButton, "signOutButton");
						 click(AccountPageLocators.signOutButton, "signOutButton");
						 Thread.sleep(2000);	
						 break;
					  }
				}
			}
			if(result){
					Reporter.SuccessReport("validate "+description, " Successfull");
				}else{
					Reporter.failureReport("validate "+description, " Failed");
				}
			}catch (Exception e) {
				e.printStackTrace();
				Reporter.failureReport("Connect with Facebook login", "Failed");
			}
		}
		@DataProvider(name="testData")
		public Object[][] createdata1() {
		    return (Object[][]) new Object[][] { 
		    		{xlsFBLogin.getCellValue("ValidFBCredentials", "Value"),
		    			xlsFBLogin.getCellValue("ValidFBCredentials", "password"),"Connect with Facebook login"}};
		}
}


