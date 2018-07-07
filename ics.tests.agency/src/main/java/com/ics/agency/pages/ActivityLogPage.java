package com.ics.agency.pages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;

public class ActivityLogPage extends ICSPageUtilis {

	@FindBy(id = "FrmAgency")
	private static WebElement agencyFrame;
	
	@FindBy(xpath="//*[@id='ActivityLogGrid_grid']/tbody/tr[1]/td[1]")
	private static WebElement tblDate;
	
	@FindBy(xpath="//*[@id='ActivityLogGrid_grid']/tbody/tr[1]/td[2]")
	private static WebElement tblTime;
	
	@FindBy(xpath="//*[@id='ActivityLogGrid_grid']/tbody/tr[1]/td[3]")
	private static WebElement tblOperator;
	
	@FindBy(xpath="//*[@id='ActivityLogGrid_grid']/tbody/tr[1]/td[4]")
	private static WebElement tblAction;
	
	@FindBy(xpath="//*[@id='ActivityLogGrid_grid']/tbody/tr[1]/td[5]")
	private static WebElement tblValues;
	
	@FindBy(xpath="//*[@id='ActivityLogGrid_grid']/tbody/tr[1]/td[6]")
	private static WebElement tblSerial;
	
	@FindBy(xpath="//*[@id='ActivityLogGrid_grid']/tbody/tr[1]/td[7]")
	private static WebElement tblSortCode;
	
	@FindBy(xpath="//*[@id='ActivityLogGrid_grid']/tbody/tr[1]/td[8]")
	private static WebElement tblAcount;
	
	@FindBy(xpath="//*[@id='ActivityLogGrid_grid']/tbody/tr[1]/td[9]")
	private static WebElement tblAmount;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[3]")
	private static WebElement OperatorSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[4]")
	private static WebElement ActionSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[5]")
	private static WebElement ValuesSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[6]")
	private static WebElement SerialSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[7]")
	private static WebElement SortCodeSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[8]")
	private static WebElement AccountSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[9]")
	private static WebElement AmountSort;
	
	@FindBy(id = "ActionList")
	private static WebElement lstActionList;
	
	@FindBy(id = "Date")
	private static WebElement txtFromDate;

	@FindBy(id = "DateTo")
	private static WebElement txtToDate;
	
	@FindBy(id = "btnApplyFilters")
	private static WebElement btnApplyFilter;
	
	
	protected static ExtentTest EXTENTLOG;
	protected static boolean flag=false;

	public ActivityLogPage(WebDriver driver) {
		super(driver);		
	}

	public ActivityLogPage(WebDriver driver, String url) {
		super(driver, url);		
	}

	public static boolean returnResultFlag()
	{
		return ActivityLogPage.flag;
	}
	
	/*************************************************************************************
	 * Method Name: assertActionActivityLog
	 * Purpose:Method for validating the action and values displayed in activity log screen
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  05-Oct-2017
	/*************************************************************************************/
	
