package com.mobile.workflows;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;
import com.ctaf.support.ExcelReader;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.HomePageLocators;
import com.mobile.scripts.testObjects.LoginPageLocators;
import com.redplanet.testObjects.AboutUsLocators;
import com.redplanet.testObjects.RetrieveBookingsLocators;


public class HomePageHelper extends HomePageLocators {	

	public static boolean startApp(String country, String city) throws Throwable{
		boolean res = false;
		try {
			//waitForElementPresent(HomePageLocators.redPlanetMainFrame,"redPlanetMainFrame");
			/*if(isElementDisplayed(HomePageLocators.closeWatchPopUp)){
				click(HomePageLocators.closeWatchPopUp,"closeWatchPopUp");
			}*/
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
			(Iosdriver).tap(1, 105, 145, (int) 0.6);
			res = waitForElementPresent(HomePageLocators.chooseLocation, "chooseLocation");
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean navigateToMyAccount() throws Throwable{
		boolean res = false;
		try {
			waitForElementPresent(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			click(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			/*waitForElementPresent(HomePageLocators.myAccountMenuLink, "myAccountMenuLink");
			click(HomePageLocators.myAccountMenuLink, "myAccountMenuLink");*/
			(Iosdriver).tap(1, 91, 218, (int) 0.6);
			//res = waitForElementPresent(AccountPageLocators.userName, "nameInputField");
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
			/*waitForElementPresent(HomePageLocators.reirieveBookingsMenuLink, "reirieveBookingsMenuLink");
			click(HomePageLocators.reirieveBookingsMenuLink, "reirieveBookingsMenuLink");*/
			(Iosdriver).tap(1, 109, 284, (int) 0.6);
			res = waitForElementPresent(RetrieveBookingsLocators.retrieveBookingsSearchButton,
					"retrieveBookingsSearchButton");
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
			(Iosdriver).tap(1, 110, 356, (int) 0.6);
			res = waitForElementPresent(AboutUsLocators.contactWithUsHeading,
					"contactWithUsHeading");
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public void validateUserLogin() throws Throwable{
		
		try{
			if(isElementDisplayed(HomePageLocators.closeWatchPopUp)){
				 click(HomePageLocators.closeWatchPopUp,"closeWatchPopUp");
			 }	
			/*if(isElementDisplayed(HomePageLocators.instayPop)){
				click(HomePageLocators.instayPop, "instayPop");
			}*/
			navigateToMyAccount();
			Thread.sleep(2000);
			if(isElementDisplayed(AccountPageLocators.sinOutButton)){					  
				  waitForElementPresent(AccountPageLocators.sinOutButton, 
						  "sinOutButton");
				  click(AccountPageLocators.sinOutButton, "sinOutButton");
				  Thread.sleep(2000);
			  }
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
}
