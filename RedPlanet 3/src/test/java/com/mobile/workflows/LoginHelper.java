package com.mobile.workflows;

import com.ctaf.support.ExcelReader;
import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.LoginPageLocators;

public class LoginHelper extends HomePageHelper {
	
	//Login with credentials
	public boolean login(String email, String password) throws Throwable{
		boolean res = false;
		try {
			waitForElementPresent(LoginPageLocators.emailFieldForLogin,
					"emailField");
			type(LoginPageLocators.emailFieldForLogin, email, "emailField");
			waitForElementPresent(LoginPageLocators.passwordFieldForLogin,
					"passwordFieldForLogin");
			driver.findElement(LoginPageLocators.passwordFieldForLogin).clear();
			type(LoginPageLocators.passwordFieldForLogin, password,
					"passwordFieldForLogin");
			waitForElementPresent(LoginPageLocators.signInButton,
					"signInButton");
			click(LoginPageLocators.signInButton, "signInButton");
			Thread.sleep(3000);
			if(waitForElementPresent(LoginPageLocators.nameFieldLable,"nameInputField")){				
				/*res = waitForElementPresent(AccountPageLocators.accountScreenTitle,
						"accountScreenTitle");*/
				res = true;
			}
		}catch(Exception e){
			//e.printStackTrace();
			click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");
			return false;
		}
		System.out.println(res);
		return res;
	}
	
	//Login for errorpop
		public boolean loginforErrorPopup(String email, String password) throws Throwable{
			boolean res = false;
			try {
				waitForElementPresent(LoginPageLocators.emailFieldForLogin,
						"emailField");
				type(LoginPageLocators.emailFieldForLogin, email, "emailField");
				waitForElementPresent(LoginPageLocators.passwordFieldForLogin,
						"passwordFieldForLogin");
				type(LoginPageLocators.passwordFieldForLogin, password,
						"passwordFieldForLogin");
				waitForElementPresent(LoginPageLocators.signInButton,
						"signInButton");
				click(LoginPageLocators.signInButton, "signInButton");
				if(!(isElementDisplayed(LoginPageLocators.nameFieldLable))){				
					waitForElementPresent(LoginPageLocators.okayButtonOnErrorpop,
							"okayButtonOnErrorpop");
					click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");
					//driver.navigate().back();
				}
			}catch(Exception e){
				e.printStackTrace();				
				return false;
			}
			System.out.println(res);
			return res;
		}
		
		//Login with credentials
		public boolean userlogin(String email, String password) throws Throwable{
			boolean res = false;
			try {
				waitForElementPresent(LoginPageLocators.emailFieldForLogin,
						"emailField");
				type(LoginPageLocators.emailFieldForLogin, email, "emailField");
				waitForElementPresent(LoginPageLocators.passwordFieldForLogin,
						"passwordFieldForLogin");
				driver.findElement(LoginPageLocators.passwordFieldForLogin).clear();
				type(LoginPageLocators.passwordFieldForLogin, password,
						"passwordFieldForLogin");
				waitForElementPresent(LoginPageLocators.signInButton,
						"signInButton");
				click(LoginPageLocators.signInButton, "signInButton");
				res=true;
			}catch(Exception e){
				//e.printStackTrace();
				click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");
				return false;
			}
			System.out.println(res);
			return res;
		}
	
}
