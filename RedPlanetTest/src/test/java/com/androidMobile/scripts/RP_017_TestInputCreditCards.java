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

public class RP_017_TestInputCreditCards extends LoginHelper {

    ExcelReader xlsCards = new ExcelReader(configProps.getProperty("AndroidTestData"), "RP_ANDR_017");

    @Test(dataProvider = "testData")
    public void testInputCreditCards(String country, String city,
                                     String fName, String lName, String email,
                                     String cardHolder, String cardNum, String expMonthYear, String cvv,
                                     String description) throws Throwable {
        System.out.println(country + " " + city);
        try {
            TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
            logOutAndGotToMainScreen();
            navigateToMyAccount();
            navigateToBookNow();
            selectDestination(country, city);
            waitForElementPresent(HomePageLocators.searchButton, "searchButton");
            click(HomePageLocators.searchButton, "searchButton");
            Reporter.createReport("Search for Hotels", isElementDisplayed(PickRoomPageLocators.pickRoomPage));
            click(PickRoomPageLocators.bookNowButton, "bookNowButton");
            waitForElementPresent(BookPageLocators.contiuneButton, "contiuneButton", 10);
            click(BookPageLocators.contiuneButton, "contiuneButton");
            BookingPageHelper.populateGuestDetails("", fName, lName, email, "");
            BookingPageHelper.populatePaymentDetails(cardHolder, cardNum, expMonthYear, cvv);
            handleRateAppPopUp();
            if (waitForElementPresent(BookPageLocators.bookingCode, "bookingCode", 20)) {
                String bookingCode = driver.findElement(BookPageLocators.bookingCode).getText().trim();
                Reporter.SuccessReport(description, "Successful" + " Booking code is: " + bookingCode);
            } else if (isElementDisplayed(BookPageLocators.errorPayment)) {
                Reporter.failureReport(description, "Failed to process payment, error message is: "
                        + driver.findElement(BookPageLocators.errorPayment).getText());
                click(BookPageLocators.okButton, "okButton");
            } else {
                Reporter.failureReport(description, "Failed");
                click(BookPageLocators.doneButton, "doneButton");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Reporter.failureReport(description, "Failed with exception");
        }
    }

    @DataProvider(name = "testData")
    public Object[][] createData() {
        return new Object[][]{
                {xlsCards.getCellValue("country", "Value"), xlsCards.getCellValue("city", "Value"),
                        xlsCards.getCellValue("fName", "Value"), xlsCards.getCellValue("lName", "Value"),
                        xlsCards.getCellValue("email", "Value"), xlsCards.getCellValue("VisaCardHolder", "Value"),
                        xlsCards.getCellValue("VisaCardNum", "Value"), xlsCards.getCellValue("VisaExpirationMonth", "Value")
                        , xlsCards.getCellValue("Visacvv", "Value"), "Validate Visa card payment"},
                {xlsCards.getCellValue("country", "Value"), xlsCards.getCellValue("city", "Value"),
                        xlsCards.getCellValue("fName", "Value"), xlsCards.getCellValue("lName", "Value"),
                        xlsCards.getCellValue("email", "Value"), xlsCards.getCellValue("MasterCardHolder", "Value"),
                        xlsCards.getCellValue("MasterCardNum", "Value"), xlsCards.getCellValue("MasterExpirationMonth", "Value"),
                        xlsCards.getCellValue("Mastercvv", "Value"),
                        "Validate Master card payment"},
                {xlsCards.getCellValue("country", "Value"), xlsCards.getCellValue("city", "Value"),
                        xlsCards.getCellValue("fName", "Value"), xlsCards.getCellValue("lName", "Value"),
                        xlsCards.getCellValue("email", "Value"), xlsCards.getCellValue("AMEXCardHolder", "Value"),
                        xlsCards.getCellValue("AMEXCardNum", "Value"), xlsCards.getCellValue("AMEXExpirationMonth", "Value"),
                        xlsCards.getCellValue("AMEXcvv", "Value"),
                        "Validate American Express card payment"},
//                {xlsCards.getCellValue("country", "Value"), xlsCards.getCellValue("city", "Value"),
//                        xlsCards.getCellValue("fName", "Value"), xlsCards.getCellValue("lName", "Value"),
//                        xlsCards.getCellValue("email", "Value"), xlsCards.getCellValue("ChinaUnionCardHolder", "Value"),
//                        xlsCards.getCellValue("ChinaUnionCardNum", "Value"), xlsCards.getCellValue("ChinaUnionExpirationMonth", "Value"),
//                        xlsCards.getCellValue("ChinaUnioncvv", "Value"),
//                        "Validate China Union card payment"}
        };//TODO china union card is wrong
    }
}
