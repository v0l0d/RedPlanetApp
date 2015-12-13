package com.mobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class AccountPageLocators extends ActionEngine {

	public static By accountScreenTitle = By.xpath("//*[@label='ACCOUNT']");
	public static By EditLink = By.xpath("//UIAButton[@name='Edit']");
	public static By userName = By
			.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]");
	public static By userNationality = By
			.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[2]");
	public static By userPassport = By
			.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[2]");
	public static By userResidence = By
			.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[2]");
	public static By userPhone = By
			.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIAStaticText[2]");
	public static By signOutButton = By.xpath("//UIAButton[@name='SIGN OUT']");
	
	public static By signUpDataValidationAlert = By.xpath("//UIAStaticText[@name='Sorry!']");
	public static By okayButtonOnErrorpop = By.xpath("//UIAButton[@name='Okay']");
	public static By errorMsgForSignUpExistingEmailID = By.
			xpath("//UIAStaticText[@name='The email has already been taken.']");
	
	//Bookings under My Account
	public static By upcomingBookings = By.xpath("//UIATableGroup[@name='UPCOMING BOOKINGS']");
	public static By bookedCity = By.xpath("//UIAStaticText[@name='#']");
	public static By totalBookingCost = By.xpath("//UIAStaticText[@name='#']");
	public static By bookedFromToDate = By.xpath("//UIAStaticText[@name='#']");
	
	//MyAccount
	public static By logInButton = By.xpath("//UIAButton[contains(@name,'LOGIN')]");
	public static By signUpButton = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]"); 
	public static By connectWithFaceBookButton = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]");


}
