package com.redplanet.workflows;

import com.ctaf.utilities.Reporter;
import com.redplanet.testObjects.RoomSelectionLocators;


public class RoomSelectionHelper extends RoomSelectionLocators {
  
  public static void RoomSelection(String roomType) throws Throwable {
	  
	  waitForElementPresent(roomSelectionPage, "roomSelectionPage");
	  if(roomType.equalsIgnoreCase("twin")){		  
		  click(twinRoomLink, "twinRoomLink");
	  }else if(roomType.equalsIgnoreCase("double")){
		  click(doubleRoomLink, "doubleRoomLink");
	  }
	  click(continueButton, "confirmButton");
	  if(waitForElementPresent(guestDetailsFrame, "guestDetailsFrame")){
		  Reporter.SuccessReport("Roome Selection", "Passed");
	  }else
		  Reporter.failureReport("Room Selection", "Failed");
	  
  }
}
