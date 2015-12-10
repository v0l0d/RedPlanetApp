package com.androidMobile.scripts.testObjects;

import org.openqa.selenium.By;

public class FrontDeskWebLocators {
	
	public static By userId = By.id("username");
	public static By password = By.id("password");
	public static By signInButton = By.id("login-submit");
	public static By chatIcon = By.xpath("//i[@class='ion-android-chatboxes']");
	public static By allIssuesLink = By.xpath("//span[contains(text(),'All Issues')]");
	public static By searchInputBox = By.id("issue-search");
	public static String Testmessage = ("//div[contains(text(),'#')]");
	public static By textAreaForAdminChat = By.xpath("//textarea[@placeholder='Type your message']");
	public static By replyButton = By.xpath("//button[contains(text(),'Reply')]");
	public static By youReplyedText = By.xpath("//span[contains(text(),'replied')]");
	public static String replyMessage = ("//*[contains(text(),'#')]");
	public static By successText = By
			.xpath("//*[@id='reset-password']/div[2]/h4");
}
