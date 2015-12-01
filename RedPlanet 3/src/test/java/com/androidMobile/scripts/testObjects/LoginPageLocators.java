package com.androidMobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class LoginPageLocators extends ActionEngine {
	// Login page
	public static By emailFieldForLogin = By
			.xpath("//*[contains(@resource-id,'signin_email')]");
			//("//*[@resource-id='com.redplanethotels.staging:id/signin_email']");
	public static By passwordFieldForLogin = By
			.xpath("//*[contains(@resource-id,'signin_password')]");
	public static By signInButton = By
			.xpath("//*[contains(@resource-id,'signin_btn_signin')]");
	public static By signInWithFaceBookButton = By
			.xpath("//*[contains(@resource-id,'signin_btn_facebook')]");
	public static By forgotPasswordLink = By
			.xpath("//*[contains(@resource-id,'signin_btn_forgotpassword')]");
	public static By forgotPasswordframe = By
			.xpath("//*[contains(@resource-id,'android:id/alertTitle')]");
	public static By emailFieldForForgotPswd = By
			.xpath("//*[contains(@text,'Email Address')]");
	public static By cancelButton = By
			.xpath("//*[@text='CANCEL']");
	public static By resetButton = By
			.xpath("//*[@text='RESET']");
	public static By errorPop = By
			.xpath("//*[contains(@text,'Error:')]");
	public static By okayButtonOnErrorpop = By
			.xpath("//*[@text='OKAY']");
	public static By nameInputField = By
			.xpath("//*[@text='Name']/following::*[@class='android.widget.TextView'][1]");
	public static By editButton = By
			.xpath("//*[@text='EDIT']");
	public static By instayPopupClose = By.xpath("//*[contains(@resource-id,'instay_popup_iv_close')]");
}
