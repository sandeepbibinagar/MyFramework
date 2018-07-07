/*  
<Copyright file="SearchResultsPage.java" company="iPSL">
Copyright © iPSL 2017 All rights are reserved.
Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
is prohibited without the prior written permission of the copyright owner.
</copyright> 
*/
 
/* ************************* Module Header ******************************
 * Module Name : Handles methods related to IA Search results Page     
 * Date : 18/06/2017
 * Created By : Sandeep Bibinagar
 * Description : handling methods related to IA Search results Page 
 */ 
/* ***************** AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 30/07/2017		
*********************************************************************** 
Description : Updating file as more work flows are added to the regression suite
***********************************************************************
******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 21/08/2017		
 Description : Updating file as more work flows are added to the regression suite
 
 ******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 05/09/2017		
 Description : Updating class as per java coding standards
 ********************************************************************** */

package com.ics.ia.pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;

public class SearchResultsPage  extends ICSPageUtilis {

	public SearchResultsPage(WebDriver driver) 
	{
		super(driver);
	}

	public SearchResultsPage(WebDriver driver, String url) 
	{
		super(driver, url);
	}
	
	protected static boolean flag=false;
	public static boolean tempResult;
	
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[1]")
	private static WebElement Label_OP_AddtoOutbox;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[2]")
	private static WebElement Label_OP_SQ;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[3]")
	private static WebElement Label_OP_IP;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[4]")
	private static WebElement Label_OP_ProcDate;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[5]")
	private static WebElement Label_OP_CaptDate;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[6]")
	private static WebElement Label_OP_Serail_Reference;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[7]")
	private static WebElement Label_OP_SC;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[8]")
	private static WebElement Label_OP_Account;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[9]")
	private static WebElement Label_OP_TC;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[10]")
	private static WebElement Label_OP_Amount;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[11]")
	private static WebElement Label_OP_Cur;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[12]")
	private static WebElement Label_OP_Gnd;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[13]")
	private static WebElement Label_OP_Typ;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[14]")
	private static WebElement Label_OP_OnUs;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[15]")
	private static WebElement Label_OP_Paid;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[16]")
	private static WebElement Label_OP_RPS;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[17]")
	private static WebElement Label_OP_FRD;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[18]")
	private static WebElement Label_OP_RPR;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[19]")
	private static WebElement Label_OP_DEF;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[20]")
	private static WebElement Label_OP_SWT;
	@FindBy(xpath = "//th[text()='STO']")
	private static WebElement Label_OP_STO;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[22]")
	private static WebElement Label_OP_DUP;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[23]")
	private static WebElement Label_OP_ERR;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[24]")
	private static WebElement Label_OP_DEL;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[25]")
	private static WebElement Label_OP_State;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[26]")
	private static WebElement Label_OP_ColLoc;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[27]")
	private static WebElement Label_OP_ColBchLoc;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[28]")
	private static WebElement Label_OP_ColPID;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[29]")
	private static WebElement Label_OP_PayPID;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[30]")
	private static WebElement Label_OP_BenPID;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[31]")
	private static WebElement Label_OP_Channel;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[32]")
	private static WebElement Label_OP_UII;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[33]")
	private static WebElement Label_OP_UTSI;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[34]")
	private static WebElement Label_OP_UDSI;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[35]")
	private static WebElement Label_OP_CaptureDIN;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[36]")
	private static WebElement Label_OP_BRANCH_IBDE_DIN;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[37]")
	private static WebElement Label_OP_WorkStream;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[38]")
	private static WebElement Label_OP_Pass;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[39]")
	private static WebElement Label_OP_RT;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[40]")
	private static WebElement Label_OP_IMG;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[41]")
	private static WebElement Label_OP_FM;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[42]")
	private static WebElement Label_OP_RJ;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[43]")
	private static WebElement Label_OP_AR;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[44]")
	private static WebElement Label_OP_OR;
	@FindBy(xpath = "//div[@id='dtMainQuery_grid_wrapper']/div[2]/div[1]/div/table/thead/tr/th[45]")
	private static WebElement Label_OP_Narrative;
	@FindBy(id="dtMainQuery_grid_wrapper")
	private static WebElement Label_OP_column;
	@FindBy(xpath = "//input[@id='ProcessingDate']")
	private static WebElement textboxFocusProcDate;
	@FindBy(id = "nav-title")
	private static WebElement itemSearchHeaderTitle;	
	@FindBy(id = "ErrorProcessingDate")
	private static WebElement dateFormatErrorMessage;	
	@FindBy(id = "ErrorProcessingEndDate")
	private static WebElement dateFormatEndErrorMessage;
	@FindBy(id = "ClearingDin")
	private static WebElement textboxCaptureDIN;
	@FindBy(id = "IBDEBranchISN")
	private static WebElement textboxCaptureISN;
	@FindBy(id = "WorkStream")
	private static WebElement selectWorkStream;
	@FindBy(id = "ProcessingDate")
	private static WebElement textboxProcDate;
	@FindBy(xpath  = "//button[@id='btnSubmit']")
	private static WebElement ButtonSearch;
	@FindBy(id = "ProcessingDateEnd")
	private static WebElement textboxProcDateEnd;
	@FindBy(id = "PostingDate")
	private static WebElement textboxPostingDate;
	@FindBy(id = "PostingDateEnd")
	private static WebElement textboxPostingDateEnd;
	@FindBy(id = "SortCode")
	private static WebElement textboxSC;
	@FindBy(xpath  = "//ul[@class='site-menu']/li[2]/a")
	private static WebElement ItemSearchQueryMenu;
	@FindBy(id = "Account")
	private static WebElement textboxAccount;
	@FindBy(id = "TranCode")
	private static WebElement textboxTranCode;
	@FindBy(id = "SerialReference")
	private static WebElement textboxSerialReference;
	@FindBy(id = "SerialEnd")
	private static WebElement textboxSerialEnd;
	@FindBy(id = "Amount")
	private static WebElement textboxAmount;
	@FindBy(id = "AmountEnd")
	private static WebElement textboxAmountEnd;
	@FindBy(id = "Gnd")
	private static WebElement dropdownGnd;
	@FindBy(id = "BeneficiaryParticipant")
	private static WebElement textboxBenPID;	
	@FindBy(id = "ClearingItemReference")
	private static WebElement textboxClearingItemReference;
	@FindBy(id = "CollectingParticipant")
	private static WebElement textboxColPID;
	@FindBy(id = "UniqueItemIdentifier")
	private static WebElement textboxUII;
	@FindBy(id = "QueryTypesImageClearing")
	private static WebElement radioButtonImageClearingOnly;
	@FindBy(id = "QueryTypesPaperClearing")
	private static WebElement radioButtonPaperClearingOnly;
	@FindBy(id = "QueryTypesBoth")
	private static WebElement radioButtonQueryTypesBoth;
	@FindBy(id = "btnSubmit")
	private static WebElement buttonItemSearch;
	@FindBy(id = "dtMainQuery_grid_info")
	private static WebElement searchResultsInfo;
	@FindBy(id = "dtMainQuery_grid_paginate")
	private static WebElement paginationGrid;
	@FindBy(xpath  = "//div[@id='dtMainQuery_grid_paginate']/ul/li[4]/a")
	private static WebElement NextPaginationButton;	
	@FindBy(xpath  = "//li[@id='dtMainQuery_grid_previous']/a")
	private static WebElement PreviousPaginationButton;	
	@FindBy(xpath  = "//ul[@class='pagination']/li[6]/a")
	private static WebElement FourthPageButton;	
	@FindBy(xpath  = "//div[@id='dtMainQuery']/div[@id='dtMainQuery_grid_wrapper']//div[@class='dataTables_scroll']//div[@class='dataTables_scrollBody']/table[@id='dtMainQuery_grid']/tbody/tr[1]/td[1]/div/input[@class='editor-active outbox']")
	private static WebElement OutboxcheckBox;	
	@FindBy(id = "btnAddToOutbox_MainQuery")
	private static WebElement AddToOutboxButton;
	@FindBy(xpath = "//th[@class='obd-currency sorting']")
	private static WebElement CurrencyColumn;
	@FindBy(xpath = "//th[@class='obd-collectingparticipantidentifier sorting']")
	private static WebElement CollectingPIDColumnName;
	@FindBy(xpath = "//th[@class='obd-narrative sorting']")
	private static WebElement NarrativeColumnName;
	@FindBy(xpath = "//th[@class='obd-account sorting']")
	private static WebElement AccountColumnName;
	@FindBy(xpath = "//table[@id='dtMainQuery_grid']/tbody/tr[1]")
	private static WebElement Record1;
	@FindBy(xpath = "//table[@id='dtMainQuery_grid']/tbody/tr[5]")
	private static WebElement Record5;
	@FindBy(xpath = "//table[@id='dtMainQuery_grid']/tbody/tr[8]")
	private static WebElement Record8;
	@FindBy(xpath = "//table[@id='dtMainQuery_grid']/tbody/tr[12]")
	private static WebElement Record12;
	@FindBy(xpath = "//table[@id='dtMainQuery_grid']/tbody/tr[15]")
	private static WebElement Record15;
	@FindBy(xpath = "//table[@id='dtMainQuery_grid']/tbody/tr[20]")
	private static WebElement Record20;
	@FindBy(xpath = "//div[@id='imageViewer']/div[2]/div[1]/div[1]/div[@id='imageViewerFront']//div[@class='viewer-container']/div[@class='viewer-canvas']/img")
	private static WebElement FrontImage;
	@FindBy(xpath = "//div[@id='imageViewer']/div[2]/div[2]/div/div[@id='imageViewerRear']//div[@class='viewer-container']/div[@class='viewer-canvas']/img")
	private static WebElement RearImage;
	@FindBy(xpath = "//div[@class='no-preview']/img")
	private static WebElement NoImage;
	@FindBy(xpath  = "//div[@class='modal-footer']/button[@class='btn btn-primary']")
	private static WebElement PopUpOk;	
	@FindBy(xpath  = "//ul[@class='site-menu']//li[3]/a")
	private static WebElement OutBoxMenu;	
	@FindBy(xpath  = "//ul[@class='site-menu']//li[3]")
	private static WebElement OutBoxMenuDisabled;	
	@FindBy(xpath  = "//li[@class='paginate_button active']")
	private static WebElement activePaginationButton;	
	@FindBy(xpath  = "//li[@class='paginate_button active']/a")
	private static WebElement activePaginationNthButton;	
	@FindBy(id = "ErrorSerialReference")
	private static WebElement serialreferenceError;
	@FindBy(xpath  = "//table[@id='dtMainQuery_grid']/tbody/tr/td[8]")
	private static WebElement firstRowSortCode;
	@FindBy(xpath  = "//*[@class='fa fa-lg fa-fw fa-bars'][1]")
	private static WebElement SubQueryLink;
	@FindBy(xpath  = "//div[@class='dataTables_scrollBody']//tbody/tr/td[3]")
	private static WebElement getRowSortCodeFromOutBox;
	@FindBy(xpath  = "//div[@class='list-group']/a[1]")
	private static WebElement itemUpdateHistorySubQuery;
	@FindBy(xpath  = "//div[@class='list-group']/a[1]")
	private static WebElement itemStateHistorySubQuery;
	@FindBy(xpath  = "//div[@class='list-group']/a[1]")
	private static WebElement DebitFraudItemsSubQuery;
	@FindBy(xpath  = "//div[@class='list-group']/a[1]")
	private static WebElement CreditFraudItemsSubQuery;
	@FindBy(xpath  = "//ul[@class='nav navbar-toolbar']/li[3]/a")
	private static WebElement NavigationToolBarTitle;
	@FindBy(xpath  = "//div[@id='nav-title']")
	private static WebElement NavigationTitle;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[4]")
	private static WebElement ProcessingDateColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[7]")
	private static WebElement SerialReferenceColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[8]")
	private static WebElement SortCodeColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[9]")
	private static WebElement AccountColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[12]")
	private static WebElement AmountColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[11]")
	private static WebElement TransactionCodeColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[28]")
	private static WebElement DeletedItemIndicatorColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[40]")
	private static WebElement ClearingDinColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[41]")
	private static WebElement BranchIBDEDINColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[43]")
	private static WebElement PassNumberColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[44]")
	private static WebElement RecordTypeColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[45]")
	private static WebElement ImageIndicatorColumn;
	@FindBy(xpath  = "//span[@id='spanUserName']")
	private static WebElement UserNameLink;
	@FindBy(xpath  = "//ul[@class='dropdown-menu']/li/a")
	private static WebElement LogoutLink;
	@FindBy(xpath  = "//ul[@class='site-menu']/li[4]/a")
	private static WebElement PreviousQueryMenu;
	@FindBy(xpath  = "//table[@class='table dataTable row-border table-bordered']/tbody/tr[1]/td")
	private static WebElement PreviousQueryMenuFirstItem;
	@FindBy(xpath  = "//select[@id='ItemType']")
	private static WebElement dropdownItemType;	
	@FindBy(id = "ItemStateValue")
	private static WebElement dropdownState;	
	@FindBy(id = "ChannelIdentifier")
	private static WebElement dropdownChannel;	
	@FindBy(id = "ItemType")
	private static WebElement textboxClgItemRef;	
	@FindBy(id = "PayingParticipant")
	private static WebElement textboxPayPID;	
	@FindBy(id = "CollectingBranchLocation")
	private static WebElement textboxColBchLoc;	
	@FindBy(id = "CollectingLocation")
	private static WebElement textboxColLoc;		
	@FindBy(id = "IBDEBranchISN")
	private static WebElement textboxBranchOrIBDEISN;
	@FindBy(xpath = "")
	private static WebElement textboxDisabledClearingDIN;
	@FindBy(id = "ClearingDin")
	private static WebElement textboxClearingDIN;
	@FindBy(id = "UniqueItemIdentifier")
	private static WebElement textboxUniqueTransactionSetItemIdentifier;
	@FindBy(id = "TransactionSetIdentifier")
	private static WebElement textboxTransactionSetIdentifier;
	@FindBy(id = "ItemStateValue")
	private static WebElement dropDownItemStateValue;
	@FindBy(xpath = "//select[@id='ItemStateValue']")
	private static WebElement dropDownItemType;
	@FindBy(id = "MessageType")
	private static WebElement dropDownMessageType;
	@FindBy(id = "PayingParticipant")
	private static WebElement textboxPayingParticipantIdentifier;
	@FindBy(id = "BeneficiaryParticipant")
	private static WebElement textboxBeneficiaryParticipantIdentifier;
	@FindBy(id = "CollectingParticipant")
	private static WebElement textboxCollectingParticipantIdentifier;
	@FindBy(id = "ChannelIdentifier")
	private static WebElement dropDownChannelIdentifier;
	@FindBy(id = "CollectingLocation")
	private static WebElement textboxCollectionLocation;
	@FindBy(id = "CollectingBranchLocation")
	private static WebElement textboxCollectingBranchLocation;
	@FindBy(id = "Source")
	private static WebElement textboxSource;
	@FindBy(id = "dtpSiFromDatePicker")
	private  WebElement FromDate;
	@FindBy(id = "dtpSiToDatePicker")
	private static WebElement ToDate;
	@FindBy(id = "btnSubmit")
	private static WebElement buttonSearch;
	@FindBy(id = "btnBack")
	private static WebElement buttonBack;
	@FindBy(xpath  = "//div[@id='nav-title']")
	private static WebElement TitleItemSearchQuery;
	@FindBy(xpath  = "//button[@id='btnReset']")
	private static WebElement ButtonReset;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHeadInner']/table/thead/tr/th[18]")
	private static WebElement EntityState;
	
	
	
	
	
	
	// Description : This method validates Title on the Main search page	
	public SearchResultsPage ValidateTitle() {	
		assertTrue(getTitle(null).trim().contains("Item Search Query"), "Search Results Page launched");
		return this;
	}

