package com.mobile.workflows;

import com.ctaf.support.ReportStampSupport;
import com.mobile.scripts.testObjects.BookPageLocators;
import com.mobile.scripts.testObjects.LoginPageLocators;

public class BookingPageHelper extends HomePageHelper {	
	

	public static void populateGuestDetails(String title,String fName, String lName,
			String email,String phone) throws Throwable{	
		String randCharList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int rand = ReportStampSupport.biRandomValue();
		String randGen = Character.toString(randCharList.charAt(rand+rand));
		System.out.println("rand generated value "+randGen);
		try {
			waitForElementPresent(BookPageLocators.guestDetailsFrame,
					"guestDetailsFrame");
			//scrollToElement(locator)
			waitForElementPresent(BookPageLocators.firstNameInput,
					"firstNameInput");
			type(BookPageLocators.firstNameInput, fName+randGen, "firstNameInput");
			waitForElementPresent(BookPageLocators.lastNameInput,
					"lastNameInput");
			type(BookPageLocators.lastNameInput, lName+randGen,"lastNameInput");
			waitForElementPresent(BookPageLocators.EmailInput,
					"EmailInput");
			type(BookPageLocators.EmailInput, email, "EmailInput");									
		}catch(Exception e){
			e.printStackTrace();
			click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");			
		}		
		
	}
	
	public static void populatePaymentDetails(String cardHolder,String cardNum, 
			String expMonth,String expYear, String cvv) throws Throwable{		
		try {
			//scrollToText("Cardholder");
			//scrollToElement("//*[contains(@value,'Cardholder')]");	
			waitForElementPresent(BookPageLocators.cardHolderNameInput,"cardHolder");
			Thread.sleep(1000);
			type(BookPageLocators.cardHolderNameInput, cardHolder, "cardHolder");
			waitForElementPresent(BookPageLocators.cardNumInput,"cardNum");
			Thread.sleep(1000);
			type(BookPageLocators.cardNumInput, cardNum,"cardNum");
			//scrollToElement("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableGroup[3]/UIAButton[1]");
			waitForElementPresent(BookPageLocators.expMonthInput,"expMonthInput");
			type(BookPageLocators.expMonthInput, expMonth, "expMonth");
			waitForElementPresent(BookPageLocators.expYearInput,"expYearInput");
			type(BookPageLocators.expYearInput, expYear, "expYear");	
			waitForElementPresent(BookPageLocators.cvvNumInput,"cvvNumInput");
			Thread.sleep(1000);
			type(BookPageLocators.cvvNumInput, "123", "cvvNumInput");
			Thread.sleep(6000);
			//Iosdriver.hideKeyboard();
			waitForElementPresent(BookPageLocators.conditionsCheck, "conditionsCheck");
			click(BookPageLocators.conditionsCheck, "conditionsCheck");
			waitForElementPresent(BookPageLocators.bookButton, "bookButton");
			click(BookPageLocators.bookButton, "bookButton");
		}catch(Exception e){
			e.printStackTrace();
			//click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");			
		}		
		
	}
	
	
}
