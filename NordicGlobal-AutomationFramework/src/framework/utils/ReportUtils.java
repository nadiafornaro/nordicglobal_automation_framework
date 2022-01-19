package framework.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReportUtils {
	
	ExtentHtmlReporter htmlReport;
	
	ExtentReports extentReports;
	
	ExtentTest extentTest;
	
	public ReportUtils(String htmlReportFilename) {
		htmlReportFilename = htmlReportFilename.trim();
		
		htmlReport = new ExtentHtmlReporter(htmlReportFilename);
		
		extentReports = new ExtentReports();
		
		extentReports.attachReporter(htmlReport);
		htmlReport.config().setReportName("Nordic Global Automation Report");
	}
	
	public void createAtestCase(String testcaseName) {
		extentTest = extentReports.createTest(testcaseName);
	}
	
	public void addTestLog(Status status, String comment) {
		extentTest.log(status, comment);
	}
	
	public void attachScreenshotToReport(String filename) throws Exception{
		extentTest.addScreenCaptureFromPath(filename);
	}
	
	public void flushReport() {
		extentReports.flush();
	}
	


}
