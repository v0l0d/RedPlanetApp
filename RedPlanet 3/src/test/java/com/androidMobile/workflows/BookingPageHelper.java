package com.androidMobile.workflows;

import com.androidMobile.scripts.testObjects.BookPageLocators;
import com.androidMobile.scripts.testObjects.LoginPageLocators;
import com.ctaf.support.ReportStampSupport;

public class BookingPageHelper extends HomePageHelper {	
	

	public static void populateGuestDetails(String title,String fName, String lName,
			String email,String phone) throws Throwable{	
		String temp = "abcdefghijklmnopqrstABCDEFGHIJKLMNOP";
		int randomval = ReportStampSupport.biRandomValue();
		try {
			waitForElementPresent(BookPageLocators.guestDetailsFrame,
					"guestDetailsFrame");
			scrollToText("Email");
			Thread.sleep(2000);
			waitForElementPresent(BookPageLocators.firstNameInput,"firstNameInput");
			type(BookPageLocators.firstNameInput, fName+temp.charAt(randomval), "firstNameInput");						
			Thread.sleep(2000);
			waitForElementPresent(BookPageLocators.lastNameInput,"lastNameInput");
			type(BookPageLocators.lastNameInput, lName+temp.charAt(randomval),"lastNameInput");
			Thread.sleep(2000);
			waitForElementPresent(BookPageLocators.EmailInput,"EmailInput");
			type(BookPageLocators.EmailInput, email, "EmailInput");									
		}catch(Exception e){
			e.printStackTrace();
			click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");			
		}		
		
	}
	
	public static boolean verifyGuestDetailsAsMember(String title,String fName, String lName,
			String email,String phone) throws Throwable{	
		boolean result = false;	
		int count = 0;
		try {
			waitForElementPresent(BookPageLocators.guestDetailsFrame,"guestDetailsFrame");
			//scrollToText("Email");
			if(isTextPresent(fName)/*getAttribute(BookPageLocators.firstNameInput,"text","firstNameInput").contains(fName)*/)						
			{
				count++;
			}
			//scrollToText(lName);
			//waitForElementPresent(BookPageLocators.lastNameInput,"lastNameInput");
			/*if(isTextPresent(lName)getAttribute(BookPageLocators.lastNameInput, "text","lastNameInput").contains(lName)){
				count++;
			}*/
			/*waitForElementPresent(BookPageLocators.EmailInput,"EmailInput");
			if(isTextPresent(email)getAttribute(BookPageLocators.EmailInput, "text" , "EmailInput").contains(email)){
				count++;
			}*/
			if(count==1){
				result = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");			
		}		
		return result;
	}
	
	public static void populatePaymentDetails(String cardHolder,String cardNum, 
			String expMonth,String cvv) throws Throwable{		
		try {
			scrollToText("Cardholder's Name");	
			waitForElementPresent(BookPageLocators.cardHolderInput,"cardHolder");
			Thread.sleep(2000);
			type(BookPageLocators.cardHolderInput, cardHolder, "cardHolder");	
			waitForElementPresent(BookPageLocators.cardNumInput,"cardNum");
			Thread.sleep(2000);
			type(BookPageLocators.cardNumInput, cardNum,"cardNum");
			waitForElementPresent(BookPageLocators.expMonthInput,"expMonthInput");
			Thread.sleep(2000);
			type(BookPageLocators.expMonthInput, expMonth, "expMonth");	
			waitForElementPresent(BookPageLocators.cvvNumInput,"cvvNumInput");
			Thread.sleep(2000);
			type(BookPageLocators.cvvNumInput, cvv, "cvvNumInput");
			scrollToText("Book");
			click(BookPageLocators.conditionsCheck, "conditionsCheck");
			click(BookPageLocators.bookButton, "bookButton");
		}catch(Exception e){
			e.printStackTrace();
			click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");			
		}		
		
	}
	
	
}
