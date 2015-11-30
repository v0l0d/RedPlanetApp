package com.mobile.scripts.testObjects;

import org.openqa.selenium.By;

public class FacebookLoginLocators {

	public static By emailForFacebookLogin = By.xpath("//input[@name='email']");
	public static By passwordForFacebookLogin = By.xpath("//input[@name='pass']");
	public static By loginButtonForFacebookLogin = By.xpath("//button[@name='login']");
	public static By cancelFacebookButton = By.xpath("//button[@value='Cancel']");
	public static By oKFacebookButton = By.xpath("//button[@value='OK']");
}
