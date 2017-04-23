package org.vnpt_technology.selenium.erp.plan.targetProductionPlan;

import org.openqa.selenium.By;
import org.vnpt_technology.selenium.locator.TargetProductionplanLocator;

public class targetproductionplan extends TargetProductionplanLocator {
	public void importNewProductionPlan() {
		waitForAndGetElement(ELEMENT_BUTTON_IMPORT_TARGET_PRODUCTION_PLAN, 3000, 0);
		click(ELEMENT_BUTTON_IMPORT_TARGET_PRODUCTION_PLAN);
		driver.findElement(By.xpath("//div[@class= 'my-drop-zone' and @uploader= 'uploader']")).sendKeys(getAbsoluteFilePath("plan_month_template.xlsx"));
		waitForAndGetElement(ELEMENT_TEXTBOX_NAME_TARGET_PRODUCTION_PLAN, 3000, 0);
		type(ELEMENT_TEXTBOX_NAME_TARGET_PRODUCTION_PLAN, "THAOTESTABC123", true);
		click(ELEMENT_BUTTON_IMPORT_PLAN);
		pause(20000);
		click(ELEMENT_BUTTON_VIEW_TOTAL_PLAN);
	}
}
