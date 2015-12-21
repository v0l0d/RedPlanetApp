package com.mobile.scripts.testObjects;

import org.openqa.selenium.By;

public class ForgotPasswordLocators {

	public static By resetPasswordButton = By.xpath("//UIAButton[@name='RESET PASSWORD']");
	public static By emailInputField = By.xpath("//UIATextField[1]");
	public static By signInBackButton = By.xpath("//UIAButton[@name='SIGN IN']");
	
	//browser locators
	public static By signInGmailLink = By.xpath("//a[@id='gmail-sign-in']");
	public static By emailGmailFiled = By.xpath("//input[@id='Email']");
	public static By nextGmailButton = By.xpath("//input[@id='next']");
	public static By passwordGmailField = By.xpath("//input[@id='Passwd']");
	public static By signInGmailButton = By.xpath("//input[@id='signIn']");
	public static By invalidEmailError = By.xpath("//UIAStaticText[@name='The selected email is invalid.']");
	public static By okButtonOnErrorPopUp = By.xpath("//UIAButton[@name='Okay']");
	public static By passwordErrorPopUp = By.xpath("//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIAScrollView[1]/UIAStaticText[2]");
	
	
}