	public ActivityLogPage assertLoginLogoutActivityLog(String action)
	{
		String firstName = getAgencyValueFromDataSheet("agencyFirstName");
		String lastName = getAgencyValueFromDataSheet("agencyLastName");
		String userId = getAgencyValueFromDataSheet("agencyUserID");
		String branding =  getAgencyValueFromDataSheet("agencyBranding");
		String agencyID =  getAgencyValueFromDataSheet("agencyID");
		String role = ICSPropertiesConfig.getSuperUser();
		
		boolean flag = false;
		String loginAction = "User Sucessfully Logs in";
		String loginValues = "User Sucessfully Logs in : User Sucessfully Logs in Agency ID : "+agencyID+" , Branding : "+branding+" , UserID : "+userId+" , First Name : "+firstName+" , Last Name : "+lastName+" , Role : "+role;
		String logoutAction = "User Logs Out";
		String logoutValues = "User Logs Out : User Logs Out Agency ID : "+agencyID+" , Branding : "+branding+" , UserID : "+userId+" , First Name : "+firstName+" , Last Name : "+lastName+" , Role : "+role;
		
		assertDateColumn();
		validationStepInformation("Check Action and value column in Activity log page");
		if (action == "login"){
			if (tblAction.getText().trim().equals(loginAction) && tblValues.getText().trim().equals(loginValues)
					&& tblSerial.getText().isEmpty() && tblSortCode.getText().isEmpty() && tblAcount.getText().isEmpty() && tblAmount.getText().isEmpty()){
				flag = true;
			}
		}
		else if (action == "logout"){
			selectDropdownValue(driver, lstActionList, "User Logs Out");
			DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String date1 = inputFormat.format(date);
			fillTextBox(txtFromDate,date1);
			fillTextBox(txtToDate,date1);
			clickElement (btnApplyFilter);
			if (tblAction.getText().trim().equals(logoutAction) && tblValues.getText().trim().equals(logoutValues)
					&& tblSerial.getText().isEmpty() && tblSortCode.getText().isEmpty() && tblAcount.getText().isEmpty() && tblAmount.getText().isEmpty()){
				flag = true;
			}
		}
		if (flag)
		{
			publishResults(flag, "Action column is displayed as expected for "+action, "action value should be displayed for "+action, "Verify activity is logged successfully for "+action);		
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Action column is not displayed as expected for "+action, "action value should be displayed for "+action, "Verify activity is logged successfully for "+action);				
		}
		return this;
	}
	/*************************************************************************************
	 * Method Name: assertActionActivityLog
	 * Purpose:Method for validating the action and values displayed in activity log screen
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  05-Oct-2017
	/*************************************************************************************/
	
	public ActivityLogPage assertItemActivityLog(String action, String account, String serial , String sortCode, String amount)
	{		
		boolean flag = false;
		String itemPaidAction = "Item Paid";
		String itemPaidValues = "Tab Displayed: Incoming Debits Item Paid";
		String itemNotPaidAction = "Item Not Paid";
		String itemNotPaidValues = "Tab Displayed: Incoming Debits Item Not Paid - Reason for Return("+getAgencyValueFromDataSheet("noPayReason")+")";
		String itemApprovedAction = "Item Decision Approved";
		String itemApprovedValues = "Tab Displayed: Incoming Debits Item Decision Approved";
		String itemDecisionDeniedAction = "Item Decision Denied";
		String itemDecisionDeniedValues = "Tab Displayed: Incoming Debits Item Decision Denied";
		
		amount = amount.replaceAll("£", "").trim();
		String amountTable=tblAmount.getText().replaceAll("£", "").trim();
//		WebDriverWait wait = new WebDriverWait(driver ,10);
//		wait.until(ExpectedConditions.visibilityOf(tblAction));
		assertDateColumn();
		
		validationStepInformation("Check Action and value column in Activity log page");
		if (action == "itemPaid"){
			assertOperator("decisioner");
			if (tblAction.getText().trim().equals(itemPaidAction) && tblValues.getText().trim().equals(itemPaidValues)
					&& tblSerial.getText().trim().equals(serial) && tblSortCode.getText().trim().equals(sortCode) && tblAcount.getText().trim().equals(account) && amountTable.contains(amount)){
				flag = true;
			}
		}
		
		else if (action == "itemNotPaid"){
			assertOperator("decisioner");
			if (tblAction.getText().trim().equals(itemNotPaidAction) && tblValues.getText().trim().equals(itemNotPaidValues)
					&& tblSerial.getText().trim().equals(serial) && tblSortCode.getText().trim().equals(sortCode) && tblAcount.getText().trim().equals(account) && amountTable.contains(amount)){
				flag = true;
			}
		}
		
		else if (action == "itemDecisionApproved"){
			
			assertOperator("approver");
			if (tblAction.getText().trim().equals(itemApprovedAction) && tblValues.getText().trim().equals(itemApprovedValues)
					&& tblSerial.getText().trim().equals(serial) && tblSortCode.getText().trim().equals(sortCode) && tblAcount.getText().trim().equals(account) && amountTable.contains(amount)){
				flag = true;
			}
		}
		
		else if (action == "itemDecisionDenied"){
			assertOperator("approver");
			if (tblAction.getText().trim().equals(itemDecisionDeniedAction) && tblValues.getText().trim().equals(itemDecisionDeniedValues)
					&& tblSerial.getText().trim().equals(serial) && tblSortCode.getText().trim().equals(sortCode) && tblAcount.getText().trim().equals(account) && amountTable.trim().contains(amount)){
				flag = true;
			}
		}
		if (flag)
		{
			publishResults(flag, "Action column is displayed as expected for "+action, "action value should be displayed for "+action, "Verify activity is logged successfully for "+action);		
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Action column is not displayed as expected for "+action, "action value should be displayed for "+action, "Verify activity is logged successfully for "+action);				
		}
		return this;
	}
	
	
	/*************************************************************************************
	 * Method Name: assertDateColumn
	 * Purpose:Method for validating the current date displayed in activity log screen
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  05-Oct-2017
	/*************************************************************************************/
	
