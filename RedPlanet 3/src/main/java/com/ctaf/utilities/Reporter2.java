package com.ctaf.utilities;

import java.io.File;

import com.ctaf.accelerators.ActionEngine;
import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ConfiguratorSupport;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.support.ReportStampSupport;

public class Reporter2 extends TestEngine {
	public static ConfiguratorSupport configProps = new ConfiguratorSupport(
			"config.properties");
	static String timeStamp = ReportStampSupport.timeStamp().replace(":", "_")
			.replace(".", "_");

	public static void reportCreater() throws Throwable {
		int intReporterType = Integer.parseInt(configProps
				.getProperty("reportsType"));

		switch (intReporterType) {
		case 1:

			break;
		case 2:

			HtmlReportSupport.htmlCreateReport();
			HtmlReportSupport.createDetailedReport();

			break;
		default:

			HtmlReportSupport.htmlCreateReport();
			break;
		}
	}

	public static void SuccessReport(String strStepName, String strStepDes)
			throws Throwable {
		int intReporterType = Integer.parseInt(configProps
				.getProperty("reportsType"));
		switch (intReporterType) {
		case 1:

			break;
		case 2:
			if (configProps.getProperty("OnSuccessScreenshot")
					.equalsIgnoreCase("True")) {
				ActionEngine.screenShot(TestEngine.filePath()+"/"+"Screenshots"+"/"
						+ strStepDes.replace(" ", "_") /*+ "_"
						+ TestEngine.timeStamp*/ + ".jpeg");
			}
			HtmlReportSupport.onSuccess(strStepName, strStepDes);		
			break;

		default:
			if (configProps.getProperty("OnSuccessScreenshot")
					.equalsIgnoreCase("True")) {
				ActionEngine.screenShot(TestEngine.filePath()+"/"+"Screenshots"+"/"
						+ strStepDes.replace(" ", "_") /*+ "_"
						+ TestEngine.timeStamp*/ + ".jpeg");
			}
			HtmlReportSupport.onSuccess(strStepName, strStepDes);
			break;
		}
	}

	public static void failureReport(String strStepName, String strStepDes)
			throws Throwable {
		
		int intReporterType = Integer.parseInt(configProps
				.getProperty("reportsType"));
		
		switch (intReporterType) {
		case 1:
			flag = true;
			break;
		case 2:

			
			//// New Screen shot code to avoid overriding \\\\
			String fileName = TestEngine.filePath()+"/"+"Screenshots"+"/"
					+ strStepDes.replace(" ", "_");
			fileName = makeUniqueImagePath(fileName);
			ActionEngine.screenShot(fileName+".jpeg");
	
			flag = true;
			//HtmlReportSupport.onFailure(strStepName, strStepDes, fileName+".jpeg");
			//New Screenshot code Starts//
			//if(!screenShotSource.equalsIgnoreCase("Selenium"))
			//	screenShotSource = "Selenium";
			//New Screenshot code ends//
			break;

		default:
			flag = true;
			fileName = TestEngine.filePath()+"/"+"Screenshots"+"/"
					+ strStepDes.replace(" ", "_");
			fileName = makeUniqueImagePath(fileName);
			ActionEngine.screenShot(fileName+".jpeg");
			//HtmlReportSupport.onFailure(strStepName, strStepDes, fileName+".jpeg");
			//New Screenshot code Starts//
			//if(!screenShotSource.equalsIgnoreCase("Selenium"))
			//	screenShotSource = "Selenium";
			//New Screenshot code ends//
			break;
		}

	}
	public static void warningReport(String strStepName, String strStepDes)
			throws Throwable {
		int intReporterType = Integer.parseInt(configProps
				.getProperty("reportsType"));
		switch (intReporterType) {
		case 1:
			flag = true;
			break;
		case 2:
			ActionEngine.screenShot(TestEngine.filePath()+"/"+"Screenshots"+"/"
					+ strStepDes.replace(" ", "_") /*+ "_" + TestEngine.timeStamp*/
					+ ".jpeg");			
			flag = true;
			HtmlReportSupport.onWarning(strStepName, strStepDes);
			break;

		default:
			flag = true;
			ActionEngine.screenShot(TestEngine.filePath()+"/"+"Screenshots"+"/"
					+ strStepDes.replace(" ", "_") /*+ "_" + TestEngine.timeStamp*/
					+ ".jpeg");
			HtmlReportSupport.onWarning(strStepName, strStepDes);
			break;
		}

	}
	
	
	// New Screen shot code to avoid overriding \\\\
	
	public static String makeUniqueImagePath(String fileName){
		String newFileName = fileName;
		try {
			//Verifying if the file already exists, if so append the numbers 1,2  so on to the fine name.			

			File myPngImage  = new File(fileName+".jpeg");			
			int counter=1;
			while(myPngImage.exists())
			{
				newFileName=fileName+"_"+counter;
				myPngImage  = new File(newFileName+".jpeg");
				counter++;							
			}
			return newFileName;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return newFileName;
		}
	}
}
