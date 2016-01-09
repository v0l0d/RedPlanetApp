package com.androidMobile.scripts;

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

public class RP_004_TestGuestBookingFromHome extends LoginHelper {

    private static final String NOT_FULL_DATA = "Validate booking of a Hotel as Guest from Home screen with blank details";
    private static final String INVALID_DATA = "Validate booking of a Hotel as Guest from Home screen with Invalid details";
    private static final String VALID_DATA = "Validate booking of a Hotel as Guest from Home screen with Valid details";

    ExcelReader xlsGuestBook = new ExcelReader(configProps.getProperty("AndroidTestData"), "RP_ANDR_004");

    @Test(dataProvider = "testData")
    public void testGuestBookingFromHome(String country, String city, String fName, String lName, String email,
                                         String cardHolder, String cardNum, String expMonth, String cvv, String description) throws Throwable {
        try {
            TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
            logOutAndGotToMainScreen();
            navigateToBookNow();
            handleRateAppPopUp();
            selectDestination(country, city);
            handleRateAppPopUp();
            waitForElementPresent(HomePageLocators.searchButton, "searchButton");
            click(HomePageLocators.searchButton, "searchButton");
            handleRateAppPopUp();
            if (waitForElementPresent(PickRoomPageLocators.pickRoomPage, "pickRoomPage")) {
                Reporter.SuccessReport("Search for Hotels", "Successful");
            } else {
                Reporter.failureReport("Search for Hotels", "Failed");
            }
            click(PickRoomPageLocators.bookNowButton, "bookNowButton");
            handleRateAppPopUp();
            waitForElementPresent(BookPageLocators.contiuneButton, "contiuneButton", 10);
            click(BookPageLocators.contiuneButton, "contiuneButton");
            handleRateAppPopUp();
            BookingPageHelper.populateGuestDetails("", fName, lName, email, "");
            BookingPageHelper.populatePaymentDetails(cardHolder, cardNum, expMonth, cvv);
            handleRateAppPopUp();
            if (description.equals(VALID_DATA)) {
                waitForElementPresent(BookPageLocators.doneButton, "doneButton", 10);
                if (isElementDisplayed(BookPageLocators.bookingCode)) {
                    String bookingCode = driver.findElement(BookPageLocators.bookingCode).getText().trim();
                    Reporter.SuccessReport(description, "Successful" + " Booking code is: " + bookingCode);
                } else if (isElementDisplayed(BookPageLocators.errorPayment)) {
                    Reporter.failureReport(description, "Failed to process payment, error message is: "
                            + driver.findElement(BookPageLocators.errorPayment).getText());
                    click(BookPageLocators.okButton, "okButton");
                } else {
                    Reporter.failureReport(description, "Failed");
                }
                click(BookPageLocators.doneButton, "doneButton");
            } else if (description.equals(INVALID_DATA)) {
                if (isElementDisplayed(BookPageLocators.errorPayment)) {
                    Reporter.SuccessReport(description, "Success error message "
                            + driver.findElement(BookPageLocators.errorPayment).getText());
                    click(BookPageLocators.okButton, "okButton");
                } else
                    Reporter.failureReport(description, "Failed");
            } else if (description.equals(NOT_FULL_DATA)) {
                if (isElementDisplayed(BookPageLocators.errorPayment)) {
                    Reporter.SuccessReport(description, "Success error message "
                            + driver.findElement(BookPageLocators.errorPayment).getText());
                    click(BookPageLocators.okButton, "okButton");
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
                {xlsGuestBook.getCellValue("country", "Value"), xlsGuestBook.getCellValue("city", "Value"),
                        xlsGuestBook.getCellValue("fName", "Value"), xlsGuestBook.getCellValue("lName", "Value"),
                        xlsGuestBook.getCellValue("email", "Value"), "",
                        "", xlsGuestBook.getCellValue("expirationmonth", "Value"),
                        xlsGuestBook.getCellValue("cvv", "Value"),
                        NOT_FULL_DATA},
                {xlsGuestBook.getCellValue("country", "Value"), xlsGuestBook.getCellValue("city", "Value"),
                        xlsGuestBook.getCellValue("fName", "Value"), xlsGuestBook.getCellValue("lName", "Value"),
                        xlsGuestBook.getCellValue("email", "Value"), xlsGuestBook.getCellValue("InValidcardHolder", "Value"),
                        xlsGuestBook.getCellValue("InValidCardNum", "Value"), xlsGuestBook.getCellValue("InExpirationmonth", "Value"),
                        xlsGuestBook.getCellValue("InValidcvv", "Value"),
                        INVALID_DATA},
                {xlsGuestBook.getCellValue("country", "Value"), xlsGuestBook.getCellValue("city", "Value"),
                        xlsGuestBook.getCellValue("fName", "Value"), xlsGuestBook.getCellValue("lName", "Value"),
                        xlsGuestBook.getCellValue("email", "Value"), xlsGuestBook.getCellValue("cardHolder", "Value"),
                        xlsGuestBook.getCellValue("cardNum", "Value"), xlsGuestBook.getCellValue("expirationmonth", "Value"),
                        xlsGuestBook.getCellValue("cvv", "Value"),
                        VALID_DATA}};
    }
}
