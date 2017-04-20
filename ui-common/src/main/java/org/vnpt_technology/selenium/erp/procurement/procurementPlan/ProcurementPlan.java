package org.vnpt_technology.selenium.erp.procurement.procurementPlan;

import static org.vnpt_technology.selenium.TestLogger.*;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.vnpt_technology.selenium.locator.ProcurementPlanLocator;

public class ProcurementPlan extends ProcurementPlanLocator {

	/**
	 * go to page procurement Plan
	 */
	public void goToAddprocurementPlan() {
		info("go To Add Procurement Plan ");
		waitForAndGetElement(ELEMENT_BUTTON_PROCUREMENTPLAN_CREATE, 3000, 0);
		click(ELEMENT_BUTTON_PROCUREMENTPLAN_CREATE);
		switchToParentWindow();
	}

	/**
	 * go to view detail procurement Plan
	 */
	public void goToViewdetailprocurementPlan() {
		info("go to view Procurement Plan ");
		waitForAndGetElement(ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_ITEM, 3000, 0);
		click(ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_ITEM);
		switchToParentWindow();
	}

	/**
	 * search with search box
	 */
	public void searchWithsearchbox(String searchtext) {
		info("Type " + searchtext + "into search Box");
		waitForAndGetElement(ELEMENT_TEXTBOX_PROCUREMENTPLAN_SEARCH, 3000, 0);
		type(ELEMENT_TEXTBOX_PROCUREMENTPLAN_SEARCH, searchtext, true);

	}

	/**
	 * Check list title Procuremet Plan
	 */
	public void checklistProcurementPlan() {
		waitForElementPresent(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM_TITLE);
		WebElement titletable = driver.findElement(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM_TITLE);
		int numOfRowtitle = titletable.findElements(By.tagName("tr")).size();
		String first_part = "//div[@class='table-scrollable']/table/thead/tr[";
		String second_part = "]/td[1]";
		List<String> ColumnList = new ArrayList<String>();
		info("danh sah procurement plan co cac thong tin:");
		for (int i = 1; i <= numOfRowtitle; i++) {
			String xpath = first_part + i + second_part;
			String name = driver.findElement(By.xpath(xpath)).getText();
			ColumnList.add(name);
			info(name);
		}
	}

	/**
	 * Check list title Procuremet Plan
	 */
	public void listProcurementPlan() {
		waitForElementPresent(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM);
		WebElement table = driver.findElement(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM);
		int numOfRow = table.findElements(By.tagName("tr")).size();
		int numOfCol = driver.findElements(ELEMENT_NUMCOW_TABLE_PROCUREMNTPLAN).size();
		String first_part = "//div[@class='table-scrollable']/table/tbody/tr[";
		String second_part = "]/td[";
		String third_part = "]";
		List<String> ColumnList = new ArrayList<String>();
		for (int i = 1; i <= numOfRow; i++) {
			for (int j = 1; j <= numOfCol; j++) {
				String xpath = first_part + i + second_part + j + third_part;
				String item = driver.findElement(By.xpath(xpath)).getText();
				ColumnList.add(item);
				info(item);
			}
		}
	}

