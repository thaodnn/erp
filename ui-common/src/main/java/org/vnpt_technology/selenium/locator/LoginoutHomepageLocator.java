package org.vnpt_technology.selenium.locator;

import org.openqa.selenium.By;
import org.vnpt_technology.selenium.Utils;

public class LoginoutHomepageLocator extends Utils {
	public By ELEMENT_TEXTBOX_USERNAME = By.xpath("//input[@name='username']");
	public By ELEMENT_TEXTBOX_PASSWORD = By.xpath("//input[@name='password']");
	public By ELEMENT_BUTTON_LOGIN = By.xpath("//button[@class='btn blue pull-right ladda-button' and @type ='submit']");
	public By ELEMENT_SPAN_SYSTEM = By.xpath("//span[text()='SYSTEM']");
	public By ELEMENT_SPAN_PROCUREMNT = By.xpath("//span[text()='PROCUREMENT']");
	public By ELEMENT_SPAN_PROCUREMENT_PLAN = By.xpath("//a[@href='#/scc/scplan/']");
	public By ELEMENT_TITLE_PAGE_PROCUREMENT_PLAN = By.xpath("//h3[text()='SCC Purchasing Plan Management']");
	public By ELEMENT_SPAN_SCBoM = By.xpath("//a[@href='#/scc/scbom']");
	public By ELEMENT_SPAN_PO = By.xpath("//a[@href='#/scc/po']");
	public By ELEMENT_SPAN_MANUFACTURER = By.xpath("//a[@href='#/sys/manufacturer']");
	public By ELEMENT_DROPDOWN_LOGOUT = By.xpath("//a[@class='dropdown-toggle']");
	public By ELEMENT_BUTTON_LOGOUT = By.xpath("//a[@href='#/logout']");

}
