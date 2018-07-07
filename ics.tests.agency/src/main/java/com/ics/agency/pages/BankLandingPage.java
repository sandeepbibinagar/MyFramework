package com.ics.agency.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;

public class BankLandingPage extends ICSPageUtilis {

	@FindBy(id = "FrmAgency")
	private static WebElement agencyFrame;
	
	@FindBy(id = "divLogout")
	private static WebElement logoutPortal;
	
	@FindBy(id = "btnLogin")
	private static WebElement btnLoginPortal;
		
	@FindBy(id = "liExceptionItems")
	private static WebElement exceptions3;

	@FindBy(id = "liIncCredits")
	private static WebElement incomingCredits;
	
	@FindBy(id = "liSearch")
	private static WebElement searchArchive;

	@FindBy(id = "liAllCollectedItems")
	private static WebElement allCollectedItems;

	@FindBy(id="liCollectedElseWhere")
	private static WebElement collectedElseWhere;
	
	@FindBy(id = "liActivityLog1")
	private static WebElement settlementLimits;

	@FindBy(id="liAgencySummary")
	private static WebElement agencySummary;
	
	@FindBy(id="incomingCreditsAllItemsGrid")
	private static WebElement tblIncomingCreditsCollItems;
	
	@FindBy(id="incomingCreditsCollElseWhereGrid")
	private static WebElement tblIncomingCreditsCollElse ;
	
	@FindBy(id="incomingCreditsExceptionItemsGrid")
	private static WebElement tblIncomingCreditsException ;

	@FindBy(xpath="//div[@class='dataTables_scrollHeadInner']/table/thead/tbody/tr[2]/td[11]/select")
	private static List<WebElement> tableElement_column1;

	@FindBy(xpath="//th[@aria-controls='incomingCreditsExceptionItemsGrid_grid']")
	private static List<WebElement> tableElement_column2 ;

	@FindBy(id = "liIncDebits")
	private static WebElement incomingDebits;
	
	@FindBy(id = "incomingDebitsGrid_grid_info")
	private static WebElement filterPageDetails;
	
	@FindBy(id = "incomingDebitsGrid_grid_previous")
	private static WebElement paginationPrevious;
	
	//@FindBy(className = "paginate_button next")
	@FindBy(id = "incomingDebitsGrid_grid_next")
	//@FindBy(xpath = "//li[@class='paginate_button next']")
	private static WebElement paginationNext;
	
	@FindBy(className = "paginate_button")
	private static WebElement pageDetails;
		
	@FindBy(id = "dvMultSelect")
	private static WebElement multiSelectDownloadTab;
	
	@FindBy(id = "liUpload")
	private static WebElement upload;
	
	@FindBy(id = "liDownload")
	private static WebElement download;
	
	@FindBy(xpath = "//a[contains(@href,IncomingDebits)]")
	private static WebElement incomingDebitsHref;

	@FindBy(xpath = "//a[contains(@href,IncomingCredits)]")
	private static WebElement incomingCreditsHref;

	@FindBy(xpath = "//a[contains(@href,CreateRAQuery)]")
	private static WebElement createRAQuery;

	@FindBy(xpath = "//a[contains(@href,UploadDownload)]")
	private static WebElement uploadDownload;

	@FindBy(xpath = "//a[contains(@href,ActivityLog)]")
	private static WebElement activityLog1;
	
	@FindBy(id = "liActivityLog")
	private static WebElement activityLog;
	
	@FindBy(xpath = "//a[contains(@href,SystemSettings)]")
	private static WebElement systemSettings;

	@FindBy(xpath = "//a[contains(@href,Dashboard)]")
	private static WebElement Dashboard;

	@FindBy(id = "divDashboardItems")
	private static WebElement DashboardItems;
	
	@FindBy(xpath = "//h2[contains(@text(),'Forbidden')]")
	private static WebElement errorMsg;

	@FindBy(xpath="//div[@class='dataTables_scrollBody']/table/tbody/tr[2]/td[11]/select")
	private static WebElement debitsColumn;

	@FindBy(xpath="//div[@class='dataTables_scrollBody']/table/tbody/tr[2]/td[11]/img[@id='imgNotAllowableNoPayDecision']")
	private static WebElement payDecision;

	@FindBy(className = "FailReasonsSelect")
	private static WebElement payDecisionStatus;

	@FindBy(id = "imgNotAllowableNoPayDecision")
	private static WebElement btnNoPayIcon;
	
	@FindBy(xpath="//*[@id='incomingDebitsGrid_grid']/tbody/tr[1]/td[12]/select")
	private static WebElement selectFailreason;
		
	@FindBy(id = "SortCodeExceptionItems")
	private static WebElement txtSrtCodeExceptionsTab;

	@FindBy(id = "SortCodeToExceptionItems")
	private static WebElement txtSrtCodeToExceptionsTab;

	@FindBy(id = "AccountExceptionItems")
	private static WebElement txtAccountExceptionItems;

	@FindBy(id = "SerialExceptionItems")
	private static WebElement txtSerialExceptionItems;

	@FindBy(id = "AmountExceptionItems")
	private static WebElement txtAmountExceptionItems;

	@FindBy(id = "AmountToExceptionItems")
	private static WebElement txtAmountToExceptionItems;

	@FindBy(id = "btnClearCurrentFilters")
	private static WebElement btnClearCurrentFilters;

	@FindBy(id = "btnApplyFiltersExceptionItems")
	private static WebElement btnApplyFiltersExceptionItems;

	@FindBy(id = "SortCodeCollElseWhere")
	private static WebElement txtSrtCodeCollElseWhereTab;

	@FindBy(id = "SortCodeToCollElseWhere")
	private static WebElement txtSrtCodeToCollElseWhereTab;

	@FindBy(id = "AccountCollElseWhere")
	private static WebElement txtAccountCollElseWhere;

	@FindBy(id = "SerialCollElseWhere")
	private static WebElement txtSerialCollElseWhere;

	@FindBy(id = "AmountCollElseWhere")
	private static WebElement txtAmountCollElseWhere;	

	@FindBy(id = "AmountToCollElseWhere")
	private static WebElement txtAmountToCollElseWhere;

	@FindBy(id = "btnApplyFiltersCollElseWhere")
	private static WebElement btnApplyFiltersCollElsewhere;
	
	@FindBy(id = "AccountAllCollItems")
	private static WebElement txtAccountAllCollItems;

	@FindBy(id = "SortCodeAllCollItems")
	private static WebElement txtSortCodeAllCollItems;

	@FindBy(id = "SerialAllCollItems")
	private static WebElement txtSerialAllCollItems;

	@FindBy(id = "AmountAllCollItems")
	private static WebElement txtAmountAllCollItems;

	@FindBy(id = "SortCodeToAllCollItems")
	private static WebElement txtSortCodeToAllCollItems;

	@FindBy(id = "AmountToAllCollItems")
	private static WebElement txtAmountToAllCollItems;

	@FindBy(id = "btnClearFiltersShowAllItems")
	private static WebElement btnClearFilterIncomingDebits;
	
	@FindBy(id = "btnApplyFiltersAllCollItems")
	private static WebElement btnApplyFiltersCollItems;

	@FindBy(id = "lblShowStatusExceptionItems")
	private static WebElement lblShowStatusExceptionItems;

	@FindBy(id = "incomingCreditsCollElseWhereGrid_grid_info")
	private static WebElement lblShowStatusCollectedItems;

	@FindBy(id = "incomingCreditsAllItemsGrid_grid_info")
	private static WebElement lblShowStatusAllCollectedItems;

	@FindBy(xpath = "//a[contains(@href,OutgoingItems)]")
	private static WebElement lnkOutgoingItems;

	@FindBy(id = "liDashboard")
	private static WebElement lnkClassDashboard;

	@FindBy(id = "liOutItems")
	private static WebElement lnkOutgoing;

	@FindBy(xpath = "//a[contains(@href,Completed)]")
	private static WebElement lnkOutgoingCompleted;

	@FindBy(xpath = "//a[contains(@href,ExceptionItems)]")
	private static WebElement lnkOutgoingExeptionItems;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[1]/td[7]/i")
	private static WebElement imgImage;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[2]/td/div/div")
	private static WebElement imgFrontView;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[2]/td[2]/div/div")
	private static WebElement imgRearView;
	
	@FindBy(xpath="//*[@id='DuplicateImage']/button")
	private static WebElement btnView;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[3]/td/div/div")
	private static WebElement imgDuplicateOneFrontView;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[3]/td[2]/div/div")
	private static WebElement imgDuplicateOneRearView;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[4]/td/div/div")
	private static WebElement imgDuplicateTwoFrontView;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[4]/td[2]/div/div")
	private static WebElement imgDuplicateTwoRearView;
	
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
	
	@FindBy(xpath="//*[@class='imgInlineImgClass fa fa-up-arrow fa-2x css-for-imgCollapse AGYtooltip']")
	private static WebElement imgImageCollapse;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[1]/td[8]/i")
	private static WebElement imgKappa;
	
	@FindBy(xpath="//*[@id='incomingDebitsGrid_grid']/tbody/tr[3]")
	private static WebElement kappaData;
	
	@FindBy(xpath="//*[@id='incomingDebitsGrid_grid']/tbody/tr[4]")
	private static WebElement kappaImage;
	
	@FindBy(xpath="//*[@class='imgInlineKappaClass fa fa-up-arrow fa-2x css-for-imgCollapse AGYtooltip']")
	private static WebElement imgKappaDataCollapse;
	
	@FindBy(xpath="//*[@class='imgInlineImgClass fa fa-up-arrow fa-2x css-for-imgCollapse AGYtooltip']")
	private static WebElement imgKappaImageCollapse;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[1]/td[9]/i")
	private static WebElement imgAccountSwitch;
	
	@FindBy(xpath="//*[@id='incomingDebitsGrid_grid']/tbody/tr[5]")
	private static WebElement accountSwitchExpander;
	
	@FindBy(xpath="//*[@class='fa fa-up-arrow fa-2x imgInlineSwitchClass css-for-imgCollapse AGYtooltip']")
	private static WebElement imgAccountSwitchCollapse;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[1]/td[10]/i")
	private static WebElement imgOther;
	
	@FindBy(xpath="//*[@id='incomingDebitsGrid_grid']/tbody/tr[2]")
	private static WebElement otherExpander;
	
	@FindBy(xpath="//*[@class='fa fa-up-arrow imgInlineOthersClass css-for-imgCollapse']")
	private static WebElement imgOtherCollapse;
	
	@FindBy(id = "liActivityLog")
	private static WebElement liActivityLog;

	@FindBy(id = "liCreateRAQuery")
	private static WebElement liCreateRAQuery;

	@FindBy(id = "liUploadDownload")
	private static WebElement liUploadDownload;

	@FindBy(id = "liSystemSettings")
	private static WebElement liSystemSettings;

	@FindBy(id = "Amount")
	private static WebElement txtDebitsAmount;
	
	@FindBy(id = "Serial")
	private static WebElement txtSerial;
	
	@FindBy(id = "SortCode")
	private static WebElement txtSort;
	
	@FindBy(id = "SortCodeTo")
	private static WebElement txtSortTo;

	@FindBy(id = "AmountTo")
	private static WebElement txtDebitsAmountTo;

	@FindBy(id = "btnApplyFilters")
	private static WebElement btnApplyFilters;
	
	@FindBy(id = "btnRefresh")
	private static WebElement btnRefresh;
	
	@FindBy(id = "btnSearch")
	private static WebElement btnSearch;
	
	@FindBy(id = "ActionList")
	private static WebElement lstActionList;
	
	@FindBy(id = "lblShowStatus")
	private static WebElement lblShowCurrentFilterStatus;
	
	@FindBy(xpath="//*[@id='filterSectionBody']/div/div/div/div/div[2]/select")
	private static WebElement lblShowCurrentFilterStatus1;

	@FindBy(id = "incomingDebitsGrid_grid")
	private static WebElement tblIncomingDebits;

	@FindBy(xpath="//*[@class='clsAccount']")
	private static WebElement tblAccount;
	
	@FindBy(xpath="(//*[@class='clsAccount'])[2]")
	private static WebElement tblAccount2;
	
	@FindBy(xpath="//*[@class='clsSerial']")
	private static WebElement tblSerial;
	
	@FindBy(xpath="//*[@class='clsSortCode form-grd-col-sort']")
	private static WebElement tblSortcode;

	@FindBy(xpath="//*[@class='Pay Decision']")
	private static WebElement tblStatus;
	
	@FindBy(xpath="//*[@class='clsAmount']")
	private static WebElement tblAmount;
	
	@FindBy(xpath="//*[@class='clsDefault']")
	private static WebElement tblDefault;

	@FindBy(xpath="//*[@class='Pay']")
	private static WebElement tblDecision;

	@FindBy(xpath="//*[@class='Pay Decision']")
	private static WebElement tblDecisioner;

	@FindBy(id = "Account")
	private static WebElement txtDebitsaccount;

	@FindBy(id = "Status")
	private static WebElement ddlDebitsStatus;

	@FindBy(xpath = "//input[@class='clsChkMain clsChk']")
	private static WebElement chkDebitsSelectAll;
	
	@FindBy(xpath = "//input[@class='clsChkMain']")
	private static WebElement chkCreditsSelectAll;

	@FindBy(xpath = "//input[@class='clsChkParent chkDecision chkPayDecision']")
	private static WebElement chkPayDecisionItem;
	
	@FindBy(xpath = "//input[@class='clsChkParent chkDecision chkKappaDecision']")
	private static WebElement chkKappaDecisionItem;

