package com.mobile.scripts;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AboutUsLocators;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.FacebookLoginLocators;
import com.mobile.scripts.testObjects.HomePageLocators;
import com.mobile.scripts.testObjects.InHousePhoneLocators;
import com.mobile.scripts.testObjects.LoginPageLocators;
import com.mobile.scripts.testObjects.PickRoomPageLocators;
import com.mobile.workflows.GeneralHelper;
import com.mobile.workflows.LoginHelper;

import io.appium.java_client.ios.IOSDriver;

public class RP_018_TestDeepLinks extends LoginHelper{
	ExcelReader xlsDeepLinks = new ExcelReader(configProps.getProperty("TestData"),
			"RP_018");	
  @Test(dataProvider = "testData", groups = { "Mobile" })
  public void testDeepLinks(String firstName, String lastName, String emailId,
		  String password,boolean status, String description) throws Throwable{  
	try{
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
		  handelSplashScreen();
		  //handleSplashDialog();
		  String link = "redplanet://in_stay_mode";
		  Set<String> contexts = null;
		  	navigateToMyAccount();
		  	validateUserLogin();
		  	click(AccountPageLocators.logInButton, "logInButton");
			login(emailId, password);
			navigateToAboutUs();
			waitForElementPresent(AboutUsLocators.facebookImage,"facebookImage");
			click(AboutUsLocators.facebookImage,"facebookImage");
			//click(LoginPageLocators.connectWithFacebookButton, "connectWithFacebookButton");
			Thread.sleep(10000);
			contexts = ((IOSDriver) driver).getContextHandles();
			//GeneralHelper.deepLinkHelper(link,contexts);
			for(String currContext : contexts){
				System.out.println("current context is :"+currContext);
				if(currContext.contains("WEBVIEW")){
					((IOSDriver) driver).context(currContext);
					Reporter.SuccessReport("validate switch to web context", 
							" Successfull switched to web context"+currContext);
					//Thread.sleep(10000);
					System.out.println("++"+driver.getTitle());
					Iosdriver.navigate().to("redplanet://in_stay_mode");
					Reporter.SuccessReport("validate load deep link url", 
							" Successfull loaded url "+link);
					Thread.sleep(1000);
					
					break;
					}
				}
			Set<String> contexts2 = ((IOSDriver) driver).getContextHandles();
			for(String currContext : contexts2){
				System.out.println("current context is :"+currContext);
				if(currContext.contains("NATIVE")){
					((IOSDriver) driver).context(currContext);
					//System.out.println(driver.switchTo().alert().getText());
					//System.out.println(Iosdriver.getPageSource());
					/*
					if(isElementDisplayed(By.xpath("//*[@name='Open']"))){
						click(By.xpath("//*[@name='Open']"),"Open");
					}*/
					handleOpenPrompt();
					/*List<WebElement> we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation
							("target.frontMostApp().windows()");
							System.out.println("+++"+we.size());*/
					Reporter.SuccessReport("validate switch to Native context", 
							" Successfull swithched to app");
					handleSplashDialog();
					waitForElementPresent(FacebookLoginLocators.redPlanetApplication, "redPlanetApplication");
					
					waitForElementPresent(HomePageLocators.inHousePhoneButton, "inHousePhoneButton");
					break;
				}
			}
			
	}catch(Exception e) {
		e.printStackTrace();
		Reporter.failureReport(description, "Failed with exception");
	}	
  }
  public static void handleOpenPrompt() throws Exception{
	  Screen screen = new Screen();
		Match mat = null;
		if(mat.compare(System.getProperty("user.dir")+"/Drivers/Open.png") != null){
	  //BufferedImage imgScreen = screen.capture().getImage();
	  //ImageIO.write(imgScreen, "png", new File(System.getProperty("user.dir")+"/Drivers/test.png"));
	  System.out.println("in Sikuli block");
			screen.click(mat.compare(System.getProperty("user.dir")+"/Drivers/Open.png"));
		}
  }
  
  	@DataProvider(name="testData")
	public Object[][] createdata1() {
  		return (Object[][]) new Object[][] { 
			  {xlsDeepLinks.getCellValue("fName", "Value"),xlsDeepLinks.getCellValue("lName", "Value"),
				  xlsDeepLinks.getCellValue("ValidCredentials","Value"),
				  xlsDeepLinks.getCellValue("ValidCredentials","password"),true,"Test DeepLinks"}};
	}
}
