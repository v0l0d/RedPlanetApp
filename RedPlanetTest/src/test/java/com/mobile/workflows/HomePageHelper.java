package com.mobile.workflows;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ctaf.utilities.Reporter;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.HomePageLocators;

import io.appium.java_client.ios.IOSDriver;


public class HomePageHelper extends HomePageLocators {	

	public static boolean selectDestination(String country, String city) throws Throwable{
		boolean res = false;
		try {
			System.out.println("Selecting country & city "+country +" "+city);
			waitForElementPresent(HomePageLocators.chooseLocation, "chooseLocation");
			click(HomePageLocators.chooseLocation, "chooseLocation");
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
			List<WebElement> we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(sideMenuUITable1);
			if(we.size()==0){
				we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(sideMenuUITable2);
			}
			System.out.println("++++++++"+we.size());
			for(WebElement ww : we){
				String name = ww.getAttribute("name");
				if(name.toLowerCase().contains("book")){
					System.out.println("+++++++"+ww.getAttribute("name"));
					ww.click();
					Reporter.SuccessReport("navigateToBookNow", 
							" Successful clicked on "+ww.getAttribute("name"));
					break;
				}
			}
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
			List<WebElement> we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(sideMenuUITable1);
			if(we.size()==0){
				we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(sideMenuUITable2);
			}
			System.out.println("++++++++"+we.size());
			for(WebElement ww : we){
				String name = ww.getAttribute("name");
				if(name.toLowerCase().contains("account")){
					System.out.println("+++++++"+ww.getAttribute("name"));
					ww.click();
					Reporter.SuccessReport("navigateToMyAccount", 
							" Successful clicked on "+ww.getAttribute("name"));
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean navigateToReirieveBooking() throws Throwable{
		boolean res = false;
		try {
			waitForElementPresent(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			click(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			/*
			waitForElementPresent(HomePageLocators.reirieveBookingsMenuLink, "reirieveBookingsMenuLink");
			click(HomePageLocators.reirieveBookingsMenuLink, "reirieveBookingsMenuLink");
			*/
			Thread.sleep(6000);
			List<WebElement> we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(sideMenuUITable1);
			if(we.size()==0){
				we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(sideMenuUITable2);
			}
			System.out.println("++++++++"+we.size());
			for(WebElement ww : we){
				String name = ww.getAttribute("name");
				if(name.toLowerCase().contains("retrieve")){
					System.out.println("+++++++"+ww.getAttribute("name"));
					ww.click();
					Reporter.SuccessReport("navigateToReirieveBookings", 
							" Successful clicked on "+ww.getAttribute("name"));
					break;
				}
			}
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
			List<WebElement> we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(sideMenuUITable1);
			if(we.size()==0){
				we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(sideMenuUITable2);
			}
			System.out.println("++++++++"+we.size());
			for(WebElement ww : we){
				String name = ww.getAttribute("name");
				if(name.toLowerCase().contains("about")){
					System.out.println("+++++++"+ww.getAttribute("name"));
					ww.click();
					Reporter.SuccessReport("navigateToAboutUs", 
							" Successful clicked on "+ww.getAttribute("name"));
					break;
				}
			}
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
			List<WebElement> we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(sideMenuUITable1);
			if(we.size()==0){
				we = ((IOSDriver)Iosdriver).findElementsByIosUIAutomation(sideMenuUITable2);
			}
			System.out.println("++++++++"+we.size());
			for(WebElement ww : we){
				String name = ww.getAttribute("name");
				if(name.toLowerCase().contains("home")){
					System.out.println("+++++++"+ww.getAttribute("name"));
					ww.click();
					Reporter.SuccessReport("navigateToHome", " Successful clicked on "+ww.getAttribute("name"));
					break;
				}
			}
			res = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static void handleSplashDialog() throws Throwable{
		/*if(isElementDisplayedTemp(HomePageLocators.closeWatchPopUp)){
			 click(HomePageLocators.closeWatchPopUp,"closeWatchPopUp");
		 }	*/
		if(isElementDisplayed(HomePageLocators.closeButtonOnDashboardDialog)){
			click(HomePageLocators.closeButtonOnDashboardDialog, "closeButtonOnDashboardDialog");
		}
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
	
	public static boolean handelSplashScreen() throws Throwable{
		boolean res = false;
		try {
			
			if(isElementDisplayedTemp(HomePageLocators.notificationPopUp)){
				click(HomePageLocators.notificationPopUp, "notificationPopUp");
				res = true;
			}
			if(isElementDisplayedTemp(HomePageLocators.locationPopUp)){
				click(HomePageLocators.locationPopUp, "locationPopUp");
				res = true;
			}
			
			if(isElementDisplayedTemp(HomePageLocators.addImage)){
				click(HomePageLocators.addImage, "addImage");
				res = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
}
