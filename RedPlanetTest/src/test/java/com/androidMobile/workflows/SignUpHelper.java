package com.androidMobile.workflows;

import org.testng.annotations.Test;

import com.androidMobile.scripts.testObjects.SignUpEmailLocators;

public class SignUpHelper extends SignUpEmailLocators{
  @Test
  public static boolean SignUp(String fName, String lName, String email,
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
		  type(SignUpEmailLocators.verifyInputField, password, 
				  "verifyInputField");
		  //scrollToText("Sign up");
		  waitForElementPresent(SignUpEmailLocators.signUpButton, "signUpButton");
		  click(SignUpEmailLocators.signUpButton, "signUpButton");
		  Thread.sleep(8000);
		  if(isElementDisplayed(SignUpEmailLocators.editLink)){
			  res = true;
		  }
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
		  type(SignUpEmailLocators.verifyInputField, password, 
				  "verifyInputField");
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
  
  public static boolean ModifyUserDetails(String fName, String lName, String email,
		  String title) throws Throwable {
	  boolean res = false;
	  try{
		  waitForElementPresent(SignUpEmailLocators.editLink, 
				  "editLink");
		  Thread.sleep(1000);
		  click(SignUpEmailLocators.editLink, "editLink");
		  waitForElementPresent(SignUpEmailLocators.firstnameInputField, 
				  "firstnameInputField");
		  Thread.sleep(2000);
		  type(SignUpEmailLocators.firstnameInputField, fName, 
				  "firstnameInputField");
		  waitForElementPresent(SignUpEmailLocators.lastNameInputField,
				  "lastNameInputField");
		  type(SignUpEmailLocators.lastNameInputField, lName, 
				  "lastNameInputField");
		  click(SignUpEmailLocators.doneButton, "doneButton");
		  Thread.sleep(5000);
		  res = true;
	  }catch(Exception e){
			e.printStackTrace();
		}
		return res;
  }
  
  
}
