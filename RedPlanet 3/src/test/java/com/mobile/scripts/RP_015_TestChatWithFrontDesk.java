package com.mobile.scripts;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.support.ExcelReader;
import com.ctaf.utilities.Reporter;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.HtmlReportSupport;
import com.mobile.scripts.testObjects.AccountPageLocators;
import com.mobile.scripts.testObjects.BookPageLocators;
import com.mobile.scripts.testObjects.LoginPageLocators;
import com.mobile.scripts.testObjects.PickRoomPageLocators;
import com.mobile.workflows.GeneralHelper;
import com.mobile.workflows.LoginHelper;
import com.mobile.scripts.testObjects.HomePageLocators;
import com.mobile.scripts.testObjects.InHousePhoneLocators;

public class RP_015_TestChatWithFrontDesk  extends LoginHelper{
	ExcelReader xlsChatFrontDesk = new ExcelReader(configProps.getProperty("TestData"),
			"RP_015");
		@Test(dataProvider = "testData")
  public void testChatWithFrontDesk(String email,String password,String chatAdminEmail, 
		 String chatAdminPassword, String description) throws Throwable {
			Calendar cal = Calendar.getInstance();
			String chatUrl = xlsChatFrontDesk.getCellValue("chatURL", "Value");
			boolean res = false;
	  try{
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
		  String testMessage = "Test message "+cal.getTime();
		  System.out.println("Test message "+testMessage);
		  navigateToMyAccount();
		  //verify user already loggedIn, if yes sign out
		  validateUserLogin();		  
		  click(AccountPageLocators.logInButton, "logInButton");
		  login(email, password);
		  navigateToHome();
		  //handelWelcomeDashboardDialog();
		  waitForElementPresent(HomePageLocators.chatWithFrontDeskButton, "chatWithFrontDeskButton");
		  click(HomePageLocators.chatWithFrontDeskButton, "chatWithFrontDeskButton");
		  Thread.sleep(2000);
			  waitForElementPresent(InHousePhoneLocators.textAreaForChat, "textAreaForChat");
			  click(InHousePhoneLocators.textInputField, "textInputField");
			  type(InHousePhoneLocators.textInputField, testMessage, "textInputField");
			  click(InHousePhoneLocators.sendButtonForChat, "sendButtonForChat");
			  String replyMsg = GeneralHelper.FrontDeskChat(chatUrl, chatAdminEmail, chatAdminPassword, testMessage);
			  if(replyMsg!= null){
				  res = Iosdriver.getPageSource().contains(replyMsg);
			  	}
				  System.out.println("resutl "+res);
				  if(res){
					  Reporter.SuccessReport(description, " Successful");
					  System.out.println("successfully calling Front Desk ");
				  }else{
					  Reporter.failureReport(description, " Failed");
				  }
	  }catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("LogIn", "Failed");
		}
  }
  
  @DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{xlsChatFrontDesk.getCellValue("ValidCredentials", "Value"),xlsChatFrontDesk.getCellValue("ValidCredentials", "password"),
	    			xlsChatFrontDesk.getCellValue("ChatLoginID", "Value"),
	    			xlsChatFrontDesk.getCellValue("ChatLoginID", "password"),"Validate chat with FrontDesk"}};
	}
}

