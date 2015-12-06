package com.mobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class PickRoomPageLocators extends ActionEngine{
	
	public static By pickRoomPage = By
			.xpath("//UIAStaticText[@name='ROOM DETAILS']");
	public static By bookNowButton = By
			.xpath("//UIAButton[contains(@name,'CONTINUE')]");
	public  static By checkInButtonOnPickRoom = By.xpath(
			"//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAButton[2]");
	public static By tableWinodow = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]");
	public static By foodTab = By.xpath(" //UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableGroup[1]/UIAButton[1]");
	public static By attractionsTab = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableGroup[1]/UIAButton[2]");
	public static By eventsTab = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableGroup[1]/UIAButton[3]");
	public static By backButton = By.xpath("//UIAButton[@name='Back'][1]");
	public static By oKOnConfirmation = By.xpath("//*[@name='Open']");
	

}
