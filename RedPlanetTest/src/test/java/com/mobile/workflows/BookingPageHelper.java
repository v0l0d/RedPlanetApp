package com.mobile.workflows;

import com.ctaf.support.ReportStampSupport;
import com.mobile.scripts.testObjects.BookPageLocators;

public class BookingPageHelper extends HomePageHelper {	
	

	public static void populateGuestDetails(String title,String fName, String lName,
			String email,String phone) throws Throwable{	
		String randCharList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int rand1 = ReportStampSupport.biRandomValue();
		int rand2 = ReportStampSupport.biRandomValue();
		String randGen = Character.toString(randCharList.charAt(rand1)).concat(
				Character.toString(randCharList.charAt(rand2)));
		System.out.println("rand generated value "+randGen);
		try {
			waitForElementPresent(BookPageLocators.firstNameInput,
					"firstNameInput");
			if(fName.length()>0){
			type(BookPageLocators.firstNameInput, fName+randGen, "firstNameInput");
			}
			waitForElementPresent(BookPageLocators.lastNameInput,
					"lastNameInput");
			if(lName.length()>0){
			type(BookPageLocators.lastNameInput, lName+randGen,"lastNameInput");
			}
			waitForElementPresent(BookPageLocators.EmailInput,
					"EmailInput");
			if(email.length()>0){
			type(BookPageLocators.EmailInput, email, "EmailInput");		
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		
	}
	
	public static void populatePaymentDetails(String cardHolder,String cardNum, 
			String expMonth,String expYear, String cvv) throws Throwable{		
		try {
			//scrollToText("Cardholder");
			waitForElementPresent(BookPageLocators.cardHolderNameInput,"cardHolder");
			if(cardHolder.length()>0){
			type(BookPageLocators.cardHolderNameInput, cardHolder, "cardHolder");
			}
			waitForElementPresent(BookPageLocators.cardNumInput,"cardNum");
			if(cardNum.length()>0){
			type(BookPageLocators.cardNumInput, cardNum,"cardNum");
			}
			waitForElementPresent(BookPageLocators.expMonthInput,"expMonthInput");
			if(expMonth.length()>0){
			type(BookPageLocators.expMonthInput, expMonth, "expMonth");
			}
			waitForElementPresent(BookPageLocators.expYearInput,"expYearInput");
			if(expYear.length()>0){
			type(BookPageLocators.expYearInput, expYear, "expYear");	
			}
			waitForElementPresent(BookPageLocators.cvvNumInput,"cvvNumInput");
			if(cvv.length()>0){
			type(BookPageLocators.cvvNumInput, cvv, "cvvNumInput");
			}
			Iosdriver.hideKeyboard();
			waitForElementPresent(BookPageLocators.conditionsCheck, "conditionsCheck");
			click(BookPageLocators.conditionsCheck, "conditionsCheck");
			Iosdriver.hideKeyboard();
			waitForElementPresent(BookPageLocators.bookButton, "bookButton");
			click(BookPageLocators.bookButton, "bookButton");
		}catch(Exception e){
			e.printStackTrace();
			//click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");			
		}		
		
	}
	
	
}
