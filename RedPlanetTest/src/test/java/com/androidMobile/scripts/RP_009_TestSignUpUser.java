package com.androidMobile.scripts;

import com.androidMobile.scripts.testObjects.AccountPageLocators;
import com.androidMobile.scripts.testObjects.LoginPageLocators;
import com.androidMobile.scripts.testObjects.SignUpEmailLocators;
import com.androidMobile.workflows.LoginHelper;
import com.androidMobile.workflows.SignUpHelper;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.support.ReportStampSupport;
import com.ctaf.utilities.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RP_009_TestSignUpUser extends LoginHelper {

    private final static String SIGNUP_NEW_USER = "Validate SignUp new user";
    private final static String SIGNUP_EXISTING_USER = "Validate error on SignUp with exising user";

    ExcelReader xlsSearch = new ExcelReader(configProps.getProperty("AndroidTestData"), "RP_ANDR_009");

    @Test(dataProvider = "testData")
    public void testSignUpUser(String fName, String lName, String email,
                               String password, String description) throws Throwable {
        try {
            TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
            logOutAndGotToMainScreen();
            click(AccountPageLocators.signUpButton, "signUpButton");
            waitForElementPresent(SignUpEmailLocators.firstnameInputField, "firstnameInputField");
            if (description.equals(SIGNUP_NEW_USER)) {
                boolean result = SignUpHelper.SignUp(fName, lName, email, password);
                Reporter.createReport(description, result);
            } else if (description.equals(SIGNUP_EXISTING_USER)) {
                boolean result = SignUpHelper.SignUp(fName, lName, email, password);
                if (!result) {
                    waitForElementPresent(LoginPageLocators.errorPop, "errorPop", 10);
                    String errorMsg = getText(LoginPageLocators.errorPop, "errorPop");
                    System.out.println("error message on signUp with existing user " + errorMsg);
                    Reporter.SuccessReport(description, "Successful " + errorMsg);
                    click(LoginPageLocators.okayButtonOnErrorpop, "okayButtonOnErrorpop");
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
        String newEmail = "test" + ReportStampSupport.randomValue().concat("@gmail.com");
        return new Object[][]{

                {xlsSearch.getCellValue("fName", "Value"), xlsSearch.getCellValue("lName", "Value"),
                        newEmail, xlsSearch.getCellValue("password", "Value"), SIGNUP_NEW_USER},
                {xlsSearch.getCellValue("fName", "Value"), xlsSearch.getCellValue("lName", "Value"),
                        xlsSearch.getCellValue("existingUser", "Value"), xlsSearch.getCellValue("password", "Value"),
                        SIGNUP_EXISTING_USER}};
    }
}
