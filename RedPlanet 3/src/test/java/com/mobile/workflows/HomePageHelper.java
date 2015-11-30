package com.mobile.workflows;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.HomePageLocators;

import io.appium.java_client.ios.IOSDriver;


public class HomePageHelper extends HomePageLocators {	

	public static boolean selectDestination(String country, String city) throws Throwable{
		boolean res = false;
		try {
			System.out.println("Selecting country & city "+country +" "+city);
			Thread.sleep(1000);
			waitForElementPresent(HomePageLocators.chooseLocation, "chooseLocation");
			click(HomePageLocators.chooseLocation, "chooseLocation");
			Thread.sleep(2000);
			waitForElementPresent(By.xpath(HomePageLocators.locationCountryName.replace("#", country.trim().toUpperCase())),
					"locationCountryName");
			click(By.xpath(HomePageLocators.locationCountryName.replace("#", country.trim().toUpperCase())),
					"locationCountryName");
			waitForElementPresent(By.xpath(HomePageLocators.locationCityName.replace("#", city.trim().toUpperCase())),
					"locationCityName");
			click(By.xpath(HomePageLocators.locationCityName.replace("#", city.trim().toUpperCase())), "locationCityName");
			res = waitForElementPresent(HomePageLocators.checkInLink, "checkInLink");
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	public boolean navigateToBookNow() throws Throwable{
		boolean res = false;
		try {
			waitForElementPresent(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			click(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			/*waitForElementPresent(HomePageLocators.bookNowMenuLink, "bookNowMenuLink");
			click(HomePageLocators.bookNowMenuLink, "bookNowMenuLink");*/
			Thread.sleep(6000);
			List<WebElement> we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(sideMenuUITable);
			if(we.size()==0){
				we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(/*sideMenuUITable*/
						"target.frontMostApp().mainWindow().tableViews()[0].visibleCells()");
			}
			System.out.println("++++++++"+we.size());
			we.get(1).click();
			res = waitForElementPresent(HomePageLocators.chooseLocation, "chooseLocation");
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean navigateToMyAccount() throws Throwable{
		boolean res = false;
		try {
			waitForElementPresent(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			click(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			//System.out.println("waitForElementPresent "+
			//waitForElementPresent(HomePageLocators.myAccountMenuItem, "myAccountMenuItem"));
			//(HomePageLocators.myAccountMenuItem, "myAccountMenuItem");
			//
			List<WebElement> we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(/*sideMenuUITable*/
					"target.frontMostApp().mainWindow().tableViews()[1].visibleCells()");
			if(we.size()==0){
				we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(/*sideMenuUITable*/
						"target.frontMostApp().mainWindow().tableViews()[0].visibleCells()");
			}
			System.out.println("++++++++"+we.size());
			
			we.get(2).click();
			System.out.println("++++++++"+"clicked on My Account Menu");
			 
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean navigateToReirieveBookings() throws Throwable{
		boolean res = false;
		try {
			waitForElementPresent(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			click(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			/*
			waitForElementPresent(HomePageLocators.reirieveBookingsMenuLink, "reirieveBookingsMenuLink");
			click(HomePageLocators.reirieveBookingsMenuLink, "reirieveBookingsMenuLink");
			*/
			Thread.sleep(6000);
			List<WebElement> we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(sideMenuUITable);
			if(we.size()==0){
				we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(/*sideMenuUITable*/
						"target.frontMostApp().mainWindow().tableViews()[0].visibleCells()");
			}
			System.out.println("++++++++"+we.size());
			we.get(3).click();
			/*res = waitForElementPresent(RetrieveBookingsLocators.retrieveBookingsSearchButton,
					"retrieveBookingsSearchButton");*/
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	public boolean navigateToAboutUs() throws Throwable{
		boolean res = false;
		try {
			waitForElementPresent(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			click(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			/*waitForElementPresent(HomePageLocators.aboutUsMenuLink, "aboutUsMenuLink");
			click(HomePageLocators.aboutUsMenuLink, "aboutUsMenuLink");*/
			Thread.sleep(6000);
			List<WebElement> we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(sideMenuUITable);
			if(we.size()==0){
				we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(/*sideMenuUITable*/
						"target.frontMostApp().mainWindow().tableViews()[0].visibleCells()");
			}
			System.out.println("++++++++"+we.size());
			we.get(4).click();
			/*res = waitForElementPresent(AboutUsLocators.contactWithUsHeading,
					"contactWithUsHeading");*/
			res = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	public boolean navigateToHome() throws Throwable{
		boolean res = false;
		try {
			waitForElementPresent(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			click(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			/*waitForElementPresent(HomePageLocators.bookNowMenuLink, "bookNowMenuLink");
			click(HomePageLocators.bookNowMenuLink, "bookNowMenuLink");*/
			Thread.sleep(6000);
			List<WebElement> we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(sideMenuUITable);
			if(we.size()==0){
				we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(/*sideMenuUITable*/
						"target.frontMostApp().mainWindow().tableViews()[0].visibleCells()");
			}
			System.out.println("++++++++"+we.size());
			we.get(0).click();
			res = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public void handleSplashDialog() throws Throwable{
		if(isElementDisplayed(HomePageLocators.closeWatchPopUp)){
			 click(HomePageLocators.closeWatchPopUp,"closeWatchPopUp");
		 }	
		/*if(isElementDisplayed(HomePageLocators.instayPop)){
			click(HomePageLocators.instayPop, "instayPop");
		}*/
	}
	public void validateUserLogin() throws Throwable{
		
		try{
			Thread.sleep(2000);
			if(!(isElementDisplayed(AccountPageLocators.logInButton))){					    
				scrollToText("SIGN OUT");
				waitForElementPresent(AccountPageLocators.signOutButton, 
						  "signOutButton");
				  click(AccountPageLocators.signOutButton, "signOutButton");
				  Thread.sleep(2000);
			  }
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	public boolean handelWelcomeDashboardDialog() throws Throwable{
		boolean res = false;
		try {
			if(isElementDisplayed(HomePageLocators.closeButtonOnDashboardDialog)){
				click(HomePageLocators.closeButtonOnDashboardDialog, "closeButtonOnDashboardDialog");
				res = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
}
