package com.androidMobile.workflows;

import com.androidMobile.scripts.testObjects.BookPageLocators;
import com.androidMobile.scripts.testObjects.LoginPageLocators;
import com.ctaf.support.ReportStampSupport;

import java.util.Random;

public class BookingPageHelper extends HomePageHelper {	
	

	public static void populateGuestDetails(String title,String fName, String lName,
			String email,String phone) throws Throwable{	
		String temp = "abcdefghijklmnopqrstABCDEFGHIJKLMNOP";
		int randomVal = new Random().nextInt(36);
		try {
            //TODO[andrey]: recheck this
            scrollToText("First Name");
			if(fName.length()>0){
//                scrollToText("First Name");
//                waitForElementPresent(BookPageLocators.firstNameInput,"firstNameInput");
                type(BookPageLocators.firstNameInput, fName+temp.charAt(randomVal), "firstNameInput");
//                driver.navigate().back();
            }

			if(lName.length()>0){
//                scrollToText("Last Name");
//                waitForElementPresent(BookPageLocators.lastNameInput,"lastNameInput");
			    type(BookPageLocators.lastNameInput, lName+temp.charAt(randomVal),"lastNameInput");
//                driver.navigate().back();
            }

			if(email.length()>0) {
//                scrollToElement("Email");
//                waitForElementPresent(BookPageLocators.EmailInput,"EmailInput");
                type(BookPageLocators.EmailInput, email, "EmailInput");
//                driver.navigate().back();
			}
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

//            scrollToText("Cardholder's Name");
            if(cardHolder.length()>0){
                scrollToText("Cardholder's Name");
//                waitForElementPresent(BookPageLocators.cardNumInput,"cardNum");
    			type(BookPageLocators.cardHolderInput, cardHolder, "cardHolder");
//                driver.navigate().back();
            }
			if(cardNum.length()>0){
                scrollToText("Card Number");
//                waitForElementPresent(BookPageLocators.cardNumInput,"cardNumInput");
                type(BookPageLocators.cardNumInput, cardNum,"cardNum");
//                driver.navigate().back();
            }
			if(expMonth.length()>0){
                scrollToText("Expiration Month");
//                waitForElementPresent(BookPageLocators.expMonthInput,"expMonthInput");
                type(BookPageLocators.expMonthInput, expMonth, "expMonth");
//                driver.navigate().back();
            }
			if(cvv.length()>0){
//                scrollToText("Book");
                waitForElementPresent(BookPageLocators.cvvNumInput,"cvvNumInput");
                type(BookPageLocators.cvvNumInput, cvv, "cvvNumInput");
//                driver.navigate().back();
			}

			click(BookPageLocators.conditionsCheck, "conditionsCheck");
			click(BookPageLocators.bookButton, "bookButton");
		}catch(Exception e){
			e.printStackTrace();
			click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");			
		}		
	}
	
	
}