	public ActivityLogPage assertDateColumn()
	{
		validationStepInformation("Check the current date is displayed in activity log page");
		pause(1000);
		boolean flag = false;
		
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String date1 = dateformat.format(date);
		
		if (tblDate.getText().trim().equals(date1))
		{
			flag = true;
			publishResults(flag, "Date column displayed as expected", "Date column should be displayed as expected", "Verify Date column displays todays date");		
		}else
		{
			flag = false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Date column is not displayed as expected", "Date column should be displayed as expected", "Verify Date column displays todays date");				
		}
		return this;
	}
	
	/*************************************************************************************
	 * Method Name: assertOperator
	 * Purpose:Method for validating the operator/ user displayed in activity log screen
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  05-Oct-2017
	/*************************************************************************************/
	
	public ActivityLogPage assertOperator(String operator)
	{		
		boolean flag = false;	
		String firstName = null;
		String lastName = null;
		String userId = null;
		
		if (operator.equals("approver") ){
			firstName = getAgencyValueFromDataSheet("agencyApproverFirstName");
			lastName = getAgencyValueFromDataSheet("agencyApproverLastName");
			userId = getAgencyValueFromDataSheet("agencyApproverUserId");
		}
		else
		{
			firstName = getAgencyValueFromDataSheet("agencyFirstName");
			lastName = getAgencyValueFromDataSheet("agencyLastName");
			userId = getAgencyValueFromDataSheet("agencyUserID");
			
		}
		String operatorName =firstName+" "+lastName+" ("+userId+")";
			
		if (tblOperator.getText().trim().equals(operatorName))
		{
			flag = true;
			publishResults(flag, "Operator column displayed as expected", "Operator column should be displayed as expected", "Verify Operator column displays the user who has performed the activity");		
		}else
		{
			flag = false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Operator column is not displayed as expected", "Operator column should be displayed as expected", "Verify Operator column displays the user who has performed the activity");				
		}
		return this;
	}
	
