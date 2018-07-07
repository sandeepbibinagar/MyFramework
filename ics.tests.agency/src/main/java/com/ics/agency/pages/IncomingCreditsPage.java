package com.ics.agency.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import com.relevantcodes.extentreports.ExtentTest;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;

public class IncomingCreditsPage extends ICSPageUtilis {

	@FindBy(id = "FrmAgency")
	private static WebElement agencyFrame;

	@FindBy(id = "liExceptionItems")
	private static WebElement exceptions3;

	@FindBy(id = "liIncCredits")
	private static WebElement incomingCredits;

	@FindBy(id = "liAllCollectedItems")
	private static WebElement allCollectedItems;

	@FindBy(id="liCollectedElseWhere")
	private static WebElement collectedElseWhere;

	@FindBy(id="incomingCreditsCollElseWhereGrid")
	private static WebElement tblIncomingCreditsCollElse ;

	@FindBy(xpath="//div[@class='dataTables_scrollHeadInner']/table/thead/tbody/tr[2]/td[11]/select")
	private static List<WebElement> tableElement_column1;

	@FindBy(xpath="//th[@aria-controls='incomingCreditsExceptionItemsGrid_grid']")
	private static List<WebElement> tableElement_column2 ;

	@FindBy(id = "liIncDebits")
	private static WebElement incomingDebits;

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

	@FindBy(id = "btnClearCurrentFilters")
	private static WebElement btnClearCurrentFilters;

	@FindBy(id = "lblShowStatusExceptionItems")
	private static WebElement lblShowStatusExceptionItems;

	@FindBy(id = "incomingCreditsCollElseWhereGrid_grid_info")
	private static WebElement lblShowStatusCollectedItems;

	@FindBy(id = "incomingCreditsAllItemsGrid_grid_info")
	private static WebElement lblShowStatusAllCollectedItems;

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

	@FindBy(xpath="//*[@class='imgInlineImgClass fa fa-up-arrow']")
	private static WebElement imgImageCollapse;

	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[2]/td[9]/i")
	private static WebElement imgKappa;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[2]/td[9]/span")
	private static WebElement imgKappaTooltip;

	@FindBy(xpath="//*[@class='trKappa']")
	private static WebElement kappaData;

	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[3]")
	private static WebElement kappaImage;

	@FindBy(xpath="//*[@class='imgInlineKappaClass fa fa-up-arrow']")
	private static WebElement imgKappaDataCollapse;

	@FindBy(xpath="//*[@class='imgInlineImgClass fa fa-up-arrow']")
	private static WebElement imgKappaImageCollapse;

	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[2]/td[10]/i")
	private static WebElement imgOther;

	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[2]/td[10]/span")
	private static WebElement imgOtherTooltip;
	
	@FindBy(xpath="//*[@class='trOtherInfo']")
	private static WebElement otherExpander;

	@FindBy(xpath="//*[@class='fa fa-up-arrow imgInlineOthersClass']")
	private static WebElement imgOtherCollapse;

	@FindBy(id = "btnRefreshAllCollItems")
	private static WebElement btnRefreshAllCollItems;
	
	@FindBy(id = "btnRefreshColElsWhr")
	private static WebElement btnRefreshCollElsewhere;
	
	@FindBy(id = "btnRefreshExceptionItems")
	private static WebElement btnRefreshException;

	@FindBy(id = "lblShowStatusAllCollItems")
	private static WebElement lblShowCurrentFilterAllColl;
	
	@FindBy(id = "lblShowStatusCollElseWhere")
	private static WebElement lblShowCurrentFilterCollElsewhere;
	
	@FindBy(id = "lblShowStatusExceptionItems")
	private static WebElement lblShowCurrentFilterExcecption;

	@FindBy(id = "iHelp")
	private static WebElement helpIcon;

	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[5]")
	private static WebElement AccountSort;

	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[2]")
	private static WebElement StatusSort;

	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[3]")
	private static WebElement SerialSort;

	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[4]")
	private static WebElement SortCodeSort;

	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[6]")
	private static WebElement AmountSort;

	@FindBy(xpath = "//*[@class='dataTables_scrollHeadInner']/table/thead/tr/th[11]")
	private static WebElement DefaultSort;

	@FindBy(id = "StatusAllCollItems")
	private static WebElement CollItemsCreditsStatus;

	@FindBy(id = "StatusCollElseWhere")
	private static WebElement CollElsewhereCreditsStatus;

	@FindBy(id = "incomingCreditsAllItemsGrid_grid")
	private static WebElement tblIncomingCreditsCollItems;

	@FindBy(id = "incomingCreditsCollElseWhereGrid_grid")
	private static WebElement tblIncomingCreditsCollElsewhere;

	@FindBy(id = "incomingCreditsExceptionItemsGrid_grid")
	private static WebElement tblIncomingCreditsException;

	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[1]/td[2]/i")
	private static WebElement firstTsetdownArrow;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[1]/td[2]/span")
	private static WebElement firstTsetdownArrowTooltip;

	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[2]/td[11]")
	private static WebElement firstTsetResponseStatus;

	@FindBy(id = "btnApplyFiltersAllCollItems")
	private static WebElement btnApplyFiltersCollItems;

	@FindBy(id = "btnApplyFiltersCollElseWhere")
	private static WebElement btnApplyFiltersCollElsewhere;

	@FindBy(id = "incomingCreditsAllItemsGrid_grid_info")
	private static WebElement lblFilterPageDetailsAllItems;

	@FindBy(id = "incomingCreditsAllItemsGrid_grid_previous")
	private static WebElement paginationPreviousAllItems;

	@FindBy(id = "incomingCreditsAllItemsGrid_grid_next")
	private static WebElement paginationNextAllItems;
	
	@FindBy(xpath ="//div[@id='incomingCreditsAllItemsGrid_grid_paginate']/ul/li[2]")
	private static WebElement pageOneAllItems;
	
	@FindBy(xpath ="//div[@id='incomingCreditsAllItemsGrid_grid_paginate']/ul/li[3]")
	private static WebElement pageTwoAllItems;

	@FindBy(id = "incomingCreditsCollElseWhereGrid_grid_info")
	private static WebElement lblFilterPageDetailsCollElsewhere;

	@FindBy(id = "incomingCreditsCollElseWhereGrid_grid_previous")
	private static WebElement paginationPreviousCollElsewhere;

	@FindBy(id = "incomingCreditsCollElseWhereGrid_grid_next")
	private static WebElement paginationNextCollElsewhere;
	
	@FindBy(xpath ="//div[@id='incomingCreditsCollElseWhereGrid_grid_paginate']/ul/li[2]")
	private static WebElement pageOneCollElsewhere;
	
	@FindBy(xpath ="//div[@id='incomingCreditsCollElseWhereGrid_grid_paginate']/ul/li[3]")
	private static WebElement pageTwoCollElsewhere;

	@FindBy(id = "incomingCreditsExceptionItemsGrid_grid_info")
	private static WebElement lblFilterPageDetailsException;

	@FindBy(id = "incomingCreditsExceptionItemsGrid_grid_previous")
	private static WebElement paginationPreviousException;

	@FindBy(id = "incomingCreditsExceptionItemsGrid_grid_next")
	private static WebElement paginationNextException;
	
	@FindBy(xpath ="//div[@id='incomingCreditsExceptionItemsGrid_grid_paginate']/ul/li[2]")
	private static WebElement pageOneException;
	
	@FindBy(xpath ="//div[@id='incomingCreditsExceptionItemsGrid_grid_paginate']/ul/li[3]")
	private static WebElement pageTwoException;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[1]/td[2]")
	private static WebElement firstTset;
		
	@FindBy(xpath="//*[@id='incomingCreditsAllItemsGrid_grid']/tbody/tr[1]/td[7]")
	private static WebElement firstTsetAmountAllItems;
	
	@FindBy(xpath="//*[@id='incomingCreditsCollElseWhereGrid_grid']/tbody/tr[1]/td[7]")
	private static WebElement firstTsetAmountCollElsewhere;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[2]/td[8]/i")
	private static WebElement imgImageCollItems;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[2]/td[8]/span")
	private static WebElement imgImageCollItemsTooltip;
	
	@FindBy(xpath="//*[@id='incomingCreditsExceptionItemsGrid_grid']/tbody/tr[1]/td[9]/i")
	private static WebElement imgImageException;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[3]/td/div/div/div")
	private static WebElement imgFrontViewCollItems;

	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[3]/td[2]/div/div")
	private static WebElement imgRearViewCollItems;
	
	@FindBy(xpath="//*[@id='incomingCreditsExceptionItemsGrid_grid']/tbody/tr[2]/td/div/div/div")
	private static WebElement imgFrontViewException;

	@FindBy(xpath="//*[@id='incomingCreditsExceptionItemsGrid_grid']/tbody/tr[2]/td[2]/div/div/div")
	private static WebElement imgRearViewException;
	
	@FindBy(xpath="//*[@class='fa fa-up-arrow fa-2x imgInlineImgClass clsImgArrow css-for-imgfafa AGYtooltip']")
	private static WebElement imgImageCollapseCollItems;

	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[2]/td[9]/i")
	private static WebElement imgKappaCollItems;
	
	@FindBy(xpath="//*[@id='incomingCreditsExceptionItemsGrid_grid']/tbody/tr[1]/td[10]/i")
	private static WebElement imgKappaException;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[4]")
	//@FindBy(xpath="//*[@class='trKappaInfo css-for-OtherInfo']")
	private static WebElement kappaDataCollItems;

	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[5]")
	//@FindBy(xpath="//*[@class='trImages css-for-img']")	
	private static WebElement kappaImageCollItems;
	
	@FindBy(xpath="//*[@class='imgInlineKappaClass fa fa-up-arrow fa-2x css-for-imgCollapse AGYtooltip']")
	private static WebElement imgKappaDataCollapseCollItems;

	@FindBy(xpath="//*[@class='imgInlineKappaClass fa fa-up-arrow fa-2x css-for-imgCollapse AGYtooltip']")
	private static WebElement imgKappaImageCollapseCollItems;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[2]/td[10]/i")
	private static WebElement imgOtherCollItems;
	
	@FindBy(xpath="//*[@id='incomingCreditsExceptionItemsGrid_grid']/tbody/tr[1]/td[11]/i")
	private static WebElement imgOtherException;

	@FindBy(xpath="//*[@id='incomingCreditsAllItemsGrid_grid']/tbody/tr[6]")
	//@FindBy(xpath="//*[@class='trOtherInfo form-grd-img']")
	private static WebElement otherExpanderCollItems;
	
	@FindBy(xpath="//*[@id='incomingCreditsCollElseWhereGrid_grid']/tbody/tr[6]")
	private static WebElement otherExpanderCollElsewhere;
		
	@FindBy(xpath="//*[@class='fa fa-up-arrow fa-2x imgInlineOthersClass clsImgArrow css-for-imgfafa AGYtooltip']")
	private static WebElement imgOtherCollapseCollItems;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[2]/td/ul")
	private static WebElement txtotherInfoException;
	
	@FindBy(xpath="//*[@class='dataTables_scrollBody']/table/tbody/tr[3]/td/ul")
	private static WebElement txtotherInfo;
	
	@FindBy(xpath="//*[@class='clsAccount']")
	private static WebElement tblAccount;
	
	@FindBy(xpath="//*[@class='incomingCreditsExceptionItemsGrid_grid']/tbody/tr[1]/td[3]")
	private static WebElement tblCrAccountException;
	
	@FindBy(xpath="(//*[@class='clsAccount'])[2]")
	private static WebElement tblAccount2;
	
