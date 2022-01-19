/*
 * This automated test is part of automation framework created for Nordic Global
 * To run it, go to Run_tests folder, open XML file and add the script name to test group.
 * Change any parameter in this test, go to XML file in testXmlFiles folder
 * 
*/


package tests.NordicGlobal;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import pageObjects.pages.NordicPages.HomePage_page;
import tests.NordicGlobal.BaseTest;


public class Validate_Elements_in_HomePage extends BaseTest {
	
	
	//Test Case 1
	@Test(groups ="Regression")
	public void Validate_Elements_in_HomePage_test () throws Exception{
			
			//Setting up 
			driver = cmnDriver.getDriver();
			driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
			
			//creating object to call the functions and elements
			HomePage_page obj = new HomePage_page(driver);
			
			//Test Start
			reportUtils.createAtestCase("Open Nordic Home Page and get page status");
			//Accepting cookies
			obj.accept_cookies();
			
			
			//get Page health status

			reportUtils.addTestLog(Status.INFO, "Home Page validated");
			getStatusCodeandURL(driver.getCurrentUrl());
			
			//Validate Web Elements
			reportUtils.createAtestCase("Validate Home Page elements");
			reportUtils.addTestLog(Status.INFO, "Home Page check start ");
			obj.validateHomePageElements();
			
			//Click in hamburguer menu
			reportUtils.createAtestCase("Click in Hamburguer menu");
			obj.clickInHamburguerMenu();
			
			reportUtils.createAtestCase("Validate expand buttons inside hamburguer menu");
			obj.clickInItemsInsideHamburguerMenu();
			reportUtils.addTestLog(Status.INFO, "Expand buttons are working correctly");
			
			//Close hamburguer menu
			reportUtils.createAtestCase("Close hamburguer menu");
			obj.clickInCloseButton_in_menu();
			
			getStatusCodeandURL(driver.getCurrentUrl());

			reportUtils.addTestLog(Status.PASS, "Test finished");
			
	}

	

}