	/*Identifying the control id for clicking on Pay incomingDebitsScreen*/
	@FindBy(id = "btnPaySlctd")
	private static WebElement btnDecisionPay;

	@FindBy(id = "imgAllowablePayDecision")
	//@FindBy(xpath = "//i[@id='imgAllowablePayDecision')[1]")
	private static WebElement btnPayDecision;
	
	@FindBy(id = "imgAwaitingApprovalApprove")
	private static WebElement btnApproval;
	
	@FindBy(id = "imgAwaitingApprovalNotApprove")
	private static WebElement btnDenyApproval;

	@FindBy(id = "btnCreatePdf")
	private static WebElement btnCreatePdf;

	/*Identifying the control id for clicking on No Pay incomingDebitsScreen*/
	@FindBy(id = "btnNoPaySlctd")
	private static WebElement btnDecisionNoPay;

	/*Identifying the control id for Fail Reason field incomingDebitsScreen*/
	@FindBy(id = "ddFailReason")
	private static WebElement ddlFailReason;	

	//@FindBy(xpath = "//div[@class='iframe-container']/iframe/html/body/nav/div/ul/li[10]/i")
	@FindBy(id = "iHelp")
	private static WebElement helpIcon;

	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[4]")
	private static WebElement AccountSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[2]")
	private static WebElement StatusSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[5]")
	private static WebElement SerialSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[3]")
	private static WebElement SortCodeSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[6]")
	private static WebElement AmountSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[11]")
	private static WebElement DefaultSort;
	
	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[12]")
	private static WebElement DecisionSort;
	
	@FindBy(id = "lblShowStatusAllCollItems")
	private static WebElement lblShowCurrentFilterAllColl;
	
	@FindBy(id = "lblShowStatusCollElseWhere")
	private static WebElement lblShowCurrentFilterCollElsewhere;
	
	@FindBy(id = "lblShowStatusExceptionItems")
	private static WebElement lblShowCurrentFilterExcecption;
	
	@FindBy(id = "overridelink")
	private static WebElement securityOverrideLink;
	
	@FindBy(xpath = "//*[@class='header']/div/h4")
	private static WebElement sessionExpired;
			
	protected static ExtentTest EXTENTLOG;
	protected static boolean flag=false;
	protected static boolean flagBanner = false;

	private static String assertFailLogForIncomingTabAvailable = "Incoming Credits Screen tab is not available";
	private static String assertFailLogForAllCollectedItemsTabAvailable = "AllCollectedItems tab is not available";
	private static String assertFailLogForCollectedElseWhereTabAvailable = "CollectedElseWhereTab tab is not available";
	private static String assertFailLogForExceptionItemsTabAvailable =  "Exceptions tab is not available";
	private static String assertFailLogForIncomingTabNotAvailable = "Incoming Credits Screen tab is available";
	private static String assertLandingPage="Bank Landing Page is opened";
	private static String errorMessage="403 - Forbidden: Access is denied.";
	private static String errMsg="Sorry, an error occurred while processing your request. Please check details below";
	private static String assertDashboard="Dashboard tab should not be present";
	private static String assertOutgoingItems="Outgoing Items tab should not be present";
	private static String assertIncomingItems="Incoming Items tab should not be present";
	private static String assertSearchTab="Search Archive tab should not be present";
	private static String assertRandAQuery="R&A tab should not be present";
	private static String assertUploadDownloadTab="Upload and Download tab should not be present";
	private static String assertActivityLogTab="Activity Log tab should not be present";
	private static String assertSystemSettingsTab="System Settings tab should not be present";
	private static String assertMultiSelectTab="Multiselect expander is not displayed";

	public BankLandingPage(WebDriver driver) {
		super(driver);		
	}

	public BankLandingPage(WebDriver driver, String url) {
		super(driver, url);		
	}

	public BankLandingPage switchToAgencyFrame()
	{		
		frameSwitch(agencyFrame);
		return new BankLandingPage(driver);
	}

	public static boolean returnResultFlag()
	{
	
		return BankLandingPage.flag;
	}


	public BankLandingPage clickOnIncomingCredits()
	{		
		pause(3000);	
		clickElement(incomingCredits);	
		getDefaultImplicitTimeout();
		return new BankLandingPage(driver);
	}
	public BankLandingPage clickOnActivityLog()
	{		
		pause(3000);	
		clickElement(activityLog);	
		getDefaultImplicitTimeout();
		return new BankLandingPage(driver);
	}

	public BankLandingPage clickOnExceptionsTab()
	{		
		clickElement(exceptions3);	
		getDefaultImplicitTimeout();
		return new BankLandingPage(driver);
	}

	public BankLandingPage clickOnCollectedElseWhereTab()
	{	
		pause(1000);
		clickElement(collectedElseWhere);	
		getDefaultImplicitTimeout();
		return new BankLandingPage(driver);
	}

	public BankLandingPage clickOnCollectedItemsTab()
	{	pause(1000);
		clickElement(allCollectedItems);	
		getDefaultImplicitTimeout();
		return new BankLandingPage(driver);
	}
	
	public BankLandingPage clickOnSearchArchive()
	{		
		pause(2000);
		clickElement(searchArchive);	
		getDefaultImplicitTimeout();
		return new BankLandingPage(driver);
	}
	
	public BankLandingPage clickOnSettlementLimits()
	{		
		pause(2000);
		clickElement(settlementLimits);	
		getDefaultImplicitTimeout();
		return new BankLandingPage(driver);
	}
	
	public BankLandingPage clickOnAgencySummary()
	{		
		pause(2000);
		clickElement(agencySummary);	
		getDefaultImplicitTimeout();
		return new BankLandingPage(driver);
	}
	
	public BankLandingPage assertTablePresent( String TabName)
	{		
		List <WebElement> rows_table = driver.findElements(By.xpath("//div[@class='dataTables_scrollBody']/table/tbody/tr"));
		int rows_count = rows_table.size();
		String  row1= driver.findElement(By.xpath("//div[@class='dataTables_scrollBody']/table/tbody/tr[1]/td[1]")).getAttribute("class");
		
		boolean check = (row1.contains("dataTables_empty"));
		
		if (TabName == "CollectedItems")
		{validationStepInformation("User should be able to view the transactions in CollectedItems tab");
			if((rows_count > 0) && (check==false))
		{
			flag=true;
			publishResults(flag, "Items on the Collected tems subtab are viewable", "Items on the Collected items subtab should be viewable", "Verify Items on the Collected items subtab are viewable");
		}else
		{
			flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Items on the Collected tems subtab are not viewable / data not available", "Items on the Collected items subtab should be viewable", "Verify Items on the Collected items subtab are viewable");
		}
		assertTrue(((rows_count > 0) && (check==false)), "Table is not visible");
		}
		else if (TabName == "CollectedElsewhere")
		{validationStepInformation("User should be able to view the transactions in CollectedElsewhere tab");
			if((rows_count > 0) && (check==false))
			{
				flag=true;
				publishResults(flag, "Items on the Collected elsewhere subtab are viewable", "Items on the Collected elsewhere subtab should be viewable", "Verify Items on the Collected elsewhere subtab are viewable");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "Items on the Collected tems subtab are not viewable / data not available", "Items on the Collected elsewhere subtab should be viewable", "Verify Items on the Collected elsewhere subtab are viewable");
			}
			assertTrue(((rows_count > 0) && (check==false)), "Table is not visible");
		}

