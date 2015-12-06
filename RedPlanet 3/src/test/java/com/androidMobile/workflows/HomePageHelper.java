package com.androidMobile.workflows;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.androidMobile.scripts.testObjects.AboutUsLocators;
import com.androidMobile.scripts.testObjects.HomePageLocators;
import com.androidMobile.scripts.testObjects.LoginPageLocators;
import com.androidMobile.scripts.testObjects.RetrieveBookingLocators;


public class HomePageHelper extends HomePageLocators {	

	
	public static boolean handleSplashDialog() throws Throwable{
		boolean res = false;
		try {
			//waitForElementPresent(HomePageLocators.redPlanetMainFrame,"redPlanetMainFrame");
			if(isElementDisplayedTemp(HomePageLocators.closeWatchPopUp)){
				click(HomePageLocators.closeWatchPopUp,"closeWatchPopUp");
			}
			if(isElementDisplayedTemp(LoginPageLocators.instayPopupClose)){
				click(LoginPageLocators.instayPopupClose, "instayPopupClose");
			}
			if(isElementDisplayedTemp(LoginPageLocators.addPopupClose)){
				click(LoginPageLocators.addPopupClose, "addPopupClose");
			}
			Thread.sleep(1000);
			res = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static void handleRateAppPopUp() throws Throwable{
		try {
			
			//driver.findElement(loc).isDisplayed()
			if(isElementDisplayedTemp(HomePageLocators.rateAppPopUp)){
				click(HomePageLocators.closeRateAppPopUp,"CloseRateAppPopUp");
			}
			if(isElementDisplayedTemp(HomePageLocators.closeWatchPopUp)){
				click(HomePageLocators.closeWatchPopUp,"closeWatchPopUp");
			}
			if(isElementDisplayedTemp(LoginPageLocators.instayPopupClose)){
				click(LoginPageLocators.instayPopupClose, "instayPopupClose");
			}
			if(isElementDisplayedTemp(LoginPageLocators.addPopupClose)){
				click(LoginPageLocators.addPopupClose, "addPopupClose");
			}
		} catch (Exception e) {
			
		}
	}
	
	
	public static boolean selectDestination(String country, String city) throws Throwable{
		boolean res = false;
		try {
			  //handleSplashDialog();
			waitForElementPresent(HomePageLocators.chooseLocation, "chooseLocation");
			click(HomePageLocators.chooseLocation, "chooseLocation");
			Thread.sleep(2000);
			waitForElementPresent(By.xpath(HomePageLocators.locationCountryName.replace("#", country.trim())),
					"locationCountryName");
			click(By.xpath(HomePageLocators.locationCountryName.replace("#", country.trim())),
					"locationCountryName");
			Thread.sleep(2000);
			waitForElementPresent(By.xpath(HomePageLocators.locationCityName.replace("#", city.trim())),
					"locationCityName");
			click(By.xpath(HomePageLocators.locationCityName.replace("#", city.trim())), "locationCityName");
			Thread.sleep(2000);
			handleRateAppPopUp();
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
			Thread.sleep(2000);
			waitForElementPresent(HomePageLocators.bookNowMenuLink, "bookNowMenuLink");
			click(HomePageLocators.bookNowMenuLink, "bookNowMenuLink");
			Thread.sleep(2000);
			handleRateAppPopUp();
			res = waitForElementPresent(HomePageLocators.chooseLocation, "chooseLocation");
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
			Thread.sleep(2000);
			waitForElementPresent(HomePageLocators.homeMenuLink, "homeMenuLink");
			click(HomePageLocators.homeMenuLink, "homeMenuLink");
			Thread.sleep(2000);
			handleRateAppPopUp();
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
			Thread.sleep(2000);
			waitForElementPresent(HomePageLocators.myAccountMenuLink, "myAccountMenuLink");
			click(HomePageLocators.myAccountMenuLink, "myAccountMenuLink");
			Thread.sleep(2000);
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
			Thread.sleep(2000);
			waitForElementPresent(HomePageLocators.reirieveBookingsMenuLink, "reirieveBookingsMenuLink");
			click(HomePageLocators.reirieveBookingsMenuLink, "reirieveBookingsMenuLink");
			Thread.sleep(2000);
			res = waitForElementPresent(RetrieveBookingLocators.searchButton,
					"retrieveBookingsSearchButton");
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	public boolean navigateToAboutUs() throws Throwable{
		boolean res = false;
		try {
			handleRateAppPopUp();
			waitForElementPresent(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			click(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			Thread.sleep(2000);
			waitForElementPresent(HomePageLocators.aboutUsMenuLink, "aboutUsMenuLink");
			click(HomePageLocators.aboutUsMenuLink, "aboutUsMenuLink");
			handleRateAppPopUp();
			 handleSplashDialog();
			Thread.sleep(2000);
			res = waitForElementPresent(AboutUsLocators.AboutUsTitle,
					"AboutUsTitle");
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
}
