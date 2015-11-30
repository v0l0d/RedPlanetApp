package com.mobile.scripts.testObjects;

import org.openqa.selenium.By;

public class RetrieveBookingLocators {
	
	public static By emailInput = By
			.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIATextField[1]");	
	public static By bookingCodeInput = By
			.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIATextField[1]");
	public static By submitButton = By
			.xpath("//*[@name='SUBMIT']");	
	public static By bookingDetailView = By
			.xpath("//*[@name='RPBookingDetailView']");
	public static By doneButton = By
			.xpath("//*[@text='DONE']");	
}
