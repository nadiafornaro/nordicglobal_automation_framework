package framework;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageObjects.pages.NordicPages.HomePage_page;

public class CommonDriver {
	
	private WebDriver driver;
	private int pageLoadTimeout;
	private int elementDetectionTimeout;
	private String currentWorkingDirectory;
	
	public CommonDriver(String browserType) throws Exception{
		
		pageLoadTimeout = 10;
		elementDetectionTimeout = 10;	
		currentWorkingDirectory = System.getProperty("user.dir");
		
		if(browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", currentWorkingDirectory + "/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--profile-directory=<profile>");
            options.addArguments("--test-type");
            options.addArguments("--disable-extensions");
		}
		else if (browserType.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", currentWorkingDirectory + "/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
			
		}
		else if (browserType.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", currentWorkingDirectory + "/drivers/firefoxdriver.exe");
			driver = new FirefoxDriver();
		}
		else { 
			throw new Exception("Invalid browser" + browserType);
		}
		
		driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		
	}
	
	public void navigatetoURL(String url) throws InterruptedException {
		driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);		
		driver.get(url);
		Thread.sleep(2000);


	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setPageLoadTimeout(int pageLoadTimeout) {
		this.pageLoadTimeout = pageLoadTimeout;
	}

	public void setElementDetectionTimeout(int elementDetectionTimeout) {
		this.elementDetectionTimeout = elementDetectionTimeout;
	}
	
	public void closeAllBrowser() {
		driver.quit();
	}
	
	public String getTitleofThePage() {
		return driver.getTitle();
	}
	
	public boolean ValidatePageText(String text) {		
		return driver.getPageSource().contains(text);
	}
	
	public int getURLStatus(String URL) {
		int statusCode =0;
	 try {
		 URL url = new URL(URL);
         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
         connection.setRequestMethod("GET");
         connection.connect();
         statusCode = connection.getResponseCode();
     } catch (IOException e){
         statusCode = 0;
     }
	    return statusCode;
	}
}
