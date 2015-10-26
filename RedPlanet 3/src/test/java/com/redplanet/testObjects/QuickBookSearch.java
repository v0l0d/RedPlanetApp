package com.redplanet.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class QuickBookSearch extends ActionEngine{
	
	public static By quickSearchLink = By.id("quick-search");
	public static By quickSearchBox = By.xpath("//div[@class='quick-search-box-content']");
	public static By locationInput = By.xpath("//div[@id='quick-search-location']/input");
	public static By tonightButton = By.xpath("//body/descendant::button[contains(text(),'Tonight')][1]");
	public static By tomorrowButton = By.xpath("//body/descendant::button[contains(text(),'Tomorrow')][1]");
	public static By pickDatesButton = By.xpath("//body/descendant::button[contains(@class,'pickdate right')][1]");
	public static By checkInDatePicker = By.xpath("//body/descendant::input[@class='checkin datepicker'][1]");
	public static By checkOutDatePicker = By.xpath("//body/descendant::input[@class='checkout datepicker'][1]");	
	public static By adultCombo = By.xpath("//body/descendant::select[@id='guest-adult'][2]");
	public static By childCombo = By.xpath("//body/descendant::select[@id='guest_child'][2]");
	public static By searchButton = By.xpath("//a[contains(text(),'Search')]");
	public static By searchResultsPage = By.id("search-page");		

}
