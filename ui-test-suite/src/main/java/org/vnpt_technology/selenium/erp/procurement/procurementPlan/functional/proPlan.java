package org.vnpt_technology.selenium.erp.procurement.procurementPlan.functional;

import org.testng.annotations.Test;
import org.vnpt_technology.selenium.LoginLogout;
import org.vnpt_technology.selenium.erp.procurement.procurementPlan.ProcurementPlan;
import org.vnpt_technology.selenium.locator.LoginoutHomepageLocator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import static org.vnpt_technology.selenium.TestLogger.info;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class proPlan extends ProcurementPlan {

	LoginLogout logio;
	LoginoutHomepageLocator loginlor;

	/**
	 * <li>Case ID:TCProPlan01</li>
	 * <li>Test Case Name: Check list procurement plan</li>
	 */
	@Test(priority = 1)
	public void tc_checklistProcurementPlan() {
		info("Test : Kiem tra danh sach Procurement plan co cac thong tin: PlanID, Plan Name, Month, Status");
		logio.gotoPageProcurementPlan();
		checklistProcurementPlan();
	}

	/**
	 * <li>Case ID:TCProPlan02</li>
	 * <li>Test Case Name: Check search box</li>
	 */
	@Test(dataProvider = "getTextSearchBox", priority = 2)
	public void tc_checksearchbox(String searchtext) {
		info("Test: Check action of search box when input text  ");
		logio.gotoPageProcurementPlan();
		pause(3000);
		searchWithsearchbox(searchtext);
		pause(3000);
		if (isElementPresent(ELEMENT_TEXT_NO_RESULTS) == true) {
			info("System inform no results for " + searchtext);
		} else if (isElementPresent(ELEMENT_TEXT_NO_RESULTS) == false) {
			info("System inform results for " + searchtext + "is: ");
			listProcurementPlan();
		}
	}

	@DataProvider
	public Object[][] getTextSearchBox() throws Exception {
		Object[][] testObjArray = getTableArray(getAbsoluteFilePath("TestData\\Procurement_Plan.xls"), "Sheet1", 1);
		return (testObjArray);
	}

	/**
	 * <li>Case ID:TCProPlan03</li>
	 * <li>Test Case Name: Check delete multi procurement plan Items has status
	 * is ready</li>
	 */
	@Test(priority = 3)
	public void tc_checkdeletemultippready() {
		info("Test : Check delete multi procurement plan Items has status is ready");
		logio.gotoPageProcurementPlan();
		info("List Procurement Plan before delete is: ");
		listProcurementPlan();
		deleteMultiItemProcurementPlanReady();
		info("List Procurement Plan after delete is: ");
		listProcurementPlan();

	}

	/**
	 * <li>Case ID:TCProPlan04</li>
	 * <li>Test Case Name: Check delete procurement plan Items has status is
	 * ready</li>
	 */
	@Test(priority = 4)
	public void tc_checkdeleteitemppready() {
		info("Test : Check delete procurement plan Items has status is ready");
		logio.gotoPageProcurementPlan();
		info("List Procurement Plan before delete is: ");
		listProcurementPlan();
		deleteItemProcurementPlanReady();
		info("List Procurement Plan after delete is: ");
		listProcurementPlan();

	}

	/**
	 * <li>Case ID:TCProPlan05</li>
	 * <li>Test Case Name: Check add procurement plan in month have not target
	 * production plan</li>
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5)
	public void tc_checkaddprocurement1() throws Exception {
		info("Test : Check add procurement plan in month have not target production plan ");
		String File_Procurement_Plan = getAbsoluteFilePath("TestData\\Procurement_Plan.xls");
		setExcelFile(File_Procurement_Plan, "Sheet2");
		String monthText = getcelldata(0, 1);
		logio.gotoPageProcurementPlan();
		info("List Procurement Plan before add is: ");
		listProcurementPlan();
		goToAddprocurementPlan(monthText);
		if (isElementPresent(ELEMENT_TEXT_PRODUCT_NO_RESULTS) == true) {
			if (isElementPresent(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_SAVE_DISABLE) == true) {
				info("PASS: can't press button save");
				click(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_CANCEL);
				switchToParentWindow();
			} else {
				info("FAIL: can press button save");
				click(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_CANCEL);
				switchToParentWindow();
			}
		} else {
			info("month have target production plan, check data test again ");
			click(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_CANCEL);
			switchToParentWindow();

		}
	}

	/**
	 * <li>Case ID:TCProPlan07</li>
	 * <li>Test Case Name: Check add procurement plan have target production
	 * plan production plan</li>
	 * 
	 * @throws Exception
	 */
	@Test(priority = 7)
	public void tc_checkaddprocurement2() throws Exception {
		info("Test : Check add procurement plan in month have target production plan ");
		String File_Procurement_Plan = getAbsoluteFilePath("TestData\\Procurement_Plan.xls");
		setExcelFile(File_Procurement_Plan, "Sheet2");
		String monthText = getcelldata(1, 1);
		logio.gotoPageProcurementPlan();
		info("List Procurement Plan before add is: ");
		listProcurementPlan();
		goToAddprocurementPlan(monthText);
		if (isElementPresent(ELEMENT_TEXT_PRODUCT_NO_RESULTS) == true) {
			info("month have not target production plan, check data test again ");
			click(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_CANCEL);
		} else {
			info("month have target production plan ");
			info("not select production:  ");
			if (isElementPresent(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_SAVE_DISABLE) == true) {
				info("can't press button save when not select production");
				pause(3000);
				WebElement table = driver.findElement(ELEMENT_TABLE_LIST_PRODUCT);
				int numOfRow = table.findElements(By.tagName("tr")).size();
				int numOfCol = driver.findElements(ELEMENT_NUMCOW_TABLE_PROCUREMNTPLAN).size();
				String first_part = "//div[@class='table-scrollable']/table/tbody/tr[";
				String second_part = "]/td[";
				String third_part = "]";
				for (int i = 1; i <= numOfRow; i++) {
					for (int j = 1; j <= numOfCol; j++) {
						By ELEMENT_CHECK_BOX = By.xpath(first_part + i + second_part + 1 + third_part);
						check(ELEMENT_CHECK_BOX);
						By ELEMENT_QUANTITY = By.xpath(first_part + i + second_part + 6 + third_part);
						type(ELEMENT_QUANTITY, "1", true);
					}
				}
				info("select production:  ");
				if (isElementPresent(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_SAVE_DISABLE) == false) {
					info(" press button save ");
					String planname = getText(ELEMENT_TEXTBOX_PROCUREMENTPLAN_ADD_PLANNAME);
					click(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_SAVE);
					switchToParentWindow();
					if (isElementPresent(ELEMENT_POPUP_VIEW_PROCUREMENT_PLAN) == true) {
						info(" create procurement plan" + planname + "successfully");
						refreshPage();
						info("List Procurement Plan after add is: ");
						listProcurementPlan();
					} else {
						info(" create procurement plan" + planname + " not successfully");
						refreshPage();
					}
				} else {
					info("can't press button save when select production, create procurement plan  not successfully");
					click(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_CANCEL);
				}
			} else {
				info("can press button save when not select production");
				click(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_CANCEL);
			}
		}
	}

	/**
	 * <li>Case ID:TCProPlan07</li>
	 * <li>Test Case Name: Check add procurement plan have 2 target production
	 * plan production plan</li>
	 * 
	 * @throws Exception
	 */
	@Test(priority = 6)
	public void tc_checkaddprocurement3() throws Exception {
		info("Test : Check add procurement plan in month have 2 target production plan ");
		String File_Procurement_Plan = getAbsoluteFilePath("TestData\\Procurement_Plan.xls");
		setExcelFile(File_Procurement_Plan, "Sheet2");
		String monthText = getcelldata(2, 1);
		logio.gotoPageProcurementPlan();
		info("List Procurement Plan before add is: ");
		listProcurementPlan();
		goToAddprocurementPlan(monthText);
		if (isElementPresent(ELEMENT_TEXT_PRODUCT_NO_RESULTS) == true) {
			info("month have not target production plan, check data test again ");
			click(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_CANCEL);
		} else {
			info("month have target production plan ");
			info("not select production:  ");
			if (isElementPresent(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_SAVE_DISABLE) == true) {
				info("can't press button save when not select production");
				pause(3000);
				WebElement table = driver.findElement(ELEMENT_TABLE_LIST_PRODUCT);
				int numOfRow = table.findElements(By.tagName("tr")).size();
				int numOfCol = driver.findElements(ELEMENT_NUMCOW_TABLE_PROCUREMNTPLAN).size();
				String first_part = "//div[@class='table-scrollable']/table/tbody/tr[";
				String second_part = "]/td[";
				String third_part = "]";
				for (int i = 1; i <= numOfRow; i++) {
					for (int j = 1; j <= numOfCol; j++) {
						By ELEMENT_CHECK_BOX = By.xpath(first_part + i + second_part + 1 + third_part);
						check(ELEMENT_CHECK_BOX);
						By ELEMENT_QUANTITY = By.xpath(first_part + i + second_part + 6 + third_part);
						type(ELEMENT_QUANTITY, "1", true);
					}
				}
				info("select production:  ");
				if (isElementPresent(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_SAVE_DISABLE) == false) {
					info(" press button save ");
					String planname = getText(ELEMENT_TEXTBOX_PROCUREMENTPLAN_ADD_PLANNAME);
					click(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_SAVE);
					switchToParentWindow();
					if (isElementPresent(ELEMENT_POPUP_VIEW_PROCUREMENT_PLAN) == true) {
						info(" create procurement plan" + planname + "successfully");
						refreshPage();
						info("List Procurement Plan after add is: ");
						listProcurementPlan();
					} else {
						info(" create procurement plan" + planname + " not successfully");
						refreshPage();
					}
				} else {
					info("can't press button save when select production, create procurement plan  not successfully");
					click(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_CANCEL);
				}
			} else {
				info("can press button save when not select production");
				click(ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_CANCEL);
			}
		}
	}

	/**
	 * <li>Case ID:TCProPlan07</li>
	 * <li>Test Case Name: Check update add product of procurement plan draft</li>
	 * 
	 * @throws Exception
	 */
	@Test(priority = 7)
	public void tc_checkaddproductofprocurementdraft() throws Exception {
		info("Test : Check add product to procurement plan draff ");
		String File_Procurement_Plan = getAbsoluteFilePath("TestData\\Procurement_Plan.xls");
		setExcelFile(File_Procurement_Plan, "Sheet2");
		String monthText = getcelldata(1, 1);
		logio.gotoPageProcurementPlan();
		goToViewdetailprocurementPlan(monthText, "Draft");
		updatePPPlanAddProduct() ;
	}
	

	/**
	 * <li>Case ID:TCProPlan08</li>
	 * <li>Test Case Name: Check update add product of procurement plan ready</li>
	 * 
	 * @throws Exception
	 */
	@Test(priority = 8)
	public void tc_checkupdateproductofprocurementready() throws Exception {
		info("Test : Check add product to procurement plan ready ");
		String File_Procurement_Plan = getAbsoluteFilePath("TestData\\Procurement_Plan.xls");
		setExcelFile(File_Procurement_Plan, "Sheet2");
		String monthText = getcelldata(1, 1);
		logio.gotoPageProcurementPlan();
		goToViewdetailprocurementPlan(monthText, "Ready");
		updatePPPlanAddProduct() ;
	}
	
	

	/**
	 * <li>Case ID:TCProPlan08</li>
	 * <li>Test Case Name: Check approve of procurement plan ready</li>
	 * 
	 * @throws Exception
	 */
	@Test(priority = 8)
	public void tc_checkapproveofprocurementready() throws Exception {
		info("Test : Check add product to procurement plan ready ");
		String File_Procurement_Plan = getAbsoluteFilePath("TestData\\Procurement_Plan.xls");
		setExcelFile(File_Procurement_Plan, "Sheet2");
		String monthText = getcelldata(1, 1);
		logio.gotoPageProcurementPlan();
		goToViewdetailprocurementPlan(monthText, "Ready");
		approvepp() ;
	}


	/**
	 * <li>Case ID:TCProPlan08</li>
	 * <li>Test Case Name: Check approve of procurement plan ready</li>
	 * 
	 * @throws Exception
	 */
	@Test(priority = 8)
	public void tc_checkapproveofprocurementdraft() throws Exception {
		info("Test : Check add product to procurement plan ready ");
		String File_Procurement_Plan = getAbsoluteFilePath("TestData\\Procurement_Plan.xls");
		setExcelFile(File_Procurement_Plan, "Sheet2");
		String monthText = getcelldata(1, 1);
		logio.gotoPageProcurementPlan();
		goToViewdetailprocurementPlan(monthText, "Ready");
		approvepp() ;
	}
	

	/**
	 * <li>Case ID:TCProPlan08</li>
	 * <li>Test Case Name: Check approve of procurement plan ready</li>
	 * 
	 * @throws Exception
	 */
	@Test(priority = 8)
	public void tc_checkoderproductofprocurementdraft() throws Exception {
		info("Test : Check add product to procurement plan ready ");
		String File_Procurement_Plan = getAbsoluteFilePath("TestData\\Procurement_Plan.xls");
		setExcelFile(File_Procurement_Plan, "Sheet2");
		String monthText = getcelldata(1, 1);
		logio.gotoPageProcurementPlan();
		goToViewdetailprocurementPlan(monthText, "draft");
		approvepp() ;
	}
	

	/**
	 * <li>Case ID:TCProPlan08</li>
	 * <li>Test Case Name: Check approve of procurement plan ready</li>
	 * 
	 * @throws Exception
	 */
	@Test(priority = 8)
	public void tc_checkoderproductofprocurementready() throws Exception {
		info("Test : Check add product to procurement plan ready ");
		String File_Procurement_Plan = getAbsoluteFilePath("TestData\\Procurement_Plan.xls");
		setExcelFile(File_Procurement_Plan, "Sheet2");
		String monthText = getcelldata(1, 1);
		logio.gotoPageProcurementPlan();
		goToViewdetailprocurementPlan(monthText, "Ready");
		approvepp() ;
	}
	
	/**
	 * <li>Case ID:TCProPlan04</li>
	 * <li>Test Case Name: Check delete multi procurement plan Items has status
	 * is ready</li>
	 */
	@Test(priority = 5)
	public void tc_checkdeletemultippdraft() {
		info("Test : Check delete multi procurement plan Items has status is draft");
		logio.gotoPageProcurementPlan();
		info("List Procurement Plan before delete is: ");
		listProcurementPlan();
		deleteMultiItemProcurementPlanDraft();
		info("List Procurement Plan after delete is: ");
		listProcurementPlan();

	}

	/**
	 * <li>Case ID:TCProPlan05</li>
	 * <li>Test Case Name: Check delete procurement plan Items has status is
	 * ready</li>
	 */
	@Test(priority = 7)
	public void tc_checkdeleteitemppdraft() {
		info("Test : Check delete procurement plan Items has status is draft");
		logio.gotoPageProcurementPlan();
		info("List Procurement Plan before delete is: ");
		listProcurementPlan();
		deleteItemProcurementPlanDraft();
		info("List Procurement Plan after delete is: ");
		listProcurementPlan();

	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@BeforeClass
	public void beforeClass() throws Exception {
		initSeleniumTest();
		baseUrl = "http://10.84.11.6";
		driver.get(baseUrl);
		logio = new LoginLogout();
		loginlor = new LoginoutHomepageLocator();
		String File_Data_User = getAbsoluteFilePath("TestData\\Login_Logout.xls");
		setExcelFile(File_Data_User, "Sheet1");
		String username = getcelldata(0, 1);
		String password = getcelldata(1, 1);
		pause(5000);
	/*	type(loginlor.ELEMENT_TEXTBOX_USERNAME, username, true);
		type(loginlor.ELEMENT_TEXTBOX_PASSWORD, password, true);
		click(loginlor.ELEMENT_BUTTON_LOGIN);
		WebElement loginSucess = waitForAndGetElement(loginlor.ELEMENT_SPAN_SYSTEM, 3000, 0);
		if (loginSucess == null) {
			info("Login not sucessfully");
		} else {
			info("Login sucessfully");
		}*/
		loginPage(username , password);
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
	}

	@AfterSuite
	public void afterSuite() {
	}

}