	/**
	 * Delete multi Procurement plan is approved
	 */
	public void deleteMultiItemProcurementPlanReady() {
		waitForElementPresent(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM);
		WebElement table = driver.findElement(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM);
		int numOfRow = table.findElements(By.tagName("tr")).size();
		int numOfCol = driver.findElements(ELEMENT_NUMCOW_TABLE_PROCUREMNTPLAN).size();
		String first_part = "//div[@class='table-scrollable']/table/tbody/tr[";
		String second_part = "]/td[";
		String third_part = "]";
		for (int i = 1; i <= numOfRow; i++) {
			for (int j = 1; j <= numOfCol; j++) {
				String xpath = first_part + i + second_part + j + third_part;
				String item = driver.findElement(By.xpath(xpath)).getText();
				if (item == "Ready") {
					By ELEMENT_CHECK_BOX = By.xpath(first_part + i + second_part + 1 + third_part);
					check(ELEMENT_CHECK_BOX);
				}
			}
		}
		clickByJavascript(ELEMENT_BUTTON_PROCUREMENTPLAN_DELETE);
		switchToParentWindow();
		clickByJavascript(ELEMENT_BUTTON_COMFIRM_YES);
	}

/**
 * Delete multi Procurement plan is not approved
 */
public void deleteMultiItemProcurementPlanDraft() {
	waitForElementPresent(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM);
	WebElement table = driver.findElement(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM);
	int numOfRow = table.findElements(By.tagName("tr")).size();
	int numOfCol = driver.findElements(ELEMENT_NUMCOW_TABLE_PROCUREMNTPLAN).size();
	String first_part = "//div[@class='table-scrollable']/table/tbody/tr[";
	String second_part = "]/td[";
	String third_part = "]";
	for (int i = 1; i <= numOfRow; i++) {
		for (int j = 1; j <= numOfCol; j++) {
			String xpath = first_part + i + second_part + j + third_part;
			String item = driver.findElement(By.xpath(xpath)).getText();
			if (item == "Draft") {
				By ELEMENT_CHECK_BOX = By.xpath(first_part + i + second_part + 1 + third_part);
				check(ELEMENT_CHECK_BOX);
			}
		}
	}
	clickByJavascript(ELEMENT_BUTTON_PROCUREMENTPLAN_DELETE);
	switchToParentWindow();
	clickByJavascript(ELEMENT_BUTTON_COMFIRM_YES);
}

/**
 * Delete  Procurement plan item is approved
 */
public void deleteItemProcurementPlanReady() {
	waitForElementPresent(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM);
	WebElement table = driver.findElement(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM);
	int numOfRow = table.findElements(By.tagName("tr")).size();
	int numOfCol = driver.findElements(ELEMENT_NUMCOW_TABLE_PROCUREMNTPLAN).size();
	String first_part = "//div[@class='table-scrollable']/table/tbody/tr[";
	String second_part = "]/td[";
	String third_part = "]/button[@class ='btn btn-danger btn-xs ng-isolate-scope']";
	for (int i = 1; i <= numOfRow; i++) {
		for (int j = 1; j <= numOfCol; j++) {
			String xpath = first_part + i + second_part + j + third_part;
			String item = driver.findElement(By.xpath(xpath)).getText();
			if (item == "Ready") {
				By ELEMENT_BUTTON_DELETE_ITEM = By.xpath(first_part + i + second_part + 7 + third_part);
				clickByJavascript(ELEMENT_BUTTON_DELETE_ITEM);
				switchToParentWindow();
				clickByJavascript(ELEMENT_BUTTON_COMFIRM_YES);
			}
		}
	}
}

/**
 * Delete Procurement plan item is not approved
 */
public void deleteItemProcurementPlanDraft() {
	waitForElementPresent(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM);
	WebElement table = driver.findElement(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM);
	int numOfRow = table.findElements(By.tagName("tr")).size();
	int numOfCol = driver.findElements(ELEMENT_NUMCOW_TABLE_PROCUREMNTPLAN).size();
	String first_part = "//div[@class='table-scrollable']/table/tbody/tr[";
	String second_part = "]/td[";
	String third_part = "]/button[@class ='btn btn-danger btn-xs ng-isolate-scope']";
	for (int i = 1; i <= numOfRow; i++) {
		for (int j = 1; j <= numOfCol; j++) {
			String xpath = first_part + i + second_part + j + third_part;
			String item = driver.findElement(By.xpath(xpath)).getText();
			if (item == "Draft") {
				By ELEMENT_BUTTON_DELETE_ITEM = By.xpath(first_part + i + second_part + 7 + third_part);
				clickByJavascript(ELEMENT_BUTTON_DELETE_ITEM);
				switchToParentWindow();
				clickByJavascript(ELEMENT_BUTTON_COMFIRM_YES);
			}
		}
	}
}


}
