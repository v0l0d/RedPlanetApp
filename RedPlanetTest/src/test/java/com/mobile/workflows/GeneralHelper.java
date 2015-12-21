package com.mobile.workflows;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.FrontDeskWebLocators;
import com.mobile.scripts.testObjects.PickRoomPageLocators;

import io.appium.java_client.ios.IOSDriver;

public class GeneralHelper extends ForgotPasswordHelper{
	
	public static boolean locateExpandButton() throws Throwable{
		boolean res = false;
		try {
			waitForElementPresent(PickRoomPageLocators.pickRoomPage,"pickRoomPage");
			 Dimension dim = Iosdriver.findElement(PickRoomPageLocators.checkInButtonOnPickRoom).getSize();
			 Point pt = Iosdriver.findElement(PickRoomPageLocators.checkInButtonOnPickRoom).getLocation();
			 Point newPt = null;
			 int newX = pt.getX()+dim.getWidth();
			 int tempY = pt.getY();
			for(int j = 1;j<100;j++){
				tempY = tempY-1;
				 (Iosdriver).tap(1, newX, tempY, (int) 0.6);
				 newPt = (Iosdriver.findElement(PickRoomPageLocators.checkInButtonOnPickRoom).getLocation());
				 if(newPt.getY()!=pt.getY()){
					 System.out.println(" x "+newX+" y "+tempY);
					 break;
				 }
			}
			res = newPt.getY()!=pt.getY();
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public static boolean locateNearByTab() throws Throwable{
		boolean res = false;
		try {
			Dimension dim2 = Iosdriver.findElement(PickRoomPageLocators.tableWinodow).getSize();
			Point pt2 = Iosdriver.findElement(PickRoomPageLocators.tableWinodow).getLocation();
			int newHt = (pt2.y+dim2.getHeight())/2;
			int newWdt = (dim2.width+pt2.getX());
			System.out.println((pt2.y+dim2.getHeight())/2);
			(Iosdriver).tap(1, (newWdt), (newHt), (int) 0.6);
			if(!isElementDisplayed(PickRoomPageLocators.checkInButtonOnPickRoom)){
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
		
		try {
			WebDriver  browser = new FirefoxDriver();	 	
			browser.manage().window().maximize();			 	
			 	browser.get(url);
			 	browser.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			 	waitForElementPresent(browser,FrontDeskWebLocators.userId, "userId");
			 	type(browser,FrontDeskWebLocators.userId, userId, "userId");
			 	waitForElementPresent(browser,FrontDeskWebLocators.password, "password");
			 	type(browser,FrontDeskWebLocators.password, password, "password");
			 	click(browser,FrontDeskWebLocators.signInButton, "signInButton");
			 	waitForElementPresent(browser,FrontDeskWebLocators.chatIcon, "chatIcon");
			 	click(browser,FrontDeskWebLocators.chatIcon, "chatIcon");
			 	waitForElementPresent(browser,FrontDeskWebLocators.allIssuesLink, "allIssuesLink");
			 	click(browser,FrontDeskWebLocators.allIssuesLink, "allIssuesLink");
			 	waitForElementPresent(browser,FrontDeskWebLocators.searchInputBox, "searchInputBox");
			 	type(browser,FrontDeskWebLocators.searchInputBox,"Test message","searchInputBox");
			    click(browser, By.xpath(FrontDeskWebLocators.Testmessage.replace("#", "Test message")), "Testmessage");
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
			    
			   browser.close();			    
			 	browser.quit();
		}catch(Exception e){
			e.printStackTrace();						
		}finally{
			
		}
		return returnMessage;
	}

	public static void deepLinkHelper(String link , Set<String> contexts) throws Throwable{
		//HomePageHelper.navigateToMyAccount();
		//click(LoginPageLocators.connectWithFacebookButton, "connectWithFacebookButton");							
		try{
		Set<String> contexts2 = ((IOSDriver) driver).getContextHandles();
		
		for(String currContext : contexts2){
			System.out.println("current context is :"+currContext);
			if(currContext.contains("WEBVIEW")){
				((IOSDriver) driver).context(currContext);
				Reporter.SuccessReport("validate switch to web context", 
						" Successfull switched to web context"+currContext);
				Iosdriver.navigate().to(link);
				Reporter.SuccessReport("validate load deep link url", 
						" Successfull loaded url "+link);
				break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
