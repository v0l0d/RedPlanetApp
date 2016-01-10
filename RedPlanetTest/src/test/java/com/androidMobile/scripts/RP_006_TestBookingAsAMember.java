package com.androidMobile.scripts;

import com.androidMobile.scripts.testObjects.AccountPageLocators;
import com.androidMobile.scripts.testObjects.BookPageLocators;
import com.androidMobile.scripts.testObjects.HomePageLocators;
import com.androidMobile.scripts.testObjects.PickRoomPageLocators;
import com.androidMobile.workflows.BookingPageHelper;
import com.androidMobile.workflows.LoginHelper;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RP_006_TestBookingAsAMember extends LoginHelper {

    ExcelReader xlsBook = new ExcelReader(configProps.getProperty("AndroidTestData"), "RP_ANDR_006");

    @Test(dataProvider = "testData")
    public void testGuestBookingAsAMember(String userId, String password,
                                          String country, String city, String fName, String lName, String email,
                                          String cardHolder, String cardNum, String expMonth, String cvv, boolean status, String description) throws Throwable {
        try {
            TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
            logOutAndGotToMainScreen();
            navigateToMyAccount();
            click(AccountPageLocators.logInButton, "logInButton");
            login(userId, password);
            navigateToBookNow();
            handleRateAppPopUp();
            selectDestination(country, city);
            handleRateAppPopUp();
            waitForElementPresent(HomePageLocators.searchButton, "searchButton");
            click(HomePageLocators.searchButton, "searchButton");
            handleRateAppPopUp();
            if (waitForElementPresent(PickRoomPageLocators.pickRoomPage, "pickRoomPage")) {
                Reporter.SuccessReport("Search for Hotels", "Successful");
            } else
                Reporter.failureReport("Search for Hotels", "Failed");
            click(PickRoomPageLocators.bookNowButton, "bookNowButton");
            handleRateAppPopUp();
            if (isElementDisplayedTemp(BookPageLocators.contiuneButton)) {
                click(BookPageLocators.contiuneButton, "contiuneButton");
            }
            /* if(isElementDisplayedTemp(BookPageLocators.iAMTheGuestRadio)){
                 click(BookPageLocators.iAMTheGuestRadio, "iAMTheGuestRadio");
             }*/
            BookingPageHelper.populateGuestDetails("", fName, lName, email, "");
            //click(BookPageLocators.guestRadioButton, "guestRadioButton");
            BookingPageHelper.populatePaymentDetails(cardHolder, cardNum, expMonth, cvv);
            handleRateAppPopUp();
            if (status) {
                waitForElementPresent(BookPageLocators.doneButton, "doneButton", 10);
                if (isElementDisplayed(BookPageLocators.bookingCode)) {
                    String bookingCode = driver.findElement(BookPageLocators.bookingCode).getText().trim();
                    Reporter.SuccessReport(description, "Successful" + " Booking code is: " + bookingCode);
                    String bookedCity = driver.findElement(BookPageLocators.bookedCity).getText().trim();
                    String bookingToDate = driver.findElement(BookPageLocators.bookingToDate).getText().trim();
                    String bookingFromDate = driver.findElement(BookPageLocators.bookingFromDate).getText().trim();
                    String bookedHotel = driver.findElement(BookPageLocators.bookedHotel).getText().trim();
                    String totalBookingCost = driver.findElement(BookPageLocators.totalBookingCost).getText().trim();
                    Reporter.SuccessReport("Validate Successful Booking ", " Booking City is: " + bookedCity);
                    Reporter.SuccessReport("Validate Successful Booking ", " Booking Hotel is: " + bookedHotel);
                    Reporter.SuccessReport("Validate Successful Booking ", " ToBookingDate  is: " + bookingToDate);
                    Reporter.SuccessReport("Validate Successful Booking ", " FromBookingDate  is: " + bookingFromDate);
                    Reporter.SuccessReport("Validate Successful Booking ", " Total Booking Cost is: " + totalBookingCost);
                    click(BookPageLocators.doneButton, "doneButton");
                    if (isElementDisplayed(BookPageLocators.doneButton)) {
                        click(BookPageLocators.doneButton, "doneButton");
                    }
                    navigateToMyAccount();
//                    Thread.sleep(6000);
                    //scrollToText("Upcoming");
                    //waitForElementPresent(AccountPageLocators.upcomingBookings, "upcomingBookings");
                    String newCity[] = bookedCity.split(" ");

                    System.out.println("City " + newCity);
                    String newCost = totalBookingCost.replace(",", "").trim();
                    System.out.println("Cost " + newCost);
//                    Thread.sleep(6000);
                    if (scrollToText(newCost)) {
                        Reporter.SuccessReport("Validate Booking details under My Account Screen ",
                                " Successfully validated Booking details City : " + bookedCity + " Hotel "
                                        + bookedHotel + " To Date " + bookingToDate + " From Date " + bookingFromDate + " and Total Cost "
                                        + totalBookingCost);
                    } else if (isElementDisplayed(BookPageLocators.errorPayment)) {
                        Reporter.failureReport(description, "Failed to process payment, error message is: "
                                + driver.findElement(BookPageLocators.errorPayment).getText());
                        click(BookPageLocators.okButton, "okButton");
                    } else if (isElementDisplayed(BookPageLocators.errorPayment)) {
                        Reporter.failureReport(description, "Failed to process payment, error message is: "
                                + driver.findElement(BookPageLocators.errorPayment).getText());
                        click(BookPageLocators.okButton, "okButton");
                    }/*else if(isElementDisplayed(BookPageLocators.doneButton))
			 Reporter.failureReport(description, "Failed");
			 click(BookPageLocators.doneButton, "doneButton");	*/
                }
            } else {

                if (isElementDisplayed(BookPageLocators.errorPayment)) {
                    Reporter.SuccessReport(description, "Success error message "
                            + driver.findElement(BookPageLocators.errorPayment).getText());
                } else
                    Reporter.failureReport(description, "Failed");

            }
        } catch (Exception e) {
            e.printStackTrace();
            Reporter.failureReport(description, "Failed with exception");
        }
    }

    @DataProvider(name = "testData")
    public Object[][] createData() {
        return new Object[][]{
                {xlsBook.getCellValue("ValidUser", "Value"), xlsBook.getCellValue("ValidUser", "password"),
                        xlsBook.getCellValue("country", "Value"), xlsBook.getCellValue("city", "Value"),
                        xlsBook.getCellValue("fName", "Value"), xlsBook.getCellValue("lName", "Value"),
                        xlsBook.getCellValue("email", "Value"), "",
                        "", xlsBook.getCellValue("expirationmonth", "Value"), xlsBook.getCellValue("cvv", "Value"),
                        false, "Validate booking of a Hotel as Member from Booking screen with blank details"},
                {xlsBook.getCellValue("ValidUser", "Value"), xlsBook.getCellValue("ValidUser", "password"),
                        xlsBook.getCellValue("country", "Value"), xlsBook.getCellValue("city", "Value"),
                        xlsBook.getCellValue("fName", "Value"), xlsBook.getCellValue("lName", "Value"),
                        xlsBook.getCellValue("email", "Value"), xlsBook.getCellValue("cardHolder", "Value"),
                        xlsBook.getCellValue("cardNum", "Value"), xlsBook.getCellValue("expirationmonth", "Value"),
                        xlsBook.getCellValue("cvv", "Value"), true, "Validate booking of a Hotel as Member from home screen"},
                {xlsBook.getCellValue("ValidUser", "Value"), xlsBook.getCellValue("ValidUser", "password"),
                        xlsBook.getCellValue("country", "Value"), xlsBook.getCellValue("city", "Value"),
                        xlsBook.getCellValue("fName", "Value"), xlsBook.getCellValue("lName", "Value"),
                        xlsBook.getCellValue("email", "Value"), xlsBook.getCellValue("InValidcardHolder", "Value"),
                        xlsBook.getCellValue("InValidCardNum", "Value"), xlsBook.getCellValue("InExpirationmonth", "Value"),
                        xlsBook.getCellValue("InValidcvv", "Value"),
                        false, "Validate booking of a Hotel as Member from Booking screen with Invalid details"},
        };
    }
}
