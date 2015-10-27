package com.mobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class AccountPageLocators extends ActionEngine {

	public static By accountScreenTitle = By
			.xpath("//*[@label='ACCOUNT']");
	public static By EditLink = By
			.xpath("//UIAButton[@name='Edit']");
	public static By userName = By
			.xpath("//UIATableCell[@name='NAME']");
	public static By userNationality = By
			.xpath("//UIATableCell[@name='NATIONALITY']");
	public static By userPassport = By
			.xpath("//UIATableCell[@name='PASSPORT']");
	public static By userResidence = By
			.xpath("//UIATableCell[@name='RESIDENCE']");
	public static By userPhone = By
			.xpath("//UIATableCell[@name='PHONE']");
	public static By signOutButton = By
			.xpath("//UIAButton[@name='SIGN OUT']");
	//MyAccount
	public static By logInButton = By
			.xpath("//UIAButton[contains(@name,'LOGIN')]");
	public static By signUpButton = By
			.xpath("//UIAButton[contains(@name,'CONNECT WITH FACEBOOK')]"); 
	public static By faceBookButton = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/notloggedin_btn_facebook']");


}
