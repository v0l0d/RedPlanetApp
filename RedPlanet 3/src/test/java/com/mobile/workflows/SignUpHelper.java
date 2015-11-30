package com.mobile.workflows;

import org.testng.annotations.Test;

import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.SignUpEmailLocators;

public class SignUpHelper extends SignUpEmailLocators{
  @Test
  public static boolean SignUp(String fName, String lName, String email,
		  String password) throws Throwable {
	  boolean res = false;
	  try{
		  
		  waitForElementPresent(SignUpEmailLocators.emailInputField, 
				  "emailInputField");
		  type(SignUpEmailLocators.emailInputField, email, 
				  "emailInputField");
		  waitForElementPresent(SignUpEmailLocators.passwordInputField, 
				  "passwordInputField");
		  type(SignUpEmailLocators.passwordInputField, password, 
				  "passwordInputField");
		  waitForElementPresent(SignUpEmailLocators.firstnameInputField, 
				  "firstnameInputField");
		  type(SignUpEmailLocators.firstnameInputField, fName,
				  "firstnameInputField");
		  waitForElementPresent(SignUpEmailLocators.lastNameInputField, 
				  "lastNameInputField");
		  type(SignUpEmailLocators.lastNameInputField, lName, 
				  "lastNameInputField");
		  /*type(SignUpEmailLocators.verifyInputField, password, 
				  "verifyInputField");*/
		  waitForElementPresent(SignUpEmailLocators.signUpButton, "signUpButton");
		  click(SignUpEmailLocators.signUpButton, "signUpButton");
		  Thread.sleep(8000);
		  //if(waitForElementPresent(AccountPageLocators.accountScreenTitle,"accountScreenTitle")){
			  res = true;
		  //}
	  }catch(Exception e){
			e.printStackTrace();
		}
		return res;
  }
  
  public static boolean UserSignUp(String fName, String lName, String email,
		  String password) throws Throwable {
	  boolean res = false;
	  try{
		  waitForElementPresent(SignUpEmailLocators.firstnameInputField, 
				  "firstnameInputField");
		  type(SignUpEmailLocators.firstnameInputField, fName,
				  "firstnameInputField");
		  type(SignUpEmailLocators.lastNameInputField, lName, 
				  "lastNameInputField");
		  type(SignUpEmailLocators.emailInputField, email, 
				  "emailInputField");
		  type(SignUpEmailLocators.passwordInputField, password, 
				  "passwordInputField");
		  /*type(SignUpEmailLocators.verifyInputField, password, 
				  "verifyInputField");*/
		  scrollToText("Sign up");
		  waitForElementPresent(SignUpEmailLocators.signUpButton, "signUpButton");
		  click(SignUpEmailLocators.signUpButton, "signUpButton");
		  Thread.sleep(5000);
		  res = true;
	  }catch(Exception e){
			e.printStackTrace();
		}
		return res;
  }
  
}
