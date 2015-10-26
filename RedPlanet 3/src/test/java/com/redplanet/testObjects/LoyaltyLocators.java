package com.redplanet.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class LoyaltyLocators extends ActionEngine{
	
	public static By signUpForm = By.xpath("//div[@class='form-input']");
	public static By joinNowButton = By.xpath("//button[contains(text(),'Join now')]");
	public static By firstNameInput = By.id("first_name");
	public static By lastNameInput = By.id("last_name");
	public static By emailInput = By.id("email");
	public static By phoneInput = By.id("phone");
	public static By passwordInput = By.id("password");
	public static By countrycodeDropDown = By.xpath("//div[@class='arrow']");
	public static String countriesList = "//div[@class='arrow']/../following-sibling::ul/li";
	public static By joinButton = By.xpath("//button[text()='Join ']");
	public static By faceBookButton = By.xpath("//button[text()='Join ']/following-sibling::button");

}
