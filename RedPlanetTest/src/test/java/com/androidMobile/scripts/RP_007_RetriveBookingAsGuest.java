package com.androidMobile.scripts;

import com.androidMobile.scripts.testObjects.BookPageLocators;
import com.androidMobile.scripts.testObjects.HomePageLocators;
import com.androidMobile.scripts.testObjects.PickRoomPageLocators;
import com.androidMobile.scripts.testObjects.RetrieveBookingLocators;
import com.androidMobile.workflows.BookingPageHelper;
import com.androidMobile.workflows.LoginHelper;
import com.androidMobile.workflows.RetrieveBookingPageHelper;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class RP_007_RetriveBookingAsGuest extends LoginHelper {

    ExcelReader xlsRetrive = new ExcelReader(configProps.getProperty("AndroidTestData"), "RP_ANDR_007");

    @Test(dataProvider = "testData")
    public void testRetriveBookingAsGuest(String country, String city, String fName, String lName, String email, String cardHolder,
                                          String cardNum, String expMonthYear, String cvv, String description) throws Throwable {
        String bookingCode = null;
        try {
            TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
            logOutAndGotToMainScreen();
            navigateToBookNow();
            handleRateAppPopUp();
            selectDestination(country, city);
            waitForElementPresent(HomePageLocators.searchButton, "searchButton");
            click(HomePageLocators.searchButton, "searchButton");
            handleRateAppPopUp();
            Reporter.createReport("Search for Hotels", isElementDisplayed(PickRoomPageLocators.pickRoomPage));
            waitForElementPresent(PickRoomPageLocators.bookNowButton, "bookNowButton");
            click(PickRoomPageLocators.bookNowButton, "bookNowButton");
            handleRateAppPopUp();
            waitForElementPresent(BookPageLocators.contiuneButton, "contiuneButton");
            click(BookPageLocators.contiuneButton, "contiuneButton");
            handleRateAppPopUp();
            BookingPageHelper.populateGuestDetails("", fName, lName, email, "");
            BookingPageHelper.populatePaymentDetails(cardHolder, cardNum, expMonthYear, cvv);
            handleRateAppPopUp();
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
                {xlsRetrive.getCellValue("country", "Value"), xlsRetrive.getCellValue("city", "Value"),
                        xlsRetrive.getCellValue("fName", "Value"), xlsRetrive.getCellValue("lName", "Value"),
                        xlsRetrive.getCellValue("email", "Value"), xlsRetrive.getCellValue("cardHolder", "Value"),
                        xlsRetrive.getCellValue("cardNum", "Value"), xlsRetrive.getCellValue("expirationmonth", "Value"),
                        xlsRetrive.getCellValue("cvv", "Value"), "Validate Retrive Booking As Guest"}};
    }
}
