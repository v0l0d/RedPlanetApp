package com.mobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class PickRoomPageLocators extends ActionEngine{
	
	public static By pickRoomPage = By
			.xpath("//UIAStaticText[@name='ROOM DETAILS']");
	public static By bookNowButton = By
			.xpath("//UIAButton[contains(@name,'CONTINUE')]");
	

}