	// Description : This method validates clear all/reset functionality on Main search page	
	public SearchResultsPage clickClearALL() {	
			pause(1500);
			clickElementEnter(ButtonReset);
			pause(3000);
			return this;	
		}
		
		// Description : This method validates Single page functionality on the Item search results page	
		public SearchResultsPage validateSinglePageDisplay() 
		{
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
		fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
		fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		String page = null;
		if(paginationGrid.isDisplayed()){
		if(activePaginationButton.isDisplayed()){
				page=activePaginationNthButton.getText();
				System.out.println("Page value is " +page);
				if(page.equals(1)){
					flag =true;
				}
			}
		}
		tempResult=	verifyTrue(page.equals("1") , "Search Results are not Displayed in Single Page");
		publishResults(tempResult,"Search results displayed in Single Page : "+activePaginationNthButton.isDisplayed()+"","Results should be displayed in Single Page", "Validation of single page Results screen");
		clickElementEnter(buttonBack);
		pause(2000);
		return this;
	}
		
		// Description : This method validates Next page functionality on the Item search results page	
		public SearchResultsPage validateNextPageButton() 
		{
			clickClearALL();
			String page = null;
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
			fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
			clickElementEnter(ButtonSearch);
			pause(9000);
			clickElementEnter(searchResultsInfo);
			if(paginationGrid.isDisplayed()){
					pause(200);
					clickElementEnter(NextPaginationButton);
					pause(2500);
					page=activePaginationNthButton.getText();
					if(page.equals(2)){
						flag =true;
					}
			}
			tempResult=	verifyTrue(page.equals("2") , "Next Page Button in Results are not Displayed");
			publishResults(tempResult,"Next Page Button in Results are Displayed : "+activePaginationNthButton.isEnabled()+"","Next Page Button in Results should be Displayed", "Validation of Next Page Button in Search Results");
			clickElementEnter(buttonBack);
			pause(2000);
			return this;
		}
		
