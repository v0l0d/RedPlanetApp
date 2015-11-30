package com.mobile.workflows;


import com.mobile.scripts.testObjects.RetrieveBookingLocators;

import java.awt.RenderingHints.Key;

import org.openqa.selenium.Keys;

import com.mobile.scripts.testObjects.LoginPageLocators;

public class RetrieveBookingPageHelper extends HomePageHelper {	
	

	public static void RetrieveBooking(String email,String bookingCode) throws Throwable{		
		try {
			waitForElementPresent(RetrieveBookingLocators.emailInput,
					"emailInput");			
			type(RetrieveBookingLocators.emailInput,email,
					"emailInput");						
			type(RetrieveBookingLocators.bookingCodeInput, bookingCode,
					"bookingCodeInput");
			//Iosdriver.hideKeyboard();
			Iosdriver.tap(1, driver.findElement(RetrieveBookingLocators.bookingCodeInput), (int) 0.6);
			//scrollToElement("//*[@name='SUBMIT']");
			//driver.findElement(RetrieveBookingLocators.bookingCodeInput).sendKeys( Keys.ENTER);
			Iosdriver.hideKeyboard();
			click(RetrieveBookingLocators.submitButton, "submitButton");
			
		}catch(Exception e){
			e.printStackTrace();						
		}		
		
	}		
}
