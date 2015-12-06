package com.androidMobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class BookPageLocators extends ActionEngine{

	public static By contiuneButton = By
			.xpath("//*[@text='Continue']");
	public static By logInButton = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/not_logged_in']");
	public static By guestDetailsFrame = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/guestdatabynum_container']");
	public static By guestRadioButton = By
			.xpath("//*[@text='I have read and agree to the Terms and Conditions and Room Rates']");
	public static By firstNameInput = By.xpath("//*[@text='First Name']");
	public static By lastNameInput = By.xpath("//*[@text='Last Name']");
	public static By EmailInput = By.xpath("//*[@text='Email']");
	public static By cardHolderInput = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/payment_holder']");
	public static By cardNumInput = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/payment_number']");
	public static By expMonthInput = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/payment_exp_month_and_year']");
	public static By cvvNumInput = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/payment_cvv']");
	public static By conditionsCheck = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/confirm_tac_cb']");
	public static By bookedCity = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/confirmed_tv_hotel_name']");
	public static By bookingToDate = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/dateview_tv_checkin_date']");
	public static By bookingFromDate = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/dateview_tv_checkout_date']");
	public static By bookedHotel = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/confirmed_tv_hotel_address']");
	public static By totalBookingCost = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/total_amount']");
	public static By bookButton = By.xpath("//*[@text='Book']");
	public static By errorPayment = By.xpath("//*[@resource-id='android:id/message']");
	public static By okButton = By.xpath("//*[@text='OKAY']");
	public static By iAMTheGuestRadio = By.xpath("//*[@text='I am the guest']");
	
	//Confirmation page Locators
	public static By bookingCode = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/confirmed_code']");
	public static By doneButton = By.xpath("//*[@text='DONE']");
	
}
