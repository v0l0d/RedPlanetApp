package com.androidMobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class HomePageLocators extends ActionEngine {
	
	//Home page 
	public static By redPlanetMainFrame = By.xpath("//*[@class='android.widget.LinearLayout']");
	public static By closeWatchPopUp = By.xpath("//*[contains(@resource-id,'dynamic_splash_close')]"); 
	public static By chooseLocation = By.xpath("//*[@text='CHOOSE']");
			//("//*[@resource-id='com.redplanethotels.staging:id/search_initial_tv_location']");
	public static String locationCountryName = ("//*[@text='#']");
	public static String locationCityName = ("//*[@text='#']");
	public static By checkInLink = By.xpath("//*[@text='CHECK-IN']");
	public static By checkOutLink = By.xpath("//*[@text='CHECK-OUT']");
	public static String checkInDate = ("//*[@text='#'][@class='android.widget.TextView']/following::*[@text='$'][@class='android.widget.TextView'][1]");
	public static String checkOutDate = ("//*[@text='#'][@class='android.widget.TextView']/following::*[@text='$'][@class='android.widget.TextView'][1]");
	public static By searchButton = By.xpath("//*[contains(@resource-id,'search_tv_search')]");
			//("//*[@resource-id='com.redplanethotels.staging:id/search_tv_search']"); 
	//Main Menu locators
	public static By mainMenuIcon =  By.xpath("//*[contains(@resource-id,'titlebar_iv_menu')]");
			//("//*[@resource-id='com.redplanethotels.staging:id/titlebar_iv_menu_icon']");
	public static By bookNowMenuLink = By.xpath("//*[@text='BOOK NOW']");
	public static By myAccountMenuLink = By.xpath("//*[@text='MY ACCOUNT']");
	public static By reirieveBookingsMenuLink = By.xpath("//*[@class='android.widget.TextView'][@text='RETRIEVE BOOKINGS']");
	public static By aboutUsMenuLink = By.xpath("//*[@class='android.widget.TextView'][@text='ABOUT US']");
	public static By helpMenuLink = By.xpath("//*[@class='android.widget.TextView'][@text='HELP & INFO']");
	public static By rateAppPopUp = By.xpath("//*[@text='Rate Our App']");
	public static By closeRateAppPopUp = By.xpath("//*[@text='Close']");
	public static By homeMenuLink = By.xpath("//*[@text='HOME']");
	//in-house
		public static By closeButtonOnDashboardDialog = By.xpath("//*[@text='X']");
		public static By chatWithFrontDeskButton = By.xpath("//*[@text='CHAT WITH FRONT DESK']");
		public static By inHousePhoneButton = By.xpath("//*[@text='In-House Phone']");
		public static By localAttractionButton = By.xpath("//*[@text='Local Attractions']");
		
	
}
