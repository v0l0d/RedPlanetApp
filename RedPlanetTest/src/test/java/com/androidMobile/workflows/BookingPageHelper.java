package com.androidMobile.workflows;

import com.androidMobile.scripts.testObjects.BookPageLocators;
import com.androidMobile.scripts.testObjects.LoginPageLocators;

import java.util.Random;

public class BookingPageHelper extends HomePageHelper {

    public static void populateGuestDetails(String title, String fName, String lName,
                                            String email, String phone) throws Throwable {
        String temp = "abcdefghijklmnopqrstABCDEFGHIJKLMNOP";
        int randomVal = new Random().nextInt(36);
        try {
            if (fName.length() > 0) {
                if (!isElementDisplayedTemp(BookPageLocators.firstNameInput)) {
                    scrollToText("First Name");
                }
                type(BookPageLocators.firstNameInput, fName + temp.charAt(randomVal), "firstNameInput");
            }

            if (lName.length() > 0) {
                if (!isElementDisplayedTemp(BookPageLocators.lastNameInput)) {
                    scrollToText("Last Name");
                }
                type(BookPageLocators.lastNameInput, lName + temp.charAt(randomVal), "lastNameInput");
            }

            if (email.length() > 0) {
                if (!isElementDisplayedTemp(BookPageLocators.EmailInput)) {
                    scrollToText("Email");
                }
                type(BookPageLocators.EmailInput, email, "EmailInput");
            }
        } catch (Exception e) {
            e.printStackTrace();
            click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");
        }
    }

    public static void populatePaymentDetails(String cardHolder, String cardNum,
                                              String expMonth, String cvv) throws Throwable {
        try {

            if (cardHolder.length() > 0) {
                if (!isElementDisplayedTemp(BookPageLocators.cardHolderInput)) {
                    scrollToText("Cardholder's Name");
                }
                type(BookPageLocators.cardHolderInput, cardHolder, "cardHolder");
            }
            if (cardNum.length() > 0) {
                if (!isElementDisplayedTemp(BookPageLocators.cardNumInput)) {
                    scrollToText("Card Number");
                }
                type(BookPageLocators.cardNumInput, cardNum, "cardNum");
            }
            if (expMonth.length() > 0) {
                if (!isElementDisplayedTemp(BookPageLocators.expMonthInput)) {
                    scrollToText("Expiration Month");
                }
                type(BookPageLocators.expMonthInput, expMonth, "expMonth");
            }
            if (cvv.length() > 0) {
                if (!isElementDisplayedTemp(BookPageLocators.cvvNumInput)) {
                    scrollToText("CVC");
                }
                type(BookPageLocators.cvvNumInput, cvv, "cvvNumInput");
            }

            if (!isElementDisplayedTemp(BookPageLocators.bookButton)) {
                scrollToText("BOOK");
            }
            click(BookPageLocators.conditionsCheck, "conditionsCheck");
            click(BookPageLocators.bookButton, "bookButton");
        } catch (Exception e) {
            e.printStackTrace();
            click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");
        }
    }
}
