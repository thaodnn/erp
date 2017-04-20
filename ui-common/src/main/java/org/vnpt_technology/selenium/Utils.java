package org.vnpt_technology.selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import static org.vnpt_technology.selenium.TestLogger.*;

public class Utils {

	private static HSSFSheet ExcelWSheet;
	private static HSSFWorkbook ExcelWBook;
	private static HSSFCell Cell;
	private static HSSFRow Row;

	public WebDriver driver;

	public WebDriver newDriver;
	public Boolean isDriver = true;
	public final int ACTION_REPEAT = 5;
	public int loopCount = 0;
	protected int DEFAULT_TIMEOUT = 30000; // milliseconds = 30 seconds
	protected int WAIT_INTERVAL = 1000; // milliseconds
	public Actions action;

	/* ========System Property==================== */
	public static String baseUrl;
	public static String server;
	public static String browser;
	protected boolean ieFlag;
	protected boolean chromeFlag;
	protected String nativeEvent;

	// Driver path
	public String uploadfile = getAbsoluteFilePath("TestData\\attachFile.exe");
	public String downloadfile = getAbsoluteFilePath("TestData\\downloadIE9.exe");
	public String ieDriver = getAbsoluteFilePath("TestData\\IEDriverServer.exe");
	public String chromeDriver = getAbsoluteFilePath("TestData\\chromedriver.exe");
	public String chromeDriverUbuntu = getAbsoluteFilePath("TestData\\chromedriver");
	public String firefoxDriver = getAbsoluteFilePath("TestData\\geckodriver.exe");

	/* ========Default System Property============= */
	public final String DEFAULT_BASEURL = "http://10.84.11.6";
	public final String DEFAULT_BROWSER = "firefox";// iexplorer, firefox,chrome

	public final String DEFAULT_SERVER = "win"; // win, ubuntu
	public final String DEFAULT_NATIVE_EVENT = "true";

	/**
	 * Get System Property
	 */
	public void getSystemProperty() {
		nativeEvent = System.getProperty("nativeEvent");
		browser = System.getProperty("browser");
		server = System.getProperty("server");
		baseUrl = System.getProperty("baseUrl");
		// Permission
		if (browser == null)
			browser = DEFAULT_BROWSER;
		if (server == null)
			server = DEFAULT_SERVER;
		if (nativeEvent == null)
			nativeEvent = DEFAULT_NATIVE_EVENT;
	}

	/**
	 * 
	 * Init FF driver
	 */
	/*
	 * public WebDriver initFFDriver() { String pathFile = ""; if
	 * ("win".equals(server)) { pathFile = System.getProperty("user.dir") +
	 * "\\src\\main\\resources\\TestData\\TestOutput"; } else { pathFile =
	 * System.getProperty("user.dir") +
	 * "/src/main/resources/TestData/TestOutput"; } info("Init FF driver");
	 * FirefoxProfile profile = new FirefoxProfile();
	 * profile.setPreference("plugins.hide_infobar_for_missing_plugin", true);
	 * profile.setPreference("dom.max_script_run_time", 0); DesiredCapabilities
	 * capabilities = DesiredCapabilities.firefox();
	 * capabilities.setCapability(FirefoxDriver.PROFILE, profile);
	 * info("Save file to " + pathFile);
	 * profile.setPreference("browser.download.manager.showWhenStarting",
	 * false); profile.setPreference("browser.download.dir", pathFile);
	 * profile.setPreference("browser.download.folderList", 2);
	 * profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
	 * "application/x-xpinstall;" +
	 * "application/x-zip;application/x-zip-compressed;application/x-winzip;application/zip;"
	 * +
	 * "gzip/document;multipart/x-zip;application/x-gunzip;application/x-gzip;application/x-gzip-compressed;"
	 * +
	 * "application/x-bzip;application/gzipped;application/gzip-compressed;application/gzip"
	 * + "application/octet-stream" +
	 * ";application/pdf;application/msword;text/plain;" +
	 * "application/octet;text/calendar;text/x-vcalendar;text/Calendar;" +
	 * "text/x-vCalendar;image/jpeg;image/jpg;image/jp_;application/jpg;" +
	 * "application/x-jpg;image/pjpeg;image/pipeg;image/vnd.swiftview-jpeg;image/x-xbitmap;image/png;application/xml;text/xml;text/icalendar;"
	 * );
	 * 
	 * profile.setPreference("plugin.disable_full_page_plugin_for_types",
	 * "application/pdf");
	 * profile.setPreference("pref.downloads.disable_button.edit_actions",
	 * true); profile.setPreference("pdfjs.disabled", true);
	 * profile.setPreference("browser.helperApps.alwaysAsk.force", false);
	 * return new FirefoxDriver(profile); }
	 */
	public WebDriver initFFDriver() {
		getSystemProperty();
		info("Init FF driver");
		if (server.contains("win")) {
			System.setProperty("webdriver.gecko.driver", firefoxDriver);
		}
		return new FirefoxDriver();

	}

