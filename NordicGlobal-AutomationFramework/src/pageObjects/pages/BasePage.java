package pageObjects.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ElementControl;
import tests.NordicGlobal.BaseTest;

public class BasePage {
	protected WebDriver driver;
	public ElementControl elementControl;
	public BasePage(WebDriver driver) {
		this.driver = driver;
		elementControl = new ElementControl(driver);
	}
		
}