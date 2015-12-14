package com.mobile.scripts.testObjects;

import org.openqa.selenium.By;

public class InHousePhoneLocators {

	public static By phoneTitle = By.xpath("//UIAStaticText[@name= 'PHONE']");
	public static String noButtonToDial = ("//UIAButton[@name= '#']");
	public static By callIcon = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[14]");
	public static By callingRoomLabel = By.xpath("//UIAStaticText[contains(@name,'CALLING ROOM')]");
	public static By rigingLabel = By.xpath("//UIAStaticText[contains(@name,'ringing')]");
	public static By callEndButton = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[3]");
	public static By frontDeskButtonToCall = By.xpath("//UIAButton[@name= 'front desk']");
	public static By newConversationTitle = By.xpath("//UIAStaticText[@name='New Conversation']");
	public static By textAreaForChat = By.xpath("//UIATextView[1]");
	public static By textInputField = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextView[1]");
	public static By sendButtonForChat = By.xpath("//UIAButton[contains(@name,'send')]");
	
	
	
	
}
