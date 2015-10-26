package com.mobile.workflows;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ctaf.support.ExcelReader;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.GmailLocators;
import com.mobile.scripts.testObjects.LoginPageLocators;

public class GmailHelper extends GmailLocators {

	static boolean b = true;
	
	public static void ResetPasswordViaGmail(String gmailUrl,String userId,String password,String resetPwd) throws Throwable{
		
		try {
			 	WebDriver  browser = new FirefoxDriver();			 	
			 	browser.manage().window().maximize();			 	
			 	browser.get(gmailUrl);
			 	browser.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			 	if(waitForElementPresent(browser,GmailLocators.userId, "userId")){
			 		type(browser,GmailLocators.userId, userId, "userId");
			 		click(browser,GmailLocators.nextButton, "nextButton");
			 	}
			 	waitForElementPresent(browser,GmailLocators.password, "password");
			 	type(browser,GmailLocators.password, password, "password");
			 	click(browser,GmailLocators.signInButton, "signInButton");
			 	Thread.sleep(5000);
			 	waitForElementPresent(browser,GmailLocators.composeButton, "composeButton");
			 	click(browser,GmailLocators.mailItem, "mailItem");
			 	String oldTab = browser.getWindowHandle();
			 	waitForElementPresent(browser,GmailLocators.resetLink, "resetLink");
			 	click(browser,GmailLocators.resetLink, "resetLink");
			 	Thread.sleep(5000);
			 	ArrayList<String> newTab = new ArrayList<String>(browser.getWindowHandles());
			    newTab.remove(oldTab);
			    browser.switchTo().window(newTab.get(0));
			    type(browser, GmailLocators.resetPwdInput, resetPwd, "resetPwdInput");
			    click(browser, GmailLocators.submitButton, "submitButton");
			    Thread.sleep(5000);
			    if(browser.findElement(GmailLocators.successText).getText().equals("Your password has been changed.")){
			    	Reporter.SuccessReport("Reset password", "Successful");
			    }else
			    	Reporter.failureReport("Reset password", "Failed");
			    browser.close();			    
			    browser.switchTo().window(oldTab);
			 	browser.quit();
			 	
		}catch(Exception e){
			e.printStackTrace();						
		}		
	}
	
	public static boolean click(WebDriver browser,By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			browser.findElement(locator).click();
			flag = true;
		} catch (Exception e) {
			Assert.assertTrue(flag,"Unable to click on "+ locatorName);
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("Click", "Unable to click on "
						+ locatorName);
				Assert.assertTrue(flag,"Unable to click on "+ locatorName);
				return flag;
			} else if (b && flag) {
				Reporter.SuccessReport("Click", "Successfully click on "
						+ locatorName);

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
			Assert.assertEquals(false, true," type : Data typing action is not perform on  "+locatorName);

			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("Type ",
						"Data typing action is not perform on " + locatorName
								+ " with data is " + testdata);
				Assert.assertTrue(flag,
						"Unable to perform type action on the element "+ locatorName);
				return true;
			} else if (b && flag) {

				Reporter.SuccessReport("Type ",
						"Data typing action is performed on " + locatorName
								+ " with data is " + testdata);

			}
		}
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
			
			Assert.assertTrue(flag,"waitForElementPresent : Falied to locate element "+locator);

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
