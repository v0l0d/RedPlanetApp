package com.mobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class BookPageLocators extends ActionEngine{

	public static By contiuneButton = By
			.xpath("//UIAButton[@name='CONTINUE']");
	public static By logInButton = By
			.xpath("//UIAButton[contains(@name,'Login')]");
	public static By guestDetailsFrame = By
			.xpath("//UIAStaticText[contains(@name,'GUEST DETAILS')]");
	
	public static By titleInput = By
			.xpath("//*[@value='Title']");
	public static By firstNameInput = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIATextField[2]");
			//.xpath("//*[@value='First Name']");
	public static By lastNameInput = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIATextField[3]");
			//.xpath("//*[@value='Last Name']");//
	public static By EmailInput = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIATextField[4]");
			//.xpath("//*[@value='Email']");
	public static By cardHolderNameInput = By
			.xpath("//*[contains(@value,'Cardholder')]");
	public static By cardNumInput = By
			.xpath("//*[contains(@value,'Card Number')]");
	public static By expMonthInput = By
			.xpath("//*[contains(@value,'MM')]");
	public static By expYearInput = By
			.xpath("//*[contains(@value,'YYYY')]");
	public static By cvvNumInput = By
			.xpath("//*[contains(@value,'CVV')]");
	public static By conditionsCheck = By
			.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableGroup[3]/UIAButton[1]");
	public static By bookButton = By
			.xpath("//*[contains(@name,'BOOK')]");
	public static By errorPayment = By
			.xpath("//*[contains(@name,'Error')]");
	public static By inValidError = By.xpath("//UIAAlert[1]/UIAScrollView[1]/UIAStaticText[2]");
	public static By okButton = By
			.xpath("//*[contains(@name,'Okay')]");
	
	//Confirmation page Locators
	public static By bookingCode = By
			.xpath("//*[contains(@name,'CONFIRMATION CODE')]");
	public static By doneButton = By
			.xpath("//*[contains(@name,'DONE')]");
	public static By bookedCity = By.
			xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]");
	public static By bookedHotel = By.
			xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[2]");
	public static By bookingFromToDate = By.
			xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[3]");
	public static By totalCost = By.
			xpath("//UIAStaticText[contains(@name,'(')][2]");
	
	//Retrieve booking
	public static By emailInputOnRetrieveBooking = By
			.xpath("//*[contains(@name,'CODE')]");
	public static By codeOnRetrieveBooking = By
			.xpath("//*[contains(@name,'CODE')]");
	
	
}
