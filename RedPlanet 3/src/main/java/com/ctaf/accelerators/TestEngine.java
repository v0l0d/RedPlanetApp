package com.ctaf.accelerators;       

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.ctaf.support.ActionEngineSupport;
import com.ctaf.support.ConfiguratorSupport;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.support.ReportStampSupport;
import com.ctaf.utilities.Reporter;
//import com.redplanet.utils.RedPlanetUtils;

public class TestEngine extends HtmlReportSupport {
	public static Logger logger = Logger.getLogger(TestEngine.class.getName());
	public static ConfiguratorSupport configProps = new ConfiguratorSupport(
			"config.properties");
	public static ConfiguratorSupport counterProp = new ConfiguratorSupport(
			configProps.getProperty("counterPath"));
	public static String currentSuite = "";
	public static String method = "";
	public static String timeStamp = ReportStampSupport.timeStamp()
			.replace(" ", "_").replace(":", "_").replace(".", "_");
	public static boolean flag = false;
	
	public static WebDriver driver=null;
	public static int stepNum = 0;
	public static int PassNum =0;
	public static int FailNum =0;
	public static int passCounter =0;
	public static int failCounter =0;
	public static String testName = "";
	public static String testCaseExecutionTime = "";
	public static WebDriver webDriver = null;
	public static StringBuffer x=new StringBuffer();
	public static String finalTime = "";
	public static boolean isSuiteRunning=false;
	public static Map<String, String> testDescription = new LinkedHashMap<String, String>();
	public static Map<String, String> testResults = new LinkedHashMap<String, String>();
	public static String url=null;
	static ExcelReader xlsrdr = new ExcelReader(configProps.getProperty("TestData"),configProps.getProperty("sheetName0"));
	public DesiredCapabilities capabilities;
	//***************************************************************************************************
	public static String DeviceName = configProps.getProperty("DeviceName");

	public static AppiumDriver AndroidDriver2 = null;
	public static AppiumDriver Iosdriver = null;
	//***************************************************************************************************

	/*
	 * public static Screen s; public static String url =
	 * "jdbc:mysql://172.16.6.121/"; public static String dbName = "root";
	 * public static String userName = "root"; public static Connection conn =
	 * null; public static Statement stmt = null; public static
	 * PreparedStatement pstmt = null; public static ResultSet rs = null;
	 */
	public static int countcompare = 0;
	public static String browser = null;
	static int len = 0;
	static int i = 0;
	public static ITestContext itc;
	public static String groupNames = null;


