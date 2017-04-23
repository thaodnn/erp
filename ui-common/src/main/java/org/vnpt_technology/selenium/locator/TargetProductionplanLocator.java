package org.vnpt_technology.selenium.locator;

import org.openqa.selenium.By;
import org.vnpt_technology.selenium.Utils;

public class TargetProductionplanLocator  extends Utils{
	public By ELEMENT_BUTTON_IMPORT_TARGET_PRODUCTION_PLAN = By.xpath("//a[@href='#/fac/plan-month/import']");
	public By ELEMENT_TEXTBOX_NAME_TARGET_PRODUCTION_PLAN = By.xpath("//input[@data-ng-model='namePlan']");
	public By ELEMENT_BUTTON_IMPORT_PLAN = By.xpath("//span[@class=''and @ng-show='!submitting']");
	public By ELEMENT_BUTTON_VIEW_TOTAL_PLAN = By.xpath("//a[@ui-sref='main.fac.planMonth.view']");
	public By ELEMENT_TOOLTIP_NAME_PLAN = By.xpath("//a[@ng-model='plan' and @tooltip='THAOTESTABC123']");
}
