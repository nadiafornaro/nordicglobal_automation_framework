package framework;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ElementControl {
	
	WebDriver driver;
	
	public ElementControl(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickElement(WebElement element) {
		element.click();
	}
	
	public void clearText(WebElement element) throws Exception {
		element.clear();
	}
	
	public void getText(WebElement element) {
	        element.getText();
	}
	
	public void sendText(WebElement element, String text) {
		element.sendKeys(text);
	}
	
	public boolean isEnabled(WebElement element) {
		return element.isEnabled();
	}
	
	public boolean isDisplayed (WebElement element) {
		return element.isDisplayed();
	}
	
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}
	
	public String getTextFromAlert() {
		return driver.switchTo().alert().getText();
	}
	
	public void selectViaVisibleText (WebElement element, String text) {
		Select selDropDown = new Select(element);
		
		selDropDown.selectByVisibleText(text);
	}
	
	public void selectViaValue(WebElement element, String value) throws Exception {
		getSelect(element).selectByValue(value);
	}

	public boolean isElementVisible(WebElement element) throws Exception {
		return element.isDisplayed();
	}
	
	public List<WebElement> getAllOptions(WebElement element) {
		return getSelect(element).getOptions();
	}
	 
	private Select getSelect(WebElement element) {
		Select select = new Select(element);
		return select;
	}
}
