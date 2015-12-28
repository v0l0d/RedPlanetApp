package com.mobile.workflows;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.ForgotPasswordLocators;
import com.mobile.scripts.testObjects.GmailLocators;
import com.mobile.scripts.testObjects.LoginPageLocators;

public class ForgotPasswordHelper extends GmailLocators {

	static boolean b = true;
	
	public static void navigateToForgotPasswordScreen() throws Throwable{
		Point pt = Iosdriver.findElement(LoginPageLocators.signInButton).getLocation();
		  int tempY = 0;  
		int locX = pt.getX();
		  int locY = pt.getY();
		  System.out.println("locY "+ locY +"locX "+locX);
		  Dimension dim = Iosdriver.findElement(LoginPageLocators.signInButton).getSize();
		  System.out.println("++++ "+dim.height+"+++++ "+dim.width);
		  int newY = locY+dim.getHeight();
		  int newX = dim.getWidth()/2;
		  System.out.println(" newY " +newY+" newX " +newX); 
		  		tempY = newY;
		  		for(int j=1;j<500;j++){
					 tempY=(tempY-1);
				 //System.out.println(" j "+j+" x "+newX+" y "+tempY);
				 (Iosdriver).tap(1, newX, tempY, (int) 0.6);
				 if(isElementDisplayedTemp(ForgotPasswordLocators.resetPasswordButton)){	
					 System.out.println(" x "+newX+" y "+tempY);
					 break;
				 }
		  }	
	}
	
	public static void ResetPasswordViaGmail(String gmailUrl,String userId,String password,String resetPwd) throws Throwable{
		WebDriver  browser = null;
		try {
			 	  browser = new FirefoxDriver();			 	
			 	browser.manage().window().maximize();			 	
			 	browser.get(gmailUrl);
			 	browser.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			 	if(waitForElementPresent(browser,GmailLocators.userIdInputField, "userId")){
			 		type(browser,GmailLocators.userIdInputField, userId, "userId");
			 		click(browser,GmailLocators.nextButton, "nextButton");
			 	}
			 	waitForElementPresent(browser,GmailLocators.passwordInputField, "password");
			 	type(browser,GmailLocators.passwordInputField, password, "password");
			 	click(browser,GmailLocators.signInButton, "signInButton");
			 	waitForElementPresent(browser,GmailLocators.composeNewMailButton, "composeButton");
			 	click(browser,GmailLocators.mailItemFromRedPlanetHotels, "mailItem");
			 	String oldTab = browser.getWindowHandle();
			 	waitForElementPresent(browser,GmailLocators.resetLink, "resetLink");
			 	click(browser,GmailLocators.resetLink, "resetLink");
			 	Thread.sleep(5000);
			 	ArrayList<String> newTab = new ArrayList<String>(browser.getWindowHandles());
			    newTab.remove(oldTab);
			    browser.switchTo().window(newTab.get(0));
			    waitForElementPresent(browser,GmailLocators.resetPassworddInputField, "resetPassworddInputField");
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
		 	browser.quit();
		}
	}
	
	public static boolean click(WebDriver browser,By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			browser.findElement(locator).click();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("Click", "Unable to click on "
						+ locatorName);
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
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("Type ",
						"Data typing action is not perform on " + locatorName
								+ " with data is " + testdata);
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
