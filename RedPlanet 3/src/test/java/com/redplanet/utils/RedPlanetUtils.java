package com.redplanet.utils; 

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

import com.ctaf.accelerators.TestEngine;
//import com.redplanet.testObjects.*;
public class RedPlanetUtils  extends TestEngine{

	

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
	
	
	
	
	
	public static boolean startAppiumForiOS(String LogfileName) throws Throwable {
		boolean result = false;
		try {
			
			// ---------------------------------------------------
			System.out.println("Starting Appium Server....");
			if(isAppiumForIosRunning()){
				stopAppiumForIos();
				Thread.sleep(10000);
			}
			CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");
			command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js",false);
			command.addArgument("--address",false);
			command.addArgument("127.0.0.1");
			command.addArgument("--port",false);
			command.addArgument("4723");
			//command.addArgument("--no-reset");
			//command.addArgument("--command-timeout");
			//command.addArgument("1200");
			//command.addArgument("--device-ready-timeout");
			//command.addArgument("12000");
			//command.addArgument("--show-ios-log");
			//command.addArgument("--log");
			//command.addArgument(LogfileName);
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			DefaultExecutor executor = new DefaultExecutor();
			executor.setExitValue(1);
			executor.execute(command,resultHandler);
			Thread.sleep(15000);
			if (isAppiumForIosRunning()) {
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
	 
 
 public static void stopAppiumForIos() throws Throwable {
	 
	    	Runtime.getRuntime().exec("killall KILL "+"node");
			 System.out.println("** Appium Status after Stop **"+isAppiumForIosRunning());
 }
 public static boolean isAppiumForIosRunning() throws Throwable {
	 
	    boolean found = false;
	    String line;
	    	BufferedReader input = new BufferedReader(new 
	    			InputStreamReader(Runtime.getRuntime().
	    					exec("pgrep -f "+"node").
	    					getInputStream()));
			while ((line = input.readLine()) != null) {
			 System.out.println("** Process Info **"+line);
			 if(line.length()>0){
				 found = true;
			 }
			}
			input.close();
	   
			System.out.println(" +++ Appium status +++ "+ found );
			return found;
}
 public static void main(String[] args) throws Throwable {
 isAppiumForIosRunning();
	 stopAppiumForIos();
	 isAppiumForIosRunning();
}
}

