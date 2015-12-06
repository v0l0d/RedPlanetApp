package com.androidMobile.scripts;

import java.util.Calendar;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.androidMobile.scripts.testObjects.AccountPageLocators;
import com.androidMobile.scripts.testObjects.HomePageLocators;
import com.androidMobile.scripts.testObjects.InHousePhoneLocators;
import com.androidMobile.workflows.GeneralHelper;
import com.androidMobile.workflows.LoginHelper;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

public class RP_015_TestChatWithFrontDesk  extends LoginHelper{
	ExcelReader xlsChatFrontDesk = new ExcelReader(configProps.getProperty("TestData"),
			"RP_ANDR_015");
		@Test(dataProvider = "testData")
  public void testChatWithFrontDesk(String email,String password,String chatName,String chatAdminEmail, 
		 String chatAdminPassword, String description) throws Throwable {
			Calendar cal = Calendar.getInstance();
			String chatUrl = xlsChatFrontDesk.getCellValue("chatURL", "Value");
			boolean res = false;
	  try{
		  TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
		  String testMessage = "Test message "+cal.getTime();
		  System.out.println("Test message "+testMessage);
		  handleRateAppPopUp();
		  navigateToMyAccount();	
		  handleRateAppPopUp();
		  click(AccountPageLocators.logInButton, "logInButton");
		  login(email, password);
		  handleRateAppPopUp();
		  navigateToHome();
		  handleRateAppPopUp();
		  //handelWelcomeDashboardDialog();
		  waitForElementPresent(HomePageLocators.chatWithFrontDeskButton, "chatWithFrontDeskButton");
		  click(HomePageLocators.chatWithFrontDeskButton, "chatWithFrontDeskButton");
		  Thread.sleep(2000);
			  waitForElementPresent(InHousePhoneLocators.textAreaForChat, "textAreaForChat");
			  click(InHousePhoneLocators.textAreaForChat, "textInputField");
			  type(InHousePhoneLocators.textAreaForChat, testMessage, "textInputField");
			  waitForElementPresent(InHousePhoneLocators.textAreaForChat, "textAreaForChat");
			  
			  click(InHousePhoneLocators.ChatNameInput, "ChatNameInput");
			  type(InHousePhoneLocators.ChatNameInput, chatName, "ChatNameInput");
			  click(InHousePhoneLocators.sendButtonForChat, "sendButtonForChat");
			  String replyMsg = GeneralHelper.FrontDeskChat(chatUrl, chatAdminEmail, chatAdminPassword, testMessage);
			  if(replyMsg!= null){
				  res = AndroidDriver2.getPageSource().contains(replyMsg);
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
	    			xlsChatFrontDesk.getCellValue("chatName", "Value"),xlsChatFrontDesk.getCellValue("ChatLoginID", "Value"),
	    			xlsChatFrontDesk.getCellValue("ChatLoginID", "password"),"Validate chat with FrontDesk"}};
	}
}

