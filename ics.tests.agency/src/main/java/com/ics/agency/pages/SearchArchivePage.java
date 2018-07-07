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

public class SearchArchivePage extends ICSPageUtilis {

	@FindBy(id = "FrmAgency")
	private static WebElement agencyFrame;

	@FindBy(id = "dtFrom")
	private static WebElement txtFromDate;

	@FindBy(id = "dtTo")
	private static WebElement txtToDate;
	
	@FindBy(id = "ddlWFStatus")
	private static WebElement lstWorkflowStatus;
	
	@FindBy(id = "ddlSubChnl")
	private static WebElement lstSubmissionChannel;

	@FindBy(id = "txtItmId")
	private static WebElement txtItemId;
	
	@FindBy(id = "txtTsetId")
	private static WebElement txtTsetId;
	
	@FindBy(id = "txtSortcode")
	private static WebElement txtSortCodeFrom;
	
	@FindBy(id = "txtSortcodeTo")
	private static WebElement txtSortCodeTo;
	
	@FindBy(id = "txtAccount")
	private static WebElement txtAccount;

	@FindBy(id = "txtSerial")
	private static WebElement txtSerial;
	
	@FindBy(id = "txtAmountDr")
	private static WebElement txtAmoutFrom;
	
	@FindBy(id = "txtAmountToDr")
	private static WebElement txtAmoutTo;
	
	@FindBy(id = "ddlStatus")
	private static WebElement lstStatus;
	
	@FindBy(id = "btnSearch")
	private static WebElement btnSearch;
	
	@FindBy(id = "btnClearCurrentFilters")
	private static WebElement btnClearSearch;
		
	@FindBy(id="SearchGrid_grid")
	private static WebElement tblSearchResults;
		
	@FindBy(id = "SearchGrid_grid_info")
	private static WebElement filterPageDetails;
	
	@FindBy(id = "SearchGrid_grid_previous")
	private static WebElement paginationPrevious;
	
	@FindBy(id = "SearchGrid_grid_next")
	private static WebElement paginationNext;
	
	@FindBy(className = "paginate_button")
	private static WebElement pageDetails;
	
	@FindBy(className = "bootbox-body")
	private static WebElement dlgErrorMessage;
	
	//@FindBy(className = "btn btn-primary")
	@FindBy(xpath="//*[@class='bootbox modal fade bootbox-alert in']/div/div/div/button")
	private static WebElement btnOk;
	
	@FindBy(id = "imgSearchExpandCollapse")
	private static WebElement btnFilterExpandCollapse;
	
	@FindBy(xpath="//*[@class='row form-div-row CM-DIV-R']/div[2]/span")
	private static WebElement btnFilterExpandCollapseTooltip;

	@FindBy(xpath="//*[@id='SearchGrid_grid']/tbody/tr[1]/td[1]/i")
	private static WebElement imgAuditHistoryExpander;
	
	@FindBy(xpath="//*[@id='SearchGrid_grid']/tbody/tr[1]/td[1]/span")
	private static WebElement imgAuditHistoryExpanderTooltip;
		
	@FindBy(xpath="//*[@id='SearchGrid_grid']/tbody/tr[1]/td[8]/i")
	private static WebElement imgImage;
	
	@FindBy(xpath="//*[@id='SearchGrid_grid']/tbody/tr[1]/td[8]/span")
	private static WebElement imgImageTooltip;
	
	@FindBy(xpath="//*[@id='SearchGrid_grid']/tbody/tr[1]/td[9]/span")
	private static WebElement imgKappaTooltip;
	
	@FindBy(xpath="//*[@id='SearchGrid_grid']/tbody/tr[1]/td[9]/i")
	private static WebElement imgKappa;
	
	@FindBy(xpath="//*[@id='SearchGrid_grid']/tbody/tr[1]/td[10]/i")
	private static WebElement imgOther;
	
	@FindBy(xpath="//*[@id='SearchGrid_grid']/tbody/tr[1]/td[10]/span")
	private static WebElement imgOtherTooltip;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[2]")
	private static WebElement btnDateSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[3]")
	private static WebElement btnTypeSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[4]")
	private static WebElement btnSortcodeSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[5]")
	private static WebElement btnAccountSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[6]")
	private static WebElement btnSerialSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[7]")
	private static WebElement btnAmountSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[8]")
	private static WebElement btnImageSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[9]")
	private static WebElement btnKappaSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[10]")
	private static WebElement btnOtherSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[11]")
	private static WebElement btnStatusSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[12]")
	private static WebElement btnTsetSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[13]")
	private static WebElement btnChannelSort;
	
	@FindBy(xpath = "//*[@id='SearchGrid_grid']/tbody/tr[1]/td[4]")
	private static WebElement tblSortCode;
	
	@FindBy(xpath = "//*[@id='SearchGrid_grid']/tbody/tr[1]/td[5]")
	private static WebElement tblAccount;
	
	@FindBy(xpath = "//*[@id='SearchGrid_grid']/tbody/tr[1]/td[6]")
	private static WebElement tblSerial;
	
	@FindBy(id = "chkPay")
	private static WebElement agencyRolePaying;
	
	@FindBy(id = "chkBenf")
	private static WebElement agencyRoleBeneficiary;
	
	@FindBy(id = "rdCurrent")
	private static WebElement rdCurrent;
	
	@FindBy(id = "rdHistoric")
	private static WebElement rdHistoric;
	
	@FindBy(className ="viewer-zoom-in")
	private static WebElement imgFrontZoomIn;
	
	@FindBy(className ="viewer-zoom-out")
	private static WebElement imgFrontZoomOut;
	
	@FindBy(className ="viewer-one-to-one")
	private static WebElement imgFrontonetoone;
	
	@FindBy(className ="viewer-reset")
	private static WebElement imgFrontReset;
	
	@FindBy(className ="viewer-prev")
	private static WebElement imgFrontPrev;
	
	@FindBy(className ="viewer-next")
	private static WebElement imgFrontNext;
	
	@FindBy(className ="viewer-rotate-left")
	private static WebElement imgFrontRotateLeft;
	
	@FindBy(className ="viewer-rotate-right")
	private static WebElement imgFrontRotateRight;
	
	@FindBy(xpath="(//*[@class='viewer-zoom-in'])[2]")
	private static WebElement imgRearZoomIn;
	
	@FindBy(xpath="(//*[@class='viewer-zoom-out'])[2]")
	private static WebElement imgRearZoomOut;
	
	@FindBy(xpath="(//*[@class='viewer-one-to-one'])[2]")
	private static WebElement imgRearonetoone;
	
	@FindBy(xpath="(//*[@class='viewer-reset'])[2]")
	private static WebElement imgRearReset;
	
	@FindBy(xpath="(//*[@class='viewer-prev'])[2]")
	private static WebElement imgRearPrev;
	
	@FindBy(xpath="(//*[@class='viewer-next'])[2]")
	private static WebElement imgRearNext;
	
	@FindBy(xpath="(//*[@class='viewer-rotate-left'])[2]")
	private static WebElement imgRearRotateLeft;
	
	@FindBy(xpath="(//*[@class='viewer-rotate-right'])[2]")
	private static WebElement imgRearRotateRight;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[2]/td/div/div")
	private static WebElement imgFrontView;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[2]/td[2]/div/div")
	private static WebElement imgRearView;
	
	@FindBy(xpath="//*[@id='SearchGrid_grid']/tbody/tr[3]")
	private static WebElement kappaData;
	
	@FindBy(xpath="//*[@id='SearchGrid_grid']/tbody/tr[4]")
	private static WebElement kappaImage;
	
	@FindBy(xpath="//*[@id='SearchGrid_grid']/tbody/tr[5]")
	private static WebElement otherExpander;
	
	@FindBy(xpath="//*[@class='imgInlineKappaClass fa fa-up-arrow fa-2x css-for-imgCollapse AGYtooltip']")
	private static WebElement imgKappaDataCollapse;
	
	@FindBy(xpath="//*[@class='imgInlineImgClass fa fa-up-arrow fa-2x css-for-imgCollapse AGYtooltip']")
	private static WebElement imgKappaImageCollapse;
	
	@FindBy(xpath="//*[@class='imgInlineImgClass fa fa-up-arrow fa-2x css-for-imgCollapse AGYtooltip']")
	private static WebElement imgImageCollapse;
	
	@FindBy(xpath="//*[@class='fa fa-up-arrow imgInlineOthersClass css-for-imgCollapse']")
	private static WebElement imgOtherCollapse;
	
	
	protected static ExtentTest EXTENTLOG;
	protected static boolean flag=false;

	public SearchArchivePage(WebDriver driver) {
		super(driver);		
	}

	public SearchArchivePage(WebDriver driver, String url) {
		super(driver, url);		
	}

	public SearchArchivePage switchToAgencyFrame()
	{		
		frameSwitch(agencyFrame);
		return new SearchArchivePage(driver);
	}

	public static boolean returnResultFlag()
	{

		return SearchArchivePage.flag;
	}
	/*************************************************************************************
	 * Method Name: fillSearchFilterDetails
	 * Purpose:Method for entering mandatory search filters
	 * Author: Deepa Ramu
	 * Created Date:  22-May-2017
	/*************************************************************************************/
	
