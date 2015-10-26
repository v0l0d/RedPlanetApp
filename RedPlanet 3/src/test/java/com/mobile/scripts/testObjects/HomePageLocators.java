package com.mobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class HomePageLocators extends ActionEngine {
	
	//Home page 
	public static By closeWatchPopUp = By.xpath("//UIAApplication[1]"); 
	public static By chooseLocation = By.xpath("//UIAStaticText[@name='LOCATION']");
	public static String locationCountryName = ("//*[@name='#']");
	public static String locationCityName = ("//*[@name='#']");
	public static By checkInLink = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAButton[1]");
	public static By checkOutLink = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAButton[1]");
	public static String checkInDate = ("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAButton[1]");
	public static String checkOutDate = ("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAButton[1]");
	public static By searchButton = By.xpath("//UIAButton[@name='SEARCH']"); 
	//Main Menu locators
	public static By mainMenuIcon =  By.xpath("//*[@name='sidebar button']");
	
	
	//In-stay Locators
	public static By inStayFrame = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/instay_ll_instay']");
	public static By chatButton = By.xpath("//*[@name='CHAT WITH FRONT DESK']");
	public static By inHousePhoneButton = By.xpath("//*[@name='IN-HOUSE PHONE']");
	public static By localAttractionButton = By.xpath("//*[@name='LOCAL ATTRACTIONS']");
	public static By instayPop = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/instay_popup_iv_close']");
}
