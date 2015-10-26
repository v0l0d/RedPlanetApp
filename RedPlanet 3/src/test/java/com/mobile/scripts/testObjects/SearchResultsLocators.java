package com.mobile.scripts.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class SearchResultsLocators extends ActionEngine{
	
	//Search Results
	public static By searchResultsPage = By.xpath("//UIAStaticText[@name='ROOM DETAILS']");
	public static By booknowButton = By.xpath("//UIAButton[contains(@name,'CONTINUE')]");
	public static By editSrchButton = By.xpath("//button[text()='Edit search']");
	public static By LocInput = By.xpath("//autocomplete[@id='location']/div/input");
	public static By checkInDatePicker = By.xpath("//body/descendant::input[@class='checkin datepicker'][2]");
	public static By checkOutDatePicker = By.xpath("//body/descendant::input[@class='checkout datepicker'][2]");
	public static By adultCombo = By.id("adult");
	public static By childCombo = By.id("child");
	public static By btnSearch = By.xpath("//button[text()='Search']");
	public static By srchFilterFrame = By.xpath("//search-filter-box[@class='ng-isolate-scope']");

}
