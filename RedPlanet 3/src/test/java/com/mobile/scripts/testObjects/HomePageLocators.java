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
	public static By mainMenuIcon =  By.xpath("//UIAButton[@label='sidebar button' and @name='sidebar button']");
	public static By chatWithFrontDeskButton = By.xpath("//UIAButton[@name='CHAT WITH FRONT DESK']");
	public static By inHousePhoneButton = By.xpath("//UIAButton[@name='IN-HOUSE PHONE']");
	public static By localAttractionButton = By.xpath("//UIAButton[@name='LOCAL ATTRACTIONS']");
	//Side Menu locators
	public static By bookNowMenuItem = By.xpath("//UIAStaticText[@name='BOOK NOW'][1]");
	public static By myAccountMenuItem = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]");
	////UIAStaticText[@name='MY ACCOUNT'][1]
	public static By retrieveBookingMenuItem = By.xpath("//UIAStaticText[@name='RETRIEVE BOOKING'][1]");
	public static By aboutUsMenuItem = By.xpath("//UIAStaticText[@name='ABOUT US'][1]");
	public static By helpAndInfoMenuItem = By.xpath("//UIAStaticText[@name='HELP & INFO'][1]");
	public static By homeMenuItem = By.xpath("//UIAStaticText[@name='MY ACCOUNT'][1]");
	public static String sideMenuUITable = "target.frontMostApp().mainWindow().tableViews()[1].visibleCells()";
	//in-house
	public static By closeButtonOnDashboardDialog = By.xpath("//UIAButton[@name='X']");
	public static By addImage = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAImage[1]");
	
	
}
