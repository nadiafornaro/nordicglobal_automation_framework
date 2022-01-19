package tests.NordicGlobal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;

import framework.CommonDriver;
import framework.utils.ConfigUtils;
import framework.utils.ReportUtils;
import framework.utils.ScreenshotUtils;
import pageObjects.pages.NordicPages.HomePage_page;


public class BaseTest {
	
	CommonDriver cmnDriver;	
	String url;	
	static WebDriver driver;	
	String configFileName;	
	String currentWorkDirectory;	
	Properties configProperty;	
	public static ReportUtils reportUtils;
	String reportFilename;	
	ScreenshotUtils screenshot;
	long executionTime = System.currentTimeMillis();
	HomePage_page homepage;
	
	@Parameters({"browserType"})
	@BeforeClass(alwaysRun= true)
	public void preSetup(@Optional("browserType")String browserType) throws Exception {
	    
	    String storyName = this.getClass().getSimpleName();
		
		currentWorkDirectory = System.getProperty("user.dir");
		
		configFileName = currentWorkDirectory + "/config/config.properties";
		
		reportFilename = currentWorkDirectory + "/reports/NordicGlobal_TestReport_" + storyName +"_"+ browserType + "_" + executionTime + ".html";
		
		configProperty = ConfigUtils.readProperties(configFileName);
		
		reportUtils = new ReportUtils(reportFilename);
	}
	
	@Parameters({"URL", "browserType"})
	@BeforeMethod(alwaysRun = true)
	public void setup(String URL, String browserType) throws Exception {
		
		/*use this if you want to use config.properties
			url = configProperty.getProperty("baseUrl");		
			String browserType = configProperty.getProperty("browserType");
		*/
		
		cmnDriver = new CommonDriver(browserType);
		driver = cmnDriver.getDriver();
		screenshot = new ScreenshotUtils(driver);
		cmnDriver.navigatetoURL(URL);	
	
	}
	
	@AfterMethod(alwaysRun = true)
	public void postTestAction(ITestResult result) throws Exception {
		
		String testcasename = result.getName();	
		String screenshotFilename = currentWorkDirectory + "/screenshots/" 
		+ testcasename + executionTime + ".jpeg";
		
		//If the test case fails, get a screenshot
		if(result.getStatus() == ITestResult.FAILURE) {
			reportUtils.addTestLog(Status.FAIL, "The test " + result.getMethod().getConstructorOrMethod().getName() + " failed");
			screenshot.captureAndSaveScreenshot(screenshotFilename);
			reportUtils.attachScreenshotToReport(screenshotFilename);
		}
		else{
			reportUtils.addTestLog(Status.INFO, "The test " + result.getMethod().getConstructorOrMethod().getName() + " passed");
			screenshot.captureAndSaveScreenshot(screenshotFilename);
			reportUtils.attachScreenshotToReport(screenshotFilename);
	    }
		reportUtils.flushReport();
		

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		//reportUtils.flushReport();
		cmnDriver.closeAllBrowser();
	}
	
	@AfterSuite(alwaysRun = true)
	public void postTearDown() {
		reportUtils.flushReport();
	}
	
	 public static int getStatusCodeandURL(String URL){
	        int statusCode = 0;
	        try {
	            URL url = new URL(URL);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");
	            connection.connect();
	            statusCode = connection.getResponseCode();
	            if(statusCode == 200 || statusCode == 403) {
	            reportUtils.addTestLog(Status.INFO,"URL " + URL +" has a status code - " + HttpURLConnection.HTTP_OK);
	            reportUtils.addTestLog(Status.INFO, "<a href=" + URL+ " target=\"_blank\">"+ URL + "</a>");
	            }
	            else if(statusCode == 500) {
		            reportUtils.addTestLog(Status.WARNING,"URL " + URL +" has a status code - " + HttpURLConnection.HTTP_OK);
		            reportUtils.addTestLog(Status.WARNING, "<a href=" + URL+ " target=\"_blank\">"+ URL + "</a>");
		            }
	            else if(statusCode == 404) {
		            reportUtils.addTestLog(Status.WARNING,"URL " + URL +" has a status code - " + HttpURLConnection.HTTP_OK);
		            reportUtils.addTestLog(Status.WARNING, "<a href=" + URL+ " target=\"_blank\">"+ URL + "</a>");
		            }
	            } catch (IOException e){
	            statusCode = 0;
	            reportUtils.addTestLog(Status.INFO, e.getMessage());
	            reportUtils.addTestLog(Status.INFO,"URL " + URL +" has a status code - " + statusCode);
	        }
	        return statusCode;  
	    }
    
}
