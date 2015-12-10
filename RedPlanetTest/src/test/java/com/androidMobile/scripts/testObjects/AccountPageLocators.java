package com.androidMobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class AccountPageLocators extends ActionEngine {

	public static By accountScreenTitle = By
			.xpath("//*[@text='ACCOUNT");
	public static By EditLink = By
			.xpath("//*[@text='EDIT']");
	public static By userName = By
			.xpath("//*[@class='android.widget.TextView'][contains(@resource-id,'account_name')]");
	public static By userNationality = By
			.xpath("//*[@class='android.widget.TextView'][contains(@resource-id,'account_nationality')]");
	public static By userPassport = By
			.xpath("//*[@class='android.widget.TextView'][contains(@resource-id,'account_passport')]");
	public static By userResidence = By
			.xpath("//*[@class='android.widget.TextView'][contains(@resource-id,'account_residence')]");
	public static By userPhone = By
			.xpath("//*[@class='android.widget.TextView'][contains(@resource-id,'account_phone'])");
	public static By signOutButton = By.xpath("//*[@text='Sign Out']");
	
	
	//MyAccount
	public static By logInButton = By.xpath("//*[@text='Login']");
	public static By signUpButton = By.xpath("//*[contains(@text,'Sign up using email')]"); 
	public static By faceBookButton = By.xpath("//*[contains(@text,'Facebook')]");
	public static By upcomingBookings = By.xpath("//[@resource-id='com.redplanethotels.staging:id/upcoming_bookings_tv']");


}
