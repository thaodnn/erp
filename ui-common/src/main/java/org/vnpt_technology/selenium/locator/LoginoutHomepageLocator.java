package org.vnpt_technology.selenium.locator;

import org.openqa.selenium.By;
import org.vnpt_technology.selenium.Utils;

public class LoginoutHomepageLocator extends Utils {
	public final By ELEMENT_TEXTBOX_USERNAME = By.xpath("//input[@name='username']");
	public final By ELEMENT_TEXTBOX_PASSWORD = By.xpath("//input[@name='password']");
	public final By ELEMENT_BUTTON_LOGIN = By.xpath("//button[@class='btn blue pull-right ladda-button' and @type ='submit']");
	public final By ELEMENT_SPAN_SYSTEM = By.xpath("//span[text()='SYSTEM']");
	public final By ELEMENT_SPAN_PROCUREMNT = By.xpath("//span[text()='PROCUREMENT']");
	public final By ELEMENT_SPAN_PROCUREMENT_PLAN = By.xpath("//a[@href='#/scc/scplan/']");
	public final By ELEMENT_TITLE_PAGE_PROCUREMENT_PLAN = By.xpath("//h3[text()='SCC Purchasing Plan Management']");
	public final By ELEMENT_SPAN_SCBoM = By.xpath("//a[@href='#/scc/scbom']");
	public final By ELEMENT_SPAN_PO = By.xpath("//a[@href='#/scc/po']");
	public final By ELEMENT_SPAN_MANUFACTURER = By.xpath("//a[@href='#/sys/manufacturer']");
	public final By ELEMENT_SPAN_PLAN = By.xpath("//span[text()='PLAN']");
	public final By ELEMENT_SPAN_TARGET_PRODUCTION_PLAN = By.xpath("//a[@href='#/fac/plan-month/view']");
	public final By ELEMENT_PAGE_TARGET_PRODUCTION_PLAN = By.xpath("//span[text()='View Plan Month']");
	public final By ELEMENT_SPAN_SCBOM = By.xpath("//a[@href='#/scc/scbom']");
	public final By ELEMENT_PAGE_SCBOM = By.xpath("//span[text()='SCBOM Manager']");
	public final By ELEMENT_DROPDOWN_LOGOUT = By.xpath("//a[@class='dropdown-toggle']");
	public final By ELEMENT_BUTTON_LOGOUT = By.xpath("//a[@href='#/logout']");

}
