package com.androidMobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class PickRoomPageLocators extends ActionEngine{
	
	public static By pickRoomPage = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/searchresult_container']");
	public static By bookNowButton = By.xpath("//*[@text='BOOK']");
	public static By expanderButton = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/sri_hotel_btn_expand']");
	public static By checkIn = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/dateview_checkin']");
	public static By checkOut = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/dateview_checkout']");
	public static By infoTab = By.xpath("//*[@text='INFO']");
	public static By mapTab = By.xpath("//*[@text='MAP']");
	public static By galleryTab = By.xpath("//*[@text='GALLERY']");
	public static By nearByTab = By.xpath("//*[@text='NEARBY']");
	public static By expandButtonOnPickRoom = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/sri_hotel_btn_expand']");

	public static By tableWinodow = By.xpath("//*[@resource-id='com.redplanethotels.staging:id/hotel_tab_food']");
	public static By foodTab = By.xpath(" //*[@text='food']");
	public static By attractionsTab = By.xpath("//*[@text='attractions']");
	public static By eventsTab = By.xpath("//*[@text='events']");
	public static By backButton = By.xpath("//*[@text='Back']");
}
