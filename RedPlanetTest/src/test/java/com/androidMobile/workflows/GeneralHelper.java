package com.androidMobile.workflows;

import io.appium.java_client.ios.IOSDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.androidMobile.scripts.testObjects.FrontDeskWebLocators;
import com.androidMobile.scripts.testObjects.PickRoomPageLocators;
import com.ctaf.utilities.Reporter;

public class GeneralHelper extends GmailHelper{
	
	public static boolean locateExpandButton() throws Throwable{
		boolean res = false;
		try {
			waitForElementPresent(PickRoomPageLocators.expandButtonOnPickRoom,"expandButtonOnPickRoom");
			click(PickRoomPageLocators.expandButtonOnPickRoom,"expandButtonOnPickRoom");
			res = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public static boolean locateNearByTab() throws Throwable{
		boolean res = false;
		try {
			waitForElementPresent(PickRoomPageLocators.nearByTab,"nearByTab");
			click(PickRoomPageLocators.nearByTab,"nearByTab");
			Thread.sleep(6000);
			if(!isElementDisplayed(PickRoomPageLocators.checkIn)){
				res = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
public static String FrontDeskChat(String url,String userId,String password,String message) throws Throwable{
		boolean result = false;
		String newMessage = message+"_From Admin";
		String returnMessage = null; 
		WebDriver  browser = new FirefoxDriver();	
		try {
			 	browser.manage().window().maximize();			 	
			 	browser.get(url);
			 	browser.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			 	GmailHelper.waitForElementPresent(browser,FrontDeskWebLocators.userId, "userId");
			 	type(browser,FrontDeskWebLocators.userId, userId, "userId");
			 	waitForElementPresent(browser,FrontDeskWebLocators.password, "password");
			 	type(browser,FrontDeskWebLocators.password, password, "password");
			 	click(browser,FrontDeskWebLocators.signInButton, "signInButton");
			 	Thread.sleep(5000);
			 	waitForElementPresent(browser,FrontDeskWebLocators.chatIcon, "chatIcon");
			 	click(browser,FrontDeskWebLocators.chatIcon, "chatIcon");
			 	waitForElementPresent(browser,FrontDeskWebLocators.allIssuesLink, "allIssuesLink");
			 	click(browser,FrontDeskWebLocators.allIssuesLink, "allIssuesLink");
			 	waitForElementPresent(browser,FrontDeskWebLocators.searchInputBox, "searchInputBox");
			 	type(browser,FrontDeskWebLocators.searchInputBox,"Test message","searchInputBox");
			 	Thread.sleep(5000);
			    click(browser, By.xpath(FrontDeskWebLocators.Testmessage.replace("#", "Test message")), "Testmessage");
			    Thread.sleep(5000);
			    waitForElementPresent(browser,FrontDeskWebLocators.textAreaForAdminChat, "textAreaForAdminChat");
			    type(browser,FrontDeskWebLocators.textAreaForAdminChat,newMessage, "textAreaForAdminChat");
			    waitForElementPresent(browser,FrontDeskWebLocators.replyButton, "replyButton");
			    click(browser,FrontDeskWebLocators.replyButton, "replyButton");
			    Thread.sleep(5000);
			    browser.navigate().refresh();
			    Thread.sleep(5000);
			   if(browser.getPageSource().contains(newMessage)){
			    	Reporter.SuccessReport("Validate Replay chat", "Successful");
			    	returnMessage = newMessage;
			    }else{
			    	Reporter.failureReport("Validate Replay chat", "Failed");
			    }
			    
			 	
		}catch(Exception e){
			e.printStackTrace();						
		}finally{
			browser.close();			    
		 	browser.quit();
		}
		return returnMessage;
	}

	public static void deepLinkHelper(String link) throws Throwable{
		//HomePageHelper.navigateToMyAccount();
		//click(LoginPageLocators.connectWithFacebookButton, "connectWithFacebookButton");							
		Thread.sleep(10000);
		Set<String> contexts = AndroidDriver2.getContextHandles();
		for(String currContext : contexts){
			System.out.println("current context is :"+currContext);
			if(currContext.contains("WEBVIEW")){
				Reporter.SuccessReport("validate switch to web context", 
						" Successfull switched to web context"+currContext);
				AndroidDriver2.context(currContext);
				AndroidDriver2.navigate().to(link);
				Thread.sleep(10000);
				break;
			}
		}
	}
	
}
