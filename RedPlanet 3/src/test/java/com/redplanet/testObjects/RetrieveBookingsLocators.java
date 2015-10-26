package com.redplanet.testObjects;

import org.openqa.selenium.By;

public class RetrieveBookingsLocators {

	
	public static By retrieveBookingsSearchButton = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/booking_btn_search']");
	public static By emailInputField = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/nonmember_et_email']");
	public static By bookingCodeInputField = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/nonmember_et_booking_code']");
}
