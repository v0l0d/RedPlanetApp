package com.redplanet.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class GuestDetailsLocators extends ActionEngine{
	
	public static By guestDetailsFrame = By.xpath("//guest-input[@class='booking-container guest ng-isolate-scope']/div");
	public static By firstNameInput = By.id("first-name");
	public static By lastNameInput = By.id("last-name");
	public static By emailInput = By.id("email");
	public static By phoneInput = By.id("phone");
	public static By countrycodeDropDown = By.xpath("//div[@class='arrow']");
	public static String countriesList = "//div[@class='arrow']/../following-sibling::ul/li";
	public static By continueButton = By.xpath("//div[@id='guest-input-continue']/a");
	public static By paymentDtsframe = By.xpath("//payment-input[@class='booking-container payment ng-isolate-scope']");
	
}