		// Description : This method validates previous page navigation functionality on the Item search results page	
		public SearchResultsPage validatePreviousPageButton() 
		{
			clickClearALL();
			String page = null;
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
			fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
			clickElementEnter(ButtonSearch);
			pause(9000);
			clickElementEnter(searchResultsInfo);
			if(paginationGrid.isDisplayed()){
					pause(200);
					clickElementEnter(PreviousPaginationButton);
					page=activePaginationNthButton.getText();
					System.out.println("Page value is " +page);
					if(page.equals(1)){
						flag =true;
					}
			}
			tempResult=	verifyTrue(page.equals("1") , "Previous Page Button in Results are not Displayed");
			publishResults(tempResult,"Previous Page Button in Results are Displayed : "+activePaginationNthButton.isEnabled()+"","Previous Page Button in Results should be Displayed", "Validation of Previous Page Button in Search Results");
			clickElementEnter(buttonBack);
			pause(2000);
			return this;
		}
		
		// Description : This method validates page navigation functionality on the Item search results page	
		public SearchResultsPage validatePageNavigation() 
		{
			clickClearALL();
			String page = null;
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
			fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
			clickElementEnter(ButtonSearch);
			pause(9000);
			clickElementEnter(searchResultsInfo);
			if(paginationGrid.isDisplayed()){
					pause(200);
					clickElementEnter(FourthPageButton);
					pause(2500);
					page=activePaginationNthButton.getText();
					System.out.println("Page value is " +page);
					if(page.equals(4)){
						flag =true;
					}
			}
			tempResult=	verifyTrue(page.equals("4") , "Page Navigation did not work..");
			publishResults(tempResult,"Page Navigated to respective page : "+activePaginationNthButton.isEnabled()+"","Page should be Navigated", "Validation of Page navigation");
			clickElementEnter(buttonBack);
			pause(2000);
			return this;
		}
		
