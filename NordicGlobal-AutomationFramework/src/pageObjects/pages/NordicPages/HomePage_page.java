package pageObjects.pages.NordicPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.Status;
import pageObjects.pages.BasePage;
import tests.NordicGlobal.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage_page extends BasePage{
	
	public HomePage_page(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	BaseTest report = new BaseTest();
	
	
	//elements in home page
	@CacheLookup
	@FindBy(xpath="/html/body/header/div/a")
	public WebElement nordic_logo;
	
	@CacheLookup
	@FindBy(xpath="/html/body/header/div/div[2]/div[1]/form/button/span")
	public WebElement search_button;
	
	@CacheLookup
	@FindBy(xpath="/html/body/header/div/div[2]/div[1]/form/input")
	public WebElement search_text_field;
	
	@CacheLookup
	@FindBy(xpath="/html/body/div[1]/span/div[1]/span/div/div/ul/li[1]")
	public WebElement slide_first_button;
	
	@CacheLookup
	@FindBy(xpath="/html/body/header/div/div[2]/div[2]")
	public WebElement hamburguer_menu;
	
	@CacheLookup
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div/a[1]")
	public WebElement accept_cookies;
	
	
	
	//items inside hamburguer_menu
	@CacheLookup
	@FindBy(xpath="/html/body/header/div/div[1]/div/div[2]/div[2]/nav/div/span/div/ul/li[2]/ul/li[1]/span")
	public WebElement Strategic_advisory_expand_button;
	
	@CacheLookup
	@FindBy(xpath="/html/body/header/div/div[1]/div/div[2]/div[2]/nav/div/span/div/ul/li[2]/ul/li[1]/span")
	public WebElement Digital_and_cloud_initiatives_expand_button;
	
	@CacheLookup
	@FindBy(xpath="/html/body/header/div/div[1]/div/div[1]/div[2]")
	public WebElement close_button;
	
	
	//items in footer
	@CacheLookup
	@FindBy(xpath="/html/body/footer/div[1]/div/div[4]/img")
	public WebElement nordic_footer_logo;
	
	@CacheLookup
	@FindBy(xpath="/html/body/footer/div[1]/div/div[4]/p")
	public WebElement nordic_footer_text;
	
	@CacheLookup
	@FindBy(xpath="/html/body/footer/div[1]/div/div[4]/div")
	public WebElement nordic_footer_menu;
	

	public void validateHomePageElements() {
		try {
			nordic_logo.isDisplayed();
			search_button.isDisplayed();
			hamburguer_menu.isDisplayed();
			nordic_footer_menu.isDisplayed();
			Thread.sleep(1000);
			report.reportUtils.addTestLog(Status.PASS, "All elements are displayed");
			} catch (InterruptedException e) {
				BaseTest.reportUtils.addTestLog(Status.INFO, "Element is missing" + e.getMessage());
			}
		
	}
	
	
	public void clickInCloseButton_in_menu() {
		try {
			close_button.click();
			Thread.sleep(1000);
			report.reportUtils.addTestLog(Status.PASS, "Close button clicked");
			} catch (InterruptedException e) {
				report.reportUtils.addTestLog(Status.INFO, "Close button not clicked" + e.getMessage());
				
			}
	}
	
	public void clickInHamburguerMenu() {
		try {
			hamburguer_menu.click();
			Thread.sleep(1000);
			report.reportUtils.addTestLog(Status.PASS, "HamburguerMenu Clicked");
			} catch (InterruptedException e) {
				report.reportUtils.addTestLog(Status.INFO, "HamburguerMenu not clicked" + e.getMessage());
			}
	}
	
	public void clickInItemsInsideHamburguerMenu() {
		try {
			Strategic_advisory_expand_button.click();
			Digital_and_cloud_initiatives_expand_button.click();
			Thread.sleep(1000);
		
			report.reportUtils.addTestLog(Status.PASS, "Items clicked");
			} catch (InterruptedException e) {
				report.reportUtils.addTestLog(Status.INFO, "Items not clicked" + e.getMessage());
			}
	}
	
	
	public void accept_cookies() throws InterruptedException {
			Thread.sleep(500);
			accept_cookies.click();
			Thread.sleep(1000);
			BaseTest.reportUtils.addTestLog(Status.PASS, "Accept cookies button cliked");
	}
	
	
	

	
	
			
			
			
			
	
	
	

}
