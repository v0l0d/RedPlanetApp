package com.redplanet.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class RoomSelectionLocators extends ActionEngine {
	
	public static By roomSelectionPage = By.xpath("//body/descendant::h2[contains(text(),'Book your hotel')][1]");
	public static By twinRoomLink = By.xpath("//div[text()=' Twin Room ']");
	public static By doubleRoomLink = By.xpath("//div[text()=' Double Room ']");
	public static By continueButton = By.xpath("//a[text()='Continue']");
	public static By guestDetailsFrame = By.xpath("//guest-input[@class='booking-container guest ng-isolate-scope']/div");

}