		// Description : This method validates Add to out box functionality on the Item search results page	
		public SearchResultsPage validateSelectRowAddToOutbox() 
		{
			clickClearALL();
			pause(1000);
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
			fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
			clickElementEnter(ButtonSearch);
			pause(9000);
			clickElementEnter(searchResultsInfo);
					String SortCode = firstRowSortCode.getText();
					System.out.println("SortCode is "+SortCode);
					clickElementSpace(OutboxcheckBox);
					pause(2200);
					clickElementEnter(AddToOutboxButton);
					pause(2300);
					clickElementEnter(PopUpOk);
					pause(2200);
					clickElementEnter(OutBoxMenu);
					pause(4000);
					String SortCodefromOutBox= getRowSortCodeFromOutBox.getText();
					System.out.println("Outbox SortCode is : "+SortCodefromOutBox);
					if(SortCode.equals(SortCodefromOutBox))
					{
						System.out.println("Selected item added to Outbox");
					}
			tempResult=	verifyTrue(SortCode.equals(SortCodefromOutBox) , "Selected Item not added to Outbox");
			publishResults(tempResult,"Selected item added to Outbox : "+SortCode.equals(SortCodefromOutBox)+"","Selected Item should be added to outbox", "Validation of Outbox");
			return this;
		}
		
		
		// Description : This method validates entity state column on the Item search results page	
				public SearchResultsPage validateEntityStateColumnOnSearchResultsPage() 
				{
					tempResult=	verifyTrue(EntityState.isDisplayed() , "Entity State Column is not displayed on search results page");
					publishResults(tempResult,"Entity state column is displayed : "+EntityState.isDisplayed()+"","Entity state column should be displayed on search results page", "Validation of Entity state column on search results page");
					clickElementEnter(buttonBack);
					pause(2000);
					return this;
				}
				
		
		// Description : This method validates sub queries functionality on the Item search results page	
		public SearchResultsPage validateSubQueries() 
		{
			clickElementEnter(ItemSearchQueryMenu);
			pause(2100);
			String NavTitleText = "Item Update History - Results";
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
			fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
			clickElementEnter(ButtonSearch);
			pause(9000);
			JavascriptExecutor ex=(JavascriptExecutor)driver;
			ex.executeScript("arguments[0].click();", SubQueryLink);
			pause(1000);
			verifyTrue(itemUpdateHistorySubQuery.isDisplayed(), "itemUpdateHistorySubQuery is not displayed");
			verifyTrue(itemStateHistorySubQuery.isDisplayed(), "itemStateHistorySubQuery is not displayed ");
			verifyTrue(DebitFraudItemsSubQuery.isDisplayed(), "DebitFraudItemsSubQuery is not displayed");
			verifyTrue(CreditFraudItemsSubQuery.isDisplayed(), "CreditFraudItemsSubQuery is not displayed ");
			pause(1000);	
			JavascriptExecutor exe=(JavascriptExecutor)driver;
			exe.executeScript("arguments[0].click();", itemUpdateHistorySubQuery);
			pause(2000);
			if(NavigationTitle.getText().equals("Item Update History - Results"))
			tempResult=	verifyTrue(NavigationTitle.getText().equals(NavTitleText),"Item Update History SubQuery Results are not displayed");
			publishResults(tempResult,NavigationTitle.getText(),NavTitleText,"Item Update History SubQuery Validation");
			pause(2000);
			return this;
		}
		
