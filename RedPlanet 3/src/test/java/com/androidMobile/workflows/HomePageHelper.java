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
		} catch (Exception e) {
			
		}
	}
	
	
	public static boolean selectDestination(String country, String city) throws Throwable{
		boolean res = false;
		try {
			  //handleSplashDialog();
			waitForElementPresent(HomePageLocators.chooseLocation, "chooseLocation");
			click(HomePageLocators.chooseLocation, "chooseLocation");
			waitForElementPresent(By.xpath(HomePageLocators.locationCountryName.replace("#", country.trim())),
					"locationCountryName");
			click(By.xpath(HomePageLocators.locationCountryName.replace("#", country.trim())),
					"locationCountryName");
			waitForElementPresent(By.xpath(HomePageLocators.locationCityName.replace("#", city.trim())),
					"locationCityName");
			click(By.xpath(HomePageLocators.locationCityName.replace("#", city.trim())), "locationCityName");
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
			waitForElementPresent(HomePageLocators.bookNowMenuLink, "bookNowMenuLink");
			click(HomePageLocators.bookNowMenuLink, "bookNowMenuLink");
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
			waitForElementPresent(HomePageLocators.bookNowMenuLink, "bookNowMenuLink");
			click(HomePageLocators.bookNowMenuLink, "bookNowMenuLink");
			handleRateAppPopUp();
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
			waitForElementPresent(HomePageLocators.myAccountMenuLink, "myAccountMenuLink");
			click(HomePageLocators.myAccountMenuLink, "myAccountMenuLink");
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
			waitForElementPresent(HomePageLocators.reirieveBookingsMenuLink, "reirieveBookingsMenuLink");
			click(HomePageLocators.reirieveBookingsMenuLink, "reirieveBookingsMenuLink");
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
			waitForElementPresent(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			click(HomePageLocators.mainMenuIcon,"mainMenuIcon");
			waitForElementPresent(HomePageLocators.aboutUsMenuLink, "aboutUsMenuLink");
			click(HomePageLocators.aboutUsMenuLink, "aboutUsMenuLink");
			res = waitForElementPresent(AboutUsLocators.AboutUsTitle,
					"AboutUsTitle");
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
}