	/**
	 * Init Chrome driver
	 */
	public ChromeDriver initChromeDriver() {
		info("Init chrome driver");
		getSystemProperty();
		String pathFile = "";
		String fs = File.separator;
		String temp = System.getProperty("user.dir") + "/src/main/resources/TestData/TestOutput";
		pathFile = temp.replace("/", fs).replace("\\", fs);
		if (server.contains("ubuntu")) {
			System.setProperty("webdriver.chrome.driver", chromeDriverUbuntu);
		} else if (server.contains("win")) {
			System.setProperty("webdriver.chrome.driver", chromeDriver);
		} else {
			System.setProperty("webdriver.chrome.driver", chromeDriverUbuntu);
		}

		// Add the WebDriver proxy capability.
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		String[] switches = { "start-maximized", "remote-debugging-port=9222" };
		capabilities.setCapability("chrome.switches", switches);
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", pathFile);
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(capabilities);
	}

	/**
	 * Init IE driver
	 */
	public WebDriver initIEDriver() {
		info("Init IE driver");
		System.setProperty("webdriver.ie.driver", ieDriver);
		DesiredCapabilities capabilitiesIE = DesiredCapabilities.internetExplorer();
		capabilitiesIE.setCapability("ignoreProtectedModeSettings", true);
		if ("true".equals(nativeEvent)) {
			info("Set nativeEvent is TRUE");
			capabilitiesIE.setCapability("nativeEvents", true);
		} else {
			info("Set nativeEvent is FALSE");
			capabilitiesIE.setCapability("nativeEvents", false);
		}
		capabilitiesIE.setCapability("javascriptEnabled", true);
		capabilitiesIE.setCapability("requireWindowFocus", true);
		capabilitiesIE.setCapability("enablePersistentHover", false);
		capabilitiesIE.setCapability("ignoreZoomSetting", true);
		capabilitiesIE.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		capabilitiesIE.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
		capabilitiesIE.setCapability("initialBrowserUrl", baseUrl);
		return new InternetExplorerDriver(capabilitiesIE);
	}

	/**
	 * init browser
	 * 
	 * @param opParams
	 */
	public void initSeleniumTest(Object... opParams) {
		initSeleniumTestWithOutTermAndCondition();
		driver.manage().window().maximize();
		driver.navigate().refresh();
		// termsAndConditions(opParams);
	}

	public void initSeleniumTestWithOutTermAndCondition(Object... opParams) {

		getSystemProperty();
		if ("chrome".equals(browser)) {
			driver = initChromeDriver();
			chromeFlag = true;
		} else if ("iexplorer".equals(browser)) {
			driver = initIEDriver();
			ieFlag = true;
		} else {
			driver = initFFDriver();
		}
		action = new Actions(driver);
	}

