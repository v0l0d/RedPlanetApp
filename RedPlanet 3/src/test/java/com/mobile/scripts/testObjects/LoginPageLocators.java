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
	public static By signInWithFaceBookButton = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/signin_btn_facebook']");
	public static By forgotPasswordLink = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/signin_btn_forgotpassword']");
	public static By forgotPasswordframe = By
			.xpath("//*[@resource-id='android:id/alertTitle']");
	public static By emailFieldForForgotPswd = By
			.xpath("//*[@text='Email Address']");
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
}
