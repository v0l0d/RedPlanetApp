package com.mobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class SignUpEmailLocators extends ActionEngine{
	
	
	// MyAccount page
	public static By nameInputField = By
			.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIATextField[1]");
	public static By nationalityInputField = By
			.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIASecureTextField[1]");
	public static By passportInputField = By
			.xpath("//*[@text='Passport']/following::*[@class='android.widget.TextView'][1]");
	public static By residenceInputField = By
			.xpath("//*[@text='Residence']/following::*[@class='android.widget.TextView'][1]");
	public static By phoneInputField = By
			.xpath("//*[@text='Phone']/following::*[@class='android.widget.TextView'][1]");
	/*public static By upcomingLink = By
			.xpath("//*[@class='com.redplanethotels.staging:id/upcoming_bookings_tv']");
	public static By pastLink = By
			.xpath("//*[@class='com.redplanethotels.staging:id/past_bookings_tv']");*/
	public static By signOutButton = By
			.xpath("//*[@class='com.redplanethotels.staging:id/account_btn_signout']");
	public static By editLink = By
			.xpath("//*[@text='EDIT']");
	public static By doneButton = By
			.xpath("//*[@text='DONE']");
	public static By upcomingLink = By
			.xpath("//*[@text='Upcoming']");
	public static By pastLink = By
			.xpath("//*[@text='Past']");
	public static By bookingLabel = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/bookings_no_label']");
	
	//Signup page
	public static By firstnameInputField = By
			.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIATextField[1]");
	public static By lastNameInputField = By
			.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIATextField[1]");
	public static By emailInputField = By
			.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIATextField[1]");
	public static By passwordInputField = By
			.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIASecureTextField[1]");
	public static By verifyInputField = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/profile_verify']");
	public static By signUpButton = By
			.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableGroup[4]/UIAButton[1]");
	public static By facebookButton = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/signup_btn_facebook']");
	public static By loginButton = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/notloggedin_btn_login']");
	
	//Errorpop
	public static By errorPop = By
			.xpath("//*[@resource-id='android:id/parentPanel']");
	public static By okButton = By
			.xpath("//*[@text='OKAY']");
	public static By errorMessage = By
			.xpath("@class='android.widget.TextView'");
			
}
