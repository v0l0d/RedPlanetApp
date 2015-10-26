package com.redplanet.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class PaymentDtsLocators extends ActionEngine{

	
	public static By creditCardRadio = By.xpath("//input[@type='radio' and @value='credit_card']");
	public static By payAtHotelRadio = By.xpath("//input[@type='radio' and @value='pay_later']");
	public static By paymentFrame = By.id("adyen-encrypted-form");
	public static By nameOnCardInput = By.id("holder-name");
	public static By cardNumberInput = By.id("number");
	public static By cardExpiryInput = By.id("expiry");
	public static By cvcInput = By.id("cvc");
	public static By promotionInput = By.id("promotion");
	public static By termAndConditionsCheckBox = By.xpath("//input[@id='agreed']/following-sibling::label");
	public static By confirmandPayButton = By.xpath("//div[@id='payment-input-submit']/a");
	public static By applyPromocodeLink = By.xpath("//a[contains(text(),'Apply')]");
	
}
