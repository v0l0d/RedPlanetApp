package com.ctaf.accelerators;


	import java.io.BufferedReader;
import java.io.InputStreamReader;

	public class ProcessRunUtils {

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

	  public static void main(String[] args){
	    boolean result = ProcessRunUtils.isRunning("notepad.exe");
	   System.out.println("result "+result);
	  }

	}