	/*************************************************************************************
	 * Method Name: validateSorting
	 * Purpose:Method for validating sorting of incoming debit screen
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  12-July-2017
	/*************************************************************************************/
	public ActivityLogPage validateSorting() throws Exception
	{		
		// -- > Default sorting cannot be checked as the items are displayed on how it comes from DEW
		//Sorting has been removed on Status column
			
validationStepInformation("Verify Sorting of operator column");
		
		clickElement(OperatorSort);
		List<WebElement> elementList = driver.findElements(By.xpath("//*[@id='ActivityLogGrid_grid']/tbody/tr/td[3]"));
		flag = verifySortAscending (elementList);
		publishStatementAscending ("operator",flag);	
		pause(1000);
		clickElement(OperatorSort);
		elementList = driver.findElements(By.xpath("//*[@id='ActivityLogGrid_grid']/tbody/tr/td[3]"));
		flag = verifySortDescending (elementList);
		publishStatementDescending ("operator",flag);
validationStepInformation("Verify Sorting of action column");
		
		clickElement(ActionSort);
		elementList = driver.findElements(By.xpath("//*[@id='ActivityLogGrid_grid']/tbody/tr/td[4]"));
		flag = verifySortAscending (elementList);
		publishStatementAscending ("action",flag);	
		pause(1000);
		clickElement(ActionSort);
		elementList = driver.findElements(By.xpath("//*[@id='ActivityLogGrid_grid']/tbody/tr/td[4]"));
		flag = verifySortDescending (elementList);
		publishStatementDescending ("action",flag);
		
validationStepInformation("Verify Sorting of values column");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()",ValuesSort);	
		clickElement(ValuesSort);
		elementList = driver.findElements(By.xpath("//*[@id='ActivityLogGrid_grid']/tbody/tr/td[5]"));
		flag = verifySortAscending (elementList);
		publishStatementAscending ("values",flag);	
		pause(1000);
		jse.executeScript("arguments[0].scrollIntoView()",ValuesSort);	
		clickElement(ValuesSort);
		elementList = driver.findElements(By.xpath("//*[@id='ActivityLogGrid_grid']/tbody/tr/td[5]"));
		flag = verifySortDescending (elementList);
		publishStatementDescending ("values",flag);
		
validationStepInformation("Verify Sorting of Serial Number");
		
		pause(1000);
		jse.executeScript("arguments[0].scrollIntoView()",SerialSort);
		clickElement(SerialSort);
		elementList = driver.findElements(By.xpath("//*[@id='ActivityLogGrid_grid']/tbody/tr/td[6]"));
		flag = verifySortAscending (elementList);
		publishStatementAscending ("Serial Number",flag);	
		pause(1000);
		jse.executeScript("arguments[0].scrollIntoView()",SerialSort);
		clickElement(SerialSort);
		elementList = driver.findElements(By.xpath("//*[@id='ActivityLogGrid_grid']/tbody/tr/td[6]"));
		flag = verifySortDescending (elementList);
		publishStatementDescending ("Serial Number",flag);
		
validationStepInformation("Verify Sorting of Sort Code");
		
		jse.executeScript("arguments[0].scrollIntoView()",SortCodeSort);
		clickElement(SortCodeSort);
		elementList = driver.findElements(By.xpath("//*[@id='ActivityLogGrid_grid']/tbody/tr/td[7]"));
		flag = verifySortAscending (elementList);
		publishStatementAscending ("Sort Code",flag);
		pause(1000);		
		jse.executeScript("arguments[0].scrollIntoView()",SortCodeSort);
		clickElement(SortCodeSort);
		elementList = driver.findElements(By.xpath("//*[@id='ActivityLogGrid_grid']/tbody/tr/td[7]"));
		flag = verifySortDescending (elementList);
		publishStatementDescending ("Sort Code",flag);
		
validationStepInformation("Verify Sorting of Account Column");

		pause(1000);	
		jse.executeScript("arguments[0].scrollIntoView()",AccountSort);
		clickElement(AccountSort);
		 elementList = driver.findElements(By.xpath("//*[@id='ActivityLogGrid_grid']/tbody/tr/td[8]"));		
		flag = verifySortAscending (elementList);
		publishStatementAscending ("Account",flag);
		jse.executeScript("arguments[0].scrollIntoView()",AccountSort);
		clickElement(AccountSort);
		elementList = driver.findElements(By.xpath("//*[@id='ActivityLogGrid_grid']/tbody/tr/td[8]"));
		flag = verifySortDescending (elementList);
		publishStatementDescending ("Account",flag);
						
validationStepInformation("Verify Sorting of Amount");
		jse.executeScript("arguments[0].scrollIntoView()",AmountSort);
		clickElement(AmountSort);
		elementList = driver.findElements(By.xpath("//*[@id='ActivityLogGrid_grid']/tbody/tr/td[9]"));
		flag = verifySortAscending (elementList);
		if(flag)
		publishStatementAscending ("Amount",flag);				
		pause(1000);
		jse.executeScript("arguments[0].scrollIntoView()",AmountSort);
		clickElement(AmountSort);
		elementList = driver.findElements(By.xpath("//*[@id='ActivityLogGrid_grid']/tbody/tr/td[9]"));	
		flag = verifySortDescending (elementList);		
		publishStatementDescending ("Amount",flag);


		return this;	
	}
	