	@FindBy(xpath="//*[@class='clsSerial']")
	private static WebElement tblSerial;
	
	@FindBy(xpath="//*[@class='clsSortCode']")
	private static WebElement tblSortcode;
	
	@FindBy(xpath="//*[@class='clsSortCode form-grd-col-sort']")
	private static WebElement tblSortcodeException;
	
	@FindBy(xpath="//*[@id='incomingCreditsExceptionItemsGrid_grid']/tbody/tr[1]/td[3]")
	private static WebElement tblAccountException;
	
	@FindBy(id = "imgFilterExpandCollapseCollElseWhere")
	private static WebElement btnFilterCollElsewhere;
	
	@FindBy(id = "imgFilterExpandCollapseAllCollItems")
	private static WebElement btnFilterCollItems;
	
	@FindBy(xpath="//*[@class='row form-div-row CM-DIV-R']/div[2]/span")
	private static WebElement btnFilterCollItemsTooltip;
		
	@FindBy(className = "bootbox-body")
	private static WebElement dlgErrorMessage;
	
	@FindBy(xpath="//*[@class='bootbox modal fade bootbox-alert in']/div/div/div/button")
	private static WebElement btnOk;
	
	@FindBy(id = "btnCreatePdf")
	private static WebElement btnCreatePdf;
	
	@FindBy(id = "dvMultSelect")
	private static WebElement multiSelectDownloadTab;	

	protected static ExtentTest EXTENTLOG;
	protected static boolean flag=false;
	private static String assertFailLogForIncomingTabAvailable = "Incoming Credits Screen tab is not available";

	public IncomingCreditsPage(WebDriver driver) {
		super(driver);		
	}

	public IncomingCreditsPage(WebDriver driver, String url) {
		super(driver, url);		
	}

	public IncomingCreditsPage switchToAgencyFrame()
	{		
		frameSwitch(agencyFrame);
		return new IncomingCreditsPage(driver);
	}

	public static boolean returnResultFlag()
	{

		return IncomingCreditsPage.flag;
	}
	/*************************************************************************************
	 * Method Name: assertselectStatusFieldsinCreditsTab
	 * Purpose:Method for filtering and validating the different status in Incoming Credits tab
	 * (collectedByMe ,Collected Elsewhere, Exceptions Tab) 
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  10-May-2017
	/*************************************************************************************/
	
	public IncomingCreditsPage assertselectStatusFieldsinCreditsTab(String tabName)
	{
		boolean flag1=false;
		validationStepInformation("Check Select Status Fields In Credits Tab");
		pause(1000);
		if (tabName == ICSPropertiesConfig.getCollectedItemsTab()){
			selectDropdownValue(driver, CollItemsCreditsStatus, "Pay");	
			flag = validateStatusColumnValue ("Paid",tabName);	
			flag1 = lblShowCurrentFilterAllColl.getText().contains("Pay Filtered:");
		}
		else if (tabName == ICSPropertiesConfig.getCollectedElsewhereTab()){
			selectDropdownValue(driver, CollElsewhereCreditsStatus, "Pay");
			flag = validateStatusColumnValue ("Paid",tabName);	
			flag1 = lblShowCurrentFilterCollElsewhere.getText().contains("Pay Filtered:");
		}
		
		if ((flag1=true) && (flag=true))
		{
			publishResults(flag, "Pay status filter applied as expected", "Pay status filter should be applied", "user should be able to view items in Pay status");		
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Pay status filter not applied as expected", "Pay status filter should be applied", "user should be able to view items in Pay status");				
		}
		assertTrue(flag, "Pay status are not applied"); 
		if (tabName == ICSPropertiesConfig.getCollectedItemsTab() ){
			selectDropdownValue(driver, CollItemsCreditsStatus, "NoPay");
			flag = validateStatusColumnValue ("Not Paid",tabName);
			flag1=lblShowCurrentFilterAllColl.getText().contains("NoPay Filtered:");
		}
		else if (tabName == ICSPropertiesConfig.getCollectedElsewhereTab()){
			selectDropdownValue(driver, CollElsewhereCreditsStatus, "NoPay");
			flag = validateStatusColumnValue ("Not Paid",tabName);
			lblShowCurrentFilterCollElsewhere.getText().contains("NoPay Filtered:");
		}
		
		if ((flag1=true) && (flag=true))
		{	
			publishResults(flag, "No Pay status filter applied as expected", "No Pay status filter should be applied", "user should be able to view items in No Pay status");			
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "No Pay status filter not applied as expected", "No Pay status filter should be applied", "user should be able to view items in No Pay status");					
		}
		assertTrue(flag, "No Pay status are not applied");

		/*if (tabName == ICSPropertiesConfig.getCollectedItemsTab() ){
			selectDropdownValue(driver, CollItemsCreditsStatus, "Bank Holiday");
			flag = validateStatusColumnValue ("Bank Holiday",tabName);
			flag1=lblShowCurrentFilterAllColl.getText().contains("Bank Holiday Filtered:");
		}
		else if (tabName == ICSPropertiesConfig.getCollectedElsewhereTab()){
			selectDropdownValue(driver, CollElsewhereCreditsStatus, "Bank Holiday");
			flag = validateStatusColumnValue ("Bank Holiday",tabName);
			flag1=lblShowCurrentFilterCollElsewhere.getText().contains("Bank Holiday Filtered:");
		}

		
		if ((flag1=true) && (flag=true))
		{
			publishResults(flag, "Bank Holiday status filter applied as expected", "Bank Holiday status filter should be applied", "user should be able to view items in Bank Holiday status");					
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Bank Holiday status filter not applied as expected", "Bank Holiday status filter should be applied", "user should be able to view items in Bank Holiday status");							
		}
		assertTrue(flag, "Bank Holiday status are not applied");	*/		

