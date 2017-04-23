package org.vnpt_technology.selenium.erp.plan.targetProductionPlan.functional;

import org.testng.annotations.Test;
import org.vnpt_technology.selenium.LoginLogout;
import org.vnpt_technology.selenium.erp.plan.targetProductionPlan.targetproductionplan;
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

public class ImportTargetProductionPlan extends targetproductionplan {
	LoginLogout logio;

	@Test
	public void importargetproductionplan() {
		info("Test1 : Import target production plan");
		logio.gotoPageProcurementPlan();
		importNewProductionPlan();
		pause(3000);
		if (isElementPresent(ELEMENT_TOOLTIP_NAME_PLAN) == true) {
			info("Import target production plan successfully");
		} else {
			info("Import target production plan not successfully ");
			refreshPage();
		}
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