	public SearchArchivePage fillSearchFilterDetails()
	{
		fillTextBox(txtFromDate," ");
		fillTextBox(txtFromDate,getAgencyValueFromDataSheet("searchFromDate"));
		fillTextBox(txtAmoutFrom,"1");
		fillTextBox(txtAmoutTo,"10000");	
		selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");
		return this;
	}
	/*************************************************************************************
	 * Method Name: selectHistoricDate
	 * Purpose:Method for selecting historic date in search screen
	 * Author: Deepa Ramu
	 * Created Date:  26-Sep-2017
	/*************************************************************************************/
	
	public SearchArchivePage selectHistoricDate()
	{
		clickElement(rdHistoric);
		clickElement(rdHistoric);
		fillTextBox(txtFromDate," ");
		fillTextBox(txtFromDate,getAgencyValueFromDataSheet("searchFromDate"));
		return this;
	}
	
	
	/*************************************************************************************
	 * Method Name: fillDateError
	 * Purpose:Method for entering value greater than 365 days
	 * Author: Deepa Ramu
	 * Created Date:  28-Jun-2017
	/*************************************************************************************/
	
	public SearchArchivePage fillDateError()
	{
		clickElement(rdHistoric);
		clickElement(rdHistoric);
		fillTextBox(txtFromDate," ");
		fillTextBox(txtFromDate,getAgencyValueFromDataSheet("searchFromDateError"));	
		selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");
		fillTextBox(txtTsetId,getAgencyValueFromDataSheet("tSetIDCreditwithVersion"));	
		clickElementEnter (btnSearch);
		pause(2000);
		if (dlgErrorMessage.getText().equals("Date From Range cannot be more than one year") ){
			flag=true;
			publishResults(flag, "Error message displayed as expected", "Error pop up should be displayed","Error pop up should be displayed when date range is greater than 365 days");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  "error message not displayed", "Error pop up should be displayed","Error pop up should be displayed when date range is greater than 365 days");
		}
		pause(1000);
		clickElementEnter(btnOk);
		assertTrue(flag,"Error message displayed as expected");
		return null;
	}
	
	/*************************************************************************************
	 * Method Name: cdoeLinkeFilterErrorHistoric
	 * Purpose:Method for validating error mssage when 1 codeline value is entered for historic date
	 * Author: Deepa Ramu
	 * Created Date:  26-Sep-2017
	/*************************************************************************************/
	
	public SearchArchivePage codeLineFilterErrorHistoric(String workflowStatus)
	{
		selectHistoricDate();
		if (workflowStatus == "Credit InClearing"){
			selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");}
		else if (workflowStatus == "Debit InClearing"){
			selectDropdownValue(driver, lstWorkflowStatus, "Debit InClearing");}
		fillTextBox(txtAmoutFrom,"1");
		fillTextBox(txtAmoutTo,"1000");			
		clickElementEnter (btnSearch);
		pause(2000);
		if (dlgErrorMessage.getText().contains("The minimum criteria has not been entered for date range search. As a minimum, please enter at least") ){
			flag=true;
			publishResults(flag, "Error message displayed as expected", "Error pop up should be displayed","Error pop up should be displayed when only one codeline filter is applied for historic date"+workflowStatus);
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  "error message not displayed", "Error pop up should be displayed","Error pop up should be displayed when only one codeline filter is applied for historic date"+workflowStatus);
		}
		pause(1000);
		clickElementEnter(btnOk);
		assertTrue(flag,"Error message not displayed as expected");
		return this;
	}
	
	/*************************************************************************************
	 * Method Name: assertstatusFieldSearchFilter
	 * Purpose:Method for validating search results when serial and status fields are entered
	 * Author: Deepa Ramu
	 * Created Date:  22-May-2017
	/*************************************************************************************/	
	public SearchArchivePage assertpayDecisionStatusFieldSearchFilter()
	{
		String fromAmount = getAgencyValueFromDataSheet("searchFromAmount");
		String toAmount = getAgencyValueFromDataSheet("searchToAmount");
		selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");
		if (lstStatus.isEnabled()){
			finalTestScriptResultFailFlag = true;
				flag=false;
				publishResults(flag,  "Status field is not disabled when credit inclearing is selected", "Status field should be disabled when credit inclearing is selected","Verify Status is disabled when credit inclearing is selected");		
			}else
			{
				flag=true;
				publishResults(flag, "Status field disabled when credit inclearing is selected", "Status field should be disabled when credit inclearing is selected","Verify Status field is disabled when credit inclearing is selected");
			}
		
//		selectDropdownValue(driver, lstWorkflowStatus, "Debit InClearing");		
//		selectDropdownValue(driver, lstStatus, "Paid");
//		fillTextBox(txtAmoutFrom,fromAmount);	
//		fillTextBox(txtAmoutTo,toAmount);
//		clickElementEnter (btnSearch);
//		pause(2000);
//		validateSearchResults("status" ,"Paid");	
		
		clickElement (btnClearSearch);		
		selectDropdownValue(driver, lstWorkflowStatus, "Debit InClearing");
		selectDropdownValue(driver, lstStatus, "Not Paid");
		fillTextBox(txtAmoutFrom,fromAmount);	
		fillTextBox(txtAmoutTo,toAmount);
		clickElementEnter (btnSearch);
		pause(2000);
		validateSearchResults("status" ,"Not paid");	
		return this;
	}
	/*************************************************************************************
	 * Method Name: assertAmountFieldSearchFilter
	 * Purpose:Method for validating the search table for the filtered amount value passed
	 * Author: Deepa Ramu
	 * Created Date:  10-May-2017
	/*************************************************************************************/
	
	public SearchArchivePage assertAmountFieldSearchFilter()
	{
		validationStepInformation("Check Amount field Search filter");
		fillSearchFilterDetails();
		clickElementEnter (btnSearch);
		pause(2000);
		validateAmountSearchResults(1,1000);
		assertTrue(flag, "All items status are not applied");			
		return null;
	}
	
	/*************************************************************************************
	 * Method Name: assertSortCodeFieldSearchFilter
	 * Purpose:Method for validating the search table for the filtered SortCode value passed
	 * Author: Deepa Ramu
	 * Created Date:  26-06-2017
	/*************************************************************************************/
	
	public SearchArchivePage assertSortCodeAccountFieldSearchFilter(String workflowStatus, String date)
	{
		validationStepInformation("Check Sort code Search filter for" +date+ "date- "+workflowStatus);
		if (workflowStatus == "Credit InClearing"){
			selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");
			fillTextBox(txtTsetId,getAgencyValueFromDataSheet("tSetIDCreditwithVersion"));	
		}
		else if (workflowStatus == "Debit InClearing"){
			clickElement(btnClearSearch);
			selectDropdownValue(driver, lstWorkflowStatus, "Debit InClearing");
			fillTextBox(txtTsetId,getAgencyValueFromDataSheet("tSetIDDebitwithVersion"));	
		}		
		clickElement(btnSearch);
		pause(1000);
		String sortCodeFrom = tblSortCode.getText();		
		int sortCodeTo1 = Integer.parseInt(sortCodeFrom);
		sortCodeTo1 = sortCodeTo1+1;
		String sortCodeTo = Integer.toString(sortCodeTo1);
		String tblAccountValue = tblAccount.getText();
		
		clickElement(btnClearSearch);
		if (workflowStatus == "Credit InClearing"){
			selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");
		}
		else if (workflowStatus == "Debit InClearing"){
			selectDropdownValue(driver, lstWorkflowStatus, "Debit InClearing");
		}	
		if (date == "historic"){
			selectHistoricDate();
		}	
		
		fillTextBox(txtSortCodeFrom,sortCodeFrom);	
		fillTextBox(txtSortCodeTo,sortCodeTo);		
		fillTextBox(txtAccount,tblAccountValue);
		clickElement(btnSearch);
		pause(2000);
		validateSortSearchResults(sortCodeFrom,sortCodeTo);
		validateSearchResults("account" ,tblAccountValue);	
		assertTrue(flag, "Sort code search filter not applied as expected");			
		return this;
	}
	
	/*************************************************************************************
	 * Method Name: assertSerialAmountFieldSearchFilter
	 * Purpose:Method for validating the search table for the filtered Serial number and amount value passed
	 * Author: Deepa Ramu
	 * Created Date:  26-09-2017
	/*************************************************************************************/
	
	public SearchArchivePage assertSerialAmountFieldSearchFilter(String workflowStatus, String date)
	{
		validationStepInformation("Check Serial and amount Search filter for" +date+ "date- "+workflowStatus);
		if (workflowStatus == "Credit InClearing"){
			selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");
			fillTextBox(txtTsetId,getAgencyValueFromDataSheet("tSetIDCreditwithVersion"));	
		}
		else if (workflowStatus == "Debit InClearing"){
			clickElement(btnClearSearch);
			selectDropdownValue(driver, lstWorkflowStatus, "Debit InClearing");
			fillTextBox(txtTsetId,getAgencyValueFromDataSheet("tSetIDDebitwithVersion"));	
		}		
		clickElementEnter (btnSearch);
		pause(1000);
		String tblSerialValue = tblSerial.getText();
		clickElement (btnClearSearch);
		pause(1000);
		
		if (date == "historic"){
			selectHistoricDate();
		}
		if (workflowStatus == "Credit InClearing"){
			selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");
		}
		else if (workflowStatus == "Debit InClearing"){
			selectDropdownValue(driver, lstWorkflowStatus, "Debit InClearing");
		}
		String fromAmount = getAgencyValueFromDataSheet("searchFromAmount");
		String toAmount = getAgencyValueFromDataSheet("searchToAmount");
		fillTextBox(txtAmoutFrom,fromAmount);	
		fillTextBox(txtAmoutTo,toAmount);	
		fillTextBox(txtSerial,tblSerialValue);
		clickElementEnter (btnSearch);
		pause(1000);
		validateSearchResults("serial" ,tblSerialValue);
		validateAmountSearchResults(Float.parseFloat(fromAmount),Float.parseFloat(toAmount));
				
		assertTrue(flag, "serial , amount and status search filter not applied as expected");			
		return this;
	}
	
	/*************************************************************************************
	 * Method Name: assertTsetFieldSearchFilter
	 * Purpose:Method for validating the search table for the filtered tset value passed
	 * Author: Deepa Ramu
	 * Created Date:  10-May-2017
	/*************************************************************************************/
	
	public SearchArchivePage assertTsetFieldSearchFilter(String workflowStatus, String date)
	{
		selectDropdownValue(driver, lstWorkflowStatus, workflowStatus);
		
		if (date == "Historic"){
			clickElement(rdHistoric);
		}
		validationStepInformation("Check T-set Search filter");	
		if (workflowStatus == "Credit InClearing"){
			fillTextBox(txtTsetId,getAgencyValueFromDataSheet("tSetIDCredit"));	
			clickElementEnter (btnSearch);
			pause(2000);
			if (txtItemId.isEnabled()){
				finalTestScriptResultFailFlag = true;
				publishResults(false,"item Identifier is enabled", "Item Identifier should be disabled","Verify Item Identifier is disabled");		
				}else
				{
					publishResults(true,"item Identifier is not enabled", "Item Identifier should be disabled","Verify Item Identifier is disabled");
			}
			validateSearchResults("t-set", getAgencyValueFromDataSheet("tSetIDCreditwithVersion"));	
			fillTextBox(txtTsetId,getAgencyValueFromDataSheet("tSetIDCreditwithVersion"));	
			clickElementEnter (btnSearch);
			pause(2000);
			validateSearchResults("t-set", getAgencyValueFromDataSheet("tSetIDCreditwithVersion"));
		}
		else if (workflowStatus == "Debit InClearing"){
			fillTextBox(txtTsetId,getAgencyValueFromDataSheet("tSetIDDebit"));	
			clickElementEnter (btnSearch);
			pause(2000);
			if (txtItemId.isEnabled()){
				finalTestScriptResultFailFlag = true;
				publishResults(false,"item Identifier is enabled", "Item Identifier should be disabled","Verify Item Identifier is disabled");		
				}else
				{
					publishResults(true,"item Identifier is not enabled", "Item Identifier should be disabled","Verify Item Identifier is disabled");
			}
			validateSearchResults("t-set", getAgencyValueFromDataSheet("tSetIDDebitwithVersion"));	
			fillTextBox(txtTsetId,getAgencyValueFromDataSheet("tSetIDDebitwithVersion"));	
			clickElementEnter (btnSearch);
			pause(2000);
			validateSearchResults("t-set", getAgencyValueFromDataSheet("tSetIDDebitwithVersion"));
		}	
		return this;
	}
	
	/*************************************************************************************
	 * Method Name: assertDebitCreditDisabled
	 * Purpose:Method for validating the debit credit section is disabled when item identifier or Tset identifier search is applied
	 * Author: Deepa Ramu
	 * Created Date:  10-May-2017
	/*************************************************************************************/
	
	public SearchArchivePage assertDebitCreditDisabled()
	{
		if (txtSortCodeFrom.isEnabled() && txtSortCodeTo.isEnabled() && txtAccount.isEnabled()
				&& txtSerial.isEnabled() && txtAmoutFrom.isEnabled() && txtAmoutTo.isEnabled()
				&& lstStatus.isEnabled()){
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  "Debit/credit secion is not disabled", "Debit/credit secion should be disabled","Verify Debit/credit secion is disabled");		
			}else
			{
				publishResults(flag, "Debit/credit secion is disabled", "Debit/credit secion should be disabled","Verify Debit/credit secion is disabled");
			}
		return null;
	}
	
	/*************************************************************************************
	 * Method Name: assertItemIdentifierSearchFilter
	 * Purpose:Method for validating the search table for the filtered itemIdentifier value passed
	 * Author: Deepa Ramu
	 * Created Date:  20-june-2017
	/*************************************************************************************/
	
	public SearchArchivePage assertItemIdentifierFieldSearchFilter(String workflowStatus)
	{
		if (workflowStatus == "Debit InClearing"){
			fillTextBox(txtItemId,getAgencyValueFromDataSheet("itemIdentifierDebit"));	
			selectDropdownValue(driver, lstWorkflowStatus, "Debit InClearing");
			clickElementEnter (btnSearch);
			if (txtTsetId.isEnabled()){
				finalTestScriptResultFailFlag = true;
				publishResults(false,"tset Identifier is enabled", "tset Identifier should be disabled","Verify tset Identifier is disabled");		
				}else
				{
					publishResults(true,"tset Identifier is not enabled", "tset Identifier should be disabled","Verify tset Identifier is disabled");
			}
			validateSearchResults("t-set",getAgencyValueFromDataSheet("tSetIDDebitwithVersion"));	
		}	
		else if (workflowStatus == "Credit InClearing"){
			fillTextBox(txtItemId,getAgencyValueFromDataSheet("itemIdentifierCredit"));	
			selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");
			clickElementEnter (btnSearch);
			if (txtTsetId.isEnabled()){
				finalTestScriptResultFailFlag = true;
				publishResults(false,"tset Identifier is enabled", "tset Identifier should be disabled","Verify tset Identifier is disabled");		
				}else
				{
					publishResults(true,"tset Identifier is not enabled", "tset Identifier should be disabled","Verify tset Identifier is disabled");
			}
			validateSearchResults("t-set",getAgencyValueFromDataSheet("tSetIDCreditwithVersion"));	
		}	
		return this;
	}
	/*************************************************************************************
	 * Method Name: assertItemIdentifierSearchFilter
	 * Purpose:Method for validating the search table for the filtered itemIdentifier value passed
	 * Author: Deepa Ramu
	 * Created Date:  20-june-2017
	/*************************************************************************************/
	
	public SearchArchivePage assertAccountFieldSearchFilter()
	{
		validationStepInformation("Check Account Search filter");	
		fillSearchFilterDetails();
		clickElementEnter (btnSearch);
		String tblAccountValue = tblAccount.getText();
		fillTextBox(txtAccount,tblAccountValue);
		clickElementEnter (btnSearch);
		validateSearchResults("account" ,tblAccountValue);	
		return null;
	}
	
	/*************************************************************************************
	 * Method Name: assertWorkflowStatus
	 * Purpose:Method for validating the WorkflowStatus  passed
	 * Author: Deepa Ramu
	 * Created Date:  20-june-2017
	/*************************************************************************************/
	
	public SearchArchivePage assertWorkflowStatus()
	{
		validationStepInformation("Check Account Search filter");	
		
		fillTextBox(txtAccount,getAgencyValueFromDataSheet("searchAccount"));	
		fillSearchFilterDetails();
		clickElementEnter (btnSearch);
		validateSearchResults("account" ,getAgencyValueFromDataSheet("searchAccount"));	
		return null;
	}
	
	/*************************************************************************************
	 * Method Name: assertDefaultdate
	 * Purpose:Method for validating default date is displayed as todays date
	 * Author: Deepa Ramu
	 * Created Date:  26-june-2017
	/*************************************************************************************/
	
	public SearchArchivePage assertDefaultdate()
	{
		validationStepInformation("Check Default date in search screen");;
		clickElement(txtFromDate);
		String a = txtFromDate.getText();
		System.out.println(a);
		validateSearchResults("account" ,getAgencyValueFromDataSheet("searchAccount"));	
		return null;
	}
	
	/*************************************************************************************
	 * Method Name: validateAmountSearchResults
	 * Purpose:Method for validating the search table for the filtered amount value passed
	 * Author: Deepa Ramu
	 * Created Date:  15-June-2017
	/*************************************************************************************/
	
	public  boolean validateAmountSearchResults(float fromAmount , float toAmount ){

		pause(1000);
		ArrayList<WebElement> rowcount = new ArrayList<>();
		rowcount=(ArrayList<WebElement>) tblSearchResults.findElements(By.tagName("tr"));
		int i=1;		
		for (WebElement rowElement:rowcount){	
			WebElement amount = rowElement.findElement(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr["+i+"]/td[7]"));		
			System.out.println(amount);
			String amount2 = amount.getText();
			amount2=amount2.replace("£", "");
			float amount1=Float.valueOf(amount2);
			System.out.println(amount2);
			 if ( ( amount1 < toAmount) && (amount1 > fromAmount) ) 
		 	 {
				 flag =true;
				 i++;				
			 }else{
				 flag =false;
				 break;
			 }
			 if (i >=(rowcount.size()-2) ) {
				 break;
			 }
		}
		assertTrue(flag,"Search results filtered for Amount as expected");
		if (flag ){
			publishResults(flag, "Amount search result displayed as expected", "Amount search result should be displayed as expected","Amount search filter applied as expected");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  "Amount search result not displayed as expected", "Amount search result should be displayed as expected","Amount search filter applied as expected");
		}
		return flag;
}
	/*************************************************************************************
	 * Method Name: validateSortSearchResults
	 * Purpose:Method for validating the search table for the filtered amount value passed
	 * Author: Deepa Ramu
	 * Created Date:  15-June-2017
	/*************************************************************************************/
	
	public  boolean validateSortSearchResults(String fromSort , String toSort ){

		ArrayList<WebElement> rowcount = new ArrayList<>();
		rowcount=(ArrayList<WebElement>) tblSearchResults.findElements(By.tagName("tr"));
		System.out.println(rowcount.size());
		int i=1;		
		for (WebElement rowElement:rowcount){	
			WebElement sortCode = rowElement.findElement(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr["+i+"]/td[4]"));		
			String sortCode2 = sortCode.getText();	
			int sortCodeInt = Integer.parseInt(sortCode2);
			System.out.println(sortCode2);
			 if ( ( sortCodeInt <= Integer.parseInt(toSort)) && (sortCodeInt >= Integer.parseInt(fromSort))) 
		 	 {
				 flag =true;			
			 }else{
				 flag =false;
				 break;
			 }
			  if (i==(rowcount.size()-2)) {
				 break;
			 }
			  i++;
		}
		assertTrue(flag,"Search results filtered for Sort Code as expected");
		if (flag ){
			publishResults(flag, "Sort Code search result displayed as expected", "Sort Code search result should be displayed as expected","Sort Code search filter applied as expected");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  "Sort Code search result not displayed as expected", "Sort Code search result should be displayed as expected","Sort Code search filter applied as expected");
		}
		return flag;
}
	/*************************************************************************************
	 * Method Name: validateSearchResults
	 * Purpose:Method for validating the search table for the filtered tset value passed
	 * Author: Deepa Ramu
	 * Created Date:  15-June-2017
	/*************************************************************************************/
	
	public  SearchArchivePage validateSearchResults(String column , String value ){

		WebElement status = null  ;
		pause(1000);
		ArrayList<WebElement> rowcount = new ArrayList<>();
		rowcount=(ArrayList<WebElement>) tblSearchResults.findElements(By.tagName("tr"));
		int i=1;
		for (WebElement rowElement:rowcount){	
			
			if (column == "status"){
				status = rowElement.findElement(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr["+i+"]/td[11]"));	
					}
			else if (column == "serial"){
				status = rowElement.findElement(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr["+i+"]/td[6]"));	
					}
			else if (column == "account"){
				status = rowElement.findElement(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr["+i+"]/td[5]"));	
					}
			else if (column == "type"){
				status = rowElement.findElement(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr["+i+"]/td[3]"));	
					}
			else if (column == "channel"){
				status = rowElement.findElement(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr["+i+"]/td[13]"));	
					}
			else if (column == "t-set"){
				status = rowElement.findElement(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr["+i+"]/td[12]"));	
					}
			 if ( status.getText().equals(value)) 	 {	
				 flag =true;				
			 }	
			 else{
				 flag =false;
				 break;
			 }
			  if (i >=(rowcount.size()-2) ) {
				 break;
			 }
			  i++;
		}
		assertTrue(flag,value+" Search results filtered as expected");
		if (flag ){
			publishResults(flag, value+" search result displayed as expected", value+" search result should be displayed as expected","Verify search filter applied as expected");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  value+" search result not displayed as expected", value+" search result should be displayed as expected","Verify search filter applied as expected");
		}
		return this;
}
	/*************************************************************************************
	 * Method Name: validateAllSubmissionChannel
	 * Purpose:Method for validating the search table for the filtered tset value passed
	 * Author: Deepa Ramu
	 * Created Date:  15-June-2017
	/*************************************************************************************/
	
	public  SearchArchivePage validateAllSubmissionChannel( ){

		WebElement status = null  ;
		ArrayList<WebElement> rowcount = new ArrayList<>();
		rowcount=(ArrayList<WebElement>) tblSearchResults.findElements(By.tagName("tr"));
		int i=1;
		String a = null;
		String b = null;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()",btnChannelSort);
		pause(1000);
		clickElement (btnChannelSort);
		//clickElement (btnChannelSort);
		for (WebElement rowElement:rowcount){			
				status = rowElement.findElement(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr["+i+"]/td[13]"));	

			 if ( status.getText().equals("Automated Collection") || status.getText().equals("Central Collection") ||
					 status.getText().equals("Counter Collection") || status.getText().equals("Bulk Remote Deposit Capture") ||
					 status.getText().equals("Remote Deposit Capture")) 	 {
				 if (i==1 ){
				a = status.getText();
				 }
				b = status.getText();
				 flag =true;				
			 }	
			 else{
				 flag =false;
				 break;
			 }
			  if ((i >=(rowcount.size()-2)) || (a.equals(b))==false ) {
				 break;
			 }
			  i++;
		}
		assertTrue(flag,"Search results for All channel filtered as expected");
		if (flag ){
			publishResults(flag, "search result for All channel displayed as expected", "search result for All channel should be displayed as expected","Verify search filter is applied as expected");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  "search result for All channel not displayed as expected", "search result for All channel should be displayed as expected","Verify search filter is applied as expected");
		}
		return this;
}
	
	/*************************************************************************************
	 * Method Name: assertstatusFieldDisabled
	 * Purpose:Method for validating status field is enabled or disabled
	 * Author: Deepa Ramu
	 * Created Date:  23-May-2017
	/*************************************************************************************/	
	public SearchArchivePage validateStatusFieldDisabled()
	{	
		selectDropdownValue(driver, lstWorkflowStatus, "Completed");
		fillTextBox(txtAmoutFrom,"1");
		fillTextBox(txtAmoutTo,"1000");	
		clickElementEnter (btnSearch);
		if(!(lstStatus.isEnabled())){
			publishResults(!(lstStatus.isEnabled()),  "Status field is disabled as expected", "Status field should be disabled when workflow status is completed","Verify Status field is disabled when workflow status is completed");
			}else
		{	
				finalTestScriptResultFailFlag = true;
				publishResults(lstStatus.isEnabled(), "Status field is enabled", "Status field should be disabled when workflow status is completed","Verify Status field should is disabled when workflow status is completed");						
		}
		selectDropdownValue(driver, lstWorkflowStatus, "Debit InClearing");
		if(lstStatus.isEnabled()){
			publishResults(lstStatus.isEnabled(),  "Status field is enabled as expected", "Status field should be enabled when workflow status is not completed","Verify Status field is enabled when workflow status is not completed");
			}else
		{	
				finalTestScriptResultFailFlag = true;
				publishResults(lstStatus.isEnabled(), "Status field is disabled","Status field is not enabled when workflow status is not completed","Verify Status field is enabled when workflow status is not completed");				
		}
		
		return null;
	}

	/*************************************************************************************
	 * Method Name: validateSearchResultsWorkflowStatusFilter
	 * Purpose:Method for validating the search table for the filtered tset value passed
	 * Author: Deepa Ramu
	 * Created Date:  15-June-2017
	/*************************************************************************************/
	
	public  SearchArchivePage validateSearchResultsWorkflowStatusFilter(String value ){

		flag =false;
		WebElement status = null  ;
		ArrayList<WebElement> rowcount = new ArrayList<>();
		rowcount=(ArrayList<WebElement>) tblSearchResults.findElements(By.tagName("tr"));
		int i=1;
		for (WebElement rowElement:rowcount){	
			status = rowElement.findElement(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr["+i+"]/td[11]"));	
			
			if ( (value == "Credit InClearing") || (value == "Debit InClearing") ){
			
			 if ( status.getText().equals("Paid") ||  status.getText().equals("Not Paid") 
					 ||  status.getText().equals("")) 	 {	
				 flag =true;
			 }
			}
			else if ((value == "Completed")){
				 if ( status.getText().equals("All Process Complete")) 	 {	
					 flag =true;
				 }
			}
			 else{
				 flag =false;
				 break;
			 }
			  if (i >=(rowcount.size()-2) ) {
				 break;
			 }
			  i++;
		}
		if (flag ){
			publishResults(flag, "search result for workflow status"+value+" displayed as expected", "search result for workflow status"+value+" should be displayed as expected","search filter applied as expected for"+value );
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  "search result for workflow status"+value+" not displayed as expected", "search result for workflow status"+value+" should be displayed as expected","search filter applied as expected for"+value);
		}
		assertTrue(flag,"Search results for workflow status"+value+" filtered as expected");
		return this;
}
	/*************************************************************************************
	 * Method Name: assertWorkflowStatusFilter
	 * Purpose:Method for validating workflow status filter is applied and results are as expected
	 * Author: Deepa Ramu
	 * Created Date:  22-May-2017
	/*************************************************************************************/	
	public SearchArchivePage assertWorkflowStatusFilter(String workflowStatus)
	{
		fillSearchFilterDetails();
		pause (1000);
		selectDropdownValue(driver, lstWorkflowStatus, workflowStatus);
		clickElementEnter (btnSearch);
		if (workflowStatus == "Credit InClearing"){						
			validateSearchResultsWorkflowStatusFilter(workflowStatus);		
		}		
		else if (workflowStatus == "Debit InClearing"){
			//clickElementSpace(agencyRolePaying);
			clickElementSpace(agencyRoleBeneficiary);
			clickElementEnter (btnSearch);
			validateSearchResultsWorkflowStatusFilter(workflowStatus);	
		}
		else if (workflowStatus == "Outclearing"){
			validateSearchResultsWorkflowStatusFilter(workflowStatus);	
		}
		else if (workflowStatus == "Completed"){
			validateSearchResultsWorkflowStatusFilter("Completed");	
		}
		
		return null;
	}


/*************************************************************************************
 * Method Name: assertSubmissionChannel
 * Purpose:Method for validating submission channel filter is applied and results are as expected
 * Author: Deepa Ramu
 * Created Date:  22-May-2017
/*************************************************************************************/	
public SearchArchivePage assertSubmissionChannel(String submissionChannel)
{
		fillSearchFilterDetails();
		selectDropdownValue(driver, lstSubmissionChannel, submissionChannel);
		clickElementEnter (btnSearch);
		if (submissionChannel=="All Channel"){
			validateAllSubmissionChannel();
		}
		else{
		validateSearchResults("channel" ,submissionChannel);	}
		return null;
	}

/*************************************************************************************
 * Method Name: validatePaginationSearch
 * Purpose:Method for validating pagination functionality on search screen
 * Author: Deepa Ramu
 * Created Date:  22-May-2017
/*************************************************************************************/	
public SearchArchivePage validatePaginationSearch()
{
	fillTextBox(txtAmoutFrom,"1");
	fillTextBox(txtAmoutTo,"10000");	
	selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");
	clickElement (btnSearch);
	pause(2000);
	validationStepInformation("Initial Pagination details :");
	if(filterPageDetails.getText().contains("Showing 1 to 10 of") && (paginationPrevious.getAttribute("class").contains("paginate_button previous disabled")) && !(paginationNext.getAttribute("class").contains("paginate_button next disabled")) )
	{
		flag=true;
		publishResults(true, "Pagination details is displayed , Previous button is currently disabled and Next button is enabled", "Pagination details should be present as expected", "Verify pagination details are displayed");
	}else
	{	flag=false;
		finalTestScriptResultFailFlag = true;
		publishResults(false, "Pagination details is not displayed as expected", "Pagination details should be present as expected", "Verify pagination details are displayed");
	}
	assertTrue(flag, "Pagination details dispalyed as expected");
				
	validationStepInformation("Click on Next button Validation :");
	clickElement(paginationNext);
	pause(2000);
	if(!(paginationPrevious.getAttribute("class").contains("paginate_button previous disabled")) && (paginationNext.getAttribute("class").contains("paginate_button next disabled")) 
			&& filterPageDetails.getText().contains("Showing 11 to 11 of") ){
			flag=true;
			publishResults(flag, "Pagination details when Next is clicked is displayed as expected ", "Pagination details when Next is clicked should be displayed as expected", "Verify next page grid is displayed , previous button is Enabled");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Pagination details when Next is clicked is displayed as expected ", "Pagination details when Next is clicked should be displayed as expected", "Verify next page grid is displayed , previous button is Enabled");
		}
	assertTrue(flag, "Pagination details when Next is clicked is not dispalyed as expected");
	validationStepInformation("Click on Previous button Validation :");
	clickElement(paginationPrevious);
	pause(2000);
	if((paginationPrevious.getAttribute("class").contains("paginate_button previous disabled")) && !(paginationNext.getAttribute("class").contains("paginate_button next disabled")) 
			&& filterPageDetails.getText().contains("Showing 1 to 10 of") ){
			flag=true;
			publishResults(flag, "Pagination details when Previous is clicked is displayed as expected ", "Pagination details when Previous is clicked is displayed should be as expected", "Verify Previous page grid is displayed , previous button is Disabled");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Pagination details when Previous is clicked is not displayed as expected ", "Pagination details when Previous is clicked is displayed should be as expected", "Verify Previous page grid is displayed , previous button is Disabled");
		}
	assertTrue(flag, "Pagination details when Previous is clicked is not dispalyed as expected");
	List <WebElement> pagination = driver.findElements(By.xpath("//div[@id='SearchGrid_grid_paginate']//ul//li"));
	System.out.println(pagination.size());
	
	if (pagination.size() > 2){
		publishResults(true, "Multiple pages are displayed", "Multiple pages should be displayed", "Verify more than one page is displayed");
	}
		WebElement page2 = driver.findElement(By.xpath("//div[@id='SearchGrid_grid_paginate']/ul/li[3]"));
		
		clickElement(page2);
		 
			System.out.println("clicked on page no. 2");				 
			Boolean Page2Previous = verifyTrue(!(paginationPrevious.getAttribute("class").contains("paginate_button previous disabled")),"previous is enabled");				
			Boolean Page2Next = verifyTrue((paginationNext.getAttribute("class").contains("paginate_button next disabled")),"next is disabled");
			Boolean Page2Status = verifyTrue(filterPageDetails.getText().contains("Showing 11 to"),"status field updated accordingly");
			
			if(Page2Previous==true && Page2Next==true && Page2Status==true ){
					publishResults(true, "Pagination details for page 2 is displayed as expected ", "Pagination details when page 2 is clicked should be as expected", "Verify page 2 is displayed , previous button is enabled with status messagae updated");
				}else
				{
					finalTestScriptResultFailFlag = true;
					publishResults(false, "Pagination details for page 2 is not displayed as expected ", "Pagination details when page 2 is clicked should be as expected", "Verify page 2 is displayed , previous button is enabled with status messagae updated");
				}
			WebElement page1 = driver.findElement(By.xpath("//div[@id='SearchGrid_grid_paginate']/ul/li[2]"));			
			pause(2000);
			clickElement(page1);
			pause(2000);
			 System.out.println("clicked on page no. 1");				 
			 Boolean Page1Previous = verifyTrue((paginationPrevious.getAttribute("class").contains("paginate_button previous disabled")),"previous is disabled");
			 Boolean Page1Next = verifyTrue(!(paginationNext.getAttribute("class").contains("paginate_button next disabled")),"next is enabled");
			 Boolean Page1Status = verifyTrue(filterPageDetails.getText().contains("Showing 1 to 10 of"),"status field updated accordingly");
			 			
			 if(Page1Previous==true && Page1Next==true && Page1Status==true ){
					publishResults(true, "Pagination details for page 1 is displayed as expected ", "Pagination details when page 1 is clicked should be as expected", "Verify page 1 is displayed , previous button is disabled with status messagae updated");
				}else
				{
					finalTestScriptResultFailFlag = true;
					publishResults(false, "Pagination details for page 1 is not displayed as expected ", "Pagination details when page 1 is clicked should be as expected", "Verify page 1 is displayed , previous button is disabled with status messagae updated");
				}
	return this;

}

/*************************************************************************************
 * Method Name: validateSearchResultsIconEnabled
 * Purpose:Method for validating Image ,Kappa and other icon enabled or disabled
 * Author: Deepa Ramu
 * Created Date:  04-July-2017
/*************************************************************************************/

public  SearchArchivePage validateSearchResultsIcon(String value ){
	if ( (value == "Enabled") ){	
		selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");
		fillTextBox(txtTsetId,getAgencyValueFromDataSheet("tSetIDCredit"));
		clickElementEnter (btnSearch);
		pause(1000);
		clickElement(btnImageSort);
		pause(1000);
		/*boolean flag1 = (imgImage.getAttribute("class").contains("form-img-enabled CM-IMG-E"))
				 && (imgKappa.getAttribute("class").contains("fa fa-kappa fa-2x clsImgKappa AGYtooltip"))
				 && (imgOther.getAttribute("class").contains("fa fa-Other fa-2x clsImgOthers AGYtooltip"));*/
		
		 
		 clickElement(imgImage);
		 clickElement(imgImage);			
			pause(3000);	
			if(isElementDisplayed(imgFrontView))
			{
				clickElement(imgFrontZoomIn);
				clickElement(imgFrontZoomOut);
				clickElement(imgFrontonetoone);
				clickElement(imgFrontReset);
				clickElement(imgFrontPrev);
				clickElement(imgFrontNext);	
				clickElement(imgFrontRotateLeft);
				clickElement(imgFrontRotateRight);
				
				flag=true;
				publishResults(flag, "Image Front view is displayed with zoom ,invert,rotate options working", "Image Front view is displayed with zoom ,invert,rotate options should be visible", "Verify Image front is dispalyed");
			}else
			{	flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "Image Front view is displayed with zoom ,invert,rotate options not working", "Image Front view is displayed with zoom ,invert,rotate options should be visible", "Verify Image front is dispalyed");
			}
			
			if(isElementDisplayed(imgRearView))
			{
				clickElement(imgRearZoomIn);
				clickElement(imgRearZoomOut);
				clickElement(imgRearonetoone);
				clickElement(imgRearReset);
				clickElement(imgRearPrev);
				clickElement(imgRearNext);
				clickElement(imgRearRotateLeft);
				clickElement(imgRearRotateRight);
				flag=true;
				publishResults(flag, "Image Rear view is displayed with zoom ,invert,rotate options working", "Image Rear view is displayed with zoom ,invert,rotate options should be visible", "Verify Image Rear is dispalyed");
			}else
			{	flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "Image Rear view is displayed with zoom ,invert,rotate options not working", "Image Rear view is displayed with zoom ,invert,rotate options should be visible", "Verify Image Rear is dispalyed");
			}
			assertTrue(flag, "Image displayed as expected");	
			clickElement (imgKappa);
			pause(2000);
			flag = kappaData.getAttribute("Class").contains("css-for-OtherInfo") && kappaData.getAttribute("Class").contains("trKappa")
					&& kappaImage.getAttribute("Class").contains("trImages css-for-img"); 
			if(flag)
			{
				publishResults(flag, "Kappa Expander with Data and Image is Visible", "Kappa Expander with Data and Image should be present Visible", "Verify Kappa expander is dispalyed");
			}else
			{	
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "Kappa Expander with Data and Image is not Visible", "Kappa Expander with Data and Image should be present Visible", "Verify Kappa expander is dispalyed");
			}	
			assertTrue(flag, "Kappa Image and data displayed as expected");
			clickElement(imgOther);
			pause(1000);		
			if (otherExpander.isDisplayed())
		{
			flag=true;
			publishResults(flag, "Other Information data displayed", "Other Information data should be displayed", "Verify Other Information data is dispalyed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Other Information data is not displayed", "Other Information data should be displayed", "Verify Other Information data is dispalyed");
		}	 
		assertTrue(flag,"Other details is present");
		clickElement(imgKappaDataCollapse);
		clickElement(imgKappaImageCollapse);
		clickElement(imgImageCollapse);
		}
	 else if ( (value == "Disabled") ){
		 selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");
		 	fillTextBox(txtTsetId,getAgencyValueFromDataSheet("tSetIDDebit"));
			clickElementEnter (btnSearch);
		 if ( (imgImage.getAttribute("class").contains("fa fa-cam fa-2x form-img-disabled CM-IMG-D"))
				 && (imgKappa.getAttribute("class").contains("fa fa-kappa fa-2x form-img-disabled CM-IMG-D AGYtooltip"))
				 /*&& (imgOther.getAttribute("class").contains("fa fa-Other fa-2x form-img-disabled CM-IMG-D AGYtooltip"))*/) 	 {	
			 flag =true;
		 }
		 else{
			 flag =false;
		 }
	}
	if (flag ){
		publishResults(flag, "Image ,Kappa and other icon "+value+" as expected", "Image ,Kappa and other icon should be "+value,"Verify Image ,Kappa and other icon is "+value);
	}else
	{
		finalTestScriptResultFailFlag = true;
		publishResults(flag,  "Image ,Kappa and other icon not "+value, "Image ,Kappa and other icon should be "+value,"Verify Image ,Kappa and other icon is "+value);
	}
	assertTrue(flag,"Image ,Kappa and other icon not "+value);
	return this;
}

/*************************************************************************************
 * Method Name: validateSearchResultsColumnHeaders
 * Purpose:Method for validating Search Results Column Headers
 * Author: Deepa Ramu
 * Created Date:  04-July-2017
/*************************************************************************************/

public  SearchArchivePage validateSearchResultsColumnHeaders(){
	
		fillSearchFilterDetails();
		clickElementEnter (btnSearch);
		for (int i=2 ; i<14 ; i++){	
		WebElement column = driver.findElement(By.xpath("//div[@class='dataTables_scrollHeadInner']/table/thead/tr/th["+i+"]"));
		if (i==2 ){
			assertTrue(column.getText().equals("Date"),"Verify Date Column");
		}
		else if (i==3 ){
			assertTrue(column.getText().equals("Type"),"Verify Type Column");
		}
		else if (i==4 ){
			assertTrue(column.getText().equals("SortCode"),"Verify SortCode Column");
		}
		else if (i==5 ){
			assertTrue(column.getText().equals("Account"),"Verify Account Column");
		}
		else if (i==6 ){
			assertTrue(column.getText().equals("Serial"),"Verify Serial Column");
		}
		else if (i==7 ){
			assertTrue(column.getText().equals("Amount"),"Verify Amount Column");
		}
		else if (i==8 ){
			assertTrue(column.getText().equals("Image"),"Verify Image Column");
		}
		else if (i==9 ){
			assertTrue(column.getText().equals("Kappa"),"Verify Kappa Column");
		}
		else if (i==10 ){
			assertTrue(column.getText().equals("Other"),"Verify Other Column");
		}
		else if (i==11 ){
			assertTrue(column.getText().equals("Status"),"Verify Status Column");
		}
		else if (i==12 ){
			assertTrue(column.getText().equals("Tset"),"Verify Tset Column");
		}
		else if (i==13 ){
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView()",btnChannelSort);
			assertTrue(column.getText().equals("Channel"),"Verify Channel Column");
			flag=true;
		}	
	} 
	if (flag ){
		publishResults(flag, "All columns present as expected", "Search table column should be present as expected","Verify all column values are present as expected");
	}else
	{
		finalTestScriptResultFailFlag = true;
		publishResults(flag,  "All columns present as expected", "Search table column should be present as expected","Verify all column values are present as expected");
	}
	return this;
}

/*************************************************************************************
 * Method Name: validateSearchResultsColumnHeaders
 * Purpose:Method for validating Search Results Column Headers
 * Author: Deepa Ramu
 * Created Date:  04-July-2017
/*************************************************************************************/

public SearchArchivePage assertClearFieldsinSearchScreen()
{
	boolean flagClearFields=false;
	selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");
	fillTextBox(txtTsetId,getAgencyValueFromDataSheet("tSetIDCreditwithVersion"));	
	clickElementEnter (btnSearch);
	pause(1000);
	String sortCodeFrom = tblSortCode.getText();		
	int sortCodeTo1 = Integer.parseInt(sortCodeFrom);
	sortCodeTo1 = sortCodeTo1+1;
	String sortCodeTo = Integer.toString(sortCodeTo1);	
	String tblAccountValue = tblAccount.getText();	
	String tblSerialValue = tblSerial.getText();
	clickElement(btnClearSearch);
	pause(1000);
	fillTextBox(txtAccount,tblAccountValue);	
	fillTextBox(txtSortCodeTo,sortCodeTo);
	fillTextBox(txtSortCodeFrom,sortCodeFrom);
	fillTextBox(txtSerial,tblSerialValue);	
	clickElement(btnClearSearch);
	pause(1000);
	Select workFlowStatus = new Select (lstWorkflowStatus);
	String statusResult = workFlowStatus.getFirstSelectedOption().getText();
	
	if( statusResult.contentEquals("Select Workflow Status")  
			&& txtSortCodeFrom.getText().isEmpty() && txtSortCodeTo.getText().isEmpty()
			&& txtAccount.getText().isEmpty() && txtAmoutFrom.getText().isEmpty() 
			&& txtAmoutTo.getText().isEmpty() && txtSerial.getText().isEmpty()
			&& (isElementEnabled(lstStatus)))
		flagClearFields=true;
	assertTrue(flagClearFields, "All fields are cleared");
	if(flagClearFields){
		publishResults(flagClearFields, "All fields cleared - 1" , "All fields should be cleared", "Verify all fields are cleared when clicked on Clear filter");	
	}else
	{
		finalTestScriptResultFailFlag = true;
		publishResults(flagClearFields, "All fields are not cleared" , "All fields should be cleared", "Verify all fields are cleared when clicked on Clear filter");
	}	
	flagClearFields=false;
	selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");
	fillTextBox(txtTsetId,getAgencyValueFromDataSheet("tSetIDCredit"));	
	clickElementEnter(btnSearch);
	pause(1000);
	clickElement(btnClearSearch);
	pause(1000);
	if( statusResult.contentEquals("Select Workflow Status")  
			&& txtSortCodeFrom.getText().isEmpty() && txtSortCodeTo.getText().isEmpty()
			&& txtAccount.getText().isEmpty() && txtAmoutFrom.getText().isEmpty() 
			&& txtAmoutTo.getText().isEmpty() && txtSerial.getText().isEmpty() && txtTsetId.getText().isEmpty() 
			&& (isElementEnabled(lstStatus)))
	flagClearFields=true;
	
	if(flagClearFields){
		publishResults(flagClearFields, "All fields cleared - 2" , "All fields should be cleared", "Verify all fields are cleared when clicked on Clear filter");	
	}else
	{
		finalTestScriptResultFailFlag = true;
		publishResults(flagClearFields, "All fields are not cleared" , "All fields should be cleared", "Verify all fields are cleared when clicked on Clear filter");
	}	
	assertTrue(flagClearFields, "All fields are cleared");
	return this;
}

/*************************************************************************************
 * Method Name: validateSorting
 * Purpose:Method for validating Sorting of columns in Search screen
 * Author: Deepa Ramu
 * Created Date:  10-July-2017
/*************************************************************************************/
public SearchArchivePage validateSorting() throws Exception
{
validationStepInformation("Data should be sorted in descending order by Default");

	fillTextBox(txtAmoutFrom,"1");
	fillTextBox(txtAmoutTo,"10000");	
	selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");
	clickElement (btnSearch);
	pause(2000);
	
	List<WebElement> elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[2]"));	
	flag = verifySortDescendingDate (elementList);
	BankLandingPage.publishStatementDescending ("Date",flag);
	pause(1000);
	clickElement(btnDateSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[2]"));
	flag = verifySortAscendingDate  (elementList);
	BankLandingPage.publishStatementAscending ("Date",flag);

validationStepInformation("Verify Sorting of Type Column");
	
	pause(1000);
	clickElement(btnTypeSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[3]"));
	flag = verifySortDescending (elementList);
	BankLandingPage.publishStatementDescending ("Type",flag);
	pause(1000);
	clickElement(btnTypeSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[3]"));
	flag = verifySortAscending (elementList);
	BankLandingPage.publishStatementAscending ("Type",flag);
	
validationStepInformation("Verify Sorting of Sort Code");
	
	pause(1000);
	clickElement(btnSortcodeSort);
	elementList = driver.findElements(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr/td[4]"));
	flag = verifySortDescending (elementList);
	BankLandingPage.publishStatementDescending ("Sort Code",flag);
	pause(1000);		
	clickElement(btnSortcodeSort);
	elementList = driver.findElements(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr/td[4]"));
	flag = verifySortAscending (elementList);
	BankLandingPage.publishStatementAscending ("Sort Code",flag);
	
	
validationStepInformation("Verify Sorting of Account Column");

	pause(1000);
	clickElement(btnAccountSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[5]"));
	flag = verifySortDescending (elementList);
	BankLandingPage.publishStatementDescending ("Account",flag);
	pause(1000);
	clickElement(btnAccountSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[5]"));
	flag = verifySortAscending (elementList);
	BankLandingPage.publishStatementAscending ("Account",flag);
	
	
validationStepInformation("Verify Sorting of Serial Number");
	
	pause(1000);
	clickElement(btnSerialSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[6]"));
	flag = verifySortDescending (elementList);
	BankLandingPage.publishStatementDescending ("Serial",flag);
	pause(1000);
	clickElement(btnSerialSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[6]"));
	flag = verifySortAscending (elementList);
	BankLandingPage.publishStatementAscending ("Serial",flag);
	

/*validationStepInformation("Verify Sorting of Amount");
	
	pause(1000);
	clickElement(btnAmountSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[7]"));
	flag = verifySortDescendingAmount (elementList);
	BankLandingPage.publishStatementDescending ("Amount",flag);
	pause(1000);
	clickElement(btnAmountSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[7]"));
	flag = verifySortAscending (elementList);
	BankLandingPage.publishStatementAscending ("Amount",flag);*/
	
validationStepInformation("Verify Sorting of Image");
	pause(1000);
	clickElement(btnImageSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[8]/i"));
	flag = verifyImageSorting (elementList , "descending");
	BankLandingPage.publishStatementDescending ("Image icon",flag);
	pause(1000);
	clickElement(btnImageSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[8]/i"));
	flag = verifyImageSorting (elementList , "ascending");
	BankLandingPage.publishStatementAscending ("Image icon",flag);
	
	
validationStepInformation("Verify Sorting of Kappa");
	pause(1000);
	clickElement(btnKappaSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[9]/i"));
	flag = verifyKappaSorting (elementList , "descending");
	BankLandingPage.publishStatementDescending ("Kappa icon",flag);
	pause(1000);
	clickElement(btnKappaSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[9]/i"));
	flag = verifyKappaSorting (elementList , "ascending");
	BankLandingPage.publishStatementAscending ("Kappa icon",flag);
	
validationStepInformation("Verify Sorting of Other Icon");
	pause(1000);
	clickElement(btnOtherSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[10]/i"));
	flag = verifyOtherSorting (elementList , "descending");
	BankLandingPage.publishStatementDescending ("Other icon",flag);
	pause(1000);
	clickElement(btnOtherSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[10]/i"));
	flag = verifyOtherSorting (elementList , "descending");
	BankLandingPage.publishStatementAscending ("Other icon",flag);
	

validationStepInformation("Verify Sorting of Status");
	
	pause(1000);
	clickElement(btnStatusSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[11]"));
	flag = verifySortDescending (elementList);
	BankLandingPage.publishStatementDescending ("Tset",flag);
	pause(1000);
	clickElement(btnStatusSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[11]"));
	flag = verifySortAscending (elementList);
	BankLandingPage.publishStatementAscending ("Tset",flag);
	
	
validationStepInformation("Verify Sorting of Tset");
	
	pause(1000);
	clickElement(btnTsetSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[12]"));
	flag = verifySortDescending (elementList);
	BankLandingPage.publishStatementDescending ("Tset",flag);
	pause(1000);
	clickElement(btnTsetSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[12]"));
	flag = verifySortAscending (elementList);
	BankLandingPage.publishStatementAscending ("Tset",flag);

	
validationStepInformation("Verify Sorting of Channel");
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	jse.executeScript("arguments[0].scrollIntoView()",btnChannelSort);
	pause(1000);
	clickElement(btnChannelSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[13]"));
	flag = verifySortDescending (elementList);
	BankLandingPage.publishStatementDescending ("Channel",flag);
	pause(1000);
	clickElement(btnChannelSort);
	elementList = driver.findElements(By.xpath("//*[@id='SearchGrid_grid']/tbody/tr/td[13]"));
	flag = verifySortAscending (elementList);
	BankLandingPage.publishStatementAscending ("Channel",flag);
	
	
	return this;	
}

public static boolean verifyImageSorting(List<WebElement> elementList,String check ){
int countEnabled = 0;
int countDisabled = 0;
int i=1;
	try {
		ArrayList<String> obtainedList = new ArrayList<>();
		for(WebElement rowEle:elementList){	
			if (rowEle.getAttribute("class").equals("fa fa-cam fa-2x clsImgHeld GridItemImage form-img-enabled CM-IMG-E AGYtooltip")){
			countEnabled++;
			}
			else if (rowEle.getAttribute("class").equals("fa fa-cam fa-2x form-img-disabled CM-IMG-D")){
			countDisabled++;
			}
			obtainedList.add(rowEle.getText());
		}
		
		if (check == "descending" ){		
		for(WebElement rowEle:elementList){
			if (rowEle.getAttribute("class").equals("fa fa-cam fa-2x clsImgHeld GridItemImage form-img-enabled CM-IMG-E AGYtooltip") 
					&& i<countEnabled){
				i++;
				if (i==countEnabled)
				{
					flag=true;
					break;
				}
				}	
			else{
				System.out.println("Element is not enabled");
				break;			
			}
		}
		}
		else if  (check == "ascending" ){
			for(WebElement rowEle:elementList){
				if (rowEle.getAttribute("class").equals("fa fa-cam fa-2x form-img-disabled CM-IMG-D") 
						&& i<countEnabled){
					i++;
					if (i==countDisabled)
					{
						flag=true;
						break;
					}
					}	
				else{
					System.out.println("Element is not disabled");
					break;			
				}
			}		
		}
		assertTrue(flag, "Image not sorted in "+check+ "order");
		return flag;
	} catch (AssertionError e) {
		return false;
	}
}

public static boolean verifyKappaSorting(List<WebElement> elementList,String check ){
int countEnabled = 0;
int countDisabled = 0;
int i=1;
	try {
		ArrayList<String> obtainedList = new ArrayList<>();
		for(WebElement rowEle:elementList){	
			if (rowEle.getAttribute("class").equals("fa fa-kappa fa-2x clsImgKappa AGYtooltip CM-IMG-E")){
			countEnabled++;
			}
			else if (rowEle.getAttribute("class").equals("fa fa-kappa fa-2x form-img-disabled CM-IMG-D AGYtooltip")){
			countDisabled++;
			}
			obtainedList.add(rowEle.getText());
		}
		
		if (check == "descending" ){		
		for(WebElement rowEle:elementList){
			if (rowEle.getAttribute("class").equals("fa fa-kappa fa-2x clsImgKappa AGYtooltip CM-IMG-E") 
					&& i<countEnabled){
				i++;
				if (i==countEnabled)
				{
					flag=true;
					break;
				}
				}	
			else{
				System.out.println("Element is not enabled");
				break;			
			}
		}
		}
		else if  (check == "ascending" ){
			for(WebElement rowEle:elementList){
				if (rowEle.getAttribute("class").equals("fa fa-kappa fa-2x form-img-disabled CM-IMG-D AGYtooltip") 
						&& i<countEnabled){
					i++;
					if (i==countDisabled)
					{
						flag=true;
						break;
					}
					}	
				else{
					System.out.println("Element is not disabled");
					break;			
				}
			}		
		}
		assertTrue(flag, "Kappa Not sorted in "+check+ "order");
		return flag;
	} catch (AssertionError e) {
		return false;
	}
}

public static boolean verifyOtherSorting(List<WebElement> elementList,String check ){
int countEnabled = 0;
int countDisabled = 0;
int i=1;
	try {
		ArrayList<String> obtainedList = new ArrayList<>();
		for(WebElement rowEle:elementList){	
			if (rowEle.getAttribute("class").equals("fa fa-Other fa-2x clsImgOthers AGYtooltip CM-IMG-E")){
			countEnabled++;
			}
			else if (rowEle.getAttribute("class").equals("fa fa-Other fa-2x form-img-disabled CM-IMG-D AGYtooltip")){
			countDisabled++;
			}
			obtainedList.add(rowEle.getText());
		}
		
		if (check == "descending" ){		
		for(WebElement rowEle:elementList){
			if (rowEle.getAttribute("class").equals("fa fa-Other fa-2x clsImgOthers AGYtooltip CM-IMG-E") 
					&& i<countEnabled){
				i++;
				if (i==countEnabled)
				{
					flag=true;
					break;
				}
				}	
			else{
				System.out.println("Element is not enabled");
				break;			
			}
		}
		}
		else if  (check == "ascending" ){
			for(WebElement rowEle:elementList){
				if (rowEle.getAttribute("class").equals("fa fa-Other fa-2x form-img-disabled CM-IMG-D AGYtooltip") 
						&& i<countEnabled){
					i++;
					if (i==countDisabled)
					{
						flag=true;
						break;
					}
					}	
				else{
					System.out.println("Element is not disabled");
					break;			
				}
			}		
		}
		assertTrue(flag, "Kappa Not sorted in "+check+ "order");
		return flag;
	} catch (AssertionError e) {
		return false;
	}
}

public static boolean verifySortDescendingAmount(List<WebElement> elementList ){

	try {
		
		ArrayList<String> obtainedList = new ArrayList<>();
		for(WebElement rowEle:elementList){		
			String amount = rowEle.getText().trim().replaceAll("£", "");
			//float amount1=Float.valueOf(amount);
			obtainedList.add(amount);
		}
	
		System.out.println(obtainedList);
		ArrayList<String> sortedList = new ArrayList<>();
		for(String s:obtainedList){
			sortedList.add(s);			
		}
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		
		System.out.println(sortedList);
		assertTrue(sortedList.equals(obtainedList), "Not Sorted in Decending order");
		return true;
	} catch (AssertionError e) {
		return false;
	}	
}

public static boolean verifySortDescendingDate(List<WebElement> elementList ){

	try {
		ArrayList<String> obtainedList = new ArrayList<>();
		
		
		for(WebElement rowEle:elementList){
	
			/*DateFormat inputFormat = new SimpleDateFormat("dd/M/yyyy hh:mm");
			inputFormat.setLenient(false);
			DateFormat outputFormat = new SimpleDateFormat("dd/M/yyyy");
			outputFormat.setLenient(false);
			Date d = inputFormat.parse(rowEle.getText());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	*/
			String b = rowEle.getText().substring(0,rowEle.getText().indexOf(' '));
			String a = rowEle.getText().split(" ",1)[0];
			obtainedList.add(b);		
		}
	
		System.out.println(obtainedList);
		ArrayList<String> sortedList = new ArrayList<>();
		for(String s:obtainedList){
			sortedList.add(s);			
		}
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		
		System.out.println(sortedList);
		assertTrue(sortedList.equals(obtainedList), "Not Sorted in Decending order");
		return true;
	} catch (AssertionError e) {
		return false;
	}	
}

public static boolean verifySortAscendingDate(List<WebElement> elementList ){

	try {
		ArrayList<String> obtainedList = new ArrayList<>();
		
		
		for(WebElement rowEle:elementList){
			String b = rowEle.getText().substring(0,rowEle.getText().indexOf(' '));
			String a = rowEle.getText().split(" ",1)[0];
			obtainedList.add(b);		
		}
	
		System.out.println(obtainedList);
		ArrayList<String> sortedList = new ArrayList<>();
		for(String s:obtainedList){
			sortedList.add(s);			
		}
		Collections.sort(sortedList);	
		System.out.println(sortedList);
		assertTrue(sortedList.equals(obtainedList), "Not Sorted in Ascending order");
		return true;
	} catch (AssertionError e) {
		return false;
	}	
}

/*************************************************************************************
 * Method Name: assertClientAccessibilityText
 * Purpose:Method for validating the client accessibility text when hovered over the required links 
 * Author: Deepa Ramu
 * Created Date:  11-Aug-2017
/*************************************************************************************/

public SearchArchivePage assertClientAccessibilityText()
{
	boolean tsetFlag=false;
	boolean kappaFlag=false;
	boolean ImageFlag=false;
	boolean otherFlag=false;
	Actions action = new Actions(driver);
	selectDropdownValue(driver, lstWorkflowStatus, "Credit InClearing");
	fillTextBox(txtTsetId,getAgencyValueFromDataSheet("tSetIDCredit"));
	clickElementEnter (btnSearch);
	pause(2000);
	
	action.moveToElement(imgKappa).build().perform();
	if (imgKappaTooltip.getAttribute("class").contains("tooltiptext") &&  imgKappaTooltip.getText().contains("Kappa")){
		kappaFlag=true;
	}
	action.moveToElement(imgImage).build().perform();
	if (imgImageTooltip.getAttribute("class").contains("tooltiptext") &&  imgImageTooltip.getText().contains("Image")){
		ImageFlag=true;
	}
	action.moveToElement(imgOther).build().perform();
	if (imgOtherTooltip.getAttribute("class").contains("tooltiptext") &&  imgOtherTooltip.getText().contains("Other")){
		otherFlag=true;
	}
	
	/*clickElement(imgAuditHistoryExpander);	
	action.moveToElement(imgAuditHistoryExpander).build().perform();
	if (imgAuditHistoryExpanderTooltip.getAttribute("class").contains("tooltiptext") && imgAuditHistoryExpanderTooltip.getText().contentEquals("AuditHistory")){
		tsetFlag=true;
	}
	*/
	if (/*tsetFlag==true &&*/ kappaFlag==true && ImageFlag==true && otherFlag==true)  {		
		publishResults(true, "tool tip value is present for grid icons" , "verify tool tip value is present for grid icons", "Verify tool tip value is present for grid icons(image,kappa and other)");
	}else
	{
		finalTestScriptResultFailFlag = true;
		publishResults(false, "tool tip value is not present for grid icons" , "verify tool tip value is present for grid icons", "Verify tool tip value is present for grid icons(image,kappa and other)");
	}
	action.moveToElement(btnFilterExpandCollapse).build().perform();
	if (btnFilterExpandCollapseTooltip.getAttribute("class").contains("tooltiptext") && btnFilterExpandCollapseTooltip.getText().contentEquals("Filter") ){		
		publishResults(true, "tool tip value is present for filter icon" , "verify tool tip value is present for filter icon", "Verify tool tip value is present for filter icon");
	}else
	{
		finalTestScriptResultFailFlag = true;
		publishResults(false, "tool tip value is not present for filter icon" , "verify tool tip value is present for filter icon", "Verify tool tip value is present for filter icon");
	}		
	return this;
}

/*************************************************************************************
 * Method Name: assertWorkflowStatusSubmissionChannel
 * Purpose:Method for validating submission channel is present or not when workflow status is selected
 * Author: Deepa Ramu
 * Created Date:  19-Sep-2017
/*************************************************************************************/	
public SearchArchivePage assertWorkflowStatusSubmissionChannel(String workflowStatus)
{
	selectDropdownValue(driver, lstWorkflowStatus, workflowStatus);

	if (workflowStatus == "Credit InClearing" || workflowStatus == "Debit InClearing"){						
		if (lstSubmissionChannel.isDisplayed()){
			finalTestScriptResultFailFlag = true;
			publishResults(false,"submission channel is displayed when we select"+workflowStatus, "submission channel should not be displayed when we select"+workflowStatus,"verify submission channel is not displayed when we select"+workflowStatus);		
			}else
			{
				publishResults(true,"submission channel is not displayed when we select"+workflowStatus, "submission channel should not be displayed when we select"+workflowStatus,"verify submission channel is not displayed when we select"+workflowStatus);
		}
	}		
	else if (workflowStatus == "OutClearing"){
		Select submissionChannel = new Select (lstSubmissionChannel);
		String submissionChannelResult = submissionChannel.getFirstSelectedOption().getText();
		if (lstSubmissionChannel.isDisplayed() && submissionChannelResult.contentEquals("All Channel") ){
			publishResults(true,"submission channel is displayed when we select"+workflowStatus, "submission channel should be displayed when we select"+workflowStatus,"verify submission channel is displayed when we select"+workflowStatus);
			}else
			{
				finalTestScriptResultFailFlag = true;
				publishResults(false,"submission channel is not displayed when we select"+workflowStatus, "submission channel should be displayed when we select"+workflowStatus,"verify submission channel is displayed when we select"+workflowStatus);	
		}
	}
	
	return this;
}

}
