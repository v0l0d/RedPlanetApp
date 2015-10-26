package com.mobile.workflows;


import com.mobile.scripts.testObjects.RetrieveBookingLocators;
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
			click(RetrieveBookingLocators.searchButton, "searchButton");
			
		}catch(Exception e){
			e.printStackTrace();						
		}		
		
	}		
}
