package com.androidMobile.scripts;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.androidMobile.scripts.testObjects.HomePageLocators;
import com.androidMobile.scripts.testObjects.PickRoomPageLocators;
import com.androidMobile.workflows.LoginHelper;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

public class RP_003_TestSearchHotels extends LoginHelper {

    private static final String VERIFY_SEARCH_HOTELS = "Verify Search Hotels";
    private static final String VERIFY_SELECT_DESTINATION = "Verify Select Destination";

    ExcelReader xlsSearch = new ExcelReader(configProps.getProperty("AndroidTestData"), "RP_ANDR_003");

    @Test(dataProvider = "testData")
    public void testSearchHotels(String country, String city, String description) throws Throwable {
        try {
            TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
            logOutAndGotToMainScreen();
            navigateToBookNow();
            handleRateAppPopUp();
            selectDestination(country, city);
            if (description.equals(VERIFY_SELECT_DESTINATION)) {
                if ((isElementPresent(By.xpath(HomePageLocators.locationCityName.replace("#", city.trim())),
                        "locationCityName")) &
                        (isElementPresent(By.xpath(HomePageLocators.locationCountryName.replace("#", country.trim())),
                                "locationCityName"))) {
                    Reporter.SuccessReport(description, "Successful");
                } else {
                    Reporter.failureReport(description, "Failed");
                }
            } else if (description.equals(VERIFY_SEARCH_HOTELS)) {
                waitForElementPresent(HomePageLocators.searchButton, "searchButton");
                click(HomePageLocators.searchButton, "searchButton");
                handleRateAppPopUp();
                if (isElementDisplayed(PickRoomPageLocators.pickRoomPage)) {
                    Reporter.SuccessReport(description, "Successful");
                } else {
                    Reporter.failureReport(description, "Failed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Reporter.failureReport(description, "Failed with exception");
        }
    }

    @DataProvider(name = "testData")
    public Object[][] createData() {
        return new Object[][]{
                {xlsSearch.getCellValue("country", "Value"), xlsSearch.getCellValue("city", "Value"),
                        VERIFY_SELECT_DESTINATION},
                {xlsSearch.getCellValue("country", "Value"), xlsSearch.getCellValue("city", "Value"),
                        VERIFY_SEARCH_HOTELS}};
    }
}
