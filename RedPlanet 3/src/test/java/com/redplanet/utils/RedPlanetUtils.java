package com.redplanet.utils; 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.poi.ss.formula.ptg.TblPtg;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.utilities.Zip;
//import com.redplanet.testObjects.*;
public class RedPlanetUtils  extends TestEngine{

	
	/*Read t*/
	public static String getCookieFromFile(String cookieName, String fileName) {
	    BufferedReader br = null;
	    String cookieValue = null;
	    try {
	    	br = new BufferedReader(new FileReader(fileName));
	        String line = br.readLine();
	        while (line != null) {
	        	if(line.indexOf(cookieName) > 0){
	        		String []temp = line.split("[;]");
	        		for (String data : temp) {
						if(data.indexOf(cookieName) > 0){
							cookieValue =  data.split("[=]")[1];
							//System.out.println(cookieValue);
						}
					}
	        		break;
	            }
	            line = br.readLine();
	        }
	        return cookieValue;
	    } catch (Exception e) {
			e.printStackTrace();
		}finally {
	        try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
		return cookieValue;
	}

	/*public static boolean isVideoPlaying(String fileName) {
	    BufferedReader br = null;
	    boolean isPlaying = false;
	    try {
	    	br = new BufferedReader(new FileReader(fileName));
	        List<Long> frames =new ArrayList<Long>();
	    	String line = br.readLine();
	        
	        while (line != null) {
	        	if(line.indexOf("video=") > 0){
	        		String frameCount = line.split("(video=)")[1].split("[)]")[0];
	        		frames.add(Long.valueOf(frameCount));
	        	}
	            line = br.readLine();
	        }
	        if(frames.size() > 0){
	        	int size = frames.size();

	        	if(frames.get(size-1) > frames.get(0)){
	        		return true;
	        	}else{
	        		return false;	        		
	        	}
	        	
	        }
	        return isPlaying;
	        
	    } catch(Exception e){
	    	e.printStackTrace();
	    } finally {
	        try {
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		return isPlaying;
	}*/	
	
	public static void zipMailQuickflixReports(){
		try {
			String filePath = System.getProperty("user.dir") + "\\"
					+ filePath() + "\\SummaryResults_" + timeStamp+ ".html";
			//System.out.println("filePath = "+filePath());
			Zip.zipFolder(System.getProperty("user.dir") + "\\"
					+ filePath(), System.getProperty("user.dir") + 
					"\\"+"QuickflixReports.zip");
			/*String sendReporMailFlag = xlsrdr.getCellValue(
					"sendReprotMailFlag", "value");*/
			if (/*sendReporMailFlag.equalsIgnoreCase("TRUE")*/true) {
				
				try {
					MailUtility.sendReportAsMailBody(filePath,
							"Test Summary Report " + timeStamp,System.getProperty("user.dir") + 
							"\\"+"QuickflixReports.zip");
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static boolean startAppium(String LogfileName) throws Throwable {
		boolean result = false;
		try {
			if(isRunning("node.exe")){
				System.out.println("Appium already running ");
				stopAppium();
			}
			// ---------------------------------------------------
			System.out.println("Starting Appium Server....");
			CommandLine command = new CommandLine("cmd");
			String AppiumPath = configProps.getProperty("AppiumPath");
			command.addArgument("/c");
			String node = AppiumPath+"\\node.exe";
			String appium = AppiumPath+"\\node_modules\\appium\\bin\\appium.js";
			System.out.println(node +appium);
			command.addArgument("\""+node+"\"");
			command.addArgument("\""+appium+"\"");
			command.addArgument("--address");
			command.addArgument("127.0.0.1");
			command.addArgument("--bootstrap-port");
			command.addArgument("4242");
			//command.addArgument("--no-reset");
			command.addArgument("--command-timeout");
			command.addArgument("120000");
			command.addArgument("--device-ready-timeout");
			command.addArgument("12000");
			command.addArgument("--log");
			command.addArgument(LogfileName);
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			DefaultExecutor executor = new DefaultExecutor();
			executor.setExitValue(1);
			executor.execute(command,resultHandler);
			Thread.sleep(15000);
			if ((new File(LogfileName).exists())) {
				result = true;
			} else {
				result = false;
			}
			// -----------------------------------------------------
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean stopAppium() throws Throwable {
		boolean result = false;
		try {
			
			//****************************************************************************
			//Runtime.getRuntime().exec("cmd /c  taskkill /F /IM node.exe");
			CommandLine command = new CommandLine("cmd");
			command.addArgument("/c");
			command.addArgument("taskkill");
			command.addArgument("/F");
			command.addArgument("/IM");
			command.addArgument("node.exe");
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			DefaultExecutor executor = new DefaultExecutor();
			executor.setExitValue(1);
			executor.execute(command, resultHandler);
			// ---------------------------------------------------
						System.out.println("Stoping Appium Server....");
			//****************************************************************************
			result = true;
			// -----------------------------------------------------
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void createcounter(int counterVal,String counterName) throws Throwable {
		
		String count1 = null;		
		count1 = Integer.toString(counterVal);
		counterProp.clean();
		counterProp.setProperty(counterName, count1);
		System.out.println("updating counter : " + counterName + " to "+counterVal);
		}
	
	public static boolean isRunning(String processName) {
	    boolean found = false;
	    String line;
	    try {
	    	BufferedReader input = new BufferedReader(new 
	    			InputStreamReader(Runtime.getRuntime().
	    					exec("cmd /c tasklist | findstr "+processName).
	    					getInputStream()));
			while ((line = input.readLine()) != null) {
			 System.out.println("** Process Info **"+line);
			 if(line.contains(processName)){
				 found = true;
			 }
			}
			input.close();
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	    return found;
	  }
	
	
	
	/*public static void DatePicker(WebElement selectDate,String dateTime) throws Exception{
		
	    
	    selectDate.click();
	 
	    //button to move next in calendar
	    WebElement nextLink = driver.findElement(By.xpath(RedPlanetBooking.tblCalendar+"/thead/tr/th[3]"));
	 
	    //button to click in center of calendar header
	    WebElement midLink = driver.findElement(By.xpath(RedPlanetBooking.tblCalendar+"/thead/tr/th[2]"));
	    
	    //button to move previous month in calendar
	    WebElement previousLink = driver.findElement(By.xpath(RedPlanetBooking.tblCalendar+"/thead/tr/th[1]")); 
 
        //Split the date 
        String date_dd_MM_yyyy[] = (dateTime.split("/"));
        
        //get the year difference between current year and year to set in calander 
        int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2])- Calendar.getInstance().get(Calendar.YEAR);        
        midLink.click(); 
        
        if(yearDiff!=0){
        	
            //if you have to move next year
            if(yearDiff>0){
                for(int i=0;i< yearDiff;i++){
                    System.out.println("Year Diff->"+i);
                    nextLink.click();
                }
            }
 
            //if you have to move previous year
            else if(yearDiff<0){
                for(int i=0;i< (yearDiff*(-1));i++){
                    System.out.println("Year Diff->"+i);
                    previousLink.click();
                }
            }
        }
 
        Thread.sleep(1000);
 
        //Get all months from calendar to select correct one
 
        List<WebElement> list_AllMonthToBook = driver.findElements(By.xpath(RedPlanetBooking.tblCalendar+"/tbody/tr/td/span")); 
        list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1])-1).click();
        Thread.sleep(1000);
 
        //get all dates from calendar to select correct one
        List<WebElement> list_AllDateToBook = driver.findElements(By.xpath(RedPlanetBooking.tblCalendar+"/table/tbody/tr/td[@class='day']"));
        list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0])-1).click();
		       
 
    }*/
 

	
	
}