	@BeforeSuite (alwaysRun = true)
	public static void setupSuite(ITestContext ctx) throws Throwable {
		itc = ctx;
		groupNames = ctx.getCurrentXmlTest().getIncludedGroups().toString();
		ReportStampSupport.calculateSuiteStartTime();
		try {
			if (ctx.getCurrentXmlTest().getIncludedGroups().toString()
					.contains("chrome")) {
				logger.info("chrome_browser : "
						+ xlsrdr.getCellValue("chrome_browser", "value"));
				browser = xlsrdr.getCellValue("chrome_browser", "value");
				System.out.println("browser "+browser  );
			} else if (ctx.getCurrentXmlTest().getIncludedGroups().toString()
					.contains("firefox")) {
				logger.info("firefox_browser : "
						+ xlsrdr.getCellValue("firefox_browser", "value"));
				browser = xlsrdr.getCellValue("firefox_browser", "value");
			} else if (ctx.getCurrentXmlTest().getIncludedGroups().toString()
					.contains("ie")) {
				logger.info("ie_browser : "
						+ xlsrdr.getCellValue("ie_browser", "value"));
				browser = xlsrdr.getCellValue("ie_browser", "value");
			}else if (ctx.getCurrentXmlTest().getIncludedGroups().toString()
					.contains("Android")) {
				logger.info("Android");
				browser = "Android";
			}
			else if (ctx.getCurrentXmlTest().getIncludedGroups().toString()
					.contains("Mobile")) {
				logger.info("iPhone");
				browser = "iPhone";
			}
			else {
				logger.info("Starting browser : "
						+ configProps.getProperty("browserType"));
				browser = configProps.getProperty("browserType");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(e1);
		}
		try {
			if (ctx.getCurrentXmlTest().getIncludedGroups().toString()
					.contains("afiliate")) {
				System.out.println("afiliate URL : "
						+ xlsrdr.getCellValue("afiliate", "value"));
				url = (xlsrdr.getCellValue("afiliate", "value"));
			} else if (ctx.getCurrentXmlTest().getIncludedGroups().toString()
					.contains("booking")) {
				System.out.println("booking URL : "
						+ xlsrdr.getCellValue("booking", "value"));
				url = (xlsrdr.getCellValue("booking", "value"));
			} else if (ctx.getCurrentXmlTest().getIncludedGroups().toString()
					.contains("referred")) {
				System.out.println("referred URL : "
						+ xlsrdr.getCellValue("referred", "value"));
				url = (xlsrdr.getCellValue("referred", "value"));
			}else {
				url = (configProps.getProperty("URL"));
			}
			
			Reporter.reportCreater();
			HtmlReportSupport.currentSuit = ctx.getCurrentXmlTest().getSuite()
					.getName();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	

	@AfterSuite(alwaysRun = true)
	public void tearDown(ITestContext ctx) throws Throwable {
		try{
		ReportStampSupport.calculateSuiteExecutionTime();
		
		HtmlReportSupport.createHtmlSummaryReport(browser, url);
		closeSummaryReport();
		//driver.quit();
		}catch(Exception e){
			e.printStackTrace();
		} /*finally {
			if (browser.contains("Android")) {
				RedPlanetUtils.stopAppium();
			} else if (browser.contains("iPhone")) {
				RedPlanetUtils.stopAppiumForIos();
			}
		}*/
		
		
		
	}
	

	/**
	 * Write results to Browser specific path
	 */
	// @Parameters({"browserType"})
	public static String filePath() {
		String strDirectoy = "";
		
		if (browser.equalsIgnoreCase("ie")) {
			strDirectoy = "IE"+File.separator+"IE";

		} else if (browser.equalsIgnoreCase("firefox")) {
			strDirectoy = "Firefox"+File.separator+"Firefox";

		} else if(browser.equalsIgnoreCase("chrome")){
			strDirectoy = "Chrome"+File.separator+"Chrome";
			
		}else if(browser.equalsIgnoreCase("Android")){
			strDirectoy = "Android"+File.separator+"Android";
			
		}else if(browser.equalsIgnoreCase("iphone")){
			strDirectoy = "iPhone"+File.separator+"iPhone";
			
		}

	if (strDirectoy != "") {
		new File(configProps.getProperty("screenShotPath") + strDirectoy
				+ "_" + timeStamp).mkdirs();
	}

	File results = new File(configProps.getProperty("screenShotPath") + strDirectoy
		+ "_" + timeStamp+File.separator+"Screenshots");
	if(!results.exists())
	{
		results.mkdir();
		HtmlReportSupport.copyLogos();
	}

	return configProps.getProperty("screenShotPath") + strDirectoy + "_"
			+ timeStamp + File.separator;

}

	/**
	 * Browser type prefix for Run ID
	 * 
	 */
	public static String result_browser() {
		if (groupNames.equals("")) {
			if (configProps.getProperty("browserType").equals("ie")) {
				return "IE";
			} else if (configProps.getProperty("browserType").equals("firefox")) {
				return "Firefox";
			} else {
				return "Chrome";
			}
		} else {
			if (browser.equalsIgnoreCase("ie")) {
				return "IE";

			} else if (browser.equalsIgnoreCase("firefox")) {
				return "Firefox";

			}  else if (browser.equalsIgnoreCase("Android")) {
				return "Android";

			}else if (browser.equalsIgnoreCase("iPhone")) {
				return "iPhone";

			}else {
				return "Chrome";

			}
		}
	}

	/**
	 * Related to Xpath
	 * 
	 * @Date 19/02/2013
	 * @Revision History
	 * 
	 */
	public static String methodName() {
		
			if (browser.equals("ie")) {
				return "post";
			} else {
				return "POST";
			}
		}
	@BeforeMethod(alwaysRun = true)
	public void reportHeader(Method method) throws Throwable {
		//itc = ctx;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy hh mm ss SSS");
		String formattedDate = sdf.format(date);
		ReportStampSupport.calculateTestCaseStartTime();
		
			if (browser.equalsIgnoreCase("firefox")) {
				ProfilesIni profile = new ProfilesIni();
				FirefoxProfile ffprofile = new FirefoxProfile();
				ffprofile.setEnableNativeEvents(true);
				webDriver = new FirefoxDriver(ffprofile);
			
			} else if (browser.equalsIgnoreCase("ie")) {
				File file = new File("Drivers\\IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver",
						file.getAbsolutePath());
				DesiredCapabilities caps = DesiredCapabilities
						.internetExplorer();
				caps.setCapability(
						InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				webDriver = new InternetExplorerDriver(caps);
				i = i + 1;
				/*if (configProps.getProperty("browserType").equals("ie")) {
					Process p = Runtime
							.getRuntime()
							.exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
					p.waitFor();
					Thread.sleep(1000);
				}*/
			
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"Drivers\\chromedriver.exe");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				webDriver = new ChromeDriver(capabilities);
				

			}else if(browser.equalsIgnoreCase("iphone")){
				try {
					// ---------------------------------------------------
					//System.out.println(System.getProperty("user.home")+"/Log/RPMob_" + timeStamp + ".log");
					String logFile = System.getProperty("user.dir")+"/Logs/RPMob_"+System.currentTimeMillis()+".log";
				/*while (true) {
					if (RedPlanetUtils.startAppiumForiOS(logFile)) {
						break;
					}
				}
				if ((new File(logFile).exists())) {
					System.out.println("Log File Created by Appium at path : " + System.getProperty("user.dir")
							+ "/Log/RPMob_" + timeStamp + ".log");
				}
				Thread.sleep(10000);*/
					// -----------------------------------------------------
					DeviceName = configProps.getProperty("iOSDeviceName");
					String device = configProps.getProperty("Device");
					String appPath = configProps.getProperty("appPath");
					String ipaPath = configProps.getProperty("ipaPath");

					String platformVer = configProps.getProperty("platformVersion");
					String udid = configProps.getProperty("UDID");
					String bundleID = configProps.getProperty("BundleID");
					DesiredCapabilities capabilitiesForAppium = new DesiredCapabilities();
					System.out.println("DeviceName is : " + DeviceName);
					capabilitiesForAppium.setCapability("deviceName",device);
					capabilitiesForAppium.setCapability("platformName","iOS");
					capabilitiesForAppium.setCapability("platformVersion",platformVer);
					capabilitiesForAppium.setCapability("deviceName",device);
					capabilitiesForAppium.setCapability("bundleId", bundleID);
					capabilitiesForAppium.setCapability("newCommandTimeout","6000");
					capabilitiesForAppium.setCapability("takesScreenshot", true);
					capabilitiesForAppium.setCapability("autoWebviewTimeout","6000");
					//capabilitiesForAppium.setCapability("fullReset", "true");
					if((DeviceName.contains("Simulator"))||((udid.length()==0))){
						System.out.println("using simulator");
						//capabilitiesForAppium.setCapability("app",appPath);
					}else{
						System.out.println("using real device");
						capabilitiesForAppium.setCapability("udid", udid);
						//capabilitiesForAppium.setCapability("app",ipaPath);
					}
					Iosdriver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),
							capabilitiesForAppium);
					//Iosdriver.closeApp();
					//Iosdriver.resetApp();
					driver = Iosdriver;
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}else if (browser.equalsIgnoreCase("Android")) {
				try {
					String AppPackage = configProps.getProperty("appPackage");
					String AppActivity = configProps.getProperty("appActivity");
					String OSVersion =  configProps.getProperty("OSVersion");
					// ---------------------------------------------------
					//System.out.println("Starting Appium Server....");
					// -----------------------------------------------------
					DesiredCapabilities capabilitiesForAppium = new DesiredCapabilities();
				
					System.out.println("DeviceName is : " + DeviceName);
					capabilitiesForAppium.setCapability("deviceName",
							DeviceName);
					capabilitiesForAppium.setCapability("platformVersion",OSVersion);
					capabilitiesForAppium.setCapability("platformName","Android");
					/*capabilitiesForAppium.setCapability("newCommandTimeout",
							"12000");*/
					//capabilitiesForAppium.setCapability("autoWebview", "true");
					capabilitiesForAppium.setCapability("autoWebviewTimeout","1000");
					//capabilitiesForAppium.setCapability("noReset", false);
					capabilitiesForAppium.setCapability("appPackage", AppPackage);
					capabilitiesForAppium.setCapability("appActivity", AppActivity);
					AndroidDriver2 = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
							capabilitiesForAppium);
					driver = (AndroidDriver2);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		flag = false;

		
		if((!(browser.equalsIgnoreCase("Android")))&(!(browser.equalsIgnoreCase("iPhone")))){
			driver = /*new EventFiringWebDriver*/(webDriver);
			/*ActionEngineSupport myListener = new ActionEngineSupport();
			driver.register(myListener);*/
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
			driver.get(url);
		}
		
		HtmlReportSupport.tc_name = method.getName().toString() + "-"
				+ formattedDate;
		String[] ts_Name = this.getClass().getName().toString().split("\\.");
		HtmlReportSupport.packageName = ts_Name[0] + "." + ts_Name[1] + "."
				+ ts_Name[2];


			HtmlReportSupport.testHeader(HtmlReportSupport.tc_name, browser);

		stepNum = 0;
		PassNum = 0;
		FailNum = 0;
		testName = method.getName();
		logger.info("Current Test : "+testName);

	}
	
		
	
	@AfterMethod(alwaysRun = true)
	public static void tearDownMethod() throws Throwable
	{
		try{
		ReportStampSupport.calculateTestCaseExecutionTime();
		closeDetailedReport();
		if(FailNum!=0)
		{
			failCounter=failCounter+1;
			testResults.put(HtmlReportSupport.tc_name, "FAIL");
		}
		else
		{
			testResults.put(HtmlReportSupport.tc_name, "PASS");
			passCounter=passCounter+1;
		}
		}catch(Exception e){
			e.printStackTrace();
			//Reporter.onFailure("ConfigurationFailure From tearDownMethod", "Failed due to ServerConfirguration error");

		} /*finally {
			if (browser.contains("iPhone")) {
				//Iosdriver.closeApp();
				RedPlanetUtils.stopAppiumForIos();
			}
		}*/
		driver.quit();

	}
	
	
}