		else if (TabName == "Exceptions")
		{validationStepInformation("User should be able to view the transactions in Exceptions tab");
			if((rows_count > 0) && (check==false))
			{
				flag=true;
				publishResults(flag, "Items on the Exception subtab are viewable", "Items on the Exception subtab should be viewable", "Verify Items on the Exception subtab are viewable");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "Items on the Collected tems subtab are not viewable / data not available", "Items on the Exception subtab should be viewable", "Verify Items on the Exception subtab are viewable");
			}
			assertTrue(((rows_count > 0) && (check==false)), "Table is not visible");
		}
		return this;
	}

	/*
	 * Common method for verifying the below links for all the roles to check the following tabs :
	 *     Incoming Credits, All Collected Items1, Collected Elsewhere2 and Exceptions3
	 */
	public BankLandingPage assertRolesTabAvailable()
	{
		pause(3000);
		assertTrue(isElementDisplayed(incomingCredits), assertFailLogForIncomingTabAvailable);
		//assertTrue(isElementDisplayed(allCollectedItems), assertFailLogForAllCollectedItemsTabAvailable);
		assertTrue(isElementDisplayed(collectedElseWhere), assertFailLogForCollectedElseWhereTabAvailable);
		assertTrue(isElementDisplayed(exceptions3), assertFailLogForExceptionItemsTabAvailable);
		return this;
	}

	/*
	 * Common method for verifying the below links Incoming Credits is not available for all the roles 
	 */
	public BankLandingPage assertRolesNotExist()
	{
	assertTrue(isElementDisplayed(incomingCreditsHref), assertFailLogForIncomingTabNotAvailable);
		return this;		
	}
	
	public BankLandingPage assertIncomingCreditTabNotAvailable(){
		validationStepInformation("verify Incoming Credit tab is not available");
		flag =false;
	
		try{
			driver.findElement(By.id("liIncCredits"));
		} catch (NoSuchElementException e){
			flag=true;
			publishResults(true, "Incoming Credit tab is not visible","Incoming Credit tab should not be visible", "Verify Incoming Credit tab is not visible");		
		}
		if (flag ==false){
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Incoming Credit tab is visible","Incoming Credit tab should not be visible", "Verify Incoming Credit tab is not visible");
		}	
		return this;		
	}
	
	public BankLandingPage assertDashboardTabNotAvailable(){
		validationStepInformation("verify Dashboard tab is not available");
		flag =false;	
		try{
			driver.findElement(By.id("liDashboard"));
		} catch (NoSuchElementException e){
			flag=true;
			publishResults(true, "Dashboard tab is not visible","Dashboard tab should not be visible", "Verify Dashboard tab is not visible");		
		}
		if (flag ==false){
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Dashboard tab is visible","Dashboard tab should not be visible", "Verify Dashboard tab is not visible");
		}	
		return this;		
	}

	public BankLandingPage assertBankLandingPageVaidations()
	{
		assertTrue(isElementDisplayed(Dashboard), assertLandingPage);
		return this;
	}

	public BankLandingPage assertAgencyIdErrorMessage()
	{
		boolean expected=getPageSource().contains(errorMessage);
		assertTrue(expected, "SSO Error message is displayed");
		return this;
	}

	public BankLandingPage assertAgencyErrorMessage()
	{
		boolean expected=getPageSource().contains(errMsg);
		assertTrue(expected, "SSO Error message is displayed");
		return this;
	}

	public BankLandingPage validateTableColumnName()
	{
		boolean flagColumnElement=false;	
		String source=driver.getPageSource();		
		if(source.contains(ICSPropertiesConfig.getCreditSortCode()) && source.contains(ICSPropertiesConfig.getCreditAccount()) && source.contains(ICSPropertiesConfig.getCreditAmount())
				&&source.contains(ICSPropertiesConfig.getItemSerial())&& source.contains(ICSPropertiesConfig.getItemSortCode())
				&&source.contains(ICSPropertiesConfig.getItemAccount())&&source.contains(ICSPropertiesConfig.getItemAmount())
				&&source.contains(ICSPropertiesConfig.getOther())&&source.contains(ICSPropertiesConfig.getImageColumn())
				&&source.contains(ICSPropertiesConfig.getResponseFromPayingBank())&& source.contains(ICSPropertiesConfig.getActionColumn()))		
			flagColumnElement=true;
		if(flagColumnElement)
			assertTrue(flagColumnElement=true, "All columns are not present");		
		return this;
	}

	public BankLandingPage clickOnIncomingDebits() 
	{	
		pause(3000);
		clickElement(incomingDebits);	
		getDefaultImplicitTimeout();
		pause(3000);
		return new BankLandingPage(driver);
	}

	public BankLandingPage selectPayDecisionStatus() throws InterruptedException 
	{
		if(debitsColumn!=null)
			System.out.println(debitsColumn.getText());
		selectDropdownValue(driver, debitsColumn, ICSPropertiesConfig.getPayDecisionStatus());
		Thread.sleep(50000);
		clickElement(btnNoPayIcon);
		//		WebElement element=driver.findElement(By.xpath("//*[@id='imgNotAllowableNoPayDecision']"));
		//		JavascriptExecutor executor=(JavascriptExecutor) driver;
		//		executor.executeScript("arguments[0].click()", element);
		//		System.out.println(element);
		return new BankLandingPage(driver);
	}

	public BankLandingPage validateTableColumnsCollectedElsewhere()
	{
		boolean flagColumnElement=false;	
		String source=driver.getPageSource();		
		if(source.contains(ICSPropertiesConfig.getTxSetColumn()) && source.contains(ICSPropertiesConfig.gettypeChangeColumn()) && source.contains(ICSPropertiesConfig.getserialChangeColumn())
				&&source.contains(ICSPropertiesConfig.getaccountChangeColumn())&& source.contains(ICSPropertiesConfig.getamountChangeColumn())
				&&source.contains(ICSPropertiesConfig.getkappaChangeColumn())&&source.contains(ICSPropertiesConfig.getotherChangeColumn())
				&&source.contains(ICSPropertiesConfig.getimageChangeColumn())&&source.contains(ICSPropertiesConfig.getresponseChangeColumn())
				&&source.contains(ICSPropertiesConfig.getactionChangeColumn()))		
			flagColumnElement=true;
		if(flagColumnElement)
			assertTrue(flagColumnElement=true, "All columns are not present");		
		return this;
	}
		
	public static boolean assertClearFieldsinDebititemsTab()
	{
		clickElementEnter(btnClearFilterIncomingDebits);
		boolean flag=false;
		if(txtDebitsaccount.getText().isEmpty()
				&& txtSerial.getText().isEmpty()&& txtDebitsAmount.getText().isEmpty() && txtDebitsAmountTo.getText().isEmpty()
				&& txtSort.getText().isEmpty() && txtSortTo.getText().isEmpty()){
			flag=true;
		publishResults(flag, "All items are cleared when clear filter is applied", "All items should be cleared when clear filter is applied", "Click on clear filter and verify if table is cleared");
		}else{flag=false;
		finalTestScriptResultFailFlag = true;
		publishResults(flag, "All items are not cleared when clear filter is applied", "All items should be cleared when clear filter is applied", "Click on clear filter and verify if table is cleared");
		}if(flag)
			assertTrue(flag, "All fields are cleared");		
		return flag;
	}
	public BankLandingPage fillSerialFieldsinExceptionsTab()
	{
		fillTextBox(txtSerialExceptionItems, ICSPropertiesConfig.getSerialExceptionItemsTab());
		clickElement(btnApplyFiltersExceptionItems);
		return this;
	}
	public BankLandingPage assertApplyfiltersinExceptionsTab()
	{
		boolean flag=false;
		if(lblShowStatusExceptionItems.getText().contains(ICSPropertiesConfig.getSerialExceptionItemsTab()))
		{
			flag=true;
		}
		assertTrue(flag, "filters are applied on the exceptions tab");	
		return this;
	}
	public BankLandingPage assertOutGoingTabNotAvailable()
	{
		assertTrue(isElementDisplayed(lnkOutgoingItems), assertOutgoingItems);
		return this;
	}
	public BankLandingPage assertIncomingTabNotAvailable()
	{
		assertTrue(isElementDisplayed(incomingDebitsHref), assertIncomingItems);
		return this;
	}
	
	public BankLandingPage assertIncomingDebitTabNotAvailable()
	{
		validationStepInformation("verify Incoming Debit tab is not available");
		flag =false;
		
		try{
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e){
		}
		
		//flag = isElementDisplayed(incomingDebits);
		try{
			driver.findElement(By.id("liIncDebits"));
		} catch (NoSuchElementException e){
			flag= true;
			publishResults(flag, "Incoming Debit tab is not visible","Incoming Debit tab should not be visible", "Verify Debit Credit tab is not visible");		
		}
		if (flag ==false){
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Incoming Debit is visible","Incoming Debit tab should not be visible", "Verify Incoming Debit tab is not visible");
		//assertFalse(isElementDisplayed(incomingDebits), assertFailLogForIncomingTabNotAvailable);	
		}return this;
	}
	
	public BankLandingPage assertSearchTabNotAvailable()
	{
		validationStepInformation("verify Search tab is not available");
		flag =false;
	
		try{
			driver.findElement(By.id("liSearch"));
		} catch (NoSuchElementException e){
			flag=true;
			publishResults(flag, "Search tab is not visible","Search tab should not be visible", "Verify Search tab is not visible");		
		}
		if (flag ==false){
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Search tab is visible","Search tab should not be visible", "Verify Search tab is not visible");
		}
		//assertFalse(isElementDisplayed(incomingDebits), assertFailLogForIncomingTabNotAvailable);
		return this;
	}
	
	public BankLandingPage assertActivityLogTabNotAvailable()
	{
		validationStepInformation("verify activity log tab is not available");
		flag =false;
	
		try{
			driver.findElement(By.id("liActivityLog"));
		} catch (NoSuchElementException e){
			flag=true;
			publishResults(flag, "activity log tab is not visible","activity log tab should not be visible", "Verify activity log tab is not visible");		
		}
		if (flag ==false){
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "activity log tab is visible","activity log tab should not be visible", "Verify activity log tab is not visible");
		}
		return this;
	}
	
	/*public BankLandingPage assertSearchTabNotAvailable()
	{
		assertTrue(isElementDisplayed(searchArchive), assertSearchTab);
		return this;
	}*/
	public BankLandingPage assertRAQueryNotAvailable()
	{
		assertTrue(isElementDisplayed(createRAQuery), assertRandAQuery);
		return this;
	}
	public BankLandingPage assertUploadDownloadNotAvailable()
	{
		assertTrue(isElementDisplayed(uploadDownload), assertUploadDownloadTab);
		return this;
	}
	public BankLandingPage assertActivityLogNotAvailable()
	{
		assertTrue(isElementDisplayed(activityLog), assertActivityLogTab);
		return this;
	}
	public BankLandingPage assertSystemSettingsNotAvailable()
	{
		assertTrue(isElementDisplayed(systemSettings), assertSystemSettingsTab);
		return this;
	}
	public BankLandingPage assertAllTabsAvailable()
	{
		boolean flag=false;

		clickElement(lnkClassDashboard);
		if(lnkClassDashboard.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Dashboard Page Opened");
		}
		clickElement(lnkOutgoingItems);
		if(lnkOutgoing.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(isElementDisplayed(lnkOutgoingCompleted), "Outgoing Completed Items tab opened");
			assertTrue(isElementDisplayed(lnkOutgoingExeptionItems), "Outgoing Exception Items tab opened");
			assertTrue(flag, "Outgoing Items Page Opened");
		}
		clickElement(incomingDebits);
		if(incomingDebits.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Incoming Debits Page Opened");
		}
		clickElement(incomingCredits);
		if(incomingCredits.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(isElementDisplayed(allCollectedItems), "All Collected tab opened");
			assertTrue(isElementDisplayed(collectedElseWhere), "Collected Elsewhere tab opened");
			assertTrue(isElementDisplayed(exceptions3), "Exceptions tab opened");
			assertTrue(flag, "Incoming Credits Page Opened");
		}
		clickElement(searchArchive);
		if(searchArchive.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Search Archive Page Opened");
		}
		clickElement(activityLog);
		if(liActivityLog.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Activity Log Page Opened");
		}
		clickElement(createRAQuery); 
		if(liCreateRAQuery.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "R&A Query Page Opened");
		}
		clickElement(uploadDownload);
		if(liUploadDownload.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Upload/Download Page Opened");
		}
		clickElement(systemSettings);
		if(liSystemSettings.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "System Settings Page Opened");
		}
		return this;
	}

	public BankLandingPage assertAllTabsAvailableforUserBOF()
	{
		boolean flag=false;

		clickElement(lnkOutgoingItems);
		if(lnkOutgoing.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(isElementDisplayed(lnkOutgoingCompleted), "Outgoing Completed Items tab opened");
			assertTrue(isElementDisplayed(lnkOutgoingExeptionItems), "Outgoing Exception Items tab opened");
			assertTrue(flag, "Outgoing Items Page Opened");
		}
		clickElement(incomingDebits);
		if(incomingDebits.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Incoming Debits Page Opened");
		}

		clickElement(searchArchive);
		if(searchArchive.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Search Archive Page Opened");
		}
		clickElement(activityLog);
		if(liActivityLog.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Activity Log Page Opened");
		}
		clickElement(createRAQuery); 
		if(liCreateRAQuery.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "R&A Query Page Opened");
		}
		clickElement(uploadDownload);
		if(liUploadDownload.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Upload/Download Page Opened");
		}
		clickElement(systemSettings);
		if(liSystemSettings.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "System Settings Page Opened");
		}
		return this;
	}
	public BankLandingPage assertAllTabsAvailableforUserCOM()
	{
		boolean flag=false;

		clickElement(lnkClassDashboard);
		if(lnkClassDashboard.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Dashboard Page Opened");
		}
		clickElement(lnkOutgoingItems);
		if(lnkOutgoing.getAttribute("class").contains("bgColor"))
		{selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusAwaitingApproval"));
		flag=true;
		assertTrue(isElementDisplayed(lnkOutgoingCompleted), "Outgoing Completed Items tab opened");
		assertTrue(isElementDisplayed(lnkOutgoingExeptionItems), "Outgoing Exception Items tab opened");
		assertTrue(flag, "Outgoing Items Page Opened");
		}
		clickElement(incomingDebits);
		if(incomingDebits.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Incoming Debits Page Opened");
		}
		clickElement(incomingCredits);
		if(incomingCredits.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(isElementDisplayed(allCollectedItems), "All Collected tab opened");
			assertTrue(isElementDisplayed(collectedElseWhere), "Collected Elsewhere tab opened");
			assertTrue(isElementDisplayed(exceptions3), "Exceptions tab opened");
			assertTrue(flag, "Incoming Credits Page Opened");
		}
		clickElement(searchArchive);
		if(searchArchive.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Search Archive Page Opened");
		}

		return this;
	}
	public BankLandingPage assertAllTabsAvailableforUserSYS()
	{
		boolean flag=false;

		clickElement(uploadDownload);
		if(liUploadDownload.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Upload/Download Page Opened");
		}
		clickElement(systemSettings);
		if(liSystemSettings.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "System Settings Page Opened");
		}

		return this;
	}
	public BankLandingPage assertAllTabsAvailableforUserSAV()
	{
		boolean flag=false;

		clickElement(lnkOutgoingItems);
		if(lnkOutgoing.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(isElementDisplayed(lnkOutgoingCompleted), "Outgoing Completed Items tab opened");
			assertTrue(isElementDisplayed(lnkOutgoingExeptionItems), "Outgoing Exception Items tab opened");
			assertTrue(flag, "Outgoing Items Page Opened");
		}
		clickElement(incomingDebits);
		if(incomingDebits.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Incoming Debits Page Opened");
		}
		clickElement(searchArchive);
		if(searchArchive.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Search Archive Page Opened");
		}
		clickElement(activityLog);
		if(liActivityLog.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Activity Log Page Opened");
		}
		clickElement(createRAQuery); 
		if(liCreateRAQuery.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "R&A Query Page Opened");
		}
		clickElement(uploadDownload);
		if(liUploadDownload.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "Upload/Download Page Opened");
		}
		clickElement(systemSettings);
		if(liSystemSettings.getAttribute("class").contains("bgColor"))
		{
			flag=true;
			assertTrue(flag, "System Settings Page Opened");
		}
		return this;
	}
	public BankLandingPage fillSerialFieldsinCollectedElseWhereTab()
	{
		fillTextBox(txtSrtCodeCollElseWhereTab, ICSPropertiesConfig.getSrtCodeCollElseWhereTab());
		clickElement(btnClearCurrentFilters);
		return this;
	}
	public BankLandingPage assertApplyfiltersinCollectedElseWhereTab()
	{
		boolean flag=false;
		if(lblShowStatusCollectedItems.getText().contains(getAgencyResourceFile().getString("collectedElseWhereErrMsg")))
		{
			flag=true;
		}
		assertTrue(flag, "Apply filters are applied on the collected elsewhere tab");	
		return this;
	}
	public BankLandingPage fillSerialFieldsinAllCollectedTab()
	{
		fillTextBox(txtSortCodeAllCollItems, ICSPropertiesConfig.getSortCodeAllCollItemsTab());
		clickElement(btnClearCurrentFilters);
		return this;
	}
	public BankLandingPage assertApplyfiltersinAllCollectedTab()
	{
		boolean flag=false;
		if(lblShowStatusAllCollectedItems.getText().contains(getAgencyResourceFile().getString("collectedElseWhereErrMsg")))
		{
			flag=true;
		}
		assertTrue(flag, "Apply filters are applied on the collected elsewhere tab");	
		return this;
	}
	public BankLandingPage fillAmountFieldsinDebitsTab()
	{
		fillTextBox(txtDebitsAmount,getAgencyResourceFile().getString("amountFrom"));
		fillTextBox(txtDebitsAmountTo,getAgencyResourceFile().getString("amountTo"));
		clickElementEnter(btnApplyFilters);
		return this;
	}
	public BankLandingPage fillSerialFieldinDebitsTab()
	{
		fillTextBox(txtSerial,getAgencyResourceFile().getString("serial"));
		clickElementEnter(btnApplyFilters);
		return this;
	}
	public BankLandingPage fillSortCodeFieldinDebitsTab()
	{
		fillTextBox(txtSort,getAgencyResourceFile().getString("sortCode"));
		fillTextBox(txtSortTo,getAgencyResourceFile().getString("sortCodeTo"));
		clickElementEnter(btnApplyFilters);
		return this;
	}
	public BankLandingPage assertApplyfiltersinAmountColDebitsTab()
	{
		boolean flag=false;
		if(lblShowCurrentFilterStatus.getText().contains(getAgencyResourceFile().getString("debitsStatusMsg"))
				&& getTableRowCount("incomingDebitsGrid_grid")>0)
		{
			flag=true;
		}
		assertTrue(flag, "Apply filters are applied on the amount debits tab");	
		return this;
	}
	public BankLandingPage fillAccountFieldsinDebitsTab()
	{
		selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusAllItems"));
		fillTextBox(txtDebitsaccount,getAgencyResourceFile().getString("account"));
		clickElementEnter(btnApplyFilters);
		return this;
	}
	
	public static BankLandingPage fillNewAccountDetailsAndVerify(String accNum)
	{
		fillTextBox(txtDebitsaccount,accNum);
		fillTextBox(txtSerial,"");
		clickElementEnter(btnRefresh);
		pause(1000);
		String table = tblIncomingDebits.getText();
		if(lblShowCurrentFilterStatus.getText().contains(accNum) &&  table.contains(accNum))
		{
			flag=true;
			publishResults(flag, "New account displayed after refresh", "New account should be updated after refresh", "user should be able to view refreshed value");
			
		}else
		{
			flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "New account details not displayed after refresh", "New account details should be updated after refresh", "user should be able to view refreshed value");
			
		}
		return null;
	}
	
	public BankLandingPage assertApplyfiltersinAccountColDebitsTab()
	{
		boolean flag=false;
		if(lblShowCurrentFilterStatus.getText().contains(getAgencyResourceFile().getString("accountdebitsStatusMsg"))
				&& getTableRowCount("incomingDebitsGrid_grid")>0)
		{
			flag=true;
		}
		assertTrue(flag, "Apply filters are applied on the account debits tab");	
		return this;
	}
	public BankLandingPage selectStatusFieldsinDebitsTab() throws InterruptedException 
	{
		selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusAwaitingApproval"));
		clickElement(btnApplyFilters);
		return this;
	}
	public BankLandingPage assertApplyfiltersinStatusColDebitsTab()
	{
		boolean flag=false;
		if(lblShowCurrentFilterStatus.getText().contains(getAgencyResourceFile().getString("statusdebitsStatusMsg"))
				&& getTableRowCount("incomingDebitsGrid_grid")>0)
		{
			flag=true;
		}
		assertTrue(flag, "Apply filters are applied on the status debits tab");	
		return this;
	}
	public BankLandingPage verifyClearFilterDisabled()
	{
		boolean flag=false;
		if(btnClearCurrentFilters.getAttribute("disabled").equalsIgnoreCase("true"))
		{	
			flag=true;
		}
		assertTrue(flag, "Clear Filters button is disabled");
		return this;
	}
	/*************************************************************************************
	 * Method Name: assertselectStatusFieldsinDebitsTab
	 * Purpose:Method for validating the status field in incoming debits tab
	 * Author: Deepa Ramu
	 * Created Date:  15-July-2017
	/*************************************************************************************/
	public boolean assertselectStatusFieldsinDebitsTab() throws InterruptedException 
	{
		validationStepInformation("Check Select Status Fields In Debits Tab");
		selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusAwaitingApproval"));
		clickElement(btnApplyFilters);
		pause(2000);
		if(lblShowCurrentFilterStatus.getText().contains(getAgencyResourceFile().getString("statusAwaitingApproval")))
		{
			String resultsTable=tblIncomingDebits.getText();
			if ((resultsTable.contains(getAgencyResourceFile().getString("statusAwaitingApproval"))) && !(resultsTable.contains(getAgencyResourceFile().getString("statusPayDecision"))) && !(resultsTable.contains(getAgencyResourceFile().getString("statusCompleted")))&& !(resultsTable.contains(getAgencyResourceFile().getString("statusKappa"))))
			{
				flag=true;	
				publishResults(flag, "Awaiting approval filter applied as expected", "Awaiting approval filter should be applied", "user should be able to view items in awaiting approval");
		
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "Awaiting approval filter not applied as expected", "Awaiting approval filter should be applied", "user should be able to view items in awaiting approval");
				
			}
			assertTrue(flag, "Apply filters are applied on the status debits tab for Awaiting Approval");
		}
		
		selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusKappa"));
		clickElement(btnApplyFilters);
		pause(2000);
		if(lblShowCurrentFilterStatus.getText().contains(getAgencyResourceFile().getString("statusKappa")))
		{
			String resultsTable=tblIncomingDebits.getText();
			if ((resultsTable.contains(getAgencyResourceFile().getString("statusKappa"))) && !(resultsTable.contains(getAgencyResourceFile().getString("statusPayDecision"))) && !(resultsTable.contains(getAgencyResourceFile().getString("statusCompleted")))&& !(resultsTable.contains(getAgencyResourceFile().getString("statusAwaitingApproval"))))
			{
				flag=true;
				publishResults(flag, "Kappa status filter applied as expected", "Kappa status filter should be applied", "user should be able to view items in kappa decision");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "Kappa status filter not applied as expected", "Kappa status filter should be applied", "user should be able to view items in kappa decision");
			}
			assertTrue(flag, "Apply filters are applied on the status debits tab for statusKappa");
		}

		selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusAllItems"));
		clickElement(btnApplyFilters);
		pause(2000);
		if(lblShowCurrentFilterStatus.getText().contains(getAgencyResourceFile().getString("statusAllItems")))
		{
			String resultsTable=tblIncomingDebits.getText();
			if ((resultsTable.contains(getAgencyResourceFile().getString("statusKappa"))) || (resultsTable.contains(getAgencyResourceFile().getString("statusPayDecision"))) || (resultsTable.contains(getAgencyResourceFile().getString("statusCompleted")))|| (resultsTable.contains(getAgencyResourceFile().getString("statusAwaitingApproval"))))
			{
				flag=true;
				publishResults(flag, "All Items filter applied as expected", "All items filter should be applied", "user should be able to view items in all status");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "All Items filter not applied as expected", "All items filter should be applied", "user should be able to view items in all status");
			}
			assertTrue(flag, "Apply filters are applied on the status debits tab for statusAllItems");
		}
		selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusPayDecision"));
		clickElement(btnApplyFilters);
		pause(2000);
		if(lblShowCurrentFilterStatus.getText().contains(getAgencyResourceFile().getString("statusPayDecision")))
		{
			String resultsTable=tblIncomingDebits.getText();
			if ((resultsTable.contains(getAgencyResourceFile().getString("statusPayDecision"))) && !(resultsTable.contains(getAgencyResourceFile().getString("statusKappa"))) && !(resultsTable.contains(getAgencyResourceFile().getString("statusCompleted")))&& !(resultsTable.contains(getAgencyResourceFile().getString("statusAwaitingApproval"))))
			{
				flag=true;
				publishResults(flag, "Pay Decision filter applied as expected", "Pay decicsion filter should be applied", "user should be able to view items in pay decision status");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "Pay Decision filter not applied as expected", "Pay decicsion filter should be applied", "user should be able to view items in pay decision status");
			}
			assertTrue(flag, "Apply filters are applied on the status debits tab for statusPayDecision");
		}
		selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusCompleted"));
		clickElement(btnApplyFilters);
		pause(2000);
		if(lblShowCurrentFilterStatus.getText().contains(getAgencyResourceFile().getString("statusCompleted")))
		{
			String resultsTable=tblIncomingDebits.getText();
			if ((resultsTable.contains(getAgencyResourceFile().getString("statusCompleted"))) && !(resultsTable.contains(getAgencyResourceFile().getString("statusKappa"))) && !(resultsTable.contains(getAgencyResourceFile().getString("statusPayDecision")))&& !(resultsTable.contains(getAgencyResourceFile().getString("statusAwaitingApproval"))))
			{
			flag=true;
			publishResults(flag, "Completed filter applied as expected", "Completed filter should be applied", "user should be able to view items in Completed status");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "Completed filter not applied as expected", "Completed filter should be applied", "user should be able to view items in Completed status");
			}
			assertTrue(flag, "Apply filters are applied on the status debits tab for statusCompleted");
		}
		return flag;
	}
	
	public BankLandingPage assertOnlyCompleteStatusInDebitsTab() throws InterruptedException 
	{
		assertTrue(lblShowCurrentFilterStatus.getText().contains(getAgencyResourceFile().getString("statusCompleted")), "user is able to view status other that completed status ");
		assertTrue(ddlDebitsStatus.getText().contains(getAgencyResourceFile().getString("statusCompleted")), "user is able to view status other that completed status ");
		String resultsTable=tblIncomingDebits.getText();
			if ((resultsTable.contains(getAgencyResourceFile().getString("statusCompleted"))) && !(resultsTable.contains(getAgencyResourceFile().getString("statusKappa"))) && !(resultsTable.contains(getAgencyResourceFile().getString("statusPayDecision")))&& !(resultsTable.contains(getAgencyResourceFile().getString("statusAwaitingApproval"))))
			{
			flag=true;
			publishResults(flag, "user is able to view items in only completed status ", "user should be able to view items in only completed status", "Verify user is able to view items in only completed status");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items in only completed status ", "user should be able to view items in only completed status", "Verify user is able to view items in only completed status");
			}
			assertTrue(flag, "user is able to view status other that completed status ");
		return this;
	}
	
	
	/*************************************************************************************
	 * Method Name: validateSearchResults
	 * Purpose:Method for validating the search table for the filtered tset value passed
	 * Author: Deepa Ramu
	 * Created Date:  15-July-2017
	/*************************************************************************************/
	
	public  BankLandingPage validateSearchResults(String column , String value ){

		WebElement status = null  ;
		ArrayList<WebElement> rowcount = new ArrayList<>();
		rowcount=(ArrayList<WebElement>) tblIncomingDebits.findElements(By.tagName("tr"));
		int i=1;
		for (WebElement rowElement:rowcount){	
			
			if (column == "account"){
				status = rowElement.findElement(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr["+i+"]/td[4]"));	
					}
			else if (column == "serial"){
				status = rowElement.findElement(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr["+i+"]/td[5]"));	
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
		assertTrue(flag,column+" Search results filtered as expected");
		if (flag ){
			publishResults(flag, column+value+" search result displayed as expected", column+" search result should be displayed as expected","Verify search filter applied as expected");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  column+value+" search result not displayed as expected", column+" search result should be displayed as expected","Verify search filter applied as expected");
		}
		return this;
}
	
	/*************************************************************************************
	 * Method Name: validateSortSearchResults
	 * Purpose:Method for validating the search table for the filtered sort code value passed
	 * Author: Deepa Ramu
	 * Created Date:  15-June-2017
	/*************************************************************************************/
	
	public  boolean validateSortSearchResults(String fromSort , String toSort ){

		
		ArrayList<WebElement> rowcount = new ArrayList<>();
		rowcount=(ArrayList<WebElement>) tblIncomingDebits.findElements(By.tagName("tr"));
		System.out.println(rowcount.size());
		int i=1;		
		for (WebElement rowElement:rowcount){	
			WebElement sortCode = rowElement.findElement(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr["+i+"]/td[3]"));		
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
		if(lblShowCurrentFilterStatus.getText().contains(fromSort) && lblShowCurrentFilterStatus.getText().contains(toSort)){
			flagBanner = true;
		}
		assertTrue(flag,"Search results filtered for Sort Code as expected");
		assertTrue(flagBanner, "Sort code filters are applied debits tab");

		if (flag && flagBanner){
			publishResults(flag, "Sort Code search result displayed as expected", "Sort Code search result should be displayed as expected","Sort Code search filter applied as expected");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  "Sort Code search result not displayed as expected", "Sort Code search result should be displayed as expected","Sort Code search filter applied as expected");
		}
		return flag;
}
	
	/*************************************************************************************
	 * Method Name: validateAmountSearchResults
	 * Purpose:Method for validating the search table for the filtered amount value passed
	 * Author: Deepa Ramu
	 * Created Date:  15-June-2017
	/*************************************************************************************/
	
	public  boolean validateAmountSearchResults(float fromAmount , float toAmount ){

		ArrayList<WebElement> rowcount = new ArrayList<>();
		rowcount=(ArrayList<WebElement>) tblIncomingDebits.findElements(By.tagName("tr"));
		int i=1;		
		for (WebElement rowElement:rowcount){	
			WebElement amount = rowElement.findElement(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr["+i+"]/td[6]"));		
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
			  if (i==(rowcount.size()-1)) {
				 break;
			 }
		}
		assertTrue(flag,"Search results filtered for Amount as expected");
		assertTrue(flagBanner,"from account not present in filter banner" );
		if(lblShowCurrentFilterStatus.getText().contains("200.00") && lblShowCurrentFilterStatus.getText().contains("200000.00")){
			flagBanner = true;
		}
		if (flag && flagBanner){
			publishResults(flag, "Amount search result displayed as expected", "Amount search result should be displayed as expected","Amount search filter applied as expected");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  "Amount search result not displayed as expected", "Amount search result should be displayed as expected","Amount search filter applied as expected");
		}
		return flag;
}
	/*************************************************************************************
	 * Method Name: assertDetailsFieldsinDebitsTab
	 * Purpose:Method for validating the details entered in incoming debits tab
	 * Author: Deepa Ramu
	 * Created Date:  25-June-2017
	/*************************************************************************************/	
	public boolean assertDetailsFieldsinDebitsTab() throws InterruptedException 
	{
		selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusAllItems"));
		String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		fillTextBox(txtDebitsaccount,accno);
		clickElement(btnApplyFilters);
		pause(1000);
		validateSearchResults("account",accno);
		assertTrue(lblShowCurrentFilterStatus.getText().contains(accno), "Account filters are applied debits tab");
		
		clickElement(btnClearFilterIncomingDebits);
		pause(1000);
		String serial = tblSerial.getText();
		fillTextBox(txtSerial,serial);
		clickElement(btnApplyFilters);
		pause(1000);
		validateSearchResults("serial",serial);
		assertTrue(lblShowCurrentFilterStatus.getText().contains(serial), "Serial filters are applied debits tab");

		clickElement(btnClearFilterIncomingDebits);
		pause(1000);
		String sortCodeFrom = tblSortcode.getText();
		fillTextBox(txtSort,sortCodeFrom);
		String sortCodeTo = tblSortcode.getText();
		int sortCodeTo1 = Integer.parseInt(sortCodeTo);
		sortCodeTo1 = sortCodeTo1+1;
		sortCodeTo = Integer.toString(sortCodeTo1);
		fillTextBox(txtSortTo,sortCodeTo);
		clickElement(btnApplyFilters);
		pause(1000);
		validateSortSearchResults(sortCodeFrom,sortCodeTo);
		
		clickElement(btnClearFilterIncomingDebits);
		pause(1000);
		fillTextBox(txtDebitsAmount,"200");
		fillTextBox(txtDebitsAmountTo,"200000");
		clickElement(btnApplyFilters);
		pause(1000);
		validateAmountSearchResults(200,200000);
		
		accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		fillTextBox(txtDebitsaccount,accno);
		serial = tblSerial.getText();
		fillTextBox(txtSerial,serial);
		clickElement(btnApplyFilters);
		pause(1000);
		validateSearchResults("account",accno);
		validateSearchResults("serial",serial);
		assertTrue(lblShowCurrentFilterStatus.getText().contains(accno), "Account filters are applied debits tab");
		assertTrue(lblShowCurrentFilterStatus.getText().contains(serial), "Serial filters are applied debits tab");
		
		sortCodeFrom = tblSortcode.getText();
		fillTextBox(txtSort,sortCodeFrom);
		sortCodeTo = tblSortcode.getText();
		sortCodeTo1 = Integer.parseInt(sortCodeTo);
		sortCodeTo1 = sortCodeTo1+1;
		sortCodeTo = Integer.toString(sortCodeTo1);
		clickElement(btnApplyFilters);
		pause(1000);
		validateSortSearchResults(sortCodeFrom,sortCodeTo);
		validateSearchResults("account",accno);
		validateSearchResults("serial",serial);
		assertTrue(lblShowCurrentFilterStatus.getText().contains(accno), "Account filters are applied debits tab");
		assertTrue(lblShowCurrentFilterStatus.getText().contains(serial), "Serial filters are applied debits tab");
		return flag;
	}

	
	public BankLandingPage setPaymentDecisionStatus() throws InterruptedException 
	{
		selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusPayDecision"));
		clickElement(btnApplyFilters);
		return this;
	}
	
	public BankLandingPage setPaymentDecisionStatusClearFields() throws InterruptedException 
	{
		clickElementEnter(btnClearFilterIncomingDebits);
		selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusPayDecision"));
		clickElement(btnApplyFilters);
		pause(1000);
		return this;
	}
	
	public BankLandingPage setKappaDecisionStatusClearFields() throws InterruptedException 
	{
		clickElementEnter(btnClearFilterIncomingDebits);
		pause(1000);
		selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusKappa"));
		clickElement(btnApplyFilters);
		pause(1000);
		return this;
	}

	public BankLandingPage setAwaitingApprovalStatus() throws InterruptedException 
	{
		selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusAwaitingApproval"));
		clickElement(btnApplyFilters);
		return this;
	}
	
	public BankLandingPage setKappaDecisionStatus() throws InterruptedException 
	{
		selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusKappa"));
		clickElement(btnApplyFilters);
		return this;
	}
	

	public BankLandingPage setAllItemsStatus() throws InterruptedException 
	{
		selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusAllItems"));
		clickElement(btnApplyFilters);
		return this;
	}
	
	public BankLandingPage assertPaymentDecisionStatus() throws InterruptedException 
	{
		flag = false;
		if(lblShowCurrentFilterStatus.getText().contains(getAgencyResourceFile().getString("statusPayDecisionMsg"))
				&& validateTableText("incomingDebitsGrid_grid", getAgencyResourceFile().getString("statusPayDecision")))
		{
			flag = true;
		}

		assertTrue(flag, "Apply filters are applied on the status debits tab");	
		return this;
	}
	
	public BankLandingPage setBelowThresholdAmount() throws InterruptedException 
	{
		fillTextBox(txtDebitsAmount,getAgencyValueFromDataSheet("thresholdFromAmount"));
		fillTextBox(txtDebitsAmountTo,getAgencyValueFromDataSheet("thresholdToAmount"));
		clickElement(btnApplyFilters);
		pause(1000);
		return this;
	}
	
	public BankLandingPage setAboveThresholdAmount() throws InterruptedException 
	{
		fillTextBox(txtDebitsAmount,getAgencyValueFromDataSheet("thresholdToAmount"));
		fillTextBox(txtDebitsAmountTo,"999");
		clickElement(btnApplyFilters);
		pause(1000);
		return this;
	}
	
	public BankLandingPage checkSelectAllCheckbox()
	{
		clickElementSpace(chkDebitsSelectAll);		
		return this;
	}
	
	public BankLandingPage checkSelectAllCheckboxIncomingCredits()
	{
		clickElementSpace(chkCreditsSelectAll);		
		return this;
	}
	
	public BankLandingPage clickButtonDecisionPay()
	{
		clickElementEnter(btnDecisionPay);
		return this;
	}
	public BankLandingPage clickButtonDecisionNoPay()
	{
		clickElementEnter(btnDecisionNoPay);
		return this;
	}

	public BankLandingPage checkCheckbox()
	{
		clickElement(chkPayDecisionItem);	
		return this;
	}
	
	public BankLandingPage checkKappaDecisionCheckbox()
	{
		clickElement(chkKappaDecisionItem);	
		return this;
	}
	
	public boolean assertErrorMessageFromAlert()
	{
		boolean flag=false;
		String errMessage=getTextFromAlert();
		if(errMessage.equalsIgnoreCase("Maximum items allowed to download is 5 Please try Again."))
		{
			flag=true;
		}
		assertTrue(flag, "Expected error message is displayed");	
		WindowsAlertPopUp("Accept");
		return flag;
	}
	public BankLandingPage getRowsFromIncomingDebitsTable()
	{
		getTableRowCount("incomingDebitsGrid_grid");

		System.out.println(validateTableText("incomingDebitsGrid_grid", getAgencyResourceFile().getString("statusPayDecision")));
		return this;
	}

	/*************************************************************************************
	 * Method Name: getValuesFromTableOnAccountNumber
	 * Purpose:Method for getting the text from the table in Incoming Debits Screen
	 * Author: Shilpa Madhu
	 * Created Date:  09-Mar-2017
	/*************************************************************************************/
	public static String getDefaultdecisionValueFromTable()
	{
		pause(2000);
		String resultsTable=tblDefault.getText();
		return resultsTable;
	}
	
	/*************************************************************************************
	 * Method Name: getValuesFromTableOnAccountNumber
	 * Purpose:Method for getting the text from the table in Incoming Debits Screen
	 * Author: Shilpa Madhu
	 * Created Date:  09-Mar-2017
	/*************************************************************************************/
	public static String getFirstAccountNumberFromTableBasedOnFilter()
	{
		pause(2000);
		String resultsTable=tblAccount.getText();
		return resultsTable;
	}
	
	public static String getFirstSerialFromTableBasedOnFilter()
	{
		pause(2000);
		String resultsSerial=tblSerial.getText();
		return resultsSerial;
	}
	
	public static String getFirstSortCodeFromTableBasedOnFilter()
	{
		String resultsSortCode=tblSortcode.getText();
		return resultsSortCode;
	}
	
	public static String getFirstAmountFromTableBasedOnFilter()
	{
		String resultsAmount=tblAmount.getText();		
		return resultsAmount;
	}
		
	public BankLandingPage filterwithAmountandPay(String Decision) throws InterruptedException
	{ 
		clickElement(AmountSort);
		pause(1000);
		String Amount1 = driver.findElement(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr[1]/td[6]")).getText();
		String Amount2 = driver.findElement(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr[2]/td[6]")).getText();
		
		Amount1=Amount1.replace(",", "");
		Amount2=Amount2.replace(",", "");
		fillTextBox(txtDebitsAmount,Amount1);
		fillTextBox(txtDebitsAmountTo,Amount2);
		clickElementEnter(btnApplyFilters);	
		pause(1000);
		checkSelectAllCheckbox();
		pause(10000);
		assertMultiSelectTabs();
		pause(1000);
		if (Decision == "Pay")
		{
		clickButtonDecisionPay();
		}
		else if (Decision == "NoPay")
		{
			selectDropdownValue(driver, ddlFailReason,getAgencyValueFromDataSheet("noPayReason"));		
			clickButtonDecisionNoPay();
		}
		setAllItemsStatus();
		pause(1000);
		fillTextBox(txtDebitsAmount,Amount1);
		fillTextBox(txtDebitsAmountTo,Amount2);
		return this;
	}

	/*************************************************************************************
	 * Method Name: getValuesFromTableOnAccountNumber
	 * Purpose:Method for getting the text from the table in Incoming Debits Screen
	 * Author: Shilpa Madhu
	 * Created Date:  09-Mar-2017
	/*************************************************************************************/
	public static String getFirstStatusFromTableBasedOnFilter()
	{
		String resultsTable=tblIncomingDebits.getText();
		System.out.println(resultsTable);
		return resultsTable;
	}


	/*************************************************************************************
	 * Method Name: applyFiltersBasedOnAccountNumber
	 * Purpose:Method for applying filters based on AccountNumber Incoming Debits Screen
	 * Author: Shilpa Madhu
	 * Created Date:  09-Mar-2017
	/*************************************************************************************/
	public BankLandingPage applyFiltersBasedOnAccountNumber(String AccNo)throws Exception
	{
		selectDropdownValue(driver, ddlDebitsStatus, getAgencyResourceFile().getString("statusAllItems"));
		fillTextBox(txtDebitsaccount, AccNo);
		pause(1000);
//		WebDriverWait wait= new WebDriverWait(driver,20);
//		wait.until(ExpectedConditions.elementToBeClickable(btnApplyFilters));
		clickElementEnter(btnApplyFilters);
		pause(1000);
		return this;
	}

	/**********************************************************************************************
	 * Method Name: approveDecisionBasedOnAccountFilter
	 * Purpose:Method for clicking on Approve button based on the account number filters
	 * Author: Abir Pramanik
	 * Created Date:  14-Mar-2017
	/**********************************************************************************************/	
	public BankLandingPage approveDecisionBasedOnAccountFilter(String accNo) throws Exception
	{	
		applyFiltersBasedOnAccountNumber(accNo);	
		BankLandingPage.clickApproveBasedOnDecision();
		return this;
	}
	
	/**********************************************************************************************
	 * Method Name: approveDecisionBasedOnAccountFilter
	 * Purpose:Method for clicking on Approve button based on the account number filters
	 * Author: Abir Pramanik
	 * Created Date:  14-Mar-2017
	/**********************************************************************************************/	
	public BankLandingPage payDecisionBasedOnAccountFilter(String accNo) throws Exception
	{	
		applyFiltersBasedOnAccountNumber(accNo);	
		BankLandingPage.clickOnPayIcon();
		return this;
	}

	/**********************************************************************************************
	 * Method Name: clickApproveBasedOnDecision
	 * Purpose:Method for clicking on Approve button based on the decision
	 * Author: Shilpa Madhu
	 * Created Date:  11-Mar-2017
	/**********************************************************************************************/
	public static void clickApproveBasedOnDecision()
	{
		clickElementSpace(chkDebitsSelectAll);
		pause(2000);
		clickElement(btnApproval);	
		pause(2000);		
	}
	
	/**********************************************************************************************
	 * Method Name: clickApproveBasedOnDecision
	 * Purpose:Method for clicking on Approve button based on the decision
	 * Author: Shilpa Madhu
	 * Created Date:  11-Mar-2017
	/**********************************************************************************************/
	public static void clickOnPayIcon()
	{
		clickElementSpace(chkDebitsSelectAll);
		pause(2000);
		clickElement(btnPayDecision);	
		pause(2000);		
	}

	/**********************************************************************************************
	 * Method Name: clickDenyBasedOnDecision
	 * Purpose:Method for clicking on Deny button based on the decision
	 * Author: Shilpa Madhu
	 * Created Date:  16-Mar-2017
	/**********************************************************************************************/
	public static void clickDenyBasedOnDecision(String failReasonStatus) throws Exception
	{
		clickElementSpace(chkDebitsSelectAll);
		pause(1000);
		selectDropdownValue(driver, selectFailreason, failReasonStatus);	
		pause(1000);
		clickElement(btnNoPayIcon);
		pause(1000);		
	}
	/**********************************************************************************************
	 * Method Name: selectAllNoPayReason
	 * Purpose:Method for clicking on Deny button based on the decision
	 * Author: Deepa Ramu
	 * Created Date:  31-aug-2017
	/**********************************************************************************************/
	public BankLandingPage verifyAllNoPayReason() throws Exception
	{
		boolean flag;
		boolean flag1;
		String decisioner= getAgencyValueFromDataSheet("userName");
		pause(3000);
		Select dropdown = new Select (selectFailreason);
		int totalValues =dropdown.getOptions().size();
		for(int i=1;i<(totalValues-1);i++){
			selectDropdownValue(driver, selectFailreason, dropdown.getOptions().get(i).getText());
			String reasonCode=dropdown.getOptions().get(i).getText().trim();
			pause(1000);
			String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
			clickElement(btnNoPayIcon);
			pause(1000);	
			applyFiltersBasedOnAccountNumber(accno);
			flag1= validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
			flag = validateColumnsInWebPortal("Decisions","No Pay - "+reasonCode);
			//validateColumnsInWebPortal("Decisioner",decisioner);
			if (flag == false){
			finalTestScriptResultFailFlag = true;
			publishResults(BankLandingPage.returnResultFlag(), "unable to take decision for-"+reasonCode, "Decision value should be set for-"+reasonCode, "Verify no pay decision is taken successfully for all reason code");
			break;
		}
			clickElementEnter(btnClearFilterIncomingDebits);
			setPaymentDecisionStatus();
			
		}
		return null;
			
	}
	/**********************************************************************************************
	 * Method Name: clickDenyByApprover
	 * Purpose:Method for clicking on Deny approval button based on the decision
	 * Author: Shilpa Madhu
	 * Created Date:  16-Mar-2017
	/**********************************************************************************************/
		
	public BankLandingPage clickDenyByApprover(String accNo) throws Exception
	{
		applyFiltersBasedOnAccountNumber(accNo);
		clickElementSpace(chkDebitsSelectAll);
		pause(10000);
		clickElement(btnDenyApproval);
		pause(10000);	
		return this;
	}
	/**********************************************************************************************
	 * Method Name: validateColumnsInWebPortal
	 * Purpose:Method for validating columns in web portal
	 * Author: Deepa Ramu
	 * Created Date:  16-Mar-2017
	/**********************************************************************************************/
public static boolean validateColumnsInWebPortal(String Item,String value) throws Exception
	{	
		if (Item=="Status")	{

			pause(1000);
			String uiagencyTableresults=BankLandingPage.getFirstStatusFromTableBasedOnFilter();
			if(uiagencyTableresults.contains(value)){
				flag = true;
				publishResults(flag,"Status is in "+value+ "status", "Status should be in "+value, "Verify status is in "+value);
			}else{
				flag = false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag,"Status is not in "+value+ "status", "Status should be in "+value+"status", "Verify status is in"+value);
			}
		}
		else if (Item=="Decisions"){
			String uiagencyTableresults=BankLandingPage.getFirstStatusFromTableBasedOnFilter();
			if(uiagencyTableresults.contains(value)){
				flag = true;
				publishResults(flag, "Decision value is changed to "+value, "Decision value should be "+value, "Verify Decision is in "+value+" status");
			}
			else{
				flag = false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "Decision value is not changed to "+value, "Decision value should be "+value, "Verify Decision is in "+value+ "status");
			}
		}

		else if (Item=="Decisioner"){
			String uiagencyTableresults=BankLandingPage.getFirstStatusFromTableBasedOnFilter();
			if(uiagencyTableresults.contains(value)){
				flag = true;
				publishResults(BankLandingPage.returnResultFlag(), "Decisioner name is displayed", "Decisioner name should be displayed", "Verify Decisioner name is displayed as: "+value);
			}
			else{
				flag = false;
				finalTestScriptResultFailFlag = true;
				publishResults(BankLandingPage.returnResultFlag(), "Decisioner name is not displayed", "Decisioner name should be displayed", "Verify Decisioner name is displayed as: "+value);
			}
		}
			
		else if (Item=="DenyApprovalStatus"){
			String uiagencyTableresults=BankLandingPage.getFirstStatusFromTableBasedOnFilter();
			if((uiagencyTableresults.contains(value))){
				flag = true;
				publishResults(BankLandingPage.returnResultFlag(), "Status value changed to "+value+ "as expected", "Decision value  should be changed to "+value+" as expected", "Verify status is changed to previous status accordingly - "+value);
			}
			else{
				flag = false;
				finalTestScriptResultFailFlag = true;
				publishResults(BankLandingPage.returnResultFlag(), "Status value not changed to "+value+" as expected", "Decision value  should be changed to "+value+" as expected", "Verify status is changed to previous status accordingly - "+value);
			}
		}
		return flag;
	}	

/**********************************************************************************************
 * Method Name: validateColumnsInWebPortalDefault
 * Purpose:Method for validating columns in web portal
 * Author: Deepa Ramu
 * Created Date:  16-Mar-2017
/**********************************************************************************************/
public static boolean validateColumnsInWebPortalDefault(String Item,String value) throws Exception
{	
	if (Item=="Status")	{

		pause(2000);
		String uiagencyTableresults=BankLandingPage.getFirstStatusFromTableBasedOnFilter();
		if(uiagencyTableresults.contains(value)){
			flag = true;
		}else{
			flag = false;
			finalTestScriptResultFailFlag = true;
			publishResults(BankLandingPage.returnResultFlag(),"Status is not in "+value+ "status", "Status should be in "+value+"status", "Verify status is in"+value);
		}
	}
	else if (Item=="Decisions"){
		String uiagencyTableresults=BankLandingPage.getFirstStatusFromTableBasedOnFilter();
		if(uiagencyTableresults.contains(value)){
			flag = true;
		}
		else{
			flag = false;
			finalTestScriptResultFailFlag = true;
			publishResults(BankLandingPage.returnResultFlag(), "Decision value is not changed to "+value, "Decision value should be "+value, "Verify Decision is in "+value+ "status");
		}
	}

	else if (Item=="Decisioner"){
		String uiagencyTableresults=BankLandingPage.getFirstStatusFromTableBasedOnFilter();
		if(uiagencyTableresults.contains(value)){
			flag = true;
		}
		else{
			flag = false;
			finalTestScriptResultFailFlag = true;
			publishResults(BankLandingPage.returnResultFlag(), "Decisioner name is not displayed", "Decisioner name should be displayed", "Verify Decisioner name is displayed");
		}
	}
		
	else if (Item=="DenyApprovalStatus"){
		String uiagencyTableresults=BankLandingPage.getFirstStatusFromTableBasedOnFilter();
		if((uiagencyTableresults.contains(value))){
			flag = true;
		}
		else{
			flag = false;
			finalTestScriptResultFailFlag = true;
			publishResults(BankLandingPage.returnResultFlag(), "Status value not changed to "+value+" as expected", "Decision value  should be changed to "+value+" as expected", "Verify status is changed to previous status accordingly - "+value);
		}
	}
	
	else if (Item=="Default"){
		String uiagencyTableresults=BankLandingPage.getFirstStatusFromTableBasedOnFilter();
		if(uiagencyTableresults.contains(value)){
			flag = true;
		}
		else{
			flag = false;
			finalTestScriptResultFailFlag = true;
			publishResults(BankLandingPage.returnResultFlag(), "Column Default value is No Pay", "Column Default value should be No Pay", "Verify Default value is No pay");
		}
	}
	return flag;
}	

	/**********************************************************************************************
	 * Method Name: approveNoPayDecisionBasedOnAccountFilter
	 * Purpose:Method for clicking on Deny button based on the account number filters
	 * Author: Shilpa Madhu
	 * Created Date:  16-Mar-2017
	/**********************************************************************************************/	
	public BankLandingPage approveNoPayDecisionBasedOnAccountFilter(String accNo,String failReasonStatus) throws Exception
	{	
		applyFiltersBasedOnAccountNumber(accNo);	
		BankLandingPage.clickDenyBasedOnDecision(failReasonStatus);
		return this;
	}
	
	/**********************************************************************************************
	 * Method Name: approveNoPayDecisionBasedOnAccountFilter
	 * Purpose:Method for clicking on Deny button based on the account number filters
	 * Author: Shilpa Madhu
	 * Created Date:  16-Mar-2017
	/**********************************************************************************************/	
	public BankLandingPage noPayDecisionBasedOnAccountFilter(String accNo,String failReasonStatus) throws Exception
	{	
		applyFiltersBasedOnAccountNumber(accNo);	
		BankLandingPage.clickDenyBasedOnDecision(failReasonStatus);
		return this;
	}

	public void clickOnHelpIcon()
	{	
		String title = null;
		pause(1000);
		clickElement(helpIcon);	
		
		for (String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}	
		/*pause(2000);
		clickElement(securityOverrideLink);	*/
		pause(2000);
		title= driver.getTitle();
		if (title.contains("Your ASP.NET application")){
			publishResults(true, "Help page is displayed as expected", "Help page should open when Help icon is clicked", "user should be able to view the help page");
		driver.close();
		}
		else{
			finalTestScriptResultFailFlag = true;
			publishResults(false, "Help page is not displayed", "Help page should open when Help icon is clicked", "user should be able to view the help page");
		}
	}

	/*************************************************************************************
	 * Method Name: assertMultiSelectTabs
	 * Purpose:Method for validating multiselctselectTabs in incoming debits
	 * Author: 
	 * Reviewed by : 
	 * Created Date:  28-june-2017
	/*************************************************************************************/	
	
	public BankLandingPage assertMultiSelectTabs()
	{
		if (isElementDisplayed(btnCreatePdf) && multiSelectDownloadTab.getAttribute("style").contentEquals("display: block;"))
		{
			publishResults(isElementDisplayed(btnCreatePdf), "Download selected items is present", "Download selected items button should be present", "Verify Download button is present");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(isElementDisplayed(btnCreatePdf),"Download selected items is not present", "Download selected items button should be present", "Verify Download button is present");
		}
		assertTrue(isElementDisplayed(btnCreatePdf), assertMultiSelectTab);
		if (isElementDisplayed(btnDecisionPay))
		{
			publishResults(isElementDisplayed(btnDecisionPay), "Pay selected item is present", "pay selected item button should be present", "Verify pay selected item button is present");
		}
		else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(isElementDisplayed(btnDecisionPay),"Pay selected item is not present", "pay selected item button should be present", "Verify pay selected item button is present");
		}
		assertTrue(isElementDisplayed(btnDecisionPay), assertMultiSelectTab);
		if (isElementDisplayed(ddlFailReason))
		{
			publishResults(isElementDisplayed(ddlFailReason), "Return reason dropdown is present", "Return reason dropdown should be present", "Verify Return reason dropdown is present");
		}
		else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(isElementDisplayed(ddlFailReason),"Return reason dropdown is not present", "Return reason dropdown should be present", "Verify Return reason dropdown is present");
		}
		assertTrue(isElementDisplayed(ddlFailReason), assertMultiSelectTab);
		
		if (isElementDisplayed(btnDecisionNoPay))
		{
			publishResults(isElementDisplayed(btnDecisionNoPay), "Unpay selected item button is present", "Unpay selected item button should be present", "Verify Unpay selected item button is present");
		}
		else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(isElementDisplayed(btnDecisionNoPay),"Unpay selected item button is not present", "Unpay selected item button should be present", "Verify Unpay selected item button is present");
		}
		assertTrue(isElementDisplayed(btnDecisionNoPay), assertMultiSelectTab);
		return this;
	}
	/*************************************************************************************
	 * Method Name: assertPayNoPayDisabled
	 * Purpose:Method for validating pay no pay icons disabled
	 * Author: 
	 * Reviewed by : 
	 * Created Date:  28-june-2017
	/*************************************************************************************/			
	public BankLandingPage assertPayNoPayDisabled()
	{
		if (btnPayDecision.getAttribute("class").trim().contains("fa fa-pay fa-2x CM-IMG-D"))
		{	flag=true;
			publishResults(flag, "Pay button is disabled", "Pay icon should be disabled", "Verify pay icon is disabled");
			}
		else
		{
			flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag,"Pay button is not disabled", "Pay icon should be disabled", "Verify pay icon is disabled");			
			}
		assertTrue(flag, "pay icon not disabled");
		if (btnNoPayIcon.getAttribute("class").trim().contains("fa fa-no-pay fa-2x CM-IMG-D"))
		{
			flag=true;
			publishResults(flag, "No Pay button is not disabled", "No Pay icon should be disabled", "Verify no pay icon is disabled");
		}
		else
		{
			flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag,"No Pay button is disabled", "No Pay icon should be disabled", "Verify no pay icon is disabled");
				
			}
		assertTrue(flag, "No pay icon not disabled");
		return this;
	}
	/*************************************************************************************
	 * Method Name: assertPayNoPayNotVisisble
	 * Purpose:Method for validating pay no pay icons not visible
	 * Author: 
	 * Reviewed by : 
	 * Created Date:  28-june-2017
	/*************************************************************************************/	
	public BankLandingPage assertPayNoPayNotVisisble()
	{
		validationStepInformation("User Role that does not have permission should not be able to view the pay , no pay icons");
		flag =false;
	
		try{
			driver.findElement(By.id("imgAllowablePayDecision"));
		} catch (NoSuchElementException e){
			flag=true;
			publishResults(true, "Pay button is not visible","Pay icon should not be visible", "Verify pay icon is not visible");		
		}
		try{
			driver.findElement(By.id("FailReasonsSelect"));
				
		} catch (NoSuchElementException e){
			flag=true;
			publishResults(true, "Reason Code LOVs are not present", "Reason Code LOVs should not be displayed", "Verify Reason Code LOVs are not visible");		
		}
		try{
			driver.findElement(By.id("imgNotAllowableNoPayDecision"));
				
		} catch (NoSuchElementException e){
			flag=true;
			publishResults(true, "No Pay icon is not visible","No Pay icon should not be visible", "Verify no pay icon is not visible");		
		}
		finally{
		if (flag=true)
		{
		}else
		{publishResults(false, "Reason Code/pay/no pay icon is present", "Reason Code/pay/no pay icon should not be displayed", "Verify Reason Code/pay/no pay icon are not visible");		
			}
		}		
		return this;
	}
	/*************************************************************************************
	 * Method Name: assertPayNoPayEnabled
	 * Purpose:Method for validating approve icons enabled
	 * Author: 
	 * Reviewed by : 
	 * Created Date:  28-june-2017
	/*************************************************************************************/			
	public BankLandingPage assertPayNoPayEnabled()
	{	
		validationStepInformation("User Role that has required permission should be able to view the pay , no pay icons");
		pause(1000);
		if ( btnPayDecision.getAttribute("class").trim().contains("form-img-color-gr CM-IMG-E")){
			flag = true;
			publishResults(flag, "Pay button is enabled", "Pay icon should be enabled", "Verify pay icon is enabled");}
		else
		{
			flag = false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag,"Pay button is not enabled", "Pay icon should be enabled", "Verify pay icon is enabled");
			}
		assertTrue(flag, "pay icon is enabled");
		pause(1000);
		if (isElementDisplayed(selectFailreason))
		{	flag = true; 
			selectDropdownValue ( driver, selectFailreason, getAgencyValueFromDataSheet("noPayReason"));
			publishResults(isElementDisplayed(selectFailreason), "Reason Code LOVs are present", "Reason Code LOVs should be displayed", "Verify Reason Code LOVs are enabled");
		}else
		{
			flag = false;
			finalTestScriptResultFailFlag = true;
			publishResults(isElementDisplayed(selectFailreason), "Reason Code LOVs are not present", "Reason Code LOVs should be displayed", "Verify Reason Code LOVs are enabled");
					
			}
		assertTrue(isElementDisplayed(selectFailreason), "Reason Code LOVs are not present");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()",btnNoPayIcon);
		if ( btnNoPayIcon.getAttribute("class").trim().contains("fa fa-no-pay fa-2x form-kappa-js CM-IMG-E")){
			flag = true;
			publishResults(flag , "No Pay button is enabled", "No Pay icon should be enabled", "Verify no pay icon is enabled");
		}
		else
		{
			flag = false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag , "No Pay button is not enabled", "No Pay icon should be enabled", "Verify no pay icon is enabled");
					
			}
		assertTrue(flag, "No pay icon is enabled");
		return this;
	}
	/*************************************************************************************
	 * Method Name: assertApproveDenyApprovalDisabled
	 * Purpose:Method for validating approve icons disabled
	 * Author: 
	 * Reviewed by : 
	 * Created Date:  28-june-2017
	/*************************************************************************************/			
	public BankLandingPage assertApproveDenyApprovalDisabled()
	{
		if (btnApproval.getAttribute("class").trim().contains("fa fa-pay fa-2x CM-IMG-D css-for-imgfafa AGYtooltip"))
		{
			flag=true;
			publishResults(flag,"Approval button is disabled", "Approval icon should be disabled", "Verify Approval icon is disabled");
			}
		else
		{
			flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag,"Approval button is not disabled", "Approval icon should be disabled", "Verify Approval icon is disabled");
		
			}
		assertTrue(flag, "Approval icon not disabled");
		if (btnDenyApproval.getAttribute("class").trim().contains("fa fa-no-pay fa-2x CM-IMG-D"))
		{
			flag=true;
			publishResults(flag, "Deny Approval button is disabled", "Deny Approval icon should be disabled", "Verify deny Approval icon is disabled");
		}
		else
		{
			flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag,"Deny Approval button is disabled", "Deny Approval icon should be disabled", "Verify deny Approval icon is disabled");
		
			}
		assertTrue(flag, "Deny Approval icon not disabled");
		return this;
	}
	/*************************************************************************************
	 * Method Name: verifyImageIcons
	 * Purpose:Method for validating image icons
	 * Author: 
	 * Reviewed by : 
	 * Created Date:  28-june-2017
	/*************************************************************************************/		
	public BankLandingPage verifyImageIcons()
	{
		if (isElementEnabled(btnApproval))
		{
			publishResults(isElementEnabled(btnApproval),"Approval button is disabled", "Approval icon should be disabled", "Verify Approval icon is disabled");
			}
		else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(isElementEnabled(btnApproval),"Approval button is not disabled", "Approval icon should be disabled", "Verify Approval icon is disabled");
		
			}
		assertTrue(isElementEnabled(btnApproval), "Approval icon not disabled");
		return this;
	}
	/*************************************************************************************
	 * Method Name: assertMultiSelectTabsNotPresent
	 * Purpose:Method for validating multiselect tabs are not present by default
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  28-june-2017
	/*************************************************************************************/		
	public BankLandingPage assertMultiSelectTabsNotPresent()
	{
		if(multiSelectDownloadTab.getAttribute("style").contentEquals("display: none;"))
		{
			publishResults(true, "Multiselect expander not present after the checkbox is unchecked", "Multiselect expander should not be present after the checkbox is unchecked", "Verify Multiselect expander not present after the checkbox is unchecked");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(false,"Multiselect expander present after the checkbox is unchecked", "Multiselect expander should not be present after the checkbox is unchecked", "Verify Multiselect expander not present after the checkbox is unchecked");
		}
		assertTrue(multiSelectDownloadTab.getAttribute("style").contentEquals("display: none;"), "Multi select expander still present");
		return this;
	}
	/*************************************************************************************
	 * Method Name: validateIncomingDebitsTab
	 * Purpose:Method for validating incoming debits tab columns and icons
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  12-june-2017
	/*************************************************************************************/	
	public BankLandingPage validateIncomingDebitsTab()
	{		assertTrue(isElementDisplayed(tblIncomingDebits), "T set table is not visible ");
		String resultsTable=tblIncomingDebits.getText();
		String source=driver.getPageSource();
		if(source.contains("Status") && source.contains("Serial") && source.contains("SortCode")
				 && source.contains("Account") && source.contains("Amount") && source.contains("Image")
				 && source.contains("Kappa") && source.contains("AccountSwitch") && source.contains("Other")
				 && source.contains("Default") && source.contains("Decisions")  && source.contains("Decisioner")
				 && source.contains("Approval") && getTableRowCount("incomingDebitsGrid_grid")>0)
		{
			flag=true;
			publishResults(flag, "T- set exists with the required columns", "T-set should be displayed with all the required columns", "Verify T set is present with all required fields");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "T- set does not display all required columns", "T-set should be displayed with all the required columns", "Verify T set is present with all required fields");
		}
		assertTrue(flag, "Incoming Debit tab Tset dispalyed as expected");	
		fillTextBox(txtDebitsaccount,getAgencyValueFromDataSheet("allIconEnabledAccount"));
		clickElement(btnApplyFilters);
		pause(1000);	
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
		if(kappaData.getAttribute("Class").contains("css-for-OtherInfo") && kappaData.getAttribute("Class").contains("trKappa")
				&& kappaImage.getAttribute("Class").contains("trImages css-for-img"))
		{
			flag=true;
			publishResults(flag, "Kappa Expander with Data and Image is Visible", "Kappa Expander with Data and Image should be present Visible", "Verify Kappa expander is dispalyed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Kappa Expander with Data and Image is not Visible", "Kappa Expander with Data and Image should be present Visible", "Verify Kappa expander is dispalyed");
		}	
		assertTrue(flag, "Kappa Image and data displayed as expected");
		
		clickElement(imgAccountSwitch);
		clickElement(imgAccountSwitch);
		pause(2000);
		if(isElementDisplayed(accountSwitchExpander))
		{
			flag=true;
			publishResults(flag, "Account Switch Expander is displayed", "Account Switch Expander should be displayed", "Verify Account Switch Expander is dispalyed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Account Switch Expander is not displayed", "Account Switch Expander should be displayed", "Verify Account Switch Expander is dispalyed");
		}
		assertTrue(flag, "Account switch data displayed as expected");
		clickElement (imgOther);
		clickElement (imgOther);
		pause(2000);
		if(otherExpander.isDisplayed())
		{
			flag=true;
			publishResults(flag, "Other Data Expander is displayed", "Other Data Expander should be displayed", "Verify Other Data Expander is dispalyed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Other Data Expander is not displayed", "Other Data Expander should be displayed", "Verify Other Data Expander is dispalyed");
		}
		assertTrue(flag, "Other data displayed as expected");		
		clickElement(imgKappaDataCollapse);
		clickElement(imgKappaImageCollapse);
		clickElement(imgAccountSwitchCollapse);
		clickElement(imgImageCollapse);
		imgOtherCollapse.click();
		publishResults(true, "All data is collapsed", "All data should be collapsed", "Verify all data is collapsed");
		return this;
	}
	/*************************************************************************************
	 * Method Name: validatePagination
	 * Purpose:Method for validating pagination of incoming debit screen
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  12-July-2017
	/*************************************************************************************/
	public BankLandingPage validatePagination()
	{
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
		if(!(paginationPrevious.getAttribute("class").contains("paginate_button previous disabled")) && !(paginationNext.getAttribute("class").contains("paginate_button next disabled")) 
				&& filterPageDetails.getText().contains("Showing 11 to 20 of") ){
				flag=true;
				publishResults(flag, "Pagination details when Next is clicked is displayed as expected ", "Pagination details when Next is clicked is displayed should be as expected", "Verify next page grid is displayed , previous button is Enabled");
			}else
			{	flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "Pagination details when Next is clicked is displayed as expected ", "Pagination details when Next is clicked is displayed should be as expected", "Verify next page grid is displayed , previous button is Enabled");
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
		List <WebElement> pagination = driver.findElements(By.xpath("//div[@id='incomingDebitsGrid_grid_paginate']//ul//li"));
		System.out.println(pagination.size());
		
		if (pagination.size() > 2){
			publishResults(true, "Multiple pages are displayed", "Multiple pages should be displayed", "Verify more than one page is displayed");
		}
			WebElement page2 = driver.findElement(By.xpath("//div[@id='incomingDebitsGrid_grid_paginate']/ul/li[3]"));
			
			clickElement(page2);
			 
				System.out.println("clicked on page no. 2");				 
				Boolean Page2Previous = verifyTrue(!(paginationPrevious.getAttribute("class").contains("paginate_button previous disabled")),"previous is enabled");				
				Boolean Page2Next = verifyTrue(!(paginationNext.getAttribute("class").contains("paginate_button next disabled")),"next is enabled");
				Boolean Page2Status = verifyTrue(filterPageDetails.getText().contains("Showing 11 to 20 of"),"status field updated accordingly");
				//Boolean Page2State = verifyTrue((page2.getAttribute("class").contains("paginate_button active")),"2 is disabled");
				
				if(Page2Previous==true && Page2Next==true && Page2Status==true /*&& Page2State==true*/){
						publishResults(flag, "Pagination details for page 2 is displayed as expected ", "Pagination details when page 2 is clicked should be as expected", "Verify page 2 is displayed , previous button is enabled with status messagae updated");
					}else
					{
						finalTestScriptResultFailFlag = true;
						publishResults(flag, "Pagination details for page 2 is displayed as expected ", "Pagination details when page 2 is clicked should be as expected", "Verify page 2 is displayed , previous button is enabled with status messagae updated");
					}
				pause(2000);
				WebElement page1 = driver.findElement(By.xpath("//div[@id='incomingDebitsGrid_grid_paginate']/ul/li[2]"));						
				clickElement(page1);
				pause(1000);
				 System.out.println("clicked on page no. 1");				 
				 Boolean Page1Previous = verifyTrue((paginationPrevious.getAttribute("class").contains("paginate_button previous disabled")),"previous is disabled");
				 Boolean Page1Next = verifyTrue(!(paginationNext.getAttribute("class").contains("paginate_button next disabled")),"next is enabled");
				 Boolean Page1Status = verifyTrue(filterPageDetails.getText().contains("Showing 1 to 10 of"),"status field updated accordingly");
				 //Boolean Page1State = verifyTrue(page1.getAttribute("class").contains("paginate_button active"),"1 is disabled");			 
				
				 if(Page1Previous==true && Page1Next==true && Page1Status==true /*&& Page1State==true*/){
						publishResults(flag, "Pagination details for page 1 is displayed as expected ", "Pagination details when page 1 is clicked should be as expected", "Verify page 1 is displayed , previous button is disabled with status messagae updated");
					}else
					{
						finalTestScriptResultFailFlag = true;
						publishResults(flag, "Pagination details for page 1 is displayed as expected ", "Pagination details when page 1 is clicked should be as expected", "Verify page 1 is displayed , previous button is disabled with status messagae updated");
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
	public BankLandingPage validateSorting() throws Exception
	{		
		// -- > Default sorting cannot be checked as the items are displayed on how it comes from DEW
		//Sorting has been removed on Status column
validationStepInformation("Verify Sorting of Account Column");

		pause(1000);	
		clickElement(AccountSort);
		List<WebElement> elementList = driver.findElements(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr/td[4]"));		
		flag = verifySortAscending (elementList);
		publishStatementAscending ("Account",flag);
	
		clickElement(AccountSort);
		elementList = driver.findElements(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr/td[4]"));
		flag = verifySortDescending (elementList);
		publishStatementDescending ("Account",flag);
		
validationStepInformation("Verify Sorting of Serial Number");
		
		pause(1000);
		clickElement(SerialSort);
		elementList = driver.findElements(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr/td[5]"));
		flag = verifySortAscending (elementList);
		publishStatementAscending ("Serial Number",flag);	
		pause(1000);
		clickElement(SerialSort);
		elementList = driver.findElements(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr/td[5]"));
		flag = verifySortDescending (elementList);
		publishStatementDescending ("Serial Number",flag);
		
validationStepInformation("Verify Sorting of Sort Code");
		
		clickElement(SortCodeSort);
		elementList = driver.findElements(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr/td[3]"));
		flag = verifySortAscending (elementList);
		publishStatementAscending ("Sort Code",flag);
		pause(1000);		
		clickElement(SortCodeSort);
		elementList = driver.findElements(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr/td[3]"));
		flag = verifySortDescending (elementList);
		publishStatementDescending ("Sort Code",flag);
						
validationStepInformation("Verify Sorting of Amount");
		
		clickElement(AmountSort);
		elementList = driver.findElements(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr/td[6]"));
		flag = verifySortAscending (elementList);
		if(flag)
		publishStatementAscending ("Amount",flag);				
		pause(1000);
		clickElement(AmountSort);
		elementList = driver.findElements(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr/td[6]"));	
		flag = verifySortDescending (elementList);		
		publishStatementDescending ("Amount",flag);

validationStepInformation("Verify Sorting of default column");
		
		clickElement(DefaultSort);
		elementList = driver.findElements(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr/td[11]"));
		flag = verifySortAscending (elementList);
		publishStatementAscending ("Default",flag);	
		pause(1000);
		clickElement(DefaultSort);
		elementList = driver.findElements(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr/td[11]"));
		flag = verifySortDescending (elementList);
		publishStatementDescending ("Default",flag);
		
validationStepInformation("Verify Sorting of decisions column");
		
		clickElement(DecisionSort);
		elementList = driver.findElements(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr/td[12]"));
		flag = verifySortAscending (elementList);
		publishStatementAscending ("Decision",flag);	
		pause(1000);
		clickElement(DecisionSort);
		elementList = driver.findElements(By.xpath("//*[@id='incomingDebitsGrid_grid']/tbody/tr/td[12]"));
		flag = verifySortDescending (elementList);
		publishStatementDescending ("Decision",flag);
		return this;	
	}
	
	/*************************************************************************************
	 * Method Name: publishStatementDescending
	 * Purpose:Method for publish statements in descending order
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  12-July-2017
	/*************************************************************************************/
	public static BankLandingPage publishStatementDescending(String value,boolean flag) throws Exception
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
	 * Method Name: downloadValidation
	 * Purpose:Method for validating the multiselect expander
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  12-Aug-2017
	/*************************************************************************************/
	public BankLandingPage downloadValidation()
	{
		clickElement(btnCreatePdf);
		assertMultiSelectTabsNotPresent();
		if (chkKappaDecisionItem.isSelected())
		{
			publishResults(isElementDisplayed(btnCreatePdf), "Download selected items is present", "Download selected items button should be present", "Verify Download button is present");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(isElementDisplayed(btnCreatePdf),"Download selected items is not present", "Download selected items button should be present", "Verify Download button is present");
		}
		assertTrue(isElementDisplayed(btnCreatePdf), assertMultiSelectTab);
		return this;
	}
	

	/*************************************************************************************
	 * Method Name: validateOtherInformation
	 * Purpose:Method for validating other info in incoming debits tab
	 * Author: Deepa Ramu
	 * Created Date:  14-June-2017
	/*************************************************************************************/
	
	public BankLandingPage validateOtherInformation()
	{	
		String otherInfo = null;
		fillTextBox(txtDebitsaccount,getAgencyValueFromDataSheet("otherDataAccount"));
		WebDriverWait wait= new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(btnApplyFilters));
		clickElementEnter(btnApplyFilters);
		pause(1000);
			clickElement(imgOther);
			pause(1000);
			assertTrue(otherExpander.isDisplayed(),"Other details is present");
			otherInfo = otherExpander.getText();
		
		//assertTrue(isElementDisplayed(otherExpanderCollItems), "Other Items expander is present");
		
		if (otherInfo.contains("Transaction Set Identifier:") && 
			otherInfo.contains("Transaction Set Version:") &&
			otherInfo.contains("Is Amount Corrected: Yes") &&
			otherInfo.contains("Is AN Corrected: Yes") &&
			otherInfo.contains("Is SortCode Corrected: No") &&
			otherInfo.contains("Is Serial Corrected: Yes") &&
			otherInfo.contains("D2 Response Window Start Date:") &&
			otherInfo.contains("D2 Response Window End Date:") &&
			otherInfo.contains("Is Bank Holiday: No") &&
			otherInfo.contains("Item Reject Codes: 999") &&
			otherInfo.contains("Holdover: Yes"))
		
		{
			flag=true;
			publishResults(flag, "Other Information data displayed", "Other Information data should be displayed", "Verify Other Information data is dispalyed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Other Information data is not displayed", "Other Information data should be displayed", "Verify Other Information data is dispalyed");
		}
		
		return this;
	}
	/*************************************************************************************
	 * Method Name: urlValidation
	 * Purpose:Method for validating the portal when we enter url in new tab in same browser (In progress)
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  12-Aug-2017
	/*************************************************************************************/
	public BankLandingPage urlValidation(String screenname)
	{
		pause(1000);
		if(sessionExpired.getText().contains("Session has expired."))
			{
				flag=true;
				publishResults(flag, "user is not able to access the screen "+screenname, "User should not be able to access the screen "+screenname, "Verify user is not able to access the portal screen directly with url");
			}else
			{	flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is able to access the screen "+screenname, "User should not be able to access the screen "+screenname, "Verify user is not able to access the portal screen directly with url");
			}
		return this;
	}
	/*************************************************************************************
	 * Method Name: urlValidationNewTab
	 * Purpose:Method for validating the portal when we enter url in new tab in same browser (In progress)
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  12-Aug-2017
	/*************************************************************************************/
	public BankLandingPage urlValidationNewTab(String screenname) throws Exception
	{
		//assertTrue(isElementDisplayed(incomingDebits),"User logged in successfully");
		pause(1000);
		String oldTab = driver.getWindowHandle();
		/*Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_T);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_T);*/
		//driver.get("https://129.227.33.38/iPSL.AP.Web/Dashboard");
		String a = Keys.chord(Keys.CONTROL,"t");
		driver.findElement(By.tagName("body")).sendKeys(a);
		/*pause(1000);
		WebElement a = driver.findElement(By.xpath("//*[@id='uploading-url']/input"));
		a.sendKeys("https://129.227.33.38/iPSL.AP.Web/Dashboard");
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);*/
		
		//ArrayList<String> newTab = new ArrayList<String> (driver.getWindowHandles());
		//Iterator<String> newTab = driver.getWindowHandles().iterator();
		//String aa = driver.getWindowHandle();
		//newTab.navigate().to("https://129.227.33.38/iPSL.AP.Web/Dashboard");
		//driver.switchTo().window(newTab.get(1));
		//driver.switchTo().window(driver.getWindowHandles().iterator().next());
		driver.get("https://129.227.33.38/iPSL.AP.Web/Dashboard");
		
		if(isElementDisplayed(incomingDebits) == false)
			{
				flag=true;
				publishResults(flag, "user is not able to access the screen "+screenname, "User should not be able to access the screen "+screenname, "Verify user is not able to access the portal screen directly with url");
			}else
			{	flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is able to access the screen "+screenname, "User should not be able to access the screen "+screenname, "Verify user is not able to access the portal screen directly with url");
			}
		
		driver.quit();
		startDriver();
		return this;
	}

	/*************************************************************************************
	 * Method Name: fillFieldsWithValueFromTable
	 * Purpose:Method for fetching values from table and validating
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  12-Aug-2017
	/*************************************************************************************/
	public static String fillFieldsWithValueFromTable()
	{
		//Boolean fieldFlag = true;
		String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		fillTextBox(txtDebitsaccount,accno);
		String accno1 = tblAccount2.getText();
		String serial = tblSerial.getText();
		fillTextBox(txtSerial,serial);
		String sortCodeFrom = tblSortcode.getText();
		fillTextBox(txtSort,sortCodeFrom);
		String sortCodeTo = tblSortcode.getText();
		int sortCodeTo1 = Integer.parseInt(sortCodeTo);
		sortCodeTo1 = sortCodeTo1+1;
		sortCodeTo = Integer.toString(sortCodeTo1);
		String fromAmount= getAgencyValueFromDataSheet("filterFromAmount");
		String toAmount= getAgencyValueFromDataSheet("filterToAmount");
		fillTextBox(txtSortTo,sortCodeTo);
		fillTextBox(txtDebitsAmount,fromAmount);
		fillTextBox(txtDebitsAmountTo,toAmount);
		clickElement(btnApplyFilters);
		pause(2000);	
		
		/*if (!(txtDebitsaccount.getText().isEmpty()) && !(txtSerial.getText().isEmpty())
				&& !(txtSort.getText().isEmpty()) && !(txtSortTo.getText().isEmpty())
				&& !(txtDebitsAmount.getText().isEmpty()) && !(txtDebitsAmountTo.getText().isEmpty())){
			fieldFlag = false;		
		}*/
		if(lblShowCurrentFilterStatus.getText().contains("Filtered :Item Status:All Items , Sort Code : "+sortCodeFrom+" , To : "+sortCodeTo+" , Account : "+accno+" , Serial : "+serial+" , Amount : £"+fromAmount+" , To : £"+toAmount) )				
		{
			flag=true;
			publishResults(flag, "Initial filters applied as expected", "Filter values entered should be applied", "user should be able to view applied filters");
		}
		assertTrue(flag, "Initial Filters are applied on the as expected");	
		return accno1;
	}
	/*************************************************************************************
	 * Method Name: verifySessionExpiry
	 * Purpose:Method for validating the session expiry scenario after waiting for 1 minute (configurable in DB)
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  16-Aug-2017
	/*************************************************************************************/
	
	public BankLandingPage verifySessionExpiry() 
	{	
		pause(60000);
		clickElement(Dashboard);	
		if(sessionExpired.getText().contains("Session has expired."))
		{
			flag=true;
			publishResults(flag, "Session has expired", "Session expiry message should be displayed", "Verify session expiry message is displayed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Session not expired", "Session expiry message is not displayed", "Verify session expiry message is displayed");
		}
		return new BankLandingPage(driver);
	}
	/*************************************************************************************
	 * Method Name: verifyLogout
	 * Purpose:logout of the portal successfully
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  07-Sep-2017
	/*************************************************************************************/
	
	public BankLandingPage verifyLogout() 
	{	
		driver.switchTo().defaultContent();
		clickElement(logoutPortal);	
		if(btnLoginPortal.isDisplayed())
		{
			flag=true;
			publishResults(flag, "Logout successful", "user should be logged out successfully", "Verify user is logged out,clicking logout link");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Logout not successful", "user should be logged out successfully", "Verify user is logged out,clicking logout link");
		}
		return new BankLandingPage(driver);
	}
	
	/*************************************************************************************
	 * Method Name: validateDuplicateImageData
	 * Purpose:Method for validating duplicate image data
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  02-oct-2017
	/*************************************************************************************/	
	public BankLandingPage validateDuplicateImageData()
	{	
		clickElement(imgImage);		
		pause(3000);	
		if(isElementDisplayed(imgFrontView))
		{
			clickElement(btnView);
			pause(2000);	
			flag=true;
			publishResults(flag, "original Image is displayed ", "original Image is displayed ", "Verify Image is dispalyed with view button enabled");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "original Image is displayed", "original Image is not displayed ", "Verify Image is dispalyed with view button enabled");
		}
		if(isElementDisplayed(imgDuplicateOneFrontView) && isElementDisplayed(imgDuplicateOneRearView)
				&& isElementDisplayed(imgDuplicateTwoFrontView) && isElementDisplayed(imgDuplicateTwoRearView))
		{
			flag=true;
			publishResults(flag, "duplicate image should be displayed", "duplicate image is displayed", "Verify duplicate image is displayed when view button is clicked");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "duplicate image should be displayed", "duplicate image is not displayed", "Verify duplicate image is displayed when view button is clicked");
		}
		assertTrue(flag, "duplicate image data not displayed as expected");	
		return this;
	}
	/*************************************************************************************
	 * Method Name: verifyDropdownValues
	 * Purpose:Method for validating dropdown items are not present
	 * Author: 
	 * Reviewed by : 
	 * Created Date:  12-Oct-2017
	/*************************************************************************************/		
	public BankLandingPage verifyValueNotPresent(String value)
	{	
		flag = true;
	     Select dropdown = new Select (lblShowCurrentFilterStatus1);
			int totalValues =dropdown.getOptions().size();
			for(int i=0;i<(totalValues-1);i++){
				String statusValue=dropdown.getOptions().get(i).getText().trim();
				if (statusValue.contains(value)){
					flag= false;
					finalTestScriptResultFailFlag = true;
					publishResults(false,value+" is present", value+" is should not be present", "Verify "+value+" is not present in the dropdown");
				}			
			}
			if (flag = true )
			{
				publishResults(true,value+" is not present", value+" is should not be present", "Verify "+value+" is not present in the dropdown");
			}
		assertTrue(flag, "verify the value is not present in the dropdown");
		return this;
	}
	
	
	/*************************************************************************************
	 * Method Name: verifyLandingPage
	 * Purpose:Method for validating the default landing page for different roles
	 * Author: 
	 * Reviewed by : 
	 * Created Date:  19-oct-2017
	/*************************************************************************************/	
	public BankLandingPage assertLandingPage(String TabName)
	{
		validationStepInformation("Verify the default landing page for the User Role");
		flag =false;
	
		if ( TabName.contains("Dashboard")){
			if (isElementDisplayed(btnRefresh)){
				flag=true;
			}
		}
			else if ( TabName.contains("Search")){				
				if (isElementDisplayed(btnSearch)){
					flag=true;
				}
			}
			else if ( TabName.contains("ActivityLog")){
				if (isElementDisplayed(lstActionList)){
						flag=true;
					}
			}
			else if ( TabName.contains("UploadDownload")){						
				if (isElementDisplayed(download)){
							flag=true;
			}
			}
		
		if (flag == true)
		{
			publishResults(true, "Default landing page displayed as exepected", "Default landing page should be displayed", "Verify default landing page is"+TabName);
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(false,"Default landing page displayed as exepected",  "Default landing page should be displayed", "Verify default landing page is"+TabName);
		}
		assertTrue(flag, "verify default landing page is displayed as expected");
		return this;
	}	
}

