package com.androidMobile.workflows;

import com.androidMobile.scripts.testObjects.AccountPageLocators;
import com.androidMobile.scripts.testObjects.HomePageLocators;
import com.androidMobile.scripts.testObjects.LoginPageLocators;
import com.ctaf.utilities.Reporter;

public class LoginHelper extends HomePageHelper {

    //Login with credentials
    public boolean login(String email, String password) throws Throwable {
        boolean res = false;
        try {
            waitForElementPresent(LoginPageLocators.emailFieldForLogin,
                    "emailField", 10);
            type(LoginPageLocators.emailFieldForLogin, email, "emailField");
            waitForElementPresent(LoginPageLocators.passwordFieldForLogin,
                    "passwordFieldForLogin");
            driver.findElement(LoginPageLocators.passwordFieldForLogin).clear();
            type(LoginPageLocators.passwordFieldForLogin, password,
                    "passwordFieldForLogin");
            waitForElementPresent(LoginPageLocators.signInButton,
                    "signInButton");
            click(LoginPageLocators.signInButton, "signInButton");
            Thread.sleep(3000);
            handleRateAppPopUp();
            handleSplashDialog();
            if (waitForElementPresent(LoginPageLocators.nameInputField, "nameInputField")) {
                /*res = waitForElementPresent(AccountPageLocators.accountScreenTitle,
						"accountScreenTitle");*/
                res = true;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");
            return false;
        }
        return res;
    }

    //Login for errorpop
    public boolean loginforErrorPopup(String email, String password) throws Throwable {
        boolean res = false;
        try {
            waitForElementPresent(LoginPageLocators.emailFieldForLogin,
                    "emailField");
            type(LoginPageLocators.emailFieldForLogin, email, "emailField");
//				waitForElementPresent(LoginPageLocators.passwordFieldForLogin,
//						"passwordFieldForLogin");
            type(LoginPageLocators.passwordFieldForLogin, password,
                    "passwordFieldForLogin");
//				waitForElementPresent(LoginPageLocators.signInButton,
//						"signInButton");
            click(LoginPageLocators.signInButton, "signInButton");
            handleRateAppPopUp();
            waitForElementPresent(LoginPageLocators.okayButtonOnErrorpop,
                    "okayButtonOnErrorpop", 20);
            click(LoginPageLocators.okayButtonOnErrorpop, "OKAY");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        System.out.println(res);
        return res;
    }

    //Login with credentials
    public boolean userlogin(String email, String password) throws Throwable {
        try {
            waitForElementPresent(LoginPageLocators.emailFieldForLogin,
                    "emailField");
            type(LoginPageLocators.emailFieldForLogin, email, "emailField");
            waitForElementPresent(LoginPageLocators.passwordFieldForLogin,
                    "passwordFieldForLogin");
            driver.findElement(LoginPageLocators.passwordFieldForLogin).clear();
            type(LoginPageLocators.passwordFieldForLogin, password,
                    "passwordFieldForLogin");
            waitForElementPresent(LoginPageLocators.signInButton,
                    "signInButton");
            click(LoginPageLocators.signInButton, "signInButton");
            handleRateAppPopUp();
        } catch (Exception e) {
            //e.printStackTrace();
            if (isElementDisplayed(LoginPageLocators.instayPopupClose)) {
                click(LoginPageLocators.instayPopupClose, "instayPopupClose");
            }
            click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");
            return false;
        }
        return true;
    }

    //Forgot password
    public boolean forgotPassword(String email) throws Throwable {
        boolean res = false;
        try {
            waitForElementPresent(LoginPageLocators.forgotPasswordLink,
                    "forgotPasswordLink");
            click(LoginPageLocators.forgotPasswordLink, "forgotPasswordLink");
            if (waitForElementPresent(LoginPageLocators.forgotPasswordframe,
                    "forgotPasswordframe")) {
                if (email.length() > 0) {
                    type(LoginPageLocators.emailFieldForForgotPswd, email,
                            "emailFieldForForgotPswd");
                    click(LoginPageLocators.resetButton, "resetButton");
                    Thread.sleep(2000);
                    if (!waitForElementPresent(LoginPageLocators.errorPop, "errorPop")) {
                        res = true;
                    } else {
                        Reporter.failureReport("Verify forgot password",
                                "Invalid email or left blank to reset the password");
                    }
                } else {
                    click(LoginPageLocators.cancelButton, "cancelButton");
                    res = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return res;
    }

    public boolean logOut() throws Throwable {
        boolean res = false;
        try {
            if (!(isElementDisplayedTemp(AccountPageLocators.logInButton))) {
                System.out.println("User logged in");
                scrollToText("SIGN OUT");
                waitForElementPresent(AccountPageLocators.signOutButton, "sinOutButton");
                click(AccountPageLocators.signOutButton, "sinOutButton");
//                Thread.sleep(2000);
            }
            if (waitForElementPresent(AccountPageLocators.logInButton, "logInButton")) {
                System.out.println("User logged out successfully");
                res = true;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");
            return false;
        }
        return res;
    }

    //TODO rename and replace(maybe) this method
    protected void logOutAndGotToMainScreen() throws Throwable {
        handleSplashDialog();
        while (!isElementDisplayedTemp(HomePageLocators.mainMenuIcon)) {
            driver.navigate().back();
        }
        navigateToMyAccount();
        handleRateAppPopUp();
        logOut();

    }
}
