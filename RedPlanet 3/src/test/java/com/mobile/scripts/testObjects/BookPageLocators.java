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
	
}
