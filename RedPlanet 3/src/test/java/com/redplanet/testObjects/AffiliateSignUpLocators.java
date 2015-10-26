package com.redplanet.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class AffiliateSignUpLocators extends ActionEngine{
	
	public static By signUpFrame = By.id("signup-box");
	public static By OrganizationRadioButton = By.id("group_org");
	public static By IndvdlRadioButton = By.id("group_ind");
	public static By organizationInput = By.id("organization");
	public static By fnameInput = By.id("first_name");
	public static By lnameInput = By.id("last_name");
	public static By emailInput = By.id("email");
	public static By phoneInput = By.id("phone");
	public static By signUpButton = By.id("submit");
	public static By signInLink = By.xpath("//a[text()='Already have an account? Sign in here']");
	public static By RedPlanetLogo = By.xpath("//a[@class='logo']");

}
