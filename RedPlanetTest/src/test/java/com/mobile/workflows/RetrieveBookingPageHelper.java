package com.mobile.workflows;


import com.mobile.scripts.testObjects.RetrieveBookingLocators;

public class RetrieveBookingPageHelper extends HomePageHelper {	
	

	public static void RetrieveBooking(String email,String bookingCode) throws Throwable{		
		try {
			waitForElementPresent(RetrieveBookingLocators.emailInput,
					"emailInput");			
			type(RetrieveBookingLocators.emailInput,email,
					"emailInput");						
			type(RetrieveBookingLocators.bookingCodeInput, bookingCode,"bookingCodeInput");
			Iosdriver.hideKeyboard();
			click(RetrieveBookingLocators.submitButton, "submitButton");
			
		}catch(Exception e){
			e.printStackTrace();						
		}		
		
	}		
}
