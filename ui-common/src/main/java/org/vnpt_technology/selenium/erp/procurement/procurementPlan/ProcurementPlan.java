package org.vnpt_technology.selenium.erp.procurement.procurementPlan;

import static org.vnpt_technology.selenium.TestLogger.*;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.vnpt_technology.selenium.LoginLogout;
import org.vnpt_technology.selenium.locator.ProcurementPlanLocator;

public class ProcurementPlan extends ProcurementPlanLocator {
	LoginLogout logio = new LoginLogout();
	
	/**
	 * Login page
	 * 
	 * @throws Exception
	 */
	public void loginPage(String username, String password) throws Exception {
		info("login with user " + username + " and pass " + password);
		//waitForElementPresent(ELEMENT_TEXTBOX_USERNAME);
		//waitForElementPresent(ELEMENT_TEXTBOX_PASSWORD);
		//waitForAndGetElement(ELEMENT_TEXTBOX_USERNAME, 3000, 0);
		//waitForAndGetElement(ELEMENT_TEXTBOX_PASSWORD, 3000, 0);
		//driver.findElement(By.xpath("//input[@ng-model='user.email']")).sendKeys("admin");
		//driver.findElement(By.xpath("//input[@ng-model='user.password']")).sendKeys("20071649");
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
	 * go to page procurement Plan
	 */
	public void goToAddprocurementPlan(String monthtext) {
		info("go To Add Procurement Plan ");
		waitForAndGetElement(ELEMENT_BUTTON_PROCUREMENTPLAN_CREATE, 3000, 0);
		click(ELEMENT_BUTTON_PROCUREMENTPLAN_CREATE);
		switchToParentWindow();
		type(ELEMENT_TEXTBOX_PROCUREMENTPLAN_ADD_MONTH, monthtext, true);
		pause(3000);
	}

	/**
	 * go to view detail procurement Plan
	 */
	public void goToViewdetailprocurementPlan(String month, String status) {
		waitForElementPresent(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM);
		WebElement table = driver.findElement(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM);
		int numOfRow = table.findElements(By.tagName("tr")).size();
		int numOfCol = driver.findElements(ELEMENT_NUMCOW_TABLE_PROCUREMNTPLAN).size();
		String first_part = "//div[@class='table-scrollable']/table/tbody/tr[";
		String second_part = "]/td[";
		String third_part = "]";
		String fourth_part = "]/button[@ng-click ='showModalEditPlan(item)']";
		for (int i = 1; i <= numOfRow; i++) {
			for (int j = 1; j <= numOfCol; j++) {
				String xpathmonth = first_part + i + second_part + 5 + third_part;
				String xpathstatus = first_part + i + second_part + 5 + third_part;
				By ELEMENT_VIEW_BUTTON = By.xpath(first_part + i + second_part + 5 + fourth_part);
				String monthitem = driver.findElement(By.xpath(xpathmonth)).getText();
				String statusitem = driver.findElement(By.xpath(xpathstatus)).getText();
				if (month == monthitem && status == statusitem) {
					clickByJavascript(ELEMENT_VIEW_BUTTON);
					switchToParentWindow();
					waitForElementPresent(ELEMENT_TEXTBOX_PROCUREMENTPLAN_VIEW_PRODUCTNAME);
					if (isElementPresent(ELEMENT_TEXTBOX_PROCUREMENTPLAN_VIEW_PRODUCTNAME) == true) {
						info("view detail procurement Plan successfully");
						break;
					} else {
						info("view detail procurement Plan not successfully");
					}

				} else {
					info("check data test");
				}
			}
		}
	}

	/**
	 * add product to procurement plan
	 */
	public void updatePPPlanAddProduct() {
		waitForElementPresent(ELEMENT_TEXTBOX_PROCUREMENTPLAN_VIEW_PRODUCTNAME);
		WebElement tableproduct = driver.findElement(By.xpath("//div[@class='modal-footer']/table/tbody"));
		int numOfRowbefore = tableproduct.findElements(By.tagName("tr")).size();
		Select select = new Select(driver.findElement(ELEMENT_TEXTBOX_PROCUREMENTPLAN_VIEW_PRODUCTNAME));
		select.selectByIndex(0);
		type(ELEMENT_TEXTBOX_PROCUREMENTPLAN_VIEW_QUANTITY, "135", true);
		pause(2000);
		click(ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_ADD_PRODUCT);
		pause(2000);
		int numOfRowafter = tableproduct.findElements(By.tagName("tr")).size();
		if (numOfRowafter > numOfRowbefore) {
			info("add product successfully");
		} else {
			info("add product not successfully");
		}
	}

	/**
	 * subtract product to procurement plan
	 */
	public void updatePPPlansubProduct() {
		waitForElementPresent(ELEMENT_TEXTBOX_PROCUREMENTPLAN_VIEW_PRODUCTNAME);
		WebElement tableproduct = driver.findElement(By.xpath("//div[@class='modal-footer']/table/tbody"));
		int numOfRowbeforesub = tableproduct.findElements(By.tagName("tr")).size();
		By ELEMENT_CHECK_BOX_PRODUCT = By.xpath("//div[@class='modal-footer']/table/tbody/tr[1]/td[1]");
		check(ELEMENT_CHECK_BOX_PRODUCT);
		pause(2000);
		click(ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_SUBTRACT_PRODUCT);
		pause(2000);
		int numOfRowaftersub = tableproduct.findElements(By.tagName("tr")).size();
		if (numOfRowbeforesub > numOfRowaftersub) {
			info("subtract product successfully");
		} else {
			info("subtract product not successfully");
		}
	}

	/**
	 * approve procurement plan
	 */
	public void approvepp() {
		waitForElementPresent(ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_APPROVE);
		pause(2000);
		click(ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_APPROVE);
		pause(3000);
		if (isElementPresent(ELEMENT_LABEL_PROCUREMENTPLAN_VIEW_STATUS_PLAN_READY) == true) {
			info("Apprrove procurement plan  successfully");
		} else {
			info("Apprrove procurement plan not successfully");
		}
	}

	/**
	 * order product of procurement plan
	 */
	public void orderproduct() {
		waitForElementPresent(ELEMENT_TEXTBOX_PROCUREMENTPLAN_VIEW_PRODUCTNAME);
		By ELEMENT_ORDER_PRODUCT = By.xpath("//div[@class='modal-footer']/table/tbody/tr[1]/td[11]");
		click(ELEMENT_ORDER_PRODUCT);
		pause(5000);
		waitForElementPresent(ELEMENT_TAB_INITIALIZE);
		if (isElementPresent(ELEMENT_TAB_INITIALIZE) == true) {
			info("Order product successfully");
		} else {
			info("Order product not successfully");
		}
	}

	/**
	 * Check generate product to SCBom
	 */
	public void generatetoSCBOM() {
		waitForElementPresent(ELEMENT_TEXTBOX_PROCUREMENTPLAN_VIEW_PRODUCTNAME);
		WebElement tableproduct = driver.findElement(By.xpath("//div[@class='modal-footer']/table/tbody"));
		int numOfRow = tableproduct.findElements(By.tagName("tr")).size();
		int numOfCol = driver.findElements(By.xpath("//div[@class='modal-footer']/table/tbody/tr[1]/td")).size();
		String first_part = "//div[@class='table-scrollable']/table/tbody/tr[";
		String second_part = "]/td[";
		String fourth_part = "]/button[@ng-click ='showModalEditPlan(item)']";
		String fiveth_part = "]/span]";
		for (int i = 1; i <= numOfRow; i++) {
			for (int j = 1; j <= numOfCol; j++) {
				String xpathstatus = first_part + i + second_part + 9 + fiveth_part;
				String statusitem = driver.findElement(By.xpath(xpathstatus)).getText();
				if (statusitem == "Not Buy") {
					By ELEMENT_ORDER_PRODUCT = By.xpath(first_part + i + second_part + 11 + fourth_part);
					click(ELEMENT_ORDER_PRODUCT);
					waitForElementPresent(ELEMENT_TAB_INITIALIZE);
					click(ELEMENT_TAB_CREATE_SCBOM);
					waitForElementPresent(ELEMENT_BUTTON_GENNERATE_SCBOM);
					WebElement element1 = driver.findElement(ELEMENT_TEXT_NAME_SCBOM);
					element1.sendKeys(Keys.chord(Keys.CONTROL, "a"));
					element1.sendKeys(Keys.chord(Keys.CONTROL, "c"));
					click(ELEMENT_BUTTON_GENNERATE_SCBOM);
					waitForElementnotPresent(ELEMENT_BUTTON_GENNERATE_SCBOM);
					logio.gotoPageScBom();
					WebElement element2 = driver.findElement(ELEMENT_SEARCH_BOX_SCBOM);
					element2.click();
					element2.sendKeys(Keys.chord(Keys.CONTROL, "v"));
					pause(3000);
					if (isElementNotPresent(ELEMENT_NO_FIND_RESULT_SCBOM) == true) {
						info("generate ScBom successfully");
					} else {
						info("generate ScBom not successfully");
					}
					break;
				} else {
					info("Have not product not buy");
				}
			}
		}
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
	 * Check list title Procurement Plan
	 */
	public void checklistProcurementPlan() {
		waitForElementPresent(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM_TITLE);
		WebElement titletable = driver.findElement(ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM_TITLE);
		int numOfRowtitle = titletable.findElements(By.tagName("tr")).size();
		String first_part = "//div[@class='table-scrollable']/table/thead/tr[";
		String second_part = "]/td[1]";
		List<String> ColumnList = new ArrayList<String>();
		info("danh sach procurement plan co cac thong tin:");
		for (int i = 1; i <= numOfRowtitle; i++) {
			String xpath = first_part + i + second_part;
			String name = driver.findElement(By.xpath(xpath)).getText();
			ColumnList.add(name);
			info(name);
		}
	}

	/**
	 * Check list title Procurement Plan
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
	 * Delete Procurement plan item is approved
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
					break;
				}
			}
		}
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
					break;
				}
			}
		}
	}

}
