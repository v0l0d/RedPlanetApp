package com.mobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class LoginPageLocators extends ActionEngine {
	// Login page
	public static By emailFieldForLogin = By
			.xpath("//UIATextField[@value='email@example.com']");
	public static By passwordFieldForLogin = By
			.xpath("//UIASecureTextField[@value='Password']");
	public static By signInButton = By
			.xpath("//*[@name ='Forgot Your Password?']");
	public static By forgotPasswrod = By.xpath("//UIAElement[@name='Forgot Your Password?']");
	public static By cancelButton = By
			.xpath("//UIAButton[@name='Cancel']");
	public static By resetButton = By
			.xpath("//*[@text='RESET']");
	public static By errorPop = By
			.xpath("//*[contains(@text,'Error:')]");
	public static By okayButtonOnErrorpop = By
			.xpath("//UIAButton[@name='Okay']");
	public static By nameFieldLable = By
			.xpath("//UIAStaticText[@name='NAME']");
	public static By editButton = By
			.xpath("//UIAButton[@name='Edit']");
	public static By ORText = By.xpath("//UIAButton[@name='OR']");
	public static By signInWindow = By.xpath("//UIAWindow[1]");
	public static By connectWithFacebookButton = By.xpath("//UIAButton[@name='CONNECT WITH FACEBOOK']");
}
