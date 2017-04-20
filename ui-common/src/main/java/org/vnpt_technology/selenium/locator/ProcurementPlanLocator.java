package org.vnpt_technology.selenium.locator;
import org.openqa.selenium.By;
import org.vnpt_technology.selenium.Utils;

public class ProcurementPlanLocator extends Utils {
	// Page Procurement plan
	public By ELEMENT_TEXTBOX_PROCUREMENTPLAN_SEARCH = By.xpath("//input[@name='username']");
	public By ELEMENT_TEXTBOX_PROCUREMENTPLAN_SEARCH_MONTH = By.xpath("//input[@name='password']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_DELETE = By.xpath("//button[@class='btn btn-danger' and @ng-disabled='check.list == 0']");
	public By ELEMENT_BUTTON_COMFIRM_YES = By.xpath("//button[text()='Yes']");
	public By ELEMENT_BUTTON_COMFIRM_No = By.xpath("//button[text()='No']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_DELETE_DISABLE = By.xpath("//button[@class='btn btn-danger' and @ng-disabled='check.list == 0' and @disabled='disabled']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_CREATE = By.xpath("//button[@class='btn btn-primary' and @ng-click='showModalCreate()']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_ITEM = By.xpath("//button[@class='btn btn-primary btn-xs' and @ng-click='showModalEditPlan(item)']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_DELETE_ITEM = By.xpath("//button[@class='btn btn-danger btn-xs' and @on-confirm='deleteItem(item)']");
	public By ELEMENT_LIST_PROCUREMENTLAN_ITEM_PLANID = By.xpath("//a[text()='Plan ID']");
	public By ELEMENT_LIST_PROCUREMENTLAN_ITEM_PLANNAME = By.xpath("//a[text()='Purchasing Plan's name']");
	public By ELEMENT_LIST_PROCUREMENTLAN_ITEM_MONTH = By.xpath("//a[text()=' Month']");
	public By ELEMENT_LIST_PROCUREMENTLAN_ITEM_STATUS = By.xpath("//a[text()='Status']");
	public By ELEMENT_TEXT_NO_RESULTS = By.xpath("//p[text()='No results']");
	public By ELEMENT_ROWS_ITEM_2 = By.xpath("//td[text()='2']");
	public By ELEMENT_RESULTS_SEARCH_NAME = By.xpath("//a[@class='ng-binding' and @ng-click='showModalEditPlan(item)']");
	public By ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM = By.xpath("//div[@class='table-scrollable']/table/tbody");
	public By ELEMENT_TABLE_LIST_PROCURMENTPLAN_ITEM_TITLE = By.xpath("//div[@class='table-scrollable']/table/thead");
	public By ELEMENT_NUMCOW_TABLE_PROCUREMNTPLAN = By.xpath("//div[@class='table-scrollable']/table/thead/tr[1]/td");
	//Display Add Procurement Plan
	public By ELEMENT_TEXTBOX_PROCUREMENTPLAN_ADD_PLANNAME = By.xpath("//input[@id='name']");
	public By ELEMENT_TEXTBOX_PROCUREMENTPLAN_ADD_MONTH = By.xpath("//input[@ng-model='item.plan_date' and @class='form-control form-control date-picker ng-pristine ng-untouched ng-valid']");
	public By ELEMENT_TEXTBOX_PROCUREMENTPLAN_ADD_PLAN = By.xpath("//input[@id='select_sys_plan']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_SAVE = By.xpath("//button[@class='btn btn-primary ladda-button' and @ng-click='saveScPlan()']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_SAVE_DISABLE = By.xpath("//button[@class='btn btn-primary ladda-button' and @ng-click='saveScPlan()'and @disabled='disabled']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_RESET = By.xpath("//button[@class='btn btn-warning' and @ng-click='resetModal()']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_ADD_CANCEL = By.xpath("//button[@class='btn btn-default' and @ng-click='cancelModal()']");
	//Display View Procurement Plan
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_APPROVE = By.xpath("//button[@class='btn green' and @ng-click='updateStatusScPlan(pr)']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_APPROVE_DISABLE = By.xpath("//button[@class='btn green' and @ng-click='updateStatusScPlan(pr)'and @disabled='disabled']");
	public By ELEMENT_TEXTBOX_PROCUREMENTPLAN_VIEW_MONTH = By.xpath("//input[@ng-model='item.plan_date'and @class='form-control form-control-inline date-picker2 ng-pristine ng-untouched ng-valid']");
	public By ELEMENT_TEXTBOX_PROCUREMENTPLAN_VIEW_PRODUCTNAME = By.xpath("//select[@class='form-control ng-pristine ng-untouched ng-valid'and @ng-model='_item.product_name']");
	public By ELEMENT_TEXTBOX_PROCUREMENTPLAN_VIEW_QUANTITY = By.xpath("//input[@class='form-control ng-pristine ng-untouched ng-invalid ng-invalid-required' and @ng-model='_item.quantity']");
	public By ELEMENT_TEXTBOX_PROCUREMENTPLAN_VIEW_STARTDATE = By.xpath("//input[@class='form-control form-control-inline date-picker ng-pristine ng-untouched ng-valid'and @ng-model='_item.start_time']");
	public By ELEMENT_TEXTBOX_PROCUREMENTPLAN_VIEW_ENDDATE = By.xpath("//input[@class='form-control form-control-inline date-picker ng-pristine ng-untouched ng-valid' and  @ng-model='_item.end_time']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_ADD_PRODUCT = By.xpath("//button[@class='btn btn-primary pull-right'and @ng-click='addProductToListEdit()']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_SUBTRACT_PRODUCT = By.xpath("//button[@class='btn btn-danger pull-right ng-isolate-scope'and @on-confirm='delProductInListEdit()']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_ADD_PRODUCT_DISABLE = By.xpath("//button[@class='btn btn-primary pull-right'and @ng-click='addProductToListEdit()' and @disabled='disabled']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_SUBTRACT_PRODUCT_DISABLE = By.xpath("//button[@class='btn btn-danger pull-right ng-isolate-scope'and @on-confirm='delProductInListEdit()'and @disabled='disabled']");
	public By ELEMENT_TEXTBOX_PROCUREMENTPLAN_VIEW_MODIFY_QUANTITY_ITEM_PRODUCT = By.xpath("//input[@class='form-control input-sm input-small ng-pristine ng-untouched ng-valid' and @ng-model='pr.quantity']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_MODIFY_QUANTITY_ITEM_PRODUCT = By.xpath("//a[@class='btn btn-sm green' and @ng-click='updateQuantity(pr)']");
	public By ELEMENT_TEXTBOX_PROCUREMENTPLAN_VIEW_MODIFY_OWNER_ITEM_PRODUCT = By.xpath("//input[@class='ui-select-search input-xs ng-pristine ng-untouched ng-valid' and @ng-model='$select.search']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_ORDER_PRODUCT = By.xpath("//a[@class='btn btn-primary btn-xs' and @data-ng-click='offModal(pr.id)']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_UPDATE = By.xpath("//button[@class='btn btn-primary ladda-button' and @ng-click='updateScPlan()']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_RESET = By.xpath("//button[@class='btn btn-warning' and @ng-click='resetModalEidt()']");
	public By ELEMENT_BUTTON_PROCUREMENTPLAN_VIEW_CANCEL = By.xpath("//input[@class='btn btn-default' and @ng-click='cancelModal()']");
	public By ELEMENT_LABEL_PROCUREMENTPLAN_VIEW_STATUS_PLAN_READY = By.xpath("//span[text()='Ready']");
	public By ELEMENT_LABEL_PROCUREMENTPLAN_VIEW_STATUS_PLAN_DRAFT = By.xpath("//span[text()='Draft']");
	public By ELEMENT_LABEL_PROCUREMENTPLAN_VIEW_STATUS_PRODUCT_NOTBUY = By.xpath("//span[text()='Not Buy']");
	public By ELEMENT_LABEL_PROCUREMENTPLAN_VIEW_STATUS_PRODUCT_BUYING = By.xpath("//span[text()='Buying']");
	
}
