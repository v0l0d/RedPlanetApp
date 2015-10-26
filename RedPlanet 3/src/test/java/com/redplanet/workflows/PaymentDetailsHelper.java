package com.redplanet.workflows;

import org.testng.annotations.Test;

import com.redplanet.testObjects.PaymentDtsLocators;

public class PaymentDetailsHelper extends PaymentDtsLocators {
  @Test
  public static boolean PaymentDetails(String paymentMethod, String cardName, String cardNum,
		  String expiry,String cvc,String promocode) throws Throwable {
	
	  boolean res = false;
	  
	  try{
		  waitForElementPresent(paymentFrame, "PaymentFrame");
		  if(paymentMethod.equalsIgnoreCase("creditcard")){
			click(creditCardRadio, "creditCardRadio");
			type(nameOnCardInput, cardName, "nameOnCardInput");
			type(cardNumberInput, cardNum, "cardNumberInput");
			type(cardExpiryInput, expiry, "cardExpiryInput");
			type(cvcInput, cvc, "cvcInput");			
		  }else if(paymentMethod.equalsIgnoreCase("payathotel")){
			click(payAtHotelRadio, "payAtHotelRadio");
			type(promotionInput, promocode, "promotionInput");
			click(applyPromocodeLink, "applyPromocodeLink");
		  }
		  type(nameOnCardInput, cardName, "nameOnCardInput");
		  
	  }catch(Exception e){
			e.printStackTrace();
			res = false;
			return res;
		  }
	return res;
	  
  }
}
