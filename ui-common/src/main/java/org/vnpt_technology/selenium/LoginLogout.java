package org.vnpt_technology.selenium;

import org.vnpt_technology.selenium.locator.LoginoutHomepageLocator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.vnpt_technology.selenium.TestLogger.*;


public class LoginLogout extends LoginoutHomepageLocator {
	static WebDriver driver = new FirefoxDriver();
    
	/**
	 * Login page
	 * 
	 * @throws Exception
	 */
	public void loginPage(String username, String password) throws Exception {
		//info("login with user " + username + " and pass " + password);
		type(ELEMENT_TEXTBOX_USERNAME, username, true);
		type(ELEMENT_TEXTBOX_PASSWORD, password, true);
		click(ELEMENT_BUTTON_LOGIN);
		WebElement loginSucess = waitForAndGetElement(ELEMENT_SPAN_SYSTEM, 3000, 0);
		if (loginSucess != null) {
			info("Login not sucessfully");
		} else {
			info("Login sucessfully");
		}
	}

	/**
	 * Logout page
	 * 
	 * @throws Exception
	 */
	public void logoutPage(String URL) throws Exception {
		info("Logout page");
		click(ELEMENT_DROPDOWN_LOGOUT);
		if (waitForAndGetElement(ELEMENT_BUTTON_LOGOUT, 2000, 0) != null) {
			mouseOverAndClick(ELEMENT_BUTTON_LOGOUT);
		}
		WebElement logOutSucess = waitForAndGetElement(ELEMENT_TEXTBOX_USERNAME, 3000, 0);
		if (logOutSucess != null) {
			info("Logout not sucessfully, close brower and init the new one");
			driver.manage().deleteAllCookies();
			driver.get(baseUrl);
		} else {
			info("Logout sucessfully");
		}
	}

}
