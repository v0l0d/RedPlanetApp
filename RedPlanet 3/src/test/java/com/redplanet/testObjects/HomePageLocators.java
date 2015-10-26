package com.redplanet.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class HomePageLocators extends ActionEngine {
	
	//Home page Links
	public static By RedPlanetLogo = By.xpath("//*[@id='ng-app']/div[1]/div/a/i");	
	public static By loyaltylink = By.xpath("//a[contains(text(),'LOYALTY')]");
	public static By dealslink = By.xpath("//a[contains(text(),'Deals')]");
	public static By myBookingslink = By.xpath("//a[contains(text(),'My bookings')]");
	public static By aboutUslink = By.xpath("//a[contains(text(),'About us')]");
	public static By contactUslink = By.xpath("//a[contains(text(),'Contact us')]");
	public static By signOutlink = By.xpath("//a[contains(text(),'signout')]");
	public static By logInlink = By.xpath("//a[contains(text(),'Login')]");
	public static By referredlink = By.xpath("//a[contains(text(),'Referred')]");
	//public static String navigationLink = "//a[contains(text(),'#')]";
	public static By pageLoyalty = By.id("loyalty");
	public static By pageReferred = By.id("referred-bg");
	public static By pageDeals = By.id("flash-sale");
	public static By pageMyBooking = By.id("my-booking");
	public static By pageAboutUs = By.id("about-us");
	public static By pageContactus = By.id("contact-us");
	public static By dialogLogIn = By.id("login-dialog");
	public static By linkQuickSearch = By.id("quick-search");
	
	//Search form
	public static By locationInputField = By.xpath("//autocomplete[@id='hotel-name-inline']/div/input");
	public static By tonightButton = By.xpath("//body/descendant::button[contains(text(),'Tonight')][2]");
	public static By tomorrowButton = By.xpath("//body/descendant::button[contains(text(),'Tomorrow')][2]");
	public static By pickDatesButton = By.xpath("//body/descendant::button[contains(@class,'pickdate right')][2]");
	public static By checkInDatePicker = By.xpath("//body/descendant::input[@class='checkin datepicker'][2]");
	public static By checkOutDatePicker = By.xpath("//body/descendant::input[@class='checkout datepicker'][2]");
	//public static String calendarTable = "//body[@id='ng-app']/div[6]/div[1]/table";
	public static By adultCombo = By.xpath("//body/descendant::select[@id='guest-adult'][2]");
	public static By childCombo = By.xpath("//body/descendant::select[@id='guest_child'][2]");
	public static By searchButton = By.xpath("//button[contains(@class,'search button')]");
	public static By searchResultsPage = By.id("search-page");

	
	
}