	/*************************************************************************************
	 * Method Name: publishStatementDescending
	 * Purpose:Method for publish statements in descending order
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  12-July-2017
	/*************************************************************************************/
	public static ActivityLogPage publishStatementDescending(String value,boolean flag) throws Exception
	{
		if(flag)
		{		
			publishResults(flag, value+" is sorted in descending order" , value+" should be sorted in descending order", "Verify "+value+" is sorted in descending order");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag, value+" is not sorted in descending order" , value+" should be sorted in descending order", "Verify "+value+" is sorted in descending order");
		}
		return null;
	}

	/*************************************************************************************
	 * Method Name: publishStatementAscending
	 * Purpose:Method for publish statements in ascending order
	 * Author: Deepa Ramu
	 *  Reviewed by : venkatasainath devisetty
	 * Created Date:  12-July-2017
	/*************************************************************************************/
	public static BankLandingPage publishStatementAscending(String value , boolean flag) throws Exception
	{
		if(flag)
		{		
			publishResults(flag, value+" is sorted in ascending order" , value+" should be sorted in ascending order", "Verify "+value+" is sorted in ascending order");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag, value+" is not sorted in ascending order" , value+" should be sorted in ascending order", "Verify "+value+" is sorted in ascending order");
		}
		return null;
	}
	/*************************************************************************************
	 * Method Name: assertActionActivityLog
	 * Purpose:Method for validating the action and values displayed in activity log screen
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  05-Oct-2017
	/*************************************************************************************/
	
	public ActivityLogPage assertItemActivityLogSearch(String action, String workFlowStatus)
	{		
		boolean flag = false;

		String searchActionCurrent = "Search Button Pressed(Current Cycle Search)";
		String searchActionHistoric = "Search Button Pressed(Completed Item Search)";
		
		assertDateColumn();
		assertOperator("decisioner");
		validationStepInformation("Check Action and value column in Activity log page");
		if (action == "searchCurrent"){
			
			String searchValuesTset = "Tab Displayed: Search Button Pressed(Current Cycle ) Date From : 31/05/2017, Date To : 31/05/2017, Status : All Items, Work Flow Status : "
					+workFlowStatus+", Submission Channel : All Channel, Transaction Set ID :"+getAgencyValueFromDataSheet("tSetIDCreditwithVersion");
			
			if (tblAction.getText().trim().equals(searchActionCurrent) && tblValues.getText().trim().equals(searchValuesTset)
					&& tblSerial.getText().isEmpty() && tblSortCode.getText().isEmpty() && tblAcount.getText().isEmpty() && tblAmount.getText().isEmpty()){
				flag = true;
			}
		}
		
		else if (action == "searchHistoric"){
			
			String searchValuesFilter = "Tab Displayed: Search Button Pressed(Completed Item ) Date From : 01/05/2017, Date To : 31/05/2017, SortCode : ";
			
			if (tblAction.getText().trim().equals(searchActionHistoric) && tblValues.getText().trim().contains(searchValuesFilter)
					&& tblSerial.getText().isEmpty() && tblSortCode.getText().isEmpty() && tblAcount.getText().isEmpty() && tblAmount.getText().isEmpty()){
				flag = true;
			}
		}
		if (flag)
		{
			publishResults(flag, "Action column is displayed as expected for "+action, "action value should be displayed for "+action, "Verify activity is logged successfully for "+action);		
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Action column is not displayed as expected for "+action, "action value should be displayed for "+action, "Verify activity is logged successfully for "+action);				
		}
		return this;
	}
	
	
	
}