		/*if (tabName == ICSPropertiesConfig.getCollectedItemsTab() ){
			selectDropdownValue(driver, CollItemsCreditsStatus, "Holdover");
			flag = validateStatusColumnValue ("Hold Over",tabName);
			flag1=lblShowCurrentFilterAllColl.getText().contains("Holdover Filtered:");
		}
		else if (tabName == ICSPropertiesConfig.getCollectedElsewhereTab()){
			selectDropdownValue(driver, CollElsewhereCreditsStatus, "Holdover");
			flag = validateStatusColumnValue ("Hold Over",tabName);
			flag1=lblShowCurrentFilterCollElsewhere.getText().contains("Holdover Filtered:");
		}
		
		if ((flag1=true) && (flag=true))
		{	
			publishResults(flag, "Holdover status filter applied as expected", "Holdover status filter should be applied", "user should be able to view items in Holdover status");							
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Holdover status filter not applied as expected", "Holdover status filter should be applied", "user should be able to view items in Holdover status");									
		}
		assertTrue(flag, "Holdover status are not applied");

		if (tabName == ICSPropertiesConfig.getCollectedItemsTab() ){
			selectDropdownValue(driver, CollItemsCreditsStatus, "Bank-No Reponse");
			flag = validateStatusColumnValue ("",tabName);
			flag1=lblShowCurrentFilterAllColl.getText().contains("Bank-No Reponse Filtered:");
		}
		else if (tabName == ICSPropertiesConfig.getCollectedElsewhereTab()){
			selectDropdownValue(driver, CollElsewhereCreditsStatus, "Bank-No Reponse");
			flag = validateStatusColumnValue ("",tabName);
			flag1=lblShowCurrentFilterCollElsewhere.getText().contains("Bank-No Reponse Filtered:");
		}

		
		if ((flag1=true) && (flag=true))
		{
			publishResults(flag, "Bank-No Reponse status filter applied as expected", "Bank-No Reponse status filter should be applied", "user should be able to view items in Bank-No Reponse status");

		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Bank-No Reponse status filter not applied as expected", "Bank-No Reponse status filter should be applied", "user should be able to view items in Bank-No Reponse status");								
		}
		assertTrue(flag, "Bank-No Reponse status are not applied");

		if (tabName == ICSPropertiesConfig.getCollectedItemsTab() ){
			selectDropdownValue(driver, CollItemsCreditsStatus, "All Items");
			clickElement(btnApplyFiltersCollItems);
			if ( lblShowCurrentFilterAllColl.getText().contains("All Items Filtered:") && tblIncomingCreditsCollItems.findElements(By.tagName("tr")).size() > 1 )
			{
				flag=true;	
				publishResults(flag, "All Items status filter applied as expected", "All Items status filter should be applied", "user should be able to view items in all status");							
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "All Items status filter not applied as expected", "All Items status filter should be applied", "user should be able to view items in all status");							
			}
		}
		else if (tabName == ICSPropertiesConfig.getCollectedElsewhereTab()){
			selectDropdownValue(driver, CollElsewhereCreditsStatus, "All Items");
			clickElement(btnApplyFiltersCollElsewhere);
			if ( lblShowCurrentFilterCollElsewhere.getText().contains("All Items Filtered:") && tblIncomingCreditsCollElsewhere.findElements(By.tagName("tr")).size() > 1 )
			{
				flag=true;	
				publishResults(flag, "All Items status filter applied as expected", "All Items status filter should be applied", "user should be able to view items in all status");							
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "All Items status filter not applied as expected", "All Items status filter should be applied", "user should be able to view items in all status");							
			}
		}	
		assertTrue(flag, "All items status are not applied");	*/		
		return null;
	}
	/*************************************************************************************
	 * Method Name: validateStatusColumnValue
	 * Purpose:Method for validating the table for the filtered status value passed
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  10-May-2017
	/*************************************************************************************/
	
	public  boolean validateStatusColumnValue(String statusValue ,String tabName ){

		flag=false;
		ArrayList<WebElement> rowcount = new ArrayList<>();
		if (tabName == ICSPropertiesConfig.getCollectedItemsTab() ){
			clickElement(btnApplyFiltersCollItems);
			clickElement(firstTsetdownArrow);
			rowcount=(ArrayList<WebElement>) tblIncomingCreditsCollItems.findElements(By.tagName("tr"));		
		}else if (tabName == ICSPropertiesConfig.getCollectedElsewhereTab()){
			clickElement(btnApplyFiltersCollElsewhere);
			clickElement(firstTsetdownArrow);
			rowcount=(ArrayList<WebElement>) tblIncomingCreditsCollElsewhere.findElements(By.tagName("tr"));		
		}
		int rowindex = 1;
		for (WebElement rowElement:rowcount){
			ArrayList<String> rowData = new ArrayList<>();
			rowData.add(rowElement.getText());
			System.out.println(rowData);
			if (rowData.get(0).contains("Credit") && rowData.get(0).contains(statusValue)){
				flag =true;
			}
			if (flag){
				break;
			}	
			rowindex = rowindex+1;
		}
		return flag;
	}
	/*************************************************************************************
	 * Method Name: refreshDetailsAndVerify
	 * Purpose:Method for validating the table and filter banner when Refresh button is clicked
	 * (collectedByMe ,Collected Elsewhere, Exceptions Tab) 
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  11-May-2017
	/*************************************************************************************/
	
	public IncomingCreditsPage refreshDetailsAndVerify(String tabName)
	{	
		String resultsTable=null;
		String currentFilterLabel = null;
		if (tabName == ICSPropertiesConfig.getCollectedItemsTab()){
			fillTextBox(txtAccountAllCollItems, getAgencyValueFromDataSheet("collectedByMeAccount"));
			fillTextBox(txtSerialAllCollItems,"");
			fillTextBox(txtSortCodeAllCollItems,"");
			fillTextBox(txtAmountAllCollItems,"");
			fillTextBox(txtSortCodeToAllCollItems,"");
			fillTextBox(txtAmountToAllCollItems,"");
			clickElement(btnRefreshAllCollItems);
			pause(1000);
			clickElement(firstTsetdownArrow);
			resultsTable=tblIncomingCreditsCollItems.getText();
			currentFilterLabel = lblShowCurrentFilterAllColl.getText();
		}
		else if (tabName == ICSPropertiesConfig.getCollectedElsewhereTab()){
			fillTextBox(txtAccountCollElseWhere,getAgencyValueFromDataSheet("collectedElsewhereAccount"));
			fillTextBox(txtSerialCollElseWhere,"");
			fillTextBox(txtSrtCodeCollElseWhereTab,"");
			fillTextBox(txtSrtCodeToCollElseWhereTab,"");
			fillTextBox(txtAmountCollElseWhere,"");
			fillTextBox(txtAmountToCollElseWhere,"");
			clickElement(btnRefreshCollElsewhere);
			pause(1000);
			clickElement(firstTsetdownArrow);
			pause(1000);
			resultsTable=tblIncomingCreditsCollElsewhere.getText();
			currentFilterLabel = lblShowCurrentFilterCollElsewhere.getText();
		}
		else if (tabName == ICSPropertiesConfig.getExceptionsTab()){
			fillTextBox(txtAccountExceptionItems,getAgencyValueFromDataSheet("exceptionRefreshAccount"));
			fillTextBox(txtSerialExceptionItems,"");
			fillTextBox(txtSrtCodeExceptionsTab,"");
			fillTextBox(txtSrtCodeToExceptionsTab,"");
			fillTextBox(txtAmountExceptionItems,"");
			fillTextBox(txtAmountToExceptionItems,"");
			clickElement(btnRefreshException);
			pause(1000);
			resultsTable=tblIncomingCreditsException.getText();
			currentFilterLabel = lblShowCurrentFilterExcecption.getText();
		}

		if((currentFilterLabel.contains("Item Status:All Items , Account : "+getAgencyValueFromDataSheet("collectedByMeAccount")) && (resultsTable.contains(getAgencyValueFromDataSheet("collectedByMeAccount"))))
				|| (currentFilterLabel.contains("Item Status:All Items , Account : "+getAgencyValueFromDataSheet("collectedElsewhereAccount")) && (resultsTable.contains(getAgencyValueFromDataSheet("collectedElsewhereAccount"))))
				|| (currentFilterLabel.contains("Item Status:All Items , Account : "+getAgencyValueFromDataSheet("exceptionRefreshAccount")) && (resultsTable.contains(getAgencyValueFromDataSheet("exceptionRefreshAccount")))))
		{
			flag=true;
			publishResults(flag, "New account displayed after refresh", "New account should be updated after refresh", "user should be able to view refreshed value");

		}else
		{
			flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "New account details not displayed after refresh", "New account details should be updated after refresh", "user should be able to view refreshed value");

		}
		assertTrue(flag, "Data is not refreshed as expected");	
		return this;
	}
	/*************************************************************************************
	 * Method Name: assertDetailsFieldsinCreditsTab
	 * Purpose:Method for validating the different details fields in Incoming Credits tab
	 * (collectedByMe ,Collected Elsewhere, Exceptions Tab) 
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  12-May-2017
	/*************************************************************************************/
	
	public boolean assertDetailsFieldsinCollectedByMeTab() throws InterruptedException 
	{
		String resultsTable=null;
		clickElement(firstTsetdownArrow);
		String account = tblAccount.getText();	
		String serial = tblSerial.getText();	
		String sortCodeFrom = tblSortcode.getText();	
		int sortCodeTo1 = Integer.parseInt(sortCodeFrom);
		sortCodeTo1 = sortCodeTo1+1;
		String sortCodeTo = Integer.toString(sortCodeTo1);
			fillTextBox(txtAccountAllCollItems, account);
			clickElement(btnApplyFiltersCollItems);
			pause(1000);
			clickElement(firstTsetdownArrow);
			resultsTable=tblIncomingCreditsCollItems.getText();
			if(lblShowCurrentFilterAllColl.getText().contains(account) && (resultsTable.contains(account)))
			{
				flag=true;
				publishResults(flag, "user is able to view items matching the account filter", "user should able to view items matching the account filter", "Valid account number is entered and click on apply filter");

			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items matching the account filter", "user should able to view items matching the account filter", "Valid account number is entered and click on apply filter");
			}
			assertTrue(flag, "Account filters are applied debits tab");
			
			fillTextBox(txtSerialAllCollItems,serial);
			clickElement(btnApplyFiltersCollItems);
			pause(1000);
			clickElement(firstTsetdownArrow);
			resultsTable=tblIncomingCreditsCollItems.getText();
			if(lblShowCurrentFilterAllColl.getText().contains(account) && lblShowCurrentFilterAllColl.getText().contains(serial)
					&& (resultsTable.contains(account) && (resultsTable.contains(serial))))
			{ 
				flag=true;	
				publishResults(flag, "user is able to view items matching the account and serial filter", "user should able to view items matching the account and serial filter", "Valid account number and Serial code is entered and click on apply filter");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items matching the account and serial filter", "user should able to view items matching the account and serial filter", "Valid account number and Serial code is entered and click on apply filter");
			}
			assertTrue(flag, "Serial and account filters are applied debits tab");	
			clickElementEnter(btnClearCurrentFilters);
			String fromAmount = getAgencyValueFromDataSheet("incomingCreditFromAmount");
			String toAmount = getAgencyValueFromDataSheet("incomingCreditToAmount");
			fillTextBox(txtAmountAllCollItems,fromAmount );
			fillTextBox(txtAmountToAllCollItems,toAmount);
			clickElement(btnApplyFiltersCollItems);
			pause(1000);
			clickElement(firstTsetdownArrow);
			resultsTable=tblIncomingCreditsCollItems.getText();
			if(lblShowCurrentFilterAllColl.getText().contains("Amount : £"+fromAmount+" , To : £"+toAmount))
			{ 
				flag=true;	
				publishResults(flag, "user is able to view items matching the amount filter", "user should able to view items matching the amount filter", "Valid amount range is entered and click on apply filter");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items matching the amount filter", "user should able to view items matching the amount filter", "Valid amount range is entered and click on apply filter");
			}
			assertTrue(flag, "Amount filters are applied debits tab");
			fillTextBox(txtSortCodeAllCollItems, sortCodeFrom);
			fillTextBox(txtSortCodeToAllCollItems,sortCodeTo);
			clickElement(btnApplyFiltersCollItems);
			pause(1000);
			clickElement(firstTsetdownArrow);
			resultsTable=tblIncomingCreditsCollItems.getText();
			if(lblShowCurrentFilterAllColl.getText().contains("Amount : £"+fromAmount+" , To : £"+toAmount) &&
					lblShowCurrentFilterAllColl.getText().contains(sortCodeFrom) && lblShowCurrentFilterAllColl.getText().contains(sortCodeTo) &&
					resultsTable.contains(sortCodeFrom))
			{ 
				flag=true;	
				publishResults(flag, "user is able to view items matching the amount and sort code filter", "user should able to view items matching the amount and sort code filter", "Valid amount range and sort code range is entered and click on apply filter");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items matching the amount and sort code filter", "user should able to view items matching the amount and sort code filter", "Valid amount range and sort code range is entered and click on apply filter");
			}
			assertTrue(flag, "Amount and Sort code filters are applied debits tab");	
		return flag;
	}
	/*************************************************************************************
	 * Method Name: assertDetailsFieldsinCollectedElsewhereTab
	 * Purpose:Method for validating the different details fields in Incoming Credits tab
	 * (Collected Elsewhere) 
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  12-May-2017
	/*************************************************************************************/
	
	public boolean assertDetailsFieldsinCollectedElsewhereTab() throws InterruptedException 
	{
		String resultsTable=null;
		clickElement(firstTsetdownArrow);
		pause(1000);
		String account = tblAccount.getText();	
		String serial = tblSerial.getText();	
		String sortCodeFrom = tblSortcode.getText();	
		int sortCodeTo1 = Integer.parseInt(sortCodeFrom);
		sortCodeTo1 = sortCodeTo1+1;
		String sortCodeTo = Integer.toString(sortCodeTo1);
			fillTextBox(txtAccountCollElseWhere, account);
			clickElementEnter(btnApplyFiltersCollElsewhere);
			pause(1000);
			clickElement(firstTsetdownArrow);
			pause(1000);
			resultsTable=tblIncomingCreditsCollElsewhere.getText();
			if(lblShowCurrentFilterCollElsewhere.getText().contains(account) && (resultsTable.contains(account)))
			{
				flag=true;
				publishResults(flag, "user is able to view items matching the account filter", "user should able to view items matching the account filter", "Valid account number is entered and click on apply filter");

			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items matching the account filter", "user should able to view items matching the account filter", "Valid account number is entered and click on apply filter");
			}
			assertTrue(flag, "Account filters are applied debits tab");
			fillTextBox(txtSerialCollElseWhere, serial);
			clickElementEnter(btnApplyFiltersCollElsewhere);
			pause(1000);
			clickElement(firstTsetdownArrow);
			pause(1000);
			resultsTable=tblIncomingCreditsCollElsewhere.getText();
			if(lblShowCurrentFilterCollElsewhere.getText().contains(account) && lblShowCurrentFilterCollElsewhere.getText().contains(serial)
					&& (resultsTable.contains(account) && (resultsTable.contains(serial))))
			{ 
				flag=true;	
				publishResults(flag, "user is able to view items matching the account and serial filter", "user should able to view items matching the account and serial filter", "Valid account number and Serial code is entered and click on apply filter");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items matching the account and serial filter", "user should able to view items matching the account and serial filter", "Valid account number and Serial code is entered and click on apply filter");
			}
			assertTrue(flag, "Serial and account filters are applied debits tab");	
			clickElementEnter(btnClearCurrentFilters);
			String fromAmount = getAgencyValueFromDataSheet("incomingCreditFromAmount");
			String toAmount = getAgencyValueFromDataSheet("incomingCreditToAmount");
			fillTextBox(txtAmountCollElseWhere,fromAmount );
			fillTextBox(txtAmountToCollElseWhere,toAmount );
			clickElementEnter(btnApplyFiltersCollElsewhere);
			pause(1000);
			clickElement(firstTsetdownArrow);
			pause(1000);
			resultsTable=tblIncomingCreditsCollElsewhere.getText();
			if(lblShowCurrentFilterCollElsewhere.getText().contains("Amount : £"+fromAmount+" , To : £"+toAmount))
			{ 
				flag=true;	
				publishResults(flag, "user is able to view items matching the amount filter", "user should able to view items matching the amount filter", "Valid amount range is entered and click on apply filter");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items matching the amount filter", "user should able to view items matching the amount filter", "Valid amount range is entered and click on apply filter");
			}
			assertTrue(flag, "Amount filters are applied debits tab");
			fillTextBox(txtSrtCodeCollElseWhereTab, sortCodeFrom);
			fillTextBox(txtSrtCodeToCollElseWhereTab, sortCodeTo);
			clickElementEnter(btnApplyFiltersCollElsewhere);
			pause(1000);
			clickElement(firstTsetdownArrow);
			pause(1000);
			resultsTable=tblIncomingCreditsCollElsewhere.getText();
			if(lblShowCurrentFilterCollElsewhere.getText().contains("Amount : £"+fromAmount+" , To : £"+toAmount) &&
					lblShowCurrentFilterCollElsewhere.getText().contains(sortCodeFrom) && lblShowCurrentFilterCollElsewhere.getText().contains(sortCodeTo) &&
					 (resultsTable.contains(sortCodeFrom)))
			{ 
				flag=true;	
				publishResults(flag, "user is able to view items matching the amount and sort code filter", "user should able to view items matching the amount and sort code filter", "Valid amount range and sort code range is entered and click on apply filter");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items matching the amount and sort code filter", "user should able to view items matching the amount and sort code filter", "Valid amount range and sort code range is entered and click on apply filter");
			}
			assertTrue(flag, "Amount and Sort code filters are applied debits tab");	
		return flag;
	}
	/*************************************************************************************
	 * Method Name: assertDetailsFieldsinExceptionTab
	 * Purpose:Method for validating the different details fields in Incoming Credits tab
	 * (Exception tab) 
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  12-May-2017
	/*************************************************************************************/
	public boolean assertDetailsFieldsinExceptionTab() throws InterruptedException 
	{
		String resultsTable=null;
		String account = tblAccountException.getText();		
		String serial = getAgencyValueFromDataSheet("exceptionSerial");
		String sortCodeFrom = tblSortcodeException.getText();	
		int sortCodeTo1 = Integer.parseInt(sortCodeFrom);
		sortCodeTo1 = sortCodeTo1+1;
		String sortCodeTo = Integer.toString(sortCodeTo1);
			fillTextBox(txtAccountExceptionItems, account);
			clickElement(btnApplyFiltersExceptionItems);
			resultsTable=tblIncomingCreditsException.getText();
			if(lblShowCurrentFilterExcecption.getText().contains(account) && (resultsTable.contains(account)))
			{
				flag=true;
				publishResults(flag, "user is able to view items matching the account filter", "user should able to view items matching the account filter", "Valid account number is entered and click on apply filter");

			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items matching the account filter", "user should able to view items matching the account filter", "Valid account number is entered and click on apply filter");
			}
			assertTrue(flag, "Account filters are applied debits tab");
			fillTextBox(txtSerialExceptionItems, serial);
			clickElement(btnApplyFiltersExceptionItems);
			resultsTable=tblIncomingCreditsException.getText();
			if(lblShowCurrentFilterExcecption.getText().contains(account) && lblShowCurrentFilterExcecption.getText().contains(serial)
					&& (resultsTable.contains(account)))
			{ 
				flag=true;	
				publishResults(flag, "user is able to view items matching the account and serial filter", "user should able to view items matching the account and serial filter", "Valid account number and Serial code is entered and click on apply filter");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items matching the account and serial filter", "user should able to view items matching the account and serial filter", "Valid account number and Serial code is entered and click on apply filter");
			}
			assertTrue(flag, "Serial and account filters are applied debits tab");	
			clickElement(btnClearCurrentFilters);
			String fromAmount = getAgencyValueFromDataSheet("incomingCreditFromAmount");
			String toAmount = getAgencyValueFromDataSheet("incomingCreditToAmount");
			fillTextBox(txtAmountExceptionItems, fromAmount);
			fillTextBox(txtAmountToExceptionItems,toAmount);
			clickElement(btnApplyFiltersExceptionItems);
			resultsTable=tblIncomingCreditsException.getText();
			if(lblShowCurrentFilterExcecption.getText().contains("Amount : £"+fromAmount+" , To : £"+toAmount))
			{ 
				flag=true;	
				publishResults(flag, "user is able to view items matching the amount filter", "user should able to view items matching the amount filter", "Valid amount range is entered and click on apply filter");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items matching the amount filter", "user should able to view items matching the amount filter", "Valid amount range is entered and click on apply filter");
			}
			assertTrue(flag, "Amount filters are applied debits tab");
			fillTextBox(txtSrtCodeExceptionsTab,sortCodeFrom);
			fillTextBox(txtSrtCodeToExceptionsTab, sortCodeTo);
			clickElement(btnApplyFiltersExceptionItems);
			resultsTable=tblIncomingCreditsException.getText();
			if(lblShowCurrentFilterExcecption.getText().contains("Amount : £"+fromAmount+" , To : £"+toAmount) &&
					lblShowCurrentFilterExcecption.getText().contains(sortCodeFrom) && lblShowCurrentFilterExcecption.getText().contains(sortCodeTo) 
					&& (resultsTable.contains(sortCodeFrom)))
			{ 
				flag=true;	
				publishResults(flag, "user is able to view items matching the amount and sort code filter", "user should able to view items matching the amount and sort code filter", "Valid amount range and sort code range is entered and click on apply filter");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items matching the amount and sort code filter", "user should able to view items matching the amount and sort code filter", "Valid amount range and sort code range is entered and click on apply filter");
			}
			assertTrue(flag, "Amount and Sort code filters are applied debits tab");	
		return flag;
	}
	/*************************************************************************************
	 * Method Name: validatePagination
	 * Purpose:Method for validating the Pagination for Incoming Credits Tab(collectedByMe ,Collected Elsewhere, Exceptions Tab) 
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  15-May-2017
	/*************************************************************************************/
	
	public IncomingCreditsPage validatePagination(String tabName)
	{
	if (tabName == ICSPropertiesConfig.getCollectedItemsTab()){	
		validationStepInformation("Initial Pagination details :");
		if(lblFilterPageDetailsAllItems.getText().contains("Showing 1 to 10 of") && (paginationPreviousAllItems.getAttribute("class").contains("paginate_button previous disabled")) && !(paginationNextAllItems.getAttribute("class").contains("paginate_button next disabled")) )
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
		clickElement(paginationNextAllItems);
		pause(2000);
		if(!(paginationPreviousAllItems.getAttribute("class").contains("paginate_button previous disabled")) &&
				 lblFilterPageDetailsAllItems.getText().contains("Showing 11 to") ){
			flag=true;
			publishResults(flag, "Pagination details when Next is clicked is displayed as expected ", "Pagination details when Next is clicked is displayed should be as expected", "Verify next page grid is displayed , previous button is Enabled");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Pagination details when Next is clicked is not displayed as expected ", "Pagination details when Next is clicked is displayed should be as expected", "Verify next page grid is displayed , previous button is Enabled");
		}
		assertTrue(flag, "Pagination details when Next is clicked is not dispalyed as expected");
		validationStepInformation("Click on Previous button Validation :");
		clickElement(paginationPreviousAllItems);
		pause(2000);
		if((paginationPreviousAllItems.getAttribute("class").contains("paginate_button previous disabled")) && !(paginationNextAllItems.getAttribute("class").contains("paginate_button next disabled")) 
				&& lblFilterPageDetailsAllItems.getText().contains("Showing 1 to 10 of") ){
			flag=true;
			publishResults(flag, "Pagination details when Previous is clicked is displayed as expected ", "Pagination details when Previous is clicked is displayed should be as expected", "Verify Previous page grid is displayed , previous button is Disabled");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Pagination details when Previous is clicked is not displayed as expected ", "Pagination details when Previous is clicked is displayed should be as expected", "Verify Previous page grid is displayed , previous button is Disabled");
		}
		assertTrue(flag, "Pagination details when Previous is clicked is not dispalyed as expected");
		List <WebElement> pagination = driver.findElements(By.xpath("//div[@id='incomingCreditsAllItemsGrid_grid_paginate']//ul//li"));

		if (pagination.size() > 2){
			publishResults(true, "Multiple pages are displayed", "Multiple pages should be displayed", "Verify more than one page is displayed");
		}
	
		clickElement(pageTwoAllItems);
		System.out.println("clicked on page no. 2");				 
		Boolean Page2Previous = verifyTrue(!(paginationPreviousAllItems.getAttribute("class").contains("paginate_button previous disabled")),"previous is enabled");				
		Boolean Page2Status = verifyTrue(lblFilterPageDetailsAllItems.getText().contains("Showing 11 to"),"status field updated accordingly");
		Boolean Page2State = verifyTrue((pageTwoAllItems.getAttribute("class").contains("paginate_button active")),"2 is enabled");
		Boolean Page1State = verifyTrue(!(pageOneAllItems.getAttribute("class").contains("paginate_button active")),"1 is enabled");

		if(Page2Previous==true && Page1State==true && Page2Status==true && Page2State==true){
			publishResults(flag, "Pagination details for page 2 is displayed as expected ", "Pagination details when page 2 is clicked should be as expected", "Verify page 2 is displayed , previous button is enabled with status messagae updated");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Pagination details for page 2 is not displayed as expected ", "Pagination details when page 2 is clicked should be as expected", "Verify page 2 is displayed , previous button is enabled with status messagae updated");
		}
		clickElement(pageOneAllItems);
		pause(2000);
		System.out.println("clicked on page no. 1");				 
		Boolean Page1Previous = verifyTrue((paginationPreviousAllItems.getAttribute("class").contains("paginate_button previous disabled")),"previous is disabled");
		Boolean Page1Next = verifyTrue(!(paginationNextAllItems.getAttribute("class").contains("paginate_button next disabled")),"next is enabled");
		Boolean Page1Status = verifyTrue(lblFilterPageDetailsAllItems.getText().contains("Showing 1 to 10 of"),"status field updated accordingly");
		Page1State = verifyTrue(pageOneAllItems.getAttribute("class").contains("paginate_button active"),"1 is active");			 
		Page2State = verifyTrue (!(pageTwoAllItems.getAttribute("class").contains("paginate_button active")),"2 is active");			 

		if(Page1Previous==true && Page1Next==true && Page1Status==true && Page1State==true && Page2State==true ){
			publishResults(flag, "Pagination details for page 1 is displayed as expected ", "Pagination details when page 1 is clicked should be as expected", "Verify page 1 is displayed , previous button is disabled with status messagae updated");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Pagination details for page 1 is not displayed as expected ", "Pagination details when page 1 is clicked should be as expected", "Verify page 1 is displayed , previous button is disabled with status messagae updated");
		}
		int lastPage = pagination.size()-1;
		driver.findElement(By.xpath("//div[@id='incomingCreditsAllItemsGrid_grid_paginate']//ul//li"+"["+lastPage+"]")).click();
		pause(3000);
		if(!(paginationPreviousAllItems.getAttribute("class").contains("paginate_button previous disabled")) && paginationNextAllItems.getAttribute("class").contains("paginate_button next disabled"))
				{
			flag=true;
			publishResults(flag, "Next button is disabled when the last page is clicked", "Next button should be disabled when the last page is clicked", "Verify next button is disabled , and previous button is Enabled");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Next button is not disabled when the last page is clicked", "Next button should be disabled when the last page is clicked", "Verify next button is disabled , and previous button is Enabled");
		}
		assertTrue(flag, "Pagination details when last page is clicked is not dispalyed as expected");	
	}
	else if (tabName == ICSPropertiesConfig.getCollectedElsewhereTab()){		
		validationStepInformation("Initial Pagination details CollectedElsewhere:");
		if(lblFilterPageDetailsCollElsewhere.getText().contains("Showing 1 to 10 of") && (paginationPreviousCollElsewhere.getAttribute("class").contains("paginate_button previous disabled")) 
				&& !(paginationNextCollElsewhere.getAttribute("class").contains("paginate_button next disabled")) )
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
		clickElement(paginationNextCollElsewhere);
		pause(2000);
		if(!(paginationPreviousCollElsewhere.getAttribute("class").contains("paginate_button previous disabled")) && (paginationNextCollElsewhere.getAttribute("class").contains("paginate_button next disabled")) 
				&& lblFilterPageDetailsCollElsewhere.getText().contains("Showing 11 to") ){
			flag=true;
			publishResults(true, "Pagination details when Next is clicked is displayed as expected ", "Pagination details when Next is clicked is displayed should be as expected", "Verify next page grid is displayed , previous button is Enabled");
		}else
		{	flag=false;
		finalTestScriptResultFailFlag = true;
		publishResults(false, "Pagination details when Next is clicked is not displayed as expected ", "Pagination details when Next is clicked is displayed should be as expected", "Verify next page grid is displayed , previous button is Enabled");
		}
		assertTrue(flag, "Pagination details when Next is clicked is not dispalyed as expected");
		validationStepInformation("Click on Previous button Validation :");
		clickElement(paginationPreviousCollElsewhere);
		pause(2000);
		if((paginationPreviousCollElsewhere.getAttribute("class").contains("paginate_button previous disabled")) && !(paginationNextCollElsewhere.getAttribute("class").contains("paginate_button next disabled")) 
				&& lblFilterPageDetailsCollElsewhere.getText().contains("Showing 1 to 10 of") ){
			flag=true;
			publishResults(flag, "Pagination details when Previous is clicked is displayed as expected ", "Pagination details when Previous is clicked is displayed should be as expected", "Verify Previous page grid is displayed , previous button is Disabled");
		}else
		{	flag=false;
		finalTestScriptResultFailFlag = true;
		publishResults(flag, "Pagination details when Previous is clicked is not displayed as expected ", "Pagination details when Previous is clicked is displayed should be as expected", "Verify Previous page grid is displayed , previous button is Disabled");
		}
		assertTrue(flag, "Pagination details when Previous is clicked is not dispalyed as expected");
		List <WebElement> pagination = driver.findElements(By.xpath("//div[@id='incomingCreditsCollElseWhereGrid_grid_paginate']/ul/li"));
		System.out.println(pagination.size());

		if (pagination.size() > 2){
			flag=true;
			publishResults(true, "Multiple pages are displayed", "Multiple pages should be displayed", "Verify more than one page is displayed");
		}
		clickElement(pageTwoCollElsewhere);

		System.out.println("clicked on page no. 2");				 
		Boolean Page2Previous = verifyTrue(!(paginationPreviousCollElsewhere.getAttribute("class").contains("paginate_button previous disabled")),"previous is enabled");				
		Boolean Page2Status = verifyTrue(lblFilterPageDetailsCollElsewhere.getText().contains("Showing 11 to"),"status field updated accordingly");
		Boolean Page2State = verifyTrue((pageTwoCollElsewhere.getAttribute("class").contains("paginate_button active")),"2 is enabled");
		Boolean Page1State = verifyTrue(!(pageOneCollElsewhere.getAttribute("class").contains("paginate_button active")),"2 is enabled");

		if(Page2Previous==true && Page2Status==true && Page2State==true && Page2Status==true && Page1State==true){
			flag=true;
			publishResults(flag, "Pagination details for page 2 is displayed as expected ", "Pagination details when page 2 is clicked should be as expected", "Verify page 2 is displayed , previous button is enabled with status messagae updated");
		}else
		{
			flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Pagination details for page 2 is not displayed as expected ", "Pagination details when page 2 is clicked should be as expected", "Verify page 2 is displayed , previous button is enabled with status messagae updated");
		}
		pause(1000);
		clickElement(pageOneCollElsewhere);
		pause(2000);
		System.out.println("clicked on page no. 1");				 
		Boolean Page1Previous = verifyTrue((paginationPreviousCollElsewhere.getAttribute("class").contains("paginate_button previous disabled")),"previous is disabled");
		Boolean Page1Next = verifyTrue(!(paginationNextCollElsewhere.getAttribute("class").contains("paginate_button next disabled")),"next is enabled");
		Boolean Page1Status = verifyTrue(lblFilterPageDetailsCollElsewhere.getText().contains("Showing 1 to 10 of"),"status field updated accordingly");
		Page1State = verifyTrue(pageOneCollElsewhere.getAttribute("class").contains("paginate_button active"),"1 is enabled");			 
		Page2State = verifyTrue(!(pageTwoCollElsewhere.getAttribute("class").contains("paginate_button active")),"2 is not enabled");	
		if(Page1Previous==true && Page1Next==true && Page1Status==true && Page1State==true && Page2State==true){
			flag=true;
			publishResults(flag, "Pagination details for page 1 is displayed as expected ", "Pagination details when page 1 is clicked should be as expected", "Verify page 1 is displayed , previous button is disabled with status messagae updated");
		}else
		{
			flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Pagination details for page 1 is not displayed as expected ", "Pagination details when page 1 is clicked should be as expected", "Verify page 1 is displayed , previous button is disabled with status messagae updated");
		}
		int lastPage = pagination.size()-1;
		driver.findElement(By.xpath("//div[@id='incomingCreditsCollElseWhereGrid_grid_paginate']//ul//li"+"["+lastPage+"]")).click();
		pause(2000);		
		driver.findElement(By.xpath("//div[@id='incomingCreditsCollElseWhereGrid_grid_paginate']//ul//li"+"["+lastPage+"]")).click();
		pause(2000);
		if(!(paginationPreviousCollElsewhere.getAttribute("class").contains("paginate_button previous disabled")) && (paginationNextCollElsewhere.getAttribute("class").contains("paginate_button next disabled")))
				{
			flag=true;
			publishResults(flag, "Next button is disabled when the last page is clicked", "Next button should be disabled when the last page is clicked", "Verify next button is disabled , and previous button is Enabled");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Next button is not disabled when the last page is clicked", "Next button should be disabled when the last page is clicked", "Verify next button is disabled , and previous button is Enabled");
		}
		assertTrue(flag, "Pagination details when last page is clicked is not dispalyed as expected");	
	}
	else if (tabName == ICSPropertiesConfig.getExceptionsTab()){		
		validationStepInformation("Initial Pagination details :");
		if(lblFilterPageDetailsException.getText().contains("Showing 1 to 10 of") && (paginationPreviousException.getAttribute("class").contains("paginate_button previous disabled")) && !(paginationNextException.getAttribute("class").contains("paginate_button next disabled")) )
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
		clickElement(paginationNextException);
		pause(2000);
		if(!(paginationPreviousException.getAttribute("class").contains("paginate_button previous disabled")) && !(paginationNextException.getAttribute("class").contains("paginate_button next disabled")) 
				&& lblFilterPageDetailsException.getText().contains("Showing 11 to") ){
			flag=true;
			publishResults(flag, "Pagination details when Next is clicked is displayed as expected ", "Pagination details when Next is clicked is displayed should be as expected", "Verify next page grid is displayed , previous button is Enabled");
		}else
		{	flag=false;
		finalTestScriptResultFailFlag = true;
		publishResults(flag, "Pagination details when Next is clicked is displayed as expected ", "Pagination details when Next is clicked is displayed should be as expected", "Verify next page grid is displayed , previous button is Enabled");
		}
		assertTrue(flag, "Pagination details when Next is clicked is not dispalyed as expected");
		validationStepInformation("Click on Previous button Validation :");
		clickElement(paginationPreviousException);
		pause(2000);
		if((paginationPreviousException.getAttribute("class").contains("paginate_button previous disabled")) && !(paginationNextException.getAttribute("class").contains("paginate_button next disabled")) 
				&& lblFilterPageDetailsException.getText().contains("Showing 1 to 10 of") ){
			flag=true;
			publishResults(flag, "Pagination details when Previous is clicked is displayed as expected ", "Pagination details when Previous is clicked is displayed should be as expected", "Verify Previous page grid is displayed , previous button is Disabled");
		}else
		{	flag=false;
		finalTestScriptResultFailFlag = true;
		publishResults(flag, "Pagination details when Previous is clicked is not displayed as expected ", "Pagination details when Previous is clicked is displayed should be as expected", "Verify Previous page grid is displayed , previous button is Disabled");
		}
		assertTrue(flag, "Pagination details when Previous is clicked is not dispalyed as expected");
		List <WebElement> pagination = driver.findElements(By.xpath("//div[@id='incomingCreditsExceptionItemsGrid_grid_paginate']/ul/li"));
		System.out.println(pagination.size());

		if (pagination.size() > 2){
			publishResults(true, "Multiple pages are displayed", "Multiple pages should be displayed", "Verify more than one page is displayed");
		}
		
		clickElement(pageTwoException);

		System.out.println("clicked on page no. 2");				 
		Boolean Page2Previous = verifyTrue(!(paginationPreviousException.getAttribute("class").contains("paginate_button previous disabled")),"previous is enabled");				
		Boolean Page2Next = verifyTrue(!(paginationNextException.getAttribute("class").contains("paginate_button next disabled")),"next is enabled");
		Boolean Page2Status = verifyTrue(lblFilterPageDetailsException.getText().contains("Showing 11 to 20 of"),"status field updated accordingly");
		Boolean Page2State = verifyTrue((pageTwoException.getAttribute("class").contains("paginate_button active")),"2 is enabled");
		Boolean Page1State = verifyTrue(!(pageOneException.getAttribute("class").contains("paginate_button active")),"1 is enabled");			 
		
		if(Page2Previous==true && Page2Next==true && Page2Status==true && Page2State==true && Page1State==true){
			publishResults(flag, "Pagination details for page 2 is displayed as expected ", "Pagination details when page 2 is clicked should be as expected", "Verify page 2 is displayed , previous button is enabled with status messagae updated");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Pagination details for page 2 is not displayed as expected ", "Pagination details when page 2 is clicked should be as expected", "Verify page 2 is displayed , previous button is enabled with status messagae updated");
		}
		pause(2000);
		clickElement(pageOneException);
		pause(2000);
		System.out.println("clicked on page no. 1");				 
		Boolean Page1Previous = verifyTrue((paginationPreviousException.getAttribute("class").contains("paginate_button previous disabled")),"previous is disabled");
		Boolean Page1Next = verifyTrue(!(paginationNextException.getAttribute("class").contains("paginate_button next disabled")),"next is enabled");
		Boolean Page1Status = verifyTrue(lblFilterPageDetailsException.getText().contains("Showing 1 to 10 of"),"status field updated accordingly");
		Page1State = verifyTrue(pageOneException.getAttribute("class").contains("paginate_button active"),"1 is enabled");			 
		Page2State = verifyTrue(!(pageTwoException.getAttribute("class").contains("paginate_button active")),"2 is enabled");			 

		if(Page1Previous==true && Page1Next==true && Page1Status==true && Page1State==true && Page2State==true){
			publishResults(flag, "Pagination details for page 1 is displayed as expected ", "Pagination details when page 1 is clicked should be as expected", "Verify page 1 is displayed , previous button is disabled with status messagae updated");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Pagination details for page 1 is not displayed as expected ", "Pagination details when page 1 is clicked should be as expected", "Verify page 1 is displayed , previous button is disabled with status messagae updated");
		}
		int lastPage = pagination.size()-1;
		driver.findElement(By.xpath("//div[@id='incomingCreditsExceptionItemsGrid_grid_paginate']//ul//li"+"["+lastPage+"]")).click();
		pause(1000);
		if(!(paginationPreviousException.getAttribute("class").contains("paginate_button previous disabled")) && (paginationNextException.getAttribute("class").contains("paginate_button next disabled")))
				{
			flag=true;
			publishResults(flag, "Next button is disabled when the last page is clicked", "Next button should be disabled when the last page is clicked", "Verify next button is disabled , and previous button is Enabled");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Next button is not disabled when the last page is clicked", "Next button should be disabled when the last page is clicked", "Verify next button is disabled , and previous button is Enabled");
		}
		assertTrue(flag, "Pagination details when last page is clicked is not dispalyed as expected");	
	}
	return this;

	}
	/*************************************************************************************
	 * Method Name: validateTSetEntry
	 * Purpose:Method for validating the Tset Entry in Incoming Credits Tab
	 * (collectedByMe Tab) 
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  16-May-2017
	/*************************************************************************************/
	
	public IncomingCreditsPage validateTSetEntryAllItems()
	{		
		assertTrue(isElementDisplayed(tblIncomingCreditsCollItems), "T set table is not visible ");
		String source=driver.getPageSource();
		if(		source.contains("Transaction Set") && source.contains("Serial") && source.contains("Type")
				 && source.contains("Account") && source.contains("Amount") && source.contains("Image")
				 && source.contains("Kappa") && source.contains("SortCode") && source.contains("Other")
				 && source.contains("Response") && source.contains("Action")
				 && getTableRowCount("incomingCreditsAllItemsGrid_grid")>0)
		{
			flag=true;
			publishResults(flag, "T- set exists with the required columns", "T-set should be displayed with all the required columns", "Verify T set is present with all required fields");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "T- set does not display all required columns", "T-set should be displayed with all the required columns", "Verify T set is present with all required fields");
		}
		assertTrue(flag, "Incoming Credit tab Tset dispalyed as expected");	
				
		assertTrue((firstTset.getText().contains("No Of Items Included : 3")),"Number of items label is present");
		validateAmount(tblIncomingCreditsCollItems);
		pause(1000);	
		clickElement(imgImageCollItems);
		pause(1000);	
		validateTSetImageIcon();
		pause(1000);
		clickElement(imgKappaCollItems);	
		if(kappaDataCollItems.getAttribute("class").contains("trKappa_") && kappaDataCollItems.getAttribute("class").contains("css-for-OtherInfo")
				&& kappaImageCollItems.getAttribute("class").contains("trKappa_") && kappaImageCollItems.getAttribute("class").contains("trImages css-for-img"))
		{
			flag=true;
			publishResults(flag, "Kappa Expander with Data and Image is Visible", "Kappa Expander with Data and Image should be present Visible", "Verify Kappa expander is dispalyed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Kappa Expander with Data and Image is not Visible", "Kappa Expander with Data and Image should be present Visible", "Verify Kappa expander is dispalyed");
		}	
		assertTrue(kappaDataCollItems .isDisplayed(),"Kappa expander and data is present");
		clickElement (imgOtherCollItems);
		pause(1000);
		if(otherExpanderCollItems.getAttribute("class").contains("trOtherInfo_"))
		{
			flag=true;
			publishResults(flag, "Other Data Expander is displayed", "Other Data Expander should be displayed", "Verify Other Data Expander is dispalyed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Other Data Expander is not displayed", "Other Data Expander should be displayed", "Verify Other Data Expander is dispalyed");
		}
		assertTrue(otherExpanderCollItems.isDisplayed(),"Other data is present");
		imgKappaDataCollapseCollItems.click();
		imgKappaImageCollapseCollItems.click();
		imgOtherCollapseCollItems.click();
		imgImageCollapseCollItems.click();
		publishResults(flag, "All data is collapsed", "All data should be collapsed", "Verify all data is collapsed");
		
		return this;
	}
	
	/*************************************************************************************
	 * Method Name: validateTSetEntryCollectedElsewhere
	 * Purpose:Method for validating the Tset Entry in Incoming Credits Tab
	 * (Collected Elsewhere) 
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  01-june-2017
	/*************************************************************************************/
	
	public IncomingCreditsPage validateTSetEntryCollectedElsewhere()
	{		pause(2000);
		assertTrue(isElementDisplayed(tblIncomingCreditsCollElsewhere), "T set table is not visible ");
		String source=driver.getPageSource();
		if(source.contains("Transaction Set:") && source.contains("Serial") && source.contains("Type")
				 && source.contains("Account") && source.contains("Amount") && source.contains("Image")
				 && source.contains("Kappa") && source.contains("SortCode") && source.contains("Other")
				 && source.contains("Response") && source.contains("Action")
				 && getTableRowCount("incomingCreditsCollElseWhereGrid_grid")>0)
		{
			flag=true;
			publishResults(flag, "T- set exists with the required columns", "T-set should be displayed with all the required columns", "Verify T set is present with all required fields");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "T- set does not display all required columns", "T-set should be displayed with all the required columns", "Verify T set is present with all required fields");
		}
		assertTrue(flag, "Incoming Credit Collected elsewhere tab Tset displayed as expected");	
				
		assertTrue((firstTset.getText().contains("Number Of Items Included : 5")),"Number of items label is present");
		validateAmount(tblIncomingCreditsCollElsewhere);
		pause(1000);
		clickElement(imgImageCollItems);
		clickElement(imgImageCollItems);
		pause(1000);	
		validateTSetImageIcon();
		clickElement(imgKappaCollItems);
		if(kappaDataCollItems .isDisplayed()&& kappaImageCollItems.isDisplayed())
		{
			flag=true;
			publishResults(flag, "Kappa Expander with Data and Image is Visible", "Kappa Expander with Data and Image should be present Visible", "Verify Kappa expander is dispalyed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Kappa Expander with Data and Image is not Visible", "Kappa Expander with Data and Image should be present Visible", "Verify Kappa expander is dispalyed");
		}	
		clickElement (imgOtherCollItems);
		pause(1000);
		if(otherExpanderCollElsewhere.isDisplayed())//*[@id='incomingCreditsAllItemsGrid_grid']/tbody/tr[4
		{
			publishResults(flag, "Other Data Expander is displayed", "Other Data Expander should be displayed", "Verify Other Data Expander is dispalyed");
		}else
		{	
		finalTestScriptResultFailFlag = true;
		publishResults(flag, "Other Data Expander is not displayed", "Other Data Expander should be displayed", "Verify Other Data Expander is dispalyed");
		}
		assertTrue(otherExpanderCollElsewhere.isDisplayed(),"Other data is present");
		imgKappaDataCollapseCollItems.click();
		imgKappaImageCollapseCollItems.click();
		imgOtherCollapseCollItems.click();
		imgImageCollapseCollItems.click();
		publishResults(flag, "All data is collapsed", "All data should be collapsed", "Verify all data is collapsed");
		
		return this;
	}
	
	/*************************************************************************************
	 * Method Name: validateTSetEntryExceptions
	 * Purpose:Method for validating the Tset Entry in Incoming Credits Exception tab
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  02-june-2017
	/*************************************************************************************/
	
	public IncomingCreditsPage validateTSetEntryExceptions()
	{		
		assertTrue(isElementDisplayed(tblIncomingCreditsException), "T set table is not visible ");
		String source=driver.getPageSource();
		if(source.contains("Credit Sort Code") && source.contains("Credit Account")
				 && source.contains("Credit Amount") && source.contains("Serial") && source.contains("SortCode")
				 && source.contains("Account") && source.contains("Amount") && source.contains("Image")
				 && source.contains("Kappa") && source.contains("Other :") && source.contains("Response") && source.contains("Action")
				 && getTableRowCount("incomingCreditsExceptionItemsGrid_grid")>0)
		{
			flag=true;
			publishResults(flag, "Exception table with the required columns", "Exception table should be displayed with all the required columns", "Verify Exception table is present with all required fields");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Exception table does not display all required columns", "Exception table should be displayed with all the required columns", "Verify Exception table is present with all required fields");
		}
		assertTrue(flag, "Exception table column displayed as expected");	
				
		clickElement(imgImageException);
		pause(1000);	
		if(imgFrontViewException .isDisplayed())
		{
			imgFrontZoomIn.click();
			imgFrontZoomOut.click();
			imgFrontonetoone.click();
			imgFrontReset.click();
			imgFrontPrev.click();
			imgFrontNext.click();	
			imgFrontRotateLeft.click();
			imgFrontRotateRight.click();
			flag=true;
			publishResults(flag, "Image Front view is displayed with zoom ,invert,rotate options working", "Image Front view is displayed with zoom ,invert,rotate options should be visible", "Verify Image front is dispalyed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Image Front view is displayed with zoom ,invert,rotate options not working", "Image Front view is displayed with zoom ,invert,rotate options should be visible", "Verify Image front is dispalyed");
		}
		
		if(imgRearViewException .isDisplayed())
		{
			imgRearZoomIn.click();
			imgRearZoomOut.click();
			imgRearonetoone.click();
			imgRearReset.click();
			imgRearPrev.click();
			imgRearNext.click();
			imgRearRotateLeft.click();
			imgRearRotateRight.click();
			flag=true;
			publishResults(flag, "Image Rear view is displayed with zoom ,invert,rotate options working", "Image Rear view is displayed with zoom ,invert,rotate options should be visible", "Verify Image Rear is dispalyed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Image Rear view is displayed with zoom ,invert,rotate options not working", "Image Rear view is displayed with zoom ,invert,rotate options should be visible", "Verify Image Rear is dispalyed");
		}
		clickElement(imgKappaException);
		if(kappaDataCollItems .isDisplayed()&& kappaImageCollItems.isDisplayed())
		{
			flag=true;
			publishResults(flag, "Kappa Expander with Data and Image is Visible", "Kappa Expander with Data and Image should be present Visible", "Verify Kappa expander is dispalyed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Kappa Expander with Data and Image is not Visible", "Kappa Expander with Data and Image should be present Visible", "Verify Kappa expander is dispalyed");
		}	
		clickElement (imgOtherException);
		pause(1000);
		if(otherExpander.isDisplayed())
		{
			flag=true;
			publishResults(flag, "Other Data Expander is displayed", "Other Data Expander should be displayed", "Verify Other Data Expander is dispalyed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Other Data Expander is not displayed", "Other Data Expander should be displayed", "Verify Other Data Expander is dispalyed");
		}
		assertTrue(otherExpanderCollItems.isDisplayed(),"Other data is present");
		imgKappaDataCollapseCollItems.click();
		imgKappaImageCollapseCollItems.click();
		imgOtherCollapseCollItems.click();
		imgImageCollapseCollItems.click();
		publishResults(true, "All data is collapsed", "All data should be collapsed", "Verify all data is collapsed");
		
		return this;
	}
	/*************************************************************************************
	 * Method Name: validateAmount
	 * Purpose:Method for validating the sum of credit transactions is equal to the amount on the tset
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  01-june-2017
	/*************************************************************************************/
		
	public IncomingCreditsPage validateAmount(WebElement tableName){
	flag=false;
	ArrayList<WebElement> rowcount = new ArrayList<>();
	clickElement(firstTsetdownArrow);
	pause(1000);
	rowcount=(ArrayList<WebElement>) tableName.findElements(By.tagName("tr"));
	System.out.println(rowcount);
	int rowindex = 0;
	int i=2;
	WebElement amount= null;
	float totalAmount = 0;
	for (WebElement rowElement:rowcount){
	ArrayList<String> rowData = new ArrayList<>();
	rowData.add(rowElement.getText());	
						
	if (rowData.get(0).contains("Credit")){
		if(tableName == tblIncomingCreditsCollItems){
			amount = 
					rowElement.findElement(By.xpath("//*[@id='incomingCreditsAllItemsGrid_grid']/tbody/tr["+i+"]/td[7]"));		
		}
		else if(tableName == tblIncomingCreditsCollElsewhere){
			amount = 
					rowElement.findElement(By.xpath("//*[@id='incomingCreditsCollElseWhereGrid_grid']/tbody/tr["+i+"]/td[7]"));
		}
		String amount2 = amount.getText();
		amount2=amount2.replace("£", "");
		totalAmount = totalAmount + Float.valueOf(amount2);
		System.out.println(totalAmount);	
		i++;				
	}	
	rowindex++;
	if (!(rowData.get(0).contains("Credit") || rowData.get(0).contains("Chq")) && rowindex > 3 )
	{
		String firstTsetAmount = null;
		if(tableName == tblIncomingCreditsCollItems){
			firstTsetAmount = firstTsetAmountAllItems.getText();
			}
		else if(tableName == tblIncomingCreditsCollElsewhere){
			firstTsetAmount = firstTsetAmountCollElsewhere.getText();
		}
		double totalAmount1 = Math.round(totalAmount*100.00)/100.00;
		if ( ("£ "+totalAmount1).equals(firstTsetAmount)){
				flag=true;
				publishResults(flag, "T- set amount is equal to the sum of all the credit amounts present", "T- set amount is equal to the sum of all the credit items present","T set Total amount displayed as expected");
			}else
			{	flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "T- set amount is not equal to the sum of all the credit amounts present", "T- set amount is equal to the sum of all the credit items present","T set Total amount displayed as expected");	
			}
		break;
		}
	}	
	return this;
	}
	/*************************************************************************************
	 * Method Name: validateTSetImageIcon
	 * Purpose:Method for validate the TsetImageIcon
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  01-june-2017
	/*************************************************************************************/
		
	public IncomingCreditsPage validateTSetImageIcon(){
	
	if(imgFrontViewCollItems .isDisplayed())
	{
		imgFrontZoomIn.click();
		imgFrontZoomOut.click();
		imgFrontonetoone.click();
		imgFrontReset.click();
		imgFrontPrev.click();
		imgFrontNext.click();	
		imgFrontRotateLeft.click();
		imgFrontRotateRight.click();
		flag=true;
		publishResults(flag, "Image Front view is displayed with zoom ,invert,rotate options working", "Image Front view is displayed with zoom ,invert,rotate options should be visible", "Verify Image front is dispalyed");
	}else
	{	flag=false;
		finalTestScriptResultFailFlag = true;
		publishResults(flag, "Image Front view is displayed with zoom ,invert,rotate options not working", "Image Front view is displayed with zoom ,invert,rotate options should be visible", "Verify Image front is dispalyed");
	}
	
	if(imgRearViewCollItems .isDisplayed())
	{
		imgRearZoomIn.click();
		imgRearZoomOut.click();
		imgRearonetoone.click();
		imgRearReset.click();
		imgRearPrev.click();
		imgRearNext.click();
		imgRearRotateLeft.click();
		imgRearRotateRight.click();
		flag=true;
		publishResults(flag, "Image Rear view is displayed with zoom ,invert,rotate options working", "Image Rear view is displayed with zoom ,invert,rotate options should be visible", "Verify Image Rear is dispalyed");
	}else
	{	flag=false;
		finalTestScriptResultFailFlag = true;
		publishResults(flag, "Image Rear view is displayed with zoom ,invert,rotate options not working", "Image Rear view is displayed with zoom ,invert,rotate options should be visible", "Verify Image Rear is dispalyed");
	}
	return this;
	}
	
	/*************************************************************************************
	 * Method Name: validateOtherInformation
	 * Purpose:Method for validating other info in incoming credits tab
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  2-June-2017
	/*************************************************************************************/
	
	public IncomingCreditsPage validateOtherInformation(String TabName)
	{	
		String otherInfo = null;
		if (TabName == ICSPropertiesConfig.getCollectedItemsTab()){
			assertTrue(isElementDisplayed(tblIncomingCreditsCollItems), "T set table is not visible ");
			clickElement(firstTsetdownArrow);	
			clickElement (imgOtherCollItems);
			clickElementEnter (imgOtherCollItems);
			otherInfo = txtotherInfo.getText();
		}
		else if (TabName == ICSPropertiesConfig.getCollectedElsewhereTab()){
		assertTrue(isElementDisplayed(tblIncomingCreditsCollItems), "T set table is not visible ");
		clickElement(firstTsetdownArrow);	
		clickElement (imgOtherCollItems);
		clickElementEnter (imgOtherCollItems);
		otherInfo = txtotherInfo.getText();
		}
		else if (TabName == ICSPropertiesConfig.getExceptionsTab()){
			clickElement (imgOtherException);
			assertTrue(otherExpanderCollElsewhere.isDisplayed(),"Other details for exception is present");
			otherInfo = txtotherInfoException.getText();
		}
				
		if (otherInfo.contains("Transaction Set Identifier:") && 
			otherInfo.contains("Transaction Set Version:") &&
			otherInfo.contains("Is Amount Corrected: Yes") &&
			otherInfo.contains("Is AN Corrected: Yes") &&
			otherInfo.contains("Is SortCode Corrected: Yes") &&
			otherInfo.contains("Is Serial Corrected: Yes") &&
			otherInfo.contains("D2 Response Window Start Date:") &&
			otherInfo.contains("D2 Response Window End Date:") &&
			otherInfo.contains("Is Bank Holiday: No") &&
			otherInfo.contains("Item Reject Codes: 999") &&
			otherInfo.contains("Holdover: No"))
		
		{
			flag=true;
			publishResults(flag, "Other Information data displayed", "Other Information data should be displayed", "Verify Other Information data is dispalyed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Other Information data is not displayed", "Other Information data should be displayed", "Verify Other Information data is dispalyed");
		}
		assertTrue(flag, "Other information is not present");
		return this;
	}
	
	/*************************************************************************************
	 * Method Name: fillDetailsInCollectedItemsTab
	 * Purpose:Method for filling values in search filters getting data from datasheet in collected items tab
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  11-Aug-2017
	/*************************************************************************************/
	public IncomingCreditsPage fillDetailsInCollectedItemsTab() throws InterruptedException 
	{
		String resultsTable=null;
		clickElement(firstTsetdownArrow);
		String account = tblAccount.getText();		
		String serial = tblSerial.getText();		
		String sortCodeFrom = tblSortcode.getText();	
		int sortCodeTo1 = Integer.parseInt(sortCodeFrom);
		sortCodeTo1 = sortCodeTo1+1;
		String sortCodeTo = Integer.toString(sortCodeTo1);
		String fromAmount = getAgencyValueFromDataSheet("incomingCreditFromAmount");		
		String toAmount = getAgencyValueFromDataSheet("incomingCreditToAmount");
		
		fillTextBox(txtSerialAllCollItems,serial);
		fillTextBox(txtSortCodeAllCollItems,sortCodeFrom);
		fillTextBox(txtAccountAllCollItems,account);
		fillTextBox(txtSortCodeToAllCollItems,sortCodeTo);	
		fillTextBox(txtAmountAllCollItems,fromAmount);
		fillTextBox(txtAmountToAllCollItems, toAmount);		
		clickElement(btnApplyFiltersCollItems);
		pause(1000);
		clickElement(firstTsetdownArrow);
		resultsTable=tblIncomingCreditsCollItems.getText();
			if(lblShowCurrentFilterAllColl.getText().contains(account) && lblShowCurrentFilterAllColl.getText().contains(serial) 
					&& lblShowCurrentFilterAllColl.getText().contains(sortCodeFrom) && lblShowCurrentFilterAllColl.getText().contains(sortCodeTo) 
					&& lblShowCurrentFilterAllColl.getText().contains("Amount : £"+fromAmount+" , To : £"+toAmount) && 					
					resultsTable.contains(account) && resultsTable.contains(serial) && resultsTable.contains(sortCodeFrom))
			{
				flag=true;
				publishResults(flag, "user is able to view items matching the filter criteria entered", "user should able to view items matching the filter", "Results should be displayed based on the filter appplied");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items matching the filter criteria entered", "user should able to view items matching the filter", "Results should be displayed based on the filter appplied");
			}
			assertTrue(flag, "filters are applied in incoming credits as expected");
		return this;
	}
	/*************************************************************************************
	 * Method Name: fillDetailsInCollectedElsewhereTab
	 * Purpose:Method for filling values in search filters getting data from datasheet in collected elsewhere
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  11-Aug-2017
	/*************************************************************************************/
	public IncomingCreditsPage fillDetailsInCollectedElsewhereTab() throws InterruptedException 
	{
		pause(1000);
		String resultsTable=null;
		clickElement(firstTsetdownArrow);
		pause(2000);
		String account = tblAccount.getText();		
		String serial = tblSerial.getText();		
		String sortCodeFrom = tblSortcode.getText();	
		int sortCodeTo1 = Integer.parseInt(sortCodeFrom);
		sortCodeTo1 = sortCodeTo1+1;
		String sortCodeTo = Integer.toString(sortCodeTo1);
		
		fillTextBox(txtSerialCollElseWhere,serial);
		fillTextBox(txtSrtCodeCollElseWhereTab,sortCodeFrom);
		fillTextBox(txtAccountCollElseWhere,account);
		fillTextBox(txtSrtCodeToCollElseWhereTab,sortCodeTo);	
		fillTextBox(txtAmountCollElseWhere,"10");
		fillTextBox(txtAmountToCollElseWhere, "100000");		
		clickElement(btnApplyFiltersCollElsewhere);
		pause(2000);
		clickElement(firstTsetdownArrow);
		pause(1000);
		resultsTable=tblIncomingCreditsCollElsewhere.getText();
			if(lblShowCurrentFilterCollElsewhere.getText().contains(account) && lblShowCurrentFilterCollElsewhere.getText().contains(serial) 
					&& lblShowCurrentFilterCollElsewhere.getText().contains(sortCodeFrom) && lblShowCurrentFilterCollElsewhere.getText().contains(sortCodeTo) 
					&& lblShowCurrentFilterCollElsewhere.getText().contains("Amount : £10 , To : £100,000") && 					
					resultsTable.contains(account) && resultsTable.contains(serial) && resultsTable.contains(sortCodeFrom))
			{
				flag=true;
				publishResults(flag, "user is able to view items matching the filter criteria entered", "user should able to view items matching the filter", "Results should be displayed based on the filter appplied");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items matching the filter criteria entered", "user should able to view items matching the filter", "Results should be displayed based on the filter appplied");
			}
			assertTrue(flag, "filters are applied in incoming credits as expected");
		return this;
	}
	/*************************************************************************************
	 * Method Name: fillDetailsInExceptionTab
	 * Purpose:Method for filling values in search filters getting data from datasheet
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  11-Aug-2017
	/*************************************************************************************/
	public IncomingCreditsPage fillDetailsInExceptionTab() throws InterruptedException 
	{
		String resultsTable=null;
		String account = getAgencyValueFromDataSheet("exceptionCreditAccount");	
		String serial = getAgencyValueFromDataSheet("exceptionSerial");		
		String sortCodeFrom = getAgencyValueFromDataSheet("exceptionSortcode");		
		String sortCodeTo = getAgencyValueFromDataSheet("exceptionSortcodeTo");		
		String fromAmount = getAgencyValueFromDataSheet("incomingCreditFromAmount");		
		String toAmount = getAgencyValueFromDataSheet("incomingCreditToAmount");		
				
		fillTextBox(txtSerialExceptionItems,serial);
		fillTextBox(txtSrtCodeExceptionsTab,sortCodeFrom);
		fillTextBox(txtAccountExceptionItems,account);
		fillTextBox(txtSrtCodeToExceptionsTab,sortCodeTo);	
		fillTextBox(txtAmountExceptionItems,fromAmount);
		fillTextBox(txtAmountToExceptionItems, toAmount);		
		clickElement(btnApplyFiltersExceptionItems);
		pause(2000);
		resultsTable=tblIncomingCreditsException.getText();
			if(lblShowCurrentFilterExcecption.getText().contains(account) && lblShowCurrentFilterExcecption.getText().contains(serial) 
					&& lblShowCurrentFilterExcecption.getText().contains(sortCodeFrom) && lblShowCurrentFilterExcecption.getText().contains(sortCodeTo) 
					&& lblShowCurrentFilterExcecption.getText().contains("Amount : £"+fromAmount+" , To : £"+toAmount) && 					
					resultsTable.contains(account) && resultsTable.contains(sortCodeFrom))
			{
				flag=true;
				publishResults(flag, "user is able to view items matching the filter criteria entered", "user should able to view items matching the filter", "Results should be displayed based on the filter appplied");
			}else
			{
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "user is not able to view items matching the filter criteria entered", "user should able to view items matching the filter", "Results should be displayed based on the filter appplied");
			}
			assertTrue(flag, "filters are applied in incoming credits as expected");
		return this;
	}
	/*************************************************************************************
	 * Method Name: assertClearFieldsinAllCollItemsTab
	 * Purpose:Method for validating the clear all button in all collected tab
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  11-Aug-2017
	/*************************************************************************************/
	public IncomingCreditsPage assertClearFieldsinAllCollItemsTab()
	{
		boolean flagClearFields=false;
		pause(1000);
		clickElement(btnClearCurrentFilters);
		pause(1000);
		if( lblShowCurrentFilterAllColl.getText().contentEquals("All Items Filtered:") && txtAccountAllCollItems.getText().isEmpty() && txtSortCodeAllCollItems.getText().isEmpty()
				&& txtSerialAllCollItems.getText().isEmpty()&& txtAmountAllCollItems.getText().isEmpty()
				&& txtSortCodeToAllCollItems.getText().isEmpty() && txtAmountToAllCollItems.getText().isEmpty())
			flagClearFields=true;
		if(flagClearFields){
			publishResults(flagClearFields, "All fields cleared" , "All fields should be cleared", "Verify all fields are cleard when clicked on Clear filter");	
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flagClearFields, "All fields are not cleared" , "All fields should be cleared", "Verify all fields are cleard when clicked on Clear filter");
		}
		assertTrue(flagClearFields, "All fields are cleared");
		return this;
	}	
	/*************************************************************************************
	 * Method Name: assertClearFieldsinCollectedElseWhereTab
	 * Purpose:Method for validating the clear all button in collected elsewhere tab
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  11-Aug-2017
	/*************************************************************************************/
	
	public IncomingCreditsPage assertClearFieldsinCollectedElseWhereTab()
	{
		boolean flagClearFields=false;
		clickElement(btnClearCurrentFilters);
		pause(1000);
		if(lblShowCurrentFilterCollElsewhere.getText().contentEquals("Item Status:All Items") && txtSrtCodeCollElseWhereTab.getText().isEmpty() && txtSrtCodeToCollElseWhereTab.getText().isEmpty()
				&& txtAccountCollElseWhere.getText().isEmpty()&& txtSerialCollElseWhere.getText().isEmpty()
				&& txtAmountCollElseWhere.getText().isEmpty() && txtAmountToCollElseWhere.getText().isEmpty())
			flagClearFields=true;
		if(flagClearFields){
			publishResults(flagClearFields, "All fields cleared" , "All fields should be cleared", "Verify all fields are cleard when clicked on Clear filter");	
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flagClearFields, "All fields are not cleared" , "All fields should be cleared", "Verify all fields are cleard when clicked on Clear filter");
		}
		assertTrue(flagClearFields, "All fields are cleared");
		return this;
	}
	
	/*************************************************************************************
	 * Method Name: assertClearFieldsinExceptionTab
	 * Purpose:Method for validating the clear all button in exceptions tab
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  11-jul-2017
	/*************************************************************************************/
	
	public IncomingCreditsPage assertClearFieldsinExceptionTab()
	{
		boolean flagClearFields=false;
		clickElement(btnClearCurrentFilters);
		pause(1000);
		if(lblShowCurrentFilterExcecption.getText().contentEquals("Item Status:All Items") && txtSrtCodeToExceptionsTab.getText().isEmpty() && txtSrtCodeToExceptionsTab.getText().isEmpty()
				&& txtAccountExceptionItems.getText().isEmpty()&&txtSerialExceptionItems.getText().isEmpty()
				&& txtAmountExceptionItems.getText().isEmpty() && txtAmountToExceptionItems.getText().isEmpty())
			flagClearFields=true;
		if(flagClearFields){
			publishResults(flagClearFields, "All fields cleared" , "All fields should be cleared", "Verify all fields are cleard when clicked on Clear filter");	
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flagClearFields, "All fields are not cleared" , "All fields should be cleared", "Verify all fields are cleard when clicked on Clear filter");
		}
		assertTrue(flagClearFields=true, "All fields are cleared");
		return this;
	}
	
	/*************************************************************************************
	 * Method Name: assertClientAccessibilityTextCollectedItems
	 * Purpose:Method for validating the tooltip text when hovered over the required links 
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  11-Aug-2017
	/*************************************************************************************/

	public IncomingCreditsPage assertClientAccessibilityTextCollectedItems()
	{
		boolean tsetFlag=false;
		boolean kappaFlag=false;
		boolean ImageFlag=false;
		boolean otherFlag=false;
		Actions action = new Actions(driver);
		clickElement(firstTsetdownArrow);		
		if (firstTsetdownArrowTooltip.getAttribute("class").contains("tooltiptext") && firstTsetdownArrowTooltip.getText().contentEquals("Tset")){
			tsetFlag=true;
		}
		action.moveToElement(imgKappa).build().perform();
		if (imgKappaTooltip.getAttribute("class").contains("tooltiptext") &&  imgKappaTooltip.getText().contains("Kappa")){
			kappaFlag=true;
		}
		action.moveToElement(imgImageCollItems).build().perform();
		if (imgImageCollItemsTooltip.getAttribute("class").contains("tooltiptext") &&  imgImageCollItemsTooltip.getText().contains("Image")){
			ImageFlag=true;
		}
		action.moveToElement(imgOther).build().perform();
		if (imgOtherTooltip.getAttribute("class").contains("tooltiptext") &&  imgOtherTooltip.getText().contains("Other")){
			otherFlag=true;
		}
		
		if (tsetFlag==true && kappaFlag==true && ImageFlag==true && otherFlag==true)  {		
			publishResults(true, "tool tip value is present for grid icons" , "verify tool tip value is present for grid icons", "Verify tool tip value is present for grid icons(image,kappa and other)");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(false, "tool tip value is not present for grid icons" , "verify tool tip value is present for grid icons", "Verify tool tip value is present for grid icons(image,kappa and other)");
		}
		action.moveToElement(btnFilterCollItems).build().perform();
		if (btnFilterCollItemsTooltip.getAttribute("class").contains("tooltiptext") && btnFilterCollItemsTooltip.getText().contentEquals("Filter") ){		
			publishResults(true, "tool tip value is present for filter icon" , "verify tool tip value is present for filter icon", "Verify tool tip value is present for filter icon");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(false, "tool tip value is not present for filter icon" , "verify tool tip value is present for filter icon", "Verify tool tip value is present for filter icon");
		}		
		return this;
	}
	
	/*************************************************************************************
	 * Method Name: assertTooltipTextCollectedElsewhere
	 * Purpose:Method for validating the toolTip text when hovered over the required links 
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  11-Aug-2017
	/*************************************************************************************/

	public IncomingCreditsPage assertTooltipTextCollectedElsewhere()
	{
		boolean tsetFlag=false;
		boolean kappaFlag=false;
		boolean ImageFlag=false;
		boolean otherFlag=false;
		Actions action = new Actions(driver);
		clickElement(firstTsetdownArrow);
		pause(1000);
		if (firstTsetdownArrowTooltip.getAttribute("class").contains("tooltiptext") && firstTsetdownArrowTooltip.getText().contentEquals("Tset")){
			tsetFlag=true;
		}
		action.moveToElement(imgKappa).build().perform();
		if (imgKappaTooltip.getAttribute("class").contains("tooltiptext") &&  imgKappaTooltip.getText().contains("Kappa")){
			kappaFlag=true;
		}
		action.moveToElement(imgImageCollItems).build().perform();
		if (imgImageCollItemsTooltip.getAttribute("class").contains("tooltiptext") &&  imgImageCollItemsTooltip.getText().contains("Image")){
			ImageFlag=true;
		}
		action.moveToElement(imgOther).build().perform();
		if (imgOtherTooltip.getAttribute("class").contains("tooltiptext") &&  imgOtherTooltip.getText().contains("Other")){
			otherFlag=true;
		}
		
		if (tsetFlag==true && kappaFlag==true && ImageFlag==true && otherFlag==true)  {		
			publishResults(true, "tool tip value is present for grid icons" , "verify tool tip value is present for grid icons", "Verify tool tip value is present for grid icons(image,kappa and other)");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(false, "tool tip value is not present for grid icons" , "verify tool tip value is present for grid icons", "Verify tool tip value is present for grid icons(image,kappa and other)");
		}
		action.moveToElement(btnFilterCollElsewhere).build().perform();
		if (btnFilterCollItemsTooltip.getAttribute("class").contains("tooltiptext") && btnFilterCollItemsTooltip.getText().contentEquals("Filter") ){		
			publishResults(true, "tool tip value is present for filter icon" , "verify tool tip value is present for filter icon", "Verify tool tip value is present for filter icon");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(false, "tool tip value is not present for filter icon" , "verify tool tip value is present for filter icon", "Verify tool tip value is present for filter icon");
		}		
		return this;
	}
	
	/*************************************************************************************
	 * Method Name: validateMultiselectError
	 * Purpose:Method for validating the error message when more than 5 checkbox is selected
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  15-Aug-2017
	/*************************************************************************************/

	public IncomingCreditsPage validateMultiselectError()
	{	
		/*boolean flag=false;
		String errMessage=getTextFromAlert();
		if(errMessage.equalsIgnoreCase("Maximum items allowed to download is 5 Please try Again."))
		{
			flag=true;
		}
		assertTrue(flag, "Expected error message is displayed");	
		WindowsAlertPopUp("Accept");
		return flag;*/
		boolean toolTipFalg=false;
		if (dlgErrorMessage.getText().contains("Items cannot be more than 5") ){
			flag=true;
			publishResults(flag, "Error message displayed as expected", "Error pop up should be displayed","Error pop up should be displayed when date range is greater than 365 days");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  "error message not displayed", "Error pop up should be displayed","Error pop up should be displayed when date range is greater than 365 days");
		}
		pause(1000);
		clickElementEnter(btnOk);
		return this;
	}
	/*************************************************************************************
	 * Method Name: assertMultiSelectTabs
	 * Purpose:Method for validating the multiselect block when transactions are selected
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  15-Aug-2017
	/*************************************************************************************/

	public IncomingCreditsPage assertMultiSelectTabs()
	{
		if (isElementDisplayed(btnCreatePdf) && multiSelectDownloadTab.getAttribute("style").contentEquals("display: block;"))
		{
			publishResults(isElementDisplayed(btnCreatePdf), "Download selected items is present", "Download selected items button should be present", "Verify Download button is present");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(isElementDisplayed(btnCreatePdf),"Download selected items is not present", "Download selected items button should be present", "Verify Download button is present");
		}
		assertTrue(isElementDisplayed(btnCreatePdf), "Multiselect tab not dispalyed as expected");
		return this;
	}
	
}