		// Description : This method validates Horizontal scroll on the Item search results page	
		public SearchResultsPage validateHorizontalScroll() 
		{
			clickElementEnter(ItemSearchQueryMenu);
			pause(2000);
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
			fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
			clickElementEnter(ButtonSearch);
			pause(9000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", AccountColumnName);
			pause(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", CurrencyColumn);
			pause(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", CollectingPIDColumnName);
			pause(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", NarrativeColumnName);
			pause(4000);
			tempResult=	verifyTrue(NarrativeColumnName.isDisplayed(), "user should be able to perform horizontal scroll");
			publishResults(tempResult,"User able to perform  horizontal scroll: "+NarrativeColumnName.isDisplayed()+"","User should be able to perform horizontal scroll", "Validaiton for Horizontal scroll");
			return this;
		}
		
		// Description : This method validates Vertical scroll on the Item search results page	
		public SearchResultsPage validateVerticalScroll() 
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Record1);
			pause(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Record5);
			pause(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Record8);
			pause(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Record12);
			pause(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Record15);
			pause(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Record20);
			pause(2000);
			tempResult=	verifyTrue(Record20.isDisplayed(), "user should be able to perform vertical scroll");
			publishResults(tempResult,"User able to perform  vertical scroll: "+Record20.isDisplayed()+"","User should be able to perform vertical scroll", "Validaiton for vertical scroll");
		return this;
		}
		
		// Description : This method validates Single click or selection of item on the Item search results page	
		public SearchResultsPage validateSingleClick() 
		{
			JavascriptExecutor ex=(JavascriptExecutor)driver;
			ex.executeScript("arguments[0].click();", firstRowSortCode);
			pause(9000);
			if(Record1.getAttribute("class").contains("selected"))
			{
				System.out.println("Record is selected");
			}else{
				System.out.println("Record is Not selected");
			}
			tempResult=	verifyTrue(Record1.getAttribute("class").contains("selected"), "User should be able to select the record");
			publishResults(tempResult,"User able to select single record: "+Record1.getAttribute("class").contains("selected")+"","User should be able to select single record", "Validaiton for selecting single record");
		return this;
		}
		
		// Description : This method validates Tool tips for all columns on the Item search results page	
		public SearchResultsPage validateToolTip() 
		{
			String ProcessingDateColumnToolTip = "The date that the item was processed through clearing";
			String SerialReferenceColumnToolTip = "Credit reference or cheque serial number";
			String SortCodeColumnToolTip = "Sort code of the item";
			String AccountColumnToolTip = "Account number of the item";
			String TransactionCodeColumnToolTip = "Transaction Code";
			String AmountColumnToolTip = "Value amount of the item";
			String DeleteItemIndiacatorColumnToolTip = "Deleted Item Indicator";
			String ClearingDinColumnToolTip = "Clearing DIN";
			String BranchDinColumnToolTip = "Branch/IBDE DIN";
			String PassNumberColumnToolTip = "Pass Number";
			String RecordTypeColumnToolTip = "Record Type";
			String ImageIndicatorColumnToolTip = "Image Indicator";
			tempResult=	verifyTrue(ProcessingDateColumn.getAttribute("data-original-title").equals(ProcessingDateColumnToolTip), "User should be able to capture Processign date tool tip");
			publishResults(tempResult,"User able to capture tool tip for Processing date: "+ProcessingDateColumn.getAttribute("data-original-title").equals(ProcessingDateColumnToolTip)+"","User should be able to capture Processign date tool tip", "Validaiton for capturing Processign date tool tip");
			tempResult=	verifyTrue(SerialReferenceColumn.getAttribute("data-original-title").equals(SerialReferenceColumnToolTip), "User should be able to capture SerialReference tool tip");
			publishResults(tempResult,"User able to capture tool tip for Serial reference: "+SerialReferenceColumn.getAttribute("data-original-title").equals(SerialReferenceColumnToolTip)+"","User should be able to capture Processign date tool tip", "Validaiton for capturing Processign date tool tip");
			tempResult=	verifyTrue(SortCodeColumn.getAttribute("data-original-title").equals(SortCodeColumnToolTip), "User should be able to capture Sort Code tool tip");
			publishResults(tempResult,"User able to capture tool tip for Sort Code: "+SortCodeColumn.getAttribute("data-original-title").equals(SortCodeColumnToolTip)+"","User should be able to capture Sort Code tool tip", "Validaiton for capturing Sort Code tool tip");
			tempResult=	verifyTrue(AccountColumn.getAttribute("data-original-title").equals(AccountColumnToolTip), "User should be able to capture Account tool tip");
			publishResults(tempResult,"User able to capture tool tip for Account: "+AccountColumn.getAttribute("data-original-title").equals(AccountColumnToolTip)+"","User should be able to capture Account tool tip", "Validaiton for capturing Account tool tip");
			tempResult=	verifyTrue(TransactionCodeColumn.getAttribute("data-original-title").equals(TransactionCodeColumnToolTip), "User should be able to capture TransactionCode tool tip");
			publishResults(tempResult,"User able to capture tool tip for TransactionCode: "+TransactionCodeColumn.getAttribute("data-original-title").equals(TransactionCodeColumnToolTip)+"","User should be able to capture Transaction Code tool tip", "Validaiton for capturing Transaction Code tool tip");
			tempResult=	verifyTrue(AmountColumn.getAttribute("data-original-title").equals(AmountColumnToolTip), "User should be able to capture Amount Tool Tip ");
			publishResults(tempResult,"User able to capture tool tip for Amount: "+AmountColumn.getAttribute("data-original-title").equals(AmountColumnToolTip)+"","User should be able to capture Amount tool tip", "Validaiton for capturing Amount tool tip");
			tempResult=	verifyTrue(DeletedItemIndicatorColumn.getAttribute("data-original-title").equals(DeleteItemIndiacatorColumnToolTip), "User should be able to capture Delete Item Indiacator ToolTip ");
			publishResults(tempResult,"User able to capture tool tip for Deleted Item Indicator: "+DeletedItemIndicatorColumn.getAttribute("data-original-title").equals(DeleteItemIndiacatorColumnToolTip)+"","User should be able to capture Delete Item Indiacator  tool tip", "Validaiton for capturing Delete Item Indiacator  tool tip");
			tempResult=	verifyTrue(ClearingDinColumn.getAttribute("data-original-title").equals(ClearingDinColumnToolTip), "User should be able to capture Clearing Din tool tip");
			publishResults(tempResult,"User able to capture tool tip for Clearing Din: "+ClearingDinColumn.getAttribute("data-original-title").equals(ClearingDinColumnToolTip)+"","User should be able to capture Clearing Din tool tip", "Validaiton for capturing Clearing Din tool tip");
			tempResult=	verifyTrue(BranchIBDEDINColumn.getAttribute("data-original-title").equals(BranchDinColumnToolTip), "User should be able to capture Branch IBDEDIN tool tip");
			publishResults(tempResult,"User able to capture tool tip for Branch IBDEDIN: "+BranchIBDEDINColumn.getAttribute("data-original-title").equals(BranchDinColumnToolTip)+"","User should be able to capture Branch IBDE DIN tool tip", "Validaiton for capturing Branch IBDE DIN tool tip");
			tempResult=	verifyTrue(PassNumberColumn.getAttribute("data-original-title").equals(PassNumberColumnToolTip), "User should be able to capture Pass Number tip");
			publishResults(tempResult,"User able to capture tool tip for PassNumber: "+PassNumberColumn.getAttribute("data-original-title").equals(PassNumberColumnToolTip)+"","User should be able to capture Pass Number tool tip", "Validaiton for capturing Pass Number  tool tip");
			tempResult=	verifyTrue(RecordTypeColumn.getAttribute("data-original-title").equals(RecordTypeColumnToolTip), "User should be able to capture RecordType tool tip");
			publishResults(tempResult,"User able to capture tool tip for Record Type: "+RecordTypeColumn.getAttribute("data-original-title").equals(RecordTypeColumnToolTip)+"","User should be able to capture Record Type tool tip", "Validaiton for capturing Record Type tool tip");
			tempResult=	verifyTrue(ImageIndicatorColumn.getAttribute("data-original-title").equals(ImageIndicatorColumnToolTip), "User should be able to capture Image Indicator tool tip");
			publishResults(tempResult,"User able to capture tool tip for ImageIndicator : "+ImageIndicatorColumn.getAttribute("data-original-title").equals(ImageIndicatorColumnToolTip)+"","User should be able to capture Image Indiacator tool tip", "Validaiton for capturing Image Indicator tool tip");
			clickItemSearchQueryMenu();
			pause(1000);
		return this;
		}
		
		// Description : This method perform click on Item search query menu 
		public SearchResultsPage clickItemSearchQueryMenu() {			
			clickElement(ItemSearchQueryMenu);
			return this;	
		}
		
		// Description : This method validates if out box button is displayed on the Item search results page	
		public SearchResultsPage validateIsOutBoxPresent() 
		{
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
		fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
		fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
		fillTextBox(textboxSerialReference, ICSPropertiesConfig.getSerialRef());
		clickElementEnter(ButtonSearch);
		pause(9000);
		tempResult=	verifyTrue(AddToOutboxButton.isDisplayed(), "Add to Outbox should be dsplayed");
		publishResults(tempResult,"Add to Outbox is dsplayed: "+AddToOutboxButton.isDisplayed()+"","Add to Outbox should be displayed", "Validating if Add to Outbox is displayed or not");
		return this;
		}
		
		// Description : This method validates Front and Rear Image for an Item on the Item search results page	
		public SearchResultsPage validateFrontAndRearImage() 
		{
		pause(2000);
		JavascriptExecutor ex=(JavascriptExecutor)driver;
		ex.executeScript("arguments[0].click();", firstRowSortCode);
		pause(6000);
		tempResult=	verifyTrue(FrontImage.isDisplayed(), "Front Image should be dsplayed");
		publishResults(tempResult,"Frount Image is dsplayed: "+FrontImage.isDisplayed()+"","Front Image should be displayed", "Validating if Front Image is displayed or not");
		tempResult=	verifyTrue(RearImage.isDisplayed(), "RearImage should be dsplayed");
		publishResults(tempResult,"RearImage  is dsplayed: "+RearImage.isDisplayed()+"","RearImage should be displayed", "Validating if RearImage is displayed or not");
		pause(1000);
		clickItemSearchQueryMenu();
		pause(5000);
		return this;
		}
		
		// Description : This method validates NO image scenario on the item search results page	
		public SearchResultsPage validateNoImage() 
		{
		pause(2000);
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
		fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
		fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
		clickElementEnter(ButtonSearch);
		pause(9000);
		tempResult=	verifyTrue(NoImage.isDisplayed(), "No Image should be displayed");
		publishResults(tempResult,"No Image is displayed: "+NoImage.isDisplayed()+"","No Image should be displayed", "Validating if No Image is displayed or not");
		pause(4000);
		clickItemSearchQueryMenu();
		pause(5000);
		return this;
		}

}
