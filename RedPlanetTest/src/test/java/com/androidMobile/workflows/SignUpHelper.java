package com.androidMobile.workflows;

import com.androidMobile.scripts.testObjects.SignUpEmailLocators;
import org.testng.annotations.Test;

public class SignUpHelper extends SignUpEmailLocators {
    @Test
    public static boolean SignUp(String fName, String lName, String email,
                                 String password) throws Throwable {
        boolean res = false;
        try {
            waitForElementPresent(SignUpEmailLocators.firstnameInputField,
                    "firstnameInputField");
            type(SignUpEmailLocators.firstnameInputField, fName,
                    "firstnameInputField");
            type(SignUpEmailLocators.lastNameInputField, lName,
                    "lastNameInputField");
            if (!isElementDisplayedTemp(SignUpEmailLocators.emailInputField)) {
                scrollToText("EMAIL");
            }
            type(SignUpEmailLocators.emailInputField, email,
                    "emailInputField");
            if (!isElementDisplayedTemp(SignUpEmailLocators.passwordInputField)) {
                scrollToText("PASSWORD");
            }
            type(SignUpEmailLocators.passwordInputField, password,
                    "passwordInputField");
            if (!isElementDisplayedTemp(SignUpEmailLocators.verifyInputField)) {
                scrollToText("VERIFY");
            }
            type(SignUpEmailLocators.verifyInputField, password,
                    "verifyInputField");
            if (!isElementDisplayedTemp(SignUpEmailLocators.signUpButton)) {
                scrollToText("SIGN UP");
            }
            waitForElementPresent(SignUpEmailLocators.signUpButton, "signUpButton");
            click(SignUpEmailLocators.signUpButton, "signUpButton");
            if (isElementDisplayed(SignUpEmailLocators.editLink)) {
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static boolean UserSignUp(String fName, String lName, String email,
                                     String password) throws Throwable {
        boolean res = false;
        try {
            waitForElementPresent(SignUpEmailLocators.firstnameInputField,
                    "firstnameInputField");
            type(SignUpEmailLocators.firstnameInputField, fName,
                    "firstnameInputField");
            type(SignUpEmailLocators.lastNameInputField, lName,
                    "lastNameInputField");
            if (!isElementDisplayedTemp(SignUpEmailLocators.emailInputField)) {
                scrollToText("EMAIL");
            }
            type(SignUpEmailLocators.emailInputField, email,
                    "emailInputField");
            if (!isElementDisplayedTemp(SignUpEmailLocators.passwordInputField)) {
                scrollToText("PASSWORD");
            }
            type(SignUpEmailLocators.passwordInputField, password,
                    "passwordInputField");
            if (!isElementDisplayedTemp(SignUpEmailLocators.verifyInputField)) {
                scrollToText("VERIFY");
            }
            type(SignUpEmailLocators.verifyInputField, password,
                    "verifyInputField");
            if (!isElementDisplayedTemp(SignUpEmailLocators.signUpButton)) {
                scrollToText("SIGN UP");
            }
            waitForElementPresent(SignUpEmailLocators.signUpButton, "signUpButton");
            click(SignUpEmailLocators.signUpButton, "signUpButton");
//		  Thread.sleep(5000);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static boolean ModifyUserDetails(String fName, String lName, String email,
                                            String title) throws Throwable {
        boolean res = false;
        try {
            waitForElementPresent(SignUpEmailLocators.editLink,
                    "editLink");
            Thread.sleep(1000);
            click(SignUpEmailLocators.editLink, "editLink");
            waitForElementPresent(SignUpEmailLocators.firstnameInputField,
                    "firstnameInputField");
            Thread.sleep(2000);
            type(SignUpEmailLocators.firstnameInputField, fName,
                    "firstnameInputField");
            waitForElementPresent(SignUpEmailLocators.lastNameInputField,
                    "lastNameInputField");
            type(SignUpEmailLocators.lastNameInputField, lName,
                    "lastNameInputField");
            click(SignUpEmailLocators.doneButton, "doneButton");
            Thread.sleep(5000);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


}
