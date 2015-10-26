package com.SampleScripts;





import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.server.handler.ImplicitlyWait;
import org.testng.annotations.Test;

import com.ctaf.accelerators.ActionEngine;
import com.ctaf.accelerators.TestEngine;
import com.redplanet.utils.RedPlanetUtils;


public class Redplanet extends ActionEngine{
  @Test
  public void f() throws Throwable {
	  
	  
	  try {
		  	/*driver.findElement(By.linkText(" Our hotels ")).click();
		  	Thread.sleep(6000);
		  	driver.findElement(By.linkText(" LOYALTY ")).click();*/
		  driver.findElement(By.xpath("//body")).sendKeys(Keys.ESCAPE);
		  driver.findElement(By.xpath("//*[@id='hotel-name-inline']/div/input")).sendKeys("SOLO (Solo, Indonesia)");
		  driver.findElement(By.xpath("//*[@id='hotel-name-inline']/div/input")).sendKeys(Keys.TAB);
		  driver.findElement(By.xpath("//body/descendant::button[contains(@class,'pickdate right')][2]")).click();
		  Thread.sleep(2000);
		  type(By.xpath("//body/descendant::input[@class='checkin datepicker'][2]"),"123","checkin datepicker");
		type(By.xpath("//body/descendant::input[@class='checkin datepicker'][2]"),"22-11-2016","checkin datepicker");
		type(By.xpath("//body/descendant::input[@class='checkout datepicker'][2]"),"123","checkout datepicker");
		type(By.xpath("//body/descendant::input[@class='checkout datepicker'][2]"),"22-12-2015","checkout datepicker");
		//body/descendant::input[@class="checkout datepicker"][2]
		  Thread.sleep(5000);
		  click(By.xpath("//button[@class='btn btn-search buttons buttons-button-search ng-binding']"), "search");
		  Thread.sleep(10000);

		  
		  
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }
}
