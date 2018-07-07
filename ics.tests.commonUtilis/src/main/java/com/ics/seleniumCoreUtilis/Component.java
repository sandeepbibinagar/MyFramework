package com.ics.seleniumCoreUtilis;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

/*********************************************************************************************************************/
/* Created By: 	Umamahesh.Mulugu@ipsl.co.uk																			 */
/* Purpose:	 This is the base class for web-page handling methods. A page is a component and a popup is a component. */
/*********************************************************************************************************************/
public class Component extends GenericMethodUtilis {
	protected static final boolean debug = "true".equals(System.getProperty("scenario.debug"));
	protected static WebDriver driver;
	protected static ExtentTest extentTest;

	protected Component(WebDriver driver, boolean initializeElements) {
		// used to override the behavior of a Page. This needs to stay package-protected
		// it's not the prettiest thing but is required because Java does not allow to
		// run any code before the call to super() constructor
		Component.driver = driver;
		if (initializeElements) {
			PageFactory.initElements(getElementLocatorFactory(driver), this);
			setupImplicitTimeout();
		}
	}
	
	protected Component(WebDriver driver) {
		this(driver, true);
	}

	/**
	 * A factory method to retrieve the factory for element creation.
	 * By default it returns the DefaultElementLocatorFactory. Override this
	 * method to return for example AjaxElementLocatorFactory
	 *
	 * @param searchContext SearchContext
	 * @return ElementLocatorFactory instance
	 */
	
	protected ElementLocatorFactory getElementLocatorFactory(SearchContext searchContext) {
		return new DefaultElementLocatorFactory(searchContext);
	}

	protected Long getImplicitTimeout() {
		return Long.parseLong(System.getProperty("webdriver.implicit-wait", getDefaultImplicitTimeout().toString()));
	}

	protected static Long getDefaultImplicitTimeout() {
		return 10000L;
	}

	protected void setupImplicitTimeout() {
		driver.manage().timeouts().implicitlyWait(getImplicitTimeout(), TimeUnit.MILLISECONDS);
	}
	
	protected WebElement getWebElement(String xpath){
		return driver.findElement(By.xpath(xpath));
	}
	
	protected List<WebElement> getWebElements(String xpath){
		return driver.findElements(By.xpath(xpath));
	}
	
	protected int  getWebElementsCount(String xpath){
		int i =driver.findElements(By.xpath(xpath)).size();
		return i;
	}
	
	protected static Actions getActionsClassObject()
	{
		return new Actions(driver);
	}	 
	
	protected static Alert switchToAlert()
	{
		return driver.switchTo().alert();
	}
	
	protected static void getUrl(String Url){
		driver.get(Url);
	}
	
	protected static void switchToFrame(WebDriver driver, WebElement frame) {
		driver.switchTo().frame(frame);
	}
	
	protected static Select getSelectClassWebElementInstance(WebElement webElement)
	{
		return new Select(webElement);
	}
	
	protected static void actionMouseClickEvent(WebElement webElement){
		getActionsClassObject().click(webElement).build().perform();
	}
	
	protected static void actionMouseDoubleClickEvent(WebElement webElement){
		getActionsClassObject().doubleClick(webElement).build().perform();
	}
	
	public static void assertEquals(Object actual, Object expected, String message){
		Assert.assertEquals(actual, expected, message);
	}
	
	public static void assertNotEquals(Object actual, Object expected, String message){
		Assert.assertNotEquals(actual, expected, message);
	}

	public static void assertTrue(boolean condition, String message){
		Assert.assertTrue(condition, message);
	}

	public static void assertFalse(boolean condition, String message){
		Assert.assertFalse(condition, message);
	}
	
	protected boolean elementExists(By by) {
		return driver.findElements(by).size() > 0;
	}
	
	protected static void frameSwitch(WebElement frame)
	{		
		switchToFrame(driver,frame);		
		getDefaultImplicitTimeout();
	}
	
	protected static void pause(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
		}
	}
	
	protected static void MaximizeBrowser() {
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
		}
	}
	
	public static boolean verifyEquals(Object actual, Object expected, String message){
		
		try {
			Assert.assertEquals(actual, expected, message);
			return true;
		} catch (AssertionError e) {
			return false;
		}
	}
	
	public static boolean verifyNotEquals(Object actual, Object expected, String message){

		try {
			Assert.assertNotEquals(actual, expected, message);
			return true;
		} catch (AssertionError e) {
			return false;
		}
	}
	
	public static boolean verifyTrue(boolean condition, String message){
		try {
			Assert.assertTrue(condition, message);
			return true;
		} catch (AssertionError e) {
			return false;
		}
	}

	public static boolean verifyFalse(boolean condition, String message){
		
		try {
			Assert.assertFalse(condition, message);
			return true;
		} catch (AssertionError e) {
			return false;
		}
	}
		
	protected Object javascript(String script) {
		JavascriptExecutor exec = (JavascriptExecutor)driver;
		return exec.executeScript(script);
	}
	
	@SuppressWarnings("unchecked")
	public <Input extends Component, Output extends Component> Output run (Scenario<Input, Output> scenario) throws Exception {
		if (debug) {
			System.out.println("    > Running " + new ScenarioTextGenerator().generate(scenario));
		}
		return scenario.run((Input) this);
	}	
}

