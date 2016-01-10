package com.androidMobile.scripts;

import com.androidMobile.scripts.testObjects.*;
import com.androidMobile.workflows.BookingPageHelper;
import com.androidMobile.workflows.LoginHelper;
import com.androidMobile.workflows.RetrieveBookingPageHelper;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RP_008_RetriveBookingAsMember extends LoginHelper {

    ExcelReader xlsRetrive = new ExcelReader(configProps.getProperty("AndroidTestData"),"RP_ANDR_008");

    @Test(dataProvider = "testData")
    public void retriveBookingAsMember(String userId, String password, String country,
                                       String city, String fName, String lName, String email, String cardHolder,
                                       String cardNum, String expMonthYear, String cvv, String description) throws Throwable {
        String bookingCode = null;
        try {
            TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
            logOutAndGotToMainScreen();
            navigateToMyAccount();
            click(AccountPageLocators.logInButton, "logInButton");
            login(userId, password);
            navigateToBookNow();
            selectDestination(country, city);
            handleRateAppPopUp();
            waitForElementPresent(HomePageLocators.searchButton, "searchButton");
            click(HomePageLocators.searchButton, "searchButton");
            handleRateAppPopUp();
            Reporter.createReport("Search for Hotels", isElementDisplayed(PickRoomPageLocators.pickRoomPage));
            waitForElementPresent(PickRoomPageLocators.bookNowButton, "bookNowButton");
            click(PickRoomPageLocators.bookNowButton, "bookNowButton");
            handleRateAppPopUp();
            if (isElementDisplayed(BookPageLocators.contiuneButton)) {
                click(BookPageLocators.contiuneButton, "contiuneButton");
            }
            handleRateAppPopUp();
            BookingPageHelper.populateGuestDetails("", fName, lName, email, "");
            BookingPageHelper.populatePaymentDetails(cardHolder, cardNum, expMonthYear, cvv);
            waitForElementPresent(BookPageLocators.doneButton, "doneButton", 10);
            if (isElementDisplayed(BookPageLocators.bookingCode)) {
                String temp = getText(BookPageLocators.bookingCode, "bookingCode");
                bookingCode = temp.trim();
                logger.info("bookingCode " + bookingCode);
                Reporter.SuccessReport(description, "Successful" + " Booking code is: " + bookingCode);
            } else if (isElementDisplayed(BookPageLocators.errorPayment)) {
                Reporter.failureReport(description, "Failed to process payment, error message is: "
                        + driver.findElement(BookPageLocators.errorPayment).getText());
                click(BookPageLocators.okButton, "okButton");
            } else {
                Reporter.failureReport(description, "Failed");
            }
            click(BookPageLocators.doneButton, "doneButton");
            if (isElementDisplayed(BookPageLocators.doneButton)) {
                click(BookPageLocators.doneButton, "doneButton");
            }
            navigateToReirieveBookings();
            RetrieveBookingPageHelper.RetrieveBooking(email, bookingCode);
            if (isElementDisplayed(RetrieveBookingLocators.bookingDetailView)) {
                Reporter.SuccessReport(description, "Successful and booking details retrived");
            } else
                Reporter.failureReport(description, "Failed");
        } catch (Exception e) {
            e.printStackTrace();
            Reporter.failureReport(description, "Failed with exception");
        }
    }

    @DataProvider(name = "testData")
    public Object[][] createData() {
        return new Object[][]{
                {xlsRetrive.getCellValue("ValidUser", "Value"), xlsRetrive.getCellValue("ValidUser", "password"),
                        xlsRetrive.getCellValue("country", "Value"), xlsRetrive.getCellValue("city", "Value"),
                        xlsRetrive.getCellValue("fName", "Value"), xlsRetrive.getCellValue("lName", "Value"),
                        xlsRetrive.getCellValue("email", "Value"), xlsRetrive.getCellValue("cardHolder", "Value"),
                        xlsRetrive.getCellValue("cardNum", "Value"), xlsRetrive.getCellValue("expirationMonth", "Value"),
                        xlsRetrive.getCellValue("cvv", "Value"), "Validate Retrive Booking Details"}};
    }
}
