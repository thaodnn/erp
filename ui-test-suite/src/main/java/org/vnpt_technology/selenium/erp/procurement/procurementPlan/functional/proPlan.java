package org.vnpt_technology.selenium.erp.procurement.procurementPlan.functional;

import org.testng.annotations.Test;
import org.vnpt_technology.selenium.LoginLogout;
import org.vnpt_technology.selenium.erp.procurement.procurementPlan.ProcurementPlan;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import static org.vnpt_technology.selenium.TestLogger.info;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class proPlan extends ProcurementPlan {

	LoginLogout logio;

	/**
	 * <li>Case ID:TCProPlan01</li>
	 * <li>Test Case Name: Check list procurement plan</li>
	 */
	@Test
	public void tc_checklistProcurementPlan() {
		info("Test1 : Kiem tra danh sach Procurement plan co cac thong tin: PlanID, Plan Name, Month, Status");
		logio.gotoPageProcurementPlan();
		checklistProcurementPlan();
	}

	/**
	 * <li>Case ID:TCProPlan02</li>
	 * <li>Test Case Name: Check search box</li>
	 */
	@Test(dataProvider = "getTextSearchBox")
	public void tc_checksearchbox(String searchtext) {
		info("Test2: Check action of search box when input text  ");
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
		Object[][] testObjArray = getTableArray(getAbsoluteFilePath("TestData\\Text_Search_Box_Procurement_Plan.xls"),
				"Sheet1", 1);
		return (testObjArray);
	}

	/**
	 * <li>Case ID:TCProPlan03</li>
	 * <li>Test Case Name: Check delete multi procurement plan Items has status
	 * is ready</li>
	 */
	@Test
	public void tc_checkdeletemultippready() {
		info("Test3 : Check delete multi procurement plan Items has status is ready");
		logio.gotoPageProcurementPlan();
		info("List Procurement Plan before delete is: ");
		deleteMultiItemProcurementPlanReady();
		info("List Procurement Plan after delete is: ");
		listProcurementPlan();

	}

	/**
	 * <li>Case ID:TCProPlan04</li>
	 * <li>Test Case Name: Check delete multi procurement plan Items has status
	 * is ready</li>
	 */
	@Test
	public void tc_checkdeletemultippdraft() {
		info("Test4 : Check delete multi procurement plan Items has status is draft");
		logio.gotoPageProcurementPlan();
		info("List Procurement Plan before delete is: ");
		deleteMultiItemProcurementPlanDraft();
		info("List Procurement Plan after delete is: ");
		listProcurementPlan();

	}

	/**
	 * <li>Case ID:TCProPlan05</li>
	 * <li>Test Case Name: Check delete procurement plan Items has status is
	 * ready</li>
	 */
	@Test
	public void tc_checkdeleteitemppdraft() {
		info("Test5 : Check delete procurement plan Items has status is draft");
		logio.gotoPageProcurementPlan();
		info("List Procurement Plan before delete is: ");
		deleteItemProcurementPlanDraft();
		info("List Procurement Plan after delete is: ");
		listProcurementPlan();

	}

	/**
	 * <li>Case ID:TCProPlan06</li>
	 * <li>Test Case Name: Check delete procurement plan Items has status is
	 * ready</li>
	 */
	@Test
	public void tc_checkdeleteitemppready() {
		info("Test6 : Check delete procurement plan Items has status is ready");
		logio.gotoPageProcurementPlan();
		info("List Procurement Plan before delete is: ");
		deleteItemProcurementPlanReady();
		info("List Procurement Plan after delete is: ");
		listProcurementPlan();

	}

	@Test(dataProvider = "dp")
	public void f(Integer n, String s) {
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { 1, "a" }, new Object[] { 2, "b" }, };
	}

	@BeforeClass
	public void beforeClass() throws Exception {
		initSeleniumTest();
		baseUrl = "http://10.84.11.6";
		driver.get(baseUrl);
		logio = new LoginLogout();
		String File_Data_User = getAbsoluteFilePath("TestData\\Login_Logout.xls");
		setExcelFile(File_Data_User, "Sheet1");
		String username = getcelldata(0, 1);
		String password = getcelldata(1, 1);
		pause(20000);
		logio.loginPage(username, password);
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
