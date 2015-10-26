package com.mobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class GmailLocators extends ActionEngine{
	
	public static By userId = By
			.id("Email");
	public static By nextButton = By
			.id("next");
	public static By password = By
			.id("Passwd");
	public static By signInButton = By
			.id("signIn");
	public static By persistentCookieCheckBox = By
			.id("PersistentCookie");
	public static By composeButton = By
			.xpath("//div[text()='COMPOSE']");
	public static By mailItem = By
			.xpath("//body/descendant::span[@email='developer@redplanethotels.com'][1]");
	public static By resetLink = By
			.xpath("//body/descendant::div[contains(text(),'Please click on the link')]/a");
	public static By resetPwdInput = By
			.xpath("//*[@id='reset-password']/div[1]/form/input");
	public static By submitButton = By
			.xpath("//*[@id='reset-password']/div[1]/form/button");
	public static By successText = By
			.xpath("//*[@id='reset-password']/div[2]/h4");

}
