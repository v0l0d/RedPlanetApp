package com.mobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class PickRoomPageLocators extends ActionEngine{
	
	public static By pickRoomPage = By
			.xpath("//UIAStaticText[@name='ROOM DETAILS']");
	public static By bookNowButton = By
			.xpath("//UIAButton[contains(@name,'CONTINUE')]");
	public static By expanderButton = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/sri_hotel_btn_expand']");
	public static By checkIn = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/dateview_checkin']");
	public static By checkOut = By
			.xpath("//*[@resource-id='com.redplanethotels.staging:id/dateview_checkout']");
	public static By infoTab = By
			.xpath("//*[@text='INFO']");
	public static By mapTab = By
			.xpath("//*[@text='MAP']");
	public static By galleryTab = By
			.xpath("//*[@text='GALLERY']");
	public static By nearByTab = By
			.xpath("//*[@text='NEARBY']");

}
