package org.vnpt_technology.selenium;

import org.vnpt_technology.selenium.locator.LoginoutHomepageLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.vnpt_technology.selenium.TestLogger.*;

public class LoginLogout extends LoginoutHomepageLocator {
	// static WebDriver driver = new FirefoxDriver();

	/**
	 * Login page
	 * 
	 * @throws Exception
	 */
	public void loginPage(String username, String password) throws Exception {
		info("login with user " + username + " and pass " + password);
		isElementPresent(ELEMENT_TEXTBOX_USERNAME);
		isElementPresent(ELEMENT_TEXTBOX_PASSWORD);
		waitForAndGetElement(ELEMENT_TEXTBOX_USERNAME, 3000, 0);
		waitForAndGetElement(ELEMENT_TEXTBOX_PASSWORD, 3000, 0);
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

	/**
	 * Go to page Procurement Plan
	 */
	public void gotoPageProcurementPlan() {
		info("go To Procurement Plan Page");
		waitForAndGetElement(ELEMENT_SPAN_PROCUREMNT, 3000, 0);
		click(ELEMENT_SPAN_PROCUREMNT);
		waitForAndGetElement(ELEMENT_SPAN_PROCUREMENT_PLAN, 3000, 0);
		click(ELEMENT_SPAN_PROCUREMENT_PLAN);
		WebElement pageProcurement = waitForAndGetElement(ELEMENT_TITLE_PAGE_PROCUREMENT_PLAN, 3000, 0);
		if (pageProcurement != null) {
			info("Here is page ProcurementPlan");
		} else {
			info("Go to page ProcurementPlan not successfully");
		}

	}

}