	/**
	 * Pause
	 * 
	 * @param timeInMillis
	 */
	public static void pause(long timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wait time
	 */
	public void waitTime() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	/**
	 * wait for element present
	 * 
	 * @param element
	 */
	public void waitForElementPresent(By element) {

		FluentWait wait = new FluentWait(driver);
		wait.withTimeout(5000, TimeUnit.MILLISECONDS);
		wait.pollingEvery(250, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	/**
	 * type to textbox
	 * 
	 * @param locator
	 * @param value
	 * @param validate
	 * @param opParams
	 */
	public void type(Object locator, String value, boolean validate, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		try {
			for (int loop = 1;; loop++) {
				if (loop >= ACTION_REPEAT) {
					Assert.fail("Timeout at type: " + value + " into " + locator);
				}
				WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplay);
				if (element != null) {
					if (validate)
						element.clear();
					element.click();
					element.sendKeys(value);
					if (!validate || value.equals(getValue(locator))) {
						break;
					}
				}
				info("Repeat action..." + loop + "time(s)");
				Utils.pause(WAIT_INTERVAL);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			type(locator, value, validate, opParams);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			type(locator, value, validate, opParams);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * click action
	 * 
	 * @param locator
	 * @param opParams
	 */
	public void click(Object locator, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		WebElement element = null;
		Actions actions = new Actions(driver);
		try {
			if (browser.contains("iexplorer") || browser.contains("chrome")) {
				info("use javasript to click");
				clickByJavascript(locator, notDisplay);
			} else {
				if (!locator.getClass().getName().contains("WebElement")) {
					info("wait and get elements");
					element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplay);
				} else {
					element = (WebElement) locator;
				}
				if (browser.contains("chrome")) {
					scrollToElement(element, driver);
				}
				if (element.isEnabled()) {
					info("click element");
					actions.click(element).perform();
				} else {
					info("Element is not enabled");
					info("click element by javascript");
					clickByJavascript(locator, notDisplay);
				}
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			info("click element by javascript");
			clickByJavascript(locator, notDisplay);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			info("click element by javascript");
			clickByJavascript(locator, notDisplay);
		} finally {
			loopCount = 0;
		}
		Utils.pause(1000);
	}
	
	/**
	 * Use this function to verify if a check-box is checked (using when creating a portal/publicMode)
	 * @param locator
	 * @param opParams
	 */
	public void check(Object locator, int... opParams) {
		int notDisplayE = opParams.length > 0 ? opParams[0]: 0;
		Actions actions = new Actions(driver);
		try {
			WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE);
			if (browser.contains("chrome")) {
				scrollToElement(element, driver);
			}
			if (!element.isSelected()) {
				actions.click(element).perform();
				if(waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE).getAttribute("type")!=null && waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE).getAttribute("type")!="checkbox"){
					info("Checkbox is not checked");
					if (!element.isSelected()) {
						info("check by javascript");
						waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE);
						//mouseOver(locator, true);
						clickByJavascript(locator, notDisplayE);
					}
				}
			} else {
				info("Element " + locator + " is already checked.");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			check(locator, opParams);
		} finally {
			loopCount = 0;
		}
		Utils.pause(2000);
	}


	/**
	 * mouse over and clic
	 * 
	 * @param locator
	 */
	public void mouseOverAndClick(Object locator) {
		WebElement element;
		Actions actions = new Actions(driver);
		if (ieFlag) {
			element = getDisplayedElement(locator);
		} else {
			element = waitForAndGetElement(locator);
		}
		actions.moveToElement(element).click(element).build().perform();
	}

	/**
	 * Scroll to a element on the website
	 * 
	 * @param element
	 * @param driver
	 */
	public static void scrollToElement(WebElement element, WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Click by using javascript
	 * 
	 * @param locator
	 * @param opParams
	 */
	public void clickByJavascript(Object locator, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		WebElement e = null;
		if (locator instanceof WebElement) {
			e = (WebElement) locator;
		} else {
			info("wait and get element");
			e = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplay);
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
	}

	/**
	 * Type by java script
	 * 
	 * @param locator
	 * @param value
	 * @param opParams
	 */
	public void typeByJavascript(Object locatorById, String value, Object... opParams) {
		Utils.pause(3000);
		((JavascriptExecutor) driver)
				.executeScript("document.getElementById('" + locatorById + "').value='" + value + "'");
	}

	/**
	 * checkCycling
	 * 
	 * @param e
	 * @param loopCountAllowed
	 */
	public void checkCycling(Exception e, int loopCountAllowed) {
		info("Exception:" + e.getClass().getName());
		if (loopCount > loopCountAllowed) {
			Assert.fail("Cycled: " + e.getMessage());
		}
		info("Repeat... " + loopCount + "time(s)");
		loopCount++;
	}

	/**
	 * Get element
	 * 
	 * @param locator
	 * @param opParams
	 * @return an element
	 */
	public WebElement getElement(Object locator, Object... opParams) {
		By by = locator instanceof By ? (By) locator : By.xpath(locator.toString());
		WebDriver wDriver;
		if (isDriver)
			wDriver = (WebDriver) (opParams.length > 0 ? opParams[0] : driver);
		else
			wDriver = (WebDriver) (opParams.length > 0 ? opParams[0] : newDriver);
		WebElement elem = null;
		try {
			elem = wDriver.findElement(by);
		} catch (NoSuchElementException e) {

		}
		return elem;
	}

	/**
	 * verify element exists or not
	 * 
	 * @param locator
	 * @return true if element exists false if element doesn't exist
	 */
	public boolean isElementPresent(Object locator) {
		return getElement(locator) != null;
	}

	/**
	 * verify element exists or not
	 * 
	 * @param locator
	 * @return true if element doesn't exists false if element exist
	 */
	public boolean isElementNotPresent(Object locator) {
		return !isElementPresent(locator);
	}

	/**
	 * check element displays or net
	 * 
	 * @param locator
	 * @return true if element displays false if element doesn't display
	 */
	public boolean isDisplay(Object locator) {
		boolean bool = false;
		WebElement e = getElement(locator);
		try {
			if (e != null)
				bool = e.isDisplayed();
		} catch (StaleElementReferenceException ex) {
			checkCycling(ex, 10);
			Utils.pause(WAIT_INTERVAL);
			isDisplay(locator);
		} finally {
			loopCount = 0;
		}
		return bool;
	}

	/**
	 * get value attribute
	 * 
	 * @param locator
	 * @return value of element
	 */
	public String getValue(Object locator) {
		try {
			return waitForAndGetElement(locator).getAttribute("value");
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			return getValue(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * get text of element
	 * 
	 * @param locator
	 * @param opts
	 * @return text of element
	 */
	public String getText(Object locator, int... opts) {
		WebElement element = null;
		int display = opts.length > 0 ? opts[0] : 1;
		try {
			element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, display);
			return element.getText();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			return getText(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * get an element
	 * 
	 * @param locator
	 * @param opParams
	 * @return element
	 */
	public WebElement getDisplayedElement(Object locator, Object... opParams) {
		By by = locator instanceof By ? (By) locator : By.xpath(locator.toString());
		WebDriver wDriver;
		if (isDriver)
			wDriver = (WebDriver) (opParams.length > 0 ? opParams[0] : driver);
		else
			wDriver = (WebDriver) (opParams.length > 0 ? opParams[0] : newDriver);
		WebElement e = null;
		try {
			if (by != null)
				e = wDriver.findElement(by);
			if (e != null) {
				if (isDisplay(by))
					return e;
			}
		} catch (NoSuchElementException ex) {
		} catch (StaleElementReferenceException ex) {
			checkCycling(ex, 10);
			Utils.pause(WAIT_INTERVAL);
			getDisplayedElement(locator);
		} finally {
			loopCount = 0;
		}
		return null;
	}

	/**
	 * Get element
	 * 
	 * @param locator
	 *            locator of element
	 * @param opParams
	 *            opPram[0]: timeout opPram[1]: 0,1 0: No Assert 1: Assert
	 * @return an element
	 */
	public WebElement waitForAndGetElement(Object locator, Object... opParams) {
		WebElement elem = null;
		int timeout = (Integer) (opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT);
		int isAssert = (Integer) (opParams.length > 1 ? opParams[1] : 1);
		int notDisplayE = (Integer) (opParams.length > 2 ? opParams[2] : 0);
		WebDriver wDriver;
		if (isDriver)
			wDriver = (WebDriver) (opParams.length > 3 ? opParams[3] : driver);
		else
			wDriver = (WebDriver) (opParams.length > 3 ? opParams[3] : newDriver);
		for (int tick = 0; tick < timeout / WAIT_INTERVAL; tick++) {
			if (notDisplayE == 2) {
				elem = getElement(locator, wDriver);
			} else {
				elem = getDisplayedElement(locator, wDriver);
			}
			if (null != elem)
				return elem;
			Utils.pause(WAIT_INTERVAL);
		}
		if (isAssert == 1)
			assert false : ("Timeout after " + timeout + "ms waiting for element present: " + locator);
		info("cannot find element after " + timeout / 1000 + "s.");
		System.out.println("cannot find element after " + timeout / 1000 + "s.");
		return null;
	}

	/**
	 * set file path and to open the Excel file
	 * 
	 * @param path
	 * @param SheetName
	 * @throws Exception
	 */
	public static void setExcelFile(String path, String SheetName) throws Exception {
		try {
			FileInputStream ExcelFile = new FileInputStream(path);
			ExcelWBook = new HSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * read 1 cell data from excel file
	 * 
	 * @param RowNum
	 * @param ColNum
	 * @return
	 * @throws Exception
	 */
	public static String getcelldata(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			System.out.println(CellData);
			return CellData;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * read cell array from excel file
	 * 
	 * @param FilePath
	 * @param SheetName
	 * @param rownum
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getTableArray(String FilePath, String SheetName, int rownum) throws Exception {
		String[][] tabArray = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			ExcelWBook = new HSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			Row = ExcelWSheet.getRow(rownum);
			int startRow = 1;
			int startCol = 0;
			int ci, cj;
			int totalRows = ExcelWSheet.getLastRowNum();
			int totalCols = Row.getLastCellNum();
			tabArray = new String[totalRows][totalCols];
			ci = 0;
			for (int i = startRow; i <= totalRows; i++, ci++) {
				cj = 0;
				for (int j = startCol; j < totalCols; j++, cj++) {
					tabArray[ci][cj] = getcelldata(i, j);
				}
			}
		} catch (FileNotFoundException e) {
			info("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			info("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return (tabArray);
	}

	/**
	 * This function returns a absolute path from a relative path
	 * 
	 * @param relativeFilePath
	 * @return - FQA-2092: Run and check calendar sniff on IE and FF
	 */
	public String getAbsoluteFilePath(String relativeFilePath) {
		String fs = File.separator;
		String curDir = System.getProperty("user.dir");
		String absolutePath = curDir + "/src/main/resources/" + relativeFilePath;
		absolutePath = absolutePath.replace("/", fs).replace("\\", fs);
		;
		return absolutePath;
	}

	/**
	 * This function returns a absolute path from a relative path that get from
	 * excel file
	 * 
	 * @param relativeFilePath
	 * @return absolutePath
	 */
	public static String getAbsoluteFilePathFromFile(String relativeFilePath) {
		String curDir = System.getProperty("user.home");
		String absolutePath = curDir + relativeFilePath;
		info("absolutePath:" + absolutePath);
		return absolutePath;
	}

	/**
	 * Capture the screen of the current graphics device
	 * 
	 * @param fileName
	 *            input an image name (String)
	 */
	public static void captureScreen(String fileName) {
		String path;
		BufferedImage screenCapture;
		// Thread.sleep(3000);
		pause(3000);
		try {
			Robot robot = new Robot();
			Rectangle screenSize = getScreenSize();
			screenCapture = robot.createScreenCapture(screenSize);
			// Save as PNG
			String curDir = System.getProperty("user.dir");
			path = curDir + "/target/screenshoot/";
			File f = new File(path);
			if (!f.exists())
				f.mkdir();
			ImageIO.write(screenCapture, "png", new File(path + fileName));

		} catch (AWTException e) {
			error("Failed to capture screenshot");
		} catch (IOException e) {
			path = "Failed to capture screenshot: " + e.getMessage();
		}
	}

	/**
	 * the size of the default screen
	 * 
	 * @return the size of the default screen
	 */
	public static Rectangle getScreenSize() {
		GraphicsEnvironment graphE = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice graphD = graphE.getDefaultScreenDevice();
		Window displayM = graphD.getFullScreenWindow();
		if (displayM != null)
			return new Rectangle(displayM.getWidth(), displayM.getHeight());
		else
			return new Rectangle(1000, 1000);
	}

	/**
	 * function to switch to parent windows
	 */
	public void switchToParentWindow() {
		try {
			Set<String> availableWindows = driver.getWindowHandles();
			String WindowIdParent = null;
			int counter = 1;
			for (String windowId : availableWindows) {
				if (counter == 1) {
					WindowIdParent = windowId;
				}
				counter++;
			}
			driver.switchTo().window(WindowIdParent);
			Utils.pause(1000);
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
	}
}
