package com.ics.seleniumCoreUtilis;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.PageFactory;

/********************************************************************************************************************/
/* Created By: 	Umamahesh.Mulugu@ipsl.co.uk																	*/
/* Purpose:	 This is to store reusable web-driver methods using component class                                 	*/
/********************************************************************************************************************/
public class ICSPageUtilis extends Component{
	protected static final int PAGE_LOAD_TIMEOUT = 15000;
	protected static String fredPropertiesFile = "fred";

	protected ICSPageUtilis(WebDriver driver, String url) {
		super(driver, false);
		if (StringUtils.isNotBlank(url)) {
			driver.navigate().to(url);
			pause(10000);				
		}		
		PageFactory.initElements(getElementLocatorFactory(driver), this);

	}

	protected ICSPageUtilis(WebDriver driver) {
		this(driver, null);
	} 	

	protected static void fillTextBox(WebElement webElement, String keyValue){
		try{
			webElement.clear();
			webElement.sendKeys(keyValue);
		}
		catch (Exception e)
		{
			finalTestScriptResultFailFlag = true;
			validationErrorInformation(e.getMessage());
		}
	}

	protected static boolean isElementDisplayed(WebElement webElement)
	{
		boolean flag = false;
		try{
			webElement.isDisplayed();
			flag = true;
		}catch(Exception e)
		{
			finalTestScriptResultFailFlag = true;
			validationErrorInformation(e.getMessage());
		}
		return flag;
	}

	protected static boolean isElementEnabled(WebElement webElement)
	{
		return webElement.isEnabled();
	}

	protected static void clickElement(WebElement webElement){				
		try{
			webElement.click();
		}
		catch (Exception e)
		{
			finalTestScriptResultFailFlag = true;
			validationErrorInformation(e.getMessage());
		}
	}

	protected static void clickElementSpace(WebElement webElement){				
		try{
			webElement.sendKeys(Keys.SPACE);
		}
		catch (Exception e)
		{
			finalTestScriptResultFailFlag = true;
			validationErrorInformation(e.getMessage());
		}
	}


	protected static void clickElementEnter(WebElement webElement){
		try{
			webElement.sendKeys(Keys.RETURN);
		}
		catch (Exception e)
		{
			finalTestScriptResultFailFlag = true;
			validationErrorInformation(e.getMessage());
		}
	}

	protected static void doubleClickElement(WebElement webElement){
		try{
			Actions action = new Actions(driver);
			action.doubleClick().perform();
		}
		catch (Exception e)
		{
			finalTestScriptResultFailFlag = true;
			validationErrorInformation(e.getMessage());
		}
	}

	/*Method to select value from drop down, Added by Prateek*/

	protected static void selectDropdownValue(WebDriver driver, WebElement webElement, String Value){
		try{
			getSelectClassWebElementInstance(webElement).selectByVisibleText(Value);
		}
		catch (Exception e)
		{
			finalTestScriptResultFailFlag = true;
			validationErrorInformation(e.getMessage());
		}
	}


	protected static void alertAuthentication1(String userName, String password) 
	{		try{
		switchToAlert().authenticateUsing(new UserAndPassword(userName, password));
	}
	catch (Exception e)
	{
		finalTestScriptResultFailFlag = true;
		validationErrorInformation(e.getMessage());
	}
	}
	/*Added by Prateek to handle Alert*/

	protected static void alertAuthentication(String userName, String password) 
	{		try{
		switchToAlert().authenticateUsing(new UserAndPassword(userName, password));
	}
	catch (Exception e)
	{
		finalTestScriptResultFailFlag = true;
		validationErrorInformation(e.getMessage());
	}
	}

	protected static String getTitle(WebDriver driver){
		return driver.getTitle();
	}

	protected static String getPageSource(){
		return driver.getPageSource();
	}

	/*
	 * Modified By: Keerthiga Sankara Pandian, 17-Feb-2017
	 *  Change details: Added GetText(WebElement) to get the text value
	 */
	protected static String getText(WebElement webElement){
		return webElement.getText();
	}

	/**************************************************************************** 
	 * Method Name: WindowsAlertPopUp
	 * Author: Sugantha Mani
	 * Created Date: 22/02/2017
	 * Description: to Handle Windows Alert PopUp
	 * **Parameters
	 * InputFormat - Accept/Dismiss
	 ***************************************************************************/

	protected static void WindowsAlertPopUp(String UserAction) 
	{	
		Alert alert = driver.switchTo().alert();

		if (UserAction.equalsIgnoreCase("Accept"))
		{ 
			alert.accept();  
		}
		else if (UserAction.equalsIgnoreCase("Dismiss"))
		{   
			alert.dismiss();  
		}
	}

	/**************************************************************************** 
	 * Method Name: getTableRowCount
	 * Author: ShilpaMa
	 * Created Date: 03/01/2017
	 * Description: Get the row count of a table
	 * **Parameters
	 * InputFormat - ControlId of a table
	 * @return 
	 ***************************************************************************/
	public int getTableRowCount(String tableID) 
	{ 
		int count = 0;
		List<WebElement> rows=driver.findElements(By.id(tableID));
		for(WebElement row : rows)
		{
			count=rows.size();
		}
		return count;
	}

	/**************************************************************************** 
	 * Method Name: getTableText
	 * Author: ShilpaMa
	 * Created Date: 03/01/2017
	 * Description:Validate the table text
	 * **Parameters
	 * InputFormat - ControlId of a table
	 * InputFormat - value that needs to be validated
	 * @return 
	 ***************************************************************************/
	public boolean validateTableText(String tableID,String value) 
	{ 
		boolean flag=true;
		List<WebElement> rows=driver.findElements(By.id(tableID));
		for(WebElement row : rows)
		{
			System.out.println(row.getText());
			if(!row.getText().contains(value))
			{
				flag=false;
				break;
			}
		}
		return flag;
	}
	/**************************************************************************** 
	 * Method Name: getTextFromAlert
	 * Author: ShilpaMa
	 * Created Date: 07/03/2017
	 * Description: to get the text from Windows Alert PopUp
	 * @return 
	 ***************************************************************************/

	protected static String getTextFromAlert() 
	{	
		Alert alert = driver.switchTo().alert();
		String alertText=alert.getText();
		return alertText;
	}
}
