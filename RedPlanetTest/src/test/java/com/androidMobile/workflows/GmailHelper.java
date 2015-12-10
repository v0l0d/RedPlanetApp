package com.androidMobile.workflows;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.androidMobile.scripts.testObjects.AccountPageLocators;
import com.androidMobile.scripts.testObjects.GmailLocators;
import com.androidMobile.scripts.testObjects.LoginPageLocators;
import com.ctaf.support.ExcelReader;
import com.ctaf.utilities.Reporter;

public class GmailHelper extends GmailLocators {

	static boolean b = true;
	
	public static void ResetPasswordViaGmail(String gmailUrl,String userId,String password,String resetPwd) throws Throwable{
		
		WebDriver  browser = new FirefoxDriver();
		try {
			 	browser.manage().window().maximize();			 	
			 	browser.get(gmailUrl);
			 	browser.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			 	if(waitForElementPresent(browser,GmailLocators.userIdInputField, "userId")){
			 		type(browser,GmailLocators.userIdInputField, userId, "userId");
			 		if(isElementDisplayedTemp(browser,GmailLocators.nextButton)){
			 			click(browser,GmailLocators.nextButton, "nextButton");
			 		}
			 	}
			 	waitForElementPresent(browser,GmailLocators.passwordInputField, "password");
			 	type(browser,GmailLocators.passwordInputField, password, "password");
			 	click(browser,GmailLocators.signInButton, "signInButton");
			 	Thread.sleep(5000);
			 	waitForElementPresent(browser,GmailLocators.composeNewMailButton, "composeButton");
			 	waitForElementPresent(browser,GmailLocators.mailItemFromRedPlanetHotels, "NewMailItemFromRedPlanetHotels");
			 	click(browser,GmailLocators.mailItemFromRedPlanetHotels, "NewMailItemFromRedPlanetHotels");
			 	String oldTab = browser.getWindowHandle();
			 	waitForElementPresent(browser,GmailLocators.resetLink, "resetLink");
			 	click(browser,GmailLocators.resetLink, "resetLink");
			 	Thread.sleep(5000);
			 	ArrayList<String> newTab = new ArrayList<String>(browser.getWindowHandles());
			    newTab.remove(oldTab);
			    browser.switchTo().window(newTab.get(0));
			    type(browser, GmailLocators.resetPassworddInputField, resetPwd, "resetPwdInput");
			    click(browser, GmailLocators.submitButton, "submitButton");
			    Thread.sleep(5000);
			    if(browser.findElement(GmailLocators.successText).getText().equals("Your password has been changed.")){
			    	Reporter.SuccessReport("Reset password", "Successful");
			    }else
			    	Reporter.failureReport("Reset password", "Failed");
			   
		}catch(Exception e){
			e.printStackTrace();						
		}finally{
			 //browser.close();			    
			    //browser.switchTo().window(oldTab);
			 	browser.quit();
		}
	}
	
	public static boolean click(WebDriver browser,By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			browser.findElement(locator).click();
			Thread.sleep(1000);
			flag = true;
			System.out.println("ointialed browseron exktp																						");
		} catch (Exception e) {
			//Assert.assertTrue(flag,"Unable to click on "+ locatorName);
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("Click", "Unable to click on "+ locatorName);
				Assert.assertTrue(flag,"Unable to click on "+ locatorName);
				return flag;
			} else if (b && flag) {
				Reporter.SuccessReport("Click", "Successfully click on "+ locatorName);
			}
		}
		return flag;
	}
	
	public static boolean type(WebDriver browser,By locator, String testdata, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			browser.findElement(locator).clear();
			//driver.findElement(locator).click(); //temp
			/*Actions act = new Actions(driver);
			act.sendKeys(driver.findElement(locator), testdata).build().perform();*/
			browser.findElement(locator).sendKeys(testdata);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertEquals(false, true," type : Data typing action is not perform on  "+locatorName);
		} finally {
			if (!flag) {
				Reporter.failureReport("Type ",
						"Data typing action is not perform on " + locatorName
								+ " with data is " + testdata);
				Assert.assertTrue(flag,
						"Unable to perform type action on the element "+ locatorName);
			} else if (b && flag) {
				Reporter.SuccessReport("Type ","Data typing action is performed on " + locatorName
								+ " with data is " + testdata);
			}
		}
		return flag;
	}
	public static boolean isElementDisplayedTemp(WebDriver browser , By loc)
			throws Throwable {
		boolean flag = false;
		try{
			browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if(browser.findElement(loc) != null);
					flag = true;
		} catch (Exception e) {
			return false;
		}
		browser.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		return flag;
	}
	
	public static boolean waitForElementPresent(WebDriver browser,By by, String locator)
			throws Throwable {
		boolean flag = false;
		try {
				wait = new WebDriverWait(browser, 30);
				WebElement  element =  null;
					element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
				    boolean enabled = element.getSize().getHeight()>0;
				    if(enabled){ 
				    	flag = true;
				    }else {
				    	browser.wait(50);
					}
		} catch (Exception e) {
			//Assert.assertTrue(flag,"waitForElementPresent : Falied to locate element "+locator);
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("WaitForElementPresent ",
						"Falied to locate element " + locator);
			} else if (b && flag) {
				Reporter.SuccessReport("WaitForElementPresent ",
						"Successfully located element " + locator);
			}
		}
		return flag;
	}
		
}
